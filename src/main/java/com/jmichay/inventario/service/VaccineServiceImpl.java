package com.jmichay.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmichay.inventario.entity.Vaccine;
import com.jmichay.inventario.repository.VaccineRepository;

@Service
public class VaccineServiceImpl implements VaccineService {

    @Autowired
    private VaccineRepository vaccineRepository;

    @Override
    public Vaccine getVaccineById(Long id) {
        return vaccineRepository.findById(id).get();
    }

    @Override
    public List<Vaccine> getAllVaccines() {
        return vaccineRepository.findAll();
    }

    @Override
    public Vaccine getVaccineByName(String name) {
        return vaccineRepository.findByName(name);
    }

}
