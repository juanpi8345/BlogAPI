
package com.api.blog.controladores;

import com.api.blog.entidades.Categoria;
import com.api.blog.entidades.Publicacion;
import com.api.blog.entidades.Usuario;
import com.api.blog.excepciones.NotFoundException;
import com.api.blog.servicios.CategoriaService;
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
import org.springframework.web.bind.annotation.PutMapping;
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
    
    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping("/")
    public ResponseEntity<List<Publicacion>> obtenerPublicaciones(){
        List<Publicacion> publicaciones =  publicacionService.obtenerPublicaciones();
        if(!publicaciones.isEmpty()){
            return ResponseEntity.ok(publicaciones);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Set<Publicacion>> obtenerPublicacionesDelUsuario(@PathVariable Long usuarioId) throws NotFoundException{
        Usuario usuario = usuarioService.obtenerUsuario(usuarioId);
        if(usuario != null){
           return ResponseEntity.ok(usuario.getPublicaciones());
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/usuario/{usuarioId}/publicacion/{publicacionId}")
    public ResponseEntity<Publicacion> obtenerPublicacionDelUsuario(@PathVariable Long usuarioId, @PathVariable Long publicacionId) throws NotFoundException{
        Usuario usuario = usuarioService.obtenerUsuario(usuarioId);
        Set<Publicacion> publicacionesUsuario = usuario.getPublicaciones();
        
        for(Publicacion publicacion : publicacionesUsuario){
            if(publicacion.getPublicacionId().equals(publicacionId)){
                return ResponseEntity.ok(publicacion);
            }
        }
        
        return ResponseEntity.notFound().build();
    
    }
    
    @PostMapping("/usuario/{usuarioId}/categoria/{categoriaId}")
    public ResponseEntity<Publicacion> guardarPublicacion(@Valid @RequestBody Publicacion publicacion, @PathVariable Long usuarioId,
                                                                                                @PathVariable Long categoriaId) throws NotFoundException{
        Usuario usuario = usuarioService.obtenerUsuario(usuarioId);
        Categoria categoria = categoriaService.obtenerCategoria(categoriaId);
        if(usuario != null && categoria != null){
                publicacion.setAutor(usuario);
                categoria.getPublicaciones().add(publicacion);
                publicacion.setCategoria(categoria);
                usuario.getPublicaciones().add(publicacion);
                usuarioService.guardarUsuario(usuario);
                return ResponseEntity.ok().build();
        }else{
            return (ResponseEntity<Publicacion>) ResponseEntity.notFound();
        }
    }
    
    @PutMapping("/usuario/{usuarioId}")
    public ResponseEntity<Publicacion> actualizarPublicacion(@Valid @RequestBody Publicacion publicacion,
                                                                                                       @PathVariable Long usuarioId ) throws NotFoundException{
        
        Usuario usuario = usuarioService.obtenerUsuario(usuarioId);
        Set<Publicacion> publicacionesUsuario = usuario.getPublicaciones();
        
        for(Publicacion p : publicacionesUsuario){
            System.out.println(publicacion.getPublicacionId());
            if(p.getPublicacionId().equals(publicacion.getPublicacionId())){
                
                Publicacion publicacionActualizada = publicacionService.actualizarPublicacion(publicacion);
                return ResponseEntity.ok(publicacionActualizada);
            }
        }
        
        return ResponseEntity.notFound().build();
        
    }
    
    @DeleteMapping("/usuario/{usuarioId}/publicacion/{publicacionId}")
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
