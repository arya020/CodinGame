package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;

import com.crio.jukebox.commands.ICommand;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.services.IPlaylistservice;
import com.crio.jukebox.services.ISongservice;
import com.crio.jukebox.services.Songservice;

public class DeleteSongCommand implements ICommand {

    private final IPlaylistservice playlistservice;

    public DeleteSongCommand(IPlaylistservice playlistservice)
    {
        this.playlistservice = playlistservice;
    }
    
    @Override
    public void execute(List<String> tokens) {
        
        List<String> songids = new ArrayList<>();
        int i = 4;
        while(i<=tokens.size()-1)
        {
        songids.add(tokens.get(i));
        i++;
        }
       
        Playlist p = playlistservice.deletesong(tokens.get(2),tokens.get(3), songids);
        System.out.println(p+"\n"+"Playlist Name - "+p.getplaylistname());
        System.out.print("Song IDs - ");
        for(String s:p.getsongids())
        {
            if(s!=p.getsongids().get(p.getsongids().size()-1))
            {
            System.out.print(s+" ");
            }
            else
            {
                System.out.print(s+"\n");
            }
        }
    }
    
}