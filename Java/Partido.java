package com.mycompany.reto_bases;

public class Partido {
    private final Equipo equipoLocal;
    private final Equipo equipoVisitante;
    private int golesLocal;
    private int golesVisitante;
    private String fecha;
    private boolean jugado;

    public Partido(Equipo equipoLocal, Equipo equipoVisitante, String fecha) {
        if (equipoLocal == null || equipoVisitante == null) {
            throw new IllegalArgumentException("Los dos equipos son obligatorios.");
        }
        if (equipoLocal == equipoVisitante) {
            throw new IllegalArgumentException("Un equipo no puede jugar contra si mismo.");
        }
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        setFecha(fecha);
        this.golesLocal = 0;
        this.golesVisitante = 0;
        this.jugado = false;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha no puede estar vacia.");
        }
        this.fecha = fecha.trim();
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        validarGoles(golesLocal);
        this.golesLocal = golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(int golesVisitante) {
        validarGoles(golesVisitante);
        this.golesVisitante = golesVisitante;
    }

    public boolean isJugado() {
        return jugado;
    }

    public void registrarResultado(int golesLocal, int golesVisitante) {
        setGolesLocal(golesLocal);
        setGolesVisitante(golesVisitante);
        this.jugado = true;
    }

    private static void validarGoles(int goles) {
        if (goles < 0) {
            throw new IllegalArgumentException("Los goles no pueden ser negativos.");
        }
    }
}
