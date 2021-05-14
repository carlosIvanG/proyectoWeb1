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
		<header>
			<h1 align="center">Las aventuras de nanatsu no taizai</h1>
			<%--contenido --%>
			<nav class="navbar navbar-dark bg-dark">
				<div>
					<a class="navbar-brand" href="#">Gestion de Usuarios</a>
				</div>
				<ul class="navbar-nav">
					<li><a class="nav-link"
						href="<%=request.getContextPath()%>/list">Usuarios</a></li>
				</ul>
			</nav>
		</header>
		<br> <br>
		<div class="container col-md-5">
			<div class="card">
				<div class="card-body">
					<c:if test="${usuario != null}">
						<form action="update" method="post">
					</c:if>

					<c:if test="${usuario == null}">
						<form action="insert" method="post">
					</c:if>
					<caption>
						<h2>
							<c:if test="${usuario != null}">
							Editar Usuario</c:if>
							<c:if test="${usuario == null}">
							Agregar Nuevo Usuario</c:if>
						</h2>
					</caption>
					<c:if test="${usuario != null}">
						<input type="hidden" name="id"
							value="<c:out value='${usuario.id}'/>" />
					</c:if>
					<fieldset class="form-group">
						<label>Nombre de Usuario</label><input class="form-control" name="nombre" required="required" type="text" value="<c:out value='${usuario.nombre}'/>" />
					</fieldset>
					<fieldset class="form-group">
						<label>Email de Usuario</label><input class="form-control" name="email" type="text" value="<c:out value='${usuario.email}'/>" />
					</fieldset>
					<fieldset class="form-group">
						<label>Pass de Usuario</label><input class="form-control" name="pass" type="text" value="<c:out value='${usuario.pass}'/>" />
					</fieldset>
					<br><br>
					<button type="submit" class="btn btn-success">Guardar</button>
					</form>
				</div>
			</div>
		</div>
	</header>
</body>
</html>