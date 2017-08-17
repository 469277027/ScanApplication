package com.cly.scanlibrary;

import android.os.Handler;
import android.os.Looper;

import com.cly.scanlibrary.business.InternetBusiness;
import com.cly.scanlibrary.entity.BillCodeDatasBean;
import com.cly.scanlibrary.entity.ScanCommon;
import com.cly.scanlibrary.entity.ScanDatas;
import com.cly.scanlibrary.entity.SettingsBean;
import com.cly.scanlibrary.internet.HttpResult;
import com.cly.scanlibrary.internet.ScanSubscriber;
import com.cly.scanlibrary.tasks.ScanManager;
import com.cly.scanlibrary.utils.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 扫码暴露出的类，和UI做数据交互
 * Created by 丛龙宇 on 2017/8/16.
 */

public class Scan {

    /**
     * 扫描任务管理类
     */
    private static ScanManager mINSTANCE;
    /**
     * 扫描任务线程池
     */
    private static ExecutorService executorService;
    /**
     * Http请求业务类
     */
    private static InternetBusiness internetBusiness;
    /**
     * 扫描相关监听器
     */
    private static ScanListener scanListener;
    /**
     * 强制终止任务锁
     */
    private static final Object stopLock = new Object();

    /**
     * 是否开始扫描
     */
    private static boolean isStarted = false;

    private static Looper looper = Looper.getMainLooper();
    private static Handler handler = new Handler(looper);

//    private static InternetBusiness.OnGetBillCodeListener billCodeListener;

    /**
     * 扫码相关初始化
     *
     * @param domainName 网络请求域名
     * @param token      网络请求token
     */
    public static void init(String domainName, String token) {
        ScanCommon.domainName = domainName;
        ScanCommon.token = token;

        internetBusiness = InternetBusiness.getINSTANCE();
        getSettings();

        executorService = Executors.newCachedThreadPool();
        mINSTANCE = new ScanManager(executorService);
    }

    public static void setListener(ScanListener listener) {
        scanListener = listener;
    }

    /**
     * 开始扫码
     * 1>> 获取本地未完成的装卸列表
     * 2>>
     */
    public static void start(boolean isInternet) {

        synchronized (Scan.class) {

            if (isStarted)
                return;

            if (ScanCommon.scanSettings == null) {
                throw new RuntimeException("please call init() before");
            }

            if (isInternet && ScanCommon.scanBillCodeDatas == null) {
                scanListener.billCodeEmpty();
                return;
            }

            executorService = Executors.newCachedThreadPool();
            executorService.execute(mINSTANCE);
            isStarted = true;
        }

    }


    /**
     * 尝试终止任务的线程
     */
    private static class TryStop implements Runnable {

        @Override
        public void run() {

            try {
                synchronized (stopLock) {
                    if (!isStarted)
                        return;

                    if (!executorService.isTerminated()) {
                        scanListener.unusualInterrupted();
                        wait();
                    }
                    executorService.shutdownNow();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 扫描完成终止任务的线程
     */
    private static class finishStop implements Runnable {

        @Override
        public void run() {
            try {
                executorService.shutdown();
                while (executorService.isTerminated()) {
                    TimeUnit.MILLISECONDS.sleep(100);
                }
                scanListener.stopSuccess();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void finishStop() {

        Log.d("--> finishStop");

        Observable
                .create(new Observable.OnSubscribe<Boolean>() {
                    @Override
                    public void call(Subscriber<? super Boolean> subscriber) {
                        Log.d("--> finishStop:call");
                        try {
                            Log.d("--> finishStop:call:executorService.isTerminated = " + executorService.isShutdown());
                            executorService.shutdownNow();
                            while (!executorService.isShutdown()) {
                                TimeUnit.MICROSECONDS.sleep(100);
                            }
                            subscriber.onNext(true);
                            subscriber.onCompleted();
                        } catch (InterruptedException e) {
                            subscriber.onError(e);
                        }
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            isStarted = false;
                            scanListener.stopSuccess();
                        }
                    }
                });
    }

    public static void stop() {
        finishStop();
    }

    public static void put(ScanDatas scanDatas) {
        try {
            mINSTANCE.put(scanDatas);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取封车号相关信息
     *
     * @param billCode 封车号
     * @param listener 监听器
     */
    public static void getBillCodeDatas(String billCode, final InternetBusiness.OnGetBillCodeListener listener) {

        if (ScanCommon.domainName == null || ScanCommon.token == null) {
            throw new RuntimeException("please call init() before");
        }

        internetBusiness.getBillCodeDatas(billCode)
                .subscribe(new ScanSubscriber<HttpResult<BillCodeDatasBean>, BillCodeDatasBean>() {

                    @Override
                    protected void onSuccess(BillCodeDatasBean billCodeDatasBeanHttpResult) {
                        ScanCommon.scanBillCodeDatas = billCodeDatasBeanHttpResult;
                        listener.onSuccess(billCodeDatasBeanHttpResult);
                    }

                    @Override
                    protected void onError(String msg) {
                        listener.onError(msg);
                        Log.d("--> onError:msg = " + msg);
                    }
                });
    }


    /**
     * 从服务器读取配置信息
     */
    private static void getSettings() {
        internetBusiness.getSettings()
                .subscribe(new ScanSubscriber<HttpResult<SettingsBean>, SettingsBean>() {

                    @Override
                    protected void onSuccess(SettingsBean contentBeanHttpResult) {
                        ScanCommon.scanSettings = contentBeanHttpResult;
                    }

                    @Override
                    protected void onError(String msg) {

                    }
                });
    }


    /**
     * 扫码相关监听器
     */
    public static interface ScanListener {

        /**
         * 点击的时候未初始化封车数据
         */
        void billCodeEmpty();

        /**
         * 终止的时候还有线程未完成
         */
        void unusualInterrupted();

        /**
         * 任务关闭成功
         */
        void stopSuccess();


    }

}
