package com.pmf.webcarreras;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import logica.Usuario;
import io.github.cdimascio.dotenv.Dotenv;
import org.mariadb.jdbc.Connection;

import java.io.IOException;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
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
                System.out.println("Conexión exitosa a la base de datos." + connection);
            } catch (java.sql.SQLException ex) {
                System.err.println("Error al conectar a la base de datos: " + ex.getMessage());
            }

            properties.put("jakarta.persistence.jdbc.url", "jdbc:mariadb://iasanz.synology.me:3306/pmontaanaf01_webCarreras");
            properties.put("jakarta.persistence.jdbc.user", dbUser);
            properties.put("jakarta.persistence.jdbc.password", dbPassword);
            properties.put("jakarta.persistence.jdbc.driver", "org.mariadb.jdbc.Driver");

            entityManagerFactory = Persistence.createEntityManagerFactory("miUnidadDePersistencia", properties);
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver de MariaDB no encontrado.");
            e.printStackTrace();
            throw new ServletException("Error: Driver de MaxriaDB no encontrado.", e);
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
        String password = request.getParameter("clave");

        Usuario user = getUser(username, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", user.getUsuario());
            session.setAttribute("isAdmin", user.getEsAdmin());
            if (user.getEsAdmin()) {
                response.sendRedirect("admin.jsp");
            } else {
                response.sendRedirect("corredor.jsp");
            }
        } else {
            response.sendRedirect("login.jsp?error=true");
        }
    }

    private Usuario getUser(String username, String password) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.usuario = :username AND u.password = :password", Usuario.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}