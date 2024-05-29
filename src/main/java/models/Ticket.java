/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Ticket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t"),
    @NamedQuery(name = "Ticket.findByIdTicket", query = "SELECT t FROM Ticket t WHERE t.idTicket = :idTicket"),
    @NamedQuery(name = "Ticket.findByNumPedido", query = "SELECT t FROM Ticket t WHERE t.numPedido = :numPedido"),
    @NamedQuery(name = "Ticket.findMaxNumPedido", query = "SELECT MAX(t.numPedido) FROM Ticket t"),
    @NamedQuery(name = "Ticket.findMaxIdPlusOne", query = "SELECT MAX(t.idTicket) + 1 FROM Ticket t"),
    @NamedQuery(name = "Ticket.findByFechaHora", query = "SELECT t FROM Ticket t WHERE t.fechaHora = :fechaHora")

})
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    // clave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTicket")
    private Integer idTicket;
    @Basic(optional = false)
    @Column(name = "numPedido")
    private int numPedido;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "importeTotal")
    private BigDecimal importeTotal;
    @Basic(optional = false)
    @Column(name = "fechaHora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    //relacion de uno a muchos un tpv contiene muchos tickets
    @JoinColumn(name = "idTPV", referencedColumnName = "idTPV")
    @ManyToOne(optional = false)
    private Tpv idTPV;
    //relacion de uno a muchos quiere decir que un ticket pertenece a varios detalleTicket
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ticket")
    private Collection<DetalleTicket> detalleTicketCollection;

    public Ticket() {
    }

    public Ticket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Ticket(int numPedido, BigDecimal importeTotal, Date fechaHora, Tpv idTpv) {
        this.numPedido = numPedido;
        this.importeTotal = importeTotal;
        this.fechaHora = fechaHora;
        this.idTPV = idTpv;
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }

    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Tpv getIdTPV() {
        return idTPV;
    }

    public void setIdTPV(Tpv idTPV) {
        this.idTPV = idTPV;
    }

    @XmlTransient
    public Collection<DetalleTicket> getDetalleTicketCollection() {
        return detalleTicketCollection;
    }

    public void setDetalleTicketCollection(Collection<DetalleTicket> detalleTicketCollection) {
        this.detalleTicketCollection = detalleTicketCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTicket != null ? idTicket.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.idTicket == null && other.idTicket != null) || (this.idTicket != null && !this.idTicket.equals(other.idTicket))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "Ticket{"
                + "idTicket=" + idTicket
                + ", numPedido=" + numPedido
                + ", importeTotal=" + importeTotal
                + ", fechaHora=" + (fechaHora != null ? dateFormat.format(fechaHora) : "null")
                + ", TPV ubicacion=" + idTPV.getUbicacion()
                + '}';
    }

}
