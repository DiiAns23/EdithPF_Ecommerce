package com.PF.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.PF.apirest.modelo.orden;

@Repository
public interface InterfzOrdenRepository extends JpaRepository<orden, Integer>{

}
