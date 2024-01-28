package com.code.empcrud.empcrud.model;

public class User {

    private long id;
    private String name;
    private String email;
    private String country;

    public User(String name, String email, String country){
        this.name  = name;
        this.email = email;
        this.country = country;
    }

    public User(long id, String name, String email, String country){
        this.id = id;
        this.name  = name;
        this.email = email;
        this.country = country;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
