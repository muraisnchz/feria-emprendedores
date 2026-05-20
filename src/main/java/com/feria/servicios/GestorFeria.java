package com.feria.servicios;

import com.feria.modelos.*;
import com.feria.utils.Validadores;
import java.util.ArrayList;
import java.util.List;

public class GestorFeria {
    private List<Emprendedor> emprendedores = new ArrayList<>();
    private List<Venta> ventas = new ArrayList<>();

    // Ahora recibe el objeto completo, eliminando la obsesión por primitivos
    public void registrarEmprendedor(Emprendedor emprendedor) {
        if (Validadores.isEmprendedorValido(emprendedor)) {
            emprendedores.add(emprendedor);
        } else {
            System.out.println("Error: Datos del emprendedor inválidos.");
        }
    }

    public void registrarVenta(Venta venta) {
        ventas.add(venta);
    }

    public List<Emprendedor> getEmprendedoresConStockBajo() {
        List<Emprendedor> resultado = new ArrayList<>();
        for (Emprendedor e : emprendedores) {
            for (Producto p : e.getProductos()) {
                if (p.tieneStockBajo()) {
                    resultado.add(e);
                    break; // Evita duplicar al emprendedor si tiene varios productos bajos
                }
            }
        }
        return resultado;
    }

    public List<Venta> getVentas() { return ventas; }
    public List<Emprendedor> getEmprendedores() { return emprendedores; }
}