package com.duongtai.sydiary.controllers;

import com.duongtai.sydiary.entities.ResponseObject;
import com.duongtai.sydiary.entities.User;
import com.duongtai.sydiary.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/")
@CrossOrigin
public class UserController {

    @Autowired
    UserServiceImpl userService;

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    @PostMapping("register")
    public ResponseEntity<ResponseObject> createUser (@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseObject> getById(@PathVariable Long id){
        if (authentication!=null){
            if (authentication.isAuthenticated()){
                return userService.getById(id);
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ResponseObject("FAILURE","You do not login",null)
        );
    }

}
