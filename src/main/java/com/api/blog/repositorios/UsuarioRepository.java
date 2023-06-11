
package com.api.blog.repositorios;

import com.api.blog.entidades.Rol;
import com.api.blog.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    public List<Usuario> findAllByRol(Rol rol);
    public Usuario findByUsername(String username);
    
}
