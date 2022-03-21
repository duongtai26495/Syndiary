package com.duongtai.sydiary.services.impl;

import com.duongtai.sydiary.entities.ResponseObject;
import com.duongtai.sydiary.entities.Role;
import com.duongtai.sydiary.repositories.RoleRepository;
import com.duongtai.sydiary.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void saveNewRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name);
    }
}
