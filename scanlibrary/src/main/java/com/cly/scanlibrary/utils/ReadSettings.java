package com.cly.scanlibrary.utils;

import android.bluetooth.le.ScanSettings;

import com.cly.scanlibrary.entity.ScanCommon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 读取设置
 * Created by 丛龙宇 on 2017/8/18.
 */

public class ReadSettings {

    public enum CodeType {
        /**
         * 开票地点
         */
        CREATE_ORDER_LOCATION,

        /**
         * 发货公司
         */
        SEND_COMPANY,

        /**
         * 到货公司
         */
        RECEIVE_COMPANY,

        /**
         * 卸货地点
         */
        UNLOADING_LOCATION
    }

    /**
     * 获取自动上传时间间隔
     *
     * @return 自动上传间隔（分钟）
     */
    public static String readUploadInterval() {
        return ScanCommon.scanSettings.getMain().getMainAutoUpload();
    }


    /**
     * 公司类型
     */
    public static String readCompanyType() {
        return ScanCommon.scanSettings.getMain().getMainType();
    }

    /**
     * 获取记录保存时间
     *
     * @return
     */
    public static String readDataLifecyle() {
        return ScanCommon.scanSettings.getMain().getScanDateSaveDay();
    }

    /**
     * 拆分公式
     *
     * @param s 公式
     * @return 公式数组
     */
    private static List<String> subDatas(String s) {
        return Arrays.asList(s.split("(]\\[)"));
    }

    /**
     * 获取条码类型
     *
     * @param s 公式
     * @param i 位置
     * @return 条码类型
     */
    private static CodeType getCodeType(String s, int i) {
        List<String> list = subDatas(s);
        String type = list.get(i);
        type = type.replaceAll("\\[|]", "");
        if ("开票地点条码编码".equals(type)) {
            return CodeType.CREATE_ORDER_LOCATION;
        }
        if ("发货公司条码编码".equals(type)) {
            return CodeType.SEND_COMPANY;
        }
        if ("到货公司条码编码".equals(type)) {
            return CodeType.RECEIVE_COMPANY;
        }
        if ("卸货地点公司条码编码".equals(type)) {
            return CodeType.UNLOADING_LOCATION;
        }
        throw new IllegalArgumentException("can not find type" + type);
    }

    /**
     * 获取第一种条码类型
     *
     * @return 第一种条码类型
     */
    public static CodeType getFirstCodeType() {
        return getCodeType(ScanCommon.scanSettings.getBarcodeRule().getExpressions(), 0);
    }

    /**
     * 获取第二种条码类型
     *
     * @return 第二种条码类型
     */
    public static CodeType getSecondCodeType() {
        return getCodeType(ScanCommon.scanSettings.getBarcodeRule().getExpressions(), 1);
    }


}
