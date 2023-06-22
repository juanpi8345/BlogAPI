
package com.api.blog.controladores;

import com.api.blog.entidades.Comentario;
import com.api.blog.entidades.Publicacion;
import com.api.blog.entidades.Usuario;
import com.api.blog.entidades.ComentarioDTO;
import com.api.blog.excepciones.NotFoundException;
import com.api.blog.servicios.ComentarioService;
import com.api.blog.servicios.PublicacionService;
import com.api.blog.servicios.UsuarioService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/comentarios")
public class ComentarioController {
    
    @Autowired
    private ComentarioService comentarioService;
    
    @Autowired
    private PublicacionService publicacionService;
    
    @Autowired
    private UsuarioService usuarioService;
    
  /*  @GetMapping("/publicacion/{publicacionId}")
    public ResponseEntity<List<Comentario>> obtenerComentariosPorPublicacion(@PathVariable Long publicacionId) throws NotFoundException{
        Publicacion publicacion = publicacionService.obtenerPublicacion(publicacionId);
        if(publicacion != null){
         List <Comentario> comentarios = comentarioService.obtenerComentariosDePublicacion(publicacion);
        return ResponseEntity.ok(comentarios);
        }
        return ResponseEntity.notFound().build();
    }*/
    
    @GetMapping("/publicacion/{publicacionId}")
    public ResponseEntity<List<ComentarioDTO>> obtenerComentariosConAutor(@PathVariable Long publicacionId) throws NotFoundException{
        Publicacion publicacion = publicacionService.obtenerPublicacion(publicacionId);
        if(publicacion != null){
         List <ComentarioDTO> comentarios = comentarioService.obtenerComentariosDePublicacionConAutor(publicacion);
        return ResponseEntity.ok(comentarios);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping("/publicacion/{publicacionId}/usuario/{usuarioId}")
    public ResponseEntity<Comentario> crearComentario(@Valid @RequestBody Comentario comentario,
                                                                                        @PathVariable Long publicacionId,
                                                                                        @PathVariable Long usuarioId) throws NotFoundException{
        Publicacion publicacion = publicacionService.obtenerPublicacion(publicacionId);
        Usuario usuario = usuarioService.obtenerUsuario(usuarioId);
        if(publicacion != null && usuario != null){
            usuario.getComentarios().add(comentario);
            comentario.setUsuario(usuario);
            comentarioService.guardarComentario(comentario, publicacion);
            return ResponseEntity.ok(comentario);
        }
        
        return ResponseEntity.notFound().build();
   
    }
    
    @DeleteMapping("/{comentarioId}")
    public ResponseEntity<?> eliminarComentario(@PathVariable Long comentarioId) throws NotFoundException{
        return ResponseEntity.ok(comentarioService.eliminarComentario(comentarioId));
    }
    
  
    
}
