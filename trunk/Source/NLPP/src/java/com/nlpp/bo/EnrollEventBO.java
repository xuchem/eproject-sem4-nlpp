/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlpp.bo;

import com.nlpp.dao.EnrollEventDAO;
import com.nlpp.domain.EnrollEvent;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SVN - Team
 */
public class EnrollEventBO {

    private EnrollEventDAO enrollEventDAO;

    public void setEnrollEventDAO(EnrollEventDAO enrollEventDAO) {
        this.enrollEventDAO = enrollEventDAO;
    }

    public List<EnrollEvent> findAll(int id) {
        return enrollEventDAO.findAll(id);
    }

    public boolean saveEnrollEvent(EnrollEvent ee) {
        return enrollEventDAO.saveObject(ee);
    }

    public boolean updateEnrollEvent(EnrollEvent ee) {
        return enrollEventDAO.updateObject(ee);
    }

    public boolean removeEnrollEvent(int id) {
        return enrollEventDAO.removeObject(id);
    }

    public boolean updatePayment(EnrollEvent ee) {
        return enrollEventDAO.updatePayment(ee);
    }

    public boolean updateIsWin(EnrollEvent en) {
        return enrollEventDAO.updateIsWin(en);
    }

    public EnrollEvent getEnrollEventByPerson(int personId) {
        return enrollEventDAO.getEnrollEventByPerson(personId);
    }

    public List<EnrollEvent> getEnrollEventsByPersonId(int perId) {
        return enrollEventDAO.getEnrollEventsByPersonId(perId);
    }

    public List<EnrollEvent> findTop10() {
        return enrollEventDAO.findTop10();
    }

    public EnrollEvent findByPersonEventId(int perId, int evtId) {
        return enrollEventDAO.findByPersonEventId(perId, evtId);
    }
}
