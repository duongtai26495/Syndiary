package com.duongtai.sydiary.services;

import com.duongtai.sydiary.entities.ResponseObject;
import com.duongtai.sydiary.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {

    User findByUsername(String username);

    User findByEmail(String email);

    ResponseEntity<ResponseObject> saveUser(User user);

    ResponseEntity<ResponseObject> getById(Long id);


}
