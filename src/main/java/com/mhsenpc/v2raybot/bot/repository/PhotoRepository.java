package com.mhsenpc.v2raybot.bot.repository;

import com.mhsenpc.v2raybot.bot.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    // You can add custom query methods if needed

    List<Photo> findByOrderIdOrderByWidthDesc(int orderId);
}