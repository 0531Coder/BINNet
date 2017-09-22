package com.sheyuan.baselibrary.glideutils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;
import com.sheyuan.baselibrary.utils.CacheDirUtils;

import java.io.InputStream;

/**
 * Created by developerHaoz on 2017/6/23.
 */

@GlideModule
public final class CustomAppGlideModule extends AppGlideModule {

    /**
     *  通过GlideBuilder设置默认的结构(Engine,BitmapPool ,ArrayPool,MemoryCache等等).
     * @param context
     * @param builder
     */
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //获取系统分配给应用的总内存大小
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        //图片内存缓存占应用内存的1/8
        int memoryCacheSize = maxMemory/8;
        //重新设置内存限制
        builder.setMemoryCache(new LruResourceCache(memoryCacheSize));
        //设置Glide的磁盘缓存大小和缓存路径
        String cacheDir = CacheDirUtils.getInstance().getDiskCacheDir(context,null);
        //可缓存的磁盘大小为50M
        int diskCacheSize = 1024*1024*50;
        builder.setDiskCache(new DiskLruCacheFactory(cacheDir,"imgCache",diskCacheSize));

    }

    /**
     * 为App注册一个自定义的String类型的BaseGlideUrlLoader
     *
     * @param context
     * @param registry
     */
    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        super.registerComponents(context, glide, registry);
        registry.append(String.class, InputStream.class,new CustomBaseGlideUrlLoader.Factory());
    }

    /**
     * 清单解析的开启
     *
     * 这里不开启，避免添加相同的modules两次
     * @return
     */
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
