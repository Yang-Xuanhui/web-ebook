package com.ebook.controller;

import com.ebook.entity.*;
import com.ebook.service.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping("/carts")
public class CartController{
    @Autowired
    private OrderService orderService;
    @Autowired
    private BookService bookService;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @RequestMapping(value="/list",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Map<String,Object>> list(HttpServletRequest request){
        User user = userService.getUser(request);
        List<Cart> carts = user.getCartList();
        /* 购物车为空 */
        if(carts==null){
            return null;
        }
        List<Map<String,Object>> lists = new ArrayList<>();
        Book book;
        for(Cart cart :carts){
            book = cart.getBook();
            Map<String,Object> list = new HashMap<>();
            list.put("cid",cart.getCid());
            list.put("bid",book.getBid());
            list.put("name",book.getName());
            list.put("cname",book.getCname());
            list.put("img",book.getImg());
            list.put("storage",book.getStorage());
            list.put("price",book.getPrice());
            list.put("isDelete",book.getIsDelete());
            list.put("amount",cart.getAmount());
            lists.add(list);
        }
        return lists;
    }

    @RequestMapping(value="/add",method = RequestMethod.POST, produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String addToCart(@RequestBody JSONObject cart,HttpServletRequest request){
        User user = userService.getUser(request);
        if(user!=null && user.getEnable()==1){
            List<Cart> cartlist = user.getCartList();
            Integer bid = cart.getInt("book_id");
            Integer amount = cart.getInt("amount");
            Book book = bookService.findBook(bid);

            // have the same book in cart
            Cart oldcart = cartService.findCart(user.getUid(),book.getBid());
            if(oldcart != null){
                cartService.updateAmount(oldcart.getAmount()+amount,oldcart.getCid());
                return "加入购物车";
            }

            // new book for the cart
            Cart newcart = new Cart();
            if(!book.getIsDelete() && book.getStorage()>=amount){
                newcart.setCid(0);
                newcart.setUser(user);
                newcart.setBook(book);
                newcart.setAmount(amount);
                cartService.save(newcart);
                return "加入购物车";
            }
            else if(book.getStorage()<amount){
                newcart.setCid(0);
                newcart.setUser(user);
                newcart.setBook(book);
                newcart.setAmount(book.getStorage());
                cartService.save(newcart);
                return "图书库存不足";
            }
            else{
                return "图书已下架";
            }
        }
        else{
            return "用户不存在或被禁用";
        }
    }

    @RequestMapping(value="/delete",method = RequestMethod.POST, produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public List<Map<String,Object>> deleteCart(@RequestBody JSONObject obj,HttpServletRequest request){
        JSONArray cartArray = obj.getJSONArray("cartlist");
        Integer i =0;
        for(i=0;i<cartArray.size();i++){
            cartService.deleteById(cartArray.getInt(i));
        }
        return list(request);
    }

    @RequestMapping(value="/update",method = RequestMethod.POST, produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String updateCart(@RequestBody JSONObject cart){
        Integer cid = cart.getInt("cart_id");
        Integer amount = cart.getInt("amount");
        cartService.updateAmount(amount,cid);
        return "update amount";
    }

    @RequestMapping(value = "/buy", method = {RequestMethod.POST},produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public List<Map<String,Object>> clearCart(@RequestBody JSONObject obj,HttpServletRequest request){
        User user = userService.getUser(request);

        JSONArray cartArray = obj.getJSONArray("cartlist");

        Timestamp date = new Timestamp(new java.util.Date().getTime());

        Order order = new Order();
        order.setOid(0);
        order.setUser(user);
        order.setDate(date);
        order.setIsdelete(false);

        Order neworder = orderService.save(order);

        //Double total_cost = 0.0;
        Integer countOrder = 0;

        for(Integer i = 0;i<cartArray.size();i++) {
            JSONObject cartObj = cartArray.getJSONObject(i);
            Integer cid = cartObj.getInt("cart_id");
            Cart cart = cartService.findbyId(cid);
            Integer number = cartObj.getInt("amount");
            Book book = cart.getBook();
            if (book.getIsDelete()) {
                continue;
            }
            if (book.getStorage() < number) {
                continue;
            }
            //total_cost +=(book.getPrice()*number);
            // 加入到数据库的orderItems表中
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(neworder);
            // 新对象，如果setID(0)，会有不匹配的错误
            orderItem.setId(i);
            orderItem.setBook(book);
            orderItem.setAmount(number);
            orderItem.setPrice(book.getPrice());
            orderService.save(orderItem);
            countOrder++;
            // 从cart中删除
            cartService.deleteByUser(user.getUid(), book.getBid());

            // 更新book库存和销量信息
            bookService.updateSales(book.getSales() + number, book.getBid());
            bookService.updateStorage(book.getStorage() - number, book.getBid());
        }
        if(countOrder==0){
            orderService.deleteOrder(neworder.getOid());
        }
        return list(request);
    }
}