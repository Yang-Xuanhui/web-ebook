package com.ebook.controller;

import com.ebook.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import net.sf.json.JSONObject;

import java.util.*;
import java.util.Map.Entry;

@RestController
//@RequestMapping("/ebooks")
public class BookController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("/booklist")
    public List<Book> getList(){
        String sql = "select b_id,cname,writer,isbn,image,price,storage,sales from books";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);

        List<Book> booklist=new ArrayList();
        readRows(list,booklist);
        return booklist;
    }
    @RequestMapping("/detail")
    public Book getDetail(Integer id){
        /*从数据库获取数据*/
        String sql = "select * from books where b_id='"+id+"'";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);

        List<Book> booklist=new ArrayList();
        readRows(list,booklist);
        return booklist.get(0);
    }
    private void readRows(List<Map<String,Object>> list,List booklist){
        for(Map<String,Object> map :list){
            Set<Entry<String,Object>> entries = map.entrySet();
            Book book = new Book();
            if(entries!=null){
                Iterator<Entry<String,Object>> iterator = entries.iterator();
                while(iterator.hasNext()){
                    Entry<String,Object> entry = (Entry<String,Object>)iterator.next();
                    Object key = entry.getKey();
                    Object value = entry.getValue();

                    /* set values to Book object*/
                    setDetail(book,key,value);
                }
            }
            booklist.add(book);
        }
    }
    private void setDetail(Book book, Object key, Object value){
        if(key.toString().equals("b_id")){
            book.setId((int)value);
        }
        else if(key.toString().equals("name")){
            book.setName(value.toString());
        }
        else if(key.toString().equals("cname")){
            book.setCname(value.toString());
        }
        else if(key.toString().equals("writer")){
            book.setWriter(value.toString());
        }
        else if(key.toString().equals("brief")){
            book.setBrief(value.toString());
        }
        else if(key.toString().equals("image")){
            book.setImg(value.toString());
        }
        else if(key.toString().equals("isbn")){
            book.setIsbn(value.toString());
        }
        else if(key.toString().equals("price")){
            book.setPrice((float)value);
        }
        else if(key.toString().equals("rate")){
            book.setRate((float)value);
        }
        else if(key.toString().equals("storage")){
            book.setStorage((int)value);
        }
        else if(key.toString().equals("sales")){
            book.setSales((int)value);
        }
        else if(key.toString().equals("book_intro")){
            book.setBook_intro(value.toString());
        }
        else if(key.toString().equals("writer_intro")){
            book.setWriter_intro(value.toString());
        }
        else if(key.toString().equals("book_comment")){
            book.setBook_omment(value.toString());
        }
    }

    @RequestMapping(value="/login",method = RequestMethod.POST, produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String userlogin(@RequestBody JSONObject user) {
        /*String name=request.getParameter("name");
        String password=request.getParameter("password");*/
        System.out.println("name: " + user.get("name"));
        System.out.println("password: " + user.get("password"));
        String name = (String) user.get("name");
        String password = (String) user.get("password");
        if(name.equals("zsz") && password.equals("888888")){
            return "login success!";
        }else{
            return "login failed";
        }
    }
}