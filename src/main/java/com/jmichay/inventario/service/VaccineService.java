package com.jmichay.inventario.service;

import java.util.List;

import com.jmichay.inventario.entity.Vaccine;

public interface VaccineService {
    // Obtener vacuna por id
    public Vaccine getVaccineById(Long id);

    // Obtener todas las vacunas
    public List<Vaccine> getAllVaccines();

    // Obtener vacunas por nombre
    public Vaccine getVaccineByName(String name);
}
