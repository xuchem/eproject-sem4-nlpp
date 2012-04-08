/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlpp.helper;

import com.nlpp.dao.CategoryDAO;
import com.nlpp.domain.Category;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author SVN - Team
 */
public class Main {
    
    public static void main(String args[]) {
       /* try {
            ApplicationContext context = new ClassPathXmlApplicationContext("/web/WEB-INF/WEB-INF/applicationContext.xml");
            CategoryDAO catDAO = (CategoryDAO) context.getBean("categoryDAO");
            List<Category> aList = catDAO.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        System.out.println("Errorxx");
    }
}
