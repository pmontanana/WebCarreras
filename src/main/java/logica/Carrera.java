package logica;

import jakarta.persistence.*;

@Entity
@Table(name="carreras")
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="nombre")
    private String nombre;
    @Column(name="ubicacion")
    private String ubicacion;
    @Column(name="descripcion")
    private String descripcion;
    @Column(name="maxParticipantes")
    private Integer maxParticipantes;

    public Carrera() {
    }

    public Carrera(Integer id, String nombre, String ubicacion, String descripcion, Integer maxParticipantes) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.maxParticipantes = maxParticipantes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getMaxParticipantes() {
        return maxParticipantes;
    }

    public void setMaxParticipantes(Integer maxParticipantes) {
        this.maxParticipantes = maxParticipantes;
    }
}
