package model;

import java.util.Objects;

public class Persona{
    private String nombre;
    private boolean enfermo;
    private boolean ocupado;
    private int circulo;
    private int distrito;

    public Persona(){}

    public String getNombre() {
        return nombre;
    }

    public int getCirculo() {
        return circulo;
    }

    public int getDistrito() {
        return distrito;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public Persona(String nombre, int circulo, int edificio){
        this.nombre = nombre;
        this.circulo = circulo;
        this.distrito = edificio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(nombre, persona.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return "\n\t\tPersona [" +
                "nombre = " + nombre +
                ']';
    }
}
