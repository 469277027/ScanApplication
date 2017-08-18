package com.cly.scanlibrary.business;

import com.cly.scanlibrary.entity.ScanDatas;
import com.cly.scanlibrary.tasks.ScanManager;
import com.cly.scanlibrary.utils.Log;
import com.lc.greendaolibrary.dao.scan.ScanSub;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 扫描业务类
 * Created by 丛龙宇 on 2017/8/17.
 */

public class ScanBusiness {

    private static ScanBusiness mINSTANCE;

    private ScanListener scanListener;

    private ScanManager scanManager;
    private ExecutorService executorService;


    private ScanBusiness() {
        init();
    }

    public static ScanBusiness getINSTANCE() {
        if (mINSTANCE == null) {
            mINSTANCE = new ScanBusiness();
        }
        return mINSTANCE;
    }

    private void init() {
        executorService = Executors.newCachedThreadPool();
        scanManager = new ScanManager(executorService);
    }

    public void setListener(ScanListener listener) {
        scanListener = listener;
    }

    /**
     * 开启扫码任务
     */
    public void start() {
        executorService.execute(scanManager);
    }


    /**
     * 得到扫描到的数据，放到 队列中
     *
     * @param scanSub 包装后的扫描数据
     */
    public void put(ScanSub scanSub) {
        try {
            scanManager.put(new ScanDatas(scanSub));
        } catch (InterruptedException e) {
            scanListener.putError();
            e.printStackTrace();
        }
    }

    public void stop() {
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
                            if (executorService.isShutdown()) {
                                subscriber.onNext(true);
                            } else {
                                subscriber.onNext(false);
                            }
                            subscriber.onCompleted();
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
                            scanListener.stopSuccess();
                        }
                    }
                });
    }


    /**
     * 扫码相关监听器
     */
    public static interface ScanListener {

        /**
         * 任务添加失败
         */
        void putError();

        /**
         * 任务关闭成功
         */
        void stopSuccess();

        /**
         * 任务执行完毕，回调给UI
         */
        void showData(ScanSub scanSub);


    }

}
