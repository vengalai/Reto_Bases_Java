package com.mycompany.reto_bases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Torneo {
    private String nombre;
    private final List<Equipo> equipos;
    private final List<Partido> partidos;

    public Torneo(String nombre) {
        setNombre(nombre);
        this.equipos = new ArrayList<>();
        this.partidos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del torneo no puede estar vacio.");
        }
        this.nombre = nombre.trim();
    }

    public List<Equipo> getEquipos() {
        return Collections.unmodifiableList(equipos);
    }

    public List<Partido> getPartidos() {
        return Collections.unmodifiableList(partidos);
    }

    public boolean existeEquipo(String nombreEquipo) {
        if (!Equipo.esNombreValido(nombreEquipo)) {
            return false;
        }
        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo.trim())) {
                return true;
            }
        }
        return false;
    }

    public void agregarEquipo(Equipo equipo) {
        if (equipo == null) {
            throw new IllegalArgumentException("El equipo no puede ser nulo.");
        }
        if (existeEquipo(equipo.getNombre())) {
            throw new IllegalArgumentException("Ya existe un equipo con ese nombre.");
        }
        equipos.add(equipo);
    }

    public Equipo buscarEquipo(String nombreEquipo) {
        if (nombreEquipo == null) {
            return null;
        }
        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo.trim())) {
                return equipo;
            }
        }
        return null;
    }

    public void agregarPartido(Partido partido) {
        if (partido == null) {
            throw new IllegalArgumentException("El partido no puede ser nulo.");
        }
        if (!equipos.contains(partido.getEquipoLocal()) || !equipos.contains(partido.getEquipoVisitante())) {
            throw new IllegalArgumentException("Los dos equipos deben estar registrados en el torneo.");
        }
        partidos.add(partido);
    }

    public Jugador buscarJugador(String nombreJugador) {
        if (nombreJugador == null || nombreJugador.trim().isEmpty()) {
            return null;
        }
        for (Equipo equipo : equipos) {
            for (Jugador jugador : equipo.getJugadores()) {
                if (jugador.getNombre().equalsIgnoreCase(nombreJugador.trim())) {
                    return jugador;
                }
            }
        }
        return null;
    }

    public List<PosicionEquipo> calcularTablaPosiciones() {
        List<PosicionEquipo> tabla = new ArrayList<>();

        for (Equipo equipo : equipos) {
            tabla.add(new PosicionEquipo(equipo));
        }

        for (Partido partido : partidos) {
            if (!partido.isJugado()) {
                continue;
            }

            PosicionEquipo local = buscarPosicion(tabla, partido.getEquipoLocal());
            PosicionEquipo visitante = buscarPosicion(tabla, partido.getEquipoVisitante());

            local.incrementarPartidosJugados();
            visitante.incrementarPartidosJugados();

            if (partido.getGolesLocal() > partido.getGolesVisitante()) {
                local.sumarPuntos(3);
            } else if (partido.getGolesLocal() < partido.getGolesVisitante()) {
                visitante.sumarPuntos(3);
            } else {
                local.sumarPuntos(1);
                visitante.sumarPuntos(1);
            }
        }

        tabla.sort(Comparator.comparingInt(PosicionEquipo::getPuntos).reversed()
                .thenComparing(PosicionEquipo::getEquipoNombre, String.CASE_INSENSITIVE_ORDER));
        return tabla;
    }

    private PosicionEquipo buscarPosicion(List<PosicionEquipo> tabla, Equipo equipo) {
        for (PosicionEquipo posicion : tabla) {
            if (posicion.getEquipo() == equipo) {
                return posicion;
            }
        }
        throw new IllegalStateException("Equipo no encontrado en la tabla.");
    }

    public void mostrarTablaPosiciones() {
        List<PosicionEquipo> tabla = calcularTablaPosiciones();

        System.out.println("\nTabla de posiciones");
        System.out.println("Equipo - Partidos Jugados - Puntos");
        if (tabla.isEmpty()) {
            System.out.println("No hay equipos registrados.");
            return;
        }

        for (PosicionEquipo posicion : tabla) {
            System.out.println(posicion.getEquipoNombre() + " - "
                    + posicion.getPartidosJugados() + " - " + posicion.getPuntos());
        }
    }

    public String generarReporte() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("========================================\n");
        reporte.append("REPORTE FINAL DEL TORNEO\n");
        reporte.append("========================================\n");
        reporte.append("Torneo: ").append(nombre).append("\n");
        reporte.append("Cantidad de equipos: ").append(equipos.size()).append("\n");
        reporte.append("Cantidad de partidos: ").append(partidos.size()).append("\n\n");

        reporte.append("EQUIPOS\n");
        for (Equipo equipo : equipos) {
            reporte.append("- ").append(equipo.getNombre()).append("\n");
            if (equipo.getEntrenador() != null) {
                reporte.append("  Entrenador: ").append(equipo.getEntrenador().getNombre()).append("\n");
            } else {
                reporte.append("  Entrenador: Sin registrar\n");
            }

            reporte.append("  Jugadores:\n");
            if (equipo.getJugadores().isEmpty()) {
                reporte.append("    Sin jugadores registrados\n");
            } else {
                for (Jugador jugador : equipo.getJugadores()) {
                    reporte.append("    - ").append(jugador.getNombre())
                            .append(" | Posicion: ").append(jugador.getPosicion().getNombre())
                            .append(" | Camiseta: ").append(jugador.getNumeroCamiseta()).append("\n");
                }
            }
        }

        reporte.append("\nPARTIDOS\n");
        if (partidos.isEmpty()) {
            reporte.append("No hay partidos programados.\n");
        } else {
            for (Partido partido : partidos) {
                reporte.append("- ").append(partido.getFecha()).append(" | ")
                        .append(partido.getEquipoLocal().getNombre()).append(" vs ")
                        .append(partido.getEquipoVisitante().getNombre());
                if (partido.isJugado()) {
                    reporte.append(" | Resultado: ").append(partido.getGolesLocal())
                            .append(" - ").append(partido.getGolesVisitante());
                } else {
                    reporte.append(" | Pendiente");
                }
                reporte.append("\n");
            }
        }

        reporte.append("\nTABLA DE POSICIONES\n");
        for (PosicionEquipo posicion : calcularTablaPosiciones()) {
            reporte.append(posicion.getEquipoNombre()).append(" - ")
                    .append(posicion.getPartidosJugados()).append(" partidos - ")
                    .append(posicion.getPuntos()).append(" puntos\n");
        }

        return reporte.toString();
    }
}
