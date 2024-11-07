package logica;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "inscipciones")
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="fecha_incripcion")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name="carrera_id")
    private Carrera idCarrera;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario idUsuario;

    public Inscripcion() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Carrera getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Carrera idCarrera) {
        this.idCarrera = idCarrera;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Inscripcion(Integer id, Date fecha, Carrera idCarrera, Usuario idUsuario) {
        this.id = id;
        this.fecha = fecha;
        this.idCarrera = idCarrera;
        this.idUsuario = idUsuario;
    }
}
