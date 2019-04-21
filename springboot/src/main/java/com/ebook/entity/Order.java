package com.ebook.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Table(name="orders")
@Entity
public class Order{
    @Id
    public Integer oid;
    @JoinColumn(name="uid")
    @ManyToOne(fetch=FetchType.LAZY, targetEntity = User.class, cascade = CascadeType.MERGE)
    public Integer uid;
    public Date date;
    @OneToMany(mappedBy="id")
    private List<OrderItem> list = new ArrayList<OrderItem>();

    public void Order(){}

    public Integer getOid(){
        return oid;
    }
    public  Integer getUid(){
        return uid;
    }
    public  Date getDate(){
        return date;
    }

    public void setOid(Integer id){
        this.oid = id;
    }
    public void setUid(Integer id){
        this.uid = id;
    }
    public void setDate(Date date){
        this.date = date;
    }

    public List<OrderItem> getList() {
        return list;
    }
    public void setList(List<OrderItem> list) {
        this.list = list;
    }
}