/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.modelos;

/**
 *
 * @author Desarrollo_Planit
 */
public class Regalias {
    
    private Campos campo;
    private Produccion produccion;
    private Pbl pbl;
    private double porcentaje;
    private double regalias;

    public Regalias() {
        campo = new Campos();
        produccion = new Produccion();
        pbl = new Pbl();       
       
    }

    public Regalias(Campos campo) {
        this.campo = campo;
    }    

    public Regalias(Campos campo, Produccion produccion, Pbl pbl, double porcentaje, double regalias) {
        this.campo = campo;
        this.produccion = produccion;
        this.pbl = pbl;
        this.porcentaje = porcentaje;
        this.regalias = regalias;
    }   
             
    public double getRegalias() {
        return regalias;
    }

    public void setRegalias(double regalias) {
        this.regalias = regalias;
    }
    
      

    public Produccion getProduccion() {
        return produccion;
    }

    public void setProduccion(Produccion produccion) {
        this.produccion = produccion;
    }   
   

    public Campos getCampo() {
        return campo;
    }

    public void setCampo(Campos campo) {
        this.campo = campo;
    }

    public Pbl getPbl() {
        return pbl;
    }

    public void setPbl(Pbl pbl) {
        this.pbl = pbl;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

       
}
