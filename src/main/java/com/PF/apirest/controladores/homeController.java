package com.PF.apirest.controladores;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.PF.apirest.modelo.producto;
import com.PF.apirest.servicios.productoService;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/")
public class homeController {

    private final Logger log= LoggerFactory.getLogger(homeController.class);

    @Autowired
    private productoService productoService;

    @GetMapping("")
    public String home(Model model) {

        model.addAttribute("productos", productoService.findAll());
        
        return "usuario/home";
    }

    @GetMapping("productohome/{id}")
    public String productoHome(@PathVariable Integer id, Model model) {
        log.info("Id enviado como parámetro {}",id);
        producto producto = new producto();
        Optional<producto> productoOptional = productoService.get(id);
        producto = productoOptional.get();

        model.addAttribute("producto", producto);
        return "usuario/productohome";
    }
    
}
