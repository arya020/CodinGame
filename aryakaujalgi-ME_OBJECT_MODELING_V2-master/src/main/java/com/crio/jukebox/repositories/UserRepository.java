package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.crio.jukebox.entities.User;

public class UserRepository implements IUserRepository{

    private final Map<String,User> userMap;
    private Integer autoIncrement = 0;

    public UserRepository(){
        userMap = new HashMap<String,User>();
    }

    public UserRepository(Map<String, User> userMap) {
        this.userMap = userMap;
        this.autoIncrement = userMap.size();
    }

    public User save(User entity) {
        if( entity.getId() == null ){
            autoIncrement++;
            User u = new User(autoIncrement.toString(),entity.getname(),entity.getplaylist());
            userMap.put(u.getId(),u);
            return u;
        }
        userMap.put(entity.getId(),entity);
        return entity;
    }
   
    public List<User> findAll() {
        return userMap.values().stream().collect(Collectors.toList());
    
    }
    public Optional<User> findById(String id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public Optional<User> findByName(String name) {
    
        User[] u = userMap.values().toArray(new User[userMap.values().size()]); 
        Optional<User> checkNull = Optional.empty();
        for(User i:u)
        {
         
          if(i.getname() != name) {
              checkNull = Optional.empty();
          }
          if (i.getname() == name)
        {
            checkNull = Optional.of(i); 
            return checkNull;
        }
        }
        return checkNull;
    }

    @Override
    public void delete(User entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub

    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    
}