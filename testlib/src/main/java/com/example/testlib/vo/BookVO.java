package com.example.testlib.vo;

public class BookVO {
    private Integer num;
    private Integer id;
    private String title;
    private String author;
    private String publisher;
    private String publishtime;
    private String descri;

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescri() {
        return descri;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
