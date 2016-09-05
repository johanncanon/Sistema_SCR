/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.modelos;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Desarrollo_Planit
 */
@Entity
@Table(name = "valores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Valores.findAll", query = "SELECT v FROM Valores v"),
    @NamedQuery(name = "Valores.findByIdvalores", query = "SELECT v FROM Valores v WHERE v.idvalores = :idvalores"),
    @NamedQuery(name = "Valores.findByPx", query = "SELECT v FROM Valores v WHERE v.px = :px"),
    @NamedQuery(name = "Valores.findByPf", query = "SELECT v FROM Valores v WHERE v.pf = :pf"),
    @NamedQuery(name = "Valores.findByV1", query = "SELECT v FROM Valores v WHERE v.v1 = :v1"),
    @NamedQuery(name = "Valores.findByV2", query = "SELECT v FROM Valores v WHERE v.v2 = :v2"),
    @NamedQuery(name = "Valores.findByTrimestre", query = "SELECT v FROM Valores v WHERE v.trimestre = :trimestre"),
    @NamedQuery(name = "Valores.findByVt", query = "SELECT v FROM Valores v WHERE v.vt = :vt"),
    @NamedQuery(name = "Valores.findByCmt", query = "SELECT v FROM Valores v WHERE v.cmt = :cmt"),
    @NamedQuery(name = "Valores.findByCtmc", query = "SELECT v FROM Valores v WHERE v.ctmc = :ctmc"),
    @NamedQuery(name = "Valores.findByCtmd", query = "SELECT v FROM Valores v WHERE v.ctmd = :ctmd"),
    @NamedQuery(name = "Valores.findByCr", query = "SELECT v FROM Valores v WHERE v.cr = :cr"),
    @NamedQuery(name = "Valores.findByCce", query = "SELECT v FROM Valores v WHERE v.cce = :cce"),
    @NamedQuery(name = "Valores.findByCtme", query = "SELECT v FROM Valores v WHERE v.ctme = :ctme")})
public class Valores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvalores")
    private Integer idvalores;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "px")
    private Double px;
    @Column(name = "pf")
    private Double pf;
    @Column(name = "v1")
    private Double v1;
    @Column(name = "v2")
    private Double v2;
    @Column(name = "trimestre")
    private String trimestre;
    @Column(name = "vt")
    private Double vt;
    @Column(name = "cmt")
    private Double cmt;
    @Column(name = "ctmc")
    private Double ctmd;
    @Column(name = "ctmd")
    private Double ctmc;
    @Column(name = "cr")
    private Double cr;
    @Column(name = "cce")
    private Double cce;
    @Column(name = "ctme")
    private Double ctme;

    public Valores() {
    }

    public Valores(Integer idvalores) {
        this.idvalores = idvalores;
    }

    public Valores(Integer idvalores, Double px, Double pf, Double v1, Double v2, String trimestre, Double vt, Double cmt, Double ctmd, Double ctmc, Double cr, Double cce, Double ctme) {
        this.idvalores = idvalores;
        this.px = px;
        this.pf = pf;
        this.v1 = v1;
        this.v2 = v2;
        this.trimestre = trimestre;
        this.vt = vt;
        this.cmt = cmt;
        this.ctmd = ctmd;
        this.ctmc = ctmc;
        this.cr = cr;
        this.cce = cce;
        this.ctme = ctme;
    }

    public Valores(Double px, Double pf, Double v1, Double v2, String trimestre, Double vt, Double cmt, Double ctmd, Double ctmc, Double cr, Double cce, Double ctme) {
        this.px = px;
        this.pf = pf;
        this.v1 = v1;
        this.v2 = v2;
        this.trimestre = trimestre;
        this.vt = vt;
        this.cmt = cmt;
        this.ctmd = ctmd;
        this.ctmc = ctmc;
        this.cr = cr;
        this.cce = cce;
        this.ctme = ctme;
    }
    
    

    public Integer getIdvalores() {
        return idvalores;
    }

    public void setIdvalores(Integer idvalores) {
        this.idvalores = idvalores;
    }

    public Double getPx() {
        return px;
    }

    public void setPx(Double px) {
        this.px = px;
    }

    public Double getPf() {
        return pf;
    }

    public void setPf(Double pf) {
        this.pf = pf;
    }

    public Double getV1() {
        return v1;
    }

    public void setV1(Double v1) {
        this.v1 = v1;
    }

    public Double getV2() {
        return v2;
    }

    public void setV2(Double v2) {
        this.v2 = v2;
    }

    public String getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }

    public Double getVt() {
        return vt;
    }

    public void setVt(Double vt) {
        this.vt = vt;
    }

    public Double getCmt() {
        return cmt;
    }

    public void setCmt(Double cmt) {
        this.cmt = cmt;
    }

    public Double getCtmd() {
        return ctmd;
    }

    public void setCtmd(Double ctmd) {
        this.ctmd = ctmd;
    }

    public Double getCtmc() {
        return ctmc;
    }

    public void setCtmc(Double ctmc) {
        this.ctmc = ctmc;
    }

    public Double getCr() {
        return cr;
    }

    public void setCr(Double cr) {
        this.cr = cr;
    }

    public Double getCce() {
        return cce;
    }

    public void setCce(Double cce) {
        this.cce = cce;
    }

    public Double getCtme() {
        return ctme;
    }

    public void setCtme(Double ctme) {
        this.ctme = ctme;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvalores != null ? idvalores.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Valores other = (Valores) obj;
        if (!Objects.equals(this.idvalores, other.idvalores)) {
            return false;
        }
        if (!Objects.equals(this.px, other.px)) {
            return false;
        }
        if (!Objects.equals(this.pf, other.pf)) {
            return false;
        }
        if (!Objects.equals(this.v1, other.v1)) {
            return false;
        }
        if (!Objects.equals(this.v2, other.v2)) {
            return false;
        }
        if (!Objects.equals(this.trimestre, other.trimestre)) {
            return false;
        }
        if (!Objects.equals(this.vt, other.vt)) {
            return false;
        }
        if (!Objects.equals(this.cmt, other.cmt)) {
            return false;
        }
        if (!Objects.equals(this.ctmd, other.ctmd)) {
            return false;
        }
        if (!Objects.equals(this.ctmc, other.ctmc)) {
            return false;
        }
        if (!Objects.equals(this.cr, other.cr)) {
            return false;
        }
        if (!Objects.equals(this.cce, other.cce)) {
            return false;
        }
        if (!Objects.equals(this.ctme, other.ctme)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.planit.scr.modelos.Valores[ idvalores=" + idvalores + " ]";
    }

}
