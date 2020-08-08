package com.softserve.edu.service.impl;

import com.softserve.edu.exception.EntityNotFoundException;
import com.softserve.edu.model.Role;
import com.softserve.edu.repository.RoleRepository;
import com.softserve.edu.service.RoleService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No role /w id " + id)));
    }

    @Override
    @Transactional
    public List<Role> getAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.isEmpty() ? new ArrayList<>() : roles;
    }
}
