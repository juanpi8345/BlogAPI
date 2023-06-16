package com.api.blog.servicios;

import com.api.blog.entidades.Rol;
import com.api.blog.entidades.Usuario;
import com.api.blog.excepciones.NotFoundException;
import java.util.List;


public interface UsuarioService {
    
    public Usuario obtenerUsuario(Long idUsuario) throws NotFoundException;
    public Usuario obtenerUsuarioPorNombre(String username);
    public Usuario guardarUsuario(Usuario usuario);
    public List<Usuario> obtenerUsuarios();
    public List<Usuario> obtenerUsuariosPorRol(Rol rol);
    public Usuario eliminarUsuario(Long idUsuario) throws  NotFoundException;
    
}
