package com.feria.utils;

import com.feria.modelos.Emprendedor;

public class Validadores {

    public static boolean isEmailValido(String email) {
        return email != null && email.contains("@") && email.length() >= 5;
    }

    public static boolean isTelefonoValido(String telefono) {
        return telefono != null && telefono.length() >= 8;
    }

    public static boolean isEmprendedorValido(Emprendedor e) {
        if (e == null) return false;
        if (e.getNombre() == null || e.getNombre().length() < 2) return false;
        if (!isEmailValido(e.getEmail())) return false;
        return isTelefonoValido(e.getTelefono());
    }
}