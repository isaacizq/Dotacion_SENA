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
@Table(name = "sexo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sexo.findAll", query = "SELECT s FROM Sexo s"),
    @NamedQuery(name = "Sexo.findByIdsexo", query = "SELECT s FROM Sexo s WHERE s.idsexo = :idsexo"),
    @NamedQuery(name = "Sexo.findByNombre", query = "SELECT s FROM Sexo s WHERE s.nombre = :nombre")})
public class Sexo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Idsexo")
    private Integer idsexo;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sexoIdsexo")
    private List<CaracterizarInstructor> caracterizarInstructorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sexoIdsexo")
    private List<Dotacion> dotacionList;

    public Sexo() {
    }

    public Sexo(Integer idsexo) {
        this.idsexo = idsexo;
    }

    public Sexo(Integer idsexo, String nombre) {
        this.idsexo = idsexo;
        this.nombre = nombre;
    }

    public Integer getIdsexo() {
        return idsexo;
    }

    public void setIdsexo(Integer idsexo) {
        this.idsexo = idsexo;
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
        hash += (idsexo != null ? idsexo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sexo)) {
            return false;
        }
        Sexo other = (Sexo) object;
        if ((this.idsexo == null && other.idsexo != null) || (this.idsexo != null && !this.idsexo.equals(other.idsexo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
