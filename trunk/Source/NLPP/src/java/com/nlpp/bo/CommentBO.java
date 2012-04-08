/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nlpp.bo;

import com.nlpp.dao.CommentDAO;
import com.nlpp.domain.Comment;
import java.util.List;

/**
 *
 * @author SVN - Team
 */
public class CommentBO {
    private CommentDAO commentDAO;

    public void setCommentDAO(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }
    public List<Comment> findAll(int id) {
        return commentDAO.findAll(id);
    }
    public boolean removeComment(int id) {
        return commentDAO.removeObject(id);
    }
    public boolean saveComment(Comment c) {
        return commentDAO.saveObject(c);
    }
    public Comment findById(int id) {
        return commentDAO.findById(id);
    }

    public int getCountComment(int evtId) {
        return commentDAO.count(evtId);
    }
}
