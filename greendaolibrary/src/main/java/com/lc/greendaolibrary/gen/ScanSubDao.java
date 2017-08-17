package com.lc.greendaolibrary.gen;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.lc.greendaolibrary.dao.scan.ScanSub;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SCAN_SUB".
*/
public class ScanSubDao extends AbstractDao<ScanSub, Long> {

    public static final String TABLENAME = "SCAN_SUB";

    /**
     * Properties of entity ScanSub.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property SubID = new Property(0, Long.class, "subID", true, "_id");
        public final static Property MainID = new Property(1, Long.class, "mainID", false, "MAIN_ID");
        public final static Property ScanType = new Property(2, String.class, "scanType", false, "SCAN_TYPE");
        public final static Property ScanTime = new Property(3, String.class, "scanTime", false, "SCAN_TIME");
        public final static Property BarCode = new Property(4, String.class, "barCode", false, "BAR_CODE");
        public final static Property Sn = new Property(5, String.class, "sn", false, "SN");
        public final static Property State = new Property(6, Integer.class, "state", false, "STATE");
        public final static Property Manual = new Property(7, boolean.class, "manual", false, "MANUAL");
        public final static Property EditTime = new Property(8, String.class, "editTime", false, "EDIT_TIME");
        public final static Property IsUpload = new Property(9, boolean.class, "isUpload", false, "IS_UPLOAD");
    }

    private Query<ScanSub> scanMain_ScanSubsQuery;

    public ScanSubDao(DaoConfig config) {
        super(config);
    }
    
    public ScanSubDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SCAN_SUB\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: subID
                "\"MAIN_ID\" INTEGER," + // 1: mainID
                "\"SCAN_TYPE\" TEXT," + // 2: scanType
                "\"SCAN_TIME\" TEXT," + // 3: scanTime
                "\"BAR_CODE\" TEXT," + // 4: barCode
                "\"SN\" TEXT," + // 5: sn
                "\"STATE\" INTEGER," + // 6: state
                "\"MANUAL\" INTEGER NOT NULL ," + // 7: manual
                "\"EDIT_TIME\" TEXT," + // 8: editTime
                "\"IS_UPLOAD\" INTEGER NOT NULL );"); // 9: isUpload
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SCAN_SUB\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ScanSub entity) {
        stmt.clearBindings();
 
        Long subID = entity.getSubID();
        if (subID != null) {
            stmt.bindLong(1, subID);
        }
 
        Long mainID = entity.getMainID();
        if (mainID != null) {
            stmt.bindLong(2, mainID);
        }
 
        String scanType = entity.getScanType();
        if (scanType != null) {
            stmt.bindString(3, scanType);
        }
 
        String scanTime = entity.getScanTime();
        if (scanTime != null) {
            stmt.bindString(4, scanTime);
        }
 
        String barCode = entity.getBarCode();
        if (barCode != null) {
            stmt.bindString(5, barCode);
        }
 
        String sn = entity.getSn();
        if (sn != null) {
            stmt.bindString(6, sn);
        }
 
        Integer state = entity.getState();
        if (state != null) {
            stmt.bindLong(7, state);
        }
        stmt.bindLong(8, entity.getManual() ? 1L: 0L);
 
        String editTime = entity.getEditTime();
        if (editTime != null) {
            stmt.bindString(9, editTime);
        }
        stmt.bindLong(10, entity.getIsUpload() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ScanSub entity) {
        stmt.clearBindings();
 
        Long subID = entity.getSubID();
        if (subID != null) {
            stmt.bindLong(1, subID);
        }
 
        Long mainID = entity.getMainID();
        if (mainID != null) {
            stmt.bindLong(2, mainID);
        }
 
        String scanType = entity.getScanType();
        if (scanType != null) {
            stmt.bindString(3, scanType);
        }
 
        String scanTime = entity.getScanTime();
        if (scanTime != null) {
            stmt.bindString(4, scanTime);
        }
 
        String barCode = entity.getBarCode();
        if (barCode != null) {
            stmt.bindString(5, barCode);
        }
 
        String sn = entity.getSn();
        if (sn != null) {
            stmt.bindString(6, sn);
        }
 
        Integer state = entity.getState();
        if (state != null) {
            stmt.bindLong(7, state);
        }
        stmt.bindLong(8, entity.getManual() ? 1L: 0L);
 
        String editTime = entity.getEditTime();
        if (editTime != null) {
            stmt.bindString(9, editTime);
        }
        stmt.bindLong(10, entity.getIsUpload() ? 1L: 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ScanSub readEntity(Cursor cursor, int offset) {
        ScanSub entity = new ScanSub( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // subID
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // mainID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // scanType
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // scanTime
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // barCode
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // sn
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // state
            cursor.getShort(offset + 7) != 0, // manual
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // editTime
            cursor.getShort(offset + 9) != 0 // isUpload
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ScanSub entity, int offset) {
        entity.setSubID(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMainID(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setScanType(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setScanTime(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setBarCode(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setSn(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setState(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setManual(cursor.getShort(offset + 7) != 0);
        entity.setEditTime(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setIsUpload(cursor.getShort(offset + 9) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ScanSub entity, long rowId) {
        entity.setSubID(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ScanSub entity) {
        if(entity != null) {
            return entity.getSubID();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ScanSub entity) {
        return entity.getSubID() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "scanSubs" to-many relationship of ScanMain. */
    public List<ScanSub> _queryScanMain_ScanSubs(Long mainID) {
        synchronized (this) {
            if (scanMain_ScanSubsQuery == null) {
                QueryBuilder<ScanSub> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.MainID.eq(null));
                scanMain_ScanSubsQuery = queryBuilder.build();
            }
        }
        Query<ScanSub> query = scanMain_ScanSubsQuery.forCurrentThread();
        query.setParameter(0, mainID);
        return query.list();
    }

}
