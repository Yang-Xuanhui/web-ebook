package com.ebook.daoimpl;

import com.ebook.dao.OrderItemDao;
import com.ebook.entity.OrderItem;
import com.ebook.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {
    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> findByOrder_Oid(Integer oid){
        return orderItemRepository.findByOrder_Oid(oid);
    }

    @Override
    @Transactional
    public void saveAndFlush(OrderItem o){
        orderItemRepository.saveAndFlush(o);
    }
}
