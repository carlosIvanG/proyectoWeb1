package co.empresa.test.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.empresa.test.dao.UsuarioDao;
import co.empresa.test.dao.UsuarioDaoFactory;
import co.empresa.test.dao.UsuarioDaoMySQL;
import co.empresa.test.dao.UsuarioDaoPostgreSQL;
import co.empresa.test.modelo.Usuario;
import co.empresa.tst.util.ServicioEmail;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDao usuarioDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuarioServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		this.usuarioDao = UsuarioDaoFactory.getUsuarioDao("postgresql");
		//this.usuarioDao = new UsuarioDaoPostgreSQL();
		//this.usuarioDao = new UsuarioDaoMySQL();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertarUsuario(request, response);
				break;
			case "/delete":
				eliminarUsuario(request, response);
				break;
			case "/update":
				actualizarUsuario(request, response);
				break;
			case "/edit":
				showeditForm(request, response);
				break;
			default:
				metodoDefecto(request, response);
				break;
			}

		} catch (SQLException e) {
			// TODO: handle exception
		}

		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuario.jsp");
		dispatcher.forward(request, response);
	}

	private void insertarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");

		Usuario usuario = new Usuario(nombre, email, pass);
		usuarioDao.insertarUsuario(usuario);
		ServicioEmail servicioEmail= new ServicioEmail("carlosivangc@ufps.edu.co", "znfxogobzgrguwcp");
		servicioEmail.enviarEmail(email, "Datos Formulario", usuario.toString());
		response.sendRedirect("list");
	}

	private void showeditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		Usuario usuarioActual = usuarioDao.buscarUsuarioId(id);
		request.setAttribute("usuario", usuarioActual);
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuario.jsp");
		dispatcher.forward(request, response);
	}

	private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");

		Usuario usuario = new Usuario(id, nombre, email, pass);
		usuarioDao.actualizarUsuario(usuario);
		response.sendRedirect("list");
	}

	private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		usuarioDao.eliminarUsuario(id);
		response.sendRedirect("list");
	}

	private void metodoDefecto(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Usuario> listUsuarios = usuarioDao.buscarUsuarios();
		request.setAttribute("metodoDefecto", listUsuarios);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuarioList.jsp");
		dispatcher.forward(request, response);
	}
}
