package com.ebook.entity;

import javax.persistence.*;


@Table(name="orderitems")
@Entity
public class OrderItem{
    @Id
    public Integer id;
    @JoinColumn(name="oid")
    @ManyToOne(fetch=FetchType.LAZY, targetEntity = Order.class, cascade = CascadeType.MERGE)
    public Integer oid;
    public Integer bid;
    public Integer number;
    public Double price;

    public void OrderItem(){}

    public Integer getId(){
        return id;
    }
    public Integer getOid(){
        return oid;
    }
    public  Integer getBid(){
        return bid;
    }
    public  Integer getnumber(){
        return number;
    }
    public Double getPrice(){
        return price;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public void setOid(Integer id){
        this.oid = id;
    }
    public void setBid(Integer id){
        this.bid = id;
    }
    public void setNumber(Integer number){
        this.number = number;
    }
    public void setPrice(Double price){
        this.price = price;
    }
}