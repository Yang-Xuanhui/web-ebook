package com.ebook.controller;

import com.ebook.entity.Book;
import com.ebook.entity.Order;
import com.ebook.entity.OrderItem;
import com.ebook.entity.User;
import com.ebook.service.OrderItemService;
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
    UserService userService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;

    /* 删除订单
     * 用户删除自己的订单，但是删除后的订单仍然可以被管理员看到
     * 将订单的isDelete属性设置为true
     */
    @RequestMapping(value="/delete",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Map<String,Object>> deleteOrder(@RequestBody JSONObject obj,HttpServletRequest request){
        Integer id = obj.getInt("oid");
        orderService.Delete(id);
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
                orders = orderService.FindAll();
                return getOrder(orders);
            }
            /* 未被禁用的用户 */
            else if(user!=null && user.getRole()==1 && user.getEnable()==1) {
                orders = orderService.FindByUser(user.getUid());
                return getOrder(orders);
            }
            else return null;
        }
        /* 有时间范围 */
        Timestamp start = Timestamp.valueOf(obj.getString("start"));
        Timestamp end = Timestamp.valueOf(obj.getString("end"));

        /* 管理员 */
        if(user != null && user.getRole()==0){
            orders = orderService.FindByDate(start,end);
            return getOrder(orders);
        }
        /* 未被禁用的用户 */
        else if(user!=null && user.getRole()==1 && user.getEnable()==1) {
            orders = orderService.FindByUserAndDate(user.getUid(),start,end);
            return getOrder(orders);
        }
        else return null;
    }

    /* 处理从数据库中查询到的订单，返回需要的订单信息 */
    private List<Map<String,Object>> getOrder(List<Order> orders){
        if(orders==null){
            return null;
        }
        List<Map<String,Object>> orderlists = new ArrayList<>();
        /* 遍历查询结果 */
        for(Order order:orders){
            Map<String,Object> lists= new HashMap<>();
            List<Map<String,Object>> items = new ArrayList<>();
            /* 获取一份订单中的详情 */
            List<OrderItem> list = orderItemService.FindbyOrder(order.getOid());
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
}