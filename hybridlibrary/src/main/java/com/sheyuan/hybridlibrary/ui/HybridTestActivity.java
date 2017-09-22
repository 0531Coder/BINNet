package com.sheyuan.hybridlibrary.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.sheyuan.hybridlibrary.R;
import com.sheyuan.hybridlibrary.service.ImgService;

/**
 * Created by moutain on 17-9-21 09:53.
 */

public class HybridTestActivity extends Activity{

    private Button btn_startservice;
    private Button btn_stopservice;
    private Button btn_bindservice;
    private Button btn_unbindservice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hybrid_test_activity);
        btn_startservice = (Button) findViewById(R.id.btn_startservice);
        btn_stopservice = (Button) findViewById(R.id.btn_stopservice);
        btn_bindservice = (Button) findViewById(R.id.btn_bindservice);
        btn_unbindservice = (Button) findViewById(R.id.btn_unbindservice);
        btn_startservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HybridTestActivity.this,ImgService.class);
                startService(intent);
            }
        });

        btn_stopservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HybridTestActivity.this,ImgService.class);
                stopService(intent);
            }
        });

        btn_bindservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent bindIntent = new Intent(HybridTestActivity.this,ImgService.class);
//                bindService(bindIntent,connection,BIND_AUTO_CREATE);
            }
        });

        btn_unbindservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                unbindService(connection);
            }
        });
    }
}
