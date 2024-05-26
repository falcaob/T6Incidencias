package controlador;

import java.time.LocalDate;
import java.util.Scanner;

import persistencia.Incidencia;
import servicios.Logica;

/**
 * Clase que maneja la interacción con el usuario. Esta clase contiene métodos
 * para mostrar menús, solicitar datos al usuario y llamar a los métodos de la
 * lógica de la aplicación para gestionar incidencias.
 * 
 */
public class Interfaz {

	private static Scanner sc = new Scanner(System.in); // Un solo Scanner estático para toda la clase

	/**
	 * Muestra el mensaje de bienvenida al usuario.
	 */
	public static void menuBienvenida() {
		System.out.println("GESTIÓN DE INCIDENCIAS");
		System.out.println("**********************");
		System.out.println("Bienvenid@ a la app de gestión de incidencias.\nElija las opciones correspondientes.");
	}

	/**
	 * Muestra el menú principal de opciones al usuario.
	 */
	public static void menu() {
		System.out.println("\nMENÚ DE OPCIONES");
		System.out.println("****************");
		System.out.println("1. Registrar incidencia");
		System.out.println("2. Buscar incidencia");
		System.out.println("3. Modificar incidencia");
		System.out.println("4. Eliminar incidencia");
		System.out.println("5. Resolver incidencia");
		System.out.println("6. Modificar incidencia resuelta");
		System.out.println("7. Devolver incidencia resuelta");
		System.out.println("8. Mostrar incidencias pendientes");
		System.out.println("9. Mostrar incidencias resueltas");
		System.out.println("10. Mostrar incidencias eliminadas");
		System.out.println("11. Salir");
	}

	/**
	 * Espera hasta que el usuario pulse Intro.
	 */
	public static void esperaIntro() {
		sc.nextLine();
		System.out.println("\nPulsa Enter para continuar ...");
		sc.nextLine();
	}

	/**
	 * Solicita al usuario los datos necesarios para registrar una nueva incidencia.
	 * 
	 * @return La nueva incidencia creada con los datos ingresados por el usuario.
	 */
	public static Incidencia altaIncidencia() {
		Incidencia nuevaIncidencia = new Incidencia(0, null);

		System.out.println("\nREGISTRO INCIDENCIA");
		System.out.println("Indica el puesto:");
		nuevaIncidencia.setPuesto(sc.nextInt());
		sc.nextLine(); // cerrar búffer
		System.out.println("Indica el problema:");
		nuevaIncidencia.setProblema(sc.nextLine());

		// Mostrar los detalles de la nueva incidencia
		System.out.println("\nDATOS NUEVA INCIDENCIA");
		System.out.println("Código: " + nuevaIncidencia.getCodigo());
		System.out.println("Estado: " + nuevaIncidencia.getEstado());
		System.out.println("Puesto: " + nuevaIncidencia.getPuesto());
		System.out.println("Problema: " + nuevaIncidencia.getProblema());

		return nuevaIncidencia;
	}

	/**
	 * Solicita al usuario que introduzca un código de incidencia y llama al método
	 * de búsqueda en Logica.
	 */
	public static void buscarIncidenciaPorCodigo() {
		System.out.println("\nIntroduce el código de la incidencia:");
		String codigo = sc.nextLine();
		Incidencia incidenciaEncontrada = Logica.buscarIncidenciaParaModificar(codigo);
		System.out.println(incidenciaEncontrada);
	}

	/**
	 * Solicita al usuario los datos necesarios para modificar una incidencia
	 * existente.
	 */
	public static void modificarIncidencia() {
		System.out.println("\nMODIFICACIÓN DE INCIDENCIA");
		System.out.println("Introduce el código de la incidencia a modificar:");
		String codigo = sc.nextLine();

		// Buscar la incidencia por código
		Incidencia incidencia = Logica.buscarIncidenciaParaModificar(codigo);

		if (incidencia != null) {
			System.out.println(incidencia);
			System.out.println("Incidencia encontrada. Introduce los nuevos datos:");

			System.out.println("Nuevo puesto:");
			int nuevoPuesto = sc.nextInt();
			incidencia.setPuesto(nuevoPuesto);
			sc.nextLine();

			System.out.println("Nuevo problema:");
			String nuevoProblema = sc.nextLine();
			incidencia.setProblema(nuevoProblema);

			// Modificar la incidencia
			Logica.modificarIncidencia(incidencia);
			System.out.println(incidencia);
		} else {
			System.out.println("No se encontró ninguna incidencia pendiente con el código: " + codigo);
		}
	}

	/**
	 * Solicita al usuario los datos necesarios para eliminar una incidencia.
	 */
	public static void eliminarIncidencia() {
		System.out.println("\nELIMINACIÓN DE INCIDENCIA");
		System.out.println("Introduce el código de la incidencia a eliminar:");
		String codigo = sc.nextLine();

		System.out.println("Introduce la causa de la eliminación:");
		String causaEliminacion = sc.nextLine();

		// Eliminar la incidencia
		Logica.eliminarIncidencia(codigo, causaEliminacion);
	}

	/**
	 * Solicita al usuario los datos necesarios para resolver una incidencia.
	 */
	public static void resolverIncidencia() {
		System.out.println("\nRESOLUCIÓN DE INCIDENCIA");
		System.out.println("Introduce el código de la incidencia a resolver:");
		String codigo = sc.nextLine();

		System.out.println("Introduce la descripción de la resolución:");
		String descripcionResolucion = sc.nextLine();

		// Resolver la incidencia
		Logica.resolverIncidencia(codigo, descripcionResolucion);
	}

	/**
	 * Solicita al usuario los datos necesarios para modificar una incidencia
	 * resuelta.
	 */
	public static void modificarIncidenciaResuelta() {
		System.out.println("\nMODIFICACIÓN DE INCIDENCIA RESUELTA");
		System.out.println("Introduce el código de la incidencia a modificar:");
		String codigo = sc.nextLine();

		// Buscar la incidencia por código
		Incidencia incidencia = Logica.buscarIncidenciaResuelta(codigo);

		if (incidencia != null) {
			System.out.println("Incidencia resuelta encontrada. Introduce los nuevos datos:");

			System.out.println("Nueva fecha de resolución (YYYY-MM-DD):");
			LocalDate nuevaFechaResolucion = LocalDate.parse(sc.nextLine());

			System.out.println("Nueva descripción de la resolución:");
			String nuevaResolucion = sc.nextLine();

			// Modificar la incidencia resuelta
			Logica.modificarIncidenciaResuelta(codigo, nuevaFechaResolucion, nuevaResolucion);
		} else {
			System.out.println("No se encontró ninguna incidencia resuelta con el código: " + codigo);
		}
	}

	/**
	 * Solicita al usuario los datos necesarios para devolver una incidencia
	 * resuelta a la lista de pendientes.
	 */
	public static void devolverIncidenciaResuelta() {
		System.out.println("\nDEVOLUCIÓN DE INCIDENCIA RESUELTA A PENDIENTE");
		System.out.println("Introduce el código de la incidencia a devolver:");
		String codigo = sc.nextLine();

		// Devolver la incidencia resuelta
		Logica.devolverIncidenciaResuelta(codigo);
	}
}
