package com.ebook.service;

import com.ebook.entity.User;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService{
    User findNameAndPsw(String username, String password);
    User findUser(String username);
    User findUser(Integer id);
    List<User> findAll();

    void save(User user1);

    Integer enableOrBan(String username);

    User getUser(HttpServletRequest request);

    Boolean isAdmin(HttpServletRequest request);
    Boolean hasUser(String username);
}
