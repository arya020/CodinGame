package com.crio.jukebox.services;

import java.util.List;

import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Song;

public interface IAlbumservice {
    public Album create(String albumname,Artist owner);
    
}