package com.ebook.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="books")
@Entity
public class Book {
    @Id
    Integer bid;
    String name,cname,writer,brief,isbn,img;
    Integer storage,sales;
    Double price,rate;
    String book_intro,writer_intro,book_comment;
    Boolean isDelete;
    public void Book(){
    }
    public Integer getBid(){
        return bid;
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
    public String getBrief(){
        return brief;
    }
    public String getIsbn(){
        return isbn;
    }
    public String getImg(){
        return img;
    }
    public Double getPrice(){
        return price;
    }
    public Double getRate(){
        return rate;
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
    public Boolean getIsDelete(){
        return isDelete;
    }

    public void setBid(Integer id){
        this.bid = id;
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
    public void setBrief(String brief){
        this.brief = brief;
    }
    public void setIsbn(String isbn){
        this.isbn = isbn;
    }
    public void setImg(String img){
        this.img = img;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setRate(Double rate){
        this.rate = rate;
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
    public void setBook_comment(String comment){
        this.book_comment = comment;
    }
    public void setIsDelete(Boolean isDelete){
        this.isDelete = isDelete;
    }
}