/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlpp.facade;

import com.nlpp.entity.EventTypes;

/**
 *
 * @author Hung
 */
public class EventTypeFacade extends BaseFacade<EventTypes, Integer> {

    public EventTypeFacade() {
        super(EventTypes.class);
    }
}
