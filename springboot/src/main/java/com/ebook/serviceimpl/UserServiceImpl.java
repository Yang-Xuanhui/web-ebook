package com.ebook.serviceimpl;

import com.ebook.entity.User;
import com.ebook.repository.UserRepository;
import com.ebook.service.UserService;
import com.ebook.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findNameAndPsw(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public User findUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findUser(Integer id){
        return userRepository.findByUid(id);
    }

    @Override
    public void save(User user1) {
        userRepository.save(user1);
    }

    @Override
    @Transactional
    public Integer enable(String username){
        User user = userRepository.findByUsername(username);
        if(user.getEnable()==0){
            userRepository.updateByUsername(0,username);
            return 1;
        }
        else{
            return 0;
        }
    }

    @Override
    @Transactional
    public Integer ban(String username){
        User user = userRepository.findByUsername(username);
        if(user.getEnable()==1){
            userRepository.updateByUsername(0,username);
            return 1;
        }
        else{
            return 0;
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
        return user.getUsername().equals("admin");
    }

    @Override
    public Boolean hasUser(String username){
        if(userRepository.findByUsername(username)!= null){
            return TRUE;
        }
        else{
            return FALSE;
        }
    }
}
