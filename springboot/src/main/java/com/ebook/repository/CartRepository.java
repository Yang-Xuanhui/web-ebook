package com.ebook.repository;

import java.util.List;
import com.ebook.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByCid(Integer cid);
    Cart findByUser_UidAndBook_Bid(Integer uid,Integer bid);

    /* return number of lines deleted or updated */
    @Modifying
    @Query(value = "delete from carts where cid= ?1", nativeQuery = true)
    Integer deleteByCid(Integer cid);

    @Modifying
    @Query(value = "delete from carts where uid= ?1 and bid= ?2", nativeQuery = true)
    Integer deleteByUidAndBid(Integer uid,Integer bid);

    @Modifying
    @Query(value = "update carts set amount=?1 where cid=?2 ", nativeQuery = true)
    Integer updateAmount(Integer amount,Integer cid);
}
