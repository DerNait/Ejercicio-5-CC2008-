/*
Kevin Josué Villagrán Mérida - 23584
Ejercicio #5 
Fecha de creación: 13/10/2023 9:00
Fecha de ultima modificación: 17/10/2023 8:40
*/
public abstract class Jugador{

    //Atributos del jugador
    protected String nombre;
    protected String pais;
    protected int errores;
    protected int aces;
    protected int servicios;
    protected float efectividad; 

    //Metodo constructor
    public Jugador(String nombre, String pais, int errores, int aces, int servicios){
        this.nombre = nombre;
        this.pais = pais;
        this.errores = errores;
        this.aces = aces;
        this.servicios = servicios;
    }
    
    /*Este metodo originalmente no estaba en el analisis y diseño de mi programa porque aun no recordaba del todo bien el como
    funcionaba todo el sistema para escribir CSVs, pues este necesita de todos los datos del jugador para colocarlos, entonces
    para evitar poner varios metodos getters, solo puse uno que fuera especificamente usado en la creacion del CSV, pues
    no necesito de getters en ninguna otra parte de mi programa, por esa razon este cambio que era necesario para tener mayor
    comodidad al escribir CSVs.*/
    public String getDatosBasicos(){
        return nombre + ";" + pais + ";" + errores + ";" + aces + ";" + servicios + ";" + calcularEfectividad();
    }

    /*Por lo mismo del CSV, este metodo no estaba inicialmente en mi analisis y diseño, este, permite que cada subclase pueda
    retornar todos sus datos para escribirlos en el CSV, y como cada uno tiene algo diferente, es por eso que este metodo solo
    se hereda pero se define y overridea en las subclases.*/
    public abstract String getDatos();

    //Metodo para obtener la efectividad en cada subclase, se define en estas segun el tipo de jugador. 
    public abstract float calcularEfectividad(); 

    //toString para poder mostrar a detalle todos los datos del jugador.
    @Override
    public String toString(){
        return "\n===DATOS DEL JUGADOR: " + nombre + "===" + 
        "\nNombre: " + nombre + 
        "\nPais de origen: " + pais + 
        "\nErrores: " + errores +
        "\nAces: " + aces +
        "\nTotal de servicios: " + servicios;
    }
}