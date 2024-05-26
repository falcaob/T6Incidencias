package persistencia;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que maneja las listas de incidencias pendientes, eliminadas y
 * resueltas.
 * 
 * @author Irene
 */
public class ListasIncidencias {

	/** Lista de incidencias pendientes. */
	public static List<Incidencia> listaPendientes = new ArrayList<>();

	/** Lista de incidencias eliminadas. */
	public static List<Incidencia> listaEliminadas = new ArrayList<>();

	/** Lista de incidencias resueltas. */
	public static List<Incidencia> listaResueltas = new ArrayList<>();

	/**
	 * Registra una nueva incidencia y la agrega a la lista de incidencias
	 * pendientes.
	 *
	 * @param nuevaIncidencia La nueva incidencia a registrar.
	 */
	public static void registrarIncidenciaEnPendientes(Incidencia nuevaIncidencia) {
		listaPendientes.add(nuevaIncidencia);
	}

}
