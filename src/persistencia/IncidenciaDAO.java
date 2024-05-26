package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que maneja las operaciones de la base de datos relacionadas con las
 * incidencias. Proporciona métodos para insertar, buscar y actualizar
 * incidencias en la base de datos.
 * 
 * @author Irene
 */
public class IncidenciaDAO {

	/**
	 * Inserta una nueva incidencia en la base de datos.
	 * 
	 * @param incidencia La incidencia a insertar.
	 * @param con        La conexión a la base de datos.
	 * @throws SQLException Si ocurre un error durante la inserción.
	 */
	public static void insertar(Incidencia incidencia, Connection con) throws SQLException {

		// Sentencia parametrizada
		String sql = "INSERT INTO incidencias (codigo, estado, puesto, problema) VALUES (?, ?, ?, ?)";

		PreparedStatement sentencia = null;

		// Para asegurarnos de que PreparedStatement se cierra correctamente
		try {

			sentencia = con.prepareStatement(sql);

			sentencia.setString(1, incidencia.getCodigo());
			sentencia.setString(2, incidencia.getEstado().name());
			sentencia.setInt(3, incidencia.getPuesto());
			sentencia.setString(4, incidencia.getProblema());

			sentencia.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Busca una incidencia en la base de datos por su código.
	 * 
	 * @param codigo El código de la incidencia a buscar.
	 * @param con    La conexión a la base de datos.
	 * @return La incidencia encontrada, o null si no se encuentra ninguna
	 *         incidencia con el código dado.
	 * @throws SQLException Si ocurre un error durante la búsqueda.
	 */
	public static Incidencia buscarPorCodigo(String codigo, Connection con) throws SQLException {

		String sql = "SELECT * FROM incidencias WHERE codigo = ?";

		PreparedStatement sentencia = null;
		ResultSet rs = null;
		Incidencia incidencia = null;

		try {
			sentencia = con.prepareStatement(sql);

			sentencia.setString(1, codigo);

			rs = sentencia.executeQuery();

			while (rs.next()) {
				int puesto = rs.getInt("puesto");
				String problema = rs.getString("problema");

				incidencia = new Incidencia(puesto, problema);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return incidencia;
	}

	/**
	 * Actualiza una incidencia existente en la base de datos.
	 * 
	 * @param incidencia La incidencia a actualizar.
	 * @param con        La conexión a la base de datos.
	 * @throws SQLException Si ocurre un error durante la actualización.
	 */
	public static void actualizar(Incidencia incidencia, Connection con) throws SQLException {
		String sql = "UPDATE incidencias SET puesto = ?, problema = ? WHERE codigo = ?";

		PreparedStatement sentencia = null;

		try {

			sentencia = con.prepareStatement(sql);

			sentencia.setInt(1, incidencia.getPuesto());
			sentencia.setString(2, incidencia.getProblema());
			sentencia.setString(3, incidencia.getCodigo());

			sentencia.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
