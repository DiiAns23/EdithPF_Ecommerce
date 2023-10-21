package com.PF.apirest.controladores;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.PF.apirest.modelo.producto;
import com.PF.apirest.servicios.productoService;


@Controller
@RequestMapping("/administrador")
public class administradorControlador {

    @Autowired
    private productoService productoService;

    @GetMapping("")
    public String home(Model model) {

        List<producto> productos= productoService.findAll();
        model.addAttribute("productos", productos);
        
        return "administrador/home"; // Devuelve la ruta del archivo HTML sin la extensión
    }
}
