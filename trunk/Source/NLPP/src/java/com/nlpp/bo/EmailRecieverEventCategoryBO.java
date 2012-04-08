/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nlpp.bo;

import com.nlpp.dao.EmailRecieverEventCategoryDAO;
import com.nlpp.domain.EmailRecieverEventCategory;
import java.util.List;

/**
 *
 * @author SVN - Team
 */
public class EmailRecieverEventCategoryBO {
    private EmailRecieverEventCategoryDAO emailRecieverEventCategoryDAO;

    public void setEmailRecieverEventCategoryDAO(EmailRecieverEventCategoryDAO emailRecieverEventCategoryDAO) {
        this.emailRecieverEventCategoryDAO = emailRecieverEventCategoryDAO;
    }

    public boolean saveEmailRecieverEventCategory(EmailRecieverEventCategory evt) {
        return emailRecieverEventCategoryDAO.saveObject(evt);
    }
    public boolean removeEmailRecieverEventCategory(int id) {
        return emailRecieverEventCategoryDAO.removeObject(id);
    }

    public boolean  saveEmailReciver(List<EmailRecieverEventCategory> aList) {
        return emailRecieverEventCategoryDAO.saveEmailReciver(aList);
    }

    public List<EmailRecieverEventCategory> findByPersonId(int  perId) {
        return emailRecieverEventCategoryDAO.findByPersonId(perId);
    }

    public List<String> findEmailByCategoryId(int catId) {
        return emailRecieverEventCategoryDAO.findEmailByCategoryId(catId);
    }
}
