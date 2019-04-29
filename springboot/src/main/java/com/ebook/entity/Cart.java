package com.ebook.entity;

import javax.persistence.*;

@Table(name="carts")
@Entity
public class Cart{
    @Id
    private Integer cid;
    @JoinColumn(name="uid")
    @ManyToOne(fetch=FetchType.LAZY, targetEntity = User.class, cascade = CascadeType.MERGE)
    private User user;
    @JoinColumn(name="bid")
    @ManyToOne(fetch=FetchType.LAZY, targetEntity = Book.class, cascade = CascadeType.MERGE)
    private Book book;
    private Integer amount;

    public void Cart(){}

    public Integer getCid(){
        return cid;
    }
    public  User getUser(){
        return user;
    }
    public  Book getBook(){
        return book;
    }
    public Integer getAmount(){
        return amount;
    }

    public void setCid(Integer id){
        this.cid = id;
    }
    public void setUser(User user){
        this.user = user;
    }
    public void setBook(Book book){
        this.book = book;
    }
    public void setAmount(Integer amount){
        this.amount = amount;
    }
}