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
    @NamedQuery(name = "TipoProducto.findByTipoCat", query = "SELECT t FROM TipoProducto t WHERE t.tipoCat = :tipoCat"),
    @NamedQuery(name = "TipoProducto.findByCodTipoProducto", query = "SELECT t FROM TipoProducto t WHERE t.codTipoProducto = :codTipoProducto"),
    @NamedQuery(name = "TipoProducto.findBySubCategorias", query = "SELECT t FROM TipoProducto t WHERE t.subCategorias = :subCategorias")})
public class TipoProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "tipoCat")
    private int tipoCat;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codTipoProducto")
    private Integer codTipoProducto;
    @Basic(optional = false)
    @Column(name = "subCategorias")
    private int subCategorias;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codTipoProducto")
    private Collection<Producto> productoCollection;

    public TipoProducto() {
    }

    public TipoProducto(Integer codTipoProducto) {
        this.codTipoProducto = codTipoProducto;
    }

    public TipoProducto(Integer codTipoProducto, int tipoCat, int subCategorias) {
        this.codTipoProducto = codTipoProducto;
        this.tipoCat = tipoCat;
        this.subCategorias = subCategorias;
    }

    public int getTipoCat() {
        return tipoCat;
    }

    public void setTipoCat(int tipoCat) {
        this.tipoCat = tipoCat;
    }

    public Integer getCodTipoProducto() {
        return codTipoProducto;
    }

    public void setCodTipoProducto(Integer codTipoProducto) {
        this.codTipoProducto = codTipoProducto;
    }

    public int getSubCategorias() {
        return subCategorias;
    }

    public void setSubCategorias(int subCategorias) {
        this.subCategorias = subCategorias;
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
        return "controllers.TipoProducto[ codTipoProducto=" + codTipoProducto + " ]";
    }
    
}
