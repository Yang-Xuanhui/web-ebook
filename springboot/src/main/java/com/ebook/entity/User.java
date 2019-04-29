package com.ebook.entity;

import javax.persistence.*;
import java.util.List;

@Table(name="users")
@Entity
public class User{
    @Id
    public Integer uid;
    @Column(nullable = false, unique = true)
    private String username;
    private String email,password;
    private Integer role, enable;


    public void User(){}

    public Integer getUid(){
        return uid;
    }
    public String getUsername(){
        return username;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public Integer getRole(){
        return role;
    }
    public Integer getEnable(){
        return enable;
    }
    public void setUid(Integer id){
        this.uid = id;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setRole(Integer role){
        this.role = role;
    }
    public void setEnable(Integer enable){
        this.enable = enable;
    }

}