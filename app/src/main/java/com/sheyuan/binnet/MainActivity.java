package com.sheyuan.binnet;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sheyuan.baselibrary.BaseRxActivity;
import com.sheyuan.baselibrary.BasicObserver;
import com.sheyuan.baselibrary.RetrofitSingleton;
import com.sheyuan.baselibrary.rxpermissions.Permission;
import com.sheyuan.baselibrary.rxpermissions.RxPermissions;
import com.sheyuan.baselibrary.utils.helper.RxSchedulers;
import com.sheyuan.binnet.camera.SimpleActivity;
import com.sheyuan.binnet.response.TransportResponse;
import com.sheyuan.binnet.service.LoginService;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;


public class MainActivity extends BaseRxActivity {

    private TextView tv_result;
    private Button btn_query;
    private Button btn_call;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RxPermissions rxPermissions = new RxPermissions(this);
        btn_call = (Button) findViewById(R.id.btn_call);
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
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SimpleActivity.class);
                startActivity(intent);

            }
        });
        rxPermissions.requestEach(Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_CALENDAR,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_SMS,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(@NonNull Permission permission) throws Exception {

            }
        });
    }
}
