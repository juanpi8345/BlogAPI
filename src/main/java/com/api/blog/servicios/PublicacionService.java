package com.api.blog.servicios;

import com.api.blog.entidades.Publicacion;
import com.api.blog.entidades.Usuario;
import com.api.blog.excepciones.NotFoundException;
import java.util.List;


public interface PublicacionService {
    
    public List<Publicacion> obtenerPublicaciones();
    public Publicacion obtenerPublicacion(Long id) throws NotFoundException;
    public List<Publicacion> obtenerPublicacionesPorAutor(Usuario autor);
    public Publicacion guardarPublicacion(Publicacion publicacion);
    public Publicacion actualizarPublicacion(Publicacion publicacion);
    public Publicacion eliminarPublicacion(Long publicacionId);
    
    
}
