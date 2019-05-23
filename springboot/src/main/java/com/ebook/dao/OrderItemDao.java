package com.ebook.dao;

import com.ebook.entity.OrderItem;

import java.util.List;

public interface OrderItemDao {
    List<OrderItem> findByOrder_Oid(Integer oid);
    void saveAndFlush(OrderItem o);
}
