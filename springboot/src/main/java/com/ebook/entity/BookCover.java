package com.ebook.entity;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class BookCover {
    @Id
    private String id;
    private String name;
    private Binary cover; // 书籍封面内容
    private Date createdTime; // 上传时间
    private String contentType; // 文件类型
    private Long size; // 文件大小

    public BookCover(){}

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public Binary getCover(){
        return cover;
    }
    public void setCover(Binary cover){
        this.cover = cover;
    }

    public Date getCreatedTime(){
        return createdTime;
    }
    public void setCreatedTime(Date date){
        this.createdTime = date;
    }

    public void setContentType(String type){
        this.contentType = type;
    }

    public void setSize(Long size){
        this.size = size;
    }
}

