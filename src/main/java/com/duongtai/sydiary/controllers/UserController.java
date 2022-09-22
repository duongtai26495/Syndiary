package com.duongtai.sydiary.controllers;

import com.duongtai.sydiary.configs.Snippets;
import com.duongtai.sydiary.entities.ResponseObject;
import com.duongtai.sydiary.entities.User;
import com.duongtai.sydiary.services.impl.StorageServiceImpl;
import com.duongtai.sydiary.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @PostMapping("uploadImage/{username}")
    public ResponseEntity<ResponseObject> uploadImageWithUsername(@RequestParam("image") MultipartFile file, @PathVariable String username){
            return storageService.storeFile(file, username);

    }

    @PostMapping("uploadImage")
    public ResponseEntity<ResponseObject> uploadImage(@RequestParam("image") MultipartFile file){
        return storageService.storeFile(file, "noname");

    }

    @GetMapping("/images/{username}")
    public ResponseEntity<byte[]> readUserImage (@PathVariable String username){
        return storageService.readProfileImage(username);
    }

    @GetMapping("logoutSuccess")
    public String logoutSuccess(){
        return Snippets.SUCCESS;
    }

    @GetMapping("refresh_token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.refreshToken(request,response);
    }
}
