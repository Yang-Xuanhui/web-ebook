package com.ebook.serviceimpl;

import com.ebook.dao.OrderDao;
import com.ebook.dao.OrderItemDao;
import com.ebook.entity.Order;
import com.ebook.entity.OrderItem;
import com.ebook.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao orderItemDao;

    /* find orders without time range */
    @Override
    public List<Order> findByUser(Integer u_id) {
        List<Order> orders = orderDao.findByUser_Uid(u_id);
        return filter(orders);
    }

    @Override
    public List<Order> findAll(){
        return orderDao.findAll();
    }

    /* find the exact order with id */
    @Override
    public Order findOrder(Integer id){
        return orderDao.findByOid(id);
    }

    @Override
    public List<OrderItem> findItems(Integer o_id) {
        return orderItemDao.findByOrder_Oid(o_id);
    }

    /* find orders with given time range */
    /* for user */
    @Override
    public List<Order> findByUserAndDate(Integer u_id, Timestamp date1, Timestamp date2) {
        List<Order> orders =  orderDao.findByUser_UidAndDateBetween(u_id,date1,date2);
        return filter(orders);
    }
    /* for admin */
    @Override
    public List<Order> findByDate(Timestamp date1,Timestamp date2) {
        return orderDao.findByDateBetween(date1,date2);
    }

    /* save */
    @Override
    public Order save(Order order1) {
        return orderDao.save(order1);
    }

    @Override
    @Transactional
    public void save(OrderItem orderitem) {
        orderItemDao.saveAndFlush(orderitem);
    }

    /* update value of isDelete */
    @Override
    @Transactional
    public void deleteOrder(Integer id){
        orderDao.update(id);
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
