package com.mycompany.reto_bases;

public class Jugador extends Persona {
    private Posicion posicion;
    private int numeroCamiseta;

    public Jugador(String nombre, String documento, int edad, Posicion posicion, int numeroCamiseta) {
        super(nombre, documento, edad);
        setPosicion(posicion);
        setNumeroCamiseta(numeroCamiseta);
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        if (posicion == null) {
            throw new IllegalArgumentException("La posicion es obligatoria.");
        }
        this.posicion = posicion;
    }

    public int getNumeroCamiseta() {
        return numeroCamiseta;
    }

    public void setNumeroCamiseta(int numeroCamiseta) {
        if (numeroCamiseta < 1 || numeroCamiseta > 99) {
            throw new IllegalArgumentException("El numero de camiseta debe estar entre 1 y 99.");
        }
        this.numeroCamiseta = numeroCamiseta;
    }

    @Override
    public void mostrarRol() {
        System.out.println("Jugador: " + getNombre()
                + " - Documento: " + getDocumento()
                + " - Edad: " + getEdad()
                + " - Posicion: " + posicion.getNombre()
                + " - Camiseta: " + numeroCamiseta);
    }
}
