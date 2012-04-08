/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlpp.bo;

import com.nlpp.dao.PersonDAO;
import com.nlpp.domain.Person;
import com.nlpp.domain.PersonChart;
import java.util.List;

/**
 *
 * @author SVN - Team
 */
public class PersonBO {

    private PersonDAO personDAO;

    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public List<Person> findAll() {
        return personDAO.findAll();
    }

    public List<Person> findAllByRoles(int roles) {
        return personDAO.findAllByRoles(roles);
    }

    public List<Person> findAllEmployee() {
        return personDAO.findAllEmployee();
    }

    public Person checkLogin(String email, String password) {
        return personDAO.checkLogin(email, password);
    }

    public boolean savePerson(Person p) {
        return personDAO.saveObject(p);
    }

    public Person savePersons(Person p) {
        return personDAO.savePersons(p);
    }

    public boolean updatePerson(Person p) {
        return personDAO.updateObject(p);
    }

    public boolean removePerson(int id) {
        return personDAO.removeObject(id);
    }

    public Person findById(int id) {
        return personDAO.findById(id);
    }

    public List<Person> findByName(String name) {
        return personDAO.findByName(name);
    }

    public Person findByEmail(String email) {
        return personDAO.findByEmail(email);
    }

    public boolean updateAvatar(Person p) {
        return personDAO.updateAvatar(p);
    }

    public Person saveInformationPerson(Person p) {
        return personDAO.saveInformationPerson(p);
    }

    public boolean saveChangePassword(Person p) {
        return personDAO.saveChangePassword(p);
    }
     public List<PersonChart> getAllPersonChart() {
         return personDAO.getAllPersonChart();
     }
}
