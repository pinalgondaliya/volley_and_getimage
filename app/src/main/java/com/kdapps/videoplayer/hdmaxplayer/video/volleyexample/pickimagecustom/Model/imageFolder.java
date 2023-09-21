package com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.Model;

public class imageFolder {
    private String FolderName;
    private String firstPic;
    private int numberOfPics = 0;
    private String path;

    public imageFolder() {
    }

    public imageFolder(String path2, String folderName) {
        this.path = path2;
        this.FolderName = folderName;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path2) {
        this.path = path2;
    }

    public String getFolderName() {
        return this.FolderName;
    }

    public void setFolderName(String folderName) {
        this.FolderName = folderName;
    }

    public int getNumberOfPics() {
        return this.numberOfPics;
    }

    public void setNumberOfPics(int numberOfPics2) {
        this.numberOfPics = numberOfPics2;
    }

    public void addpics() {
        this.numberOfPics++;
    }

    public String getFirstPic() {
        return this.firstPic;
    }

    public void setFirstPic(String firstPic2) {
        this.firstPic = firstPic2;
    }
}
