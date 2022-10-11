package com.jmichay.inventario.service;

import com.jmichay.inventario.entity.Role;

public interface RoleService {
    public Role getRoleById(Long id);

    public Role getRoleByName(String name);

}
