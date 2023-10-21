package com.PF.apirest.controladores;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.PF.apirest.modelo.producto;
import com.PF.apirest.modelo.usuario;
import com.PF.apirest.servicios.productoService;
import com.PF.apirest.servicios.uploadFileService;


@Controller
@RequestMapping("/productos")
public class productoControlador {
    
    private final  Logger LOGGER = LoggerFactory.getLogger(productoControlador.class);
    
    @Autowired
    private productoService productoService;

    @Autowired
    private uploadFileService upload;
    
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
    public String save(producto producto, @RequestParam("img") MultipartFile file) throws IOException{
        try {
            usuario u = new usuario(1, "","","","","", null, null);
            producto.setUsuario(u);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        

        //imagen
        if(producto.getId()==null){
            String nombreImagen= upload.saveImage(file);
            producto.setImagen(nombreImagen);
        } else {
            
        }
            

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
    public String update(producto producto, @RequestParam("img") MultipartFile file) throws IOException{

        producto p= new producto();
        p=productoService.get(producto.getId()).get();

        if(file.isEmpty()){ //editamos el producto pero no cambiamos la imagen
            producto.setImagen(p.getImagen());
        } else { //cuando se edita tambien la imagen
            //eliminar cuando no sea la imagen por defecto
            if(!p.getImagen().equals("default.jpg")){
                upload.deleteImage(p.getImagen());
            }       
        String nombreImagen= upload.saveImage(file);
        producto.setImagen(nombreImagen);
        }
        producto.setUsuario(p.getUsuario());
        productoService.update(producto);
        return "redirect:/productos";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) throws IOException{

        producto p = new producto();
        p=productoService.get(id).get();
    
            if(!p.getImagen().equals("default.jpg")){
                upload.deleteImage(p.getImagen());
            }
        

        productoService.delete(id);
        return "redirect:/productos";
    }
}
