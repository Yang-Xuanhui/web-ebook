package com.ebook.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebook.repository.UserRepository;
import com.ebook.entity.User;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;

    public User FindNameAndPsw(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
    public void save(User user1) {
        userRepository.save(user1);
    }
    public User findByName(String username) {
        return userRepository.findByUsername(username);
    }
    public User findById(Integer id){
        return userRepository.findByUid(id);
    }
}
