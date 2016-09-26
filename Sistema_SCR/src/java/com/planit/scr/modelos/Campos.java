/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.modelos;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Desarrollo_Planit
 */
@Entity
@Table(name = "campos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Campos.findAll", query = "SELECT c FROM Campos c"),
    @NamedQuery(name = "Campos.findByIdcampo", query = "SELECT c FROM Campos c WHERE c.idcampo = :idcampo"),
    @NamedQuery(name = "Campos.findByNombre", query = "SELECT c FROM Campos c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Campos.findByCib", query = "SELECT c FROM Campos c WHERE c.cib = :cib"),
    @NamedQuery(name = "Campos.findByCar", query = "SELECT c FROM Campos c WHERE c.car = :car"),
    @NamedQuery(name = "Campos.findByCov", query = "SELECT c FROM Campos c WHERE c.cov = :cov")})
public class Campos implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcampo")
    private Collection<Produccion> produccionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcampo")
    private Collection<Regalias> regaliasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcampo")
    private Collection<Pbl> pblCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcampo")
    private Integer idcampo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "cib")
    private String cib;
    @Column(name = "car")
    private String car;
    @Column(name = "cov")
    private String cov;
    @JoinColumn(name = "idcontrato", referencedColumnName = "idcontrato")
    @ManyToOne(optional = false)
    private Contratos idcontrato;

    public Campos() {
        idcontrato = new Contratos();
    }

    public Campos(Integer idcampo) {
        this.idcampo = idcampo;
    }

    public Campos(Integer idcampo, String nombre) {
        this.idcampo = idcampo;
        this.nombre = nombre;
    }

    public Campos(Integer idcampo, String nombre, Contratos idcontrato) {
        this.idcampo = idcampo;
        this.nombre = nombre;
        this.idcontrato = idcontrato;
    }

    public Campos(Integer idcampo, String nombre, String cib, String car, String cov, Contratos idcontrato) {
        this.idcampo = idcampo;
        this.nombre = nombre;
        this.cib = cib;
        this.car = car;
        this.cov = cov;
        this.idcontrato = idcontrato;
    }

    public Campos(Integer idcampo, String nombre, String cib, String car, String cov) {
        this.idcampo = idcampo;
        this.nombre = nombre;
        this.cib = cib;
        this.car = car;
        this.cov = cov;
    }
    
    
   
    public Integer getIdcampo() {
        return idcampo;
    }

    public void setIdcampo(Integer idcampo) {
        this.idcampo = idcampo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCib() {
        return cib;
    }

    public void setCib(String cib) {
        this.cib = cib;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getCov() {
        return cov;
    }

    public void setCov(String cov) {
        this.cov = cov;
    }

    public Contratos getIdcontrato() {
        return idcontrato;
    }

    public void setIdcontrato(Contratos idcontrato) {
        this.idcontrato = idcontrato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcampo != null ? idcampo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Campos)) {
            return false;
        }
        Campos other = (Campos) object;
        if ((this.idcampo == null && other.idcampo != null) || (this.idcampo != null && !this.idcampo.equals(other.idcampo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.planit.scr.modelos.Campos[ idcampo=" + idcampo + " ]";
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Pbl> getPblCollection() {
        return pblCollection;
    }

    public void setPblCollection(Collection<Pbl> pblCollection) {
        this.pblCollection = pblCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Regalias> getRegaliasCollection() {
        return regaliasCollection;
    }

    public void setRegaliasCollection(Collection<Regalias> regaliasCollection) {
        this.regaliasCollection = regaliasCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Produccion> getProduccionCollection() {
        return produccionCollection;
    }

    public void setProduccionCollection(Collection<Produccion> produccionCollection) {
        this.produccionCollection = produccionCollection;
    }
    
}
