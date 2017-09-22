package com.sheyuan.hybridlibrary.ui;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.sheyuan.hybridlibrary.R;

/**
 * Created by moutain on 17-9-21 17:24.
 */

public class HybridWebViewActivity extends HybridBaseActivity {

    private QMUITopBar qmuiTopBar;
    private ProgressBar pb_webview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        qmuiTopBar = (QMUITopBar) findViewById(R.id.qmui_topbar);
        pb_webview = (ProgressBar) findViewById(R.id.pb_webview);

    }
}
