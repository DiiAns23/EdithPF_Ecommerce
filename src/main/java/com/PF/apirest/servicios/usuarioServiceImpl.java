package com.PF.apirest.servicios;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.PF.apirest.modelo.usuario;
import com.PF.apirest.repositories.InterfzUsuarioRepository;

@Service
public class usuarioServiceImpl implements InterfzUsuarioService {

    @Autowired 
    private InterfzUsuarioRepository usuarioRepository;

    @Override
    public Optional<usuario> findById(Integer id) {      
        return usuarioRepository.findById(id);
    }

    @Override
    public usuario save(usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}

