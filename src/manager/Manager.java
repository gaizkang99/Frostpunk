package manager;

import dao.Reader;
import exceptions.LogicExceptions;
import model.Circle;
import model.OrdenaCirculoDistrito;
import model.Persona;
import utils.Edificios;

import java.util.*;

public class Manager {

    private Reader reader;
    private ArrayList<Circle> circulos;
    private static int calor;
    private HashSet<Persona> personas;

    public Manager(){
        reader = new Reader("Data/entrada.txt");
        calor = 0;
        personas = new HashSet<Persona>();
        circulos = new ArrayList<Circle>();
    }

    public static int getCalor() {
        return calor;
    }

    public void init() {
        anadirCirculos();
        lectura(reader);
    }

    public void lectura(Reader r) {
        String linea="";
        while((linea=r.read())!=null){
            try{
                acciones(linea);
            }catch(LogicExceptions l){
                System.out.println("Error: " + linea + " " + l.getMessage());
            }
        }
    }

    //Genera los circulos del 0 al 4
    public void anadirCirculos(){
        for (int i=0; i<=4; i++){
            Circle c = new Circle();
            circulos.add(c);
        }
    }

    //Recibe la lectura del fichero txt y resuelve por casos el fichero de acciones
    public void acciones(String linea) throws LogicExceptions {
        switch(linea.charAt(0)){
            case 'T':
                caso1(linea);
                break;
            case '<':
                caso2(linea);
                break;
            case '+':
                caso3(linea);
                break;
            case '>':
                caso4(linea);
                break;
            case 'S':
                muestraResidentesRefugios();
                break;
            case 'C':
                muestraInfoCiudad();
                break;
        }
    }

    //Marca la temperatura inicial recibida por parámetro por el fichero de acciones
    public void caso1(String linea) throws LogicExceptions {
        String[] accionT = linea.split(" ");
        compruebaTamano(accionT.length, 2);
        calor = Integer.parseInt(accionT[1]);
    }

    //Recibe la línea para construir un edificio según un distrito y un círculo
    public void caso2(String linea) throws LogicExceptions{
        linea = linea.replace("<", "").replace(">", "");
        String[] accionEdificio = linea.split(" ");
        compruebaTamano(accionEdificio.length,2);
        construirEdificio(accionEdificio);
    }

    //Recibe la línea para colocar una persona en un edificio según un distrito y un círculo
    public void caso3(String linea) throws LogicExceptions{
        linea = linea.replace("+ ","").replace("<", "").replace(">", "").replace(",", " ");
        String[] accionesPersonas = linea.split(" ");
        compruebaTamano(accionesPersonas.length, 3);
        anadirPersona(accionesPersonas);
    }

    //Recibe por parámetro las coordenadas para enviar una persona a un trabajo. Es decir, cambiarla de edificio
    public void caso4(String linea) throws LogicExceptions{
        linea = linea.replace("<", "").replace(">", "");
        String[] accionesCambio = linea.split(" ");
        compruebaTamano(accionesCambio.length, 3);
        accionMovimiento(accionesCambio);
    }

    //Comprueba el tamaño que tiene que tener una acción y el tamaño que tiene
    public void compruebaTamano(int i, int j) throws LogicExceptions {
        if (i != j)throw new LogicExceptions(LogicExceptions.Parametros_Incorrectos);
    }

    //Recibe una localización y construye un edificio según el nombre que recibe
    public void construirEdificio(String[] accion) throws LogicExceptions {
        String[] accion2 = accion[0].split(",");
        String nombreEdificio = accion[1];
        int circulo = Integer.parseInt(accion2[0]);
        int distrito = Integer.parseInt(accion2[1]);

        if(circulos.get(circulo).getSecciones().get(distrito)==null){
            circulos.get(circulo).meterEdificio(distrito, Edificios.crearEdificio(nombreEdificio));
            System.out.println("Edificio añadido");
        }else throw new LogicExceptions(LogicExceptions.Parcela_Llena);
    }

