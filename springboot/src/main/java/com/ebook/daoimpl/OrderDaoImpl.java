package com.ebook.daoimpl;

import com.ebook.dao.OrderDao;
import com.ebook.entity.Order;
import com.ebook.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    @Override
    public Order findByOid(Integer oid){
        return orderRepository.findByOid(oid);
    }

    @Override
    public List<Order> findByUser_Uid(Integer uid){
        return orderRepository.findByUser_Uid(uid);
    }

    @Override
    public List<Order> findByUser_UidAndDateBetween(Integer uid, Timestamp date1, Timestamp date2){
        return orderRepository.findByUser_UidAndDateBetween(uid,date1,date2);
    }

    @Override
    public List<Order> findByDateBetween(Timestamp date1, Timestamp date2){
        return orderRepository.findByDateBetween(date1,date2);
    }

    @Override
    @Transactional
    public Integer update(Integer oid){
        return orderRepository.update(oid);
    }

    @Override
    @Transactional
    public Order save(Order order){
        return orderRepository.save(order);
    }
}
