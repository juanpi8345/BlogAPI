
package com.api.blog.repositorios;

import com.api.blog.entidades.Comentario;
import com.api.blog.entidades.Publicacion;
import com.api.blog.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    
    public List<Comentario> findAllByUsuario(Usuario usuario);
    public List<Comentario> findAllByPublicacion(Publicacion publicacion);
    
}
