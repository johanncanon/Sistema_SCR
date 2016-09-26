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
@Table(name = "pbl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pbl.findAll", query = "SELECT p FROM Pbl p"),
    @NamedQuery(name = "Pbl.findByIdpbl", query = "SELECT p FROM Pbl p WHERE p.idpbl = :idpbl"),
    @NamedQuery(name = "Pbl.findByCtc", query = "SELECT p FROM Pbl p WHERE p.ctc = :ctc"),
    @NamedQuery(name = "Pbl.findByCt1", query = "SELECT p FROM Pbl p WHERE p.ct1 = :ct1"),
    @NamedQuery(name = "Pbl.findByCce", query = "SELECT p FROM Pbl p WHERE p.cce = :cce"),
    @NamedQuery(name = "Pbl.findByCt2", query = "SELECT p FROM Pbl p WHERE p.ct2 = :ct2"),
    @NamedQuery(name = "Pbl.findByTrimestreMes", query = "SELECT p FROM Pbl p WHERE p.trimestreMes = :trimestreMes"),
    @NamedQuery(name = "Pbl.findByPrc", query = "SELECT p FROM Pbl p WHERE p.prc = :prc"),
    @NamedQuery(name = "Pbl.findByRefinacion", query = "SELECT p FROM Pbl p WHERE p.refinacion = :refinacion"),
    @NamedQuery(name = "Pbl.findByExportacion", query = "SELECT p FROM Pbl p WHERE p.exportacion = :exportacion"),
    @NamedQuery(name = "Pbl.findByAnio", query = "SELECT p FROM Pbl p WHERE p.anio = :anio")})
public class Pbl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpbl")
    private Integer idpbl;
    @Basic(optional = false)
    @Column(name = "ctc")
    private double ctc;
    @Basic(optional = false)
    @Column(name = "ct1")
    private double ct1;
    @Basic(optional = false)
    @Column(name = "cce")
    private double cce;
    @Basic(optional = false)
    @Column(name = "ct2")
    private double ct2;
    @Basic(optional = false)
    @Column(name = "trimestre_mes")
    private int trimestreMes;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prc")
    private Double prc;
    @Column(name = "refinacion")
    private Double refinacion;
    @Column(name = "exportacion")
    private Double exportacion;
    @Column(name = "anio")
    private Integer anio;
    @JoinColumn(name = "idcontrato", referencedColumnName = "idcontrato")
    @ManyToOne
    private Contratos idcontrato;

    public Pbl() {
    }

    public Pbl(Integer idpbl) {
        this.idpbl = idpbl;
    }

    public Pbl(Integer idpbl, double ctc, double ct1, double cce, double ct2, int trimestreMes) {
        this.idpbl = idpbl;
        this.ctc = ctc;
        this.ct1 = ct1;
        this.cce = cce;
        this.ct2 = ct2;
        this.trimestreMes = trimestreMes;
    }

    public Pbl(Integer idpbl, double ctc, double ct1, double cce, double ct2, int trimestreMes, Double prc, Double refinacion, Double exportacion, Integer anio, Contratos idcontrato) {
        this.idpbl = idpbl;
        this.ctc = ctc;
        this.ct1 = ct1;
        this.cce = cce;
        this.ct2 = ct2;
        this.trimestreMes = trimestreMes;
        this.prc = prc;
        this.refinacion = refinacion;
        this.exportacion = exportacion;
        this.anio = anio;
        this.idcontrato = idcontrato;
    }

    public Pbl(double ctc, double ct1, double cce, double ct2, int trimestreMes, Double prc, Double refinacion, Double exportacion, Integer anio, Contratos idcontrato) {
        this.ctc = ctc;
        this.ct1 = ct1;
        this.cce = cce;
        this.ct2 = ct2;
        this.trimestreMes = trimestreMes;
        this.prc = prc;
        this.refinacion = refinacion;
        this.exportacion = exportacion;
        this.anio = anio;
        this.idcontrato = idcontrato;
    }

    
    
    public Integer getIdpbl() {
        return idpbl;
    }

    public void setIdpbl(Integer idpbl) {
        this.idpbl = idpbl;
    }

    public double getCtc() {
        return ctc;
    }

    public void setCtc(double ctc) {
        this.ctc = ctc;
    }

    public double getCt1() {
        return ct1;
    }

    public void setCt1(double ct1) {
        this.ct1 = ct1;
    }

    public double getCce() {
        return cce;
    }

    public void setCce(double cce) {
        this.cce = cce;
    }

    public double getCt2() {
        return ct2;
    }

    public void setCt2(double ct2) {
        this.ct2 = ct2;
    }

    public int getTrimestreMes() {
        return trimestreMes;
    }

    public void setTrimestreMes(int trimestreMes) {
        this.trimestreMes = trimestreMes;
    }

    public Double getPrc() {
        return prc;
    }

    public void setPrc(Double prc) {
        this.prc = prc;
    }

    public Double getRefinacion() {
        return refinacion;
    }

    public void setRefinacion(Double refinacion) {
        this.refinacion = refinacion;
    }

    public Double getExportacion() {
        return exportacion;
    }

    public void setExportacion(Double exportacion) {
        this.exportacion = exportacion;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
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
        hash += (idpbl != null ? idpbl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pbl)) {
            return false;
        }
        Pbl other = (Pbl) object;
        if ((this.idpbl == null && other.idpbl != null) || (this.idpbl != null && !this.idpbl.equals(other.idpbl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.planit.scr.modelos.Pbl[ idpbl=" + idpbl + " ]";
    }
    
}
