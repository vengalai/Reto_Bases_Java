package com.mycompany.reto_bases;

import java.util.Scanner;

public class Reto_Bases {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Torneo torneo = null;
        int opcion;

        String[] opcionesMenu = {
            "1. Crear torneo",
            "2. Registrar equipo",
            "3. Registrar jugador o entrenador",
            "4. Programar partido",
            "5. Registrar resultado",
            "6. Mostrar tabla de posiciones",
            "7. Buscar jugador",
            "8. Generar reporte y salir"
        };

        do {
            System.out.println("\n========== MENU TORNEO ==========");
            for (String opcionMenu : opcionesMenu) {
                System.out.println(opcionMenu);
            }
            System.out.print("Elija una opcion: ");

            opcion = convertirEntero(scanner.nextLine());

            switch (opcion) {
                case 1:
                    torneo = crearTorneo(scanner);
                    break;
                case 2:
                    if (!hayTorneo(torneo)) {
                        continue;
                    }
                    registrarEquipo(scanner, torneo);
                    break;
                case 3:
                    if (!hayTorneo(torneo)) {
                        continue;
                    }
                    registrarPersona(scanner, torneo);
                    break;
                case 4:
                    if (!hayTorneo(torneo)) {
                        continue;
                    }
                    programarPartido(scanner, torneo);
                    break;
                case 5:
                    if (!hayTorneo(torneo)) {
                        continue;
                    }
                    registrarResultado(scanner, torneo);
                    break;
                case 6:
                    if (!hayTorneo(torneo)) {
                        continue;
                    }
                    torneo.mostrarTablaPosiciones();
                    break;
                case 7:
                    if (!hayTorneo(torneo)) {
                        continue;
                    }
                    buscarJugador(scanner, torneo);
                    break;
                case 8:
                    if (torneo == null) {
                        System.out.println("No hay torneo creado. Saliendo del programa.");
                    } else {
                        System.out.println(torneo.generarReporte());
                        System.out.println("Saliendo del programa...");
                    }
                    break;
                default:
                    System.out.println("Opcion invalida. Debe elegir un numero del 1 al 8.");
            }
        } while (opcion != 8);

