package com.ebook.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Table(name="orders")
@Entity
public class Order{
    @Id
    @Column(name="oid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer oid;

    @ManyToOne(optional=false)
    @JoinColumn(name="uid")
    private User user;
    private Timestamp date;
    private Boolean isdelete;
    @OneToMany(mappedBy="order",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    private List<OrderItem> items;
    public void Order(){}



    public Integer getOid(){
        return oid;
    }
    public  User getUser(){
        return user;
    }
    public  Timestamp getDate(){
        return date;
    }
    public Boolean getIsdelete(){
        return isdelete;
    }

    public void setOid(Integer id){
        this.oid = id;
    }
    public void setUser(User user){
        this.user = user;
    }
    public void setDate(Timestamp date){
        this.date = date;
    }
    public void setIsdelete(Boolean isDelete){
        this.isdelete = isDelete;
    }


    public List<OrderItem> getItems(){
        return items;
    }
}