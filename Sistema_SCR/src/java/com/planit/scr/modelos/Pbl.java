/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.modelos;

import java.io.Serializable;
import java.math.BigInteger;
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
    @NamedQuery(name = "Pbl.findByV", query = "SELECT p FROM Pbl p WHERE p.v = :v"),
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
    @NamedQuery(name = "Pbl.findByTrimestre", query = "SELECT p FROM Pbl p WHERE p.trimestre = :trimestre")})
public class Pbl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpbl")
    private Integer idpbl;
    @Basic(optional = false)
    @Column(name = "v1")
    private BigInteger v1;
    @Basic(optional = false)
    @Column(name = "v")
    private BigInteger v;
    @Basic(optional = false)
    @Column(name = "vt")
    private BigInteger vt;
    @Basic(optional = false)
    @Column(name = "pf")
    private BigInteger pf;
    @Basic(optional = false)
    @Column(name = "px")
    private BigInteger px;
    @Basic(optional = false)
    @Column(name = "ctc")
    private BigInteger ctc;
    @Basic(optional = false)
    @Column(name = "cmt")
    private BigInteger cmt;
    @Basic(optional = false)
    @Column(name = "ctmc")
    private BigInteger ctmc;
    @Basic(optional = false)
    @Column(name = "ctmd")
    private BigInteger ctmd;
    @Basic(optional = false)
    @Column(name = "cr")
    private BigInteger cr;
    @Basic(optional = false)
    @Column(name = "ct1")
    private BigInteger ct1;
    @Basic(optional = false)
    @Column(name = "cce")
    private BigInteger cce;
    @Basic(optional = false)
    @Column(name = "ctme")
    private BigInteger ctme;
    @Basic(optional = false)
    @Column(name = "ct2")
    private BigInteger ct2;
    @Basic(optional = false)
    @Column(name = "trimestre")
    private int trimestre;
    @JoinColumn(name = "idcampo", referencedColumnName = "idcampo")
    @ManyToOne(optional = false)
    private Campos idcampo;

    public Pbl() {
        idcampo = new Campos();
    }

    public Pbl(Integer idpbl) {
        this.idpbl = idpbl;
    }

    public Pbl(Integer idpbl, BigInteger v1, BigInteger v, BigInteger vt, BigInteger pf, BigInteger px, BigInteger ctc, BigInteger cmt, BigInteger ctmc, BigInteger ctmd, BigInteger cr, BigInteger ct1, BigInteger cce, BigInteger ctme, BigInteger ct2, int trimestre) {
        this.idpbl = idpbl;
        this.v1 = v1;
        this.v = v;
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

    public BigInteger getV1() {
        return v1;
    }

    public void setV1(BigInteger v1) {
        this.v1 = v1;
    }

    public BigInteger getV() {
        return v;
    }

    public void setV(BigInteger v) {
        this.v = v;
    }

    public BigInteger getVt() {
        return vt;
    }

    public void setVt(BigInteger vt) {
        this.vt = vt;
    }

    public BigInteger getPf() {
        return pf;
    }

    public void setPf(BigInteger pf) {
        this.pf = pf;
    }

    public BigInteger getPx() {
        return px;
    }

    public void setPx(BigInteger px) {
        this.px = px;
    }

    public BigInteger getCtc() {
        return ctc;
    }

    public void setCtc(BigInteger ctc) {
        this.ctc = ctc;
    }

    public BigInteger getCmt() {
        return cmt;
    }

    public void setCmt(BigInteger cmt) {
        this.cmt = cmt;
    }

    public BigInteger getCtmc() {
        return ctmc;
    }

    public void setCtmc(BigInteger ctmc) {
        this.ctmc = ctmc;
    }

    public BigInteger getCtmd() {
        return ctmd;
    }

    public void setCtmd(BigInteger ctmd) {
        this.ctmd = ctmd;
    }

    public BigInteger getCr() {
        return cr;
    }

    public void setCr(BigInteger cr) {
        this.cr = cr;
    }

    public BigInteger getCt1() {
        return ct1;
    }

    public void setCt1(BigInteger ct1) {
        this.ct1 = ct1;
    }

    public BigInteger getCce() {
        return cce;
    }

    public void setCce(BigInteger cce) {
        this.cce = cce;
    }

    public BigInteger getCtme() {
        return ctme;
    }

    public void setCtme(BigInteger ctme) {
        this.ctme = ctme;
    }

    public BigInteger getCt2() {
        return ct2;
    }

    public void setCt2(BigInteger ct2) {
        this.ct2 = ct2;
    }

    public int getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(int trimestre) {
        this.trimestre = trimestre;
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
