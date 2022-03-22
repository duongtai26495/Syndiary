package com.duongtai.sydiary.controllers;

import com.duongtai.sydiary.entities.ResponseObject;
import com.duongtai.sydiary.entities.User;
import com.duongtai.sydiary.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("profile/{username}")
    public ResponseEntity<ResponseObject> getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @GetMapping("all")
    public ResponseEntity<ResponseObject> getAll(){
        return userService.getAll();
    }

    @PostMapping("register")
    public ResponseEntity<ResponseObject> createUser (@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseObject> getById(@PathVariable Long id){
        return userService.getById(id);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<ResponseObject> editById(@PathVariable Long id, @RequestBody User user){
        user.setId(id);
        return userService.editUserById(user);
    }
}
