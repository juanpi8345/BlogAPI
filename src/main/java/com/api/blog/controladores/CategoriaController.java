
package com.api.blog.controladores;

import com.api.blog.entidades.Categoria;
import com.api.blog.excepciones.NotFoundException;
import com.api.blog.servicios.CategoriaService;
import java.util.List;
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
@RequestMapping("/categorias")
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping("/")
    public ResponseEntity<List<Categoria>> obtenerCategorias(){
        return ResponseEntity.ok(categoriaService.obtenerCategorias());
    }
    
    @GetMapping("/{categoriaId}")
    public ResponseEntity<Categoria> obtenerCategoria(@PathVariable Long categoriaId) throws NotFoundException{
        Categoria categoria = categoriaService.obtenerCategoria(categoriaId);
        if(categoria!=null){
            return ResponseEntity.ok(categoria);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping("/")
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria){
        return ResponseEntity.ok(categoriaService.guardarCategoria(categoria));
    }
    
    @DeleteMapping("/{categoriaId}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable Long categoriaId) throws NotFoundException{
        Categoria categoria = categoriaService.obtenerCategoria(categoriaId);
        if(categoria!=null){
            return ResponseEntity.ok(categoriaService.eliminarCategoria(categoriaId));
        }
        return ResponseEntity.notFound().build();
    }
    
    
    
}
