package com.ebook.dao;

import com.ebook.entity.Book;
import com.ebook.entity.BookCover;

import java.util.List;

public interface BookDao {
    List<Book> findByIsdeleteFalse();
    Book findByBid(Integer id);
    Book findByName(String name);
    Integer updateIsdelete(Integer bid);
    Integer updateStorage(Integer storage,Integer bid);
    Integer updateSales(Integer salse,Integer bid);
    void save(Book book);
    BookCover saveCover(BookCover bookCover);
    BookCover findCoverById(String id);
}
