package com.mhsenpc.v2raybot.bot.repository;

import com.mhsenpc.v2raybot.bot.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, Integer> {
    // You can add custom query methods if needed
}