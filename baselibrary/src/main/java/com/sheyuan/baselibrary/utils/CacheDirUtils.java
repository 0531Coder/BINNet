package com.sheyuan.baselibrary.utils;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by moutain on 17-9-21 12:13.
 */

public class CacheDirUtils {
    private static CacheDirUtils Instance = null;
    private String cacheDir;
    private String filesDir;

    public static CacheDirUtils getInstance() {
        if (Instance == null) {
            synchronized (CacheDirUtils.class) {
                if (Instance == null) {
                    Instance = new CacheDirUtils();
                }
            }
        }
        return Instance;

    }

    /**
     * 若有内存卡，则用户可以操作缓存（对应应用设置中的清除缓存），如果没有内存卡，用户不能操作该缓存
     *
     * @param context
     * @param dirName
     * @return SDCard/Android/data/$packageName/cache/$dirName or data/data/$packageName/cache/dirName
     */
    public String getDiskCacheDir(Context context, @Nullable String dirName) {
        if (TextUtils.isEmpty(dirName)) {
            if (context.getExternalCacheDir() != null && android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                cacheDir = context.getExternalCacheDir().toString();
            } else {
                cacheDir = context.getCacheDir().toString();
            }
        } else {
            if (context.getExternalCacheDir() != null && android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                cacheDir = context.getExternalCacheDir().toString() + "/" + dirName;
            } else {
                cacheDir = context.getCacheDir().toString() + "/" + dirName;
            }
        }
        return cacheDir;
    }

    /**
     * 　未root用户无法操作该文件夹
     *
     * @param context
     * @param dirName
     * @return data/data/$packageName/files/dirName
     */
    public String getFilesDir(Context context, String dirName) {
        if (TextUtils.isEmpty(dirName)) {
            filesDir = context.getFilesDir().toString();
        } else {
            filesDir = context.getFilesDir().toString() + "/" + dirName;
        }
        return filesDir;
    }
}
