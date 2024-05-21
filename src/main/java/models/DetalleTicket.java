/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aiman
 */
@Entity
@Table(name = "detalleTicket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleTicket.findAll", query = "SELECT d FROM DetalleTicket d"),
    @NamedQuery(name = "DetalleTicket.findByCantidadProducto", query = "SELECT d FROM DetalleTicket d WHERE d.cantidadProducto = :cantidadProducto")})
public class DetalleTicket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cantidadProducto")
    private Integer cantidadProducto;
    @JoinColumn(name = "idProducto", referencedColumnName = "idProducto")
    @ManyToOne(optional = false)
    private Producto idProducto;
    @JoinColumn(name = "idTicket", referencedColumnName = "idTicket")
    @ManyToOne(optional = false)
    private Ticket idTicket;

    public DetalleTicket() {
    }

    public DetalleTicket(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public Ticket getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Ticket idTicket) {
        this.idTicket = idTicket;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cantidadProducto != null ? cantidadProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleTicket)) {
            return false;
        }
        DetalleTicket other = (DetalleTicket) object;
        if ((this.cantidadProducto == null && other.cantidadProducto != null) || (this.cantidadProducto != null && !this.cantidadProducto.equals(other.cantidadProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controllers.DetalleTicket[ cantidadProducto=" + cantidadProducto + " ]";
    }
    
}
