package com.PF.apirest.modelo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "usuarios")    
public class usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )             
    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String rol;

    @OneToMany(mappedBy = "usuario")
    private List<producto> productos;

    @OneToMany(mappedBy = "usuario")

    private List<orden> ordenes;

    public usuario() {
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }

    

    public List<producto> getProductos() {
        return productos;
    }


    public void setProductos(List<producto> productos) {
        this.productos = productos;
    }


    @Override
    public String toString() {
        return "usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email
                + ", password=" + password + ", rol=" + rol + "]";
    }

    
}
