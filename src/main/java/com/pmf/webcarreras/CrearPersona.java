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

import java.io.IOException;

@WebServlet(name = "CrearPersona", value = "/CrearPersona")
public class CrearPersona extends HttpServlet {
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
        String password = request.getParameter("passwd");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String dni = request.getParameter("dni");
        String email = request.getParameter("email");
        boolean esAdmin = "on".equals(request.getParameter("esAdmin"));

        EntityManager em = factory.createEntityManager();
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