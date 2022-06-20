package exceptions;

import java.util.Arrays;
import java.util.List;

public class LogicExceptions extends Exception {
    public static final int Parametros_Incorrectos = 0;
    public static final int Parcela_Llena = 1;
    public static final int Parcela_Vacia = 2;
    public static final int Persona_no_anadida = 3;
    public static final int Refugio_Repetido = 4;
    public static final int Maquina_Vapor = 5;
    public static final int Trabajando = 6;
    public static final int No_Refugio = 7;
    public static final int Persona_Ocupada = 8;


    private int value;

    public LogicExceptions (int value) {
        this.value = value;
    }

    private List<String> message = Arrays.asList(
            "<< Numero de parámetros incorrecto >>",
            "<< La parcela dónde quieres construir está llena >>",
            "<< La parcela dónde quieres añadir a la persona está vacía >>",
            "<< La persona no se ha podido añadir >>",
            "<< No se puede mover de un refugio a otro >>",
            "<< No se puede mover a un edificio máquina de vapor >>",
            "<< La persona se ha asignado al trabajo >>",
            "<< No se le puede asignar un trabajo si no está en un refugio >>",
            "<< La persona seleccionada ya esta ocupada >>"
    );


    @Override
    public String getMessage() {
        return message.get(value);
    }
}