package com.crio.jukebox.entities;

import java.util.List;

public class Playlist {

    private String id;
    private String userid;
    private List<String> songIds;
    private String playlistname;
    
    public Playlist(String id,String userid, String playlistname,List<String> songIds)
    {
        this.id = id;
        this.userid = userid;
        this.playlistname = playlistname;
        this.songIds = songIds;
    }

    public String getuserid()
    {
        return userid;
    }
    public String getplaylistname()
    {
        return playlistname;
    }
    public List<String> getsongids()
    {
        return songIds;
    }
    public void add(List<String> songid)
    {
        for(String id:songid)
        {
            songIds.add(id);
        }
    }
    public void delete(List<String> songid)
    {
        for(String id:songid)
        {
            songIds.remove(id);
        }
        
    }

    @Override
    public String toString() {
        return "Playlist ID - "+id;
    }

	public String getid() {
		return id;
	}

	
}