package com.ObuchenieSpring.blog.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String full_task;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getFull_task() {
        return full_task;
    }

    public void setFull_task(String full_task) {
        this.full_task = full_task;
    }

    public Task() { }

    public Task(String full_task)
    {
        this.full_task = full_task;
    }
}
