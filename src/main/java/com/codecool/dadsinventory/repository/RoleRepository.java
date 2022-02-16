package com.codecool.dadsinventory.repository;

import com.codecool.dadsinventory.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
