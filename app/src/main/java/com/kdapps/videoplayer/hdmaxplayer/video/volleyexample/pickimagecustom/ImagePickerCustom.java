package com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.R;
import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.Model.PicHolder;
import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.Model.pictureFacer;
import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.adapter.picture_Adapter;
import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.interfaces.itemClickListener;
import com.yalantis.ucrop.UCrop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImagePickerCustom extends AppCompatActivity implements itemClickListener {
    ArrayList<pictureFacer> allpictures;
    private byte[] byteArray;
    private Uri cropUri;
    private String destpath;
    String foldePath;
    TextView folderName;
    private int height;
    RecyclerView imageRecycler;
    private Uri imageuri;
    ProgressBar load;
    private Uri mDestinationUri;
    private File mFileTemp;
    String sample_cropped_image_name = "SampleCropImage.jpeg";
    private String uri;
    private int width;

    /* access modifiers changed from: protected */
    @SuppressLint("WrongConstant")
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_picker_custom);
        TextView textView = (TextView) findViewById(R.id.foldername);
        this.folderName = textView;
        textView.setText(getIntent().getStringExtra("folderName"));
        this.foldePath = getIntent().getStringExtra("folderPath");
        this.allpictures = new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        this.imageRecycler = recyclerView;
        recyclerView.hasFixedSize();
        this.imageRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        this.load = (ProgressBar) findViewById(R.id.loader);
        if (this.allpictures.isEmpty()) {
            this.load.setVisibility(0);
            this.allpictures = getAllImagesByFolder(this.foldePath);
            this.imageRecycler.setAdapter(new picture_Adapter(this.allpictures, this, this));
            this.load.setVisibility(8);
        }
        try {
            this.mDestinationUri = Uri.fromFile(new File(getCacheDir(), this.sample_cropped_image_name));
        } catch (Exception e) {
        }
    }

    @Override // com.pinal.photoeditorphotocollage.simpleimagegallery.utils.itemClickListener
    public void onPicClicked(PicHolder holder, int position, ArrayList<pictureFacer> pics) {
        String picturePath = pics.get(position).getPicturePath();
        this.uri = picturePath;
        if (BitmapFactory.decodeFile(picturePath) == null) {
            Toast.makeText(this, "Invalid Image", Toast.LENGTH_SHORT).show();
            return;
        }
        this.imageuri = Uri.parse("file://" + this.uri);
        File file = new File(getFilesDir().getAbsolutePath() + File.separator + "cropedimage");
        if (!file.exists()) {
            file.mkdirs();
        }
        if (file.exists()) {
            this.destpath = new File(file.getAbsolutePath(), "cropimage.jpg").getAbsolutePath();
        }
        newCrop(this.imageuri, this.destpath);
    }

    @Override // com.pinal.photoeditorphotocollage.simpleimagegallery.utils.itemClickListener
    public void onPicClicked(String pictureFolderPath, String folderName2) {
    }

    public ArrayList<pictureFacer> getAllImagesByFolder(String path) {
        ArrayList<pictureFacer> images = new ArrayList<>();
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "_display_name", "_size"}, "_data like ? ", new String[]{"%" + path + "%"}, null);
        try {
            cursor.moveToFirst();
            do {
                pictureFacer pic = new pictureFacer();
                pic.setPicturName(cursor.getString(cursor.getColumnIndexOrThrow("_display_name")));
                pic.setPicturePath(cursor.getString(cursor.getColumnIndexOrThrow("_data")));
                pic.setPictureSize(cursor.getString(cursor.getColumnIndexOrThrow("_size")));
                images.add(pic);
            } while (cursor.moveToNext());
            cursor.close();
            ArrayList<pictureFacer> reSelection = new ArrayList<>();
            for (int i = images.size() - 1; i > -1; i--) {
                reSelection.add(images.get(i));
            }
            return reSelection;
        } catch (Exception e) {
            e.printStackTrace();
            return images;
        }
    }

//==================================================================

    //get all images

//    public class ImageModel {
//        private long id;
//        private String displayName;
//        private String contentUri;
//
//        // Constructors, getters, and setters
//
//
//        public long getId() {
//            return id;
//        }
//
//        public void setId(long id) {
//            this.id = id;
//        }
//
//        public String getDisplayName() {
//            return displayName;
//        }
//
//        public void setDisplayName(String displayName) {
//            this.displayName = displayName;
//        }
//
//        public String getContentUri() {
//            return contentUri;
//        }
//
//        public void setContentUri(String contentUri) {
//            this.contentUri = contentUri;
//        }
//    }
//
//    private List<ImageModel> getAllImages() {
//        List<ImageModel> images = new ArrayList<>();
//
//        String[] projection = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DISPLAY_NAME, MediaStore.Images.Media.DATA};
//
//        try (Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null)) {
//            if (cursor != null) {
//                while (cursor.moveToNext()) {
//                    @SuppressLint("Range") long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media._ID));
//                    @SuppressLint("Range") String displayName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
//                    String contentUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id).toString();
//
//                    ImageModel imageModel = new ImageModel();
//                    imageModel.setId(id);
//                    imageModel.setDisplayName(displayName);
//                    imageModel.setContentUri(contentUri);
//
//                    images.add(imageModel);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return images;
//    }

    //===========================================================================================

    public void newCrop(Uri uri2, String destpath2) {
        try {
            UCrop.of(uri2, Uri.parse(destpath2)).withAspectRatio(4.0f, 4.0f).withMaxResultSize(720, 720).start(this);
        } catch (Exception e) {
            Toast.makeText(this, "Please try again", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == 69) {
            Uri resultUri = UCrop.getOutput(data);
            if (resultUri != null) {
                this.cropUri = resultUri;
                startWithUri(resultUri);
                handleCropResult(data);
            }
        } else if (resultCode == 96) {
            UCrop.getError(data);
        }
    }

    private void handleCropResult(Intent result) {
        Uri resultUri = UCrop.getOutput(result);
        if (resultUri != null && resultUri != null) {
            startWithUri(resultUri);
            Log.v("uriString", resultUri.toString());
        }
    }

    public void startWithUri(Uri uri2) {
        float he;
        float wd;
        File mFileTemp2 = new File(uri2.getPath());
        Bitmap bitmap = BitmapFactory.decodeFile(mFileTemp2.getAbsolutePath(), new BitmapFactory.Options());
        Bitmap bitmap11 = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), false);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        this.width = dm.widthPixels;
        this.height = dm.heightPixels;
        float wd2 = (float) bitmap11.getWidth();
        float he2 = (float) bitmap11.getHeight();
        float rat1 = wd2 / he2;
        float rat2 = he2 / wd2;
        int i = this.width;
        if (wd2 > ((float) i)) {
            wd = (float) i;
            he = wd * rat2;
        } else {
            int i2 = this.height;
            if (he2 > ((float) i2)) {
                he = (float) i2;
                wd = he * rat1;
            } else {
                wd = (float) i;
                he = wd * rat2;
            }
        }
        Bitmap bitmap112 = Bitmap.createScaledBitmap(bitmap11, (int) wd, (int) he, false);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap112.compress(Bitmap.CompressFormat.JPEG, 90, stream);
        this.byteArray = stream.toByteArray();
        Intent intent = new Intent(this, ImageDisplayCustom.class);
        intent.putExtra("picture", this.byteArray);
        startActivity(intent);
    }
}