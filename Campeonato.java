/*
Kevin Josué Villagrán Mérida - 23584
Ejercicio #5 
Fecha de creación: 13/10/2023 9:00
Fecha de ultima modificación: 17/10/2023 8:40
*/
import java.io.*;//Importamos librerias
import java.util.*;

public class Campeonato{

    public static void main(String[] args){
        ArrayList<Jugador> jugadores;//Se crea la unica lista de jugadores
        Scanner scan = new Scanner(System.in);//Se define el scanner

        jugadores = cargarCSV("jugadores.csv");//Se carga el CSV y los jugadores de este se meten a nuestra lista de jugadores.

        boolean salir = false;//Permite salir del programa
        String seleccion;

        System.out.println("\nBienvenido al sistema de registro del campeonato");

        while(!salir){//Menu principal
            System.out.println("\n=== MENU DE OPCIONES ===");
            System.out.println("¿Que desea hacer?");
            System.out.println("1. Registrar jugadores");           
            System.out.println("2. Mostrar todos los jugadores inscritos");
            System.out.println("3. Mostrar los 3 mejores liberos");   
            System.out.println("4. Mostrar los jugadores con mas del 80% de efectividad");
            System.out.println("5. Salir");

            seleccion = scan.nextLine();

            switch(seleccion){//Depende de la seleccion del jugador, se hace cada opcion del menu
                case "1":
                    registrarJugador(jugadores);
                    break;
                case "2":
                    mostrarJugadores(jugadores);
                    break;
                case "3":
                    mejoresLiberos(jugadores);
                    break;
                case "4":
                    mejoresPasadores(jugadores);
                    break;
                case "5":
                    guardarCSV(jugadores);
                    salir = true;
                    break;
                default://Si mete un valor invalido
                    System.out.println("Ingrese un valor numerico valido");
            }
        }
    }

    public static ArrayList<Jugador> cargarCSV(String path){
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();//Se crea la lista que contiene a los jugadores del CSV, esta sera la que se retornara

        try{//Excepcion para poder cargar el archivo sin que al fallar se interrumpa el programa
            Scanner scan = new Scanner(new File(path));
            scan.useDelimiter(";");

            if(scan.hasNextLine()){
                scan.nextLine();
            }

            while(scan.hasNextLine()){
                String[] jugadoresData = scan.nextLine().split(";");

                switch(jugadoresData[6]){//Se crean los objetos de jugadores segun el tipo que encuentren en el CSV
                    case "Libero":
                        Libero libero = new Libero(jugadoresData[0],jugadoresData[1],Integer.parseInt(jugadoresData[2]),Integer.parseInt(jugadoresData[3]),Integer.parseInt(jugadoresData[4]),Integer.parseInt(jugadoresData[7]));
                        jugadores.add(libero);
                        break;
                    case "Pasador":
                        Pasador pasador = new Pasador(jugadoresData[0],jugadoresData[1],Integer.parseInt(jugadoresData[2]),Integer.parseInt(jugadoresData[3]),Integer.parseInt(jugadoresData[4]),Integer.parseInt(jugadoresData[8]),Integer.parseInt(jugadoresData[9]));
                        jugadores.add(pasador);
                        break;
                    case "Auxiliar":
                        Auxiliar auxiliar = new Auxiliar(jugadoresData[0],jugadoresData[1],Integer.parseInt(jugadoresData[2]),Integer.parseInt(jugadoresData[3]),Integer.parseInt(jugadoresData[4]),Integer.parseInt(jugadoresData[10]),Integer.parseInt(jugadoresData[11]),Integer.parseInt(jugadoresData[12]));
                        jugadores.add(auxiliar);
                        break;
                    case "Opuesto":
                        Opuesto opuesto = new Opuesto(jugadoresData[0],jugadoresData[1],Integer.parseInt(jugadoresData[2]),Integer.parseInt(jugadoresData[3]),Integer.parseInt(jugadoresData[4]),Integer.parseInt(jugadoresData[10]),Integer.parseInt(jugadoresData[11]),Integer.parseInt(jugadoresData[12]));
                        jugadores.add(opuesto);
                        break;
                }
            }

            System.out.println("\nArchivo jugadores.csv cargado correctamente...");//En caso de que lo cargue correctamente
        }catch(Exception e){
            System.out.println("\nNo se pudo cargar el archivo de jugadores.csv");//En caso de que no
            System.out.println("Motivo: " + e);
        }

        return jugadores;
    }

