package com.example.moshuying.Unit6.SongLib;

public class Song {
    private String fileName;
    private String title;
    private String singer;
    private String album;
    private String size;
    private String filePath;
    private int duration;
    public Song(String fileName, String title, int duration,String singer, String album, String size, String filePath){
        this.fileName=fileName;
        this.title=title;
        this.singer=singer;
        this.album=album;
        this.size=size;
        this.filePath=filePath;
        this.duration = duration;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public String getFileName() {
        return fileName;
    }

    public String getSinger() {
        return singer;
    }

    public String getSize() {
        return size;
    }

    public String getTitle() {
        return title;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getDuration() {
        return duration;
    }
}
