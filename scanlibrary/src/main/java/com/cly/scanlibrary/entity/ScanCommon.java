package com.cly.scanlibrary.entity;

/**
 * 存储扫码需要的数据，如设置等
 * Created by 丛龙宇 on 2017/8/16.
 */

public abstract class ScanCommon {

    /**
     * 域名
     */
    public static String domainName;

    /**
     * token
     */
    public static String token;

    /**
     * 扫码相关设置
     */
    public static SettingsBean scanSettings;

    /**
     * 根据封车码获取的数据（网络获取）
     */
    public static BillCodeDatasBean scanBillCodeDatas;


    /**
     * 20.1 获取设置接口
     */
    public static final String GET_SETTINGS = "scan/transportGetSeting";

    /**
     * 20.2 通过封车码获取数据
     */
    public static final String GET_SCAN_DATAS = "scan/transportBillCode";

    /**
     * 20.3 上传数据
     */
    public static final String POST_UPLOAD = domainName + "/" + "scan/transportRecordUpload";

    /**
     * 20.4 装卸列表
     */
    public static final String GET_SCAN_LIST = domainName + "/" + "scan/transportList";

    /**
     * 20.5 装卸详情
     */
//    public static final String GET_SCAN_DETAIL = domainName + "/" + ""


}
