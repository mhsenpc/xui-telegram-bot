package com.mhsenpc.v2raybot.bot.repository;

import com.mhsenpc.v2raybot.bot.entity.TestConfig;
import com.mhsenpc.v2raybot.bot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TestConfigRepository extends JpaRepository<TestConfig, Integer> {

    int  countByUserAndCreatedAtAfter(User user, LocalDateTime dateTime);

}