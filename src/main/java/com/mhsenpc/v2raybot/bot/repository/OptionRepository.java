package com.mhsenpc.v2raybot.bot.repository;

import com.mhsenpc.v2raybot.bot.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, Integer> {
    Option findFirstByKey(String key);
}