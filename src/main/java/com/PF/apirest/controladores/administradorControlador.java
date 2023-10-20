package com.PF.apirest.controladores;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;


@Controller
@RequestMapping("/administrador")
public class administradorControlador {

    @GetMapping("")
    public String home() {
        return "administrador/home"; // Devuelve la ruta del archivo HTML sin la extensión
    }
}
