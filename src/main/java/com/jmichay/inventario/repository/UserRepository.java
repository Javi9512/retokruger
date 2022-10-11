package com.jmichay.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmichay.inventario.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
