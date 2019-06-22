package com.ebook.service;

import com.ebook.entity.Book;
import com.ebook.entity.OrderItem;
import com.ebook.entity.Order;
import com.ebook.entity.User;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface OrderService{
    /* find orders without time range */
    List<Order> findByUser(Integer u_id);
    List<Order> findAll();

    /* find the exact order with id */
    Order findOrder(Integer id);

    /* find orders with given time range */
    /* for user */
    List<Order> findByUserAndDate(Integer u_id, Timestamp date1, Timestamp date2);
    /* for admin */
    List<Order> findByDate(Timestamp date1,Timestamp date2);

    /* save */
    Order save(Order order1);

    /* update value of isDelete */
    void deleteOrder(Integer id);

    /* ignore orders which are deleted */
    List<OrderItem> findItems(Integer o_id);

    void save(OrderItem orderitem);

    List<Map<String,Object>> getOrder(List<Order> orders);

    Map<Book, Integer> findBookSales(Timestamp begin, Timestamp end);

    Map<User, Double> findUserCsm(Timestamp begin, Timestamp end);

    Timestamp getBegin(String start);

    Timestamp getEnd(String time);
}
