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
    BookService bookService;
    @Autowired
    UserService userService;
    @RequestMapping("/booklist")
    public List<Book> getList(){
        return bookService.ListBook();
    }

    @RequestMapping("/detail")
    public Book getDetail(String name){
        /*从数据库获取数据*/
        return bookService.FindBook(name);
    }

    /* for admin only */
    @RequestMapping(value="/add",method = RequestMethod.POST, produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String addBook(@RequestBody JSONObject book, HttpServletRequest request){
        if(!userService.isAdmin(request)){
            return "no permission";
        }
        String name = book.getString("name");
        Book oldbook;
        if((oldbook = bookService.FindBook(name))==null){
            Book newbook = saveBook(book);
            bookService.save(newbook);
            return "add book";
        }
        else{
            if(oldbook.getIsDelete()){
                bookService.deleteById(oldbook.getBid());
                Book newbook = saveBook(book);
                bookService.save(newbook);
                return "add book";
            }
            return "add book fail";
        }

    }

    /* for admin only */
    @RequestMapping(value="/delete",method = RequestMethod.POST, produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String deleteBook(@RequestBody JSONObject book,HttpServletRequest request){
        if(!userService.isAdmin(request)){
            return "no permission";
        }
        Book book1;
        if((book1 = bookService.FindBook(book.getString("name")))!=null){
            book1.setIsDelete(true);
            bookService.save(book1);
            return "true";
        }
        else
            return "false";
    }

    /* for admin only */
    @RequestMapping(value="/update",method = RequestMethod.POST, produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String updateBook(@RequestBody JSONObject book,HttpServletRequest request){
        if(!userService.isAdmin(request)){
            return "no permission";
        }
        String name = book.getString("name");
        Book oldbook;
        if((oldbook = bookService.FindBook(name))!=null){
            bookService.deleteById(oldbook.getBid());
            Book book1 = saveBook(book);
            bookService.save(book1);
            return "true";
        }
        else
            return "false";
    }

    private Book saveBook(JSONObject book){
        Book newbook = new Book();

        String name = book.getString("name");
        String cname = book.getString("cname");
        String writer = book.getString("writer");
        String brief = book.getString("brief");
        String img = book.getString("img");
        String isbn = book.getString("isbn");
        Double price = book.getDouble("price");
        Double rate = book.getDouble("rate");
        Integer storage = book.getInt("storage");
        Integer sales = book.getInt("sales");
        String book_intro = book.getString("book_intro");
        String writer_intro = book.getString("writer_intro");
        String book_comment = book.getString("book_comment");

        newbook.setBid(0);
        newbook.setName(name);
        newbook.setCname(cname);
        newbook.setWriter(writer);
        newbook.setBrief(brief);
        newbook.setImg(img);
        newbook.setIsbn(isbn);
        newbook.setPrice(price);
        newbook.setRate(rate);
        newbook.setSales(sales);
        newbook.setStorage(storage);
        newbook.setBook_intro(book_intro);
        newbook.setWriter_intro(writer_intro);
        newbook.setBook_comment(book_comment);
        newbook.setIsDelete(false);

        return newbook;
    }
}