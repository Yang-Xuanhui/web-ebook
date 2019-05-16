package com.ebook.serviceimpl;

import com.ebook.entity.Book;
import com.ebook.repository.BookRepository;
import com.ebook.service.BookService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    /* read */
    @Override
    public Book findBook(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public Book findBook(Integer id) {
        return bookRepository.findByBid(id);
    }

    @Override
    public List<Book> listBook(){
        return bookRepository.findByIsdeleteFalse();
    }

    /* create */
    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void saveBook(JSONObject book){
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

        saveBook(newbook);
    }

    /* delete (update */
    @Override
    @Transactional
    public Integer deleteBook(Integer id){
        return bookRepository.updateIsdelete(id);
    }

    /* update */
    @Override
    @Transactional
    public Integer updateStorage(Integer storage,Integer bid){
        return bookRepository.updateStorage(storage,bid);
    }

    @Override
    @Transactional
    public Integer updateSales(Integer sales,Integer bid){
        return bookRepository.updateSales(sales,bid);
    }


}
