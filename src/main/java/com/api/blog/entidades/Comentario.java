package com.api.blog.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "comentarios")
public class Comentario extends AuditModel {

    public Comentario() {
    }

    public Comentario(Long comentarioId, String descripcion, Usuario usuario, Publicacion publicacion) {
        this.comentarioId = comentarioId;
        this.descripcion = descripcion;
        this.usuario = usuario;
        this.publicacion = publicacion;
    }
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "comentario_id")
    private Long comentarioId;
    
    @Lob
    @Column(unique = true, nullable = false)
    private String descripcion;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    private Publicacion publicacion;
    
}
