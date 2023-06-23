
package com.api.blog.entidades;

public class ComentarioDTO {
    
    private String autor;
    private String descripcion;

    // Constructor
    public ComentarioDTO(String autor, String descripcion) {
        this.autor = autor;
        this.descripcion = descripcion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setComentario(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
