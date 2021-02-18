package com.example.querydsljpademo.model;

import javax.persistence.Entity;
import java.io.Serializable;

public class Response implements Serializable {

    private String name;
    private String title;

    public Response(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public Response() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Response{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
