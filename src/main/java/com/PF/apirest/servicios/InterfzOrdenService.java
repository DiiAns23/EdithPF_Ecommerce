package com.PF.apirest.servicios;

import java.util.List;

import com.PF.apirest.modelo.orden;

public interface InterfzOrdenService {
    List<orden> findAll();
    orden save(orden orden);
    String generarNumeroOrden();
}
