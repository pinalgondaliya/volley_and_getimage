package com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.R;
import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.Model.PicHolder;
import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.Model.pictureFacer;
import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.interfaces.itemClickListener;
import java.util.ArrayList;

public class picture_Adapter extends RecyclerView.Adapter<PicHolder> {
    private final itemClickListener picListerner;
    private Context pictureContx;
    private ArrayList<pictureFacer> pictureList;

    public picture_Adapter(ArrayList<pictureFacer> pictureList2, Context pictureContx2, itemClickListener picListerner2) {
        this.pictureList = pictureList2;
        this.pictureContx = pictureContx2;
        this.picListerner = picListerner2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public PicHolder onCreateViewHolder(ViewGroup container, int position) {
        return new PicHolder(LayoutInflater.from(container.getContext()).inflate(R.layout.pic_holder_item, container, false));
    }

    public void onBindViewHolder(final PicHolder holder, @SuppressLint("RecyclerView") final int position) {
        Glide.with(this.pictureContx).load(this.pictureList.get(position).getPicturePath()).apply(new RequestOptions().centerCrop()).into(holder.picture);
        ImageView imageView = holder.picture;
        ViewCompat.setTransitionName(imageView, String.valueOf(position) + "_image");
        holder.picture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                picture_Adapter.this.picListerner.onPicClicked(holder, position, picture_Adapter.this.pictureList);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.pictureList.size();
    }
}