        scanner.close();
    }

    private static boolean hayTorneo(Torneo torneo) {
        if (torneo == null) {
            System.out.println("Primero debe crear un torneo.");
            return false;
        }
        return true;
    }

    public static int convertirEntero(String texto) {
        try {
            return Integer.parseInt(texto.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static double convertirDouble(String texto) {
        try {
            return Double.parseDouble(texto.trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            return -1.0;
        }
    }

    public static Torneo crearTorneo(Scanner scanner) {
        while (true) {
            System.out.print("Nombre del torneo: ");
            String nombre = scanner.nextLine().trim();

            if (nombre.isEmpty()) {
                System.out.println("El nombre del torneo no puede estar vacio.");
                continue;
            }

            System.out.println("Torneo creado con exito.");
            return new Torneo(nombre);
        }
    }

    public static void registrarEquipo(Scanner scanner, Torneo torneo) {
        System.out.print("Nombre del equipo: ");
        String nombre = scanner.nextLine().trim();

        if (!Equipo.esNombreValido(nombre)) {
            System.out.println("El nombre del equipo no puede estar vacio.");
            return;
        }

        if (torneo.existeEquipo(nombre)) {
            System.out.println("Ya existe un equipo con ese nombre.");
            return;
        }

        torneo.agregarEquipo(new Equipo(nombre));
        System.out.println("Equipo registrado con exito.");
    }

    public static void registrarPersona(Scanner scanner, Torneo torneo) {
        System.out.print("Nombre del equipo: ");
        Equipo equipo = torneo.buscarEquipo(scanner.nextLine());

        if (equipo == null) {
            System.out.println("No existe ese equipo.");
            return;
        }

        int tipo = leerEnteroEnRango(scanner,
                "1. Jugador\n2. Entrenador\nQue desea registrar: ", 1, 2);

        String nombre = leerTextoNoVacio(scanner, "Nombre: ");
        String documento = leerTextoNoVacio(scanner, "Documento: ");
        int edad = leerEnteroEnRango(scanner, "Edad (1-100): ", 1, 100);

        try {
            if (tipo == 1) {
                String nombrePosicion = leerTextoNoVacio(scanner,
                        "Posicion (ej: Delantero, Portero): ");
                Posicion posicion = new Posicion(nombrePosicion);
                int numero = leerEnteroEnRango(scanner, "Numero de camiseta (1-99): ", 1, 99);

                Jugador jugador = new Jugador(nombre, documento, edad, posicion, numero);
                equipo.agregarJugador(jugador);
                System.out.println("Jugador registrado con exito.");
            } else {
                int anios = leerEnteroEnRango(scanner,
                        "Anios de experiencia (0-80): ", 0, 80);
                Entrenador entrenador = new Entrenador(nombre, documento, edad, anios);
                equipo.setEntrenador(entrenador);
                System.out.println("Entrenador registrado con exito.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Dato invalido: " + e.getMessage());
        }
    }

    public static void programarPartido(Scanner scanner, Torneo torneo) {
        if (torneo.getEquipos().size() < 2) {
            System.out.println("Debe registrar al menos dos equipos para programar un partido.");
            return;
        }

        System.out.print("Nombre del equipo local: ");
        Equipo local = torneo.buscarEquipo(scanner.nextLine());
        if (local == null) {
            System.out.println("El equipo local no existe.");
            return;
        }

        System.out.print("Nombre del equipo visitante: ");
        Equipo visitante = torneo.buscarEquipo(scanner.nextLine());
        if (visitante == null) {
            System.out.println("El equipo visitante no existe.");
            return;
        }

        if (local == visitante) {
            System.out.println("Un equipo no puede jugar contra si mismo.");
            return;
        }

        String fecha = leerTextoNoVacio(scanner, "Fecha del partido (ej: 2026-09-30): ");

        try {
            torneo.agregarPartido(new Partido(local, visitante, fecha));
            System.out.println("Partido programado con exito.");
        } catch (IllegalArgumentException e) {
            System.out.println("No se pudo programar el partido: " + e.getMessage());
        }
    }

    public static void registrarResultado(Scanner scanner, Torneo torneo) {
        if (torneo.getPartidos().isEmpty()) {
            System.out.println("No hay partidos programados.");
            return;
        }

        System.out.print("Nombre del equipo local del partido: ");
        String nombreLocal = scanner.nextLine();
        System.out.print("Nombre del equipo visitante del partido: ");
        String nombreVisitante = scanner.nextLine();

        Partido partidoEncontrado = null;
        for (Partido partido : torneo.getPartidos()) {
            if (partido.getEquipoLocal().getNombre().equalsIgnoreCase(nombreLocal.trim())
                    && partido.getEquipoVisitante().getNombre().equalsIgnoreCase(nombreVisitante.trim())) {
                partidoEncontrado = partido;
                break; // Se detiene al encontrar el partido solicitado.
            }
        }

        if (partidoEncontrado == null) {
            System.out.println("No se encontro ese partido.");
            return;
        }

        int golesLocal = leerEnteroNoNegativo(scanner, "Goles del equipo local: ");
        int golesVisitante = leerEnteroNoNegativo(scanner, "Goles del equipo visitante: ");

        try {
            partidoEncontrado.registrarResultado(golesLocal, golesVisitante);
            System.out.println("Resultado registrado con exito.");
        } catch (IllegalArgumentException e) {
            System.out.println("Resultado invalido: " + e.getMessage());
        }
    }

    public static void buscarJugador(Scanner scanner, Torneo torneo) {
        String nombre = leerTextoNoVacio(scanner, "Nombre del jugador a buscar: ");
        Jugador jugador = torneo.buscarJugador(nombre);

        if (jugador == null) {
            System.out.println("Jugador no encontrado.");
            return;
        }

        jugador.mostrarRol();
    }

    private static int leerEnteroNoNegativo(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            int valor = convertirEntero(scanner.nextLine());
            if (valor >= 0) {
                return valor;
            }
            System.out.println("Dato invalido. Debe ingresar un numero entero mayor o igual a 0.");
            continue; // Omite el dato invalido y vuelve a solicitarlo.
        }
    }

    private static int leerEnteroEnRango(Scanner scanner, String mensaje, int minimo, int maximo) {
        while (true) {
            System.out.print(mensaje);
            int valor = convertirEntero(scanner.nextLine());
            if (valor >= minimo && valor <= maximo) {
                return valor;
            }
            System.out.println("Dato invalido. Debe ingresar un entero entre "
                    + minimo + " y " + maximo + ".");
        }
    }

    private static String leerTextoNoVacio(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("El dato no puede estar vacio.");
        }
    }
}
