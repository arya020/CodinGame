package com.crio.jukebox.services;

import java.util.ArrayList;
import java.util.List;

import com.crio.jukebox.entities.*;
import com.crio.jukebox.repositories.*;
public class Userservice implements IUserservice {
    
    //create user method
    //play
    private final IUserRepository userRepository;
    private final IPlaylistRepository playlistRepository;
    private final ISongRepository songRepository;
    

    public Userservice(IUserRepository userRepository,IPlaylistRepository playlistRepository,ISongRepository songRepository) {
        this.userRepository = userRepository;
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
    }

    @Override
    public User create(String name) {
    
        
        String id = Long.toString(userRepository.count()+1);
        List<Playlist> playlist = new ArrayList<>();
        User u = new User(id,name,playlist);
        userRepository.save(u);
        return u;
    }
    
    public Song playplaylist(String userid,String playlistid)
    {
        User u = userRepository.findById(userid).get();
        Playlist p = u.getplaylist().stream().filter(Playlist->userid.equals(Playlist.getuserid())).findAny().get();
        u.setcurrentsong(p.getsongids().get(0));
        u.setcurrentplaylist(p);
        return songRepository.findById(p.getsongids().get(0)).get();

    }

    public Song playsong(String userid,String s)
    {
        User u = userRepository.findById(userid).get();
        String currentsongid = u.getcurrentsong(); 
        Playlist currentplaylist = u.getcurrentplaylist();
        String id;

        if(s.equals("NEXT"))
        {
            String songId = currentplaylist.getsongids().stream().filter(songid->songid.equals(currentsongid)).findAny().get();
            int index = currentplaylist.getsongids().indexOf(songId);
            index+=1;
            if(index>currentplaylist.getsongids().size()-1)
            {
                index=0;
            }
            id = currentplaylist.getsongids().get(index);
            u.setcurrentsong(id);
        }
        else if(s.equals("BACK"))
        {
            String songId = currentplaylist.getsongids().stream().filter(songid->songid.equals(currentsongid)).findAny().get();
            int index = currentplaylist.getsongids().indexOf(songId);
            index-=1;
            if(index<0)
            {
                index = currentplaylist.getsongids().size()-1;
            }
            id = currentplaylist.getsongids().get(index);
            u.setcurrentsong(id);
        }
        else
        {
            u.setcurrentsong(s);
            id = u.getcurrentsong();
        }
        Song song = new Song();
        if(!currentplaylist.getsongids().contains(id))
        {
            throw new RuntimeException();
        }
        song = songRepository.findById(id).get();
        return song;
    }


    
}
