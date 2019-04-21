package com.ebook.repository;

import java.util.List;

import com.ebook.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByIsDeleteFalse();
    Book findByBid(Integer id);
    Book findByName(String name);
    @Transactional
    @Modifying
    @Query(value = "delete from books where bid= ?1", nativeQuery = true)
    void deleteByBid(Integer bid);

}