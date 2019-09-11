package com.ebook.repository;

import com.ebook.entity.BookCover;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CoverRepository extends MongoRepository<BookCover, String> {
    @Query("{ 'name' : ?0 }")
    public BookCover findByName(String Name);
    public BookCover findBookCoverById(String id);
}
