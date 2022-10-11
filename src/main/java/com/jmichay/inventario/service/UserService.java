package com.jmichay.inventario.service;

import com.jmichay.inventario.entity.User;

public interface UserService {
    public User getUserById(Long id);

    public User saveUser(User user);

}
