package com.mycompany.reto_bases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Entrenador extends Persona {
    private int aniosExperiencia;
    private final List<Equipo> equipos;

    public Entrenador(String nombre, String documento, int edad, int aniosExperiencia) {
        super(nombre, documento, edad);
        setAniosExperiencia(aniosExperiencia);
        this.equipos = new ArrayList<>();
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        if (aniosExperiencia < 0 || aniosExperiencia > 80) {
            throw new IllegalArgumentException("Los anios de experiencia deben estar entre 0 y 80.");
        }
        this.aniosExperiencia = aniosExperiencia;
    }

    public List<Equipo> getEquipos() {
        return Collections.unmodifiableList(equipos);
    }

    public void agregarEquipo(Equipo equipo) {
        if (equipo != null && !equipos.contains(equipo)) {
            equipos.add(equipo);
        }
    }

    @Override
    public void mostrarRol() {
        System.out.println("Entrenador: " + getNombre()
                + " - Documento: " + getDocumento()
                + " - Edad: " + getEdad()
                + " - Experiencia: " + aniosExperiencia + " anios");
    }
}
