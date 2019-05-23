package com.ebook.daoimpl;

import com.ebook.dao.UserDao;
import com.ebook.entity.User;
import com.ebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    UserRepository userRepository;

    @Override
    public User findByUsernameAndPassword(String username, String password){
        return userRepository.findByUsernameAndPassword(username,password);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByUid(Integer uid){
        return userRepository.findByUid(uid);
    }

    @Override
    @Transactional
    public Integer updateByUsername(Integer enable, String username){
        return userRepository.updateByUsername(enable,username);
    }

    @Override
    @Transactional
    public void save(User user){
        userRepository.save(user);
    }
}
