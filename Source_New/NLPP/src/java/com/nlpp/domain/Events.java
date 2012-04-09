package com.nlpp.domain;
// Generated Apr 9, 2012 11:01:15 PM by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Events generated by hbm2java
 */
public class Events  implements java.io.Serializable {


     private int eventId;
     private EventTypes eventTypes;
     private String eventName;
     private Serializable topic;
     private Serializable speakers;
     private Serializable guests;
     private Serializable criteria;
     private Serializable termsAndConditions;
     private Serializable location;
     private Date conductingDate;
     private Double fees;
     private int numberOfParticipants;
     private Set eventPrizeses = new HashSet(0);
     private Set eventEnrollmentses = new HashSet(0);

    public Events() {
    }

	
    public Events(int eventId, EventTypes eventTypes, String eventName, Serializable topic, Serializable location, Date conductingDate, int numberOfParticipants) {
        this.eventId = eventId;
        this.eventTypes = eventTypes;
        this.eventName = eventName;
        this.topic = topic;
        this.location = location;
        this.conductingDate = conductingDate;
        this.numberOfParticipants = numberOfParticipants;
    }
    public Events(int eventId, EventTypes eventTypes, String eventName, Serializable topic, Serializable speakers, Serializable guests, Serializable criteria, Serializable termsAndConditions, Serializable location, Date conductingDate, Double fees, int numberOfParticipants, Set eventPrizeses, Set eventEnrollmentses) {
       this.eventId = eventId;
       this.eventTypes = eventTypes;
       this.eventName = eventName;
       this.topic = topic;
       this.speakers = speakers;
       this.guests = guests;
       this.criteria = criteria;
       this.termsAndConditions = termsAndConditions;
       this.location = location;
       this.conductingDate = conductingDate;
       this.fees = fees;
       this.numberOfParticipants = numberOfParticipants;
       this.eventPrizeses = eventPrizeses;
       this.eventEnrollmentses = eventEnrollmentses;
    }
   
    public int getEventId() {
        return this.eventId;
    }
    
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
    public EventTypes getEventTypes() {
        return this.eventTypes;
    }
    
    public void setEventTypes(EventTypes eventTypes) {
        this.eventTypes = eventTypes;
    }
    public String getEventName() {
        return this.eventName;
    }
    
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public Serializable getTopic() {
        return this.topic;
    }
    
    public void setTopic(Serializable topic) {
        this.topic = topic;
    }
    public Serializable getSpeakers() {
        return this.speakers;
    }
    
    public void setSpeakers(Serializable speakers) {
        this.speakers = speakers;
    }
    public Serializable getGuests() {
        return this.guests;
    }
    
    public void setGuests(Serializable guests) {
        this.guests = guests;
    }
    public Serializable getCriteria() {
        return this.criteria;
    }
    
    public void setCriteria(Serializable criteria) {
        this.criteria = criteria;
    }
    public Serializable getTermsAndConditions() {
        return this.termsAndConditions;
    }
    
    public void setTermsAndConditions(Serializable termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }
    public Serializable getLocation() {
        return this.location;
    }
    
    public void setLocation(Serializable location) {
        this.location = location;
    }
    public Date getConductingDate() {
        return this.conductingDate;
    }
    
    public void setConductingDate(Date conductingDate) {
        this.conductingDate = conductingDate;
    }
    public Double getFees() {
        return this.fees;
    }
    
    public void setFees(Double fees) {
        this.fees = fees;
    }
    public int getNumberOfParticipants() {
        return this.numberOfParticipants;
    }
    
    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }
    public Set getEventPrizeses() {
        return this.eventPrizeses;
    }
    
    public void setEventPrizeses(Set eventPrizeses) {
        this.eventPrizeses = eventPrizeses;
    }
    public Set getEventEnrollmentses() {
        return this.eventEnrollmentses;
    }
    
    public void setEventEnrollmentses(Set eventEnrollmentses) {
        this.eventEnrollmentses = eventEnrollmentses;
    }




}


