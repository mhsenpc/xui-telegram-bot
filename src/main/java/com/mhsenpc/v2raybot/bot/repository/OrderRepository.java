package com.mhsenpc.v2raybot.bot.repository;

import com.mhsenpc.v2raybot.bot.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    long countByStatus(int status);

    long countByStatusAndPhotosIsNotEmpty(int orderStatus);

    List<Order> findByStatusAndPhotosIsNotEmpty(int status);
}