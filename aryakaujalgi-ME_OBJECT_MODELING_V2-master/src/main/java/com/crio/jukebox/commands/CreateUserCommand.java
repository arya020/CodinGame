package com.crio.jukebox.commands;

import java.util.List;

import com.crio.jukebox.commands.ICommand;
import com.crio.jukebox.services.IUserservice;

public class CreateUserCommand implements ICommand {

    private final IUserservice userService;

    public CreateUserCommand(IUserservice userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        System.out.print(userService.create(tokens.get(1)));
        
    }
    
}