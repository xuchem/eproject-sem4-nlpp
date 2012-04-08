/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlpp.bo;

import com.nlpp.dao.EventsDAO;
import com.nlpp.domain.Events;
import java.util.List;

/**
 *
 * @author SVN - Team
 */
public class EventsBO {

    private EventsDAO eventsDAO;

    public void setEventsDAO(EventsDAO eventsDAO) {
        this.eventsDAO = eventsDAO;
    }

    public List<Events> findAll() {
        return eventsDAO.findAll();
    }

    public List<Events> findAll(int catId) {
        return eventsDAO.findAll(catId);
    }

    public Events findEventById(int eventId) {
        return eventsDAO.findById(eventId);
    }

    public List<Events> findByName(String name) {
        return eventsDAO.findByName(name);
    }

    public Events findById(int id) {
        return eventsDAO.findById(id);
    }

    public boolean saveEvents(Events evt) {
        return eventsDAO.saveObject(evt);
    }

    public Events saveEvent(Events evt) {
        return eventsDAO.saveEvents(evt);
    }

    public boolean updateEvents(Events evt) {
        return eventsDAO.updateObject(evt);
    }

    public boolean removeEvents(int id) {
        return eventsDAO.removeObject(id);
    }
    public boolean updateViewCount(int id) {
        return eventsDAO.updateViewCount(id);
    }
    public List<Events> findAllTop10() {
        return eventsDAO.findAllTop10();
    }
}
