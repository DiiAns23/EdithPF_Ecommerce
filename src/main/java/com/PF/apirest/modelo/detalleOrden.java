package com.PF.apirest.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalles")
public class detalleOrden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    private String nombre;
    private double precio;
    private double cantidad;
    private double total;
    
    @OneToOne
    private orden orden;

    @ManyToOne
    private producto producto;

    public detalleOrden() {
    }



    public detalleOrden(Integer id, String nombre, double precio, double cantidad, double total,
            com.PF.apirest.modelo.orden orden, com.PF.apirest.modelo.producto producto) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.total = total;
        this.orden = orden;
        this.producto = producto;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public orden getOrden() {
        return orden;
    }

    public void setOrden(orden orden) {
        this.orden = orden;
    }

    public producto getProducto() {
        return producto;
    }

    public void setProducto(producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "detalleOrden [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", cantidad=" + cantidad
                + ", total=" + total + "]";
    }
    
}
