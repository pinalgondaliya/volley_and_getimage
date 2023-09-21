package com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.R;
import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.Model.imageFolder;
import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.interfaces.itemClickListener;
import java.util.ArrayList;

public class pictureFolderAdapter extends RecyclerView.Adapter<pictureFolderAdapter.FolderHolder> {
    private Context folderContx;
    private ArrayList<imageFolder> folders;
    private itemClickListener listenToClick;

    public pictureFolderAdapter(ArrayList<imageFolder> folders2, Context folderContx2, itemClickListener listen) {
        this.folders = folders2;
        this.folderContx = folderContx2;
        this.listenToClick = listen;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public FolderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FolderHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery, parent, false));
    }

    public void onBindViewHolder(FolderHolder holder, int position) {
        final imageFolder folder = this.folders.get(position);
        Glide.with(this.folderContx).load(folder.getFirstPic()).apply(new RequestOptions().centerCrop()).into(holder.folderPic);
        holder.folderSize.setText("" + folder.getNumberOfPics() + " Media");
        holder.folderName.setText("" + folder.getFolderName());
        holder.folderPic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pictureFolderAdapter.this.listenToClick.onPicClicked(folder.getPath(), folder.getFolderName());
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.folders.size();
    }

    public class FolderHolder extends RecyclerView.ViewHolder {
        TextView folderName;
        ImageView folderPic;
        TextView folderSize;

        public FolderHolder(View itemView) {
            super(itemView);
            this.folderPic = (ImageView) itemView.findViewById(R.id.imageView);
            this.folderName = (TextView) itemView.findViewById(R.id.textView_path);
            this.folderSize = (TextView) itemView.findViewById(R.id.textViewCount);
        }
    }
}
