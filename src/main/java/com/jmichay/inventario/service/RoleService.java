package com.jmichay.inventario.service;

import com.jmichay.inventario.entity.Role;

public interface RoleService {
    // Obtener rol por id
    public Role getRoleById(Long id);

    // Obtener rol por nombre
    public Role getRoleByName(String name);

}
