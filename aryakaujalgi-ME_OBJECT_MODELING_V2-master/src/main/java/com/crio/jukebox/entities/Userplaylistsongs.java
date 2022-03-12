package com.crio.jukebox.entities;

import java.util.*;

public class Userplaylistsongs {
    private final Map<Playlist,List<Song>> playlistsongsMap;

    public Userplaylistsongs(){
        playlistsongsMap = new HashMap<Playlist,List<Song>>();
    }

    public Userplaylistsongs(Map<Playlist, List<Song>> playlistsongsMap) {
        this.playlistsongsMap = playlistsongsMap;
    }


    public List<Song> getsongsbyplaylist(Playlist playlist){
        return playlistsongsMap.get(playlist);
    }
}