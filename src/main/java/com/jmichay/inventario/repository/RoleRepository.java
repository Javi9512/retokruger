package com.jmichay.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmichay.inventario.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

}
