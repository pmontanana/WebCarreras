<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Crear Persona</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        #header{
            background-color: #f8f9fa;
            padding: 20px;
        }
    </style>
</head>
<body>
<div id="header">
    <h1 class="text-center">Crear Persona</h1>
    <% Boolean isAdmin = (Boolean) session.getAttribute("isAdmin"); %>
    <% if (isAdmin != null && !isAdmin) { %>
        <a href="./corredor.jsp" class="btn btn-dark">Volver</a>
    <% } else{ %>
        <a href="./admin.jsp" class="btn btn-dark">Volver</a>

    <% } %>
</div>
<br>
<div class="container">

    <form action="CrearPersona" method="post">
        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre:</label>
            <input type="text" id="nombre" name="nombre" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="apellido" class="form-label">Apellido:</label>
            <input type="text" id="apellido" name="apellido" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="user" class="form-label">Username:</label>
            <input type="text" id="user" name="user" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="passwd" class="form-label">Password:</label>
            <input type="password" id="passwd" name="passwd" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="dni" class="form-label">DNI:</label>
            <input type="text" id="dni" name="dni" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email:</label>
            <input type="email" id="email" name="email" class="form-control" required>
        </div>
        <% if (isAdmin != null && isAdmin) { %>
        <div class="mb-3 form-check">
            <input type="checkbox" id="esAdmin" name="esAdmin" class="form-check-input">
            <label for="esAdmin" class="form-check-label">Es Administrador</label>
        </div>
        <% } %>
        <button type="submit" class="btn btn-primary">Crear Persona</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>