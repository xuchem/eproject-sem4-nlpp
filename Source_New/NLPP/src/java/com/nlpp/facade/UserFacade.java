/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlpp.facade;

import com.nlpp.entity.Users;

/**
 *
 * @author Hung
 */
public class UserFacade extends BaseFacade<Users, String> {

    public UserFacade() {
        super(Users.class);
    }
}
