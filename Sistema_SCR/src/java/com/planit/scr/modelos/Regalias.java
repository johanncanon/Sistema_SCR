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
    private Pbl pbl;
    private double porcentaje;
    private int regalias;

    public Regalias() {
    }

    public Regalias(Campos campo) {
        this.campo = campo;
    }    
    
    public Regalias(Campos campo, Pbl pbl, double porcentaje, int regalias) {
        this.campo = campo;
        this.pbl = pbl;
        this.porcentaje = porcentaje;
        this.regalias = regalias;
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

    public int getRegalias() {
        return regalias;
    }

    public void setRegalias(int regalias) {
        this.regalias = regalias;
    }  
       
}
