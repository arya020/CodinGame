package com.crio.jukebox.entities;

import java.util.*;
public class Song extends BaseEntity{
    
    private String name;
    private String genre;
    private Album album;
    private List<Artist> artists;
    
    public Song(String id,String name,String genre,Album album,List<Artist> artists)
    {
        
        this.id = id;
        this.genre = genre;
        this.album = album;
        this.name = name;
        this.artists = artists;
    }

    public Song() {
	}

	public String getname()
    {
        return name;
    }
    public List<Artist> getartist()
    {
        return artists;
    }
    public Album getalbum()
    {
        return album;
    }

	public String getgenre() {
		return genre;
    }

    @Override
    public String toString() {
        return "Song - "+name+"\n"+"Album - "+album.getAlbumName()+"\n";
    }
    

}