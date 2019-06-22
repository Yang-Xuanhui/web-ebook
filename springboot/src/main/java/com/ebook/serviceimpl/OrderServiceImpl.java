package com.ebook.serviceimpl;

import com.ebook.dao.OrderDao;
import com.ebook.dao.OrderItemDao;
import com.ebook.entity.Book;
import com.ebook.entity.Order;
import com.ebook.entity.OrderItem;
import com.ebook.entity.User;
import com.ebook.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    /* 处理从数据库中查询到的订单，返回需要的订单信息 */
    public List<Map<String,Object>> getOrder(List<Order> orders){
        if(orders==null){
            return null;
        }
        List<Map<String,Object>> orderlists = new ArrayList<>();
        /* 遍历查询结果 */
        for(Order order:orders){
            Map<String,Object> lists= new HashMap<>();
            List<Map<String,Object>> items = new ArrayList<>();
            /* 获取一份订单中的详情 */
            List<OrderItem> list = findItems(order.getOid());
            /* 订单总金额 */
            Double total = 0.0;
            /* 遍历订单详情 */
            for(OrderItem item:list){
                Book book = item.getBook();
                Integer amount = item.getAmount();
                Double price = item.getPrice();
                /* 计算订单总金额 */
                total+=(price*amount);
                /* 增添需要返回的订单信息 */
                Map<String, Object> oneItem = new HashMap<>();
                oneItem.put("b_id",book.getBid());
                oneItem.put("cname",book.getCname());
                oneItem.put("img",book.getImg());
                oneItem.put("amount",amount);
                oneItem.put("price",price);

                /* 把订单详情添加到一份订单的List中 */
                items.add(oneItem);
            }
            /* 给订单增加时间，总金额和订单号信息 */
            Map<String,Object> info = new HashMap<>();
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            info.put("date",sdf.format(order.getDate()));
            info.put("total_money",total);
            info.put("oid",order.getOid());
            info.put("user",order.getUser().getUsername());
            lists.put("info",info);

            lists.put("items",items);
            /* 把订单添加到订单列表中 */
            orderlists.add(lists);
        }
        return orderlists;
    }

    @Override
    public Map<Book, Integer> findBookSales(Timestamp begin, Timestamp end){
        List<Order> orders = findAll();
        Map<Book, Integer> sales = new HashMap<>();
        for(Order order : orders){
            Timestamp date = order.getDate();
            // the order is in the time span we search
            if(date.after(begin) && date.before(end)){
                List<OrderItem> items = order.getItems();
                for(OrderItem item : items){
                    Book book = item.getBook();
                    // the book is in the sales map
                    if(sales.containsKey(book)){
                        Integer amount = sales.get(book) + item.getAmount();
                        sales.put(book, amount);
                    }
                    // new book
                    else{
                        sales.put(book, item.getAmount());
                    }
                }
            }
        }
        return sales;
    }

    @Override
    public Map<User, Double> findUserCsm(Timestamp begin, Timestamp end){
        List<Order> orders = findAll();
        Map<User, Double> consumer = new HashMap<>();
        for(Order order : orders){
            Timestamp date = order.getDate();
            // the order is in the time span we search
            if(date.after(begin) && date.before(end)){
                User user = order.getUser();
                List<OrderItem> items = order.getItems();
                Double money = 0.0;
                // calculate the consumption
                for(OrderItem item : items){
                    money += item.getAmount()*item.getPrice();
                }
                if(consumer.containsKey(user)){
                    money += consumer.get(user);
                    consumer.put(user,money);
                }
                else{
                    consumer.put(user,money);
                }
            }
        }
        return consumer;
    }

    @Override
    public Timestamp getBegin(String start){
        Timestamp begin;
        if(start.equals("")){
            begin = Timestamp.valueOf("2000-01-01 00:00:00");
        }
        else{
            begin = Timestamp.valueOf(start);
        }
        return begin;
    }

    @Override
    public Timestamp getEnd(String time){
        Timestamp end;
        if(time.equals("")){
            end = new Timestamp(System.currentTimeMillis());
        }
        else{
            end = Timestamp.valueOf(time);
        }
        return end;
    }
}
