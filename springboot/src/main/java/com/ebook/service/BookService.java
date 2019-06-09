package com.ebook.service;

import com.ebook.entity.Book;
import com.ebook.entity.BookCover;
import net.sf.json.JSONObject;

import java.util.List;

public interface BookService{
    /* read */
    Book findBook(String name);
    Book findBook(Integer id);
    List<Book> listBook();
    BookCover findCover(String id);

    /* create */
    void saveBook(Book book);
    void saveBook(JSONObject book);
    BookCover saveBookCover(BookCover bookCover);

    /* delete (update */
    Integer deleteBook(Integer id);

    /* update */
    Integer updateStorage(Integer storage,Integer bid);
    Integer updateSales(Integer sales,Integer bid);
}
