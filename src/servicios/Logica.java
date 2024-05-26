package servicios;

import java.time.LocalDate;
import controlador.Interfaz;
import persistencia.Incidencia;
import persistencia.ListasIncidencias;
import persistencia.IncidenciaDAO;
import persistencia.ConexionBD;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Clase principal que maneja la lógica del sistema de gestión de incidencias.
 * Esta clase contiene métodos para realizar operaciones como registrar nuevas
 * incidencias, buscar, modificar, eliminar y resolver incidencias, así como
 * listarlas y exportarlas a ficheros.
 * 
 * @author Irene
 */
public class Logica {

	static Connection con = ConexionBD.openConnection();

	/**
	 * Realiza la opción seleccionada por el usuario.
	 * 
	 * @param opcion El número de la opción seleccionada por el usuario.
	 * @throws SQLException
	 */
	public static void relizaOpcion(int opcion) throws SQLException {

		switch (opcion) {

		case 1 -> {
			Incidencia nuevaIncidencia = Interfaz.altaIncidencia();
			// Agregar la nueva incidencia a la lista de pendientes
			ListasIncidencias.registrarIncidenciaEnPendientes(nuevaIncidencia);
			IncidenciaDAO.insertar(nuevaIncidencia, con);
		}

		case 2 -> {
			// Buscar incidencia por código
			Interfaz.buscarIncidenciaPorCodigo();
		}

		case 3 -> {
			// Modificar incidencia pendiente y no resuelta por código
			Interfaz.modificarIncidencia();
		}

		case 4 -> {
			// Eliminar incidencia pendiente y no resuelta
			Interfaz.eliminarIncidencia();
		}

		case 5 -> {
			// Resolver incidencia
			Interfaz.resolverIncidencia();
		}

		case 6 -> {
			// Modificar incidencia resuelta
			Interfaz.modificarIncidenciaResuelta();
		}

		case 7 -> {
			// Devolver incidencia resuelta a pendiente
			Interfaz.devolverIncidenciaResuelta();
		}

		case 8 -> {
			// Listar Pendientes
			listarPendientes();
		}

		case 9 -> {
			// Listar Resueltas
			listarResueltas();
		}

		case 10 -> {
			// Listar Eliminadas
			listarEliminadas();
		}

		case 11 -> {
			// Salir
			ConexionBD.closeConnection(con);
		}

		default -> System.out.println("\nOpción no válida. Debe ser entre 1 y 11");

		}
	}

	/**
	 * Modifica una incidencia pendiente y no resuelta en la base de datos.
	 * 
	 * @param incidencia La incidencia a modificar.
	 */
	public static void modificarIncidencia(Incidencia incidencia) {

		try {

			IncidenciaDAO.actualizar(incidencia, con);

			System.out.println("Incidencia modificada correctamente.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al modificar la incidencia en la base de datos.");
		}
	}

	/**
	 * Lista las incidencias pendientes.
	 */
	public static void listarPendientes() {
		if (!ListasIncidencias.listaPendientes.isEmpty()) {
			System.out.println("Incidencias Pendientes:");
			for (Incidencia incidencia : ListasIncidencias.listaPendientes) {
				System.out.println("Código: " + incidencia.getCodigo());
				System.out.println("Estado: " + incidencia.getEstado());
				System.out.println("Puesto: " + incidencia.getPuesto());
				System.out.println("Problema: " + incidencia.getProblema());
				System.out.println("----------------------------------------");
			}
		} else {
			System.out.println("No hay incidencias pendientes.");
		}
	}

	/**
	 * Lista las incidencias eliminadas.
	 */
	public static void listarEliminadas() {
		if (!ListasIncidencias.listaEliminadas.isEmpty()) {
			System.out.println("Incidencias Eliminadas:");
			for (Incidencia incidencia : ListasIncidencias.listaEliminadas) {
				System.out.println("Código: " + incidencia.getCodigo());
				System.out.println("Estado: " + incidencia.getEstado());
				System.out.println("Puesto: " + incidencia.getPuesto());
				System.out.println("Problema: " + incidencia.getProblema());
				System.out.println("Fecha de eliminación: " + incidencia.getFechaEliminacion());
				System.out.println("Causa de eliminación: " + incidencia.getCausaEliminacion());
				System.out.println("----------------------------------------");
			}
		} else {
			System.out.println("No hay incidencias eliminadas.");
		}
	}

	/**
	 * Lista las incidencias resueltas.
	 */
	public static void listarResueltas() {
		if (!ListasIncidencias.listaResueltas.isEmpty()) {
			System.out.println("Incidencias Resueltas:");
			for (Incidencia incidencia : ListasIncidencias.listaResueltas) {
				System.out.println("Código: " + incidencia.getCodigo());
				System.out.println("Estado: " + incidencia.getEstado());
				System.out.println("Puesto: " + incidencia.getPuesto());
				System.out.println("Problema: " + incidencia.getProblema());
				System.out.println("Fecha de resolución: " + incidencia.getFechaResolucion());
				System.out.println("Resolución: " + incidencia.getResolucion());
				System.out.println("----------------------------------------");
			}
		} else {
			System.out.println("No hay incidencias resueltas.");
		}
	}

