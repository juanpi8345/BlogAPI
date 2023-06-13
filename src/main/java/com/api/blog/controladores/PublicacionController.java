
package com.api.blog.controladores;

import com.api.blog.entidades.Publicacion;
import com.api.blog.entidades.Usuario;
import com.api.blog.excepciones.NotFoundException;
import com.api.blog.servicios.PublicacionService;
import com.api.blog.servicios.UsuarioService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {
    
    @Autowired
    private PublicacionService publicacionService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/usuario/{usuarioId}")
    public Set<Publicacion> obtenerPublicacionesDelUsuario(@PathVariable Long usuarioId) throws NotFoundException{
        Usuario usuario = usuarioService.obtenerUsuario(usuarioId);
        if(usuario != null){
           return usuario.getPublicaciones();
        }
        return null;
    }
    
    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<Publicacion> guardarPublicacion(@Valid @RequestBody Publicacion publicacion, @PathVariable Long usuarioId) throws NotFoundException{
        Usuario usuario = usuarioService.obtenerUsuario(usuarioId);
        if(usuario != null){
                publicacion.setAutor(usuario);
                usuario.getPublicaciones().add(publicacion);
                usuarioService.guardarUsuario(usuario);
                return ResponseEntity.ok().build();
        }else{
            return (ResponseEntity<Publicacion>) ResponseEntity.notFound();
        }
    }
    
    @DeleteMapping("/usuario/{usuarioId}/{publicacionId}")
    public ResponseEntity<?> eliminarPublicacionDelUsuario(@PathVariable Long usuarioId, @PathVariable Long publicacionId) throws NotFoundException{
        Usuario usuario = usuarioService.obtenerUsuario(usuarioId);
        Set<Publicacion> publicacionesDelUsuario = usuario.getPublicaciones();
        for(Publicacion publicacion : publicacionesDelUsuario){
            if(publicacion.getPublicacionId().equals(publicacionId)){
                publicacionesDelUsuario.remove(publicacion);
                usuarioService.guardarUsuario(usuario);
                publicacionService.eliminarPublicacion(publicacionId);
                return ResponseEntity.ok().build();
            }
        }
        
        return ResponseEntity.notFound().build();
       
    }
    
   
    
    
}
