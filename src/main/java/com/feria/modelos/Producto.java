package com.feria.modelos;

public class Producto {
    private String nombre;
    private double precio;
    private int stock;
    private static final int UMBRAL_STOCK_BAJO = 5;

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public void reducirStock(int cantidad) {
        if (cantidad > this.stock) {
            throw new IllegalArgumentException("Stock insuficiente para: " + nombre);
        }
        this.stock -= cantidad;
    }

    public boolean tieneStockBajo() {
        return this.stock < UMBRAL_STOCK_BAJO;
    }

    // Getters
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
}