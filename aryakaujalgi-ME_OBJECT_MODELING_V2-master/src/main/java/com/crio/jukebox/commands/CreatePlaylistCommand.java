package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;

import com.crio.jukebox.commands.ICommand;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.services.IPlaylistservice;
import com.crio.jukebox.services.Playlistservice;

public class CreatePlaylistCommand implements ICommand {

    private final IPlaylistservice Playlistservice;

    public CreatePlaylistCommand(IPlaylistservice Playlistservice) {
        this.Playlistservice = Playlistservice;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub

        List<String> songids = new ArrayList<>();
        int i = 3;
        while(i<=tokens.size()-1)
        {
        songids.add(tokens.get(i));
        i++;
        }
        //System.out.print(Playlistservice.create(tokens.get(1),tokens.get(2),songids));
        Playlist p = Playlistservice.create(tokens.get(1),tokens.get(2),songids);
        System.out.print("Playlist ID - "+p.getid()+"\n");
        

    }

    
}