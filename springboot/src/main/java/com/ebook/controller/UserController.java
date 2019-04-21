package com.ebook.controller;


import com.ebook.entity.User;
import com.ebook.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController{

    @Autowired
    UserService userService;

    @RequestMapping(value="/register",method = RequestMethod.POST, produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String userRegister(@RequestBody JSONObject user) {
        System.out.println("name: " + user.get("username"));
        System.out.println("email: " + user.get("email"));
        System.out.println("password: " + user.get("password"));
        String username = (String) user.get("username");
        String email = (String) user.get("email");
        String password = (String) user.get("password");
        if(!hasUser(username)){
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
        if((user1 = userService.FindNameAndPsw(username,password)) != null){
            /* USER */
            if(user1.getRole()==1){
                /* 是否被禁用 */
                if(user1.getEnable()==1){
                    /* 未被禁用 */
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
/*
    @RequestMapping(value="/ban",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Boolean ban(@RequestBody JSONObject infor){

    }
    */
    private Boolean hasUser(String username) {
        return (userService.findByName(username)!=null);
    }
}