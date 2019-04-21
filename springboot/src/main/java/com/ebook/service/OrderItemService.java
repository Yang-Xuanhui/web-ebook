package com.ebook.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebook.repository.OrderItemRepository;
import com.ebook.entity.OrderItem;

import java.util.List;

@Service
public class OrderItemService{

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItem> FindbyOrder(Integer o_id) {
        return orderItemRepository.findByOid(o_id);
    }
    public void save(OrderItem orderitem) {
        orderItemRepository.save(orderitem);
    }
}
