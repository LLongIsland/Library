package com.example.testlib.vo;

public class UserVO {
    private Integer id;
    private String email;
    private String password;
    private String name;
    private String account;
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

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
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
