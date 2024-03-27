package com.mhsenpc.v2raybot.bot.repository;

import com.mhsenpc.v2raybot.bot.entity.TestConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestConfigRepository extends JpaRepository<TestConfig, Integer> {
    // You can define custom methods for queries here if needed
}