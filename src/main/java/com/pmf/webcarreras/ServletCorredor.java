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
   /*El campo entityManagerFactory es una instancia de la clase EntityManagerFactory. Esta clase es parte de JPA y es responsable de crear instancias de EntityManager, que se utilizan para gestionar la interacción con la base de datos (consultas, inserciones, actualizaciones, etc.).
    EntityManagerFactory: Es una fábrica que crea y administra instancias de EntityManager. Se utiliza principalmente para interactuar con la unidad de persistencia, es decir, con la base de datos configurada en JPA.
    Solo necesitas una instancia de EntityManagerFactory en toda la aplicación, y generalmente se inicializa al inicio del ciclo de vida de la aplicación (como en la inicialización de un servlet).
     */

    private EntityManagerFactory entityManagerFactory;

    @Override
    public void init() throws ServletException {
        // Inicializar el EntityManagerFactory al iniciar el servlet
        /*Este es el paso clave en el que se inicializa el EntityManagerFactory.
          Persistence.createEntityManagerFactory("miUnidadDePersistencia"): Este método estático crea una instancia de EntityManagerFactory
          utilizando una unidad de persistencia definida en el archivo de configuración persistence.xml. El argumento "miUnidadDePersistencia"
          corresponde al nombre de esa unidad de persistencia, que define la configuración de la base de datos, las entidades mapeadas, el proveedor de JPA (como Hibernate),
          entre otros.
         */
        entityManagerFactory = Persistence.createEntityManagerFactory("miUnidadDePersistencia");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Crear el EntityManager para realizar operaciones de persistencia
        /* entityManagerFactory.createEntityManager(): El EntityManagerFactory, que fue inicializado previamente en el método init() del servlet (o en cualquier otra parte de la aplicación),
           se usa para crear una instancia de EntityManager. Esto es necesario para cada unidad de trabajo o interacción con la base de datos. Cada vez que quieras interactuar con la base de datos,
           necesitas un EntityManager nuevo.
           EntityManagerFactory: Es responsable de la creación de EntityManager y debería ser reutilizado durante la vida de la aplicación, pero cada EntityManager es generalmente creado para una unidad de trabajo
           o transacción y es desechado después de su uso.
         */
        //entityManagerFactory = Persistence.createEntityManagerFactory("miUnidadDePersistencia");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Iniciar una transacción
        /*entityManager.getTransaction(): Obtiene un objeto EntityTransaction, que se usa para gestionar transacciones en JPA. Una transacción es una secuencia de operaciones que se ejecutan de manera atómica: todas se completan o ninguna lo hace (propiedad de todo o nada). Es decir, si una parte de la transacción falla, la base de datos puede revertir las acciones que se realizaron durante esa transacción.
          begin(): Este método inicia una transacción. Esto significa que las operaciones de persistencia que se realicen a partir de este punto estarán agrupadas en la transacción. Cualquier operación realizada en el contexto del EntityManager (como guardar, actualizar o eliminar una entidad) no se comprometerá a la base de datos hasta que la transacción se finalice con commit().
         */
        entityManager.getTransaction().begin();

        // Obtener los parámetros del formulario
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String apellidos = request.getParameter("apellidos");
        String user = request.getParameter("user");
        String clave = request.getParameter("clave");
        Boolean es_admin = Boolean.parseBoolean(request.getParameter("es_admin"));

        // Crear un nuevo Usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setApellidos(apellidos);
        usuario.setUsuario(user);
        usuario.setPassword(clave);
        usuario.setEsAdmin(es_admin);


        // Persistir el objeto cliente
        /*Este método se utiliza para persistir una entidad en la base de datos. En este caso, el objeto usuario será insertado en la base de datos. Algunos puntos clave sobre persist():
          Si el objeto usuario no existe en la base de datos (es decir, es una nueva instancia), se insertará un nuevo registro.
         */
        entityManager.persist(usuario);

        // Confirmar la transacción
        //Este método confirma la transacción (commit), lo que significa que las operaciones realizadas durante la transacción (como la inserción del objeto usuario) se vuelven permanentes en la base de datos.
        entityManager.getTransaction().commit();

        // Cerrar el EntityManager
        //Este método cierra el EntityManager. Es importante cerrarlo una vez que has terminado de usarlo para liberar los recursos que ha estado utilizando.
        entityManager.close();

        // Redirigir a una página de éxito o mostrar un mensaje
        response.sendRedirect("index.jsp");

        /*Crear
        // 1. Crear y persistir Usuario
Usuario usuario = new Usuario();
usuario.setNombre("Nombre");
usuario.setEmail("email@example.com");
usuario.setApellidos("Apellidos");
usuario.setUsuario("usuario");
usuario.setClave("clave");
usuario.setEsAdmin(false);

entityManager.getTransaction().begin();
entityManager.persist(usuario); // Persistir el usuario
entityManager.getTransaction().commit(); // Confirmar la transacción

// 2. Crear y persistir Carrera
Carrera carrera = new Carrera();
carrera.setNombre("Maratón");
carrera.setFecha(new Date()); // Establecer la fecha
carrera.setUbicacion("Ciudad");
carrera.setMaxParticipantes(100);
carrera.setDescripcion("Una maratón emocionante");

entityManager.getTransaction().begin();
entityManager.persist(carrera); // Persistir la carrera
entityManager.getTransaction().commit(); // Confirmar la transacción

// 3. Crear y persistir Inscripcion
Inscripcion inscripcion = new Inscripcion();
inscripcion.setUsuario(usuario); // Asignar el usuario creado
inscripcion.setCarrera(carrera); // Asignar la carrera creada
inscripcion.setFechaInscripcion(new Date()); // Establecer la fecha de inscripción

entityManager.getTransaction().begin();
entityManager.persist(inscripcion); // Persistir la inscripción
entityManager.getTransaction().commit(); // Confirmar la transacción




         */

        /*Actualizar

Usuario usuario = entityManager.find(Usuario.class, idUsuario);
if (usuario != null) {
    usuario.setNombre(nuevoNombre);
    usuario.setEmail(nuevoEmail);
    // Otros campos a actualizar...

    entityManager.merge(usuario); // La entidad se actualizará en la base de datos
}

Carrera carrera = entityManager.find(Carrera.class, idCarrera);
if (carrera != null) {
    carrera.setNombre(nuevoNombre);
    carrera.setUbicacion(nuevaUbicacion);
    // Otros campos a actualizar...

    entityManager.merge(carrera);
}

Inscripcion inscripcion = entityManager.find(Inscripcion.class, idInscripcion);
if (inscripcion != null) {
    inscripcion.setFechaInscripcion(nuevaFechaInscripcion);
    // Otros campos a actualizar...

    entityManager.merge(inscripcion);
}



         */
        /*Listados
Usuario usuario = entityManager.find(Usuario.class, idUsuario);
Carrera carrera = entityManager.find(Carrera.class, idCarrera);
Inscripcion inscripcion = entityManager.find(Inscripcion.class, idInscripcion);

// Crear una consulta JPQL para obtener inscripciones junto con usuario y carrera
String jpql = "SELECT i FROM Inscripcion i JOIN i.usuario u JOIN i.carrera c";
List<Inscripcion> inscripciones = entityManager.createQuery(jpql, Inscripcion.class).getResultList();

// Procesar los resultados
for (Inscripcion inscripcion : inscripciones) {
    System.out.println("Usuario: " + inscripcion.getUsuario().getNombre());
    System.out.println("Carrera: " + inscripcion.getCarrera().getNombre());
    System.out.println("Fecha de Inscripción: " + inscripcion.getFechaInscripcion());
}




         */
        /*Borrados

        Usuario usuario = entityManager.find(Usuario.class, idUsuario);
if (usuario != null) {
    entityManager.remove(usuario);
}

Carrera carrera = entityManager.find(Carrera.class, idCarrera);
if (carrera != null) {
    entityManager.remove(carrera);
}

Inscripcion inscripcion = entityManager.find(Inscripcion.class, idInscripcion);
if (inscripcion != null) {
    entityManager.remove(inscripcion);
}


         */

    }

    @Override
    public void destroy() {
        // Cerrar el EntityManagerFactory al destruir el servlet
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }

    }
}

