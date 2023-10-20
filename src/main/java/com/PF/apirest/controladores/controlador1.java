package com.PF.apirest.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/controlador1")

public class controlador1 {

    @GetMapping("/ruta1")  // Aqui la ruta es /api/controlador1/ruta1
    public DatosJson obtenerDatosJson() {
        DatosJson datos = new DatosJson();
        datos.setNombre("Ejemplo");
        datos.setEdad(30);
        return datos; // Aqui devuelvo un archivo json que es lo que necesito recolectar para poder mostrarlo en mi html
    }

    @GetMapping("/ruta2")
    public String ruta2() {
        return "Esta es la ruta 2 del Controlador 1";
    }
}

