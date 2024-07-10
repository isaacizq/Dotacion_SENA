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
@Table(name = "elementos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Elementos.findAll", query = "SELECT e FROM Elementos e"),
    @NamedQuery(name = "Elementos.findByIdelemento", query = "SELECT e FROM Elementos e WHERE e.idelemento = :idelemento"),
    @NamedQuery(name = "Elementos.findByNombre", query = "SELECT e FROM Elementos e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Elementos.findByCantidades", query = "SELECT e FROM Elementos e WHERE e.cantidades = :cantidades")})
public class Elementos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Idelemento")
    private Integer idelemento;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "cantidades")
    private Integer cantidades;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "elementosIdelemento")
    private List<Descuento> descuentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "elementosIdelemento")
    private List<Dotacion> dotacionList;

    public Elementos() {
    }

    public Elementos(Integer idelemento) {
        this.idelemento = idelemento;
    }

    public Elementos(Integer idelemento, String nombre) {
        this.idelemento = idelemento;
        this.nombre = nombre;
    }

    public Integer getIdelemento() {
        return idelemento;
    }

    public void setIdelemento(Integer idelemento) {
        this.idelemento = idelemento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidades() {
        return cantidades;
    }

    public void setCantidades(Integer cantidades) {
        this.cantidades = cantidades;
    }

    @XmlTransient
    public List<Descuento> getDescuentoList() {
        return descuentoList;
    }

    public void setDescuentoList(List<Descuento> descuentoList) {
        this.descuentoList = descuentoList;
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
        hash += (idelemento != null ? idelemento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Elementos)) {
            return false;
        }
        Elementos other = (Elementos) object;
        if ((this.idelemento == null && other.idelemento != null) || (this.idelemento != null && !this.idelemento.equals(other.idelemento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
