package io.github.ossnass.simplejpa;

import org.jinq.jpa.JinqJPAStreamProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.List;


public class EntityController<T, K> {
    protected EntityManager em;
    protected EntityTransaction et;
    protected Class<T> entityClass;
    protected Class<K> pkClass;
    protected JinqJPAStreamProvider jinq;

    public EntityController(Class<T> entityClass, Class<K> pkClass) {
        this.entityClass = entityClass;
        this.pkClass = pkClass;
        this.em = UserManager.getInstance().getEntityManagerFactory().createEntityManager();
        jinq = new JinqJPAStreamProvider(em.getMetamodel());
    }

    public EntityManager getEm() {
        return this.em;
    }

    public EntityTransaction startTransaction() {
        this.et = this.em.getTransaction();
        this.et.begin();
        return this.et;
    }


    public EntityTransaction commitTransaction() {
        this.et.commit();
        return this.et;
    }


    public EntityTransaction rollbackTransaction() {
        this.et.rollback();
        return this.et;
    }

    public T edit(T entity) {
        T result = null;
        try {
            startTransaction();
            result = this.em.merge(entity);
            commitTransaction();
            DBCommon.setLastException(null);
        } catch (Exception e) {
            DBCommon.setLastException(e);
            rollbackTransaction();
        }
        return result;
    }


    public boolean save(T entity) {
        boolean result = false;
        try {
            startTransaction();

            this.em.persist(entity);

            commitTransaction();
            DBCommon.setLastException(null);
            result = true;
        } catch (Exception e) {
            DBCommon.setLastException(e);
            rollbackTransaction();
        }
        return result;
    }


    public boolean save(T[] entities) {
        boolean result = false;
        try {
            startTransaction();
            for (T entity : entities)
                this.em.persist(entity);
            commitTransaction();
            DBCommon.setLastException(null);
            result = true;
        } catch (Exception e) {
            DBCommon.setLastException(e);
            rollbackTransaction();
        }
        return result;
    }


    public boolean remove(T entity) {
        boolean result = false;
        try {
            startTransaction();
            this.em.remove(this.em.contains(entity) ? entity : this.em.merge(entity));
            commitTransaction();
            DBCommon.setLastException(null);
            result = true;
        } catch (Exception e) {
            DBCommon.setLastException(e);
            rollbackTransaction();
        }
        return result;
    }


    public boolean remove(T[] entities) {
        boolean result = false;
        try {
            startTransaction();
            for (T entity : entities)
                this.em.remove(this.em.contains(entity) ? entity : this.em.merge(entity));
            commitTransaction();
            DBCommon.setLastException(null);
            result = true;
        } catch (Exception e) {
            DBCommon.setLastException(e);
            rollbackTransaction();
        }
        return result;
    }


    public T findByKey(K keyValue) {
        return this.em.find(this.entityClass, keyValue);
    }


    public List<T> findAll() {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> from = cq.from(this.entityClass);
        cq.select((Selection) from);
        return this.em.createQuery(cq).getResultList();
    }
}