/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    @NamedQuery(name = "DetalleTicket.findByIdTicket", query = "SELECT d FROM DetalleTicket d WHERE d.detalleTicketPK.idTicket = :idTicket"),
    @NamedQuery(name = "DetalleTicket.findByIdProducto", query = "SELECT d FROM DetalleTicket d WHERE d.detalleTicketPK.idProducto = :idProducto"),
    @NamedQuery(name = "DetalleTicket.findByCantidadProducto", query = "SELECT d FROM DetalleTicket d WHERE d.cantidadProducto = :cantidadProducto")})
public class DetalleTicket implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleTicketPK detalleTicketPK;
    @Basic(optional = false)
    @Column(name = "cantidadProducto")
    private int cantidadProducto;
    @JoinColumn(name = "idProducto", referencedColumnName = "idProducto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producto producto;
    @JoinColumn(name = "idTicket", referencedColumnName = "idTicket", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ticket ticket;

    public DetalleTicket() {
    }

    public DetalleTicket(DetalleTicketPK detalleTicketPK) {
        this.detalleTicketPK = detalleTicketPK;
    }

    public DetalleTicket(DetalleTicketPK detalleTicketPK, int cantidadProducto) {
        this.detalleTicketPK = detalleTicketPK;
        this.cantidadProducto = cantidadProducto;
    }

    public DetalleTicket(int idTicket, int idProducto) {
        this.detalleTicketPK = new DetalleTicketPK(idTicket, idProducto);
    }

    public DetalleTicketPK getDetalleTicketPK() {
        return detalleTicketPK;
    }

    public void setDetalleTicketPK(DetalleTicketPK detalleTicketPK) {
        this.detalleTicketPK = detalleTicketPK;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleTicketPK != null ? detalleTicketPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleTicket)) {
            return false;
        }
        DetalleTicket other = (DetalleTicket) object;
        if ((this.detalleTicketPK == null && other.detalleTicketPK != null) || (this.detalleTicketPK != null && !this.detalleTicketPK.equals(other.detalleTicketPK))) {
            return false;
        }
        return true;
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("---------------Tacos Aiman Ticket---------------");{
//        for (Object object : col) {
//            
//        }
//    }
//    }

    public void setIdProducto(Producto producto) {
        this.producto = producto;
    }

    public Producto getIdProducto() {
        return this.producto = producto;
    }
    
}