    //Añade una persona a un edificio, recibiendo por parametros las coordenadas de este edificio
    public void anadirPersona(String[] accionPersona) throws LogicExceptions {
        String nombrePersona = accionPersona[0];
        int circulo = Integer.parseInt(accionPersona[1]);
        int distrito = Integer.parseInt(accionPersona[2]);
        Persona p = new Persona(nombrePersona, circulo, distrito);

        if(circulos.get(circulo).getSecciones().get(distrito)!=null){
            if(circulos.get(circulo).getSecciones().get(distrito).getNombre().equalsIgnoreCase("refugio")){
                if(personas.add(p)){
                    circulos.get(circulo).getSecciones().get(distrito).insertarPersona(p);
                }else throw new LogicExceptions(LogicExceptions.Persona_no_anadida);
            }
        }else throw new LogicExceptions(LogicExceptions.Parcela_Vacia);

    }

    //Según las dobles coordenadas recibidas coloca a una persona en otro edificio para realizar un trabajo. Se tendrá
    // que realizar una serie de comprobaciones.
    public void accionMovimiento(String[] movimiento) throws LogicExceptions {
        String[] origen = movimiento[1].split(",");
        String[] destino = movimiento[2].split(",");

        int circuloOrigen = Integer.parseInt(origen[0]);
        int distritoOrigen = Integer.parseInt(origen[1]);
        int circuloDestino = Integer.parseInt(destino[0]);
        int distritoDestino = Integer.parseInt(destino[1]);
        String edificioOrigen = circulos.get(circuloOrigen).getSecciones().get(distritoOrigen).getNombre();
        String edificioDestino = circulos.get(circuloDestino).getSecciones().get(distritoDestino).getNombre();
        ArrayList<Persona> pers = new ArrayList<>(circulos.get(circuloOrigen).getSecciones().get(distritoOrigen).getPersonas());

        if(edificioOrigen.equalsIgnoreCase(Edificios.REFUGIO.getNombre())) {
            if(edificioDestino.equalsIgnoreCase(Edificios.REFUGIO.getNombre())) throw new LogicExceptions (LogicExceptions.Refugio_Repetido);
            if(edificioDestino.equalsIgnoreCase(Edificios.MOTORVAPOR.getNombre())) throw new LogicExceptions (LogicExceptions.Maquina_Vapor);
            else {
                Persona p = pers.get(0);
                if(!(p.isOcupado())){
                    circulos.get(circuloDestino).getSecciones().get(distritoDestino).insertarPersona(p);
                    p.setOcupado(true);
                    throw new LogicExceptions (LogicExceptions.Trabajando);
                }else throw new LogicExceptions(LogicExceptions.Persona_Ocupada);
            }
        }else throw new LogicExceptions (LogicExceptions.No_Refugio);

    }

    //Muestra los personajes que hay en juego y donde estan colocados cada uno
    public void muestraResidentesRefugios(){
        System.out.println("\n---  MOSTRANDO PERSONAS  ---");

        ArrayList<Persona> pers = new ArrayList<>(personas);
        pers.sort(new OrdenaCirculoDistrito());

        for(Persona p : pers){
            System.out.println("Persona: [nombre: " + p.getNombre() + ", circle: " + p.getCirculo() + ", distrito: " + p.getDistrito());
        }
        System.out.println();
    }

    //Muestra la información de todos los circulos y distritos
    public void muestraInfoCiudad(){
        System.out.println();
        System.out.println("--- Mostrando Ciudad ---");
        for(int i=0; i<this.circulos.size(); i++) {
            System.out.println("Circulo "+ i);
            if(circulos.get(i).getSecciones()!=null) {
                String distrito = circulos.get(i).getSecciones().toString();
                String distritoTabulado = distrito.replace("{", "").replace("}", "\n\t").replace(":", "").replace(",", "").replace("null", " calor: "+calor+" Edificio: "+"vacío\n\t");
                System.out.println("\t " + distritoTabulado);
            }
        }
        System.out.println("---------------------------------------\n");

    }
}
