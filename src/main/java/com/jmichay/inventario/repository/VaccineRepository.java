package com.jmichay.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmichay.inventario.entity.Vaccine;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    Vaccine findByName(String name);
}
