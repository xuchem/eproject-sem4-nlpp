package com.nlpp.domain;
// Generated Sep 5, 2011 11:57:48 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Person generated by hbm2java
 */
public class Person  implements java.io.Serializable {


     private int personId;
     private String personName;
     private String email;
     private String password;
     private String phone;
     private String gender;
     private String images;
     private Date birthday;
     private String address;
     private Date dateCreate;
     private int roles;
     private int visiable;
     private Set<EnrollEvent> enrollEvents = new HashSet<EnrollEvent>(0);
     private Set<Comment> comments = new HashSet<Comment>(0);
     private Set<EmailRecieverEventCategory> emailRecieverEventCategories = new HashSet<EmailRecieverEventCategory>(0);

    public Person() {
    }

	
    public Person(int personId, String personName, String email, String password, String gender, Date birthday, String address, Date dateCreate, int roles, int visiable) {
        this.personId = personId;
        this.personName = personName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.dateCreate = dateCreate;
        this.roles = roles;
        this.visiable = visiable;
    }

    public Person(int personId, String personName, String email, String password, String phone, String gender, String images, Date birthday, String address, Date dateCreate, int roles, int visiable) {
        this.personId = personId;
        this.personName = personName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.images = images;
        this.birthday = birthday;
        this.address = address;
        this.dateCreate = dateCreate;
        this.roles = roles;
        this.visiable = visiable;
    }
    
   
    public int getPersonId() {
        return this.personId;
    }
    
    public void setPersonId(int personId) {
        this.personId = personId;
    }
    public String getPersonName() {
        return this.personName;
    }
    
    public void setPersonName(String personName) {
        this.personName = personName;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getGender() {
        return this.gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
    
    public Date getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    public Date getDateCreate() {
        return this.dateCreate;
    }
    
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }
    public int getRoles() {
        return this.roles;
    }
    
    public void setRoles(int roles) {
        this.roles = roles;
    }
    public int getVisiable() {
        return this.visiable;
    }
    
    public void setVisiable(int visiable) {
        this.visiable = visiable;
    }
    public Set<EnrollEvent> getEnrollEvents() {
        return this.enrollEvents;
    }
    
    public void setEnrollEvents(Set<EnrollEvent> enrollEvents) {
        this.enrollEvents = enrollEvents;
    }
    public Set<Comment> getComments() {
        return this.comments;
    }
    
    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
    public Set<EmailRecieverEventCategory> getEmailRecieverEventCategories() {
        return this.emailRecieverEventCategories;
    }
    
    public void setEmailRecieverEventCategories(Set<EmailRecieverEventCategory> emailRecieverEventCategories) {
        this.emailRecieverEventCategories = emailRecieverEventCategories;
    }




}


