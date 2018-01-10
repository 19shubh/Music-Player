package com.example.shubham.music;

import android.graphics.Bitmap;

public class Song {
    private String artist;
    private int duration;
    private long id;
    private String title;
    private Long albumId;
    private int minutes;

    public Song(long songID, String songTitle, String songArtist,Long albumId,int time) {
        this.id = songID;
        this.title = songTitle;
        this.artist = songArtist;
        this.duration = time;
        this.albumId = albumId;
        this.minutes = (time/1000)/60;
    }

    public long getID() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getArtist() {
        return this.artist;
    }

    public int getDuration() {
        return this.duration;
    }

    public String convertTime(int timeInMillisecond) {
        int seconds;
        int timeInSeconds = timeInMillisecond / 1000;
        int minutes = timeInSeconds / 60;
        seconds = timeInSeconds % 60;
        return Integer.toString(minutes) + ":" + String.format("%02d", new Object[]{Integer.valueOf(seconds)});
    }

    public int getMinutes(){
        return this.minutes;
    }

    public Long getAlbumId(){
        return albumId;
    }
}
