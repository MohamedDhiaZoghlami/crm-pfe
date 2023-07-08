package com.crm.pfe.repository;

import com.crm.pfe.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = 'ROLE_COMMERCIAL'")
    List<User> findAllCommercial();
}
