package com.duongtai.sydiary.services.impl;

import com.duongtai.sydiary.configs.MyUserDetail;
import com.duongtai.sydiary.entities.*;
import com.duongtai.sydiary.repositories.UserRepository;
import com.duongtai.sydiary.services.UserService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private String pattern_time = "dd/MM/yy - hh:mm:ss aa";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private static final String ROLE_USER = "ROLE_USER";
    public UserServiceImpl() {

    }

    public String getUsernameLogin (){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public ResponseEntity<ResponseObject> getUserByUsername(String username) {
        if (username.equals(getUsernameLogin())){
            User user = findByUsername(username);
            if(user!=null){
                UserDTO userDTO = ConvertEntity.convertToDTO(user);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("SUCCESS","User founded!",userDTO)
                );
            }
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("FAILED","User not found!",null)
            );
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ResponseObject("FAILED","You don't have permission!",null)
        );
    }

    @Override
    public ResponseEntity<ResponseObject> saveUser(User user) {
        if (findByEmail(user.getEmail()) != null){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                 new ResponseObject("FAILED","This email already taken!",null)
                );
        }
        if (findByUsername(user.getUsername()) != null){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("FAILED","This username already taken!",null)
            );
        }
        Role default_role_user = roleService.getRoleByName(ROLE_USER);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern_time);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(1);
        user.setJoinedAt(sdf.format(date));
        user.setLastEdited(sdf.format(date));
        user.setRole(default_role_user);
        userRepository.save(user);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()){
            System.out.println("User logged in");
        }else{
            System.out.println("User not login");
        }
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("SUCCESS","User created successfully",ConvertEntity.convertToDTO(user))
        );
    }

    @Override
    public ResponseEntity<ResponseObject> getById(Long id) {
        UserDTO userDTO = ConvertEntity.convertToDTO(userRepository.findById(id).get());
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("SUCCESS","Founded!",userDTO)
        );
    }

    @Override
    public ResponseEntity<ResponseObject> editByUsername(User user) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern_time);
        User getUser = userRepository.findByUsername(user.getUsername());
        getUser.setId(getUser.getId());
        if(user.getFullName() != null){
            getUser.setFullName(user.getFullName());
        }
        if(user.getRole() != null){
            getUser.setRole(user.getRole());
        }
        if(user.getProfile_image()!=null){
            getUser.setProfile_image(user.getProfile_image());
        }
        if(user.getGender()>0 && user.getGender() <=2){
            getUser.setGender(user.getGender());
        }
        getUser.setLastEdited(sdf.format(date));

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("SUCCESS","User edited!",ConvertEntity.convertToDTO(userRepository.save(getUser)))
        );
    }

    @Override
    public ResponseEntity<ResponseObject> updatePassword(String newPassword) {
        User user = userRepository.findByUsername(getUsernameLogin());
        String passwordEncode = passwordEncoder.encode(newPassword);
        user.setPassword(passwordEncode);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern_time);
        user.setLastEdited(sdf.format(date));
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("SUCCESS","Your password updated!",null)
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return new MyUserDetail(user);
    }
}
