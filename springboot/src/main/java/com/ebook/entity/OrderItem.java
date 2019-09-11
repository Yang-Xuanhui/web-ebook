package com.ebook.entity;

import javax.persistence.*;


@Table(name="orderitems")
@Entity
public class OrderItem{
    @Id
    private Integer id;
    @JoinColumn(name="oid")
    @ManyToOne(optional = false)
    private Order order;
    @JoinColumn(name="bid")
    @ManyToOne(targetEntity = Book.class, optional = false)
    private Book book;
    private Integer amount;
    private Double price;

    public void OrderItem(){}

    public Integer getId(){
        return id;
    }
    public Order getOrder(){
        return order;
    }
    public  Book getBook(){
        return book;
    }
    public  Integer getAmount(){
        return amount;
    }
    public Double getPrice(){
        return price;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public void setOrder(Order order){
        this.order = order;
    }
    public void setBook(Book book){
        this.book = book;
    }
    public void setAmount(Integer amount){
        this.amount = amount;
    }
    public void setPrice(Double price){
        this.price = price;
    }
}