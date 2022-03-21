package com.duongtai.sydiary.services;

import com.duongtai.sydiary.entities.ResponseObject;
import com.duongtai.sydiary.entities.Role;
import org.springframework.http.ResponseEntity;

public interface RoleService {

    void saveNewRole(Role role);
    Role getRoleByName(String name);
}
