package com.api.blog.servicios;

import com.api.blog.entidades.Comentario;
import com.api.blog.entidades.ComentarioDTO;
import com.api.blog.entidades.Publicacion;
import com.api.blog.entidades.Usuario;
import com.api.blog.excepciones.NotFoundException;
import com.api.blog.repositorios.ComentarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Override
    public List<Comentario> obtenerComentariosDelUsuario(Usuario usuario) {
        return comentarioRepository.findAllByUsuario(usuario);
    }

    @Override
    public List<Comentario> obtenerComentariosDePublicacion(Publicacion publicacion) {
        return comentarioRepository.findAllByPublicacion(publicacion);
    }

    @Override
    public Comentario obtenerComentario(Long comentarioId) throws NotFoundException {
        return comentarioRepository.findById(comentarioId).
                orElseThrow(() -> new NotFoundException("Comentario con el id " + comentarioId + " no encontrado"));
    }

    @Override
    public Comentario guardarComentario(Comentario comentario, Publicacion publicacion) {
        publicacion.getComentarios().add(comentario);
        comentario.setPublicacion(publicacion);
        
        return comentarioRepository.save(comentario);
    }

    @Override
    public Comentario actualizarComentario(Comentario comentarioRequest) throws NotFoundException {
        return comentarioRepository.findById(comentarioRequest.getComentarioId()).map(comentario -> {
            comentario.setDescripcion(comentarioRequest.getDescripcion());
            return comentarioRepository.save(comentario);
        }).orElseThrow(() -> new NotFoundException("Comentario con el id " + comentarioRequest.getComentarioId() + " no encontrado"));
    }

    @Override
    public Comentario eliminarComentario(Long comentarioId) throws NotFoundException {
       Comentario comentario = comentarioRepository.findById(comentarioId).
               orElseThrow(() -> new NotFoundException("Comentario con el id " + comentarioId + " no encontrado"));
       comentarioRepository.delete(comentario);
       return comentario;
    }

    @Override
    public List<ComentarioDTO> obtenerComentariosDePublicacionConAutor(Publicacion publicacion)  throws NotFoundException{
        List<ComentarioDTO> comentariosConAutor = new ArrayList();
        List<Comentario> comentarios =  comentarioRepository.findAllByPublicacion(publicacion);
        for(Comentario c: comentarios){
            ComentarioDTO comentarioDto = new ComentarioDTO(c.getComentarioId(),c.getUsuario().getUsername(),c.getUsuario().getAuthorities(),c.getDescripcion());
            comentariosConAutor.add(comentarioDto);
        }
        return comentariosConAutor;
    }

}
