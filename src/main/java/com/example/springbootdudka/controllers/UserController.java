package com.example.springbootdudka.controllers;

import com.example.springbootdudka.controllers.dao.UserDAO;
import com.example.springbootdudka.controllers.models.User;
import com.example.springbootdudka.controllers.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private UserDAO userDAO;
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void saveUser(@RequestBody @Valid User user) {
        userService.save(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delUserById(@PathVariable int id) {
        userService.deleteById(id);
    }

    @GetMapping("/getbyname")
    public ResponseEntity<List<User>> getUserByName(@RequestParam String name) {
        return new ResponseEntity<>(userService.findByUserName(name), HttpStatus.OK);
    }

    @PatchMapping("")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }
    @GetMapping("/activateAccount/{id}")
    public ResponseEntity<String> activateAccount(@PathVariable int id){
        return userService.setActivation(id);
    }
}
