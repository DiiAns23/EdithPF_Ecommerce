package com.PF.apirest.repositories;

import com.PF.apirest.modelo.usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfzUsuarioRepository extends JpaRepository<usuario, Integer>{
    
}
