package com.crio.jukebox.entities;

import java.util.List;

public class Album extends BaseEntity {
    
    private final String Albumname;
    private Artist owner;
    private List<String> artistsongs;
   
    public Album(String id,String Albumname,Artist owner,List<String> artistsongs)
    {
        this.id = id;
        this.Albumname = Albumname;
        this.owner = owner;
        this.artistsongs = artistsongs;
    }
    public Album(String id,String Albumname,Artist owner)
    {
        this.id = id;
        this.Albumname = Albumname;
        this.owner = owner;
    }
  

    public String getAlbumName()
    {
        return Albumname;
    }
    public Artist getowner()
    {
        return owner;
    }
    public List<String> getartistsongs()
    {
        return artistsongs;
    }

    

}