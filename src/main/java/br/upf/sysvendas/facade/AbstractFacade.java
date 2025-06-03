package br.upf.sysvendas.facade;

import jakarta.persistence.EntityManager;
import java.util.List;

public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public T createReturn(T entity) {
        getEntityManager().persist(entity);
        return getEntityManager().merge(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public T editReturn(T entity) {
        return getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        var cb = getEntityManager().getCriteriaBuilder();
        var cq = cb.createQuery(entityClass);
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        var cb = getEntityManager().getCriteriaBuilder();
        var cq = cb.createQuery(entityClass);
        cq.select(cq.from(entityClass));
        var q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        var cb = getEntityManager().getCriteriaBuilder();
        var cq = cb.createQuery(Long.class);
        var rt = cq.from(entityClass);
        cq.select(cb.count(rt));
        return getEntityManager().createQuery(cq).getSingleResult().intValue();
    }
}
