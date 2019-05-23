package com.ebook.dao;

import com.ebook.entity.Order;

import java.sql.Timestamp;
import java.util.List;

public interface OrderDao {
    List<Order> findAll();

    Order findByOid(Integer oid);

    List<Order> findByUser_Uid(Integer uid);

    List<Order> findByUser_UidAndDateBetween(Integer uid, Timestamp date1, Timestamp date2);

    List<Order> findByDateBetween(Timestamp date1, Timestamp date2);

    Integer update(Integer oid);

    Order save(Order order);
}
