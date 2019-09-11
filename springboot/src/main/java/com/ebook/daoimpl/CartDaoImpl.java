package com.ebook.daoimpl;

import com.ebook.dao.CartDao;
import com.ebook.entity.Cart;
import com.ebook.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/* simple implementation */
@Repository
public class CartDaoImpl implements CartDao {
    @Autowired
    CartRepository cartRepository;

    @Override
    public Cart findByCid(Integer cid){
        return cartRepository.findByCid(cid);
    }

    @Override
    public Cart findByUser_UidAndBook_Bid(Integer uid,Integer bid){
        return cartRepository.findByUser_UidAndBook_Bid(uid,bid);
    }

    /* return number of lines deleted or updated */
    @Override
    @Transactional
    public Integer deleteByCid(Integer cid){
        return cartRepository.deleteByCid(cid);
    }

    @Override
    @Transactional
    public Integer deleteByUidAndBid(Integer uid,Integer bid){
        return cartRepository.deleteByUidAndBid(uid,bid);
    }

    @Override
    @Transactional
    public Integer updateAmount(Integer amount,Integer cid){
        return cartRepository.updateAmount(amount,cid);
    }

    @Override
    @Transactional
    public void save(Cart cart){
        cartRepository.save(cart);
    }
}
