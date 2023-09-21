package com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.Model;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AutoFitRecyclerView extends RecyclerView {
    private int columnWidth = -1;
    private GridLayoutManager manager;

    public AutoFitRecyclerView(Context context) {
        super(context);
        initialize(context, null);
    }

    public AutoFitRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    public AutoFitRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize(context, attrs);
    }

    private void initialize(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, new int[]{16843031});
            this.columnWidth = array.getDimensionPixelSize(0, -1);
            array.recycle();
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        this.manager = gridLayoutManager;
        setLayoutManager(gridLayoutManager);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.recyclerview.widget.RecyclerView
    public void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        if (this.columnWidth > 0) {
            this.manager.setSpanCount(Math.max(1, getMeasuredWidth() / this.columnWidth));
        }
    }
}
