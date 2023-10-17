/*
Kevin Josué Villagrán Mérida - 23584
Ejercicio #5 
Fecha de creación: 13/10/2023 9:00
Fecha de ultima modificación: 17/10/2023 8:40
*/
public class Auxiliar extends Jugador{

    //Atributos
    private int ataques;
    private int bloqueosEfectivos;
    private int bloqueosFallidos;

    //Metodo constructor
    public Auxiliar(String nombre, String pais, int errores, int aces, int servicios, int ataques, int bloqueosEfectivos, int bloqueosFallidos){
        super(nombre, pais, errores, aces, servicios);
        this.ataques = ataques;
        this.bloqueosEfectivos = bloqueosEfectivos;
        this.bloqueosFallidos = bloqueosFallidos;
    }

    //Metodo que calcula la efectividad 
    @Override
    public float calcularEfectividad(){
        try{
            efectividad = ((ataques + bloqueosEfectivos - bloqueosFallidos - errores)*100/(ataques+bloqueosEfectivos+bloqueosFallidos+errores))+aces*100/servicios;
        }
        catch(ArithmeticException e){
            efectividad = 0;
        }
        
        return efectividad;
    }

    //Este metodo es el que se overridea para escribir en el CSV, justo como lo explicaba en el comentario de la clase Jugador. 
    @Override
    public String getDatos(){
        return ataques + ";" + bloqueosEfectivos + ";" + bloqueosFallidos;
    }

    //toString para poder mostrar a detalle todos los datos del jugador.
    @Override
    public String toString(){
        return "\n===DATOS DEL JUGADOR AUXILIAR: " + nombre + "===" + 
        "\nNombre: " + nombre + 
        "\nPais de origen: " + pais + 
        "\nErrores: " + errores +
        "\nAces: " + aces +
        "\nTotal de servicios: " + servicios +
        "\nAtaques hechos: " + ataques +
        "\nBloqueos efectivos: " + bloqueosEfectivos +
        "\nBloqueos fallidos: " + bloqueosFallidos +
        "\nEfectividad: " + calcularEfectividad();
    }
}