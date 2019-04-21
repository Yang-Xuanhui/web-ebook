package com.ebook.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebook.repository.OrderRepository;
import com.ebook.entity.Order;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
public class OrderService{

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> FindByUser(Integer u_id) {
        return orderRepository.findByUid(u_id);
    }
    public List<Order> FindAll(){ return orderRepository.findAll();}
    @Transactional
    public void DeleteById(Integer id){ orderRepository.deleteByOid(id);}
    public List<Order> FindById(Integer id){
        return orderRepository.findByOid(id);
    }
    public List<Order> FindbyUserAndDate(Integer u_id, Date date1,Date date2) {
        return orderRepository.findByUidAndDateBetween(u_id,date1,date2);
    }
    public List<Order> FindbyDate(Date date1,Date date2) {
        return orderRepository.findByDateBetween(date1,date2);
    }
    public void save(Order order1) {
        orderRepository.save(order1);
    }
}
