package com.ebook.service;


import com.ebook.entity.Cart;
import com.ebook.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartService {
    /* read */
    List<Cart> findbyUser(Integer uid);

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