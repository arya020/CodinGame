package com.crio.jukebox.services;

import java.util.List;

import javax.management.RuntimeErrorException;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.*;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;

public class Playlistservice implements IPlaylistservice {
    
    //create,modify,delete playlist
    IUserRepository userRepository;
    IPlaylistRepository playlistRepository;

    public Playlistservice(IUserRepository userRepository,IPlaylistRepository playlistRepository)
    {
        this.userRepository = userRepository;
        this.playlistRepository = playlistRepository;
    }

    @Override
    public Playlist create(String userid,String playlistname,List<String> songsid)
    {
        if(userRepository.findById(userid)==null)
        {
            //throw new UserNotFoundException();
            throw new RuntimeException();
        }
        String id = Long.toString(playlistRepository.count()+1);
        Playlist playlist = new Playlist(id,userid, playlistname, songsid);
        User u = userRepository.findById(userid).get();
        u.addplaylist(playlist);
        playlistRepository.save(playlist);
        playlist.getuserid();
        return playlist;
    }

   @Override
    public void deleteplaylist(String userid,String playlistid)
    {
        User u = userRepository.findById(userid).get();
        u.deleteplaylist(playlistRepository.findById(userid).get());


    }

    @Override
    public Playlist addsong(String userid,String playlistid,List<String> songids)
    {
        User u = userRepository.findById(userid).get();
        u.getcurrentplaylist().add(songids);
        playlistRepository.findById(playlistid).get().add(songids);
        playlistRepository.save(playlistRepository.findById(playlistid).get());
        return u.getcurrentplaylist();
    }

    @Override
    public Playlist deletesong(String userid,String playlistid,List<String> songids)
    {
        User u = userRepository.findById(userid).get();
        u.getcurrentplaylist().delete(songids);
        playlistRepository.findById(playlistid).get().delete(songids);
        return u.getcurrentplaylist();
    }
  


    

    
}