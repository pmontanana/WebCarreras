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
import logica.*;

import java.io.IOException;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    private static final String PERSISTENCE_UNIT_NAME = "miUnidadDePersistencia";
    private static EntityManagerFactory factory;

    @Override
    public void init() throws ServletException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    @Override
    public void destroy() {
        if (factory != null) {
            factory.close();
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
                response.sendRedirect("admin.jsp"); // Redirect to the admin page
            } else {
                response.sendRedirect("corredor.jsp"); // Redirect to the corredor page
            }
        } else {
            response.sendRedirect("login.jsp?error=true"); // Redirect back to login with an error message
        }
    }

    private Usuario getUser(String username, String password) {
        EntityManager em = factory.createEntityManager();
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