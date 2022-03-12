package com.crio.jukebox.commands;

import java.util.List;

import com.crio.jukebox.commands.*;
import com.crio.jukebox.services.IPlaylistservice;

public class DeletePlaylistCommand implements ICommand {

    private final IPlaylistservice playlistservice;

    public DeletePlaylistCommand(IPlaylistservice playlistservice) {
        this.playlistservice = playlistservice;
    }


    @Override
    public void execute(List<String> tokens) {
      
        playlistservice.deleteplaylist(tokens.get(1),tokens.get(2));
        System.out.println("Delete Successful");

    }

   
}