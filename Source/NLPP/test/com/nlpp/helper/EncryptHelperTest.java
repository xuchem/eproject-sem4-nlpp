/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nlpp.helper;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SVN - Team
 */
public class EncryptHelperTest {

    public EncryptHelperTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of encriptMd5Password method, of class EncryptHelper.
     */
    @Test
    public void testEncriptMd5Password() {
        System.out.println("encriptMd5Password");
        String passwrord = "test";
        EncryptHelper instance = new EncryptHelper();
        String expResult = "098f6bcd4621d373cade4e832627b4f6";
        String result = instance.encriptMd5Password(passwrord);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}