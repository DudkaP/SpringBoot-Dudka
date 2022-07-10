package com.example.springbootdudka.controllers;

import com.example.springbootdudka.controllers.dao.UserDAO;
import com.example.springbootdudka.controllers.models.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class MainController {

    private UserDAO userDAO;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void saveUser(@RequestBody @Valid User user){
        userDAO.save(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userDAO.findAll();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        User user = userDAO.findById(id).get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @DeleteMapping("users/{id}")
    public void delUserById(@PathVariable int id){
        userDAO.deleteById(id);
    }

    @GetMapping("users/getbyname")
    public ResponseEntity<List<User>> getUserByName(@RequestParam String name){
        List<User> users = userDAO.findByName(name);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PatchMapping("/users")
    public ResponseEntity<User> updateUser (@RequestBody User user) {
        User save = userDAO.save(user);
        return new ResponseEntity<>(save, HttpStatus.OK);
    }
}
