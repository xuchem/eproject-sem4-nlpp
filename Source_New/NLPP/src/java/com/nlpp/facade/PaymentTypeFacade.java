/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlpp.facade;

import com.nlpp.entity.PaymentType;

/**
 *
 * @author Hung
 */
public class PaymentTypeFacade extends BaseFacade<PaymentType, Integer> {

    public PaymentTypeFacade() {
        super(PaymentType.class);
    }
}
