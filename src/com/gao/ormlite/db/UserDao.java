package com.gao.ormlite.db;

import java.sql.SQLException;

import android.content.Context;

import com.gao.ormlite.bean.User;
import com.j256.ormlite.dao.Dao;

public class UserDao {
    private Context mContext;
    private Dao<User, Integer> mUserDao;
    private DatabaseHelper mDatabaseHelper;

    public UserDao(Context context) {
        this.mContext = context;
        try {
            mDatabaseHelper = DatabaseHelper.getHelper(context);
            mUserDao = mDatabaseHelper.getDao(User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个用户
     * 
     * @param user
     * @throws SQLException
     */
    public void add(User user) {
        /*
         * //事务操作
         * TransactionManager.callInTransaction(helper.getConnectionSource(),
         * new Callable<Void>() {
         * 
         * @Override public Void call() throws Exception { return null; } });
         */
        try {
            mUserDao.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User get(int id) {
        try {
            return mUserDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