    public static void registrarJugador(ArrayList<Jugador> jugadores){
        Scanner scan = new Scanner(System.in);
        
        //Atributos a definir de cada jugador
        String nombre;
        String pais;
        int errores = 0;
        int aces = 0;
        int servicios = 0;
        float efectividad; 
        int recibos = 0;
        int pases = 0;
        int fintas = 0;
        int ataques = 0;
        int bloqueosEfectivos = 0;
        int bloqueosFallidos = 0;

        String seleccion;
        boolean anException = false;//Servira para que el usuario entre en bucle si coloca valores erroneos.
        boolean selected = false;

        //Se pregunta por los datos
        System.out.println("=== REGISTRO DE JUGADORES ===");
        System.out.println("Ingrese el nombre del jugador");
        nombre = scan.nextLine();
        System.out.println("Ingrese el pais del jugador");
        pais = scan.nextLine();
        do{//Ciclo que se interrumpe solo si no hay un error en el dato que introduce el usuario
            System.out.println("Ingrese la cantidad de errores del jugador: ");
                try{
                    errores = Integer.parseInt(scan.nextLine());
                    anException = false;
                }catch(Exception e){
                    System.out.println("\nIntroduzca un valor numerico valido");
                    anException = true;
                }
        } while(anException);
        
        do{//Ciclo que se interrumpe solo si no hay un error en el dato que introduce el usuario
            System.out.println("Ingrese la cantidad de aces del jugador: ");
                try{
                    aces = Integer.parseInt(scan.nextLine());
                    anException = false;
                }catch(Exception e){
                    System.out.println("\nIntroduzca un valor numerico valido");
                    anException = true;
                }
        } while(anException);
        
        do{//Ciclo que se interrumpe solo si no hay un error en el dato que introduce el usuario
            System.out.println("Ingrese la cantidad de servicios del jugador: ");
                try{
                    servicios = Integer.parseInt(scan.nextLine());
                    anException = false;
                }catch(Exception e){
                    System.out.println("\nIntroduzca un valor numerico valido");
                    anException = true;
                }
        } while(anException);
        
        while(!selected){//Segun el tipo de jugador que se desea registrar, se crean los jugadores con los atributos definidos
            System.out.println("\n¿Que tipo de jugador es? 1. Libero, 2. Pasador, 3. Auxiliar, 4. Opuesto");
            seleccion = scan.nextLine();

            switch(seleccion){
                case "1":
                    System.out.println("\n=== JUGADOR LIBERO ===");
                    do{//Ciclo que se interrumpe solo si no hay un error en el dato que introduce el usuario
                        System.out.println("Ingrese la cantidad de recibos del jugador: ");
                            try{
                                recibos = Integer.parseInt(scan.nextLine());
                                anException = false;
                            }catch(Exception e){
                                System.out.println("\nIntroduzca un valor numerico valido");
                                anException = true;
                            }
                    } while(anException);    

                    Libero libero = new Libero(nombre, pais, errores, aces, servicios, recibos);
                    jugadores.add(libero);
                    selected = true;                   
                    break;
                case "2":
                    System.out.println("\n=== JUGADOR PASADOR ===");
                    do{//Ciclo que se interrumpe solo si no hay un error en el dato que introduce el usuario
                        System.out.println("Ingrese la cantidad de pases del jugador: ");
                            try{
                                pases = Integer.parseInt(scan.nextLine());
                                anException = false;
                            }catch(Exception e){
                                System.out.println("\nIntroduzca un valor numerico valido");
                                anException = true;
                            }
                    } while(anException); 
                    
                    do{//Ciclo que se interrumpe solo si no hay un error en el dato que introduce el usuario
                        System.out.println("Ingrese la cantidad de fintas del jugador: ");
                            try{
                                fintas = Integer.parseInt(scan.nextLine());
                                anException = false;
                            }catch(Exception e){
                                System.out.println("\nIntroduzca un valor numerico valido");
                                anException = true;
                            }
                    } while(anException);    

                    Pasador pasador = new Pasador(nombre, pais, errores, aces, servicios, pases, fintas);
                    jugadores.add(pasador);
                    selected = true;
                    break;
                case "3":
                    System.out.println("\n=== JUGADOR AUXILIAR ===");

                    do{//Ciclo que se interrumpe solo si no hay un error en el dato que introduce el usuario
                        System.out.println("Ingrese la cantidad de ataques que ha hecho el jugador: ");
                            try{
                                ataques = Integer.parseInt(scan.nextLine());
                                anException = false;
                            }catch(Exception e){
                                System.out.println("\nIntroduzca un valor numerico valido");
                                anException = true;
                            }
                    } while(anException);    

                    do{//Ciclo que se interrumpe solo si no hay un error en el dato que introduce el usuario
                        System.out.println("Ingrese la cantidad de bloqueos efectivos del jugador: ");
                            try{
                                bloqueosEfectivos = Integer.parseInt(scan.nextLine());
                                anException = false;
                            }catch(Exception e){
                                System.out.println("\nIntroduzca un valor numerico valido");
                                anException = true;
                            }
                    } while(anException);    

                    do{//Ciclo que se interrumpe solo si no hay un error en el dato que introduce el usuario
                        System.out.println("Ingrese la cantidad de bloqueos fallidos del jugador: ");
                            try{
                                bloqueosFallidos = Integer.parseInt(scan.nextLine());
                                anException = false;
                            }catch(Exception e){
                                System.out.println("\nIntroduzca un valor numerico valido");
                                anException = true;
                            }
                    } while(anException);    

                    Auxiliar auxiliar = new Auxiliar(nombre, pais, errores, aces, servicios, ataques, bloqueosEfectivos, bloqueosFallidos);
                    jugadores.add(auxiliar);
                    selected = true;
                    break;
                case "4":
                    System.out.println("\n=== JUGADOR OPUESTO ===");
                    do{//Ciclo que se interrumpe solo si no hay un error en el dato que introduce el usuario
                        System.out.println("Ingrese la cantidad de ataques que ha hecho el jugador: ");
                            try{
                                ataques = Integer.parseInt(scan.nextLine());
                                anException = false;
                            }catch(Exception e){
                                System.out.println("\nIntroduzca un valor numerico valido");
                                anException = true;
                            }
                    } while(anException);    

                    do{//Ciclo que se interrumpe solo si no hay un error en el dato que introduce el usuario
                        System.out.println("Ingrese la cantidad de bloqueos efectivos del jugador: ");
                            try{
                                bloqueosEfectivos = Integer.parseInt(scan.nextLine());
                                anException = false;
                            }catch(Exception e){
                                System.out.println("\nIntroduzca un valor numerico valido");
                                anException = true;
                            }
                    } while(anException);    

                    do{//Ciclo que se interrumpe solo si no hay un error en el dato que introduce el usuario
                        System.out.println("Ingrese la cantidad de bloqueos fallidos del jugador: ");
                            try{
                                bloqueosFallidos = Integer.parseInt(scan.nextLine());
                                anException = false;
                            }catch(Exception e){
                                System.out.println("\nIntroduzca un valor numerico valido");
                                anException = true;
                            }
                    } while(anException); 
                    
                    Opuesto opuesto = new Opuesto(nombre, pais, errores, aces, servicios, ataques, bloqueosEfectivos, bloqueosFallidos);
                    jugadores.add(opuesto);
                    selected = true;
                    break;
                default:
                    System.out.println("Ingrese un valor numerico valido");
            }
        }
    }

