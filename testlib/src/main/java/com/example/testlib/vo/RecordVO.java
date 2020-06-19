package com.example.testlib.vo;

public class RecordVO {
    private Integer id;
    private Integer aid;
    private String raccount;
    private String borrowTime;
    private String returnTime;
    private String limitTime;
    private Integer condi;//0表示未归还

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCondi(Integer condi) {
        this.condi = condi;
    }

    public Integer getCondi() {
        return condi;
    }

    public Integer getAid() {
        return aid;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public String getRaccount() {
        return raccount;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getLimitTime(){return limitTime;}

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }

    public void setRaccount(String raccount) {
        this.raccount = raccount;
    }

    public void setLimitTime(String limitTime) {this.limitTime=limitTime;}

}
