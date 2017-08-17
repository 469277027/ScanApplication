package com.cly.scanlibrary.business;

/**
 * Created by 丛龙宇 on 2017/8/17.
 */

public class ScanBusiness {

    private static ScanBusiness mINSTANCE;

    private ScanListener listener;

    private ScanBusiness() {

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
