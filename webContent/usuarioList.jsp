<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mi pagina web</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
	crossorigin="anonymous"></script>
</head>
<body>
	<header>
		<h1 align="center">Las aventuras de nanatsu no taizai</h1>
		<%--contenido --%>
		<nav class="navbar navbar-light bg-light">
			<div>
				<a class="navbar-brand" href="#">Gestion de Usuarios</a>
			</div>
			<ul class="navbar-nav">
				<li><a class="nav-link"
					href="<%=request.getContextPath()%>/list">Usuarios</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<br>
	<div class="row">
		<div clas="container">
			<h3 class="text-center">Listado de usuarios</h3>
			<hr>
			<div class="container text-left">
				<a class="btn btn-success" href="<%=request.getContextPath()%>/new">Agregar
					Nuevo Usuario</a>
			</div>
			<br>
			<table class="table table-striped table-dark">
				<thead>
					<tr>
						<th scope="col">Id</th>
						<th scope="col">Nombre</th>
						<th scope="col">Email</th>
						<th scope="col">Password</th>
						<th scope="col">Acciones</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="usuario" items="${metodoDefecto}">
						<tr>
							<td><c:out value="${usuario.id}" /></td>
							<td><c:out value="${usuario.nombre}" /></td>
							<td><c:out value="${usuario.email}" /></td>
							<td><c:out value="${usuario.pass}" /></td>
							<td><a href="edit?id=<c:out value='${usuario.id}'/>">Editar</a>&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="edit?id=<c:out value='${usuario.id}'/>">Eliminar</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>