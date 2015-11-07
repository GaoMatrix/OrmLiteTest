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
        mDatabaseHelper = DatabaseHelper.getInstance(context);
        try {
            mUserDao = mDatabaseHelper.getDao(User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(User user) {
        try {
            mUserDao.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Other operation....

}
