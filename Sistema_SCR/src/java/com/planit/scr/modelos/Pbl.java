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
 * @author Desarrollo_Planit
 */
@Entity
@Table(name = "pbl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pbl.findAll", query = "SELECT p FROM Pbl p"),
    @NamedQuery(name = "Pbl.findByIdpbl", query = "SELECT p FROM Pbl p WHERE p.idpbl = :idpbl"),
    @NamedQuery(name = "Pbl.findByV1", query = "SELECT p FROM Pbl p WHERE p.v1 = :v1"),
    @NamedQuery(name = "Pbl.findByV2", query = "SELECT p FROM Pbl p WHERE p.v2 = :v2"),
    @NamedQuery(name = "Pbl.findByVt", query = "SELECT p FROM Pbl p WHERE p.vt = :vt"),
    @NamedQuery(name = "Pbl.findByPf", query = "SELECT p FROM Pbl p WHERE p.pf = :pf"),
    @NamedQuery(name = "Pbl.findByPx", query = "SELECT p FROM Pbl p WHERE p.px = :px"),
    @NamedQuery(name = "Pbl.findByCtc", query = "SELECT p FROM Pbl p WHERE p.ctc = :ctc"),
    @NamedQuery(name = "Pbl.findByCmt", query = "SELECT p FROM Pbl p WHERE p.cmt = :cmt"),
    @NamedQuery(name = "Pbl.findByCtmc", query = "SELECT p FROM Pbl p WHERE p.ctmc = :ctmc"),
    @NamedQuery(name = "Pbl.findByCtmd", query = "SELECT p FROM Pbl p WHERE p.ctmd = :ctmd"),
    @NamedQuery(name = "Pbl.findByCr", query = "SELECT p FROM Pbl p WHERE p.cr = :cr"),
    @NamedQuery(name = "Pbl.findByCt1", query = "SELECT p FROM Pbl p WHERE p.ct1 = :ct1"),
    @NamedQuery(name = "Pbl.findByCce", query = "SELECT p FROM Pbl p WHERE p.cce = :cce"),
    @NamedQuery(name = "Pbl.findByCtme", query = "SELECT p FROM Pbl p WHERE p.ctme = :ctme"),
    @NamedQuery(name = "Pbl.findByCt2", query = "SELECT p FROM Pbl p WHERE p.ct2 = :ct2"),
    @NamedQuery(name = "Pbl.findByTrimestre", query = "SELECT p FROM Pbl p WHERE p.trimestre = :trimestre"),
    @NamedQuery(name = "Pbl.findByPrc", query = "SELECT p FROM Pbl p WHERE p.prc = :prc")})
public class Pbl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpbl")
    private Integer idpbl;
    @Basic(optional = false)
    @Column(name = "v1")
    private double v1;
    @Basic(optional = false)
    @Column(name = "v2")
    private double v2;
    @Basic(optional = false)
    @Column(name = "vt")
    private double vt;
    @Basic(optional = false)
    @Column(name = "pf")
    private double pf;
    @Basic(optional = false)
    @Column(name = "px")
    private double px;
    @Basic(optional = false)
    @Column(name = "ctc")
    private double ctc;
    @Basic(optional = false)
    @Column(name = "cmt")
    private double cmt;
    @Basic(optional = false)
    @Column(name = "ctmc")
    private double ctmc;
    @Basic(optional = false)
    @Column(name = "ctmd")
    private double ctmd;
    @Basic(optional = false)
    @Column(name = "cr")
    private double cr;
    @Basic(optional = false)
    @Column(name = "ct1")
    private double ct1;
    @Basic(optional = false)
    @Column(name = "cce")
    private double cce;
    @Basic(optional = false)
    @Column(name = "ctme")
    private double ctme;
    @Basic(optional = false)
    @Column(name = "ct2")
    private double ct2;
    @Basic(optional = false)
    @Column(name = "trimestre")
    private int trimestre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prc")
    private Double prc;
    @JoinColumn(name = "idcampo", referencedColumnName = "idcampo")
    @ManyToOne(optional = false)
    private Campos idcampo;

    public Pbl() {
    }

    public Pbl(Integer idpbl) {
        this.idpbl = idpbl;
    }

    public Pbl(Integer idpbl, double v1, double v2, double vt, double pf, double px, double ctc, double cmt, double ctmc, double ctmd, double cr, double ct1, double cce, double ctme, double ct2, int trimestre) {
        this.idpbl = idpbl;
        this.v1 = v1;
        this.v2 = v2;
        this.vt = vt;
        this.pf = pf;
        this.px = px;
        this.ctc = ctc;
        this.cmt = cmt;
        this.ctmc = ctmc;
        this.ctmd = ctmd;
        this.cr = cr;
        this.ct1 = ct1;
        this.cce = cce;
        this.ctme = ctme;
        this.ct2 = ct2;
        this.trimestre = trimestre;
    }

    public Integer getIdpbl() {
        return idpbl;
    }

    public void setIdpbl(Integer idpbl) {
        this.idpbl = idpbl;
    }

    public double getV1() {
        return v1;
    }

    public void setV1(double v1) {
        this.v1 = v1;
    }

    public double getV2() {
        return v2;
    }

    public void setV2(double v2) {
        this.v2 = v2;
    }

    public double getVt() {
        return vt;
    }

    public void setVt(double vt) {
        this.vt = vt;
    }

    public double getPf() {
        return pf;
    }

    public void setPf(double pf) {
        this.pf = pf;
    }

    public double getPx() {
        return px;
    }

    public void setPx(double px) {
        this.px = px;
    }

    public double getCtc() {
        return ctc;
    }

    public void setCtc(double ctc) {
        this.ctc = ctc;
    }

    public double getCmt() {
        return cmt;
    }

    public void setCmt(double cmt) {
        this.cmt = cmt;
    }

    public double getCtmc() {
        return ctmc;
    }

    public void setCtmc(double ctmc) {
        this.ctmc = ctmc;
    }

    public double getCtmd() {
        return ctmd;
    }

    public void setCtmd(double ctmd) {
        this.ctmd = ctmd;
    }

    public double getCr() {
        return cr;
    }

    public void setCr(double cr) {
        this.cr = cr;
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

    public double getCtme() {
        return ctme;
    }

    public void setCtme(double ctme) {
        this.ctme = ctme;
    }

    public double getCt2() {
        return ct2;
    }

    public void setCt2(double ct2) {
        this.ct2 = ct2;
    }

    public int getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(int trimestre) {
        this.trimestre = trimestre;
    }

    public Double getPrc() {
        return prc;
    }

    public void setPrc(Double prc) {
        this.prc = prc;
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
