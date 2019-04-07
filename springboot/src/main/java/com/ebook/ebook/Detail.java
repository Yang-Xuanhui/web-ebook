package com.ebook.ebook;

public class Detail{
    String name,cname,writer,isbn;
    Integer storage;
    double price;
    String detail,writer_intro,comment;
    public void Detail(){
        this.name = "000";
        this.detail="123";
        this.writer_intro="234";
        this.comment = "345";
    }
    public String getName(){
        return name;
    }
    public String getCname(){
        return cname;
    }
    public String getWriter(){
        return writer;
    }
    public String getIsbn(){
        return isbn;
    }
    public Integer getStorage(){
        return storage;
    }
    public double getPrice(){
        return price;
    }
    public String getDetail(){
        return detail;
    }
    public String getWriter_intro(){
        return writer_intro;
    }
    public String getComment(){
        return comment;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setCname(String cname){
        this.cname = cname;
    }
    public void setWriter(String writer){
        this.writer = writer;
    }
    public void setIsbn(String isbn){
        this.isbn = isbn;
    }
    public void setStorage(Integer storage){
        this.storage = storage;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setDetail(String detail){
        this.detail = detail;
    }
    public void setWriter_intro(String writer_intro){
        this.writer_intro = writer_intro;
    }
    public void setComment(String comment){
        this.comment = comment;
    }
}