<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Panel de Administrador</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            display: flex;
            min-height: 100vh;
        }

        #sidebar {
            width: 300px;
            background-color: #f8f9fa;
            padding: 20px;
        }

        #content {
            flex: 1;
            padding: 20px;
        }

        .nav-link {
            font-size: 1.2rem;
            padding: 10px 0;
        }

        .race-card {
            transition: transform 0.3s ease-in-out;
        }

        .race-card:hover {
            transform: scale(1.05);
        }

        .race-image {
            height: 200px;
            object-fit: cover;
        }

        h1 {
            font-size: 2.5rem;
        }

        .card-title {
            font-size: 1.5rem;
        }

        .card-text {
            font-size: 1.1rem;
        }
        #logout{
            float: right;
        }
    </style>
</head>
<body>

<div id="sidebar">
    <h2 class="mb-4">Menu</h2>
    <ul class="nav flex-column">
        <li class="nav-item dropdown">
            <a class="nav-link" href="./crearPersona.jsp">Crear Participantes</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="admin/gestion-participantes">Administrar Participantes</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="admin/gestion-carreras">Gestionar Carreras</a>
        </li>
    </ul>
</div>

<div id="content">

    <header class="mb-5">
        <form action="Logout" method="post" style="display:inline">
            <button id="logout" type="submit" class="btn btn-danger">Cerrar Sesión</button>
        </form>
        <h1 class="text-center">Panel de Administrador</h1>
    </header>

    <main>
        <h2 class="text-center mb-5">Proximas Carreras</h2>
        <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
            <div class="col">
                <div class="card h-100 race-card">
                    <img src="images/imagen1.jpg" class="card-img-top race-image"
                         alt="Carrera de montaña con paisaje boscoso" width="404" height="316">
                    <div class="card-body">
                        <h5 class="card-title">Carrera de Montaña</h5>
                        <p class="card-text">Fecha: 15 de Mayo, 2023</p>
                        <a href="carreras/montana" class="btn btn-primary">Ver detalles</a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card h-100 race-card">
                    <img src="images/imagen2.jpg" class="card-img-top race-image"
                         alt="Maratón urbano con edificios de fondo" width="400" height="200">
                    <div class="card-body">
                        <h5 class="card-title">Maratón Urbano</h5>
                        <p class="card-text">Fecha: 2 de Junio, 2023</p>
                        <a href="carreras/maraton-urbano" class="btn btn-primary">Ver detalles</a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card h-100 race-card">
                    <img src="images/imagen3.jpeg" class="card-img-top race-image"
                         alt="Carrera de obstáculos con participantes saltando vallas" width="318" height="159">
                    <div class="card-body">
                        <h5 class="card-title">Carrera de Obstáculos</h5>
                        <p class="card-text">Fecha: 20 de Junio, 2023</p>
                        <a href="carreras/obstaculos" class="btn btn-primary">Ver detalles</a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card h-100 race-card">
                    <img src="images/imagen4.jpeg" class="card-img-top race-image"
                         alt="Carrera nocturna con corredores iluminados" width="318" height="159">
                    <div class="card-body">
                        <h5 class="card-title">Carrera Nocturna</h5>
                        <p class="card-text">Fecha: 10 de Julio, 2023</p>
                        <a href="carreras/nocturna" class="btn btn-primary">Ver detalles</a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card h-100 race-card">
                    <img src="images/imagen5.jpg" class="card-img-top race-image"
                         alt="Carrera de trail running en montaña" width="400" height="200">
                    <div class="card-body">
                        <h5 class="card-title">Trail Running</h5>
                        <p class="card-text">Fecha: 5 de Agosto, 2023</p>
                        <a href="carreras/trail-running" class="btn btn-primary">Ver detalles</a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card h-100 race-card">
                    <img src="images/imagen6.jpg" class="card-img-top race-image"
                         alt="Carrera de relevos con corredores pasando el testigo" width="1200" height="572">
                    <div class="card-body">
                        <h5 class="card-title">Carrera de Relevos</h5>
                        <p class="card-text">Fecha: 22 de Agosto, 2023</p>
                        <a href="carreras/relevos" class="btn btn-primary">Ver detalles</a>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <footer class="mt-5 py-4">
        <div class="container text-center">
            <p>&copy; 2023 Registro de Carreras. Todos los derechos reservados.</p>
            <%
                String username = (String) session.getAttribute("username");
                if (username == null) {
                    username = "Invitado";
                }
            %>
            <p>Bienvenido <%= username %></p>
        </div>
    </footer>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        console.log('Pagina admin cargada');
    });
</script>
</body>
</html>