
package com.api.blog.entidades;

public class ComentarioDTO {
    
    private String autor;
    private String comentario;

    // Constructor
    public ComentarioDTO(String autor, String comentario) {
        this.autor = autor;
        this.comentario = comentario;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
}
