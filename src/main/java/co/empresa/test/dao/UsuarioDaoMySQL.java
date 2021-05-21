package co.empresa.test.dao;

import java.sql.*;
import java.util.*;

import com.sun.net.httpserver.Authenticator.Result;

import co.empresa.test.modelo.Usuario;
import co.empresa.tst.util.ConexionMySQL;

public class UsuarioDaoMySQL implements UsuarioDao {
	private ConexionMySQL conexion;
	private static final String insertarUsuarioSQL = "INSERT INTO usuario(nombre, email, pass) VALUES (?, ?, ?)";
	private static final String eliminarUsuarioSQL = "DELETE FROM usuario id=?";
	private static final String actualizarUsuarioSQL = "UPDATE usuario SET nombre=?, email=?,pass=? WHERE id=?";
	private static final String buscarUsuarioId = "SELECT * FROM usuarioWHERE id=?";
	private static final String buscarUsuarios = "SELECT * FROM usuario";

	public UsuarioDaoMySQL() {
		super();
		this.conexion = ConexionMySQL.getConexion();
	}

	public void insertarUsuario(Usuario usuario) throws SQLException {
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(insertarUsuarioSQL);
			preparedStatement.setString(1, usuario.getNombre());
			preparedStatement.setString(2, usuario.getEmail());
			preparedStatement.setString(3, usuario.getPass());
			conexion.execute();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void eliminarUsuario(int id) throws SQLException {
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(eliminarUsuarioSQL);
			preparedStatement.setInt(1, id);
			conexion.execute();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void actualizarUsuario(Usuario usuario) throws SQLException {
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(actualizarUsuarioSQL);
			preparedStatement.setString(1, usuario.getNombre());
			preparedStatement.setString(2, usuario.getEmail());
			preparedStatement.setString(3, usuario.getPass());
			preparedStatement.setInt(4, usuario.getId());

			conexion.execute();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public List<Usuario> buscarUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(buscarUsuarios);
			ResultSet rs = conexion.query();
			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String email = rs.getString("email");
				String pass = rs.getString("pass");
				usuarios.add(new Usuario(id, nombre, email, pass));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return usuarios;
	}

	public Usuario buscarUsuarioId(int id) {
		Usuario usuario = null;

		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(buscarUsuarioId);

			preparedStatement.setInt(1, id);
			ResultSet rs = conexion.query();
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String email = rs.getString("email");
				String pass = rs.getString("pass");
				usuario = new Usuario(id, nombre, email, pass);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return usuario;
	}

}
