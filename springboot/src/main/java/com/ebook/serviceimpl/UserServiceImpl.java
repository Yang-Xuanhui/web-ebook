package com.ebook.serviceimpl;

import com.ebook.dao.UserDao;
import com.ebook.entity.User;
import com.ebook.service.UserService;
import com.ebook.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findNameAndPsw(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }

    @Override
    public User findUser(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findUser(Integer id){
        return userDao.findByUid(id);
    }

    @Override
    public List<User> findAll(){
        return userDao.findAll();
    }

    @Override
    public void save(User user1) {
        userDao.save(user1);
    }

    @Override
    @Transactional
    public Integer enableOrBan(String username){
        User user = userDao.findByUsername(username);
        if(user.getEnable()==0){
            userDao.updateByUsername(1,username);
            return 1;
        }
        else{
            userDao.updateByUsername(0,username);
            return 1;
        }
    }

    @Override
    public User getUser(HttpServletRequest request){
        String username = CookieUtils.getCookie(request,"username");
        return findUser(username);
    }

    @Override
    public Boolean isAdmin(HttpServletRequest request){
        User user = getUser(request);
        return (user.getRole()==0);
    }

    @Override
    public Boolean hasUser(String username){
        if(userDao.findByUsername(username)!= null){
            return TRUE;
        }
        else{
            return FALSE;
        }
    }
}
