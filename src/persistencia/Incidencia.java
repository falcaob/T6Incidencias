package persistencia;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import servicios.Estado;

/**
 * Clase que representa una incidencia en el sistema de gestión de incidencias.
 * Implementa Serializable para permitir la serialización y Comparable para
 * permitir la comparación de objetos de tipo Incidencia.
 * 
 * @author Irene
 */
public class Incidencia implements Comparable, Serializable {

	/** Código único de la incidencia. */
	private String codigo;

	/** Estado de la incidencia. */
	private Estado estado;

	/** Puesto asociado a la incidencia. */
	private int puesto;

	/** Descripción del problema de la incidencia. */
	private String problema;

	/** Fecha de resolución de la incidencia. */
	private LocalDate fechaResolucion;

	/** Descripción de la resolución de la incidencia. */
	private String resolucion;

	/** Fecha de eliminación de la incidencia. */
	private LocalDate fechaEliminacion;

	/** Causa de la eliminación de la incidencia. */
	private String causaEliminacion;

	/** Número estático utilizado para generar códigos únicos de incidencia. */
	public static int ultimoNumero = 0;

	/**
	 * Constructor vacío requerido por JAXB.
	 */
	public Incidencia() {
	}

	/**
	 * Constructor para crear una incidencia con los datos proporcionados.
	 *
	 * @param puesto   El puesto asociado a la incidencia.
	 * @param problema La descripción del problema de la incidencia.
	 */
	public Incidencia(int puesto, String problema) {
		super();
		this.puesto = puesto;
		this.problema = problema;
		this.codigo = generarCodigoIncidencia();
		this.estado = Estado.PENDIENTE;
	}

	/**
	 * Obtiene el código de la incidencia.
	 *
	 * @return El código de la incidencia.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Establece el código de la incidencia.
	 *
	 * @param codigo El nuevo código de la incidencia.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Obtiene el estado de la incidencia.
	 *
	 * @return El estado de la incidencia.
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * Establece el estado de la incidencia.
	 *
	 * @param estado El nuevo estado de la incidencia.
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * Obtiene el puesto asociado a la incidencia.
	 *
	 * @return El puesto asociado a la incidencia.
	 */
	public int getPuesto() {
		return puesto;
	}

	/**
	 * Establece el puesto asociado a la incidencia.
	 *
	 * @param puesto El nuevo puesto asociado a la incidencia.
	 */
	public void setPuesto(int puesto) {
		this.puesto = puesto;
	}

	/**
	 * Obtiene la descripción del problema de la incidencia.
	 *
	 * @return La descripción del problema de la incidencia.
	 */
	public String getProblema() {
		return problema;
	}

	/**
	 * Establece la descripción del problema de la incidencia.
	 *
	 * @param problema La nueva descripción del problema de la incidencia.
	 */
	public void setProblema(String problema) {
		this.problema = problema;
	}

	/**
	 * Obtiene la fecha de resolución de la incidencia.
	 *
	 * @return La fecha de resolución de la incidencia.
	 */
	public LocalDate getFechaResolucion() {
		return fechaResolucion;
	}

	/**
	 * Establece la fecha de resolución de la incidencia.
	 *
	 * @param fechaResolucion La nueva fecha de resolución de la incidencia.
	 */
	public void setFechaResolucion(LocalDate fechaResolucion) {
		this.fechaResolucion = fechaResolucion;
	}

	/**
	 * Obtiene la descripción de la resolución de la incidencia.
	 *
	 * @return La descripción de la resolución de la incidencia.
	 */
	public String getResolucion() {
		return resolucion;
	}

	/**
	 * Establece la descripción de la resolución de la incidencia.
	 *
	 * @param resolucion La nueva descripción de la resolución de la incidencia.
	 */
	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}

	/**
	 * Obtiene la fecha de eliminación de la incidencia.
	 *
	 * @return La fecha de eliminación de la incidencia.
	 */
	public LocalDate getFechaEliminacion() {
		return fechaEliminacion;
	}

	/**
	 * Establece la fecha de eliminación de la incidencia.
	 *
	 * @param fechaEliminacion La nueva fecha de eliminación de la incidencia.
	 */
	public void setFechaEliminacion(LocalDate fechaEliminacion) {
		this.fechaEliminacion = fechaEliminacion;
	}

	/**
	 * Obtiene la causa de eliminación de la incidencia.
	 *
	 * @return La causa de eliminación de la incidencia.
	 */
	public String getCausaEliminacion() {
		return causaEliminacion;
	}

	/**
	 * Establece la causa de eliminación de la incidencia.
	 *
	 * @param causaEliminacion La nueva causa de eliminación de la incidencia.
	 */
	public void setCausaEliminacion(String causaEliminacion) {
		this.causaEliminacion = causaEliminacion;
	}

	/**
	 * Genera un código único para la incidencia basado en la fecha y hora actual y
	 * un número incremental.
	 *
	 * @return El código generado para la incidencia.
	 */
	public String generarCodigoIncidencia() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
		String fechaActual = LocalDateTime.now().format(formatter);

		ultimoNumero++;

		return codigo = fechaActual + "-" + ultimoNumero;
	}

	/**
	 * Compara esta incidencia con otra incidencia por código.
	 *
	 * @param o El objeto con el que comparar.
	 * @return Un valor negativo, cero o positivo si esta incidencia es menor, igual
	 *         o mayor que la incidencia especificada.
	 */
	@Override
	public int compareTo(Object o) {
		return this.codigo.compareTo(((Incidencia) o).getCodigo());
	}

	/**
	 * Compara esta incidencia con otro objeto para determinar si son iguales
	 * basándose en el código.
	 *
	 * @param o El objeto con el que comparar.
	 * @return true si los códigos de ambas incidencias son iguales, false en caso
	 *         contrario.
	 */
	@Override
	public boolean equals(Object o) {
		return codigo.equals(((Incidencia) o).codigo);
	}

	/**
	 * Devuelve una representación en forma de cadena de la incidencia.
	 *
	 * @return Una cadena que representa la incidencia.
	 */
	@Override
	public String toString() {
		return "DATOS DE INCIDENCIA\n" + "Código: " + codigo + '\n' + "Estado: " + estado + '\n' + "Puesto: " + puesto
				+ '\n' + "Problema: " + problema;
	}
}
