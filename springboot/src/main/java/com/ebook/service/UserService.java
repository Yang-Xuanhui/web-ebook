package com.ebook.service;

import com.ebook.entity.User;
import javax.servlet.http.HttpServletRequest;

public interface UserService{
    User findNameAndPsw(String username, String password);
    User findUser(String username);
    User findUser(Integer id);

    void save(User user1);

    Integer enable(String username);
    Integer ban(String username);

    User getUser(HttpServletRequest request);

    Boolean isAdmin(HttpServletRequest request);
    Boolean hasUser(String username);
}
