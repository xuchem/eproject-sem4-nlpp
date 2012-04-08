/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlpp.dao;

import com.nlpp.domain.EmailRecieverEventCategory;
import com.nlpp.domain.Person;
import com.nlpp.helper.HibernateUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author SVN - Team
 */
public class EmailRecieverEventCategoryDAO extends HibernateDaoSupport implements IObjectDAO<EmailRecieverEventCategory> {

    public List<EmailRecieverEventCategory> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public EmailRecieverEventCategory findById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<EmailRecieverEventCategory> findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public List<EmailRecieverEventCategory> findByPersonId(int  perId) {
        List<EmailRecieverEventCategory> aListEmailR = new ArrayList<EmailRecieverEventCategory>();
        aListEmailR = getHibernateTemplate().find("from EmailRecieverEventCategory where PersonId = ?", perId);
        for (Iterator<EmailRecieverEventCategory> it = aListEmailR.iterator(); it.hasNext();) {
            EmailRecieverEventCategory emailRecieverEventCategory = it.next();
            HibernateUtils.initialize(emailRecieverEventCategory.getCategory());
        }
        return aListEmailR;
    }

    public List<String> findEmailByCategoryId(int catId) {
        List<String> aListEmail = new ArrayList<String>();
        List<EmailRecieverEventCategory> aList = getHibernateTemplate().find("from EmailRecieverEventCategory where CatID = ?", catId);
        for (Iterator<EmailRecieverEventCategory> it = aList.iterator(); it.hasNext();) {
            EmailRecieverEventCategory emailRecieverEventCategory = it.next();
            HibernateUtils.initialize(emailRecieverEventCategory.getPerson());
        }
        for (Iterator<EmailRecieverEventCategory> it = aList.iterator(); it.hasNext();) {
            EmailRecieverEventCategory emailRecieverEventCategory = it.next();
            aListEmail.add(emailRecieverEventCategory.getPerson().getEmail());
        }
        System.out.println("Length Send Email: " + aListEmail.size());
        return aListEmail;
    }
    private boolean flag = false;

    public boolean saveObject(EmailRecieverEventCategory t) {
        try {
            getHibernateTemplate().saveOrUpdate(t);
            return true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public boolean saveEmailReciver(List<EmailRecieverEventCategory> aList) {
        try {
            if(aList.size() >= 1) {
                EmailRecieverEventCategory ec = aList.get(0);
                List<EmailRecieverEventCategory> aListRemove = getHibernateTemplate().find("from EmailRecieverEventCategory where PersonId = ?", ec.getPerson().getPersonId());
                getHibernateTemplate().deleteAll(aListRemove);
            }
            getHibernateTemplate().saveOrUpdateAll(aList);
            return true;
        }  catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public boolean removeObject(int id) {
        try {
            EmailRecieverEventCategory erec = getHibernateTemplate().get(EmailRecieverEventCategory.class, id);
            getHibernateTemplate().delete(erec);
            return true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public boolean updateObject(EmailRecieverEventCategory t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int count() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
