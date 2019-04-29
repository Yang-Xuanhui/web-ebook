package com.ebook.service;


import com.ebook.entity.Cart;
import com.ebook.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService{
    @Autowired
    private CartRepository cartRepository;

    /* read */
    public List<Cart> FindbyUser(Integer uid) {
        return cartRepository.findByUser_Uid(uid);
    }
    public Cart FindbyId(Integer cid) {
        return cartRepository.findByCid(cid);
    }
    public Cart FindCart(Integer uid,Integer bid){
        return cartRepository.findByUser_UidAndBook_Bid(uid,bid);
    }

    /* create */
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    /* delete */
    @Transactional
    public Integer Delete(Integer cid){
        return cartRepository.deleteByCid(cid);
    }
    @Transactional
    public Integer Delete(Integer uid,Integer bid){
        return cartRepository.deleteByUidAndBid(uid,bid);
    }

    /* update */
    @Transactional
    public Integer updateAmount(Integer amount, Integer cid){
        return cartRepository.updateAmount(amount,cid);
    }
}
