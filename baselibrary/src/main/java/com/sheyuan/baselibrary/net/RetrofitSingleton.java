package com.sheyuan.baselibrary.net;

import android.text.TextUtils;

import com.sheyuan.baselibrary.BuildConfig;
import com.sheyuan.baselibrary.base.BaseApplication;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by moutain on 17-9-11.
 */

public class RetrofitSingleton {
    private static volatile RetrofitSingleton Instance = null;
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;

    public RetrofitSingleton() {
        initOkhttp();
    }

    public static Retrofit getInstance() {
        if (Instance == null) {
            synchronized (RetrofitSingleton.class) {
                if (Instance == null) {
                    Instance = new RetrofitSingleton();
                }
            }
        }

        return Instance.retrofit;
    }


    private void initOkhttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //Debug判断
        if (BuildConfig.ISDEBUG) {
            // https://drakeet.me/retrofit-2-0-okhttp-3-0-config
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
// 缓存 http://www.jianshu.com/p/93153b34310e
        File cacheFile = new File(BaseApplication.getAppContext().getCacheDir(), "/NetCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        Interceptor cacheInterceptor = new Interceptor() {

            private String value;

            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder requestBuilder = request.newBuilder();

//                无网处理
//                if (!Util.isNetworkConnected(BaseAppLike.getContext())) {
//                    request = requestBuilder.cacheControl(CacheControl.FORCE_CACHE).build();
//                }
//                请求参数重组
                HashMap<String, String> map = new HashMap<>();
                FormBody.Builder newFormBody = new FormBody.Builder();
                if (request.body() instanceof FormBody) {
                    FormBody oidFormBody = (FormBody) request.body();

                    for (int i = 0; i < oidFormBody.size(); i++) {
                        newFormBody.addEncoded(oidFormBody.encodedName(i), oidFormBody.encodedValue(i));
                        value = oidFormBody.encodedValue(i);
                        if (!TextUtils.isEmpty(value)) {
                            value = URLDecoder.decode(oidFormBody.encodedValue(i), "utf-8");
                        }
                        map.put(oidFormBody.encodedName(i), value);

                    }

                }
//                map.put("v", VersionUtils.getVersion(BaseAppLike.getContext()));
//                String sign;
//                if (TextUtils.isEmpty(BaseAppLike.Appsign)) {
//                    BaseAppLike.Appsign = PrefereUtils.getInstance().getSysConfit().getAppSignKey();
//                    sign = VerifyUtil.genSignV3(map, BaseAppLike.Appsign);
//                } else {
//
//                }
//                Log.i("","");
//                newFormBody.addEncoded("sign",sign);
//                newFormBody.addEncoded("v",VersionUtils.getVersion());
                return chain.proceed(requestBuilder.build());
            }
        };
        builder.cache(cache).addInterceptor(cacheInterceptor);
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        okHttpClient = builder.build();
        retrofit = new Retrofit.Builder().baseUrl(BuildConfig.API_SERVER_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    }

}
