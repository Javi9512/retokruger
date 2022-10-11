package com.jmichay.inventario.service;

import com.jmichay.inventario.entity.User;

public interface UserService {
    // Obtener un usuario por su id
    public User getUserById(Long id);

    // Guardar un usuario
    public User saveUser(User user);

}
