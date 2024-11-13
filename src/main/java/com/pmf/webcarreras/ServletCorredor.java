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

import java.io.IOException;

@WebServlet(name = "ServletCorredor", value = "/ServletCorredor")
public class ServletCorredor extends HttpServlet {

    private EntityManagerFactory entityManagerFactory;

    @Override
    public void init() throws ServletException {
        entityManagerFactory = Persistence.createEntityManagerFactory("miUnidadDePersistencia");
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

