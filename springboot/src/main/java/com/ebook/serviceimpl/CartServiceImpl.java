package com.ebook.serviceimpl;


import com.ebook.dao.CartDao;
import com.ebook.entity.Cart;
import com.ebook.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartDao;

    /* read */
    @Override
    public List<Cart> findbyUser(Integer uid) {
        return cartDao.findByUser_Uid(uid);
    }

    @Override
    public Cart findbyId(Integer cid) {
        return cartDao.findByCid(cid);
    }

    @Override
    public Cart findCart(Integer uid,Integer bid){
        return cartDao.findByUser_UidAndBook_Bid(uid,bid);
    }

    /* create */
    @Override
    public void save(Cart cart) {
        cartDao.save(cart);
    }

    /* delete */
    @Override
    @Transactional
    public Integer deleteById(Integer cid){
        return cartDao.deleteByCid(cid);
    }

    @Override
    @Transactional
    public Integer deleteByUser(Integer uid,Integer bid){
        return cartDao.deleteByUidAndBid(uid,bid);
    }

    /* update */
    @Override
    @Transactional
    public Integer updateAmount(Integer amount, Integer cid){
        return cartDao.updateAmount(amount,cid);
    }
}
