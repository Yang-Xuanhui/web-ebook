package com.ebook.controller;

import com.ebook.entity.Order;
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

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController{
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;

    @RequestMapping("/delete")
    @ResponseBody
    public void deleteOrder(Integer id){
        orderService.DeleteById(id);
    }

    @RequestMapping("/readOrder")
    @ResponseBody
    public List<Order> getOrder(Integer id){
        User user = userService.findById(id);
        if(user != null && user.getRole()==0){
            List<Order> orders = orderService.FindAll();
            return orders;
        }
        else if(user!=null && user.getEnable()==1) {
            List<Order> orders = orderService.FindByUser(id);
            return orders;
        }
        else return null;
    }

}