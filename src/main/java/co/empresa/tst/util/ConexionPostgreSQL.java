package co.empresa.tst.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionPostgreSQL {
	private Connection con = null;
	private static ConexionPostgreSQL db;
	private PreparedStatement preparedStatement;

	private static final String url = "jdbc:postgresql://localhost:5432/";
	private static final String dbName = "sistema";
	private static final String driver = "org.postgresql.Driver";
	private static final String userName = "postgres";
	private static final String password = "1234";

	public ConexionPostgreSQL() {
		try {
			Class.forName(driver).newInstance();
			con = (Connection)DriverManager.getConnection(url + dbName, userName, password);

		} catch (InstantiationException e) {
			e.printStackTrace();

		} catch (IllegalAccessException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cerrarConexion() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ConexionPostgreSQL getConexion() {
		if (db == null)
			db = new ConexionPostgreSQL();
		return db;
	}

//metodo para consultar
	public ResultSet query() throws SQLException {
		ResultSet res = preparedStatement.executeQuery();
		return res;
	}

//metodo para actualizar
	public int execute() throws SQLException {
		int result = preparedStatement.executeUpdate();
		return result;
	}

//metodo para hacer la conexion
	public Connection getCon() {
		return this.con;
	}

	public PreparedStatement setPreparedStatement(String sql) throws SQLException {
		this.preparedStatement = con.prepareStatement(sql);
		return this.preparedStatement;
	}
}