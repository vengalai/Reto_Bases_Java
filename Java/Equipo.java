package com.mycompany.reto_bases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Equipo {
    private String nombre;
    private final List<Jugador> jugadores;
    private Entrenador entrenador;

    public Equipo(String nombre) {
        setNombre(nombre);
        this.jugadores = new ArrayList<>();
    }

    public static boolean esNombreValido(String nombre) {
        return nombre != null && !nombre.trim().isEmpty();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (!esNombreValido(nombre)) {
            throw new IllegalArgumentException("El nombre del equipo no puede estar vacio.");
        }
        this.nombre = nombre.trim();
    }

    public List<Jugador> getJugadores() {
        return Collections.unmodifiableList(jugadores);
    }

    public void agregarJugador(Jugador jugador) {
        if (jugador == null) {
            throw new IllegalArgumentException("El jugador no puede ser nulo.");
        }
        if (!jugadores.contains(jugador)) {
            jugadores.add(jugador);
        }
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
        if (entrenador != null) {
            entrenador.agregarEquipo(this);
        }
    }
}
