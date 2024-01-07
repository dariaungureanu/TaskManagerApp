package com.example.taskmanagerapp;

import com.example.taskmanagerapp.utils.DatabaseConnection;

import java.sql.*;
import java.util.List;


public class Task {
    private int taskId;
    private String title;
    private String description;
    private Date dueDate;
    private String timeSpent;
    private String priority;
    private String tagName;
    private String commentText;

    public Task(String title, String description, Date dueDate,  String priority, String timeSpent, String tag, String comment) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.timeSpent = timeSpent;
        this.priority = priority;
        this.tagName=tag;
        this.commentText=comment;
    }


    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(String timeSpent) {
        this.timeSpent = timeSpent;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
