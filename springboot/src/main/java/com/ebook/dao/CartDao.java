package com.ebook.dao;

import com.ebook.entity.Cart;
import java.util.List;

public interface CartDao {
    List<Cart> findByUser_Uid(Integer uid);
    Cart findByCid(Integer cid);
    Cart findByUser_UidAndBook_Bid(Integer uid,Integer bid);
    /* return number of lines deleted or updated */
    Integer deleteByCid(Integer cid);
    Integer deleteByUidAndBid(Integer uid,Integer bid);
    Integer updateAmount(Integer amount,Integer cid);
    void save(Cart cart);
}
