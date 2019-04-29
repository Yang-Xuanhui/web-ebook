package com.ebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ebook.entity.Order;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();

    Order findByOid(Integer oid);

    List<Order> findByUser_Uid(Integer uid);

    List<Order> findByUser_UidAndDateBetween(Integer uid, Timestamp date1, Timestamp date2);

    List<Order> findByDateBetween(Timestamp date1, Timestamp date2);

    @Transactional
    @Modifying
    @Query(value = "update orders set isDelete=true where oid= ?1", nativeQuery = true)
    Integer update(Integer oid);
}

