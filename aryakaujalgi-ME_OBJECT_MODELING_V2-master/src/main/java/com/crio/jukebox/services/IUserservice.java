package com.crio.jukebox.services;

import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;

public interface IUserservice {
    
    public User create(String name);
    public Song playplaylist(String userid,String playlistid);
    public Song playsong(String userid,String s);
    
}