/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nlpp.dao;

import com.nlpp.domain.Category;
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
public class CategoryDAOTest {

    public CategoryDAOTest() {
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
     * Test of findAll method, of class CategoryDAO.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        CategoryDAO instance = new CategoryDAO();
        List expResult = null;
        List result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class CategoryDAO.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        int id = 0;
        CategoryDAO instance = new CategoryDAO();
        Category expResult = null;
        Category result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByName method, of class CategoryDAO.
     */
    @Test
    public void testFindByName() {
        System.out.println("findByName");
        String name = "";
        CategoryDAO instance = new CategoryDAO();
        List expResult = null;
        List result = instance.findByName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveObject method, of class CategoryDAO.
     */
    @Test
    public void testSaveObject() {
        System.out.println("saveObject");
        Category t = null;
        CategoryDAO instance = new CategoryDAO();
        boolean expResult = false;
        boolean result = instance.saveObject(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeObject method, of class CategoryDAO.
     */
    @Test
    public void testRemoveObject() {
        System.out.println("removeObject");
        int id = 0;
        CategoryDAO instance = new CategoryDAO();
        boolean expResult = false;
        boolean result = instance.removeObject(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateObject method, of class CategoryDAO.
     */
    @Test
    public void testUpdateObject() {
        System.out.println("updateObject");
        Category t = null;
        CategoryDAO instance = new CategoryDAO();
        boolean expResult = false;
        boolean result = instance.updateObject(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of count method, of class CategoryDAO.
     */
    @Test
    public void testCount() {
        System.out.println("count");
        CategoryDAO instance = new CategoryDAO();
        int expResult = 0;
        int result = instance.count();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}