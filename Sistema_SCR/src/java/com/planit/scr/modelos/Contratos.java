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
@Table(name = "contratos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contratos.findAll", query = "SELECT c FROM Contratos c"),
    @NamedQuery(name = "Contratos.findByIdcontrato", query = "SELECT c FROM Contratos c WHERE c.idcontrato = :idcontrato"),
    @NamedQuery(name = "Contratos.findByNombre", query = "SELECT c FROM Contratos c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Contratos.findByCib", query = "SELECT c FROM Contratos c WHERE c.cib = :cib"),
    @NamedQuery(name = "Contratos.findByCar", query = "SELECT c FROM Contratos c WHERE c.car = :car"),
    @NamedQuery(name = "Contratos.findByCov", query = "SELECT c FROM Contratos c WHERE c.cov = :cov"),
    @NamedQuery(name = "Contratos.findByPorcentaje", query = "SELECT c FROM Contratos c WHERE c.porcentaje = :porcentaje")})

public class Contratos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcontrato")
    private Integer idcontrato;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcontrato")
    private Collection<Campos> camposCollection;
    @JoinColumn(name = "idtipo", referencedColumnName = "idtipo")
    @ManyToOne(optional = false)
    private Tipos idtipo;
    @JoinColumn(name = "idmunicipio", referencedColumnName = "idmunicipio")
    @ManyToOne(optional = false)
    private Municipios idmunicipio;
    @Column(name = "cib")
    private Integer cib;
    @Column(name = "car")
    private Integer car;
    @Column(name = "cov")
    private Integer cov;
    @Column(name = "porcentaje")
    private Integer porcentaje;

    public Contratos() {
        idmunicipio = new Municipios();
        idtipo = new Tipos();
    }

    public Contratos(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }

    public Contratos(Integer idcontrato, String nombre, Integer cib, Integer car, Integer cov, Integer porcentaje) {
        this.idcontrato = idcontrato;
        this.nombre = nombre;
        this.cib = cib;
        this.car = car;
        this.cov = cov;
        this.porcentaje = porcentaje;
    }

    public Contratos(Integer idcontrato, String nombre, Tipos idtipo, Municipios idmunicipio, Integer cib, Integer car, Integer cov, Integer porcentaje) {
        this.idcontrato = idcontrato;
        this.nombre = nombre;
        this.idtipo = idtipo;
        this.idmunicipio = idmunicipio;
        this.cib = cib;
        this.car = car;
        this.cov = cov;
        this.porcentaje = porcentaje;
    }

    public Integer getIdcontrato() {
        return idcontrato;
    }

    public void setIdcontrato(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Campos> getCamposCollection() {
        return camposCollection;
    }

    public void setCamposCollection(Collection<Campos> camposCollection) {
        this.camposCollection = camposCollection;
    }

    public Tipos getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(Tipos idtipo) {
        this.idtipo = idtipo;
    }

    public Municipios getIdmunicipio() {
        return idmunicipio;
    }

    public void setIdmunicipio(Municipios idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    public Integer getCib() {
        return cib;
    }

    public void setCib(Integer cib) {
        this.cib = cib;
    }

    public Integer getCar() {
        return car;
    }

    public void setCar(Integer car) {
        this.car = car;
    }

    public Integer getCov() {
        return cov;
    }

    public void setCov(Integer cov) {
        this.cov = cov;
    }

    public Integer getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Integer porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontrato != null ? idcontrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contratos)) {
            return false;
        }
        Contratos other = (Contratos) object;
        if ((this.idcontrato == null && other.idcontrato != null) || (this.idcontrato != null && !this.idcontrato.equals(other.idcontrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.planit.scr.modelos.Contratos[ idcontrato=" + idcontrato + " ]";
    }

}
