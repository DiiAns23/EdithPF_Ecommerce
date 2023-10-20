package com.PF.apirest.servicios;

import java.util.Optional;

import com.PF.apirest.modelo.producto;

public interface productoService {
    public producto save(producto producto);
    public Optional<producto> get(Integer id);
    public void update(producto producto);
    public void delete(Integer id);

}
