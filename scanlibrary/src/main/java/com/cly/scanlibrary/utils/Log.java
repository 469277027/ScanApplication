package com.cly.scanlibrary.utils;

import com.cly.scanlibrary.BuildConfig;

/**
 * Created by 丛龙宇 on 2017/8/16.
 */

public class Log {

    private static final String DEFAULT_TAG = "ScanLibrary";

    /*======================Log.d===================*/

    public static void d(String tag, String message) {
//        if (BuildConfig.DEBUG) {
            android.util.Log.d(tag, message);
//        }
    }

    public static void d(String message) {
        d(DEFAULT_TAG, message);
    }

    public static void d(Class<?> clazz, String message) {
        d(clazz.getSimpleName(), message);
    }

    /*======================Log.e===================*/

    public static void e(String tag, String message) {
        if (BuildConfig.DEBUG) {
            android.util.Log.e(tag, message);
        }
    }

    public static void e(String message) {
        e(DEFAULT_TAG, message);
    }

    public static void e(Class<?> clazz, String message) {
        e(clazz.getSimpleName(), message);
    }

        /*======================Log.i===================*/

    public static void i(String tag, String message) {
        if (BuildConfig.DEBUG) {
            android.util.Log.i(tag, message);
        }
    }

    public static void i(String message) {
        i(DEFAULT_TAG, message);
    }

    public static void i(Class<?> clazz, String message) {
        i(clazz.getSimpleName(), message);
    }


}
