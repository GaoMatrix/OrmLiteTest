package com.gao.ormlite.db;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;

import com.gao.ormlite.bean.Article;
import com.gao.ormlite.bean.User;
import com.j256.ormlite.dao.Dao;

public class ArticleDao {
    private Dao<Article, Integer> mArticleDao;
    private DatabaseHelper mDatabaseHelper;

    @SuppressWarnings("unchecked")
    public ArticleDao(Context context) {
        try {
            mDatabaseHelper = DatabaseHelper.getInstance(context);
            mArticleDao = mDatabaseHelper.getDao(Article.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加一个Article
     * 
     * @param article
     */
    public void add(Article article) {
        try {
            mArticleDao.create(article);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过Id得到一个Article
     * 
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    public Article getArticleWithUser(int id) {
        Article article = null;
        try {
            article = mArticleDao.queryForId(id);
            mDatabaseHelper.getDao(User.class).refresh(article.getUser());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    /**
     * 通过Id得到一篇文章
     * 
     * @param id
     * @return
     */
    public Article get(int id) {
        Article article = null;
        try {
            article = mArticleDao.queryForId(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    /**
     * 通过UserId获取所有的文章
     * 
     * @param userId
     * @return
     */
    public List<Article> listByUserId(int userId) {
        try {
            return mArticleDao.queryBuilder().where().eq("user_id", userId)
                    .query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
