package com.PF.apirest.modelo;

public class detalleOrden {
    private Integer id;
    private String nombre;
    private double precio;
    private double cantidad;
    private double total;
    
    public detalleOrden() {
    }

    public detalleOrden(Integer id, String nombre, double precio, double cantidad, double total) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.total = total;
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

    @Override
    public String toString() {
        return "detalleOrden [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", cantidad=" + cantidad
                + ", total=" + total + "]";
    }
    
    
}
