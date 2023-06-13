
package com.api.blog.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value= {"fecha_creacion", "fecha_actualizacion"},
        allowGetters = true
    )
public abstract  class  AuditModel implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion",nullable = false,updatable = false, unique = false)
    @CreatedDate()
    private Date fechaCreacion;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_actualizacion",nullable = false, unique = false)
    @LastModifiedDate
    private Date fechaActualizacion;

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
    
    
    
}
