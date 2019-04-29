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
    @JoinColumn(name="uid")
    @ManyToOne(fetch=FetchType.LAZY, targetEntity = User.class, cascade = CascadeType.MERGE)
    private User user;
    private Timestamp date;
    private Boolean isdelete;

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

}