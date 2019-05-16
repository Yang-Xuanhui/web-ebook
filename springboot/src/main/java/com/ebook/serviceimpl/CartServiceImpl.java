package com.ebook.serviceimpl;


import com.ebook.entity.Cart;
import com.ebook.repository.CartRepository;
import com.ebook.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    /* read */
    @Override
    public List<Cart> findbyUser(Integer uid) {
        return cartRepository.findByUser_Uid(uid);
    }

    @Override
    public Cart findbyId(Integer cid) {
        return cartRepository.findByCid(cid);
    }

    @Override
    public Cart findCart(Integer uid,Integer bid){
        return cartRepository.findByUser_UidAndBook_Bid(uid,bid);
    }

    /* create */
    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    /* delete */
    @Override
    @Transactional
    public Integer deleteById(Integer cid){
        return cartRepository.deleteByCid(cid);
    }

    @Override
    @Transactional
    public Integer deleteByUser(Integer uid,Integer bid){
        return cartRepository.deleteByUidAndBid(uid,bid);
    }

    /* update */
    @Override
    @Transactional
    public Integer updateAmount(Integer amount, Integer cid){
        return cartRepository.updateAmount(amount,cid);
    }
}
