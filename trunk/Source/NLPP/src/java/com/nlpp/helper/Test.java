/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nlpp.helper;

import com.nlpp.dao.CategoryDAO;
import com.nlpp.domain.Category;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author SVN - Team
 */
public class Test {
    public static void main(String args[]) {
        /*EmailHelper eh = new EmailHelper();
        List to = new ArrayList();
        to.add("vinhnv@gameso.vn");
        String subject = "DEMO SEND MAIL";
        String contents = "Nhan duoc chua???";
        boolean isCheck = eh.sendMail(subject, to, contents);
        if (isCheck) {
            System.out.println("Send mail Successfull!");
        } else {
            System.out.println("Send mail Error, Please try again...");
        }*/
        /*EncryptHelper eh = new EncryptHelper();
        System.out.println("Encoding:" + eh.encriptMd5Password("admin"));
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            CategoryDAO catDAO = (CategoryDAO) context.getBean("categoryDAO");
            List<Category> aList = catDAO.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        List aList = new ArrayList();
        aList.add(1);
        List aL = aList.subList(0, 10);
        for (int i= 0; i < aL.size(); i++) {
            System.out.println("element:" + aL.get(i));
        }
    }
}
