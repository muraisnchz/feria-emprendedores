package com.feria.servicios;

import com.feria.modelos.Categoria;
import com.feria.modelos.Emprendedor;
import com.feria.modelos.Venta;
import java.util.List;

public class Reportes {

    public double calcularFacturacionTotal(List<Venta> ventas) {
        double total = 0;
        for (Venta v : ventas) {
            total += v.calcularTotal();
        }
        return total;
    }

    public void imprimirResumenEjecutivo(GestorFeria gestor) {
        System.out.println("========== RESUMEN EJECUTIVO ==========");
        System.out.println("Total emprendedores: " + gestor.getEmprendedores().size());
        System.out.println("Total ventas: " + gestor.getVentas().size());
        System.out.println("Total facturado: $" + calcularFacturacionTotal(gestor.getVentas()));
        
        System.out.println("\nEmprendedores con alerta de stock bajo:");
        for (Emprendedor e : gestor.getEmprendedoresConStockBajo()) {
            System.out.println("- " + e.getNombre());
        }
    }

    public void imprimirReportePorCategoria(GestorFeria gestor, Categoria categoria) {
        System.out.println("=== REPORTE DE EMPRENDEDORES - CATEGORÍA: " + categoria + " ===");
        for (Emprendedor e : gestor.getEmprendedores()) {
            if (e.getCategoria() == categoria) {
                System.out.println("- " + e.getNombre() + " (Contacto: " + e.getEmail() + ")");
            }
        }
    }
}