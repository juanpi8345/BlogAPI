package com.api.blog.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    public Usuario() {
    }

    public Usuario(Long usuarioId, String username, String password, Rol rol) {
        this.usuarioId = usuarioId;
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean enabled = true;

   @ManyToOne()
   @JoinColumn(name = "rol_id")
   @JsonIgnore
    private Rol rol;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Comentario> comentarios = new HashSet<>();
    
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Publicacion> publicaciones = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       Set<Authority> autoridades = new HashSet<>();
       autoridades.add(new Authority(rol.getNombre()));
       return autoridades;
       
    }

    @Override
    public boolean isAccountNonExpired() {
       return true;
    }

    @Override
    public boolean isAccountNonLocked() {
      return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
       return true;
    }


}
