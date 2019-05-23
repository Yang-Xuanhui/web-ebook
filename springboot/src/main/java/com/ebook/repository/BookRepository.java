package com.ebook.repository;

import java.util.List;
import com.ebook.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByIsdeleteFalse();
    Book findByBid(Integer id);
    Book findByName(String name);

    @Modifying
    @Query(value = "delete from books where bid= ?1", nativeQuery = true)
    Integer updateIsdelete(Integer bid);

    @Modifying
    @Query(value = "update books set storage=?1 where bid=?2 ", nativeQuery = true)
    Integer updateStorage(Integer storage,Integer bid);

    @Modifying
    @Query(value = "update books set sales=?1 where bid=?2 ", nativeQuery = true)
    Integer updateSales(Integer salse,Integer bid);
}