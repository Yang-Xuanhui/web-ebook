package com.ebook.controller;

import com.ebook.entity.Book;
import com.ebook.entity.Order;
import com.ebook.entity.OrderItem;
import com.ebook.service.BookService;
import com.ebook.service.OrderItemService;
import com.ebook.service.OrderService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/carts")
public class CartController{
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    BookService bookService;

    @RequestMapping("/add")
    @ResponseBody
    public String addToCart(HttpServletRequest request , String name, Integer number){
        HttpSession session = request.getSession();

        Book book = bookService.FindBook(name);

        //2. 加入购物车 购物车其实就是商品和数量的对应关系。
        Map<Book , Integer> map = (Map<Book , Integer>)session.getAttribute("cartMap");
        //第一次来，连购物车都没有
        if(map == null){
            map = new HashMap<Book , Integer>();
        }

        //判断购物车里面是否有该商品 ，如果有  数量 + 1  ，如果没有，数量-1
        if(map.containsKey(book)){
            map.put(book , map.get(book) +number);
        }else{
            map.put(book , number );
        }

        //3. 存储到session中。
        request.getSession().setAttribute("cartMap" , map);

        //当前的跳转是基于template/list.html ，所以需要重定向跳转到中转页面
        return "add to cart";
    }

    @RequestMapping(value = "/buy", method = {RequestMethod.POST},produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String clearCart(@RequestBody JSONObject obj,HttpServletRequest request){
        HttpSession session = request.getSession();
        Map<String,Integer> books = obj.getJSONObject("books");
        Date date = new Date(new java.util.Date().getTime());
        Order order = new Order();
        order.setUid(obj.getInt("uid"));
        order.setDate(date);
        Book book;

        for(Map.Entry<String, Integer> entry :books.entrySet()){
            book = bookService.FindBook(entry.getKey());

            //2. 加入购物车 购物车其实就是商品和数量的对应关系。
            Map<Book , Integer> map = (Map<Book , Integer>)session.getAttribute("cartMap");
            //第一次来，连购物车都没有
            if(map == null){
                return "nothing to buy";
            }

            //判断购物车里面是否有该商品 ，如果有  数量 + 1  ，如果没有，数量-1
            Integer number = entry.getValue();
            if(map.containsKey(book)){
                if(book.getIsDelete()==true){
                    return "this book is deleted";
                }
                if(book.getStorage()<number){
                    return "there are not enough books";
                }
                map.put(book , map.get(book) -number);
                // 加入到数据库的orderItems表中
                OrderItem orderItem = new OrderItem();
                orderItem.setBid(book.getBid());
                orderItem.setOid(order.getOid());
                orderItem.setNumber(number);
                orderItem.setPrice(book.getPrice());
                orderItem.setId(0);
                orderItemService.save(orderItem);

            }else{
                return "doesn't have this book in your cart";
            }
            //3. 存储到session中。
            request.getSession().setAttribute("cartMap" , map);
        }
        return "done";
    }
}