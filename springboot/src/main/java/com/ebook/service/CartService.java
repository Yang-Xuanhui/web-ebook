package com.ebook.service;


import com.ebook.entity.Cart;

public interface CartService {
    /* read */
    Cart findbyId(Integer cid);

    Cart findCart(Integer uid, Integer bid);

    /* create */
    void save(Cart cart);

    /* delete */
    Integer deleteById(Integer cid);

    Integer deleteByUser(Integer uid, Integer bid);

    /* update */
    Integer updateAmount(Integer amount, Integer cid);
}