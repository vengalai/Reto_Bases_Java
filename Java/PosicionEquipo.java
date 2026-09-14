package com.mycompany.reto_bases;

public class PosicionEquipo {
    private final Equipo equipo;
    private int partidosJugados;
    private int puntos;

    public PosicionEquipo(Equipo equipo) {
        if (equipo == null) {
            throw new IllegalArgumentException("El equipo no puede ser nulo.");
        }
        this.equipo = equipo;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public String getEquipoNombre() {
        return equipo.getNombre();
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public int getPuntos() {
        return puntos;
    }

    public void incrementarPartidosJugados() {
        partidosJugados++;
    }

    public void sumarPuntos(int puntos) {
        if (puntos < 0) {
            throw new IllegalArgumentException("Los puntos no pueden ser negativos.");
        }
        this.puntos += puntos;
    }
}
