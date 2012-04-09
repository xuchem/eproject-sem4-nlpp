package com.nlpp.domain;
// Generated Apr 9, 2012 11:01:15 PM by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * EventTypes generated by hbm2java
 */
public class EventTypes  implements java.io.Serializable {


     private int eventTypeId;
     private Serializable eventTypeName;
     private Serializable description;
     private Set userses = new HashSet(0);
     private Set eventses = new HashSet(0);

    public EventTypes() {
    }

	
    public EventTypes(int eventTypeId, Serializable eventTypeName) {
        this.eventTypeId = eventTypeId;
        this.eventTypeName = eventTypeName;
    }
    public EventTypes(int eventTypeId, Serializable eventTypeName, Serializable description, Set userses, Set eventses) {
       this.eventTypeId = eventTypeId;
       this.eventTypeName = eventTypeName;
       this.description = description;
       this.userses = userses;
       this.eventses = eventses;
    }
   
    public int getEventTypeId() {
        return this.eventTypeId;
    }
    
    public void setEventTypeId(int eventTypeId) {
        this.eventTypeId = eventTypeId;
    }
    public Serializable getEventTypeName() {
        return this.eventTypeName;
    }
    
    public void setEventTypeName(Serializable eventTypeName) {
        this.eventTypeName = eventTypeName;
    }
    public Serializable getDescription() {
        return this.description;
    }
    
    public void setDescription(Serializable description) {
        this.description = description;
    }
    public Set getUserses() {
        return this.userses;
    }
    
    public void setUserses(Set userses) {
        this.userses = userses;
    }
    public Set getEventses() {
        return this.eventses;
    }
    
    public void setEventses(Set eventses) {
        this.eventses = eventses;
    }




}


