package com.example.testlib.po;

public class Reader {
    private Integer id;
    private String account;//学号
    private String name;
    private Integer sex;
    private Integer condi;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getSex() {
        return sex;
    }

    public Integer getCondi() {
        return condi;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setCondi(Integer condi) {
        this.condi = condi;
    }
}
