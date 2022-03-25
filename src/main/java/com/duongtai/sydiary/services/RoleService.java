package com.duongtai.sydiary.services;

import com.duongtai.sydiary.entities.ResponseObject;
import com.duongtai.sydiary.entities.Role;
import org.springframework.http.ResponseEntity;

public interface RoleService {

    ResponseEntity<ResponseObject> saveNewRole(Role role);
    Role getRoleByName(String name);
}
