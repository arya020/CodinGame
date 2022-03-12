package com.crio.jukebox.services;

import java.util.List;

import com.crio.jukebox.entities.*;

public interface ISongservice {
    public Song create(String name,String genre,Album album,String owner,List<Artist> artist);
    public void delete(String songid);
    
}