	/**
	 * Busca una incidencia pendiente y no resuelta en la base de datos utilizando
	 * el código de la incidencia.
	 * 
	 * @param codigo El código de la incidencia a buscar.
	 * @return La incidencia encontrada o null si no se encuentra.
	 */
	public static Incidencia buscarIncidenciaParaModificar(String codigo) {

		Incidencia incidenciaEncontrada = null;
		try {
			incidenciaEncontrada = IncidenciaDAO.buscarPorCodigo(codigo, con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return incidenciaEncontrada;
	}

	/**
	 * Elimina una incidencia pendiente.
	 * 
	 * @param codigo           El código de la incidencia a eliminar.
	 * @param causaEliminacion La causa de la eliminación de la incidencia.
	 */
	public static void eliminarIncidencia(String codigo, String causaEliminacion) {
		// Reutilizo el método que busca una incidencia
		Incidencia incidencia = buscarIncidenciaParaModificar(codigo);

		if (incidencia != null) {
			if (incidencia.getEstado() == Estado.PENDIENTE) {
				incidencia.setFechaEliminacion(LocalDate.now()); // establecer fecha de eliminación
				incidencia.setCausaEliminacion(causaEliminacion); // establecer causa de eliminación
				incidencia.setEstado(Estado.ELIMINADA); // cambiar estado a eliminada
				ListasIncidencias.listaEliminadas.add(incidencia); // añadir a lista eliminadas
				ListasIncidencias.listaPendientes.remove(incidencia); // quitar de lista pendientes
				System.out.println("Incidencia eliminada correctamente.");
			} else {
				System.out.println("No se puede eliminar una incidencia que no esté pendiente.");
			}
		} else {
			System.out.println("No se encontró ninguna incidencia pendiente con el código: " + codigo);
		}
	}

	/**
	 * Resuelve una incidencia pendiente.
	 * 
	 * @param codigo                El código de la incidencia a resolver.
	 * @param descripcionResolucion La descripción de la resolución de la
	 *                              incidencia.
	 */
	public static void resolverIncidencia(String codigo, String descripcionResolucion) {
		// Reutilizo el método que busca una incidencia
		Incidencia incidencia = buscarIncidenciaParaModificar(codigo);

		if (incidencia != null) {
			if (incidencia.getEstado() == Estado.PENDIENTE) {
				incidencia.setFechaResolucion(LocalDate.now()); // establecer fecha de resolución
				incidencia.setResolucion(descripcionResolucion); // establecer descripción de la resolución
				incidencia.setEstado(Estado.RESUELTA); // cambiar estado a resuelta
				ListasIncidencias.listaResueltas.add(incidencia); // añadir a lista resueltas
				ListasIncidencias.listaPendientes.remove(incidencia); // quitar de lista pendientes
				System.out.println("Incidencia resuelta correctamente.");
			} else {
				System.out.println("No se puede resolver una incidencia que no esté pendiente.");
			}
		} else {
			System.out.println("No se encontró ninguna incidencia pendiente con el código: " + codigo);
		}
	}

	/**
	 * Modifica una incidencia resuelta.
	 * 
	 * @param codigo               El código de la incidencia a modificar.
	 * @param nuevaFechaResolucion La nueva fecha de resolución de la incidencia.
	 * @param nuevaResolucion      La nueva descripción de la resolución de la
	 *                             incidencia.
	 */
	public static void modificarIncidenciaResuelta(String codigo, LocalDate nuevaFechaResolucion,
			String nuevaResolucion) {
		// Reutilizo el método que busca una incidencia resuelta
		Incidencia incidencia = buscarIncidenciaResuelta(codigo);

		if (incidencia != null) {
			incidencia.setFechaResolucion(nuevaFechaResolucion); // actualizar fecha de resolución
			incidencia.setResolucion(nuevaResolucion); // actualizar descripción de la resolución
			System.out.println("Incidencia resuelta modificada correctamente.");
		} else {
			System.out.println("No se pudo modificar la incidencia resuelta.");
		}
	}

	/**
	 * Busca una incidencia resuelta.
	 * 
	 * @param codigo El código de la incidencia a buscar.
	 * @return La incidencia encontrada o null si no se encuentra.
	 */
	public static Incidencia buscarIncidenciaResuelta(String codigo) {
		Incidencia incidenciaEncontrada = null;
		boolean encontrada = false;
		for (Incidencia incidencia : ListasIncidencias.listaResueltas) {
			if (incidencia.getCodigo().equals(codigo) && !encontrada) {
				incidenciaEncontrada = incidencia;
				encontrada = Boolean.TRUE;
			}
		}
		return incidenciaEncontrada;
	}

	/**
	 * Devuelve una incidencia resuelta a la lista de pendientes.
	 * 
	 * @param codigo El código de la incidencia a devolver.
	 */
	public static void devolverIncidenciaResuelta(String codigo) {
		Incidencia incidencia = buscarIncidenciaResuelta(codigo);

		if (incidencia != null) {
			incidencia.setEstado(Estado.PENDIENTE); // cambiar estado a pendiente
			incidencia.setFechaResolucion(null); // eliminar fecha de resolución
			incidencia.setResolucion(null); // eliminar descripción de la resolución

			ListasIncidencias.listaResueltas.remove(incidencia); // quitar de lista resueltas
			ListasIncidencias.listaPendientes.add(incidencia); // añadir a lista pendientes

			System.out.println("Incidencia devuelta a pendientes correctamente.");
		} else {
			System.out.println("No se encontró ninguna incidencia resuelta con el código: " + codigo);
		}
	}

}