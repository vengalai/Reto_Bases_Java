package com.mycompany.reto_bases;

public abstract class Persona {
    private String nombre;
    private String documento;
    private int edad;

    public Persona(String nombre, String documento, int edad) {
        setNombre(nombre);
        setDocumento(documento);
        setEdad(edad);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        this.nombre = nombre.trim();
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        if (documento == null || documento.trim().isEmpty()) {
            throw new IllegalArgumentException("El documento no puede estar vacio.");
        }
        this.documento = documento.trim();
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad < 1 || edad > 100) {
            throw new IllegalArgumentException("La edad debe estar entre 1 y 100.");
        }
        this.edad = edad;
    }

    public abstract void mostrarRol();
}
