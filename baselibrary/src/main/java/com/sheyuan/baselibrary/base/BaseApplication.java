package com.sheyuan.baselibrary.base;

import android.app.Application;
import android.content.Context;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by moutain on 17-9-11.
 */

public class BaseApplication extends Application {

    private static Context mAppContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();
        CrashReport.initCrashReport(getApplicationContext(), "b97529ce83", false);
    }
    public static Context getAppContext(){
        return mAppContext;
    }
}
