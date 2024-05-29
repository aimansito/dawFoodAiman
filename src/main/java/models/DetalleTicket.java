/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controllers.DetalleTicketJpaController;
import controllers.ProductoJpaController;
import controllers.TicketJpaController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
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
    //representa una clave primaria compuesta
    @EmbeddedId
    protected DetalleTicketPK detalleTicketPK;
    @Basic(optional = false)
    @Column(name = "cantidadProducto")
    private int cantidadProducto;
    // relacion entre Producto y detalleTicket , enlazando su clave primaria
    @JoinColumn(name = "idProducto", referencedColumnName = "idProducto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producto producto;
    // relacion entre ticket y detalleTicket , enlazando su clave primaria
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

    @Override
    public String toString() {
        DetalleTicketJpaController dt = new DetalleTicketJpaController();
        List<DetalleTicket> det = dt.findDetalleTicketEntities();
        ProductoJpaController prod = new ProductoJpaController();
        TicketJpaController tk = new TicketJpaController();
        StringBuilder sb = new StringBuilder();

        sb.append("\n---------------Tacos Aiman Ticket---------------\n");

        for (DetalleTicket detalle : det) {
            Ticket ticket = tk.findTicket(detalle.getDetalleTicketPK().getIdTicket());

            Producto producto = prod.findProducto(detalle.getDetalleTicketPK().getIdProducto());

            sb.append("ID Ticket: ").append(detalle.getDetalleTicketPK().getIdTicket()).append("\n");
            sb.append("ID Producto: ").append(detalle.getDetalleTicketPK().getIdProducto()).append("\n");
            sb.append("Descripción: ").append(producto.getDescripcion()).append("\n");
            sb.append("Precio por producto: ").append(producto.getPrecio()).append("\n");
            sb.append("Precio final: ").append(ticket.getImporteTotal()).append("\n");
            sb.append("--------------------------------------\n");
        }
        return sb.toString();

    }

    public String mostrarTicket(int id) {
        DetalleTicketJpaController dt = new DetalleTicketJpaController();
        List<DetalleTicket> det;
        ProductoJpaController prod = new ProductoJpaController();
        TicketJpaController tk = new TicketJpaController();
        StringBuilder sb = new StringBuilder();

        Ticket t = tk.findTicket(id);
        if (t != null) {
            det = new ArrayList<>(t.getDetalleTicketCollection());

            sb.append("\n---------------Tacos Aiman Ticket---------------\n");

            for (DetalleTicket detalle : det) {
                // No es necesario verificar detalle.getTicket().getIdTicket() == id ya que los detalles ya están asociados al ticket con el ID proporcionado
                Producto producto = prod.findProducto(detalle.getDetalleTicketPK().getIdProducto());

                sb.append("ID Ticket: ").append(detalle.getDetalleTicketPK().getIdTicket()).append("\n");
                sb.append("ID Producto: ").append(detalle.getDetalleTicketPK().getIdProducto()).append("\n");
                sb.append("Descripción: ").append(producto.getDescripcion()).append("\n");
                sb.append("Precio por producto: ").append(producto.getPrecio()).append("\n");
                sb.append("Precio final: ").append(t.getImporteTotal()).append("\n");
                sb.append("--------------------------------------\n");
            }
        } else {
            sb.append("No se encontró el ticket con el ID: ").append(id).append("\n");
        }

        return sb.toString();
    }

//    // Método que me devuelve un ticket de la bbdd a partir de un idTicket
//    public static String metodoDeConsultarTickets(int idTicket) {
//
//        // Busco el Ticket con ese id
//        Ticket ticketaux = tr.findTicket(idTicket);
//
//        // Cogo el collections de detallesTickets que le paso cuando creo ese ticket
//        List<Detalleventa> listaDetallesVentas = new ArrayList<>();
//        listaDetallesVentas = (List<Detalleventa>) ticketaux.getDetalleventaCollection();
//
//        Map<Productos, Integer> mapProductosDeEseTicket = new HashMap<>();
//
//        // Recorro la lista
//        for (Detalleventa detAuxx : listaDetallesVentas) {
//
//            Productos productoAuxx = pr.findProductos(detAuxx.getDetalleventaPK().getIdProducto());
//            // Si es nulo el pructo no hacemos nada
//            if (productoAuxx != null) {
//                mapProductosDeEseTicket.put(productoAuxx, detAuxx.getCantidadProducto());
//            } else {
//                System.out.println("Producto no encontrado");
//            }
//        }
//        Carrito carritoAux = new Carrito(mapProductosDeEseTicket);
//        StringBuilder sb = new StringBuilder();
//        sb.append("**DAWFOOD**\n");
//        sb.append("IDTICKET:").append(ticketaux.getIdTicket()).append("\n");
//        sb.append("CODTRANSACCIÓN: ").append(ticketaux.getCodTransaccion()).append("\n\n");
//        sb.append(carritoAux.toStringVerCarrito()).append("\n");
//        sb.append("***");
//        return sb.toString();
//    }

    public void setIdProducto(Producto producto) {
        this.producto = producto;
    }

    public Producto getIdProducto() {
        return this.producto = producto;
    }
}
