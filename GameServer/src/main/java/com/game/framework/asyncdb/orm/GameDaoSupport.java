//package com.game.framework.asyncdb.orm;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//import java.io.Serializable;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.util.List;
//
///**
// * @author liguorui
// * @date 2018/1/15 00:15
// */
//@Transactional
//public abstract class GameDaoSupport<E> extends HibernateDaoSupport implements BaseDao<E> {
//
//    private Class<E> entityClazz;
//
//    private boolean deserialize;
//
//    @Resource
//    private DruidDataSource druidDataSource;
//
//    public GameDaoSupport() {
//        super();
//        Type type = getClass().getGenericSuperclass();
//        if (!ParameterizedType.class.isAssignableFrom(type.getClass())) {
//            throw new RuntimeException("类" + getClass() + "继承必须使用泛型");
//        }
//        this.entityClazz = (Class)((ParameterizedType)type).getActualTypeArguments()[0];
//        this.deserialize = BaseDBEntity.class.isAssignableFrom(entityClazz);
//    }
//
//    @Autowired
//    public void setSuperSessionFactory(SessionFactory sessionFactory) {
//        super.setSessionFactory(sessionFactory);
//    }
//
//    public Session getSession() {
//        return getSessionFactory().getCurrentSession();
//    }
//
//    public E get(Serializable id) {
//        E e = getHibernateTemplate().get(entityClazz, id);
//        if (e != null && deserialize) {
//            BaseDBEntity entity = (BaseDBEntity)e;
//            entity.deserialize();
//        }
//        return e;
//    }
//
//    @Override
//    public List<E> getAll() {
//        List<E> eList = getHibernateTemplate().loadAll(entityClazz);
//        if (deserialize) {
//            for (E e : eList) {
//                BaseDBEntity entity = (BaseDBEntity)e;
//                entity.deserialize();
//            }
//        }
//        return eList;
//    }
//
//    @Override
//    public boolean insert(E object) {
//        getHibernateTemplate().save(object);
//        return false;
//    }
//
//    @Override
//    public boolean update(E object) {
//        getHibernateTemplate().update(object);
//        return false;
//    }
//
//    public boolean delete(E object) {
//        getHibernateTemplate().delete(object);
//        return false;
//    }
//}
