package com.jmichay.inventario.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.jmichay.inventario.entity.Role;
import com.jmichay.inventario.entity.User;
import com.jmichay.inventario.entity.Vaccine;
import com.jmichay.inventario.repository.RoleRepository;
import com.jmichay.inventario.repository.UserRepository;
import com.jmichay.inventario.repository.VaccineRepository;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private VaccineRepository vaccineRepository;

    @Autowired
    private UserRepository userRepository;

    public void run(ApplicationArguments args) {
        Role role = new Role();
        role.setName("ADMIN");
        Role roleSave = roleRepository.save(role);
        role = new Role();
        role.setName("EMPLOYEE");
        roleRepository.save(role);

        Vaccine vaccine = new Vaccine();
        vaccine.setName("Sputnik");
        vaccineRepository.save(vaccine);
        vaccine = new Vaccine();
        vaccine.setName("AstraZeneca");
        vaccineRepository.save(vaccine);
        vaccine = new Vaccine();
        vaccine.setName("Pfizer");
        vaccineRepository.save(vaccine);
        vaccine = new Vaccine();
        vaccine.setName("Jhonson&Jhonson");
        vaccineRepository.save(vaccine);
        User user = new User();
        user.setUsername("admin");
        user.setPassword(new BCryptPasswordEncoder().encode("admin"));
        User userSaved = userRepository.saveAndFlush(user);
        userSaved.setRole(roleSave);
        userRepository.save(userSaved);

    }
}