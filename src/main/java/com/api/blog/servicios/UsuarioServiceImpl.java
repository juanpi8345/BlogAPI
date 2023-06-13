package com.api.blog.servicios;

import com.api.blog.entidades.Rol;
import com.api.blog.entidades.Usuario;
import com.api.blog.excepciones.NotFoundException;
import com.api.blog.repositorios.RolRepository;
import com.api.blog.repositorios.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private RolRepository rolRepository;

    @Override
    public Usuario obtenerUsuario(Long idUsuario)  throws  NotFoundException {
        return usuarioRepository.findById(idUsuario).
                orElseThrow(()->new NotFoundException("Usuario con el id "+idUsuario + "no encontrado"));
    }
    
   @Override
    public Usuario guardarUsuario(Usuario usuarioRequest) {
        Usuario usuario = usuarioRepository.findByUsername(usuarioRequest.getUsername());
        usuarioRepository.save(usuarioRequest);
        return usuario;
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> obtenerUsuariosPorRol(Rol rol) {
        return usuarioRepository.findAllByRol(rol);
    }

    @Override
    public Usuario eliminarUsuario(Long idUsuario) throws  NotFoundException{
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
        if(usuario != null){
            usuarioRepository.deleteById(idUsuario);
            return usuario;
        }else{
            throw new  NotFoundException("Usuario con el id "+idUsuario + " no encontrado");
        } 
    }

  
    
}
