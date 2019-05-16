package com.ebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ebook.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    User findByUid(Integer uid);

    @Modifying
    @Query(value = "update users set enable=?1 where username= ?2", nativeQuery = true)
    Integer updateByUsername(Integer enable, String username);
}
