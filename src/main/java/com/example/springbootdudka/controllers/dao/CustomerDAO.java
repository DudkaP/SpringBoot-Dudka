package com.example.springbootdudka.controllers.dao;

import com.example.springbootdudka.controllers.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerDAO extends JpaRepository<Customer, Integer> {
    Customer findByLogin(String login);
}
