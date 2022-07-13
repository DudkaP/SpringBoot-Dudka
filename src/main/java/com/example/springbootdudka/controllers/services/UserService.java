package com.example.springbootdudka.controllers.services;

import com.example.springbootdudka.controllers.dao.UserDAO;
import com.example.springbootdudka.controllers.models.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserDAO userDAO;
    private MailService mailService;

    public void save(User user){
        if (user.getName() != null){
            userDAO.save(user);
            mailService.sendEmail(user);
        }
    }
    public List<User> findAll (){
        return userDAO.findAll();
    }
    public User findById(int id){
        return userDAO.findById(id).get();
    }
    public void deleteById (int id) {
        userDAO.deleteById(id);
    }
    public List<User> findByUserName (String name){
        return userDAO.findByName(name);
    }
    public User updateUser (User user) {
        return userDAO.save(user);
    }
    public ResponseEntity<String> setActivation (int id) {
        User user = this.findById(id);
        user.setActivated(true);
        userDAO.save(user);
        return new ResponseEntity<>("Account activated", HttpStatus.OK);
    }
}
