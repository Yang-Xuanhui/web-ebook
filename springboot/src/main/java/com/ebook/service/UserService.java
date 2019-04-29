package com.ebook.service;


import com.ebook.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebook.repository.UserRepository;
import com.ebook.entity.User;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService{
    public UserService(){}
    @Autowired
    private UserRepository userRepository;

    public User FindNameAndPsw(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
    public User findByName(String username) {
        return userRepository.findByUsername(username);
    }
    public User findById(Integer id){
        return userRepository.findByUid(id);
    }

    public void save(User user1) {
        userRepository.save(user1);
    }

    @Transactional
    public Integer enable(String username){
        return userRepository.updateByUsername(1,username);
    }

    @Transactional
    public Integer ban(String username){
        return userRepository.updateByUsername(0,username);
    }

    public User getUser(HttpServletRequest request){
        String username = CookieUtils.getCookie(request,"username");
        return findByName(username);
    }

    public Boolean isAdmin(HttpServletRequest request){
        User user = getUser(request);
        return user.getUsername().equals("admin");
    }
}
