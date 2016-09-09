/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.modelos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Desarrollo_Planit
 */
@Entity
@Table(name = "produccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produccion.findAll", query = "SELECT p FROM Produccion p"),
    @NamedQuery(name = "Produccion.findByIdproduccion", query = "SELECT p FROM Produccion p WHERE p.idproduccion = :idproduccion"),
    @NamedQuery(name = "Produccion.findByFecha", query = "SELECT p FROM Produccion p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Produccion.findByProduccion", query = "SELECT p FROM Produccion p WHERE p.produccion = :produccion")})
public class Produccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproduccion")
    private Integer idproduccion;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "produccion")
    private Integer produccion;
    @JoinColumn(name = "idcampo", referencedColumnName = "idcampo")
    @ManyToOne(optional = false)
    private Campos idcampo;

    public Produccion() {
        idcampo = new Campos();
    }

    public Produccion(Integer idproduccion) {
        this.idproduccion = idproduccion;
    }

    public Produccion(Integer idproduccion, Date fecha, Integer produccion, Campos idcampo) {
        this.idproduccion = idproduccion;
        this.fecha = fecha;
        this.produccion = produccion;
        this.idcampo = idcampo;
    }

   

    public Integer getIdproduccion() {
        return idproduccion;
    }

    public void setIdproduccion(Integer idproduccion) {
        this.idproduccion = idproduccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getProduccion() {
        return produccion;
    }

    public void setProduccion(Integer produccion) {
        this.produccion = produccion;
    }   

    public Campos getIdcampo() {
        return idcampo;
    }

    public void setIdcampo(Campos idcampo) {
        this.idcampo = idcampo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproduccion != null ? idproduccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produccion)) {
            return false;
        }
        Produccion other = (Produccion) object;
        if ((this.idproduccion == null && other.idproduccion != null) || (this.idproduccion != null && !this.idproduccion.equals(other.idproduccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.planit.scr.modelos.Produccion[ idproduccion=" + idproduccion + " ]";
    }
    
}
