package com.crio.jukebox.services;

import java.util.List;

import com.crio.jukebox.entities.*;
import com.crio.jukebox.repositories.AlbumRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.SongRepository;

public class Songservice implements ISongservice{
    
    //findbyid
    ISongRepository songRepository;
  
 
    public Songservice(ISongRepository songRepository) {

        this.songRepository = songRepository;
	}
	public Song create(String name,String genre,Album album,String owner,List<Artist> artist)
    {
         
        Song s = new Song(null,name,genre,album,artist);
        
        songRepository.save(s);
        return s;
    }
    public void delete(String Songid)
    {
        songRepository.deleteById(Songid);
    }
    
   
}