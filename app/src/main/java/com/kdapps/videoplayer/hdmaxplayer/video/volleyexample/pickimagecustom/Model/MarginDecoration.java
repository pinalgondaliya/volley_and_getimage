package com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.Model;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.R;

public class MarginDecoration extends RecyclerView.ItemDecoration {
    private int margin;

    public MarginDecoration(Context context) {
        this.margin = context.getResources().getDimensionPixelSize(R.dimen.item_margin);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int i = this.margin;
        outRect.set(i, i, i, i);
    }
}
