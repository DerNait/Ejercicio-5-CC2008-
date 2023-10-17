/*
Kevin Josué Villagrán Mérida - 23584
Ejercicio #5 
Fecha de creación: 13/10/2023 9:00
Fecha de ultima modificación: 17/10/2023 8:40
*/
public class Pasador extends Jugador{

    //Atributos
    private int pases;
    private int fintas;

    //Metodo constructor
    public Pasador(String nombre, String pais, int errores, int aces, int servicios, int pases, int fintas){
        super(nombre, pais, errores, aces, servicios);
        this.pases = pases;
        this.fintas = fintas;
    }

    //Metodo que calcula la efectividad 
    @Override
    public float calcularEfectividad(){
        try{
            efectividad = ((pases + fintas - errores)*100/(pases+fintas+errores))+aces*100/servicios;
        }
        catch(ArithmeticException e){
            efectividad = 0;
        }
        
        return efectividad;
    }

    //Este metodo es el que se overridea para escribir en el CSV, justo como lo explicaba en el comentario de la clase Jugador. 
    @Override
    public String getDatos(){
        return pases + ";" + fintas;
    }

    //toString para poder mostrar a detalle todos los datos del jugador.
    @Override
    public String toString(){
        return "\n===DATOS DEL JUGADOR PASADOR: " + nombre + "===" + 
        "\nNombre: " + nombre + 
        "\nPais de origen: " + pais + 
        "\nErrores: " + errores +
        "\nAces: " + aces +
        "\nTotal de servicios: " + servicios +
        "\nPases hechos: " + pases +
        "\nFintas hechas: " + fintas +
        "\nEfectividad: " + calcularEfectividad();
    }
}