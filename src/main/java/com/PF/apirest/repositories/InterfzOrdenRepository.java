package com.PF.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.PF.apirest.modelo.orden;
import com.PF.apirest.modelo.usuario;

import java.util.List;

@Repository
public interface InterfzOrdenRepository extends JpaRepository<orden, Integer>{
    List<orden> findByUsuario(usuario usuario);
}
