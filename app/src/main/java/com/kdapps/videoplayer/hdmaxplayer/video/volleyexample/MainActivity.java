package com.kdapps.videoplayer.hdmaxplayer.video.volleyexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.Model.Stickermodel;
import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.Model.stickerlistModel;
import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.adeapter.Sticker_Category_Adapter;
import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.adeapter.Sticker_Thamb_Adapter;
import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.FolderActivity;
import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.ImagePickerCustom;
import com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagefromgallary.ImagePickerActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Sticker_Thamb_Adapter.OnClickListener {
    public static String Url = "https://thevidmix.com/photo_blender/photo_blender_sticker.php";
    public static String Catthumb = "https://thevidmix.com/photo_blender/cat_thumb/";
    public static String Sticker_thumb = "https://thevidmix.com/photo_blender/all/";
    private ArrayList<Stickermodel.Datum> stickerData;
    public static RecyclerView recycler_tab_layout;
    public static RecyclerView sticker_viewpaper;
    public Button gallarymobile,gallarycustommobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gallarycustommobile = findViewById(R.id.gallarycustommobile);
        gallarymobile = findViewById(R.id.gallarymobile);

        getStickerData();

        gallarymobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ImagePickerActivity.class);
                startActivity(intent);
            }
        });

        gallarycustommobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FolderActivity.class);
                startActivity(intent);
            }
        });

    }

    public void getStickerData() {
        this.stickerData = new ArrayList<>();
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        StringRequest MyStringRequest = new StringRequest(1, Url, new Response.Listener<String>() { // from class: com.example.photoareditor.Activity.EditorActivity.57
            @Override // com.android.volley.Response.Listener
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray optJSONArray = obj.getJSONArray("data");
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject jsonObject = optJSONArray.getJSONObject(i);
                        Stickermodel.Datum dataList = new Stickermodel.Datum();
                        dataList.catid = jsonObject.getString("cat_id");
                        dataList.catthumb = Catthumb + jsonObject.getString("cat_thumb");
                        dataList.stickers = new ArrayList();
                        JSONArray arraylist = jsonObject.getJSONArray("stickers");
                        for (int j = 0; j < arraylist.length(); j++) {
                            stickerlistModel sublist = new stickerlistModel();
                            JSONObject jsonObjectsub = arraylist.getJSONObject(j);
                            sublist.sticker_id = jsonObjectsub.getString("sticker_id");
                            sublist.sticker_thumb =Sticker_thumb + jsonObjectsub.getString("sticker_thumb");
                            dataList.stickers.add(sublist);
                        }
                        stickerData.add(dataList);
                    }
                    setRectViews();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() { // from class: com.example.photoareditor.Activity.EditorActivity.58
            @Override // com.android.volley.Response.ErrorListener
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Please Check Your Internet Connection...", Toast.LENGTH_SHORT).show();
//                MainActivity.this.getStickerData1();
            }
        }) { // from class: com.example.photoareditor.Activity.EditorActivity.59
            @Override // com.android.volley.Request
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<>();
                MyData.put("appkey", "dj420dv134hk4273");
                MyData.put("ver", "7.5");
                MyData.put("os", "android");
                MyData.put("device", "redmi");
                MyData.put("category", "all");
                return MyData;
            }
        };
        MyRequestQueue.add(MyStringRequest);
    }

    public void setRectViews() {
        sticker_viewpaper = (RecyclerView) findViewById(R.id.sticker_viewpaper);
        recycler_tab_layout = (RecyclerView) findViewById(R.id.recycler_tab_layout);
        sticker_viewpaper.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        Log.e("aaaa", "setRectViews: " + this.stickerData.size());
        Sticker_Category_Adapter sticker_category_adapter = new Sticker_Category_Adapter(MainActivity.this, this.stickerData);
        sticker_viewpaper.setAdapter(sticker_category_adapter);
    }

    @Override
    public void addSticker(Bitmap bitmap) {
        Drawable d = new BitmapDrawable(getResources(), bitmap);
//        this.polishView.addSticker(new DrawableSticker(d)); //for sticker display
    }
}