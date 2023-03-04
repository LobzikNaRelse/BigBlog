package com.ObuchenieSpring.blog.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class About
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String full_about;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getFull_about() {
        return full_about;
    }

    public void setFull_about(String full_about) {
        this.full_about = full_about;
    }

    public About() { }

    public About(String full_about)
    {
        this.full_about = full_about;
    }
}
