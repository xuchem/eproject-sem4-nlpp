/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlpp.helper;

import org.hibernate.Hibernate;

/**
 *
 * @author SVN - Team
 */
public class HibernateUtils {

    public static void initialize(Object proxy) {
        if (!Hibernate.isInitialized(proxy)) {
            Hibernate.initialize(proxy);
        }
    }
}
