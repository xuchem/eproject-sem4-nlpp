/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlpp.helper;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author SVN - Team
 */
public class EmailHelper {

    public EmailHelper() {
    }

    public boolean sendMail(String subject, List to, String contents, ServletContext sc) {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
        java.util.Properties prop = context.getBean("applicationProperties", java.util.Properties.class);
        String userName = prop.getProperty("email.id");
        String passWord = prop.getProperty("email.password");
        System.out.println("  emailid  " + userName + "  password  " + passWord);
        String host = "smtp.gmail.com";
        String port = "465";
        String starttls = "true";
        String auth = "true";
        boolean debug = false;
        String socketFactoryClass = "javax.net.ssl.SSLSocketFactory";
        String fallback = "false";
        Properties props = new Properties();
        props.put("mail.smtp.user", userName);
        props.put("mail.smtp.host", host);
        if (!"".equals(port)) {
            props.put("mail.smtp.port", port);
        }
        if (!"".equals(starttls)) {
            props.put("mail.smtp.starttls.enable", starttls);
        }
        props.put("mail.smtp.auth", auth);
        if (debug) {
            props.put("mail.smtp.debug", "true");
        } else {
            props.put("mail.smtp.debug", "false");
        }
        if (!"".equals(port)) {
            props.put("mail.smtp.socketFactory.port", port);
        }
        if (!"".equals(socketFactoryClass)) {
            props.put("mail.smtp.socketFactory.class", socketFactoryClass);
        }
        if (!"".equals(fallback)) {
            props.put("mail.smtp.socketFactory.fallback", fallback);
        }
        try {
            Session session = Session.getDefaultInstance(props, null);
            session.setDebug(debug);
            MimeMessage msg = new MimeMessage(session);
            msg.setContent(contents, "text/html; charset=ISO-8859-1");
            msg.setSubject(subject);
            msg.setFrom(new InternetAddress("p_sambasivarao@sutyam.com"));
            for (int i = 0; i < to.size(); i++) {
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to.get(i).toString()));
            }
            msg.saveChanges();
            Transport transport = session.getTransport("smtp");
            transport.connect(host, userName, passWord);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            return true;
        } catch (Exception mex) {
            mex.printStackTrace();
            return false;
        }

    }
}
