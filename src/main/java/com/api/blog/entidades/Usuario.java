package com.api.blog.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "usuarios")
public class Usuario {

    public Usuario() {
    }

    public Usuario(Long usuarioId, String username, String clave, Rol rol) {
        this.usuarioId = usuarioId;
        this.username = username;
        this.clave = clave;
        this.rol = rol;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String clave;

    @Column(nullable = false)
    private boolean habilitado = true;

   @ManyToOne()
   @JoinColumn(name = "rol_id")
   @JsonIgnore
    private Rol rol;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Set<Comentario> comentarios = new HashSet<>();

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private Set<Publicacion> publicaciones = new HashSet<>();

}
