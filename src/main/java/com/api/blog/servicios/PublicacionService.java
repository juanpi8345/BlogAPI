package com.api.blog.servicios;

import com.api.blog.entidades.Categoria;
import com.api.blog.entidades.Publicacion;
import com.api.blog.entidades.Usuario;
import com.api.blog.excepciones.NotFoundException;
import java.util.List;



public interface PublicacionService {
    
    public List<Publicacion> obtenerPublicaciones();
    public List<Publicacion> obtenerPublicacionesPorCategoria(Categoria categoria) throws NotFoundException;
    public Publicacion obtenerPublicacion(Long id) throws NotFoundException;
    public List<Publicacion> obtenerPublicacionesPorAutor(Usuario autor)  throws NotFoundException;
    public Publicacion guardarPublicacion(Publicacion publicacion);
    public Publicacion actualizarPublicacion(Publicacion publicacion) throws NotFoundException;
    public Publicacion eliminarPublicacion(Long publicacionId) throws NotFoundException;
    
    
}
