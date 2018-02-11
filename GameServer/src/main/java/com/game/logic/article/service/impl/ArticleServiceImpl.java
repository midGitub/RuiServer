package com.game.logic.article.service.impl;

import com.game.logic.article.dao.ArticleDao;
import com.game.logic.article.entity.Article;
import com.game.logic.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author king.wyx@qq.com
 */
@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    ArticleDao articleDao;

    @Override
    public Article findArticleById(Long id) {
        return articleDao.findArticleById(id);
    }

    @Override
    public Page<Article> findArticlesByPage(Pageable pageable) {
        return articleDao.findAll(pageable);
    }

    @Override
    public void save(Article article) {
        articleDao.save(article);
    }
}
