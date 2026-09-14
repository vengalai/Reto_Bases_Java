package com.mycompany.reto_bases;

public class Posicion {
    private String nombre;

    public Posicion(String nombre) {
        setNombre(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("La posicion no puede estar vacia.");
        }
        this.nombre = nombre.trim();
    }
}
