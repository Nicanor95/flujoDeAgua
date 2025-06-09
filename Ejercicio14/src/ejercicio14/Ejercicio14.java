package ejercicio14;

import ejercicio14.lib.GrafoM;
import java.util.Scanner;

public class Ejercicio14 {
    
    public static void main(String[] args) {
        GrafoM redAgua = new GrafoM(10);
        Scanner scanner = new Scanner(System.in);
        String input;
        
        do {
            makeMenu();
            input = scanner.nextLine();
            
            switch (input) {
                case "1" -> {
                    // Inicializar agrega vertices (bombas) de forma interactiva
                    // Amplia el arreglo si es necesario.
                    redAgua.inicializar();
                }
                case "2" -> {
                    // Agregar canal
                }
                case "3" -> {
                    // Mostrar red
                    redAgua.dibujarMatriz(true);
                }
                case "4" -> {
                    // Capacidad bomba a bomba
                }
                case "5" -> {
                    // Capacidad hasta bomba?
                }
                case "6" -> {
                    // Camino capacidad maxima a bomba?
                }
                case "7" -> {
                    System.exit(0);
                }
                default -> {
                    System.out.println("Ingreso incorrecto.");
                }
            }
        } while (!input.startsWith("7"));
    }
    
    public static void makeMenu() {
        System.out.println("======== MENU ========");
        System.out.println("=> 1 - Agregar bombas"); //Usando GrafoM.inicializar() ??
        System.out.println("=> 2 - Agregar canal");
        System.out.println("=> 3 - Mostrar red");
        System.out.println("=> 4 - Capacidad bomba a bomba"); //Encontrar mejor nombre
        System.out.println("=> 5 - Capacidad hasta bomba"); //Encontrar mejor nombre
        System.out.println("=> 6 - Camino capacidad maxima bomba a bomba"); // floydWarshall() y getCamino()
        System.out.println("=> 7 - Salir");
        System.out.println("======================");
        System.out.print("Ingrese Opcion: ");
    }
}
