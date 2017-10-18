package com.sheyuan.hybridlibrary.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.sheyuan.baselibrary.base.BaseRxActivity;
import com.sheyuan.hybridlibrary.R;

/**
 * Created by moutain on 17-10-18 18:20.
 */

public class HybridSampleActivity extends BaseRxActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hybrid_test_activity);
        findViewById(R.id.btn_toPage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HybridSampleActivity.this,HybridWebViewActivity.class);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });
    }
}
