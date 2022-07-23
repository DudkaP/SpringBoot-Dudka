package com.example.springbootdudka.controllers;

import com.example.springbootdudka.controllers.dao.CustomerDAO;
import com.example.springbootdudka.controllers.models.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MainController {
    private CustomerDAO customerDAO;

    @GetMapping("/open")
    public String open() {
        return "open URL";
    }

    @GetMapping("/secure")
    public String secure() {
        return "secure URL";
    }

    @PostMapping("/save")
    public void save(@RequestBody CustomerDTO customerDTO) {

    }


}
