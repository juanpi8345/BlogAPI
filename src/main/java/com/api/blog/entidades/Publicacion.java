package com.api.blog.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Getter
@Setter
@Table(name = "publicaciones")
public class Publicacion {

    public Publicacion() {
    }

    public Publicacion(Long publicacionId, String titulo, String descripcion, String contenido, Usuario autor) {
        this.publicacionId = publicacionId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
        this.autor = autor;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publicacion_id")
    private Long publicacionId;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    @Lob
    private String descripcion;

    @Column(nullable = false)
    @Lob
    private String contenido;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_actualizacion")
    private Date fechaActualizacion;

    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL)
    private Set<Comentario> comentarios = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "usuario_id")

    private Usuario autor;

    @ManyToOne()
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = new Date();
        fechaActualizacion = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = new Date();
    }

}
