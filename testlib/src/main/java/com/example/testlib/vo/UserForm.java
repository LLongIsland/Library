package com.example.testlib.vo;

public class UserForm {
    /**
     * 用户，不可重复
     */
    private String email;
    /**
     * 用户密码
     */
    private String password;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
