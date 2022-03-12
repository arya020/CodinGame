package com.crio.jukebox.repositories;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.*;
import com.crio.jukebox.entities.Playlist;

public class PlaylistRepository implements IPlaylistRepository {

    private final Map<String,Playlist> playlistMap;
    private Integer autoIncrement = 0;

    public PlaylistRepository(){
        playlistMap = new HashMap<String,Playlist>();
    }

    public PlaylistRepository(Map<String, Playlist> playlistMap) {
        this.playlistMap = playlistMap;
        this.autoIncrement = playlistMap.size();
    }

    public Playlist save(Playlist entity) {
        if( entity.getuserid() == null ){
            autoIncrement++;
            Playlist u = new Playlist(autoIncrement.toString(),entity.getuserid(),entity.getplaylistname(),entity.getsongids());
            playlistMap.put(u.getuserid(),u);
            return u;
        }
        playlistMap.put(entity.getuserid(),entity);
        return entity;
    }
   
    public List<Playlist> findAll() {
        return playlistMap.values().stream().collect(Collectors.toList());
    
    }
    public Optional<Playlist> findById(String id) {
        return Optional.ofNullable(playlistMap.get(id));
    }

    @Override
    public Optional<Playlist> findByName(String name) {
    
        Playlist[] u = playlistMap.values().toArray(new Playlist[playlistMap.values().size()]); 
        Optional<Playlist> checkNull = Optional.empty();
        for(Playlist i:u)
        {
         
          if(i.getplaylistname() != name) {
              checkNull = Optional.empty();
          }
          if (i.getplaylistname() == name)
        {
            checkNull = Optional.of(i); 
            return checkNull;
        }
        }
        return checkNull;
    }

    @Override
    public void delete(Playlist entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub

    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        
        return playlistMap.size();
    }

}