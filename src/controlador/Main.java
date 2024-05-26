package controlador;

import java.util.Scanner;

import servicios.Logica;

/**
 * Clase principal que inicia la aplicación de gestión de incidencias.
 * 
 * @author Irene
 */
public class Main {
	
	/**
     * Método principal que ejecuta la aplicación.
     * 
     * @param args Argumentos de la línea de comandos.
     */
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int opcion = 0;

		Interfaz.menuBienvenida();

		do {
			Interfaz.menu();
			try {
				opcion = sc.nextInt();
				sc.nextLine();
				Logica.relizaOpcion(opcion);
			} catch (Exception e) {
				System.out.println("\nDebes de introducir un valor entre 1 y 11.");
			}
		} while (opcion != 11);
		
		System.out.println("¡Nos vemos!");
		
		sc.close();
	}

}
