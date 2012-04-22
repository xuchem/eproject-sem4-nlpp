/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nlpp.dao;

import com.nlpp.domain.Category;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author SVN - Team
 */
public class CategoryDAO extends HibernateDaoSupport implements IObjectDAO<Category>{

    public List<Category> findAll() {
        return getHibernateTemplate().find("from Category where Status = 0");
    }

    public Category findById(int id) {
        return getHibernateTemplate().get(Category.class, id);
    }

    public Category getById(int id) {
        return getHibernateTemplate().get(Category.class, id);
    }

    public List<Category> findByName(String name) {
       return getHibernateTemplate().find("from Category where CatName like ? and Status = 0",  "%"+ name + "%");
    }
    boolean flag = true;
    public boolean saveObject(Category t) {
        try {
            getHibernateTemplate().saveOrUpdate(t);
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public boolean removeObject(int id) {
        try {
            Category cat = getHibernateTemplate().get(Category.class, id);
            if (cat.getEventses().size() <= 0 && cat.getEmailRecieverEventCategories().size() <= 0) {
                getHibernateTemplate().delete(cat);
            } else {
                cat.setVisiable(1);
                getHibernateTemplate().update(cat);
            }
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public boolean updateObject(Category t) {
        try {
            Category cat = getHibernateTemplate().get(Category.class, t.getCatId());
            cat.setCatName(t.getCatName());
            getHibernateTemplate().update(cat);
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public int count() {
        return getHibernateTemplate().find("from Category where Status = 0").size();
    }

}
