package com.ebook.daoimpl;

import com.ebook.dao.BookDao;
import com.ebook.entity.Book;
import com.ebook.repository.BookRepository;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/* simple implementation */
@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> findByIsdeleteFalse(){
        return bookRepository.findByIsdeleteFalse();
    }

    @Override
    public Book findByBid(Integer id){
        return bookRepository.findByBid(id);
    }

    @Override
    public Book findByName(String name){
        return bookRepository.findByName(name);
    }

    @Override
    @Transactional
    public Integer updateIsdelete(Integer bid){
        return bookRepository.updateIsdelete(bid);
    }

    @Override
    @Transactional
    public Integer updateStorage(Integer storage,Integer bid){
        return bookRepository.updateStorage(storage,bid);
    }

    @Override
    @Transactional
    public Integer updateSales(Integer salse,Integer bid){
        return bookRepository.updateSales(salse,bid);
    }

    @Override
    @Transactional
    public void save(Book book){
        bookRepository.save(book);
    }
}
