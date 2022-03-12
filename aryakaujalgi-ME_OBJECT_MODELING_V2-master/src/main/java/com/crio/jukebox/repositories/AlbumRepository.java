package com.crio.jukebox.repositories;

import java.util.*;
import java.util.stream.Collectors;

import com.crio.jukebox.entities.Album;

public class AlbumRepository implements IAlbumRepository {

    private final Map<String,Album> albumMap;
    private Integer autoIncrement = 0;

    public AlbumRepository(){
        albumMap = new HashMap<String,Album>();
    }

    public AlbumRepository(Map<String,Album> albumMap) {
        this.albumMap = albumMap;
        this.autoIncrement = albumMap.size();
    }

    public Album save(Album entity) {
        if( entity.getId() == null ){
            autoIncrement++;
            Album u = new Album(autoIncrement.toString(),entity.getAlbumName(),entity.getowner(),entity.getartistsongs());
            albumMap.put(u.getId(),u);
            return u;
        }
        albumMap.put(entity.getId(),entity);
        return entity;
    }
   
    public List<Album> findAll() {
        return albumMap.values().stream().collect(Collectors.toList());
    
    }
    public Optional<Album> findById(String id) {
        return Optional.ofNullable(albumMap.get(id));
    }

    @Override
    public Optional<Album> findByName(String name) {
    
        Album[] u = albumMap.values().toArray(new Album[albumMap.values().size()]); 
        Optional<Album> checkNull = Optional.empty();
        for(Album i:u)
        {
         
          if(i.getAlbumName() != name) {
              checkNull = Optional.empty();
          }
          if (i.getAlbumName() == name)
        {
            checkNull = Optional.of(i); 
            return checkNull;
        }
        }
        return checkNull;
    }

    @Override
    public void delete(Album entity) {
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