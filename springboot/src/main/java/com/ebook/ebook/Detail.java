package com.ebook.ebook;

public class Detail{
    String name,cname,writer,isbn,img;
    Integer storage,sales;
    float price;
    String book_intro,writer_intro,book_comment;
    public void Detail(){
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
    public String getImg(){
        return img;
    }
    public float getPrice(){
        return price;
    }
    public Integer getStorage(){
        return storage;
    }
    public Integer getSales(){
        return sales;
    }
    public String getBook_intro(){
        return book_intro;
    }
    public String getWriter_intro(){
        return writer_intro;
    }
    public String getBook_comment(){
        return book_comment;
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
    public void setImg(String img){
        this.img = img;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public void setStorage(Integer storage){
        this.storage = storage;
    }
    public void setSales(Integer sales){
        this.sales =sales;
    }
    public void setBook_intro(String book_intro){
        this.book_intro = book_intro;
    }
    public void setWriter_intro(String writer_intro){
        this.writer_intro = writer_intro;
    }
    public void setBook_omment(String comment){
        this.book_comment = comment;
    }
}