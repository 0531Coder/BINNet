package com.sheyuan.hybridlibrary.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.sheyuan.hybridlibrary.R;
import com.sheyuan.hybridlibrary.core.HybridConfig;
import com.sheyuan.hybridlibrary.core.HybridConstant;
import com.sheyuan.hybridlibrary.core.HybridJsInterface;
import com.sheyuan.hybridlibrary.core.HybridWebViewClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

/**
 * Created by moutain on 17-9-20 12:27.
 */

public class HybridBaseActivity extends AppCompatActivity {

    private WebView webview;
    private HybridWebViewClient mHybridWebViewClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openHardWare_Accelerated();
        setContentView(R.layout.hybrid_webview_activity);
        initView();
        if (!TextUtils.isEmpty(getUrl())) {
            loadUrl(getUrl());
        }
    }

    private void loadUrl(String url) {
        if (TextUtils.isEmpty(url)) return;
        mHybridWebViewClient.setHostFilter(Uri.parse(url).getHost());
        webview.loadUrl(url);
    }

    private void openHardWare_Accelerated() {
        //打开这个Activity的硬件加速
        try {
            if (Integer.parseInt(android.os.Build.VERSION.SDK) >= 11) {
                getWindow()
                        .setFlags(
                                android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                                android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
            }
        } catch (Exception e) {
        }
    }

    private void initView() {
        webview = (WebView) findViewById(R.id.x5_webview);
        configWebView(webview);
    }

    private void configWebView(WebView webview) {
        WebSettings webSetting = webview.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setSupportZoom(false);
        webSetting.setBuiltInZoomControls(false);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(false);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSetting.setUserAgent(webSetting.getUserAgentString() + HybridConfig.HYBRID_VERSION);
        mHybridWebViewClient = new HybridWebViewClient(webview);
        webview.setWebViewClient(mHybridWebViewClient);
        webview.addJavascriptInterface(new HybridJsInterface(), HybridConfig.JSInterface);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
    }


    private String getUrl() {
        Uri data = getIntent().getData();
        String url = null;
        if (null == data) {
            url = getIntent().getStringExtra(HybridConstant.INTENT_EXTRA_KEY_TOTAB);
        } else {
            url = data.toString();
        }
        return url;
    }

}
