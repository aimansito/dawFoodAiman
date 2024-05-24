/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aiman
 */
@Entity
@Table(name = "TPV")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tpv.findAll", query = "SELECT t FROM Tpv t"),
    @NamedQuery(name = "Tpv.findByIdTPV", query = "SELECT t FROM Tpv t WHERE t.idTPV = :idTPV"),
    @NamedQuery(name = "Tpv.findByUbicacion", query = "SELECT t FROM Tpv t WHERE t.ubicacion = :ubicacion"),
    @NamedQuery(name = "Tpv.findByFechaHora", query = "SELECT t FROM Tpv t WHERE t.fechaHora = :fechaHora"),
    @NamedQuery(name = "Tpv.findByContrase\u00f1a", query = "SELECT t FROM Tpv t WHERE t.contrase\u00f1a = :contrase\u00f1a")})
public class Tpv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTPV")
    private Integer idTPV;
    @Basic(optional = false)
    @Column(name = "ubicacion")
    private String ubicacion;
    @Basic(optional = false)
    @Column(name = "fechaHora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @Basic(optional = false)
    @Column(name = "contrase\u00f1a")
    private String contraseña;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTPV")
    private Collection<Ticket> ticketCollection;

    public Tpv() {
    }

    public Tpv(Integer idTPV) {
        this.idTPV = idTPV;
    }

    public Tpv(Integer idTPV, String ubicacion, Date fechaHora, String contraseña) {
        this.idTPV = idTPV;
        this.ubicacion = ubicacion;
        this.fechaHora = fechaHora;
        this.contraseña = contraseña;
    }

    public Integer getIdTPV() {
        return idTPV;
    }

    public void setIdTPV(Integer idTPV) {
        this.idTPV = idTPV;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @XmlTransient
    public Collection<Ticket> getTicketCollection() {
        return ticketCollection;
    }

    public void setTicketCollection(Collection<Ticket> ticketCollection) {
        this.ticketCollection = ticketCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTPV != null ? idTPV.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tpv)) {
            return false;
        }
        Tpv other = (Tpv) object;
        if ((this.idTPV == null && other.idTPV != null) || (this.idTPV != null && !this.idTPV.equals(other.idTPV))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Tpv[ idTPV=" + idTPV + " ]";
    }
    
}
