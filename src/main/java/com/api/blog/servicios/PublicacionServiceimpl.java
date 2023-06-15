
package com.api.blog.servicios;

import com.api.blog.entidades.Publicacion;
import com.api.blog.entidades.Usuario;
import com.api.blog.repositorios.PublicacionRepository;
import com.api.blog.repositorios.UsuarioRepository;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicacionServiceimpl implements PublicacionService{
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public List<Publicacion> obtenerPublicaciones() {
        return publicacionRepository.findAll();
    }

    @Override
    public List<Publicacion> obtenerPublicacionesPorAutor(Usuario autor) {
      usuarioRepository.findById(autor.getUsuarioId()).
              orElseThrow(()-> new NotFoundException("Usuario con el id "+autor.getUsuarioId() + " no encontrado"));
      return  publicacionRepository.findByAutor(autor);
    }

    @Override
    public Publicacion guardarPublicacion(Publicacion publicacion) {
       return publicacionRepository.save(publicacion);
    }

    @Override
    public Publicacion actualizarPublicacion(Publicacion publicacionRequest) {
       return publicacionRepository.findById(publicacionRequest.getPublicacionId()).map(publicacion->{
           publicacion.setTitulo(publicacionRequest.getTitulo());
           publicacion.setFechaActualizacion(publicacionRequest.getFechaActualizacion());
           publicacion.setDescripcion(publicacionRequest.getDescripcion());
           publicacion.setContenido(publicacionRequest.getContenido());
           return publicacionRepository.save(publicacion);
       }).orElseThrow(()-> new NotFoundException("Publicacion con el id "+publicacionRequest.getPublicacionId() + " no encontrada"));
    }

    @Override
    public Publicacion eliminarPublicacion(Long publicacionId) {
       Publicacion publicacion = publicacionRepository.findById(publicacionId).
               orElseThrow(()-> new NotFoundException("Publicacion con el id "+publicacionId + " no encontrada"));
       publicacionRepository.delete(publicacion);
       return publicacion;
    }

    @Override
    public Publicacion obtenerPublicacion(Long id) throws NotFoundException {
        Publicacion publicacion = publicacionRepository.findById(id).
               orElseThrow(()-> new NotFoundException("Publicacion con el id "+id + " no encontrada"));
        return publicacion;
    }
    
}
