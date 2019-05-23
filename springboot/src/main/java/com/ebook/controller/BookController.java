package com.ebook.controller;

import com.ebook.entity.Book;
import com.ebook.service.BookService;
import com.ebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @RequestMapping("/booklist")
    public List<Book> getList(){
        return bookService.listBook();
    }

    @RequestMapping("/detail")
    public Book getDetail(String name){
        /*从数据库获取数据*/
        return bookService.findBook(name);
    }

    /* for admin only */
    @RequestMapping(value="/add",method = RequestMethod.POST, produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String addBook(@RequestBody JSONObject book, HttpServletRequest request){
        if(!userService.isAdmin(request)){
            return "没有增加图书的权限";
        }
        String name = book.getString("name");
        Book oldbook;
        if((oldbook = bookService.findBook(name))==null){
            bookService.saveBook(book);
            return "成功增加书籍";
        }
        else{
            if(oldbook.getIsDelete()){
                bookService.deleteBook(oldbook.getBid());
                bookService.saveBook(book);
                return "成功增加书籍";
            }
            return "增加书籍失败，已有同名的书籍";
        }

    }

    /* for admin only */
    @RequestMapping(value="/delete",method = RequestMethod.POST, produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String deleteBook(@RequestBody JSONObject book,HttpServletRequest request){
        if(!userService.isAdmin(request)){
            return "无权限";
        }
        Book book1;
        if((book1 = bookService.findBook(book.getString("name")))!=null){
            book1.setIsDelete(true);
            bookService.saveBook(book1);
            return "删除成功";
        }
        else
            return "删除失败";
    }

    /* for admin only */
    @RequestMapping(value="/update",method = RequestMethod.POST, produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String updateBook(@RequestBody JSONObject book,HttpServletRequest request){
        if(!userService.isAdmin(request)){
            return "无权限";
        }
        String name = book.getString("name");
        Book oldbook;
        if((oldbook = bookService.findBook(name))!=null){
            bookService.deleteBook(oldbook.getBid());
            bookService.saveBook(book);
            return "更新成功";
        }
        else
            return "更新失败";
    }
}