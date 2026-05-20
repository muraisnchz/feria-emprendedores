package com.feria.modelos;

import java.util.ArrayList;
import java.util.List;

public class Emprendedor {
    private String nombre;
    private String id;
    private String telefono;
    private String email;
    private Categoria categoria;
    private List<Producto> productos;

    public Emprendedor(String nombre, String id, String telefono, String email, Categoria categoria) {
        this.nombre = nombre;
        this.id = id;
        this.telefono = telefono;
        this.email = email;
        this.categoria = categoria;
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        this.productos.add(producto);
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
    public Categoria getCategoria() { return categoria; }
    public List<Producto> getProductos() { return productos; }
}