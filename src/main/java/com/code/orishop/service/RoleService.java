package com.code.orishop.service;

import com.code.orishop.model.entity.RoleEntity;
import com.code.orishop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public List<RoleEntity> getAll(){
        return roleRepository.findAll();
    }
}
