package com.PF.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.PF.apirest.modelo.producto;

@Repository
public interface productoRepository extends JpaRepository<producto, Integer>{
}
