package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que maneja la conexión a la base de datos MySQL para la aplicación de
 * gestión de incidencias. Esta clase proporciona métodos para abrir y cerrar
 * conexiones a la base de datos.
 * 
 * @author Irene
 */
public class ConexionBD {

	private static final String URL = "jdbc:mysql://localhost/appincidencias";
	private static final String USER = "irene";
	private static final String PASSWORD = "12345";

	// Asegura que el controlador de MySQL esté cargado una vez que la clase se
	// carga
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Abre una conexión a la base de datos MySQL con los parámetros especificados.
	 * 
	 * @return Una conexión abierta a la base de datos, o null si ocurre un error.
	 */
	public static Connection openConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * Cierra una conexión abierta a la base de datos MySQL.
	 * 
	 * @param con La conexión a cerrar. Si es null, no se realiza ninguna acción.
	 */
	public static void closeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
