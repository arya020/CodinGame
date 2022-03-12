package com.crio.jukebox.repositories;

import java.util.*;
import java.util.stream.Collectors;

import com.crio.jukebox.entities.Song;


public class SongRepository implements ISongRepository {
    
    private final Map<String,Song> songMap;
    private Integer autoIncrement = 0;

    public SongRepository(){
        songMap = new HashMap<String,Song>();
    }

    public SongRepository(Map<String,Song>songMap) {
        this.songMap = songMap;
        this.autoIncrement = songMap.size();
    }

    public Song save(Song entity) {
        if( entity.getId() == null ){
            autoIncrement++;
            Song u = new Song(autoIncrement.toString(),entity.getname(),entity.getgenre(),entity.getalbum(),entity.getartist());
            songMap.put(u.getId(),u);
            return u;
        }
        songMap.put(entity.getId(),entity);
        return entity;
    }
   
    public List<Song> findAll() {
        return songMap.values().stream().collect(Collectors.toList());
    
    }
    public Optional<Song> findById(String id) {
        return Optional.ofNullable(songMap.get(id));
    }

    @Override
    public Optional<Song> findByName(String name) {
    
        Song[] u = songMap.values().toArray(new Song[songMap.values().size()]); 
        Optional<Song> checkNull = Optional.empty();
        for(Song i:u)
        {
         
          if(i.getname() != name) {
              checkNull = Optional.empty();
          }
          if (i.getname() == name)
        {
            checkNull = Optional.of(i); 
            return checkNull;
        }
        }
        return checkNull;
    }

    @Override
    public void delete(Song entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub

    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    

}