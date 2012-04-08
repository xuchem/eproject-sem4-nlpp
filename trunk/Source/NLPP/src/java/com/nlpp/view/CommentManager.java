/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlpp.view;

import com.nlpp.bo.CommentBO;
import com.nlpp.domain.Comment;
import com.nlpp.domain.Events;
import com.nlpp.domain.Person;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SVN - Team
 */
public class CommentManager extends Comment {

    /** Creates a new instance of CommentManager */
    public CommentManager() {
    }
    private CommentBO managerComment;

    public CommentBO getManagerComment() {
        return managerComment;
    }

    public void setManagerComment(CommentBO managerComment) {
        this.managerComment = managerComment;
    }

    public List<Comment> getAllComment() {
        Map m = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int id = Integer.parseInt(m.get("id").toString());
        return managerComment.findAll(id);
    }

    public String saveComment() {
        try {
            Map m = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            int id = Integer.parseInt(m.get("id").toString());
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            Person p = (Person) session.getAttribute("loginH");
            Comment c = new Comment();
            Events e = new Events();
            e.setEventId(id);
            c.setEvents(e);
            Date now = new Date();
            c.setPerson(p);
            c.setDateComment(now);
            c.setContents(this.getContents());
            c.setVisiable(0);
            boolean flag = managerComment.saveComment(c);
            if(flag) {
                this.setContents("");
            }
        } catch (Exception ex) {
            System.out.println("Error: ex" + ex.getMessage());
        }
        return null;
    }

    public String removeCommnet() {
        Map m = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int id = Integer.parseInt(m.get("id").toString());
        boolean flag = managerComment.removeComment(id);
        return null;
    }

    public int getCountComment() {
        Map m = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int id = Integer.parseInt(m.get("id").toString());
        return managerComment.getCountComment(id);
    }
}
