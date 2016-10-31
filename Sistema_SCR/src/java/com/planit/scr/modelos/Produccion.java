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
 * @author VaioDevelopment
 */
@XmlRootElement
public class Produccion implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idproduccion;

    private Double produccionhdia;

    private Double produccionhmes;

    private Double producciongdia;

    private Double producciongmes;

    private Double producciontotaldia;

    private Integer mes;

    private Integer anio;

    private Double producciontotalmes;

    private Campo idcampo;

    public Produccion() {
        idproduccion = 0;
        mes = 0;
        anio = 0;
        idcampo = new Campo();
    }

    public Produccion(Integer idproduccion) {
        this.idproduccion = idproduccion;
        mes = 0;
        anio = 0;
        idcampo = new Campo();
    }

    public Produccion(Integer idproduccion, Double produccionhdia, Double produccionhmes, Double producciongdia, Double producciongmes, Double producciontotaldia, Integer mes, Integer anio, Double producciontotalmes, Campo idcampo) {
        this.idproduccion = idproduccion;
        this.produccionhdia = produccionhdia;
        this.produccionhmes = produccionhmes;
        this.producciongdia = producciongdia;
        this.producciongmes = producciongmes;
        this.producciontotaldia = producciontotaldia;
        this.mes = mes;
        this.anio = anio;
        this.producciontotalmes = producciontotalmes;
        this.idcampo = idcampo;
    }

    public Produccion(Double produccionhdia, Double produccionhmes, Double producciongdia, Double producciongmes, Double producciontotaldia, Integer mes, Integer anio, Double producciontotalmes, Campo idcampo) {
        this.produccionhdia = produccionhdia;
        this.produccionhmes = produccionhmes;
        this.producciongdia = producciongdia;
        this.producciongmes = producciongmes;
        this.producciontotaldia = producciontotaldia;
        this.mes = mes;
        this.anio = anio;
        this.producciontotalmes = producciontotalmes;
        this.idcampo = idcampo;
    }

    public Integer getIdproduccion() {
        return idproduccion;
    }

    public void setIdproduccion(Integer idproduccion) {
        this.idproduccion = idproduccion;
    }

    public Double getProduccionhdia() {
        return produccionhdia;
    }

    public void setProduccionhdia(Double produccionhdia) {
        this.produccionhdia = produccionhdia;
    }

    public Double getProduccionhmes() {
        return produccionhmes;
    }

    public void setProduccionhmes(Double produccionhmes) {
        this.produccionhmes = produccionhmes;
    }

    public Double getProducciongdia() {
        return producciongdia;
    }

    public void setProducciongdia(Double producciongdia) {
        this.producciongdia = producciongdia;
    }

    public Double getProducciongmes() {
        return producciongmes;
    }

    public void setProducciongmes(Double producciongmes) {
        this.producciongmes = producciongmes;
    }

    public Double getProducciontotaldia() {
        return producciontotaldia;
    }

    public void setProducciontotaldia(Double producciontotaldia) {
        this.producciontotaldia = producciontotaldia;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Double getProducciontotalmes() {
        return producciontotalmes;
    }

    public void setProducciontotalmes(Double producciontotalmes) {
        this.producciontotalmes = producciontotalmes;
    }

    public Campo getIdcampo() {
        return idcampo;
    }

    public void setIdcampo(Campo idcampo) {
        this.idcampo = idcampo;
    }

    @Override
    public String toString() {
        return "com.planit.scr.modelos.Produccion[ idproduccion=" + idproduccion + " ]";
    }

}
