package com.gao.ormlite;

import com.gao.ormlite.bean.Article;
import com.gao.ormlite.bean.User;
import com.gao.ormlite.db.ArticleDao;
import com.gao.ormlite.db.UserDao;

import android.test.AndroidTestCase;

public class OrmLiteDbTest extends AndroidTestCase{

    public void testAddArticle() {
        User user = new User();
        user.setName("Gao");
        new UserDao(getContext()).add(user);
        Article article = new Article();
        article.setTitle("GaoMatrix");
        article.setUser(user);
        new ArticleDao(getContext()).add(article);
    }
}
