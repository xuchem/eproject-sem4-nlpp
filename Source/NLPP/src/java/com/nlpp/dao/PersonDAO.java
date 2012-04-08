/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nlpp.dao;

import com.nlpp.domain.Person;
import com.nlpp.domain.PersonChart;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author SVN - Team
 */
public class PersonDAO extends HibernateDaoSupport implements IObjectDAO<Person>{
    private boolean  flag = true;
    public List<Person> findAll() {
        return getHibernateTemplate().find("from Person where Visiable = 0");
    }

    public List<Person> findAllByRoles(int roles) {
        return getHibernateTemplate().find("from Person where Roles = ? and Visiable = 0", roles);
    }

    public List<Person> findAllEmployee() {
        return getHibernateTemplate().find("from Person where Roles <> 0 and Visiable = 0");
    }

    public Person findById(int id) {
        return getHibernateTemplate().get(Person.class, id);
    }

    public List<Person> findByName(String name) {
        return getHibernateTemplate().find("from Person where PersonName like ? and Visiable = 0","%" + name + "%");
    }
    public Person findByEmail(String email) {
        List<Person> lPerson = getHibernateTemplate().find("from Person where Email = ? and Visiable = 0",email);
        Person p = null;
        if (lPerson.size() > 0) {
            p = new Person();
            p = lPerson.get(0);
        }
        return p;
    }
    
    public Person checkLogin(String email, String password) {
        List<Person> lPerson = getHibernateTemplate().find("from Person where Email = ? and Password = ? and Visiable = 0", email, password);
        System.out.println("LPerson:" + lPerson.size());
        Person p = null;
        if (lPerson.size() >= 1) {
            p = lPerson.get(0);
        }
        return p;
    }

    public boolean saveObject(Person t) {
        try {
            getHibernateTemplate().saveOrUpdate(t);
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public Person savePersons(Person p) {
        Person p1 = new Person();
        try {
            getHibernateTemplate().saveOrUpdate(p);
            p1 = p;
        } catch (Exception e) {
            return p;
        }
        return p;
    }

    public boolean removeObject(int id) {
        try {
            Person p = getHibernateTemplate().get(Person.class, id);
            if(p.getEnrollEvents().size() <= 0 && p.getComments().size() <=0) {
                getHibernateTemplate().delete(p);
            } else {
                p.setVisiable(1);
                getHibernateTemplate().update(p);
            }
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public boolean updateObject(Person t) {
        try {
            Person p = getHibernateTemplate().get(Person.class, t.getPersonId());
            p.setPersonName(t.getPersonName());
            p.setPassword(t.getPassword());
            p.setPhone(t.getPhone());
            p.setBirthday(t.getBirthday());
            p.setAddress(t.getAddress());
            p.setGender(t.getGender());
            getHibernateTemplate().update(p);
            return true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public Person saveInformationPerson(Person p) {
        try {
            Person per = getHibernateTemplate().get(Person.class, p.getPersonId());
            per.setPersonName(p.getPersonName());
            per.setPhone(p.getPhone());
            per.setGender(p.getGender());
            per.setBirthday(p.getBirthday());
            per.setBirthday(p.getBirthday());
            getHibernateTemplate().update(per);
            return per;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean saveChangePassword(Person p) {
        try {
            Person per = getHibernateTemplate().get(Person.class, p.getPersonId());
            per.setPassword(p.getPassword());
            getHibernateTemplate().update(per);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int count() {
        return getHibernateTemplate().find("from Person").size();
    }

    public boolean updateAvatar(Person p) {
        try {
            Person per = getHibernateTemplate().get(Person.class, p.getPersonId());
            per.setImages(p.getImages());
            getHibernateTemplate().update(per);
            return true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public List<PersonChart> getAllPersonChart() {
        List<PersonChart> aListChart = new ArrayList<PersonChart>();
        List<Person> aListPerson = getHibernateTemplate().find("from Person where Visiable = 0");
        if(aListPerson.size() >= 1) {
            for (int i= 1; i <= 12; i++) {
                int count = 0;
                for (int j= 0; j < aListPerson.size(); j++) {
                    Person p = aListPerson.get(j);
                    if (p.getDateCreate().getMonth() == i) {
                        count++;
                    }
                }
                System.out.println("Count: " + count);
                PersonChart pc = new PersonChart();
                pc.setMonth(i);
                pc.setTotal(count);
                aListChart.add(pc);
            }
        }
        return aListChart;
    }
}
