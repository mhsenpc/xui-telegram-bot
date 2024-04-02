package com.mhsenpc.v2raybot.bot.repository;

import com.mhsenpc.v2raybot.bot.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    // You can add custom query methods if needed
}