package com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.Model;

import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.R;

public class PicHolder extends RecyclerView.ViewHolder {
    public ImageView picture;

    public PicHolder(View itemView) {
        super(itemView);
        this.picture = (ImageView) itemView.findViewById(R.id.image);
    }
}
