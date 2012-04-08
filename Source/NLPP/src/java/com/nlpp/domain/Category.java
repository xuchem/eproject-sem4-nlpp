package com.nlpp.domain;
// Generated Sep 5, 2011 11:57:48 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Category generated by hbm2java
 */
public class Category  implements java.io.Serializable {


     private int catId;
     private String catName;
     private int visiable;
     private Set<Events> eventses = new HashSet<Events>(0);
     private Set<EmailRecieverEventCategory> emailRecieverEventCategories = new HashSet<EmailRecieverEventCategory>(0);

    public Category() {
    }

	
    public Category(int catId, String catName, int visiable) {
        this.catId = catId;
        this.catName = catName;
        this.visiable = visiable;
    }
    public Category(int catId, String catName, int visiable, Set<Events> eventses, Set<EmailRecieverEventCategory> emailRecieverEventCategories) {
       this.catId = catId;
       this.catName = catName;
       this.visiable = visiable;
       this.eventses = eventses;
       this.emailRecieverEventCategories = emailRecieverEventCategories;
    }
   
    public int getCatId() {
        return this.catId;
    }
    
    public void setCatId(int catId) {
        this.catId = catId;
    }
    public String getCatName() {
        return this.catName;
    }
    
    public void setCatName(String catName) {
        this.catName = catName;
    }
    public int getVisiable() {
        return this.visiable;
    }
    
    public void setVisiable(int visiable) {
        this.visiable = visiable;
    }
    public Set<Events> getEventses() {
        return this.eventses;
    }
    
    public void setEventses(Set<Events> eventses) {
        this.eventses = eventses;
    }
    public Set<EmailRecieverEventCategory> getEmailRecieverEventCategories() {
        return this.emailRecieverEventCategories;
    }
    
    public void setEmailRecieverEventCategories(Set<EmailRecieverEventCategory> emailRecieverEventCategories) {
        this.emailRecieverEventCategories = emailRecieverEventCategories;
    }




}


