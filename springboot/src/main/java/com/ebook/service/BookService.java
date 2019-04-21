package com.ebook.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.ebook.repository.BookRepository;
import com.ebook.entity.Book;

import java.util.List;

@Service
public class BookService{

    @Autowired
    private BookRepository bookRepository;

    public Book FindBook(String name) {
        return bookRepository.findByName(name);
    }
    public Book FindBookById(Integer id) {
        return bookRepository.findByBid(id);
    }
    public void save(Book book1) {
        bookRepository.save(book1);
    }
    public List<Book> ListBook() {
        return bookRepository.findByIsDeleteFalse();
    }
    @Modifying
    public void DeleteById(Integer id){ bookRepository.deleteByBid(id);}
}
