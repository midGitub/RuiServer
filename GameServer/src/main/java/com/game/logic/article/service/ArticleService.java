package com.game.logic.article.service;

import com.game.logic.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author king.wyx@qq.com
 */
public interface ArticleService {

    Article findArticleById(Long id);

    Page<Article> findArticlesByPage(Pageable pageable);

    void save(Article article);
}
