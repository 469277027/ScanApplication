package com.lc.greendaolibrary.dao.scan;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.lc.greendaolibrary.gen.DaoSession;
import com.lc.greendaolibrary.gen.ScanSubDao;
import com.lc.greendaolibrary.gen.ScanMainDao;

/**
 * Created by 丛龙宇 on 2017/8/17.
 */
@Entity
public class ScanMain {

    /**
     * 扫描主项id
     */
    @Id
    private Long mainID;

    /**
     * 扫描类型（装车/卸车）
     */
    private String scanType;
    /**
     * 扫描状态（0：正常/1：取消）
     */
    private int state;
    /**
     * 封车码
     */
    private String transportBillCode;
    /**
     * 封车类型（装车配载/提货单/转营业部）
     */
    private String transportBillType;
    /**
     * 开始扫描时间（yyyyMMddHHmmss）
     */
    private String beginScanTime;
    /**
     * 扫描地点
     */
    private String scanCompany;
    /**
     * 扫描操作员
     */
    private String scanOperator;
    /**
     * 到货公司
     */
    private String receiveCompany;
    /**
     * 线路
     */
    private String transportBillLine;
    /**
     * 车号
     */
    private String carNumber;
    /**
     * 司机
     */
    private String driverName;
    /**
     * 封车时间（yyyyMMddHHmmss）
     */
    private String sealTime;
    /**
     * 当前列表是否上传
     */
    private boolean isUpload;

    /**
     * 对应的扫描信息列表
     */
    @ToMany(referencedJoinProperty = "mainID")
    private List<ScanSub> scanSubs;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1615580418)
    private transient ScanMainDao myDao;

    @Generated(hash = 518453000)
    public ScanMain(Long mainID, String scanType, int state,
            String transportBillCode, String transportBillType,
            String beginScanTime, String scanCompany, String scanOperator,
            String receiveCompany, String transportBillLine, String carNumber,
            String driverName, String sealTime, boolean isUpload) {
        this.mainID = mainID;
        this.scanType = scanType;
        this.state = state;
        this.transportBillCode = transportBillCode;
        this.transportBillType = transportBillType;
        this.beginScanTime = beginScanTime;
        this.scanCompany = scanCompany;
        this.scanOperator = scanOperator;
        this.receiveCompany = receiveCompany;
        this.transportBillLine = transportBillLine;
        this.carNumber = carNumber;
        this.driverName = driverName;
        this.sealTime = sealTime;
        this.isUpload = isUpload;
    }

    @Generated(hash = 343146878)
    public ScanMain() {
    }

    public Long getMainID() {
        return this.mainID;
    }

    public void setMainID(Long mainID) {
        this.mainID = mainID;
    }

    public String getScanType() {
        return this.scanType;
    }

    public void setScanType(String scanType) {
        this.scanType = scanType;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTransportBillCode() {
        return this.transportBillCode;
    }

    public void setTransportBillCode(String transportBillCode) {
        this.transportBillCode = transportBillCode;
    }

    public String getTransportBillType() {
        return this.transportBillType;
    }

    public void setTransportBillType(String transportBillType) {
        this.transportBillType = transportBillType;
    }

    public String getBeginScanTime() {
        return this.beginScanTime;
    }

    public void setBeginScanTime(String beginScanTime) {
        this.beginScanTime = beginScanTime;
    }

    public String getScanCompany() {
        return this.scanCompany;
    }

    public void setScanCompany(String scanCompany) {
        this.scanCompany = scanCompany;
    }

    public String getScanOperator() {
        return this.scanOperator;
    }

    public void setScanOperator(String scanOperator) {
        this.scanOperator = scanOperator;
    }

    public String getReceiveCompany() {
        return this.receiveCompany;
    }

    public void setReceiveCompany(String receiveCompany) {
        this.receiveCompany = receiveCompany;
    }

    public String getTransportBillLine() {
        return this.transportBillLine;
    }

    public void setTransportBillLine(String transportBillLine) {
        this.transportBillLine = transportBillLine;
    }

    public String getCarNumber() {
        return this.carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getDriverName() {
        return this.driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getSealTime() {
        return this.sealTime;
    }

    public void setSealTime(String sealTime) {
        this.sealTime = sealTime;
    }

    public boolean getIsUpload() {
        return this.isUpload;
    }

    public void setIsUpload(boolean isUpload) {
        this.isUpload = isUpload;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1638535609)
    public List<ScanSub> getScanSubs() {
        if (scanSubs == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ScanSubDao targetDao = daoSession.getScanSubDao();
            List<ScanSub> scanSubsNew = targetDao._queryScanMain_ScanSubs(mainID);
            synchronized (this) {
                if (scanSubs == null) {
                    scanSubs = scanSubsNew;
                }
            }
        }
        return scanSubs;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 255057813)
    public synchronized void resetScanSubs() {
        scanSubs = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 154635659)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getScanMainDao() : null;
    }

    @Override
    public String toString() {
        return "ScanMain{" +
                "mainID=" + mainID +
                ", scanType='" + scanType + '\'' +
                ", state=" + state +
                ", transportBillCode='" + transportBillCode + '\'' +
                ", transportBillType='" + transportBillType + '\'' +
                ", beginScanTime='" + beginScanTime + '\'' +
                ", scanCompany='" + scanCompany + '\'' +
                ", scanOperator='" + scanOperator + '\'' +
                ", receiveCompany='" + receiveCompany + '\'' +
                ", transportBillLine='" + transportBillLine + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", driverName='" + driverName + '\'' +
                ", sealTime='" + sealTime + '\'' +
                ", isUpload=" + isUpload +
                ", scanSubs=" + scanSubs +
                ", daoSession=" + daoSession +
                ", myDao=" + myDao +
                '}';
    }
}
