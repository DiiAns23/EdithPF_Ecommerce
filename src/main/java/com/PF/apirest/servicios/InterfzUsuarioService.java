package com.PF.apirest.servicios;

import java.util.Optional;

import com.PF.apirest.modelo.usuario;

public interface InterfzUsuarioService {
    Optional<usuario> findById(Integer id);
    usuario save(usuario usuario);
    Optional<usuario> findByEmail(String email);
    
}
