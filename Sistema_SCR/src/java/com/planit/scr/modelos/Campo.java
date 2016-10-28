/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.modelos;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Desarrollo_Planit
 */
@XmlRootElement
public class Campo implements Serializable {

    private Integer idcampo;

    private String nombre;

    private Contrato contrato;

    //Constructores
    public Campo() {
        this.contrato = new Contrato();
        idcampo = 0;
    }

    public Campo(Integer idcampo) {
        this.idcampo = idcampo;
    }

    public Campo(Integer idcampo, String nombre) {
        this.idcampo = idcampo;
        this.nombre = nombre;
    }

    public Campo(Integer idcampo, String nombre, Contrato contrato) {
        this.idcampo = idcampo;
        this.nombre = nombre;
        this.contrato = contrato;
    }

    //Getters & Setters
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

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
}
