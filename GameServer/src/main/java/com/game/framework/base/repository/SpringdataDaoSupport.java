//package com.game.framework.base.repository;
//
//import java.util.List;
//import java.util.Map;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import javax.transaction.Transactional;
//
//import org.springframework.stereotype.Repository;
//
///**
// * Springdata功能扩展,支持
// **/
//@Repository
//public class SpringdataDaoSupport<E> {
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Transactional
//    public void saveData(E e) {
//        em.persist(e);
//    }
//
//    /**
//     * 根据原生sql查询结果集
//     *
//     * @param sql
//     * @param param 结果集为List<Object[]>
//     * @return
//     */
//    public List<Object[]> findSQL(String sql, Object... param) {
//        List<Object[]> list = execute(sql,param);
//        return list;
//    }
//
//    /**
//     * 根据原生sql查询Map结果集
//     *
//     * @param sql
//     * @param param 结果集为List<Map>
//     * @return
//     */
//    public List<Map> findMapSQL(String sql, Object... param) {
//        List<Map> list = execute(sql,param);
//        return list;
//    }
//
//    /**
//     * 根据原生sql查询List结果
//     *
//     * @param sql
//     * @param param 结果集为 List<Object>
//     * @return
//     */
//    public List<Object> findListSQL(String sql, Object... param) {
//        List<Object> list = execute(sql,param);
//        return list;
//    }
//
//    /**
//     * 查询执行
//     * @return 结果集
//     */
//    public List execute(String sql, Object... param){
//        try {
//            Query nq = em.createNativeQuery(sql);
//            if (param != null) {
//                for (int i = 1; i <= param.length; i++) {
//                    nq.setParameter(i, param[i - 1]);
//                }
//            }
//            return nq.getResultList();
//        } catch (RuntimeException re) {
//            throw re;
//        }
//    }
//
//
//}
