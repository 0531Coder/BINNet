package com.sheyuan.binnet.service;


import com.sheyuan.binnet.response.TransportResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by moutain on 17-9-11.
 */

public interface LoginService {
    @FormUrlEncoded
    @POST("query")
    Observable<TransportResponse> queryTran(@Field("type")String type, @Field("postid")String id);
}
