package com.lc.greendaolibrary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.lc.greendaolibrary.gen.DaoMaster;
import com.lc.greendaolibrary.gen.DaoSession;
import com.lc.greendaolibrary.utils.MyOpenHelper;

/**
 * Created by 丛龙宇 on 2017/8/18.
 */

public class DbManager {

    private static DbManager mINSTANCE;
    private final DaoSession daoSession;

    private DbManager(Context context) {
        MyOpenHelper helper = new MyOpenHelper(context.getApplicationContext(), "scan_db.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster master = new DaoMaster(db);
        daoSession = master.newSession();
    }

    public static DbManager getINSTANCE(Context context) {
        if (mINSTANCE == null) {
            mINSTANCE = new DbManager(context);
        }
        return mINSTANCE;
    }


    public DaoSession getDaoSession() {
        return daoSession;
    }
}
