package com.crio.jukebox.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.crio.jukebox.entities.*;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.services.Albumservice;
import com.crio.jukebox.services.IAlbumservice;
import com.crio.jukebox.services.ISongservice;

public class LoadDataCommand implements ICommand {

    SongRepository songrepository;
    private final ISongservice songservice;
    private final IAlbumservice albumservice;

    public LoadDataCommand(ISongservice songservice,IAlbumservice albumservice)
    {
        this.songservice = songservice;
        this.albumservice = albumservice;
    }

    @Override
    public void execute(List<String> tokens) {
        
        try {

            BufferedReader br = Files.newBufferedReader(Paths.get(tokens.get(1)));
            // CSV file delimiter
            String DELIMITER = ",";
            String hash = "#";
        
            // read the file line by line
            String line;
            while ((line = br.readLine()) != null) {
        
                String[] song = line.split(DELIMITER);
                String[] artists = song[4].split(hash);
                List<Artist> artist = new ArrayList<>();
                int i=0;
                for(String a:artists)
                {
                    Artist subartist = new Artist(a);
                    artist.add(subartist);
                    i++;
                }
                Album album = albumservice.create(song[2],artist.get(0));
                Song s = songservice.create(song[0],song[1],album,song[3], artist);

            }
            System.out.println("Songs Loaded successfully");
        
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}