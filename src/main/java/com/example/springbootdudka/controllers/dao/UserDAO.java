package com.example.springbootdudka.controllers.dao;

import com.example.springbootdudka.controllers.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Integer> {
    List<User> findByName(String name);
}
