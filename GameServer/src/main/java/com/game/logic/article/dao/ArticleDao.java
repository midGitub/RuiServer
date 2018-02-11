package com.game.logic.article.dao;

import com.game.logic.article.entity.Article;
import com.game.framework.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author king.wyx@qq.com
 */
@Repository
public interface ArticleDao extends BaseRepository<Article, Long> {

    Article findArticleById(Long id);

}
