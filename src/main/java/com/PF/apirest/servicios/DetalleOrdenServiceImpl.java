package com.PF.apirest.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.PF.apirest.modelo.detalleOrden;
import com.PF.apirest.repositories.InterfzDetalleOrdenRepository;

@Service
public class DetalleOrdenServiceImpl implements InterfzDetalleOrdenService{

    @Autowired
    private InterfzDetalleOrdenRepository detalleOrdenRepository;

    @Override
    public detalleOrden save(detalleOrden detalleOrden) {
        return detalleOrdenRepository.save(detalleOrden);
    }
    
}
