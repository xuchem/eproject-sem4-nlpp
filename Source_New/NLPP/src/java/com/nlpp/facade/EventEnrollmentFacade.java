/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlpp.facade;

import com.nlpp.entity.EventEnrollments;
import com.nlpp.entity.EventEnrollmentsId;

/**
 *
 * @author Hung
 */
public class EventEnrollmentFacade extends BaseFacade<EventEnrollments, EventEnrollmentsId> {

    public EventEnrollmentFacade() {
        super(EventEnrollments.class);
    }
}
