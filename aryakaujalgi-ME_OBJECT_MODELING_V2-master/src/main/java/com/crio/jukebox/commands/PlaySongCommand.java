package com.crio.jukebox.commands;

import java.util.List;

import com.crio.jukebox.entities.*;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.services.*;

public class PlaySongCommand implements ICommand{
    
    private final IUserservice userService;
    SongRepository songrepository;
    public PlaySongCommand(IUserservice userService)
    {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens)
    {
        try
        {
        Song song = userService.playsong(tokens.get(1), tokens.get(2));
        //System.out.println("Current Song Playing\n");
       // System.out.println(song);
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
        }
        catch(RuntimeException e)
        {
            System.out.println("Given song id is not a part of the active playlist");
        }

    }
}