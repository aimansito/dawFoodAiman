/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author aiman
 */
@Embeddable
public class DetalleTicketPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idTicket")
    private int idTicket;
    @Basic(optional = false)
    @Column(name = "idProducto")
    private int idProducto;

    public DetalleTicketPK() {
    }

    public DetalleTicketPK(int idTicket, int idProducto) {
        this.idTicket = idTicket;
        this.idProducto = idProducto;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTicket;
        hash += (int) idProducto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleTicketPK)) {
            return false;
        }
        DetalleTicketPK other = (DetalleTicketPK) object;
        if (this.idTicket != other.idTicket) {
            return false;
        }
        if (this.idProducto != other.idProducto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.DetalleTicketPK[ idTicket=" + idTicket + ", idProducto=" + idProducto + " ]";
    }
    
}
