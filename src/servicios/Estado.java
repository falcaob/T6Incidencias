package servicios;

/**
 * Enumeración que representa los posibles estados de una incidencia.
 * 
 * Los estados posibles son:
 * - PENDIENTE: La incidencia está pendiente de ser resuelta.
 * - ELIMINADA: La incidencia ha sido eliminada.
 * - RESUELTA: La incidencia ha sido resuelta.
 */
public enum Estado {
	
    /**
     * La incidencia está pendiente de ser resuelta.
     */
    PENDIENTE,

    /**
     * La incidencia ha sido eliminada.
     */
    ELIMINADA,

    /**
     * La incidencia ha sido resuelta.
     */
    RESUELTA
}
