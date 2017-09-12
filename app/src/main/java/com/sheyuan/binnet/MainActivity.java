package com.sheyuan.binnet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sheyuan.baselibrary.BaseRxActivity;
import com.sheyuan.baselibrary.BasicObserver;
import com.sheyuan.baselibrary.RetrofitSingleton;
import com.sheyuan.baselibrary.utils.helper.RxSchedulers;
import com.sheyuan.binnet.response.TransportResponse;
import com.sheyuan.binnet.service.LoginService;

public class MainActivity extends BaseRxActivity {

    private TextView tv_result;
    private Button btn_query;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_result = (TextView) findViewById(R.id.tv_result);
        btn_query = (Button) findViewById(R.id.btn_query);
        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitSingleton.getInstance().create(LoginService.class).queryTran("yuantong","11111111111").compose(RxSchedulers.<TransportResponse>io_main()).subscribe(new BasicObserver<TransportResponse>(MainActivity.this) {
                    @Override
                    public void onSuccess(TransportResponse response) {
                        tv_result.setText(response.getNu());
                    }
                });
            }
        });
    }
}
