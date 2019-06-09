package com.ebook.controller;

import com.ebook.entity.Book;
import com.ebook.entity.BookCover;
import com.ebook.service.BookService;
import com.ebook.service.UserService;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import net.sf.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    @RequestMapping(value="/upload",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String uploadCover(@RequestParam(value = "image") MultipartFile file,
                              HttpServletRequest request){
        if(!userService.isAdmin(request)){
            return "没有上传图片的权限";
        }
        if(file.isEmpty())
            return "请选择一张图片";
        try {
            BookCover bookCover = new BookCover();
            bookCover.setName(file.getOriginalFilename());
            bookCover.setCover(new Binary(file.getBytes()));
            bookCover.setCreatedTime(new Date());
            bookCover.setContentType(file.getContentType());
            bookCover.setSize(file.getSize());

            BookCover savedCover = bookService.saveBookCover(bookCover);
            String url = "http://localhost:8011/books/image/"+ savedCover.getId();
            return url;
        }
        catch (IOException e) {
            e.printStackTrace();
            return "上传图片失败";
        }

    }

    @GetMapping(value = "image/{id}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @ResponseBody
    public byte[] image(@PathVariable String id){
        byte[] data = null;
        BookCover file = bookService.findCover(id);
        if(file != null){
            data = file.getCover().getData();
        }
        return data;
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
                bookService.saveBook(book);
                return "成功增加书籍";
            }
            else{
                bookService.saveBook(book);
                return "更新书籍";
            }
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
}