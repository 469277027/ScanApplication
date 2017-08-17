package com.cly.scanapplication.internet;

import com.cly.scanapplication.bean.Receive;
import com.cly.scanapplication.bean.Send;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by 丛龙宇 on 2017/8/17.
 */

public interface InitCountryService {

    public static final String DOMAIN_NAME = "http://118.178.187.230:1880/web/";

    public static final String SEND_URL = "App.asq?/company/sendCounty";
    public static final String RECEIVEURL = "App.asq?/company/recieveCounty";

    @GET
    public Observable<List<Send>> getSend(@Url String url);

    @GET
    public Observable<List<Receive>> getReceive(@Url String url);


}
