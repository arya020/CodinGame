package com.crio.codingame.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.List;
import java.util.stream.Collectors;

import com.crio.codingame.dtos.UserRegistrationDto;
import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.ContestStatus;
import com.crio.codingame.entities.RegisterationStatus;
import com.crio.codingame.entities.ScoreOrder;
import com.crio.codingame.entities.User;
import com.crio.codingame.exceptions.ContestNotFoundException;
import com.crio.codingame.exceptions.InvalidContestException;
import com.crio.codingame.exceptions.InvalidOperationException;
import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.codingame.repositories.IContestRepository;
import com.crio.codingame.repositories.IUserRepository;
import com.crio.codingame.repositories.UserRepository;

public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IContestRepository contestRepository;

    public UserService(IUserRepository userRepository, IContestRepository contestRepository) {
        this.userRepository = userRepository;
        this.contestRepository = contestRepository;
    }
    // TODO: CRIO_TASK_MODULE_SERVICES
    // Create and store User into the repository.
    @Override
    public User create(String name) {
    
        
        String id = Long.toString(userRepository.count()+1);
        User u = new User(id,name,0);
        userRepository.save(u);
        return u;
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Get All Users in Ascending Order w.r.t scores if ScoreOrder ASC.
    // Or
    // Get All Users in Descending Order w.r.t scores if ScoreOrder DESC.

    @Override
    public List<User> getAllUserScoreOrderWise(ScoreOrder scoreOrder) throws InvalidOperationException {


      List<User> u = new ArrayList<>();
      if(scoreOrder==ScoreOrder.ASC)
      {
       // u= userRepository.findAll().stream().sorted().collect(Collectors.toList());  
        u=userRepository.findAll().stream().sorted((u1,u2)->u1.getScore().compareTo(u2.getScore())).collect(Collectors.toList());
        return u;
    }
      
      return userRepository.findAll().stream().sorted((u1,u2)->u2.getScore().compareTo(u1.getScore())).collect(Collectors.toList());     
     //return Collections.emptyList();
    }

/*    @Override
    public UserRegistrationDto attendContest(String contestId, String userName) throws ContestNotFoundException, UserNotFoundException,InvalidOperationException {
        Contest contest = contestRepository.findById(contestId).orElseThrow(ContestNotFoundException::new);
        User user = userRepository.findByName(userName).orElseThrow(UserNotFoundException::new);
        if(contest.getContestStatus().equals(ContestStatus.IN_PROGRESS)||contest.getContestStatus().equals(ContestStatus.ENDED))
        {
            throw new InvalidOperationException();
        }
        
        if(contest.getCreator().getName().equals(userName))
        {
            throw new InvalidOperationException();   
        }
      
        if(user.checkIfContestExists(contest))
        {
            throw new InvalidOperationException();
        }
        user.addContest(contest);
       */
    


   

    @Override
    public UserRegistrationDto attendContest(String contestId, String userName) throws ContestNotFoundException, UserNotFoundException, InvalidOperationException {
        Contest contest = contestRepository.findById(contestId).orElseThrow(() -> new ContestNotFoundException("Cannot Attend Contest. Contest for given id:"+contestId+" not found!"));
        User user = userRepository.findByName(userName).orElseThrow(() -> new UserNotFoundException("Cannot Attend Contest. User for given name:"+ userName+" not found!"));
        if(contest.getContestStatus().equals(ContestStatus.IN_PROGRESS)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is in progress!");
        }
        if(contest.getContestStatus().equals(ContestStatus.ENDED)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is ended!");
        }
        if(user.checkIfContestExists(contest)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is already registered!");
        }
        user.addContest(contest);
        userRepository.save(user);
        return new UserRegistrationDto(contest.getName(), user.getName(),RegisterationStatus.REGISTERED);
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Withdraw the user from the contest
    // Hint :- Refer Unit Testcases withdrawContest method

    @Override
    public UserRegistrationDto withdrawContest(String contestId, String userName) throws ContestNotFoundException, UserNotFoundException, InvalidOperationException {
        
        //String contestName = contestRepository.findById(contestId).get().getName();
        Optional c1 = contestRepository.findById(contestId);
        Optional u = userRepository.findByName(userName);
        if(!c1.isPresent())
        {
            throw new ContestNotFoundException("Cannot Withdraw Contest. Contest for given id:"+contestId+" not found!");
        }
        if(!u.isPresent())
        {
            System.out.println("Cannot Withdraw Contest. User for given name:"+ userName+" not found!");
            throw new UserNotFoundException();
        } 

        User u1 = (User)u.get();
        if(!u1.checkIfContestExists((Contest)c1.get()))
        {
            throw new InvalidOperationException();
        }

        Contest c = (Contest) c1.get();
        if(c.getCreator().getName()==userName)
        {
            throw new InvalidOperationException("Cannot Withdraw Contest. Contest Creator:"+userName+ "not allowed to withdraw contest!");
        }
        if(c.getContestStatus()==ContestStatus.ENDED)
        {
            throw new InvalidOperationException("Cannot Withdraw Contest. Contest for given id:"+contestId+" is ended!");
        }
        if(c.getContestStatus()==ContestStatus.IN_PROGRESS)
        {
            throw new InvalidOperationException("Cannot Withdraw Contest. Contest for given id:"+contestId+" is in progress!");
        }
        
       
        u1.deleteContest(c);
        userRepository.save(u1);
        UserRegistrationDto d = new UserRegistrationDto(c.getName(), userName, RegisterationStatus.NOT_REGISTERED);
        return d;
    }
    
}
