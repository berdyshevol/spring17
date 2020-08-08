package com.softserve.edu.service;

import com.softserve.edu.model.Role;

import java.util.List;

public interface RoleService {
    Role getRoleById(Long id);
    List<Role> getAll();
}
