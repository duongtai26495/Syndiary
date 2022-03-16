package com.duongtai.sydiary.controllers;

import com.duongtai.sydiary.entities.ResponseObject;
import com.duongtai.sydiary.entities.User;
import com.duongtai.sydiary.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/")
@CrossOrigin
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("register")
    public ResponseEntity<ResponseObject> createUser (@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getById(@PathVariable Long id){
        return userService.getById(id);
    }
}
