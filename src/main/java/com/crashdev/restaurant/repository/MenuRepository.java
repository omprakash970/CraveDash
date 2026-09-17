package com.crashdev.restaurant.repository;

import com.crashdev.restaurant.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuItem, Long> {
}
