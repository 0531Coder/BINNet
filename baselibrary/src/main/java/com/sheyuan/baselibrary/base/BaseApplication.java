package com.sheyuan.baselibrary.base;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

/**
 * Created by moutain on 17-9-11.
 */

public class BaseApplication extends Application {

    public static Context mAppContext;
    public static String cacheDir;
    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();
        if(mAppContext.getExternalCacheDir()!=null&&ExistSDCard()){
            cacheDir = mAppContext.getExternalCacheDir().toString();
        }else{
            cacheDir = mAppContext.getCacheDir().toString();
        }
    }
    //判断内存卡安装状态
    private boolean ExistSDCard(){
        return android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
    public static Context getAppContext(){
        return mAppContext;
    }
}
