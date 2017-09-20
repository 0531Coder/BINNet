package com.sheyuan.hybridlibrary.ui;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ProgressBar;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.sheyuan.baselibrary.base.BaseRxActivity;
import com.sheyuan.hybridlibrary.R;
import com.sheyuan.hybridlibrary.core.HybridConstant;
import com.tencent.smtt.sdk.WebView;

/**
 * Created by moutain on 17-9-20 12:27.
 */

public class HybridBaseActivity extends BaseRxActivity {

    private QMUITopBar topBar;
    private WebView webview;
    private ProgressBar pb_webview;
    private CharSequence url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openHardWare_Accelerated();
        setContentView(R.layout.activity_hybrid_webview);
        initView();
        if (!TextUtils.isEmpty(getUrl())) {
            loadUrl(getUrl());
        }
    }

    private void loadUrl(String url) {
       if(TextUtils.isEmpty(url))return;

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
        topBar = (QMUITopBar) findViewById(R.id.qmui_topbar);
        webview = (WebView) findViewById(R.id.x5_webview);
        pb_webview = (ProgressBar) findViewById(R.id.pb_webview);
        initTopBar();
    }

    @Override
    public void initTopBar() {
          //初始化头部导航
    }


    private String getUrl() {
        Uri data = getIntent().getData();
        String url = null;
        if(null==data){
            url = getIntent().getStringExtra(HybridConstant.INTENT_EXTRA_KEY_TOTAB);
        }else{
            url = data.toString();
        }
        return url;
    }

}
