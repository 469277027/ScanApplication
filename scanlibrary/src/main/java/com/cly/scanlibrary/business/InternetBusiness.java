package com.cly.scanlibrary.business;


import com.cly.scanlibrary.BuildConfig;
import com.cly.scanlibrary.entity.BillCodeDatasBean;
import com.cly.scanlibrary.entity.ScanCommon;
import com.cly.scanlibrary.entity.SettingsBean;
import com.cly.scanlibrary.internet.HttpResult;
import com.cly.scanlibrary.internet.ScanService;
import com.cly.scanlibrary.utils.Log;
import com.elvishew.xlog.XLog;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 网络请求业务类
 * Created by 丛龙宇 on 2017/8/17.
 */

public class InternetBusiness {

    private static InternetBusiness mINSTANCE;

    private static final String TAG = "InternetBusiness";
    private String domainName2;
    private ScanService scanService;

    public static InternetBusiness getINSTANCE() {
        if (mINSTANCE == null) {
            mINSTANCE = new InternetBusiness();
        }
        return mINSTANCE;
    }

    private InternetBusiness() {
        initConn();
    }

    /**
     * 获取设置
     *
     * @return 请求的结果Observble
     */
    public Observable<HttpResult<SettingsBean>> getSettings() {
        return scanService.getScanSettings(domainName2 + "/" + ScanCommon.GET_SETTINGS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.computation());
    }

    /**
     * 根据封车码获取基本信息
     */
    public Observable<HttpResult<BillCodeDatasBean>> getBillCodeDatas(String billCode) {
        return scanService.getScanBillCodeData(domainName2 + "/" + ScanCommon.GET_SCAN_DATAS + "/" + billCode)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 初始化网络请求
     */
    private void initConn() {

        String url = ScanCommon.domainName;
        Log.d(TAG, "url11-->" + url);
        String[] split = url.split("/");
        String domainName = url.replace(split[split.length - 1], "");
        domainName = domainName.substring(0, domainName.length() - 1);
        Log.d(TAG, "domainName-->" + domainName);
        domainName2 = split[split.length - 1];
        Log.d(TAG, "domainName2-->" + domainName2);


        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(20, TimeUnit.SECONDS);
        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request original = chain.request();

                Log.d(TAG, "url = " + original.url().url());

                XLog.d(original);


                Request request = original.newBuilder()
                        .header("postman-token", "e22671c2-56b1-2c77-4d0f-3729bf128b2b")
                        .header("Authorization", "Basic " + ScanCommon.token)
                        .header("AppVersion", String.valueOf(BuildConfig.MODULE_VERSION))
                        .method(original.method(), original.body())
                        .build();


                Response response = chain.proceed(request);

                int code = response.code();

                XLog.d(code);

                if (code == 200) {
                    ResponseBody responseBody = response.peekBody(1024 * 1024);
                    XLog.json(responseBody.string());
                    return response;
                } else if (code == 401) {
                    XLog.e("授权失败");
                    throw new AuthorizationException("授权失败");
                } else {
                    XLog.e("未知错误");
                    throw new RuntimeException("未知错误");
                }
            }

        });


        OkHttpClient client = httpClientBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(domainName)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        Log.i(TAG, domainName);
        scanService = retrofit.create(ScanService.class);
    }


    /**
     * http返回码为200，后台返回码为500时抛出该异常，代表服务器错误
     */
    public static class ServerException extends RuntimeException {
        public ServerException(String message) {
            super(message);
        }
    }

    /**
     * http返回码为200，后台返回码为100时抛出该异常，代表逻辑错误，比如xx不能为空
     */
    public static class LogicException extends RuntimeException {
        public LogicException(String message) {
            super(message);
        }
    }

    /**
     * http返回码为401，授权异常
     */
    public static class AuthorizationException extends RuntimeException {
        public AuthorizationException(String message) {
            super(message);
        }
    }

    /**
     * 根据封车码获取信息回调接口
     */
    public static interface OnGetBillCodeListener extends OnInternetListener<BillCodeDatasBean> {

    }

    /**
     * 获取设置回调接口
     */
    public static interface OnGetSettingListener extends OnInternetListener<SettingsBean> {

        /**
         * 如果有多条线路回调
         */
        void chooseLine(SettingsBean.LineBean lineBean);

    }

    /**
     * 数据回调顶层接口
     */
    public static interface OnInternetListener<T> {

        /**
         * 成功回调
         *
         * @param t 回调数据
         */
        void onSuccess(T t);

        /**
         * 失败回调
         *
         * @param msg 回调信息
         */
        void onError(String msg);
    }

}
