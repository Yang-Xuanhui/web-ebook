package com.ebook.controller;

import com.ebook.entity.Book;
import com.ebook.entity.Order;
import com.ebook.entity.OrderItem;
import com.ebook.entity.User;
import com.ebook.service.OrderService;
import com.ebook.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orders")
public class OrderController{
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    /* 删除订单
     * 用户删除自己的订单，但是删除后的订单仍然可以被管理员看到
     * 将订单的isDelete属性设置为true
     */
    @RequestMapping(value="/delete",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Map<String,Object>> deleteOrder(@RequestBody JSONObject obj,HttpServletRequest request){
        Integer id = obj.getInt("oid");
        orderService.deleteOrder(id);
        /* 重新返回orders */
        return readOrder(obj,request);
    }

    /* 查看订单
     * 返回给定时间范围内的订单
     * 未被禁用的用户可以看到自己的订单，不包括已删除的
     * 管理员可以看到所有订单，仍可以看到用户个人删除的订单记录
     */
    @RequestMapping(value = "/readOrder",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Map<String,Object>> readOrder(@RequestBody JSONObject obj, HttpServletRequest request){
        /* 通过cookie获取用户信息 */
        User user = userService.getUser(request);
        /* 从前端获取时间信息 */
        String s_start = obj.getString("start");
        String s_end = obj.getString("end");
        /*没有时间时，直接返回可查看的所有订单*/
        List<Order> orders;
        if(s_start.equals("") || s_end.equals("")){
            /* 管理员 */
            if(user != null && user.getRole()==0){
                orders = orderService.findAll();
                return orderService.getOrder(orders);
            }
            /* 未被禁用的用户 */
            else if(user!=null && user.getRole()==1 && user.getEnable()==1) {
                orders = orderService.findByUser(user.getUid());
                return orderService.getOrder(orders);
            }
            else return null;
        }
        /* 有时间范围 */
        Timestamp start = Timestamp.valueOf(obj.getString("start"));
        Timestamp end = Timestamp.valueOf(obj.getString("end"));

        /* 管理员 */
        if(user != null && user.getRole()==0){
            orders = orderService.findByDate(start,end);
            return orderService.getOrder(orders);
        }
        /* 未被禁用的用户 */
        else if(user!=null && user.getRole()==1 && user.getEnable()==1) {
            orders = orderService.findByUserAndDate(user.getUid(),start,end);
            return orderService.getOrder(orders);
        }
        else return null;
    }

    @RequestMapping(value = "/booksales", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Map<String,Object>> bookSales(@RequestBody JSONObject obj, HttpServletRequest request){
        /* 从前端获取时间信息 */
        String s_start = obj.getString("start");
        String s_end = obj.getString("end");
        if(userService.isAdmin(request)){
            Timestamp begin,end;
            begin = orderService.getBegin(s_start);
            end = orderService.getEnd(s_end);

            Map<Book, Integer> sales = orderService.findBookSales(begin,end);
            List<Map<String,Object>> items = new ArrayList<>();
            // traverse the result
            for (Map.Entry<Book, Integer> entry: sales.entrySet()) {
                Book book = entry.getKey();
                Integer amount = entry.getValue();
                Map<String,Object> lists= new HashMap<>();
                lists.put("bid",book.getBid());
                lists.put("cname",book.getCname());
                lists.put("img",book.getImg());
                lists.put("name",book.getName());
                lists.put("sales",amount);
                items.add(lists);
            }
            return items;
        }
        else{
            return null;
        }
    }

    @RequestMapping(value = "/consumption", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Map<String,Object>> consumption(@RequestBody JSONObject obj, HttpServletRequest request){
        /* 从前端获取时间信息 */
        String s_start = obj.getString("start");
        String s_end = obj.getString("end");
        if(userService.isAdmin(request)){
            Timestamp begin,end;
            begin = orderService.getBegin(s_start);
            end = orderService.getEnd(s_end);

            Map<User, Double> consumption = orderService.findUserCsm(begin,end);
            List<Map<String,Object>> items = new ArrayList<>();
            // traverse the result
            for (Map.Entry<User, Double> entry: consumption.entrySet()) {
                User user = entry.getKey();
                Double money = entry.getValue();
                Map<String,Object> lists= new HashMap<>();
                lists.put("uid",user.getUid());
                lists.put("username",user.getUsername());
                lists.put("consumption",money);
                items.add(lists);
            }
            return items;
        }
        else{
            return null;
        }
    }
}