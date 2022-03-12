package com.crio.jukebox.services;

import java.util.List;

import com.crio.jukebox.entities.Playlist;

public interface IPlaylistservice {
    public Playlist create(String userid,String playlistname,List<String> songsid);
    public void deleteplaylist(String userid,String playlistid);
    public Playlist addsong(String userid,String playlistid,List<String>songids);
    public Playlist deletesong(String userid,String playlistid,List<String>songids);
}