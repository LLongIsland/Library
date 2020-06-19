package com.example.testlib.po;

public class User {
    private Integer id;
    private String email;
    private String password;
    private String name;
    private double credit;
    private Integer condi;

    public double getCredit() {
        return credit;
    }

    public Integer getCondi() {
        return condi;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setCondi(Integer condi) {
        this.condi = condi;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
