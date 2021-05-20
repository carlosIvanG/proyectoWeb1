package co.empresa.test.modelo;

import java.io.Serializable;

public class Usuario implements Serializable {
	private Integer id;
	private String nombre;
	private String email;
	private String pass;

	public Usuario(int id, String nombre, String email, String pass) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.pass = pass;
	}

	public Usuario(String nombre, String email, String pass) {
		this.nombre = nombre;
		this.email = email;
		this.pass = pass;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", email=" + email + ", pass=" + pass + "]";
	}
}
