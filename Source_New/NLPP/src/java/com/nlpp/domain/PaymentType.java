package com.nlpp.domain;
// Generated Apr 9, 2012 11:01:15 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * PaymentType generated by hbm2java
 */
public class PaymentType  implements java.io.Serializable {


     private int paymentTypeId;
     private String paymentTypeName;
     private Set eventEnrollmentses = new HashSet(0);

    public PaymentType() {
    }

	
    public PaymentType(int paymentTypeId, String paymentTypeName) {
        this.paymentTypeId = paymentTypeId;
        this.paymentTypeName = paymentTypeName;
    }
    public PaymentType(int paymentTypeId, String paymentTypeName, Set eventEnrollmentses) {
       this.paymentTypeId = paymentTypeId;
       this.paymentTypeName = paymentTypeName;
       this.eventEnrollmentses = eventEnrollmentses;
    }
   
    public int getPaymentTypeId() {
        return this.paymentTypeId;
    }
    
    public void setPaymentTypeId(int paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }
    public String getPaymentTypeName() {
        return this.paymentTypeName;
    }
    
    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }
    public Set getEventEnrollmentses() {
        return this.eventEnrollmentses;
    }
    
    public void setEventEnrollmentses(Set eventEnrollmentses) {
        this.eventEnrollmentses = eventEnrollmentses;
    }




}

