/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlpp.dao;

import com.nlpp.domain.EnrollEvent;
import com.nlpp.helper.HibernateUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author SVN - Team
 */
public class EnrollEventDAO extends HibernateDaoSupport implements IObjectDAO<EnrollEvent> {

    public List<EnrollEvent> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<EnrollEvent> findAll(int evtID) {
        return getHibernateTemplate().find("from EnrollEvent where EventID=? and Status = 0", evtID);
    }

    public EnrollEvent findById(int id) {
        return getHibernateTemplate().get(EnrollEvent.class, id);
    }

    public List<EnrollEvent> findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    private boolean flag = false;

    public boolean saveObject(EnrollEvent t) {
        try {
            getHibernateTemplate().saveOrUpdate(t);
            return true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public boolean removeObject(int id) {
        try {
            EnrollEvent ee = getHibernateTemplate().get(EnrollEvent.class, id);
            getHibernateTemplate().delete(ee);
            return true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public boolean updateObject(EnrollEvent t) {
        try {
            EnrollEvent ee = getHibernateTemplate().get(EnrollEvent.class, t.getEnrollEventId());
            ee.setIsWin(t.getIsWin());
            return true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public boolean updatePayment(EnrollEvent ev) {
        try {
            EnrollEvent ee = getHibernateTemplate().get(EnrollEvent.class, ev.getEnrollEventId());
            HibernateUtils.initialize(ee.getEvents());
            ee.setPayMoney(BigDecimal.valueOf(ee.getEvents().getPrice()));
            ee.setDatePay(ev.getDatePay());
            ee.setIsPayment(1);
            getHibernateTemplate().update(ee);
            return true;
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
            flag = false;
        }
        return flag;
    }

    public boolean updateIsWin(EnrollEvent en) {
        try {
             EnrollEvent ee = getHibernateTemplate().get(EnrollEvent.class, en.getEnrollEventId());
             ee.setIsWin(1);
             getHibernateTemplate().update(ee);
             return true;
        } catch (Exception e) {
            return false;
        }
    }

    public EnrollEvent getEnrollEventByPerson(int personId) {
        List<EnrollEvent> aList = new ArrayList<EnrollEvent>();
        aList = getHibernateTemplate().find("from EnrollEvent where UserId=? and Status = 0", personId);
        EnrollEvent enroll = null;
        if (aList.size() >= 1) {
            enroll = aList.get(0);
            HibernateUtils.initialize(enroll.getEvents());
            HibernateUtils.initialize(enroll.getPerson());
        }
        return enroll;
    }

    public List<EnrollEvent> getEnrollEventsByPersonId(int perId) {
        List<EnrollEvent> aList = new ArrayList<EnrollEvent>();
        aList = getHibernateTemplate().find("from EnrollEvent where UserId=? and Status = 0", perId);
        for (Iterator<EnrollEvent> it = aList.iterator(); it.hasNext();) {
            EnrollEvent enrollEvent = it.next();
            HibernateUtils.initialize(enrollEvent.getEvents());
            HibernateUtils.initialize(enrollEvent.getPerson());
        }
        Date now = new Date();
        List<EnrollEvent> aListEnroll = new ArrayList<EnrollEvent>();
        for (Iterator<EnrollEvent> it = aList.iterator(); it.hasNext();) {
            EnrollEvent enrollEvent = it.next();
            if (enrollEvent.getEvents().getTimeEvent().before(now)) {
                enrollEvent.setExpires(true);
            } else {
                enrollEvent.setExpires(false);
            }
            aListEnroll.add(enrollEvent);
        }
        return aListEnroll;
    }

    public EnrollEvent findByPersonEventId(int perId, int evtId) {
        EnrollEvent e = new EnrollEvent();
        List<EnrollEvent> aListEnroll = getHibernateTemplate().find("from EnrollEvent where UserId=? and EventId =? and Status = 0", perId, evtId);
        if (aListEnroll.size() >= 1) {
            e = aListEnroll.get(0);
            HibernateUtils.initialize(e.getPerson());
            HibernateUtils.initialize(e.getEvents());
        }
        return e;
    }

    public int count() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<EnrollEvent> findTop10() {
        List<EnrollEvent> aListEnroll = getHibernateTemplate().find("from EnrollEvent where Status = 0 order by desc");
        for (Iterator<EnrollEvent> it = aListEnroll.iterator(); it.hasNext();) {
            EnrollEvent enrollEvent = it.next();
            HibernateUtils.initialize(enrollEvent.getEvents());
            HibernateUtils.initialize(enrollEvent.getPerson());
        }
        List<EnrollEvent> aListTemp = new ArrayList<EnrollEvent>();
        if (aListEnroll.size() >= 10) {
            for (int i = 0; i < 10; i++) {
                aListTemp.add(aListEnroll.get(i));
            }
        } else {
            for (int i = 0; i < aListEnroll.size(); i++) {
                aListTemp.add(aListEnroll.get(i));
            }
        }
        return aListTemp;
    }
}
