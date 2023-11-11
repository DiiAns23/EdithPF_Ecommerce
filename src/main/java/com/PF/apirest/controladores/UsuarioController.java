package com.PF.apirest.controladores;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.PF.apirest.modelo.orden;
import com.PF.apirest.modelo.usuario;
import com.PF.apirest.servicios.InterfzOrdenService;
import com.PF.apirest.servicios.InterfzUsuarioService;

import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private InterfzUsuarioService usuarioService;

    @Autowired
    private InterfzOrdenService ordenService;

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

    @GetMapping("/compras")
    public String obtenerCompras(Model model, HttpSession session) {
        model.addAttribute("sesion", session.getAttribute("idusuario"));

        usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        List<orden> ordenes = ordenService.findByUsuario(usuario);

        model.addAttribute("ordenes", ordenes);
        return "usuario/compras";
    }

    @GetMapping("/detalle/{id}")
    public String detalleCompra(@PathVariable Integer id, HttpSession session, Model model ) {
        logger.info("Id de orden: {}", id);
        System.out.println("Id de orden aaaaa: " + id);
        Optional<orden> orden = ordenService.findById(id);
        System.out.println("Orden aaaaaaaaaaaaa: " + orden.get());
        model.addAttribute("detalles", orden.get().getDetalle());

        //sesion
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        
        System.out.println();
        return "usuario/detallecompra";
    }

    @GetMapping("/cerrar")
    public String cerrarSesion(HttpSession session) {
        session.removeAttribute("idusuario");
        return "redirect:/";
    }
    
}
