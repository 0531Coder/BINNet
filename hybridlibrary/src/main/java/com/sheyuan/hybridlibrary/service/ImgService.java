package com.sheyuan.hybridlibrary.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moutain on 17-9-20 18:34.
 */

public class ImgService extends Service {

    public static final String TAG = "ImgService";
    private List<String> urls;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: executed");
    }

    private void downloadImg(List<String> urls) {
//        RetrofitSingleton.getInstance().create().
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: executed");
        return null;
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: executed");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent getInte = intent;
        String url = getInte.getStringExtra("url");
        addDownLoadUrl(url);
        return super.onStartCommand(intent, flags, startId);
    }

    private void addDownLoadUrl(String url) {
        if (urls == null) {
            urls = new ArrayList<>();
        }
        if (!urls.contains(url)) {
            urls.add(url);
        }
    }

}
