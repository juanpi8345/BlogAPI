package com.api.blog;

import com.api.blog.entidades.Rol;
import com.api.blog.entidades.Usuario;
import com.api.blog.servicios.RolService;
import com.api.blog.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner{
    
   @Autowired
    private UsuarioService usuarioService;
   
   @Autowired
   private RolService rolService;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        

      
    }

}
