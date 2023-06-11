
package com.api.blog.repositorios;

import com.api.blog.entidades.Publicacion;
import com.api.blog.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Long>{
    
    public List<Publicacion> findByAutor(Usuario autor);
    
    
  
}
