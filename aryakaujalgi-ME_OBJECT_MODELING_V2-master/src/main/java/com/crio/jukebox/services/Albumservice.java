package com.crio.jukebox.services;

import java.util.List;

import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.AlbumRepository;
import com.crio.jukebox.repositories.IAlbumRepository;

public class Albumservice implements IAlbumservice {

    private final IAlbumRepository albumrepository;

    public Albumservice(IAlbumRepository albumrepository)
    {
        this.albumrepository = albumrepository;
    }

    @Override
    public Album create(String albumname, Artist owner) {
       
        String id = Long.toString(albumrepository.count()+1);
        Album a = new Album(id,albumname,owner);
        albumrepository.save(a);
        return a;
        
    }

    

  
   
    
}