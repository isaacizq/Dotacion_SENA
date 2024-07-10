/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author isaac
 */
@Entity
@Table(name = "clima")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clima.findAll", query = "SELECT c FROM Clima c"),
    @NamedQuery(name = "Clima.findByIdclima", query = "SELECT c FROM Clima c WHERE c.idclima = :idclima"),
    @NamedQuery(name = "Clima.findByNombre", query = "SELECT c FROM Clima c WHERE c.nombre = :nombre")})
public class Clima implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Idclima")
    private Integer idclima;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "climaIdclima")
    private List<CaracterizarInstructor> caracterizarInstructorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "climaIdclima")
    private List<Dotacion> dotacionList;

    public Clima() {
    }

    public Clima(Integer idclima) {
        this.idclima = idclima;
    }

    public Clima(Integer idclima, String nombre) {
        this.idclima = idclima;
        this.nombre = nombre;
    }

    public Integer getIdclima() {
        return idclima;
    }

    public void setIdclima(Integer idclima) {
        this.idclima = idclima;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<CaracterizarInstructor> getCaracterizarInstructorList() {
        return caracterizarInstructorList;
    }

    public void setCaracterizarInstructorList(List<CaracterizarInstructor> caracterizarInstructorList) {
        this.caracterizarInstructorList = caracterizarInstructorList;
    }

    @XmlTransient
    public List<Dotacion> getDotacionList() {
        return dotacionList;
    }

    public void setDotacionList(List<Dotacion> dotacionList) {
        this.dotacionList = dotacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idclima != null ? idclima.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clima)) {
            return false;
        }
        Clima other = (Clima) object;
        if ((this.idclima == null && other.idclima != null) || (this.idclima != null && !this.idclima.equals(other.idclima))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
