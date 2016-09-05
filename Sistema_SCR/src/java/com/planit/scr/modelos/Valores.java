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
    @NamedQuery(name = "Valores.findByVt", query = "SELECT v FROM Valores v WHERE v.vt = :vt")})
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

    public Valores() {
    }

    public Valores(Integer idvalores) {
        this.idvalores = idvalores;
    }

    public Valores(Integer idvalores, Double px, Double pf, Double v1, Double v2, String trimestre) {
        this.idvalores = idvalores;
        this.px = px;
        this.pf = pf;
        this.v1 = v1;
        this.v2 = v2;
        this.trimestre = trimestre;
    }

    public Valores(Integer idvalores, Double px, Double pf, Double v1, Double v2, Double vt, String trimestre) {
        this.idvalores = idvalores;
        this.px = px;
        this.pf = pf;
        this.v1 = v1;
        this.v2 = v2;
        this.trimestre = trimestre;
        this.vt = vt;
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
        return true;
    }



    @Override
    public String toString() {
        return "com.planit.scr.modelos.Valores[ idvalores=" + idvalores + " ]";
    }

}
