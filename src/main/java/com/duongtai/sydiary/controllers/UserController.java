package com.duongtai.sydiary.controllers;

import com.duongtai.sydiary.entities.ResponseObject;
import com.duongtai.sydiary.entities.User;
import com.duongtai.sydiary.services.impl.StorageServiceImpl;
import com.duongtai.sydiary.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@CrossOrigin
@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    StorageServiceImpl storageService;

    @Autowired
    UserServiceImpl userService;



    @GetMapping("profile/{username}")
    public ResponseEntity<ResponseObject> getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @PostMapping("register")
    public ResponseEntity<ResponseObject> createUser (@RequestBody User user){
        return userService.saveUser(user);
    }

    @PutMapping("edit/{username}")
    public ResponseEntity<ResponseObject> editByUsername(@PathVariable String username, @RequestBody User user){
        user.setUsername(username);
        return userService.editByUsername(user);
    }

    @PutMapping("change_password")
    public ResponseEntity<ResponseObject> updatePasswordByUsername(@RequestBody User user){
        return userService.updatePassword(user.getPassword());
    }

    @PostMapping("uploadImage")
    public ResponseEntity<ResponseObject> uploadImage(@RequestParam("image")MultipartFile file){
        return storageService.storeFile(file);
    }


    @GetMapping("images/{fileName:.+}")
    public ResponseEntity<byte[]> readFile (@PathVariable String fileName){
        return storageService.readFile(fileName);
    }

    @GetMapping("logoutSuccess")
    public String logoutSuccess(){
        return "Success";
    }
}
