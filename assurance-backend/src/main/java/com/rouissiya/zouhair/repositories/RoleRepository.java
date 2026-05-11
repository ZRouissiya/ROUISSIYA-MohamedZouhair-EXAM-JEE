package com.rouissiya.zouhair.repositories;

import com.rouissiya.zouhair.entities.Role;
import com.rouissiya.zouhair.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}