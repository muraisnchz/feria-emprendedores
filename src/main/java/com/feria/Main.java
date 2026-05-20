package com.feria;

import com.feria.modelos.*;
import com.feria.servicios.*;
import com.feria.utils.*;

public class Main {

    public static void main(String[] args) {
        GestorFeria gestor = new GestorFeria();
        Reportes reportes = new Reportes();

        // 1. Registro de Ana (Construcción Orientada a Objetos)
        Emprendedor ana = new Emprendedor("Ana", "E001", "3423456789", "ana@gmail.com", Categoria.COMIDA);
        Producto pEmpanadas = new Producto("Empanadas", 500.0, 50);
        Producto pTortas = new Producto("Tortas", 1500.0, 10);
        Producto pAlfajores = new Producto("Alfajores", 300.0, 100);
        
        ana.agregarProducto(pEmpanadas);
        ana.agregarProducto(pTortas);
        ana.agregarProducto(pAlfajores);
        
        gestor.registrarEmprendedor(ana);
        System.out.println("Emprendedor registrado con " + ana.getProductos().size() + " productos");

        // 2. Registro de Carlos
        Emprendedor carlos = new Emprendedor("Carlos", "E002", "3423987654", "carlos@hotmail.com", Categoria.ARTESANIA);
        Producto pCollar = new Producto("Collar", 2000.0, 5);
        Producto pPulsera = new Producto("Pulsera", 800.0, 20);
        
        carlos.agregarProducto(pCollar);
        carlos.agregarProducto(pPulsera);
        
        gestor.registrarEmprendedor(carlos);

        // 3. Registrar Ventas (inyectando el objeto Producto directamente)
        Venta v1 = new Venta("V001", pEmpanadas, 10);
        Venta v2 = new Venta("V002", pCollar, 1);
        
        gestor.registrarVenta(v1);
        System.out.println("Venta registrada. Nuevo stock proyectado: " + (pEmpanadas.getStock() - 10));
        gestor.registrarVenta(v2);
        System.out.println("Venta registrada. Nuevo stock proyectado: " + (pCollar.getStock() - 1));

        System.out.println(); // Salto de línea estético

        // 4. Reporte por categoría (Usando el enum)
        reportes.imprimirReportePorCategoria(gestor, Categoria.COMIDA);

        System.out.println(); // Salto de línea estético

        // 5. Procesar ventas pendientes y cobrar (Lógica extraída a la capa de control/main)
        double totalRecaudado = 0;
        for (Venta v : gestor.getVentas()) {
            if (!v.isPagoRealizado()) {
                double monto = v.calcularTotal();
                v.registrarPago(); // ¡Aquí el objeto Venta llama a reducirStock() de forma segura!
                totalRecaudado += monto;
                System.out.println("Pago registrado y stock actualizado");
                System.out.println("Cobrada venta por $" + monto);
            }
        }
        System.out.println("Total recaudado: $" + totalRecaudado);
        System.out.println();

        // 6. Resumen Ejecutivo
        reportes.imprimirResumenEjecutivo(gestor);
        System.out.println();

        // 7. Validación manual usando la clase de utilidades
        boolean anaEsValida = Validadores.isEmprendedorValido(gestor.getEmprendedores().get(0));
        System.out.println("Emprendedor Ana válido? " + anaEsValida);

        // 8. Mostrar Info (Lógica de vista separada del modelo de datos)
        imprimirInfoEmprendedor(gestor.getEmprendedores().get(0), "E001");
    }

    /**
     * Método auxiliar (Vista): Extraemos esto de la clase Emprendedor para 
     * no violar el Principio de Responsabilidad Única (SRP).
     */
    private static void imprimirInfoEmprendedor(Emprendedor e, String idGenerico) {
        System.out.println("Emprendedor: " + e.getNombre());
        // (Nota: Si agregaste "public String getId()" en el refactor de Emprendedor, puedes usar e.getId() aquí)
        System.out.println("ID: " + idGenerico);
        System.out.println("Contacto: " + e.getTelefono() + " | " + e.getEmail());
        System.out.println("Categoría: " + e.getCategoria().name().toLowerCase());
        
        System.out.println("Productos:");
        for (Producto p : e.getProductos()) {
            System.out.println("  - " + p.getNombre() + " ($" + p.getPrecio() + ")");
        }
    }
}