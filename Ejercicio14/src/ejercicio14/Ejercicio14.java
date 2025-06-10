package ejercicio14;

import ejercicio14.lib.GrafoM;
import ejercicio14.lib.VerticeCosto;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Ejercicio14 {
    
    public static void main(String[] args) {
        GrafoM redAgua = new GrafoM(10);
        Scanner scanner = new Scanner(System.in);
        ArrayList costosEIntermedios = new ArrayList<>();
        Boolean cambios = true;
        String input;
               
        do {
            makeMenu();
            input = scanner.nextLine();
            
            switch (input) {
                case "1" -> {
                    // Inicializar agrega vertices (bombas) de forma interactiva
                    // Amplia el arreglo si es necesario.
                    redAgua.inicializar("Zona", "Zonas");
                    cambios = true;
                }
                case "2" -> {
                    redAgua.dibujarMatriz(true);
                    System.out.print("Zona origen: ");
                    String origen = scanner.nextLine();
                    System.out.print("Zona destino: ");
                    String destino = scanner.nextLine();
                    System.out.print("Capacidad del canal: ");
                    int capacidad = scanner.nextInt();
                    scanner.nextLine(); // limpiar buffer
                    try {
                        redAgua.agregarArco(origen, destino, capacidad);
                        redAgua.dibujarMatriz(true);
                        System.out.println("Canal agregado con éxito.");
                    } catch (UnsupportedOperationException e) {
                        System.out.println("Error: " + e.getMessage());
                    } finally {
                        cambios = true;
                        
                        System.out.printf("%n%nPresione ENTER para continuar...%n");
                        scanner.nextLine();
                    }
                }
                case "3" -> {
                    // Eliminar zona
                    redAgua.dibujarMatriz(true);
                    System.out.print("Zona a elminar: ");
                    String zona = scanner.nextLine();
                    boolean exito = redAgua.eliminarVertice(zona);
                    
                    if (exito) {
                        System.out.printf("Zona %s eliminada con exito", zona);
                    } else {
                        System.out.printf("Zona %s no encontrada", zona);
                    }
                    
                    cambios = true;
                    System.out.printf("%n%nPresione ENTER para continuar...%n");
                    scanner.nextLine();
                }
                case "4" -> {
                    // Eliminar canal
                    redAgua.dibujarMatriz(true);
                    System.out.print("Zona origen: ");
                    String origen = scanner.nextLine();
                    System.out.print("Zona destino: ");
                    String destino = scanner.nextLine();
                    
                    try {
                        redAgua.eliminarArco(origen, destino);
                        System.out.printf("Canal %s --> %s eliminado", origen, destino);
                    } catch (UnsupportedOperationException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        cambios = true;
                        System.out.printf("%n%nPresione ENTER para continuar...%n");
                        scanner.nextLine();
                    }
                }
                case "5" -> {
                    // Mostrar red
                    redAgua.dibujarMatriz(true);
                    
                    System.out.printf("%n%nPresione ENTER para continuar...%n");
                    scanner.nextLine();
                }
                case "6" -> {
                    // Capacidad bomba a bomba
                    redAgua.dibujarMatriz(true);
                    System.out.print("Zona origen: ");
                    String origen = scanner.nextLine();
                    System.out.print("Zona destino: ");
                    String destino = scanner.nextLine();
                    
                    System.out.printf("Flujo máximo: %d%n", redAgua.dijkstraInvertido(origen, destino));
                    
                    System.out.printf("%n%nPresione ENTER para continuar...%n");
                    scanner.nextLine();
                }
                case "7" -> {
                    // Capacidad hasta bomba
                    redAgua.dibujarMatriz(true);
                    System.out.print("Zona origen: ");
                    String origen = scanner.nextLine();
                    
                    // Calculamos el maximo flujo de todos los elementos a origen.
                    LinkedList<VerticeCosto> flujos = redAgua.maximoFlujoA(origen);
                    
                    System.out.println("Capacidad maxima de cualquier bomba a la actual:");
                    for (VerticeCosto x: flujos) {
                        System.out.printf("|%10s:%-10d|%n", x.getVertice(), x.getCosto());
                    }
                    
                    System.out.printf("%n%nPresione ENTER para continuar...%n");
                    scanner.nextLine();
                }
                case "8" -> {
                    // Camino capacidad maxima a bomba
                    redAgua.dibujarMatriz(true);
                    System.out.print("Zona origen: ");
                    String origen = scanner.nextLine();
                    System.out.print("Zona destino: ");
                    String destino = scanner.nextLine();
                    
                    if (cambios) { //floydWarshall es O(n^3)!!!
                        costosEIntermedios = redAgua.floydWarshallMaximos();
                        cambios = true;
                    }
                    
                    // Obtenemos los intermedios del floydWarshall
                    Integer[][] intermedios = (Integer[][]) costosEIntermedios.get(1);
                    
                    // Usamos los intermedios para construir el camino.
                    ArrayList camino = redAgua.getCamino(intermedios, origen, destino);
                    
                    // Dibujamos el camino.
                    System.out.println(String.join(" --> ", camino));
                    
                    
                    System.out.printf("%n%nPresione ENTER para continuar...%n");
                    scanner.nextLine();
                }
                case "9" -> {
                    redAgua.dibujarMatriz(true);
                    System.out.print("Zona origen: ");
                    String origen = scanner.nextLine();

                    LinkedList<VerticeCosto> flujos = redAgua.maximoFlujoDesde(origen);
                    
                    System.out.println("Capacidad maxima de la bomba a las demas:");
                    for (VerticeCosto x: flujos) {
                        System.out.printf("|%10s:%-10d|%n", x.getVertice(), x.getCosto());
                    }
                    
                }
                case "10" -> {
                    System.exit(0);
                }
                default -> {
                    System.out.println("Ingreso incorrecto.");
                    System.out.printf("%n%nPresione ENTER para continuar...%n");
                    scanner.nextLine();
                }
            }
        } while (!input.startsWith("9"));
    }
    
    public static void makeMenu() {
        System.out.println("======== MENU ========");
        System.out.println("=> 1 - Agregar zonas"); //Usando GrafoM.inicializar() ??
        System.out.println("=> 2 - Agregar canal");
        System.out.println("=> 3 - Eliminar zona");
        System.out.println("=> 4 - Eliminar canal");
        System.out.println("=> 5 - Mostrar red");
        System.out.println("=> 6 - Capacidad zona a zona"); //Encontrar mejor nombre
        System.out.println("=> 7 - Capacidad hasta zona"); //Encontrar mejor nombre
        System.out.println("=> 8 - Camino capacidad maxima zona a zona"); // floydWarshall() y getCamino()
        System.out.println("=> 9 - Caminos de maxima capacidad desde zona");
        System.out.println("=> 10 - Salir");
        System.out.println("======================");
        System.out.print("Ingrese Opcion: ");
    }
}
