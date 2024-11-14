package com.pmf.webcarreras;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import logica.Usuario;
import io.github.cdimascio.dotenv.Dotenv;
import org.mariadb.jdbc.Connection;


import java.io.IOException;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CrearPersona", value = "/CrearPersona")
public class CrearPersona extends HttpServlet {
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void init() throws ServletException {
        Map<String, String> properties = new HashMap<>();
        Dotenv dotenv = Dotenv.load();

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            String dbUrl = dotenv.get("DB_URL");
            String dbUser = dotenv.get("DB_USER");
            String dbPassword = dotenv.get("DB_PASSWORD");

            if (dbUrl == null || dbUser == null || dbPassword == null) {
                throw new ServletException("Error: Variables de entorno no encontradas.");
            }

            try (Connection connection = (Connection) DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
                System.out.println("Conexión exitosa a la base de datos.");
            } catch (java.sql.SQLException ex) {
                System.err.println("Error al conectar a la base de datos: " + ex.getMessage());
            }

            properties.put("jakarta.persistence.jdbc.url", dbUrl);
            properties.put("jakarta.persistence.jdbc.user", dbUser);
            properties.put("jakarta.persistence.jdbc.password", dbPassword);
            properties.put("jakarta.persistence.jdbc.driver", "org.mariadb.jdbc.Driver");

            entityManagerFactory = Persistence.createEntityManagerFactory("miUnidadDePersistencia", properties);
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver de MariaDB no encontrado.");
            e.printStackTrace();
            throw new ServletException("Error: Driver de MariaDB no encontrado.", e);
        } catch (Exception e) {
            System.err.println("Error al inicializar el EntityManagerFactory: " + e.getMessage());
            e.printStackTrace();
            throw new ServletException("Error al inicializar el EntityManagerFactory", e);
        }
    }

    @Override
    public void destroy() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("user");
        String password = request.getParameter("passwd");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String dni = request.getParameter("dni");
        String email = request.getParameter("email");
        boolean esAdmin = "on".equals(request.getParameter("esAdmin"));

        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            Usuario usuario = new Usuario();
            usuario.setUsuario(username);
            usuario.setPassword(password);
            usuario.setEsAdmin(esAdmin);
            usuario.setNombre(nombre);
            usuario.setApellidos(apellido);
            usuario.setDni(dni);
            usuario.setEmail(email);
            em.persist(usuario);
            em.getTransaction().commit();
            response.sendRedirect("admin.jsp?success=true");
        } finally {
            em.close();
        }
    }
}