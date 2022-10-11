package com.jmichay.inventario.service;

import java.util.List;

import com.jmichay.inventario.entity.Vaccine;

public interface VaccineService {
    public Vaccine getVaccineById(Long id);

    public List<Vaccine> getAllVaccines();

    public Vaccine getVaccineByName(String name);
}
