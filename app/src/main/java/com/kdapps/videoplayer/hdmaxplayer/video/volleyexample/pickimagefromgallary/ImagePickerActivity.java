package com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagefromgallary;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.R;

import java.io.IOException;

public class ImagePickerActivity extends AppCompatActivity {

    Button BSelectImage;

    // One Preview Image
    ImageView IVPreviewImage;

    // constant to compare
    // the activity result code
    int SELECT_PICTURE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_picker);

        IVPreviewImage = findViewById(R.id.IVPreviewImage);
        BSelectImage = findViewById(R.id.BSelectImage);

        BSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

    }


    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                // update the preview image in the layout
                if (selectedImageUri != null) {
//                    Bitmap selectedImageBitmap = null;
//                    try {
//                        selectedImageBitmap = MediaStore.Images.Media.getBitmap(
//                                this.getContentResolver(),
//                                selectedImageUri);
//                        IVPreviewImage.setImageBitmap(selectedImageBitmap);
//                        Intent intent = new Intent(ImagePickerActivity.this, ImageDisplayActivity.class);
//                        intent.putExtra("bitmapdisplay", selectedImageUri.toString());
//                        startActivity(intent);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                    IVPreviewImage.setImageURI(selectedImageUri);
                    Intent intent = new Intent(ImagePickerActivity.this, ImageDisplayActivity.class);
                    intent.putExtra("bitmapdisplay", selectedImageUri.toString());
                    startActivity(intent);

                } else {
                    // Handle the case when selectedImageUri is null
                }
            }
        }
    }

//    ActivityResultLauncher<Intent> launchSomeActivity = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            result -> {
//                if (result.getResultCode() == Activity.RESULT_OK) {
//                    Intent data = result.getData();
//                    if (data != null) {
//                        Uri selectedImageUri = data.getData();
//                        if(selectedImageUri !=null)
//
//    {
//        Bitmap selectedImageBitmap = null;
//        try {
//            selectedImageBitmap = MediaStore.Images.Media.getBitmap(
//                    this.getContentResolver(),
//                    selectedImageUri);
//            IVPreviewImage.setImageBitmap(selectedImageBitmap);
//            Intent intent = new Intent(ImagePickerActivity.this, ImageDisplayActivity.class);
//            intent.putExtra("bitmapdisplay", selectedImageBitmap);
//            startActivity(intent);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    } else
//
//    {
//        // Handle the case when selectedImageUri is null
//    }
//                    } else {
//                        // Handle the case when data is null
//                    }
//                }
//            });

}