
package com.api.blog.entidades;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

public class ComentarioDTO {
    
    private Long comentarioId;
    private String autor;
    private Collection<? extends GrantedAuthority> autoridades;
    private String descripcion;

    public ComentarioDTO(Long comentarioId, String autor, Collection<? extends GrantedAuthority> autoridades, String descripcion) {
        this.comentarioId = comentarioId;
        this.autor = autor;
        this.autoridades = autoridades;
        this.descripcion = descripcion;
    }

    public Long getComentarioId() {
        return comentarioId;
    }

    public void setComentarioId(Long comentarioId) {
        this.comentarioId = comentarioId;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Collection<? extends GrantedAuthority> getAutoridades() {
        return autoridades;
    }

    public void setAutoridades(Collection<? extends GrantedAuthority> autoridades) {
        this.autoridades = autoridades;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    


    
    
    
    


 
    

  
    
}
