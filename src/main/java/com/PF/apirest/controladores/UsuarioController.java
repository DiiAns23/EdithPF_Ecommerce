package com.PF.apirest.controladores;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.PF.apirest.modelo.usuario;
import com.PF.apirest.servicios.InterfzUsuarioService;

import jakarta.servlet.http.HttpSession;

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

    @GetMapping("/login")
    public String login() {
        return "usuario/login";
    }

    @PostMapping("/acceder")
    public String acceder(usuario usuario, HttpSession session) {
        logger.info("Accesos : {}", usuario);

        Optional<usuario> user = usuarioService.findByEmail(usuario.getEmail());
        logger.info("Usuario de bd: {}", user.get());

        if(user.isPresent()) {
            session.setAttribute("idusuario", user.get().getId());
            if(user.get().getRol().equals("ADMIN")) {
                return "redirect:/administrador";
            } else {
                return "redirect:/";
                }
        } else {
            logger.info("Usuario no existe");
        }
        return "redirect:/";
    }
}
