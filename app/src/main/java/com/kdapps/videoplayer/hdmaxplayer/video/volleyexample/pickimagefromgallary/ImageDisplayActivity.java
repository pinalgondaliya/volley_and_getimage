package com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagefromgallary;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.R;

public class ImageDisplayActivity extends AppCompatActivity {
    ImageView IVPreviewImage;
    String bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);

        IVPreviewImage = findViewById(R.id.IVPreviewImage);

        bitmap = getIntent().getStringExtra("bitmapdisplay");

        IVPreviewImage.setImageURI(Uri.parse(bitmap));

    }
}