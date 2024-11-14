package com.pmf.webcarreras;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.mariadb.jdbc.Connection;


import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ServletCorredor", value = "/ServletCorredor")
public class ServletCorredor extends HttpServlet {

    private EntityManagerFactory entityManagerFactory;

    @Override
    public void init() throws ServletException {

        Map<String, String> properties = new HashMap<>();
        Map<String, String> env = System.getenv();
        Dotenv dotenv = Dotenv.load();

        try {

            Class.forName("org.mariadb.jdbc.Driver");
            String dbUrl = dotenv.get("DB_URL");
            String dbUser = dotenv.get("DB_USER");
            String dbPassword = dotenv.get("DB_PASSWORD");


            try (Connection connection = (Connection) DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
                System.out.println("Conexión exitosa a la base de datos.");
            } catch (SQLException ex) {
                System.err.println("Error al conectar a la base de datos: " + ex.getMessage());
            }

            properties.put("jakarta.persistence.jdbc.url", dbUrl);
            properties.put("jakarta.persistence.jdbc.user", dbUser);
            properties.put("jakarta.persistence.jdbc.password", dbPassword);
            properties.put("jakarta.persistence.jdbc.driver", "org.mariadb.jdbc.Driver");

            entityManagerFactory = Persistence.createEntityManagerFactory("miUnidadDePersistencia",properties);
        } catch (Exception e) {
            System.err.println("Error al inicializar el EntityManagerFactory: " + e.getMessage());
            e.printStackTrace();
            throw new ServletException("Error al inicializar el EntityManagerFactory", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String apellidos = request.getParameter("apellidos");
        String user = request.getParameter("user");
        String clave = request.getParameter("clave");
        Boolean es_admin = Boolean.parseBoolean(request.getParameter("es_admin"));

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setApellidos(apellidos);
        usuario.setUsuario(user);
        usuario.setPassword(clave);
        usuario.setEsAdmin(es_admin);

        entityManager.persist(usuario);

        entityManager.getTransaction().commit();

        entityManager.close();

        response.sendRedirect("index.jsp");

    }

    @Override
    public void destroy() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }

    }
}

