/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.modelos;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "trm")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trm.findAll", query = "SELECT t FROM Trm t"),
    @NamedQuery(name = "Trm.findByIdtrm", query = "SELECT t FROM Trm t WHERE t.idtrm = :idtrm"),
    @NamedQuery(name = "Trm.findByFecha", query = "SELECT t FROM Trm t WHERE t.fecha = :fecha"),
    @NamedQuery(name = "Trm.findByValor", query = "SELECT t FROM Trm t WHERE t.valor = :valor")})
public class Trm implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtrm")
    private Integer idtrm;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "valor")
    private Integer valor;

    public Trm() {
        fecha = new Date();
    }

    public Trm(Integer idtrm) {
        this.idtrm = idtrm;
    }

    public Trm(Integer idtrm, Date fecha) {
        this.idtrm = idtrm;
        this.fecha = fecha;
    }

    public Trm(Integer idtrm, Date fecha, Integer valor) {
        this.idtrm = idtrm;
        this.fecha = fecha;
        this.valor = valor;
    }
       
    public Integer getIdtrm() {
        return idtrm;
    }

    public void setIdtrm(Integer idtrm) {
        this.idtrm = idtrm;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtrm != null ? idtrm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trm)) {
            return false;
        }
        Trm other = (Trm) object;
        if ((this.idtrm == null && other.idtrm != null) || (this.idtrm != null && !this.idtrm.equals(other.idtrm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.planit.scr.modelos.Trm[ idtrm=" + idtrm + " ]";
    }
    
}
