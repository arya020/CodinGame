package com.crio.jukebox.entities;

import java.util.*;
public class Artist {
    
   private final String name;

    public Artist(String name)
    {
        this.name = name;
    }

    public String getname()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return name;
    }
    
}