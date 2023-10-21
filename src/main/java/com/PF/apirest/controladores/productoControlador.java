package com.PF.apirest.controladores;

import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.PF.apirest.modelo.producto;
import com.PF.apirest.modelo.usuario;
import com.PF.apirest.servicios.productoService;


@Controller
@RequestMapping("/productos")
public class productoControlador {
    
    private final  Logger LOGGER = LoggerFactory.getLogger(productoControlador.class);
    
    @Autowired
    private productoService productoService;
    
    @GetMapping("")
    public String show(Model model){
        model.addAttribute("productos", productoService.findAll());
        return "productos/show";
    }

    @GetMapping("/create")
    public String create(){
        return "productos/create";
    }

    @PostMapping("/save")
    public String save(producto producto){
        usuario u = new usuario(1, "","","","","", null, null);
        producto.setUsuario(u);
        LOGGER.info("Este es el objeto producto {}",producto);
        productoService.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        producto producto = new producto();
        Optional<producto> optionalProducto = productoService.get(id);
        producto = optionalProducto.get();

        LOGGER.info("Producto buscado {}",producto);
        model.addAttribute("producto", producto);
        return "productos/edit";
    }

    @PostMapping("/update")
    public String update(producto producto){
        productoService.update(producto);
        return "redirect:/productos";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        productoService.delete(id);
        return "redirect:/productos";
    }
}
