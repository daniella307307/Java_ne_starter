package com.example.demo.repositories;

import com.example.demo.enumerations.role.EUserRole;
import com.example.demo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findRoleByName(EUserRole name);
}
