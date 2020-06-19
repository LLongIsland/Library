package com.example.testlib.po;

public class Record {
    private Integer id;
    private Integer aid;
    private String raccount;
    private String borrowTime;
    private String returnTime;
    private Integer condi;

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

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }

    public void setRaccount(String raccount) {
        this.raccount = raccount;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }
}
