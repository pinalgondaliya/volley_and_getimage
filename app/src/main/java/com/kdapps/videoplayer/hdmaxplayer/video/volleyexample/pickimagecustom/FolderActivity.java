package com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.R;
import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.Model.MarginDecoration;
import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.Model.PicHolder;
import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.Model.imageFolder;
import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.Model.pictureFacer;
import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.adapter.pictureFolderAdapter;
import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.interfaces.itemClickListener;

import java.util.ArrayList;

public class FolderActivity extends AppCompatActivity implements itemClickListener {
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    ImageView btnback;
    TextView empty;
    RecyclerView folderRecycler;
    TextView text;

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);
        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") != 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 1);
        }
        this.empty = (TextView) findViewById(R.id.empty);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.folderRecycler);
        this.folderRecycler = recyclerView;
        recyclerView.addItemDecoration(new MarginDecoration(this));
        this.folderRecycler.hasFixedSize();
        ArrayList<imageFolder> folds = getPicturePaths();
        if (folds.isEmpty()) {
            this.empty.setVisibility(View.VISIBLE);
        } else {
            this.folderRecycler.setAdapter(new pictureFolderAdapter(folds, this, this));
        }
        changeStatusBarColor();
        this.btnback = (ImageView) findViewById(R.id.btnback);
        this.text = (TextView) findViewById(R.id.text);
        this.btnback.setOnClickListener(new View.OnClickListener() {
            /* class com.pinal.photoeditorphotocollage.simpleimagegallery.MainActivity.AnonymousClass1 */

            public void onClick(View v) {
                FolderActivity.this.onBackPressed();
            }
        });
        this.text.setText("Select Album");
    }

//    private ArrayList<imageFolder> getPicturePaths() {
//        ArrayList<imageFolder> picFolders = new ArrayList<>();
//        ArrayList<String> picPaths = new ArrayList<>();
//        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "_display_name", "bucket_display_name", "bucket_id"}, null, null, null);
//        if (cursor != null) {
//            try {
//                cursor.moveToFirst();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        do {
//            imageFolder folds = new imageFolder();
//            cursor.getString(cursor.getColumnIndexOrThrow("_display_name"));
//            String folder = cursor.getString(cursor.getColumnIndexOrThrow("bucket_display_name"));
//            String datapath = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
//            String folderpaths = datapath.substring(0, datapath.lastIndexOf(folder + "/")) + folder + "/";
//            if (!picPaths.contains(folderpaths)) {
//                picPaths.add(folderpaths);
//                folds.setPath(folderpaths);
//                folds.setFolderName(folder);
//                folds.setFirstPic(datapath);
//                folds.addpics();
//                picFolders.add(folds);
//            } else {
//                for (int i = 0; i < picFolders.size(); i++) {
//                    if (picFolders.get(i).getPath().equals(folderpaths)) {
//                        picFolders.get(i).setFirstPic(datapath);
//                        picFolders.get(i).addpics();
//                    }
//                }
//            }
//        } while (cursor.moveToNext());
//        cursor.close();
//        for (int i2 = 0; i2 < picFolders.size(); i2++) {
//            Log.d("picture folders", picFolders.get(i2).getFolderName() + " and path = " + picFolders.get(i2).getPath() + " " + picFolders.get(i2).getNumberOfPics());
//        }
//        return picFolders;
//    }

    private ArrayList<imageFolder> getPicturePaths() {
        ArrayList<imageFolder> picFolders = new ArrayList<>();
        ArrayList<String> picPaths = new ArrayList<>();
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "_display_name", "bucket_display_name", "bucket_id"}, null, null, null);
        if (cursor != null) {
            try {
                cursor.moveToFirst();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        do {
            imageFolder folds = new imageFolder();
            cursor.getString(cursor.getColumnIndexOrThrow("_display_name"));
            String folder = cursor.getString(cursor.getColumnIndexOrThrow("bucket_display_name"));
            String datapath = cursor.getString(cursor.getColumnIndexOrThrow("_data"));

            // Check if datapath contains the folder name
            if (datapath.contains(folder + "/")) {
                String folderpaths;
                int lastIndex = datapath.lastIndexOf(folder + "/");
                if (lastIndex != -1) {
                    folderpaths = datapath.substring(0, lastIndex) + folder + "/";
                } else {
                    // Handle the case when the substring cannot be created
                    // You can set folderpaths to some default value or take appropriate action.
                    folderpaths = ""; // Set to a default value or handle the error.
                }

                if (!picPaths.contains(folderpaths)) {
                    picPaths.add(folderpaths);
                    folds.setPath(folderpaths);
                    folds.setFolderName(folder);
                    folds.setFirstPic(datapath);
                    folds.addpics();
                    picFolders.add(folds);
                } else {
                    for (int i = 0; i < picFolders.size(); i++) {
                        if (picFolders.get(i).getPath().equals(folderpaths)) {
                            picFolders.get(i).setFirstPic(datapath);
                            picFolders.get(i).addpics();
                        }
                    }
                }
            }
        } while (cursor.moveToNext());
        cursor.close();
        for (int i2 = 0; i2 < picFolders.size(); i2++) {
            Log.d("picture folders", picFolders.get(i2).getFolderName() + " and path = " + picFolders.get(i2).getPath() + " " + picFolders.get(i2).getNumberOfPics());
        }
        return picFolders;
    }


    @Override // com.pinal.photoeditorphotocollage.simpleimagegallery.utils.itemClickListener
    public void onPicClicked(PicHolder holder, int position, ArrayList<pictureFacer> arrayList) {
    }

    @Override // com.pinal.photoeditorphotocollage.simpleimagegallery.utils.itemClickListener
    public void onPicClicked(String pictureFolderPath, String folderName) {
        Intent move = new Intent(this, ImagePickerCustom.class);
        move.putExtra("folderPath", pictureFolderPath);
        move.putExtra("folderName", folderName);
        startActivity(move);
    }

    private void changeStatusBarColor() {
        Window window = getWindow();
        window.clearFlags(67108864);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
    }
}
