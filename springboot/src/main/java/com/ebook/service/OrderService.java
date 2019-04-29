package com.ebook.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebook.repository.OrderRepository;
import com.ebook.entity.Order;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService{

    @Autowired
    private OrderRepository orderRepository;

    /* find orders without time range */
    public List<Order> FindByUser(Integer u_id) {
        List<Order> orders = orderRepository.findByUser_Uid(u_id);
        return filter(orders);
    }
    public List<Order> FindAll(){ return orderRepository.findAll();}

    /* find the exact order with id */
    public Order FindById(Integer id){
        return orderRepository.findByOid(id);
    }

    /* find orders with given time range */
    /* for user */
    public List<Order> FindByUserAndDate(Integer u_id, Timestamp date1, Timestamp date2) {
        List<Order> orders =  orderRepository.findByUser_UidAndDateBetween(u_id,date1,date2);
        return filter(orders);
    }
    /* for admin */
    public List<Order> FindByDate(Timestamp date1,Timestamp date2) {
        return orderRepository.findByDateBetween(date1,date2);
    }

    /* save */
    public Order save(Order order1) {
        return orderRepository.save(order1);
    }
    public Order saveAndFlush(Order order){
        return orderRepository.saveAndFlush(order);
    }

    /* update value of isDelete */
    @Transactional
    public Integer Delete(Integer id){
        return orderRepository.update(id);
    }

    /* ignore orders which are deleted */
    private List<Order> filter(List<Order> orders){
        List<Order> neworders = new ArrayList<>();
        for(Order order : orders){
            if(!order.getIsdelete()){
                neworders.add(order);
            }
        }
        return neworders;
    }
}
