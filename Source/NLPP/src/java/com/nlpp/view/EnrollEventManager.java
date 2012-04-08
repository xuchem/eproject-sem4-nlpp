/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlpp.view;

import com.nlpp.bo.EnrollEventBO;
import com.nlpp.bo.PersonBO;
import com.nlpp.domain.EnrollEvent;
import com.nlpp.domain.Events;
import com.nlpp.domain.Person;
import com.nlpp.helper.EncryptHelper;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SVN - Team
 */
public class EnrollEventManager extends Person {

    /** Creates a new instance of EnrollEventManager */
    public EnrollEventManager() {
    }
    private EnrollEventBO managerEnrollEvent;
    private EncryptHelper eh = new EncryptHelper();
    private PersonBO pEnrollEvent;
    private int isPayment;
    private BigDecimal payMoney;
    private EnrollEvent selectEnroll;
    private Date currentDate;

    public Date getCurrentDate() {
        currentDate = new Date();
        return currentDate;
    }

    public EnrollEvent getSelectEnroll() {
        return selectEnroll;
    }

    public void setSelectEnroll(EnrollEvent selectEnroll) {
        this.selectEnroll = selectEnroll;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public int getIsPayment() {
        return isPayment;
    }

    public void setIsPayment(int isPayment) {
        this.isPayment = isPayment;
    }

    public PersonBO getpEnrollEvent() {
        return pEnrollEvent;
    }

    public void setpEnrollEvent(PersonBO pEnrollEvent) {
        this.pEnrollEvent = pEnrollEvent;
    }

    public EnrollEventBO getManagerEnrollEvent() {
        return managerEnrollEvent;
    }

    public void setManagerEnrollEvent(EnrollEventBO managerEnrollEvent) {
        this.managerEnrollEvent = managerEnrollEvent;
    }

    public List<EnrollEvent> getAllEnrollEvent() {
        int id = Integer.parseInt(m.get("id").toString());
        return managerEnrollEvent.findAll(id);
    }

    public String saveEnrollEvent() {
        EnrollEvent ee = new EnrollEvent();
        Events e = new Events();
        ee.setEvents(e);
        Person p = new Person();
        ee.setPerson(p);
        ee.setIsWin(0);
        ee.setVisiable(0);
        boolean flag = managerEnrollEvent.saveEnrollEvent(ee);
        return null;
    }
    Map m = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

    public String saveEventEnroll() {
        Person p = new Person();
        p.setPersonName(this.getPersonName());
        p.setAddress(this.getAddress());
        p.setGender(this.getGender());
        p.setBirthday(this.getBirthday());
        p.setEmail(this.getEmail());
        p.setPassword(eh.encriptMd5Password("123456"));
        p.setVisiable(0);
        Date now = new Date();
        p.setDateCreate(now);
        p.setPhone(this.getPhone());
        p.setRoles(0);
        Person per = pEnrollEvent.savePersons(p);
        if (per != null) {
            int id = Integer.parseInt(m.get("id").toString());
            double pricex = Float.parseFloat(m.get("price").toString());
            EnrollEvent enroll = new EnrollEvent();
            Events evt = new Events();
            evt.setEventId(id);
            enroll.setEvents(evt);
            enroll.setDateReg(now);
            enroll.setPerson(per);
            enroll.setIsPayment(this.getIsPayment());
            if (this.getIsPayment() == 1) {
                enroll.setPayMoney(BigDecimal.valueOf(pricex));
                enroll.setDatePay(now);
            }
            enroll.setIsWin(0);
            boolean flag = managerEnrollEvent.saveEnrollEvent(enroll);
            if (flag) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("addNewEnrollEvents.xhtml?id=" + id);
                } catch (IOException ex) {
                    Logger.getLogger(EnrollEventManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public String saveEventHasAccount() {
        EnrollEvent enroll = new EnrollEvent();
        Events e = new Events();
        Person p = new Person();
        int id = Integer.parseInt(m.get("id").toString());
        int perId = Integer.parseInt(m.get("perId").toString());
        e.setEventId(id);
        enroll.setEvents(e);
        p.setPersonId(perId);
        enroll.setPerson(p);
        enroll.setIsPayment(this.getIsPayment());
        Date now = new Date();
        enroll.setDateReg(now);
        if (this.getIsPayment() == 1) {
            double pricex = Float.parseFloat(m.get("price").toString());
            enroll.setPayMoney(BigDecimal.valueOf(pricex));
            enroll.setDatePay(now);

        }
        enroll.setIsWin(0);
        boolean flag = managerEnrollEvent.saveEnrollEvent(enroll);
        if (flag) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("addNewEnrollEvents.xhtml?id=" + id);
            } catch (IOException ex) {
                Logger.getLogger(EnrollEventManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    public String saveEnrollEventsHome() {
        boolean flag = false;
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        int id = Integer.parseInt(m.get("id").toString());
        Person per = (Person) session.getAttribute("loginH");
        EnrollEvent enroll = new EnrollEvent();
        Events e = new Events();
        e.setEventId(id);
        Date now = new Date();
        enroll.setDateReg(now);
        enroll.setPerson(per);
        enroll.setEvents(e);
        enroll.setIsPayment(0);
        enroll.setIsWin(0);
        enroll.setVisiable(0);
        flag = managerEnrollEvent.saveEnrollEvent(enroll);
        if (flag) {
            return "notice";
        }
        return null;
    }

    public String removeEnrollEvent() {
        int id = Integer.parseInt(m.get("id").toString());
        boolean flag = managerEnrollEvent.removeEnrollEvent(id);
        return null;
    }

    public EnrollEvent getEnrollEventByPerson() {
        int id = Integer.parseInt(m.get("id").toString());
        return managerEnrollEvent.getEnrollEventByPerson(id);
    }

    public String paymentEnrollEvent() {
        EnrollEvent ee = new EnrollEvent();
        Date now = new Date();
        ee.setDatePay(now);
        ee.setIsPayment(1);
        int id = Integer.parseInt(m.get("idEnroll").toString());
        ee.setEnrollEventId(id);
        boolean flag = managerEnrollEvent.updatePayment(ee);
        return null;
    }

    public String updateIsWin() {
        int id = Integer.parseInt(m.get("idEnroll").toString());
        EnrollEvent ee = new EnrollEvent();
        ee.setEnrollEventId(id);
        boolean flag = managerEnrollEvent.updateIsWin(ee);
        return null;
    }

    public List<EnrollEvent> getEnrollEventsByPersonId() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        Person p = (Person) session.getAttribute("loginH");
        return managerEnrollEvent.getEnrollEventsByPersonId(p.getPersonId());
    }

    public List<EnrollEvent> getTop10() {
        return managerEnrollEvent.findTop10();
    }

    public EnrollEvent getByPersonEventId() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        Person p = (Person) session.getAttribute("loginH");
        int id = Integer.parseInt(m.get("id").toString());
        return managerEnrollEvent.findByPersonEventId(p.getPersonId(), id);
    }
}
