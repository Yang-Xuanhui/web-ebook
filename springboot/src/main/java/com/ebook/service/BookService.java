package com.ebook.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebook.repository.BookRepository;
import com.ebook.entity.Book;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService{

    @Autowired
    private BookRepository bookRepository;

    /* read */
    public Book FindBook(String name) {
        return bookRepository.findByName(name);
    }
    public Book FindBookById(Integer id) {
        return bookRepository.findByBid(id);
    }
    public List<Book> ListBook(){
        return bookRepository.findByIsdeleteFalse();
    }

    /* create */
    public void save(Book book1) {
        bookRepository.save(book1);
    }

    /* delete (update */
    @Transactional
    public Integer deleteById(Integer id){
        return bookRepository.deleteByBid(id);
    }

    /* update */
    @Transactional
    public Integer updateStorage(Integer storage,Integer bid){
        return bookRepository.updateStorage(storage,bid);
    }
    @Transactional
    public Integer updateSales(Integer sales,Integer bid){
        return bookRepository.updateSales(sales,bid);
    }
}
