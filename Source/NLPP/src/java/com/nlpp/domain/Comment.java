package com.nlpp.domain;
// Generated Sep 5, 2011 11:57:48 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Comment generated by hbm2java
 */
public class Comment  implements java.io.Serializable {


     private int commentId;
     private Events events;
     private Person person;
     private Date dateComment;
     private String contents;
     private int visiable;

    public Comment() {
    }

	
    public Comment(int commentId, Date dateComment, String contents, int visiable) {
        this.commentId = commentId;
        this.dateComment = dateComment;
        this.contents = contents;
        this.visiable = visiable;
    }
    public Comment(int commentId, Events events, Person person, Date dateComment, String contents, int visiable) {
       this.commentId = commentId;
       this.events = events;
       this.person = person;
       this.dateComment = dateComment;
       this.contents = contents;
       this.visiable = visiable;
    }
   
    public int getCommentId() {
        return this.commentId;
    }
    
    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
    public Events getEvents() {
        return this.events;
    }
    
    public void setEvents(Events events) {
        this.events = events;
    }
    public Person getPerson() {
        return this.person;
    }
    
    public void setPerson(Person person) {
        this.person = person;
    }
    public Date getDateComment() {
        return this.dateComment;
    }
    
    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
    }
    public String getContents() {
        return this.contents;
    }
    
    public void setContents(String contents) {
        this.contents = contents;
    }
    public int getVisiable() {
        return this.visiable;
    }
    
    public void setVisiable(int visiable) {
        this.visiable = visiable;
    }




}


