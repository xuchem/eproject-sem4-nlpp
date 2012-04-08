/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nlpp.view;

import com.nlpp.bo.CategoryBO;
import com.nlpp.bo.EmailRecieverEventCategoryBO;
import com.nlpp.domain.Category;
import com.nlpp.domain.EmailRecieverEventCategory;
import com.nlpp.domain.Person;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


/**
 *
 * @author SVN - Team
 */
public class EmailRecieverEventCategoryManager {

    /** Creates a new instance of EmailRecieverEventCategoryManager */
    public EmailRecieverEventCategoryManager() {
    }
    private EmailRecieverEventCategoryBO managerEmailReciever;

    private CategoryBO mCategory;

    private List<String> aListCategorySelect;

    public List<String> getaListCategorySelect() {
        return aListCategorySelect;
    }

    public void setaListCategorySelect(List<String> aListCategorySelect) {
        this.aListCategorySelect = aListCategorySelect;
    }

    public EmailRecieverEventCategoryBO getManagerEmailReciever() {
        return managerEmailReciever;
    }

    public void setManagerEmailReciever(EmailRecieverEventCategoryBO managerEmailReciever) {
        this.managerEmailReciever = managerEmailReciever;
    }

    public CategoryBO getmCategory() {
        return mCategory;
    }

    public void setmCategory(CategoryBO mCategory) {
        this.mCategory = mCategory;
    }
    private Map<String,String> aListCategory;
    
    public Map<String,String> getAllCategory() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        Person p = (Person) session.getAttribute("loginH");
        List<Category> aListCat = mCategory.findAll();
        aListCategory = new HashMap<String, String>();
        for (Iterator<Category> it = aListCat.iterator(); it.hasNext();) {
            Category c = it.next();
            aListCategory.put(c.getCatName(), String.valueOf(c.getCatId()));
        }
        List<EmailRecieverEventCategory> aListEmailR = managerEmailReciever.findByPersonId(p.getPersonId());
        aListCategorySelect = new ArrayList<String>();
        for (int i = 0; i < aListEmailR.size(); i++) {
            EmailRecieverEventCategory emailRecieverEventCategory = aListEmailR.get(i);
            aListCategorySelect.add(String.valueOf(emailRecieverEventCategory.getCategory().getCatId()));
        }
        System.out.println("Size:" + aListCategory.size());
        return aListCategory;
    }
    
    public String saveEmailReciever() {
        List<EmailRecieverEventCategory> aListEmailR = new ArrayList<EmailRecieverEventCategory>();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        Person p = (Person) session.getAttribute("loginH");
        for (int i= 0; i < aListCategorySelect.size(); i++) {
            EmailRecieverEventCategory reciever = new EmailRecieverEventCategory();
            reciever.setPerson(p);
            Category c = new Category();
            c.setCatId(Integer.parseInt(aListCategorySelect.get(i).toString()));
            reciever.setCategory(c);
            aListEmailR.add(reciever);
        }
        boolean flag = managerEmailReciever.saveEmailReciver(aListEmailR);
        if (flag) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Sample info message", "PrimeFaces rocks!"));
            System.out.println("Successfull!");
        } else {
            System.out.println("Fail!");
        }
        return null;
    }
    
}
