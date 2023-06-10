package com.api.blog.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "publicacion_categoria")
public class PublicacionCategoria {

    public PublicacionCategoria() {
    }

    public PublicacionCategoria(Long publicacionCategoriaId, Publicacion publicacion, Categoria categoria) {
        this.publicacionCategoriaId = publicacionCategoriaId;
        this.publicacion = publicacion;
        this.categoria = categoria;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long publicacionCategoriaId;
    
    @ManyToOne
    private Publicacion publicacion;
    
    @ManyToOne
    private Categoria categoria;
    
}
