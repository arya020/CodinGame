package com.crio.jukebox.commands;

import java.util.List;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.services.IPlaylistservice;
import com.crio.jukebox.services.Playlistservice;

public class ModifyPlaylistCommand implements ICommand {

    private IPlaylistservice playlistservice;

    public ModifyPlaylistCommand(IPlaylistservice playlistservice)
    {
        this.playlistservice = playlistservice;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub

        if(tokens.get(1).equals("ADD-SONG"))
        {
            AddSongCommand addsongcommand = new AddSongCommand(playlistservice);
            addsongcommand.execute(tokens);
        }
        else
        {
            DeleteSongCommand deletesongcommand = new DeleteSongCommand(playlistservice);
            deletesongcommand.execute(tokens);
        }


       

    }


        


}
