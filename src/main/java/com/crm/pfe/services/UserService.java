package com.crm.pfe.services;

import com.crm.pfe.entities.Role;
import com.crm.pfe.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    Role saveRole(Role role);

    Page<User> getAllUsers(Pageable pageable);

    void addRoleToUser(String username, String roleName);

    User getUser(String username);

    List<Role> getAllRoles();
    List<User> getUsers();
}
