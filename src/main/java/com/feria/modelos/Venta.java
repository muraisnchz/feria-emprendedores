package com.feria.modelos;

import java.time.LocalDate;

public class Venta {
    private String idVenta;
    private Producto producto;
    private int cantidad;
    private LocalDate fecha;
    private boolean pagoRealizado;

    public Venta(String idVenta, Producto producto, int cantidad) {
        this.idVenta = idVenta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.fecha = LocalDate.now();
        this.pagoRealizado = false;
    }

    public double calcularTotal() {
        double subtotal = cantidad * producto.getPrecio();
        return aplicarDescuentos(subtotal);
    }

    // Método corto con una sola responsabilidad (calcular el descuento)
    private double aplicarDescuentos(double subtotal) {
        if (cantidad > 10) {
            subtotal *= 0.90;
        }
        if (subtotal > 5000) {
            subtotal *= 0.95;
        }
        return subtotal;
    }

    public void registrarPago() {
        this.producto.reducirStock(this.cantidad);
        this.pagoRealizado = true;
    }

    public boolean isPagoRealizado() { return pagoRealizado; }
}