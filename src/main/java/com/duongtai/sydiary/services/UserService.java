package com.duongtai.sydiary.services;

import com.duongtai.sydiary.entities.User;
import com.duongtai.sydiary.entities.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface UserService {

    User findByUsername(String username);

    User findByEmail(String email);

    User getUserByUsername(String username);

    User saveUser(User user);

    User getById(Long id);

    User editByUsername(User user);

    boolean updatePassword(String newPassword);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
