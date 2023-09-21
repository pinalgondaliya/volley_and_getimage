package com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.interfaces;

import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.Model.PicHolder;
import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.Model.pictureFacer;

import java.util.ArrayList;

public interface itemClickListener {
    void onPicClicked(PicHolder picHolder, int i, ArrayList<pictureFacer> arrayList);

    void onPicClicked(String str, String str2);
}
