package com.PF.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.PF.apirest.modelo.detalleOrden;

@Repository
public interface InterfzDetalleOrdenRepository extends JpaRepository<detalleOrden, Integer>{

}
