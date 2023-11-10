package com.PF.apirest.controladores;

import org.apache.commons.math3.analysis.function.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.PF.apirest.modelo.usuario;
import com.PF.apirest.servicios.InterfzUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private InterfzUsuarioService usuarioService;

    @GetMapping("/registro")
    public String create() {
        return "usuario/registro";
    }
    
    @PostMapping("/save")
    public String save(usuario usuario) {
        logger.info("Usuario registro: ", usuario);
        usuario.setRol("USER");
        usuarioService.save(usuario);
        return "redirect:/";
    }
}
