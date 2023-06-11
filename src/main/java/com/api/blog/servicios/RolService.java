
package com.api.blog.servicios;

import com.api.blog.entidades.Rol;
import com.api.blog.excepciones.NotFoundException;
import java.util.List;


public interface RolService {
    
    public List<Rol> obtenerRoles();
    public Rol obtenerRolPorId(Long rolId) throws NotFoundException;
    public Rol obtenerRolPorNombre(String nombreRol);
    public Rol guardarRol(Rol rol);
    public Rol eliminarRol(Long rolId) throws NotFoundException; 
}
