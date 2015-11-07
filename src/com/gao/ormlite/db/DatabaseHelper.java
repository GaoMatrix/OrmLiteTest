package com.gao.ormlite.db;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.gao.ormlite.bean.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String TABLE_NAME = "gao.db";
    private Map<String, Dao> mDaos = new HashMap<String, Dao>();
    private static DatabaseHelper mInstance;

    private DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 2);
    }

    public synchronized static DatabaseHelper getInstance(Context context) {
        if (null == mInstance) {
            synchronized (DatabaseHelper.class) {
                if (null == mInstance) {
                    mInstance = new DatabaseHelper(context);
                }
            }
        }
        return mInstance;
    }

    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();
        if (mDaos.containsKey(clazz)) {
            dao = mDaos.get(className);
        }
        if (null == dao) {
            dao = super.getDao(clazz);
            mDaos.put(className, dao);
        }
        return dao;
    }

    public void close() {
        super.close();
        for (String key : mDaos.keySet()) {
            Dao dao = mDaos.get(key);
            dao = null;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase database,
            ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
            ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        onCreate(database, connectionSource);
    }

}
