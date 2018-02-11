package com.game.framework.base.repository;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author liguorui
 * @date 2018/1/30 13:39
 */
public class BaseRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {

    private final EntityManager em;

    public BaseRepositoryFactory(EntityManager em) {
        super(em);
        this.em = em;
    }

    //设置具体的实现类是BaseRepositoryImpl
    @Override
    protected Object getTargetRepository(RepositoryInformation information) {
        return new BaseRepositoryImpl<T, I>((Class<T>) information.getDomainType(), em);
    }

    //设置具体的实现类的class
    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        return BaseRepositoryImpl.class;
    }
}
