/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aiman
 */
@Entity
@Table(name = "tipoProducto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoProducto.findAll", query = "SELECT t FROM TipoProducto t"),
    @NamedQuery(name = "TipoProducto.findByCodTipoProducto", query = "SELECT t FROM TipoProducto t WHERE t.codTipoProducto = :codTipoProducto"),
    @NamedQuery(name = "TipoProducto.findByNomCat", query = "SELECT t FROM TipoProducto t WHERE t.nomCat = :nomCat"),
    @NamedQuery(name = "TipoProducto.findByTipoProdDescripcion", query = "SELECT t FROM TipoProducto t WHERE t.tipoProdDescripcion = :tipoProdDescripcion")})
public class TipoProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codTipoProducto")
    private Integer codTipoProducto;
    @Basic(optional = false)
    @Column(name = "nomCat")
    private String nomCat;
    @Basic(optional = false)
    @Column(name = "tipoProdDescripcion")
    private String tipoProdDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codTipoProducto")
    private Collection<Producto> productoCollection;

    public TipoProducto() {
    }

    public TipoProducto(Integer codTipoProducto) {
        this.codTipoProducto = codTipoProducto;
    }

    public TipoProducto(Integer codTipoProducto, String nomCat, String tipoProdDescripcion) {
        this.codTipoProducto = codTipoProducto;
        this.nomCat = nomCat;
        this.tipoProdDescripcion = tipoProdDescripcion;
    }

    public Integer getCodTipoProducto() {
        return codTipoProducto;
    }

    public void setCodTipoProducto(Integer codTipoProducto) {
        this.codTipoProducto = codTipoProducto;
    }

    public String getNomCat() {
        return nomCat;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }

    public String getTipoProdDescripcion() {
        return tipoProdDescripcion;
    }

    public void setTipoProdDescripcion(String tipoProdDescripcion) {
        this.tipoProdDescripcion = tipoProdDescripcion;
    }

    @XmlTransient
    public Collection<Producto> getProductoCollection() {
        return productoCollection;
    }

    public void setProductoCollection(Collection<Producto> productoCollection) {
        this.productoCollection = productoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codTipoProducto != null ? codTipoProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoProducto)) {
            return false;
        }
        TipoProducto other = (TipoProducto) object;
        if ((this.codTipoProducto == null && other.codTipoProducto != null) || (this.codTipoProducto != null && !this.codTipoProducto.equals(other.codTipoProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.TipoProducto[ codTipoProducto=" + codTipoProducto + " ]";
    }
    
}
