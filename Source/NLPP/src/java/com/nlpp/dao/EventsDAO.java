/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlpp.dao;

import com.nlpp.domain.EnrollEvent;
import com.nlpp.domain.Events;
import com.nlpp.domain.Events;
import com.nlpp.helper.EmailHelper;
import com.nlpp.helper.HibernateUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author SVN - Team
 */
public class EventsDAO extends HibernateDaoSupport implements IObjectDAO<Events> {

    private EmailHelper helper = new EmailHelper();
    private boolean flag = false;

    public List<Events> findAll() {
        List<Events> lEvents = getHibernateTemplate().find("from Events where Visiable = 0 order by EventId desc");
        for (Iterator<Events> it = lEvents.iterator(); it.hasNext();) {
            Events events = it.next();
            HibernateUtils.initialize(events.getCategory());
        }
        return lEvents;
    }

    public List<Events> findAll(int catId) {
        List<Events> lEvents = getHibernateTemplate().find("from Events where Visiable = 0 and CatID=? order by EventId desc", catId);
        for (Iterator<Events> it = lEvents.iterator(); it.hasNext();) {
            Events events = it.next();
            HibernateUtils.initialize(events.getCategory());
        }
        return lEvents;
    }

    public List<Events> findAllTop10() {
        List<Events> lEvents = getHibernateTemplate().find("from Events  where Visiable = 0 order by EventId desc");
        for (Iterator<Events> it = lEvents.iterator(); it.hasNext();) {
            Events events = it.next();
            HibernateUtils.initialize(events.getCategory());
        }
        List<Events> aLisTep = new ArrayList<Events>();
        if (lEvents.size() >= 10) {
            for (int i = 0; i < 10; i++) {
                aLisTep.add(lEvents.get(i));
            }
        } else {
            for (int i = 0; i < lEvents.size(); i++) {
                aLisTep.add(lEvents.get(i));
            }
        }
        System.out.println("Co vao day khong??/" + aLisTep.size());
        return aLisTep;
    }

    public Events findById(int id) {
        Events e = getHibernateTemplate().get(Events.class, id);
        HibernateUtils.initialize(e.getEnrollEvents());
        Set<EnrollEvent> set = e.getEnrollEvents();
        for (Iterator<EnrollEvent> it = set.iterator(); it.hasNext();) {
            EnrollEvent enrollEvent = it.next();
            HibernateUtils.initialize(enrollEvent.getPerson());
        }
        return e;
    }

    public List<Events> findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean saveObject(Events t) {
        int catId = -1;
        try {
            getHibernateTemplate().save(t);
            catId = t.getEventId();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        if (flag) {
            /*
            }*/
        }
        return flag;
    }

    public Events saveEvents(Events evt) {
        try {
            getHibernateTemplate().save(evt);
            return evt;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean removeObject(int id) {
        try {
            Events e = getHibernateTemplate().get(Events.class, id);
            getHibernateTemplate().delete(e);
            return true;
        } catch (Exception e) {
            flag = false;
        }
        return false;
    }

    public boolean updateObject(Events t) {
        try {
            Events e = getHibernateTemplate().get(Events.class, t.getEventId());
            e.setCategory(t.getCategory());
            e.setEventName(t.getEventName());
            e.setDeadline(t.getDeadline());
            e.setDescription(t.getDescription());
            e.setDetails(t.getDetails());
            e.setImages(t.getImages());
            e.setPrice(t.getPrice());
            e.setTimeEvent(t.getTimeEvent());
            getHibernateTemplate().update(e);
            return true;
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
            flag = false;
        }
        return flag;
    }

    public boolean updateViewCount(int id) {
        try {
            Events e = getHibernateTemplate().get(Events.class, id);
            e.setCountView(e.getCountView() + 1);
            getHibernateTemplate().update(e);
            return true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public int count() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
