package com.ebook.controller;


import com.ebook.entity.User;
import com.ebook.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping(value="/ban",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String banUser(@RequestBody JSONObject user, HttpServletRequest request){
        if(userService.isAdmin(request)){
            String username = user.getString("username");
            Integer status = userService.ban(username);
            if(status==1){
                return "禁用用户";
            }
            else{
                return "该用户已被禁用";
            }
        }
        else{
            return "没有禁用用户的权限";
        }
    }

    @RequestMapping(value="/free",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String freeUser(@RequestBody JSONObject user,HttpServletRequest request){
        if(userService.isAdmin(request)){
            String username = user.getString("username");
            Integer status = userService.enable(username);
            if(status==1){
                return "接触禁用";
            }
            else{
                return "该用户未被禁用";
            }
        }
        else{
            return "没有解禁用户的权限";
        }
    }
}