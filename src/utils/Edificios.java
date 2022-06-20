package utils;

import model.Edificio;

public enum Edificios {

    REFUGIO("Refugio", 1, 1), COCINA("Cocina", 0, 1), ASERRADERO("Aserradero", -2, 1),
    CABANACAZA("Cabaña Caza", -2, 0), MINACARBON("Mina Carbón", -2, 0), MOTORVAPOR("Motor Vapor", -5, 3),
    PUESTOMEDICO("Puesto Médico", 1, 1);

    private String nombre;
    private int calorMinimo;
    private int calorInicio;

    Edificios(String nombre, int cm, int ci) {
        this.nombre = nombre;
        this.calorMinimo = cm;
        this.calorInicio = ci;
    }

    public static Edificio crearEdificio(String nombreEdificio) {
        switch (nombreEdificio) {
            case "Refugio":
                return new Edificio(REFUGIO.nombre, REFUGIO.calorMinimo, REFUGIO.calorInicio);
            case "Cocina":
                return new Edificio(COCINA.nombre, COCINA.calorMinimo, COCINA.calorInicio);
            case "Aserradero":
                return new Edificio(ASERRADERO.nombre, ASERRADERO.calorMinimo, ASERRADERO.calorInicio);
            case "CabanaCaza":
                return new Edificio(CABANACAZA.nombre, CABANACAZA.calorMinimo, CABANACAZA.calorInicio);
            case "MinaCarbon":
                return new Edificio(MINACARBON.nombre, MINACARBON.calorMinimo, MINACARBON.calorInicio);
            case "MotorVapor":
                return new Edificio(MOTORVAPOR.nombre, MOTORVAPOR.calorMinimo, MOTORVAPOR.calorInicio);
            case "PuestoMedico":
                return new Edificio(PUESTOMEDICO.nombre, PUESTOMEDICO.calorMinimo, PUESTOMEDICO.calorInicio);
            default:
                return null;
        }
    }

    public String getNombre() {
        return nombre;
    }

}
