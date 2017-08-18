package com.cly.scanlibrary.business;

import android.content.Context;

import com.lc.greendaolibrary.DbManager;
import com.lc.greendaolibrary.dao.scan.ScanMain;
import com.lc.greendaolibrary.dao.scan.ScanSub;
import com.lc.greendaolibrary.gen.DaoSession;
import com.lc.greendaolibrary.gen.ScanMainDao;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 数据库处理业务类
 * Created by 丛龙宇 on 2017/8/18.
 */

public class DbBusiness {

    private static DbBusiness mINSTANCE;
    private final DaoSession daoSession;

    private DbBusiness(Context context) {
        daoSession = DbManager.getINSTANCE(context).getDaoSession();
    }

    public static DbBusiness getINSTANCE(Context context) {
        if (mINSTANCE == null) {
            mINSTANCE = new DbBusiness(context);
        }
        return mINSTANCE;
    }

    /**
     * 存储封车号
     *
     * @param main
     */
    public void saveMain(ScanMain main) {
        daoSession
                .getScanMainDao()
                .insert(main);
    }

    /**
     * 获取所有未封车列表
     *
     * @return
     */
    public List<ScanMain> readAllMain() {
        return daoSession
                .getScanMainDao()
                .queryBuilder()
                .where(ScanMainDao.Properties.SealTime.isNull())
                .orderDesc(ScanMainDao.Properties.BeginScanTime)
                .list();
    }

    /**
     * 判断当前封车号本地是否存在
     */
    public boolean isBillCodeExist(String billCode) {
        return daoSession
                .getScanMainDao()
                .queryBuilder()
                .where(ScanMainDao.Properties.TransportBillCode.eq(billCode))
                .build()
                .unique() == null;
    }

    /**
     * 封车
     */
    public void closeCar(String billCode) {
        ScanMain closeCarData = daoSession
                .getScanMainDao()
                .queryBuilder()
                .where(ScanMainDao.Properties.TransportBillCode.eq(billCode))
                .build()
                .unique();

        if (closeCarData != null) {
            closeCarData.setSealTime(new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis()));
            daoSession.getScanMainDao().insertOrReplace(closeCarData);
        }

    }

    /**
     * 存储封车号对应的扫描信息
     */
    public void saveScanSub(ScanSub scanSub) {
        daoSession
                .getScanSubDao()
                .insert(scanSub);
    }

}
