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
public class Regalias implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idregalias;

    private double porcmunicipio;

    private double porcregalias;

    private int depproductor;

    private int munproductor;

    private int depnoproductor;

    private int puertos;
    
    private Integer anio;
    
    private Integer mes;
    
    private Double precio;
    
    private Double regalias;
    
    private Campo campo;
    
    private Departamento departamento;
    
    private Municipio municipio;
    
    private Produccion produccion;

    public Regalias() {
        this.campo = new Campo();
        this.departamento = new Departamento();
        this.municipio = new Municipio();
        this.produccion = new Produccion();
    }

    public Regalias(Integer idregalias) {
        this.idregalias = idregalias;
    }

    public Regalias(Integer idregalias, double porcmunicipio, double porcregalias, int depproductor, int munproductor, int depnoproductor, int puertos) {
        this.idregalias = idregalias;
        this.porcmunicipio = porcmunicipio;
        this.porcregalias = porcregalias;
        this.depproductor = depproductor;
        this.munproductor = munproductor;
        this.depnoproductor = depnoproductor;
        this.puertos = puertos;
    }

    public Regalias(Integer idregalias, double porcmunicipio, double porcregalias, int depproductor, int munproductor, int depnoproductor, int puertos, Integer anio, Integer mes, Double precio, Double regalias, Campo idcampo, Departamento iddepartamento, Municipio idmunicipio, Produccion idproduccion) {
        this.idregalias = idregalias;
        this.porcmunicipio = porcmunicipio;
        this.porcregalias = porcregalias;
        this.depproductor = depproductor;
        this.munproductor = munproductor;
        this.depnoproductor = depnoproductor;
        this.puertos = puertos;
        this.anio = anio;
        this.mes = mes;
        this.precio = precio;
        this.regalias = regalias;
        this.campo = idcampo;
        this.departamento = iddepartamento;
        this.municipio = idmunicipio;
        this.produccion = idproduccion;
    }

    public Regalias(double porcmunicipio, double porcregalias, int depproductor, int munproductor, int depnoproductor, int puertos, Integer anio, Integer mes, Double precio, Double regalias, Campo idcampo, Departamento iddepartamento, Municipio idmunicipio, Produccion idproduccion) {
        this.porcmunicipio = porcmunicipio;
        this.porcregalias = porcregalias;
        this.depproductor = depproductor;
        this.munproductor = munproductor;
        this.depnoproductor = depnoproductor;
        this.puertos = puertos;
        this.anio = anio;
        this.mes = mes;
        this.precio = precio;
        this.regalias = regalias;
        this.campo = idcampo;
        this.departamento = iddepartamento;
        this.municipio = idmunicipio;
        this.produccion = idproduccion;
    }

    public Integer getIdregalias() {
        return idregalias;
    }

    public void setIdregalias(Integer idregalias) {
        this.idregalias = idregalias;
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

    public Campo getCampo() {
        return campo;
    }

    public void setCampo(Campo campo) {
        this.campo = campo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Produccion getProduccion() {
        return produccion;
    }

    public void setProduccion(Produccion produccion) {
        this.produccion = produccion;
    }    
   

    @Override
    public String toString() {
        return "com.planit.scr.modelos.Regalias[ idregalias=" + idregalias + " ]";
    }

}
