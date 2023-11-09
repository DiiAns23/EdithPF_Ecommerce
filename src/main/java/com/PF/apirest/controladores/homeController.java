package com.PF.apirest.controladores;

import java.util.Date;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import com.PF.apirest.modelo.orden;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.PF.apirest.modelo.detalleOrden;
import com.PF.apirest.modelo.producto;
import com.PF.apirest.modelo.usuario;
import com.PF.apirest.servicios.InterfzDetalleOrdenService;
import com.PF.apirest.servicios.InterfzOrdenService;
import com.PF.apirest.servicios.InterfzUsuarioService;
import com.PF.apirest.servicios.productoService;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/")
public class homeController {

    private final Logger log= LoggerFactory.getLogger(homeController.class);

    @Autowired
    private productoService productoService;

    @Autowired
    private InterfzUsuarioService usuarioService;

    @Autowired
    private InterfzOrdenService ordenService;

    @Autowired
    private InterfzDetalleOrdenService detalleOrdenService;

    //para almacenar los detalles de la orden
    List<detalleOrden> detalles = new ArrayList<detalleOrden>();

    //datos de la orden
    orden orden = new orden();

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

    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
        detalleOrden detalleOrden = new detalleOrden();
        producto producto = new producto();
        double sumaTotal = 0;

        Optional<producto> optionalProducto = productoService.get(id);
        log.info("Producto añadido: {}", optionalProducto.get());
        log.info("Cantidad: {}", cantidad);
        producto = optionalProducto.get();

        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre()); 
        detalleOrden.setTotal(producto.getPrecio()*cantidad);
        detalleOrden.setProducto(producto);

        //validar que el producto no se repita si ya existe en el carrito
        Integer idProducto=producto.getId();
        boolean ingresado=detalles.stream().anyMatch(p->p.getProducto().getId()==idProducto);

        if(!ingresado){
               detalles.add(detalleOrden);   //añadimos el detalle a la lista de detalles
        }

        sumaTotal = detalles.stream().mapToDouble(dt->dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        return "usuario/carrito";
    }

    //quitar un producto del carrito
    @GetMapping("/delete/cart/{id}")
    public String deleteProductoCart(@PathVariable Integer id, Model model){
        
        //lista nueva de productos
        List<detalleOrden> ordenesNueva = new ArrayList<detalleOrden>();

        for(detalleOrden detalleOrden:detalles){
            if (detalleOrden.getProducto().getId() != id) {
                ordenesNueva.add(detalleOrden);
            }
        }
        //poner la nueva lista con lo restante
        detalles=ordenesNueva;
        double sumaTotal = 0;
        sumaTotal = detalles.stream().mapToDouble(dt->dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        return "usuario/carrito";
    }

    @GetMapping("/getCart")
    public String getCart(Model model){
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        return "/usuario/carrito";
    }

    @GetMapping("/order")
    public String order(Model model){

        usuario usuario = usuarioService.findById(1).get();

        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        model.addAttribute("usuario", usuario);
        
        return "usuario/resumenorden";
    }
    
    //guardar orden
    @GetMapping("/saveOrder")
    public String saveOrder(){
        Date fechaCreacion = new Date();
        orden.setFechaCreacion(fechaCreacion);
        orden.setNumero(ordenService.generarNumeroOrden());

        //usuario 
        usuario usuario = usuarioService.findById(1).get();
        orden.setUsuario(usuario);
        ordenService.save(orden);

        //guardar detalles
        for(detalleOrden dt:detalles){
            dt.setOrden(orden);
            detalleOrdenService.save(dt);
        }

        //limpiar lista y orden
        orden = new orden();
        detalles.clear();


        return "redirect:/";
    }

    @GetMapping("/log")
    public String log(){
        return "usuario/login";
    }

    @GetMapping("/reg")
    public String reg(){
        return "usuario/registro";
    }

    @GetMapping("/buy")
    public String buy(){
        return "usuario/compras";
    }
    
}
