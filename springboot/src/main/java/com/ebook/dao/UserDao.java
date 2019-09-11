package com.ebook.dao;

import com.ebook.entity.User;

import java.util.List;

public interface UserDao {
    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    User findByUid(Integer uid);

    List<User> findAll();

    Integer updateByUsername(Integer enable, String username);

    void save(User user);
}

