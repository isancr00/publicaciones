/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author sanch
 */
@Entity
@Table(name = "publicaciones")
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPublicacion;
    
    @Column(name = "titulo")
    private String titulo;
    
    @Column(name = "cuerpo")
    private String cuerpo;
    
    @Column(name = "comentarioprofesor")
    private String comentarioProfesor;
        
    @Column(name = "valoracion")
    private String valoracion;
    @Column(name = "fecha")
    private Date fecha;
    
            
    @JoinColumn(name = "idpersona")
    @OneToOne(cascade = CascadeType.PERSIST)
    private Persona persona;
    
     @JoinColumn(name = "idcategoria")
    @OneToOne(cascade = CascadeType.PERSIST)
    private Categoria categoria;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.idPublicacion;
        hash = 29 * hash + Objects.hashCode(this.titulo);
        hash = 29 * hash + Objects.hashCode(this.cuerpo);
        hash = 29 * hash + Objects.hashCode(this.comentarioProfesor);
        hash = 29 * hash + Objects.hashCode(this.valoracion);
        hash = 29 * hash + Objects.hashCode(this.fecha);
        hash = 29 * hash + Objects.hashCode(this.persona);
        hash = 29 * hash + Objects.hashCode(this.categoria);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Publicacion other = (Publicacion) obj;
        if (this.idPublicacion != other.idPublicacion) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.cuerpo, other.cuerpo)) {
            return false;
        }
        if (!Objects.equals(this.comentarioProfesor, other.comentarioProfesor)) {
            return false;
        }
        if (!Objects.equals(this.valoracion, other.valoracion)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.persona, other.persona)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        return true;
    }
}
