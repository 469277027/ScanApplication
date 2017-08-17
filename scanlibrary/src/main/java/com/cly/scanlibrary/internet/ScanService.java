package com.cly.scanlibrary.internet;

import com.cly.scanlibrary.entity.BillCodeDatasBean;
import com.cly.scanlibrary.entity.SettingsBean;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by 丛龙宇 on 2017/8/17.
 */

public interface ScanService {

    @GET
    public Observable<HttpResult<SettingsBean>> getScanSettings(@Url String url);

    @GET
    public Observable<HttpResult<BillCodeDatasBean>> getScanBillCodeData(@Url String url);


}
