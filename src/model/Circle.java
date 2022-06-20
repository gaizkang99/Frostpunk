package model;

import utils.Edificios;

import java.util.TreeMap;

public class Circle {
    private TreeMap<Integer, Edificio> secciones;

    public Circle() {
        this.secciones = new TreeMap<Integer, Edificio>();
        for (int i = 1; i <= 10; i++) {
            secciones.put(i, null);
        }
    }

    public TreeMap<Integer, Edificio> getSecciones() {
        return secciones;
    }

    public void meterEdificio(int i, Edificio edificio) {
        this.secciones.putIfAbsent(i, edificio);
    }

    @Override
    public String toString() {
        return "Seccion: " + secciones + "" + " \n\t";
    }
}