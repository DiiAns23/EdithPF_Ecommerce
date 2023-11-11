package com.PF.apirest.controladores;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
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
import com.PF.apirest.reportes.productoExporterPDF;
import com.PF.apirest.servicios.InterfzUsuarioService;
import com.PF.apirest.servicios.productoService;
import com.PF.apirest.servicios.uploadFileService;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/productos")
public class productoControlador {
    
    private final  Logger LOGGER = LoggerFactory.getLogger(productoControlador.class);
    
    @Autowired
    private productoService productoService;

    @Autowired
    private InterfzUsuarioService usuarioService;

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
    public String save(producto producto, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException{
        try {
            usuario u = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
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
        producto p = new producto();
        Optional<producto> optionalProducto = productoService.get(id);
        p = optionalProducto.get();

        LOGGER.info("Producto buscado {}",p);
        model.addAttribute("producto", p);
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
    public String delete(@PathVariable Integer id){
        producto p = new producto();
        Optional<producto> optionalProducto = productoService.get(id);
        p = optionalProducto.get();
        if(!p.getImagen().equals("default.jpg")){
            try {
                upload.deleteImage(p.getImagen());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        productoService.delete(id);
        return "redirect:/productos";
    }

    @GetMapping("/exportarPDF")
    public void exportarListaProductosPDF(HttpServletResponse response) throws DocumentException, IOException{
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormat.format(new Date(System.currentTimeMillis()));
        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=productos_"+fechaActual+".pdf";
        response.setHeader(cabecera, valor);

        List<producto> listaProductos = productoService.findAll();
        productoExporterPDF exporter = new productoExporterPDF(listaProductos);
        exporter.exportar(response);
    }
}
