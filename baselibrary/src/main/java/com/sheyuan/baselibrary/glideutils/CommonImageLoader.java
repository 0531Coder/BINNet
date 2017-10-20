package com.sheyuan.baselibrary.glideutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.sheyuan.baselibrary.R;

import java.io.File;
import java.util.concurrent.ExecutionException;

/**
 * Created by developerHaoz on 2017/6/23.
 */
public class CommonImageLoader {

    /**
     * 单例
     */
    private static volatile CommonImageLoader mInstance;

    public static CommonImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (CommonImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new CommonImageLoader();
                    return mInstance;
                }
            }
        }
        return mInstance;
    }

    /**
     * 在一个imageView里面异步展示一个图片
     *
     * @param uri
     * @param imageView
     */
    public void displayImage(String uri, ImageView imageView) {
        GlideApp.with(imageView.getContext())
                .load(uri)
                .error(R.drawable.ucrop_ic_angle)
                .placeholder(R.drawable.ucrop_ic_angle)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL) // 设置缓存的策略
                .into(imageView);
    }

    /**
     * 异步加载一个图片，监听加载过程，指定大小，在回调中取得位图
     * 可以用来加载大图。
     *
     * @param context
     * @param uri
     * @param listener
     */
    public void loadImage(Context context, String uri, final getImageListener listener) {
        GlideApp.with(context)
                .asBitmap()
                .load(uri)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                        listener.onSuccess(bitmap);
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        listener.onError(errorDrawable);
                    }
                });

    }

    /**
     * hybrid 前端页面图片加载
     */
    public void loadImageFile(Context context,String url,final getImgFlieListener listener){
        GlideApp.with(context).asFile().load(url).diskCacheStrategy(DiskCacheStrategy.DATA).into(new SimpleTarget<File>() {
            @Override
            public void onResourceReady(File resource, Transition<? super File> transition) {
                listener.onSuccess(resource);
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                listener.onError(errorDrawable);
            }
        });
    }

    /**
     * 获取磁盘缓存
     *
     * @return
     */
    public DiskCache getDiskCache() {
        return CommonImageLoader.getInstance().getDiskCache();
    }

    /**
     * 获取内存缓存
     *
     * @return
     */
    public MemoryCache getMemoryCache() {
        return CommonImageLoader.getInstance().getMemoryCache();
    }

    /**
     * 注意！！！该方法必须在子线程中执行
     * 清除硬盘缓存
     */
    public void cleanDiskCache(final Context context) {
        Glide.get(context).clearDiskCache();
    }

    /**
     * 清除内存缓存
     */
    public void cleanMemoryCache(Context context) {
        Glide.get(context).clearMemory();
    }

    /**
     * 内存和硬盘双清
     */
    public void cleanDoubleCache(Context context) {
        cleanDiskCache(context);
        cleanMemoryCache(context);
    }

    /**
     * 恢复请求，一般在停止滚动的时候调用
     *
     * @param context
     */
    public void resumeRequests(Context context) {
        Glide.with(context).resumeRequests();
    }

    /**
     * 暂停请求，一般在滚动的时候调用
     *
     * @param context
     */
    public void pauseRequests(Context context) {
        Glide.with(context).pauseRequests();
    }

    /**
     * 根据图片的网络地址，拿到使用 Glide 进行缓存后的图片缓存地址
     * 注意！！！ 该方法要在子线程中调用，否则会出错
     *
     * @param imageUrl 图片的网络地址
     * @return 图片的缓存地址
     */
    public String getImagePathFromCache(String imageUrl, Context context) {

        FutureTarget<File> futureTarget = Glide.with(context)
                .load(imageUrl)
                .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
        File cacheFile;
        try {
            cacheFile = futureTarget.get();
            return cacheFile.getAbsolutePath();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用于监听异步加载图片的过程
     */
    public interface ImageLoadingListener {
        void onSuccess();

        void onError();
    }

    /**
     * 用于以及加载图片获取 Bitmap
     */
    public interface getImageListener {
        void onSuccess(Bitmap bitmap);

        void onError(Drawable drawable);
    }
    /**
     * 获取下载好的图片文件
     */
    public interface getImgFlieListener{
        void onSuccess(File imgFile);

        void onError(Drawable drawable);
    }
}
