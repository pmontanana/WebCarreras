package logica;

import jakarta.persistence.*;

@Entity
@Table(name="usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="dni")
    private String dni;
    @Column(name="nombre")
    private String nombre;
    @Column(name="apellidos")
    private String apellidos;
    @Column(name="email")
    private String email;
    @Column(name="usuario")
    private String usuario;
    @Column(name="password")
    private String password;
    @Column(name="esAdmin")
    private Boolean esAdmin;

    public Usuario() {
    }

    public Usuario(Integer id, String dni, String nombre, String apellidos, String email, String usuario, String password, Boolean esAdmin) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.usuario = usuario;
        this.password = password;
        this.esAdmin = esAdmin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(Boolean esAdmin) {
        this.esAdmin = esAdmin;
    }
}
