package com.ebook.dao;

import com.ebook.entity.Cart;

public interface CartDao {
    Cart findByCid(Integer cid);
    Cart findByUser_UidAndBook_Bid(Integer uid,Integer bid);
    /* return number of lines deleted or updated */
    Integer deleteByCid(Integer cid);
    Integer deleteByUidAndBid(Integer uid,Integer bid);
    Integer updateAmount(Integer amount,Integer cid);
    void save(Cart cart);
}
