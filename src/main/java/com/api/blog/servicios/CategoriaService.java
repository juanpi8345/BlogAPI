
package com.api.blog.servicios;

import com.api.blog.entidades.Categoria;
import com.api.blog.excepciones.NotFoundException;
import java.util.List;

public interface CategoriaService {
    
    public List<Categoria> obtenerCategorias();
    public Categoria obtenerCategoria(Long categoriaId) throws NotFoundException;
    public Categoria guardarCategoria(Categoria categoria);
    public Categoria eliminarCategoria(Long categoriaId)  throws NotFoundException;
    
}
