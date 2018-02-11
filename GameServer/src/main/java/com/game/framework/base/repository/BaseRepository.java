package com.game.framework.base.repository;

import com.game.framework.asyncdb.Synchronizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @author liguorui
 * @date 2018/1/28 13:00
 */
@Transactional
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, Synchronizer<T> {

}
