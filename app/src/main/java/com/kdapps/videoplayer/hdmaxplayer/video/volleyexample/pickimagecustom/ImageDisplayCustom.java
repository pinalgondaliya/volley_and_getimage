package com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.R;

public class ImageDisplayCustom extends AppCompatActivity {

    private byte[] byteArray;
    private Bundle extras;
    private Bitmap bmp1;
    private ImageView imggallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display_custom);

        imggallery = findViewById(R.id.imggallery);

        Bundle extras2 = getIntent().getExtras();
        this.extras = extras2;
        byte[] byteArray2 = extras2.getByteArray("picture");
        this.byteArray = byteArray2;
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArray2, 0, byteArray2.length);
        this.bmp1 = decodeByteArray;
        this.imggallery.setImageBitmap(decodeByteArray);
    }
}