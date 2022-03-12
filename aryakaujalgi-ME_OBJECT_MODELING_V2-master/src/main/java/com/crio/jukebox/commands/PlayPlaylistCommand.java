package com.crio.jukebox.commands;

import java.util.List;

import com.crio.jukebox.entities.*;
import com.crio.jukebox.services.IUserservice;

public class PlayPlaylistCommand implements ICommand {

    private final IUserservice userService;

    public PlayPlaylistCommand(IUserservice userService)
    {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens)
    {
        Song song = userService.playplaylist(tokens.get(1), tokens.get(2));
        //System.out.println();
        System.out.print("Current Song Playing\n"+song+"Artists - ");
        for(Artist a:song.getartist())
        {
            if(a!=song.getartist().get(song.getartist().size()-1))
            {
            System.out.print(a+",");
            }
            else
            {
                System.out.print(a+"\n");
            }
        }
        //System.out.println(userService.playplaylist(tokens.get(1), tokens.get(2)));
    }
    
}