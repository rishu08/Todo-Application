package com.example.rishabh.todoapp;

public class Task {

    Long id;
    String title;
    String description;
    int completed;


    public Task(Long id, String title, String description, int completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCompleted() {
        return completed;
    }
}
