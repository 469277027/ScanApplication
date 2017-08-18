package com.cly.scanlibrary.entity;

import com.cly.scanlibrary.utils.ReadSettings;

/**
 * 存储扫码需要的数据，如设置等
 * Created by 丛龙宇 on 2017/8/16.
 */

public abstract class ScanCommon {

    /**
     * 扫描状态
     */
    public static abstract class ScanState {
        /**
         * 正常
         */
        public static final int STATE_SUCCESS = 0;
        /**
         * 取消
         */
        public static final int STATE_CANCEL = 1;
        /**
         * 错误
         */
        public static final int STATE_ERROR = 2;
    }

    /*======================初始化所需字段=======================*/

    /**
     * 域名
     */
    public static String domainName;

    /**
     * token
     */
    public static String token;

    /**
     * 扫描类型
     */
    public static String scanType;

    /**
     * 扫描操作员
     */
    public static String scanOperator;

    /**
     * 扫描地点
     */
    public static String scanCompany;

    /**
     * 线路
     */
    public static String transportBillLine;


    /*====================存放从网络获取的全局需要字段====================*/
    /**
     * 扫码相关设置
     */
    public static SettingsBean scanSettings;

    /**
     * 根据封车码获取的数据（网络获取）
     */
    public static BillCodeDatasBean scanBillCodeDatas;


    /*======================接口地址===========================*/

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

    /*=====================设置========================*/

    /**
     * 第一段条码类型
     */
    public static ReadSettings.CodeType firstCodeType;

    /**
     * 第二段条码类型
     */
    public static ReadSettings.CodeType secondCodeType;

    /**
     * 上传间隔时间
     */
    public static int uploadIntervalTime;

    /**
     * 公司类型
     */
    public static String companyType;

    /**
     * 扫描数据保存时间
     */
    public static String scanDataSavedDate;

    /*======================当前正在进行的扫描任务相关数据========================*/

    /**
     * 封车号
     */
    public static String curBillCode;

    /**
     * mainID
     */
    public static long curMainID;
}
