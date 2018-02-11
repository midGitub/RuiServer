package com.game.framework.base.repository;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author liguorui
 * @date 2018/1/30 14:46
 */
@Transactional
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T,ID>
        implements BaseRepository<T,ID> {

    private final EntityManager entityManager;

    /**
     * 父类没有不带参数的构造方法，这里手动构造父类
     * @param domainClass
     * @param entityManager
     */
    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }


    @Override
    public boolean insertData(T object) {
//        entityManager.persist(object);
        super.saveAndFlush(object);
        return true;
    }

    @Override
    public boolean updateData(T object) {
//        object = entityManager.merge(object);
        object = super.saveAndFlush(object);
        return true;
    }

    @Override
    public boolean deleteData(T object) {
        super.delete(object);
        return true;
    }
}
