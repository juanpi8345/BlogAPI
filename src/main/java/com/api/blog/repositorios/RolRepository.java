
package com.api.blog.repositorios;

import com.api.blog.entidades.Rol;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    
    public Optional<Rol> findByNombre(String nombreRol);
    
}
