package com.ebook.controller;


import com.ebook.entity.User;
import com.ebook.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController{

    @Autowired
    private UserService userService;

    @RequestMapping(value="/register",method = RequestMethod.POST, produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String userRegister(@RequestBody JSONObject user) {
        System.out.println("name: " + user.get("username"));
        System.out.println("email: " + user.get("email"));
        System.out.println("password: " + user.get("password"));
        String username = (String) user.get("username");
        String email = (String) user.get("email");
        String password = (String) user.get("password");
        if(!userService.hasUser(username)){
            User user1 = new User();
            user1.setUsername(username);
            user1.setEmail(email);
            user1.setPassword(password);
            user1.setRole(1);
            user1.setEnable(1);
            user1.setUid(0);
            userService.save(user1);
            return "true";
        }
        else{
            return "false";
        }
    }

    @RequestMapping(value="/login",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Integer userLogin(@RequestBody JSONObject user){
        User user1;
        String username = (String) user.get("username");
        String password = (String) user.get("password");
        if((user1 = userService.findNameAndPsw(username,password)) != null){
            /* USER */
            if(user1.getRole()==1){
                /* 未被禁用 */
                if(user1.getEnable()==1){
                    //CookieUtils.writeCookie(response,username,user1.getUsername());
                    return 1;
                }
                /* 被禁用 */
                else return -1;
            }
            /* ADMIN */
            else
                return 2;
        }
        /* 密码或用户名不正确 */
        else
            return 0;
    }

    @RequestMapping(value="/showusers",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Map<String,Object>> showUsers(HttpServletRequest request){
        if(userService.isAdmin(request)){
            List<User> lists = userService.findAll();
            List<Map<String,Object>> returnlist = new ArrayList<>();
            for(User user:lists){
                Map<String,Object> map = new HashMap<>();
                map.put("username", user.getUsername());
                map.put("email",user.getEmail());
                map.put("role",user.getRole());
                map.put("enable",user.getEnable());
                returnlist.add(map);
            }
            return returnlist;
        }
        return null;
    }

    @RequestMapping(value="/ban",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String banUser(@RequestBody JSONObject user, HttpServletRequest request){
        if(userService.isAdmin(request)){
            String username = user.getString("username");
            User u = userService.findUser(username);
            Integer enable = u.getEnable();
            userService.enableOrBan(username);
            if(enable==1){
                return "禁用用户";
            }
            else{
                return "解禁用户";
            }
        }
        else{
            return "没有管理用户的权限";
        }
    }
}