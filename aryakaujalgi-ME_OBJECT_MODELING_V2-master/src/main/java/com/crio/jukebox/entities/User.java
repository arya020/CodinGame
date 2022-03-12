package com.crio.jukebox.entities;

import java.util.List;

public class User extends BaseEntity {
    
    private String name;
    private List<Playlist> playlists;
    private String currentsong;
    private Playlist currentplaylist;

    public User (String id,String name,List<Playlist>playlists)
    {
        this.id = id;
        this.name = name;
        this.playlists = playlists;
    }
   
    
	public String getname()
    {
        return name;
    }
    public List<Playlist> getplaylist()
    {
        return playlists;
    }
    public void setcurrentsong(String currentsong)
    {
        this.currentsong = currentsong;
    }
    public String getcurrentsong()
    {
        return currentsong;
    }

    public void addplaylist(Playlist playlist)
    {
        playlists.add(playlist);
    }
    public void deleteplaylist(Playlist playlist)
    {
        playlists.remove(playlist);
    }


	public void setcurrentplaylist(Playlist p) {
        this.currentplaylist = p;
	}


	public Playlist getcurrentplaylist() {
		return currentplaylist;
    }
    
    @Override
    public String toString() {
        return id+" "+name+"\n";
    }
 

}