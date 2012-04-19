/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlpp.facade;

import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Hung
 */
public abstract class BaseFacade<TSource, TKey extends Serializable> extends HibernateDaoSupport implements IFacade<TSource, TKey> {

    private Class<TSource> cls;

    protected BaseFacade(Class<TSource> tClass) {
        cls = tClass;
    }

    public List<TSource> findAll() {
        return getSession().createCriteria(cls).list();
    }

    public TSource findByKey(TKey k) {
        return getHibernateTemplate().get(cls, k);
    }

    public boolean saveOrUpdate(TSource t) {
        try {
            getHibernateTemplate().saveOrUpdate(t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean remove(TSource t) {
        try {
            getHibernateTemplate().delete(t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean removeByKey(TKey k) {
        try {
            getHibernateTemplate().delete(findByKey(k));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int count() {
        return Integer.parseInt(getSession().createCriteria(cls).setProjection(Projections.rowCount()).uniqueResult().toString());
    }
}
