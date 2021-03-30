package com.webber;

import java.io.Serializable;

/**
 *
 * @author Nathaniel Webber
 */
public class Song implements Serializable {
    private int songID;
    private String name;
    private String artist;
    private String youtube;

    public Song() {
        this.songID = 0;
        this.name = "";
        this.artist = "";
        this.youtube = "";
    }
    
    public Song(int songID, String name, String artist, String youtube) {
        this.songID = songID;
        this.name = name;
        this.artist = artist;
        this.youtube = youtube;
    }
    
    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }
    
    public String toString() {
        return "Title: " + name + "\nArtist: " + artist + "    ";
    }
}
