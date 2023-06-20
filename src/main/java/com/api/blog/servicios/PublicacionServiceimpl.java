
package com.api.blog.servicios;

import com.api.blog.entidades.Categoria;
import com.api.blog.entidades.Publicacion;
import com.api.blog.entidades.Usuario;
import com.api.blog.excepciones.NotFoundException;
import com.api.blog.repositorios.CategoriaRepository;
import com.api.blog.repositorios.PublicacionRepository;
import com.api.blog.repositorios.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicacionServiceimpl implements PublicacionService{
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PublicacionRepository publicacionRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Publicacion> obtenerPublicaciones() {
        return publicacionRepository.findAll();
    }

    @Override
    public List<Publicacion> obtenerPublicacionesPorAutor(Usuario autor) throws NotFoundException {
      usuarioRepository.findById(autor.getUsuarioId()).
              orElseThrow(()-> new NotFoundException("Usuario con el id "+autor.getUsuarioId() + " no encontrado"));
      return  publicacionRepository.findByAutor(autor);
    }

    @Override
    public Publicacion guardarPublicacion(Publicacion publicacion) {
       return publicacionRepository.save(publicacion);
    }


    @Override
    public Publicacion actualizarPublicacion(Publicacion publicacionRequest)  throws NotFoundException {
       return publicacionRepository.findById(publicacionRequest.getPublicacionId()).map(publicacion->{
           publicacion.setTitulo(publicacionRequest.getTitulo());
           publicacion.setFechaActualizacion(publicacionRequest.getFechaActualizacion());
           publicacion.setDescripcion(publicacionRequest.getDescripcion());
           publicacion.setContenido(publicacionRequest.getContenido());
           return publicacionRepository.save(publicacion);
       }).orElseThrow(()-> new NotFoundException("Publicacion con el id "+publicacionRequest.getPublicacionId() + " no encontrada"));
    }

    @Override
    public Publicacion eliminarPublicacion(Long publicacionId) throws NotFoundException {
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

    @Override
    public List<Publicacion> obtenerPublicacionesPorCategoria(Categoria categoriaRequest) throws NotFoundException {
       Categoria categoria = categoriaRepository.findById(categoriaRequest.getCategoriaId()).
               orElseThrow(()-> new NotFoundException("Categoria con el id "+categoriaRequest.getCategoriaId() + " no encontrada"));
       return publicacionRepository.findAllByCategoria(categoria);
    }
    
}
