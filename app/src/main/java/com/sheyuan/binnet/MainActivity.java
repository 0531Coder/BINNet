package com.sheyuan.binnet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sheyuan.baselibrary.RetrofitSingleton;
import com.sheyuan.baselibrary.utils.helper.RxSchedulers;
import com.sheyuan.binnet.response.TransportResponse;
import com.sheyuan.binnet.service.LoginService;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void query(View view){
        RetrofitSingleton.getInstance().getRetrofit().create(LoginService.class).queryTran("yuantong","11111111111").compose(RxSchedulers.<TransportResponse>io_main()).subscribe(new Observer<TransportResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TransportResponse transportResponse) {
                System.out.println(transportResponse.getNu());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
