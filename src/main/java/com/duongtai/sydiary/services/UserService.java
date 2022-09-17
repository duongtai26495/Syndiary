package com.duongtai.sydiary.services;

import com.duongtai.sydiary.entities.ResponseObject;
import com.duongtai.sydiary.entities.Token;
import com.duongtai.sydiary.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public interface UserService {

    User findByUsername(String username);

    User findByEmail(String email);

    ResponseEntity<ResponseObject> getUserByUsername(String username);

    ResponseEntity<ResponseObject> saveUser(User user);

    ResponseEntity<ResponseObject> getById(Long id);

    ResponseEntity<ResponseObject> editByUsername(User user);

    ResponseEntity<ResponseObject> updatePassword(String newPassword);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
