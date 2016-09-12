/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.modelos;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author VaioDevelopment
 */
@Entity
@Table(name = "regalias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Regalias.findAll", query = "SELECT r FROM Regalias r"),
    @NamedQuery(name = "Regalias.findByIdregalias", query = "SELECT r FROM Regalias r WHERE r.idregalias = :idregalias"),
    @NamedQuery(name = "Regalias.findByTipohidrocarburo", query = "SELECT r FROM Regalias r WHERE r.tipohidrocarburo = :tipohidrocarburo"),
    @NamedQuery(name = "Regalias.findByProddia", query = "SELECT r FROM Regalias r WHERE r.proddia = :proddia"),
    @NamedQuery(name = "Regalias.findByProdmes", query = "SELECT r FROM Regalias r WHERE r.prodmes = :prodmes"),
    @NamedQuery(name = "Regalias.findByPorcmunicipio", query = "SELECT r FROM Regalias r WHERE r.porcmunicipio = :porcmunicipio"),
    @NamedQuery(name = "Regalias.findByPorcregalias", query = "SELECT r FROM Regalias r WHERE r.porcregalias = :porcregalias"),
    @NamedQuery(name = "Regalias.findByDepproductor", query = "SELECT r FROM Regalias r WHERE r.depproductor = :depproductor"),
    @NamedQuery(name = "Regalias.findByMunproductor", query = "SELECT r FROM Regalias r WHERE r.munproductor = :munproductor"),
    @NamedQuery(name = "Regalias.findByDepnoproductor", query = "SELECT r FROM Regalias r WHERE r.depnoproductor = :depnoproductor"),
    @NamedQuery(name = "Regalias.findByPuertos", query = "SELECT r FROM Regalias r WHERE r.puertos = :puertos"),
    @NamedQuery(name = "Regalias.findByAnio", query = "SELECT r FROM Regalias r WHERE r.anio = :anio"),
    @NamedQuery(name = "Regalias.findByMes", query = "SELECT r FROM Regalias r WHERE r.mes = :mes"),
    @NamedQuery(name = "Regalias.findByPrecio", query = "SELECT r FROM Regalias r WHERE r.precio = :precio"),
    @NamedQuery(name = "Regalias.findByRegalias", query = "SELECT r FROM Regalias r WHERE r.regalias = :regalias")})
public class Regalias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idregalias")
    private Integer idregalias;
    @Basic(optional = false)
    @Column(name = "tipohidrocarburo")
    private String tipohidrocarburo;
    @Basic(optional = false)
    @Column(name = "proddia")
    private double proddia;
    @Basic(optional = false)
    @Column(name = "prodmes")
    private double prodmes;
    @Basic(optional = false)
    @Column(name = "porcmunicipio")
    private double porcmunicipio;
    @Basic(optional = false)
    @Column(name = "porcregalias")
    private double porcregalias;
    @Basic(optional = false)
    @Column(name = "depproductor")
    private int depproductor;
    @Basic(optional = false)
    @Column(name = "munproductor")
    private int munproductor;
    @Basic(optional = false)
    @Column(name = "depnoproductor")
    private int depnoproductor;
    @Basic(optional = false)
    @Column(name = "puertos")
    private int puertos;
    @Column(name = "anio")
    private Integer anio;
    @Column(name = "mes")
    private Integer mes;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Double precio;
    @Column(name = "regalias")
    private Double regalias;
    @JoinColumn(name = "idcampo", referencedColumnName = "idcampo")
    @ManyToOne(optional = false)
    private Campos idcampo;
    @JoinColumn(name = "iddepartamento", referencedColumnName = "iddepartamento")
    @ManyToOne(optional = false)
    private Departamentos iddepartamento;
    @JoinColumn(name = "idmunicipio", referencedColumnName = "idmunicipio")
    @ManyToOne(optional = false)
    private Municipios idmunicipio;

    public Regalias() {
    }

    public Regalias(Integer idregalias) {
        this.idregalias = idregalias;
    }

    public Regalias(Integer idregalias, String tipohidrocarburo, double proddia, double prodmes, double porcmunicipio, double porcregalias, int depproductor, int munproductor, int depnoproductor, int puertos) {
        this.idregalias = idregalias;
        this.tipohidrocarburo = tipohidrocarburo;
        this.proddia = proddia;
        this.prodmes = prodmes;
        this.porcmunicipio = porcmunicipio;
        this.porcregalias = porcregalias;
        this.depproductor = depproductor;
        this.munproductor = munproductor;
        this.depnoproductor = depnoproductor;
        this.puertos = puertos;
    }

    public Integer getIdregalias() {
        return idregalias;
    }

    public void setIdregalias(Integer idregalias) {
        this.idregalias = idregalias;
    }

    public String getTipohidrocarburo() {
        return tipohidrocarburo;
    }

    public void setTipohidrocarburo(String tipohidrocarburo) {
        this.tipohidrocarburo = tipohidrocarburo;
    }

    public double getProddia() {
        return proddia;
    }

    public void setProddia(double proddia) {
        this.proddia = proddia;
    }

    public double getProdmes() {
        return prodmes;
    }

    public void setProdmes(double prodmes) {
        this.prodmes = prodmes;
    }

    public double getPorcmunicipio() {
        return porcmunicipio;
    }

    public void setPorcmunicipio(double porcmunicipio) {
        this.porcmunicipio = porcmunicipio;
    }

    public double getPorcregalias() {
        return porcregalias;
    }

    public void setPorcregalias(double porcregalias) {
        this.porcregalias = porcregalias;
    }

    public int getDepproductor() {
        return depproductor;
    }

    public void setDepproductor(int depproductor) {
        this.depproductor = depproductor;
    }

    public int getMunproductor() {
        return munproductor;
    }

    public void setMunproductor(int munproductor) {
        this.munproductor = munproductor;
    }

    public int getDepnoproductor() {
        return depnoproductor;
    }

    public void setDepnoproductor(int depnoproductor) {
        this.depnoproductor = depnoproductor;
    }

    public int getPuertos() {
        return puertos;
    }

    public void setPuertos(int puertos) {
        this.puertos = puertos;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getRegalias() {
        return regalias;
    }

    public void setRegalias(Double regalias) {
        this.regalias = regalias;
    }

    public Campos getIdcampo() {
        return idcampo;
    }

    public void setIdcampo(Campos idcampo) {
        this.idcampo = idcampo;
    }

    public Departamentos getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(Departamentos iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    public Municipios getIdmunicipio() {
        return idmunicipio;
    }

    public void setIdmunicipio(Municipios idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idregalias != null ? idregalias.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Regalias)) {
            return false;
        }
        Regalias other = (Regalias) object;
        if ((this.idregalias == null && other.idregalias != null) || (this.idregalias != null && !this.idregalias.equals(other.idregalias))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.planit.scr.modelos.Regalias[ idregalias=" + idregalias + " ]";
    }
    
}