    public static void mostrarJugadores(ArrayList<Jugador> jugadores){
        System.out.println("\n=== TODOS LOS JUGADORES DEL TORNEO ===");
        for(Jugador jugador : jugadores)//Por cada jugador que haya en la lista, se muestran sus datos
            System.out.println(jugador);
    }

    public static void mejoresLiberos(ArrayList<Jugador> jugadores){
        ArrayList<Float> efectividades = new ArrayList<Float>();//Lista del valor de efectividad de cada libero
        ArrayList<Jugador> mejoresJugadores = new ArrayList<Jugador>();
        int cuenta = 0; 

        for(Jugador jugador : jugadores)//A cada libero en la lista, se obtiene su efectividad y esta se agrega a la lista de efectividades
            efectividades.add(jugador.calcularEfectividad());

        Collections.sort(efectividades);//Se ordena la lista de efectividad 

        for(int i = efectividades.size()-1; i >= 0; i--){//Compara las efectividades de los liberos con las que hay en la lista ordenada de liberos
            for(int j = 0; j < jugadores.size(); j++){
                if(jugadores.get(j).calcularEfectividad() == efectividades.get(i)){
                    if(!mejoresJugadores.contains(jugadores.get(j)))
                        mejoresJugadores.add(jugadores.get(j));//Si las efectividades coinciden, se agrega este libero a la lista de mejores liberos
                }
            }
        }

        System.out.println("\n=== Los tres mejores liberos del torneo ===");//Se imprimen los tres mejores liberos si es que hay liberos registrados
        if(mejoresJugadores.size() > 0){
            for(int i = 0; i < mejoresJugadores.size(); i++){
                if(mejoresJugadores.get(i) != null)
                    if(mejoresJugadores.get(i) instanceof Libero && cuenta < 3){
                        System.out.println(mejoresJugadores.get(i));
                        cuenta++;
                    }
            }
        }
        else
            System.out.println("\nNo hay liberos registrados...");//Si no hay liberos registrados
    }

