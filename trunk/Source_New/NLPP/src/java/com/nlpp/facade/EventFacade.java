/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlpp.facade;

import com.nlpp.entity.Events;

/**
 *
 * @author Hung
 */
public class EventFacade extends BaseFacade<Events, Integer> {

    public EventFacade() {
        super(Events.class);
    }
}
