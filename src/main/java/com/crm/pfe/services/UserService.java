package com.crm.pfe.services;

import com.crm.pfe.entities.Role;
import com.crm.pfe.entities.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    User getUser(String username);


    List<User> getUsers();
}
