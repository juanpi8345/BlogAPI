
package com.api.blog.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "publicaciones")
public class Publicacion extends AuditModel {

    public Publicacion() {
    }

    public Publicacion(Long publicacionId, String titulo, String descripcion, String contenido, Usuario autor) {
        this.publicacionId = publicacionId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
        this.autor = autor;
    }
    
     private static final long serialVersionUID = 1L;
    
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
   
   @OneToMany(mappedBy="publicacion",cascade = CascadeType.ALL)
   private Set<Comentario> comentarios = new HashSet<>();
   
   @ManyToOne
   @JoinColumn(name = "usuario_id")
   private Usuario autor;
   
   @OneToMany(mappedBy = "publicacion")
   private Set<PublicacionCategoria> publicacionCategorias = new HashSet<>();
   

  
}
