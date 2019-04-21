package com.ebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ebook.entity.Order;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();

    List<Order> findByOid(Integer oid);

    List<Order> findByUid(Integer uid);

    List<Order> findByUidAndDateBetween(Integer uid, Date date1, Date date2);

    List<Order> findByDateBetween(Date date1, Date date2);

    @Transactional
    @Modifying
    @Query(value = "delete from orders where oid= ?1", nativeQuery = true)
    void deleteByOid(Integer oid);
}

