package com.ebook.dao;

import com.ebook.entity.User;

public interface UserDao {
    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    User findByUid(Integer uid);

    Integer updateByUsername(Integer enable, String username);

    void save(User user);
}

