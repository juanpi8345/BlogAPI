
package com.api.blog.servicios;

import com.api.blog.entidades.Categoria;
import com.api.blog.excepciones.NotFoundException;
import com.api.blog.repositorios.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Override
    public List<Categoria> obtenerCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria obtenerCategoria(Long categoriaId) throws NotFoundException{
        return categoriaRepository.findById(categoriaId).
                orElseThrow(()-> new NotFoundException("Categoria con el id "+categoriaId + " no encontrada"));
    }

    @Override
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria eliminarCategoria(Long categoriaId) throws NotFoundException{
       Categoria categoria = categoriaRepository.findById(categoriaId).
               orElseThrow(()-> new NotFoundException("Categoria con el id "+categoriaId + " no encontrada"));
       categoriaRepository.delete(categoria);
       return categoria;
    }
    
}
