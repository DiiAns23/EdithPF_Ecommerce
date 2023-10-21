package com.PF.apirest.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.PF.apirest.modelo.producto;
import com.PF.apirest.repositories.productoRepository;

@Service
public class productoServiceImpl implements productoService{

    @Autowired
    private productoRepository productoRepository;

    @Override
    public producto save(producto producto) {
        return productoRepository.save(producto);   
    }

    @Override
    public Optional<producto> get(Integer id) {
        return productoRepository.findById(id);
    }

    @Override
    public void update(producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void delete(Integer id) {
        productoRepository.deleteById(id);
    }

    @Override
    public List<producto> findAll() {
        return productoRepository.findAll();
    }
    
}
