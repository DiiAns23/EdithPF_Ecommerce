package com.PF.apirest.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;
    private String imagen;
    private double precio;
    private int cantidad;

    @ManyToOne
    private usuario usuario;

    public producto() {
    }

    public producto(Integer id, String nombre, String descripcion, String imagen, double precio, int cantidad,
            com.PF.apirest.modelo.usuario usuario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.precio = precio;
        this.cantidad = cantidad;
        this.usuario = usuario;
    }

    public Integer getId() {
        return this.id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getNombre() {
        return this.nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getDescripcion() {
        return this.descripcion;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public String getImagen() {
        return this.imagen;
    }


    public void setImagen(String imagen) {
        this.imagen = imagen;
    }


    public double getPrecio() {
        return this.precio;
    }


    public void setPrecio(double precio) {
        this.precio = precio;
    }


    public int getCantidad() {
        return this.cantidad;
    }


    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    @Override
    public String toString() {
        // Aqui igual lo correcto es ponerle el this. antes del nombre de la variable, no hay problema pero por buenas practicas
        return "producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", imagen=" + imagen
                + ", precio=" + precio + ", cantidad=" + cantidad + "]";
    }

    public usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(usuario usuario) {
        this.usuario = usuario; // Porque aqui si lo estas colocando :v
    } 

} 
