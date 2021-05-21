package co.empresa.test.dao;

import java.sql.*;
import java.util.*;

import co.empresa.test.modelo.Usuario;

public interface UsuarioDao {
	public void insertarUsuario(Usuario usuario) throws SQLException;

	public void eliminarUsuario(int id) throws SQLException;

	public void actualizarUsuario(Usuario usuario) throws SQLException;

	public List<Usuario> buscarUsuarios();

	public Usuario buscarUsuarioId(int id);
}
