package com.example.springbootdudka.controllers;

import com.example.springbootdudka.controllers.dao.CustomerDAO;
import com.example.springbootdudka.controllers.models.Customer;
import com.example.springbootdudka.controllers.models.dto.CustomerDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@AllArgsConstructor
public class MainController {
    private CustomerDAO customerDAO;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

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
        Customer customer = new Customer();
        customer.setLogin(customerDTO.getUsername());
        customer.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
        customer.setRole("ROLE_ADMIN");

        customerDAO.save(customer);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CustomerDTO customerDTO) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(customerDTO.getUsername(), customerDTO.getPassword()));
        if (authenticate != null){

//            Date date = new Date();
//            date.setTime(date.getTime()+10000);

            String jwToken = Jwts.builder()
                    .setSubject(authenticate.getName())
                    .signWith(SignatureAlgorithm.HS512, "lockKey".getBytes())
//                    .setExpiration(date)
                    .compact();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization","Bearer " + jwToken);
            return new ResponseEntity<>("You are loged in", httpHeaders, HttpStatus.OK);
        }
        return new ResponseEntity<>("Error", HttpStatus.UNAUTHORIZED);
    }


}
