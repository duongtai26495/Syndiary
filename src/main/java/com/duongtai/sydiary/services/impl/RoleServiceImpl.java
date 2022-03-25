package com.duongtai.sydiary.services.impl;

import com.duongtai.sydiary.entities.ResponseObject;
import com.duongtai.sydiary.entities.Role;
import com.duongtai.sydiary.repositories.RoleRepository;
import com.duongtai.sydiary.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public ResponseEntity<ResponseObject> saveNewRole(Role role) {
        if(roleRepository.getRoleByName(role.getName())!=null){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("FAILED","Create role "+role.getName()+" failed, this role already exist!",null)
            );
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("SUCCESS","Create role "+role.getName()+" successfully!",roleRepository.save(role))
            );
        }

    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name);
    }
}
