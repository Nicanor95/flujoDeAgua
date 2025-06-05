package ejercicio14;

import java.util.Scanner;

public class Ejercicio14 {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        
        do {
            makeMenu();
            input = scanner.nextLine();
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
