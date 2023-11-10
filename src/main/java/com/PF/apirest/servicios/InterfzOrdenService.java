package com.PF.apirest.servicios;

import java.util.List;
import java.util.Optional;

import com.PF.apirest.modelo.orden;
import com.PF.apirest.modelo.usuario;

public interface InterfzOrdenService {
    List<orden> findAll();
    Optional<orden> findById(Integer id);
    orden save(orden orden);
    String generarNumeroOrden();
    List<orden> findByUsuario(usuario usuario);
}
