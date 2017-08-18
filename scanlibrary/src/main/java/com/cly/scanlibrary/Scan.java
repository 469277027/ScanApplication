package com.cly.scanlibrary;

import android.content.Context;

import com.cly.scanlibrary.business.DbBusiness;
import com.cly.scanlibrary.business.InternetBusiness;
import com.cly.scanlibrary.business.ScanBusiness;
import com.cly.scanlibrary.entity.BillCodeDatasBean;
import com.cly.scanlibrary.entity.ScanCommon;
import com.cly.scanlibrary.entity.ScanInitDatas;
import com.cly.scanlibrary.entity.SettingsBean;
import com.cly.scanlibrary.internet.HttpResult;
import com.cly.scanlibrary.internet.ScanSubscriber;
import com.cly.scanlibrary.utils.Log;
import com.cly.scanlibrary.utils.ReadSettings;
import com.lc.greendaolibrary.dao.scan.ScanMain;
import com.lc.greendaolibrary.dao.scan.ScanSub;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 扫码暴露出的类，和UI做数据交互
 * Created by 丛龙宇 on 2017/8/16.
 */

public class Scan {

    /**
     * Http请求业务类
     */
    private static InternetBusiness internetBusiness;
    /**
     * 扫码业务类
     */
    private static ScanBusiness scanBusiness;
    /**
     * 数据库业务类
     */
    private static DbBusiness dbBusiness;


    /**
     * 是否开始扫描
     */
    private static boolean isStarted = false;


    public static Context context;


    /**
     * 扫码相关初始化
     *
     * @param initDatas 扫码相关信息
     */
    public static void init(ScanInitDatas initDatas) {

        ScanCommon.domainName = initDatas.getDomainName();
        ScanCommon.token = initDatas.getToken();
        ScanCommon.scanType = initDatas.getScanType();
        ScanCommon.scanOperator = initDatas.getScanOperator();
        ScanCommon.scanCompany = initDatas.getScanCompany();

        internetBusiness = InternetBusiness.getINSTANCE();
        scanBusiness = ScanBusiness.getINSTANCE();
        dbBusiness = DbBusiness.getINSTANCE(context);
        getSettings();

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

            if (isInternet && ScanCommon.scanBillCodeDatas != null) {

                if (dbBusiness.isBillCodeExist(ScanCommon.scanBillCodeDatas.getTransportBillCode())) {
                    Log.d(Scan.class, "--> start:新建列表");
                    ScanMain scanMain = new ScanMain();
                    scanMain.setScanType(ScanCommon.scanType);
                    scanMain.setMainID(System.currentTimeMillis());
                    scanMain.setState(ScanCommon.ScanState.STATE_SUCCESS);
                    scanMain.setTransportBillCode(ScanCommon.scanBillCodeDatas.getTransportBillCode());
                    scanMain.setTransportBillType(ScanCommon.scanBillCodeDatas.getTransportBillType());
                    scanMain.setBeginScanTime(new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis()));
                    scanMain.setScanCompany(ScanCommon.scanCompany);
                    scanMain.setScanOperator(ScanCommon.scanOperator);
                    scanMain.setReceiveCompany(ScanCommon.scanBillCodeDatas.getReceiveCompany());
                    scanMain.setReceiveCompany(ScanCommon.transportBillLine);
                    scanMain.setCarNumber(ScanCommon.scanBillCodeDatas.getCarNumber());
                    scanMain.setDriverName(ScanCommon.scanBillCodeDatas.getDriverName());
                    dbBusiness.saveMain(scanMain);
                } else {

                }

            }

            List<ScanMain> scanMains = dbBusiness.readAllMain();
            ScanMain scanMain = scanMains.get(0);
            ScanCommon.curBillCode = scanMain.getTransportBillCode();
            ScanCommon.curMainID = scanMain.getMainID();
            Log.d("--> start:scanMains = " + scanMains);


            scanBusiness.start();
        }

    }

    /**
     * 终止扫描任务
     */
    public static void stop() {
        scanBusiness.stop();
    }

    /**
     * 添加扫描货运单
     *
     * @param scanSub
     */
    public static void put(ScanSub scanSub) {
        scanBusiness.put(scanSub);
    }

    /**
     * 封车
     *
     * @param billCode 封车码
     */
    public static void closeCar(String billCode) {
        dbBusiness.closeCar(billCode);
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
                        ScanCommon.firstCodeType = ReadSettings.getFirstCodeType();
                        ScanCommon.secondCodeType = ReadSettings.getSecondCodeType();
                        ScanCommon.uploadIntervalTime = Integer.parseInt(ReadSettings.readUploadInterval());
                        ScanCommon.companyType = ReadSettings.readCompanyType();
                        ScanCommon.scanDataSavedDate = ReadSettings.readDataLifecyle();
                    }

                    @Override
                    protected void onError(String msg) {

                    }
                });
    }

    /**
     * 扫码整体回调接口
     */
    public static interface ScanListener {

        /*==================扫码回调====================*/

        /**
         * 任务启动成功
         */
        void startSuccess();

        /**
         * 任务启动失败
         */
        void startError(String msg);

        /**
         * 任务关闭成功
         */
        void closeSuccess();

        /**
         * 任务关闭失败
         */
        void closeError();

        /**
         * 扫描信息存储成功
         */
        void saveDataSuccess(ScanSub scanSub);


        /*=================数据库回调====================*/

        /**
         * 获取所有未封车列表
         */
        void getScanMainList(List<ScanMain> list);

        /**
         * 获取选择封车列表下所有数据
         */
        void getScanSubListByCode(List<ScanSub> list);

        /*===================设置接口回调=========================*/

        /**
         * 选择线路（如果有多条线路）
         */
        void chooseLines(SettingsBean.LineBean lineBean);

        /*=====================封车码接口回调==========================*/

        /**
         * 请求成功
         */
        void getBillCodeDataSuccess(BillCodeDatasBean billCodeDatasBean);


    }


}
