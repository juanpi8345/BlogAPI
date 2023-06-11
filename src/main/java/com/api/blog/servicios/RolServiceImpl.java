
package com.api.blog.servicios;

import com.api.blog.entidades.Rol;
import com.api.blog.excepciones.NotFoundException;
import com.api.blog.repositorios.RolRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements RolService {
    
    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<Rol> obtenerRoles() {
        return rolRepository.findAll();
    }

    @Override
    public Rol obtenerRolPorId(Long rolId) throws NotFoundException{
        return rolRepository.findById(rolId).
                orElseThrow(()->new NotFoundException("Rol con el id "+rolId+ " no encontrado"));
    }

    @Override
    public Rol eliminarRol(Long rolId) throws NotFoundException{
        Rol rol = rolRepository.findById(rolId).orElse(null);
        if(rol!= null){
            rolRepository.deleteById(rolId);
            return rol;
        }else{
            throw new NotFoundException("Rol con el id "+rolId+ " no encontrado");
        }
    }

    @Override
    public Rol obtenerRolPorNombre(String nombreRol) {
        return rolRepository.findByNombre(nombreRol).orElse(null);
    }

    @Override
    public Rol guardarRol(Rol rol) {
        if(obtenerRolPorNombre(rol.getNombre()) == null){
            return rolRepository.save(rol);
        }
        return null;
    }
    
}
