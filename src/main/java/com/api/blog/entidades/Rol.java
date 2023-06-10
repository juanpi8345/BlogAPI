package com.api.blog.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "roles")
public class Rol {

    public Rol() {
    }

    public Rol(Long rolId, String nombre) {
        this.rolId = rolId;
        this.nombre = nombre;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rol_id")
    private Long rolId;
    
    @Column(unique = true, nullable = false)
    private String nombre;
    
    @OneToMany(mappedBy = "rol",cascade = CascadeType.ALL)
    Set<Usuario> usuarios = new HashSet<>();
    
}