    public static void mejoresPasadores(ArrayList<Jugador> jugadores){
        int pasadores = 0;

        for(Jugador jugador : jugadores){//Por cada jugador en la lista que sea un Pasador, se calcula que su efectividad sea de mas del 80% (160)
            if(jugador instanceof Pasador){
                if(jugador.calcularEfectividad() > 160)
                    pasadores++;//Se suma la cantidad de pasadores en caso de que si cumpla
            }
        }

        System.out.println("\nCantidad de jugadores pasadores con mayor del 80% de efectividad: " + pasadores);//Muestra la cantidad de los mejores pasadores
    }

    public static void guardarCSV(ArrayList<Jugador> jugadores){
        
        File archivoCSV = new File("jugadores.csv");//Se sobreescribe el archivo CSV

        try{
            PrintWriter out = new PrintWriter(archivoCSV);
            //Los titulos del CSV se definen
            String titulos[] = {"nombre","pais","errores","aces","servicios","efectividad","tipo","recibos","pases","fintas","ataques","bloqueos_efectivos","bloqueos_fallidos"};
        
            for(String titulo : titulos)
                out.print(titulo + ";");//Se escriben los titulos
            
            out.println();

            for(Jugador jugador : jugadores){//Se escriben los datos de cada jugador, segun su tipo en sus casillas conrespondiente del CSV
                if(jugador instanceof Libero){
                    out.println(jugador.getDatosBasicos()+";"+"Libero"+";"+jugador.getDatos()); 
                }
                else if(jugador instanceof Pasador){
                    out.println(jugador.getDatosBasicos()+";"+"Pasador"+";"+";"+jugador.getDatos());
                }
                else if(jugador instanceof Auxiliar){
                    out.println(jugador.getDatosBasicos()+";"+"Auxiliar"+";"+";"+";"+";"+jugador.getDatos());
                }
                else if(jugador instanceof Opuesto){
                    out.println(jugador.getDatosBasicos()+";"+"Opuesto"+";"+";"+";"+";"+jugador.getDatos());
                }
            }

            out.close();//Se cierra para indicar que ya no se escribira mas y se guarda el archivo actualizado

            System.out.println("\nArchivo " + archivoCSV + " creado correctamente");//En caso de que se guarde bien
        } catch(FileNotFoundException e){
            System.out.println("No se ha encontrado el archivo");//En caso de que no encuentre el archivo (O que este en uso)
        }
    }
}