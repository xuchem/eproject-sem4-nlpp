/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nlpp.dao;

import com.nlpp.domain.Comment;
import com.nlpp.helper.HibernateUtils;
import java.util.Iterator;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author SVN - Team
 */
public class CommentDAO extends HibernateDaoSupport implements IObjectDAO<Comment>{
    private boolean flag = false;
    public List<Comment> findAll() {
        return getHibernateTemplate().find("from Comment");
    }

    public List<Comment> findAll(int id) {
        List<Comment> aList = getHibernateTemplate().find("from Comment where EventID=?",id);
        for (Iterator<Comment> it = aList.iterator(); it.hasNext();) {
            Comment comment = it.next();
            HibernateUtils.initialize(comment.getPerson());
        }
        return aList;
    }

    public Comment findById(int id) {
        return getHibernateTemplate().get(Comment.class, id);
    }

    public List<Comment> findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean saveObject(Comment t) {
        try {
            getHibernateTemplate().saveOrUpdate(t);
            flag = true;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            flag = false;
        }
        return flag;
    }

    public boolean removeObject(int id) {
        try {
            Comment c = getHibernateTemplate().get(Comment.class, id);
            getHibernateTemplate().delete(c);
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public boolean updateObject(Comment t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int count() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int count(int id) {
        return getHibernateTemplate().find("from Comment where EventId=?", id).size();
    }

}
