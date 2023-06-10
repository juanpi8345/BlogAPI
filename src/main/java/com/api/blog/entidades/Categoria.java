
package com.api.blog.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "categorias")
public class Categoria {

    public Categoria() {
    }

    public Categoria(Long categoriaId, String nombre) {
        this.categoriaId = categoriaId;
        this.nombre = nombre;
    }
    
    @Column(name = "categoria_id")
    private Long categoriaId;
    
    @Column(nullable = false, unique = true)
    private String nombre;
    
   @OneToMany(mappedBy = "categoria")
   private Set<PublicacionCategoria> publicacionCategorias = new HashSet<>();
}
