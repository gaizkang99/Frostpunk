package model;

import exceptions.LogicExceptions;
import manager.Manager;

import java.util.HashSet;

public class Edificio {
    private String nombre;
    private int calorMinimo;
    private int calorInicio;
    private HashSet<Persona> personas;

    public Edificio(String nombre, int calorMinimo, int calorInicio) {
        this.nombre = nombre;
        this.calorMinimo = calorMinimo;
        this.calorInicio = calorInicio;
        this.personas = new HashSet<Persona>();
    }

    public String getNombre() {
        return nombre;
    }

    public HashSet<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(HashSet<Persona> personas) {
        this.personas = personas;
    }

    public void insertarPersona(Persona p) throws LogicExceptions {
        if (personas.add(p)) {
            System.out.println("Se ha añadido la persona al edificio");
        } else throw new LogicExceptions(LogicExceptions.Persona_no_anadida);
    }

    @Override
    public String toString() {
        return  "calor: " + (calorInicio + Manager.getCalor()) +
                " Edificio: " + nombre + " " + personas + "\n\t";
    }
}
