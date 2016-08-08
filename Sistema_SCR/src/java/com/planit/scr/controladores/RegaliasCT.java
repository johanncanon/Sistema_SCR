/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Pbl;
import com.planit.scr.modelos.Produccion;
import java.sql.Statement;

/**
 *
 * @author Desarrollo_Planit
 */
public class RegaliasCT {

    private Produccion produccion;
    private final Statement st = ConexionSQL.conexion();
    private Pbl pbl;

    public RegaliasCT() {
        produccion = new Produccion();
        pbl = new Pbl();
    }
    
    public Produccion getProduccion() {
        return produccion;
    }

    public void setProduccion(Produccion produccion) {
        this.produccion = produccion;
    }

    public Pbl getPbl() {
        return pbl;
    }

    public void setPbl(Pbl pbl) {
        this.pbl = pbl;
    }
    
    //Metodos
    public void calcularRegalias(){    
    }

}
