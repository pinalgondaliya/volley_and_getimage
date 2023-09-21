package com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.Model;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;

public class GallerySquareImageView extends AppCompatImageView {
    public GallerySquareImageView(Context context) {
        super(context);
    }

    public GallerySquareImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public GallerySquareImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        setMeasuredDimension(measuredWidth, measuredWidth);
    }
}
