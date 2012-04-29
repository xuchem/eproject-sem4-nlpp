/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlpp.helper;

import java.util.ArrayList;
import java.util.List;
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
public class EmailHelperTest {

    public EmailHelperTest() {
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
     * Test of sendMail method, of class EmailHelper.
     */
    @Test
    public void testSendMail() {
        System.out.println("sendMail");
        String subject = "DEMO";
        List to = new ArrayList();
        to.add("aaabb@gameso.com");
        String contents = "Successfull";
        EmailHelper instance = new EmailHelper();
        boolean expResult = true;
        boolean result = instance.sendMail(subject, to, contents, null);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}