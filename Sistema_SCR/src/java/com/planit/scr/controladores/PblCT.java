/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.dao.PblDao;
import com.planit.scr.dao.ValoresDao;
import com.planit.scr.modelos.Valores;
import com.planit.scr.modelos.Municipio;
import com.planit.scr.modelos.Pbl;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class PblCT {

    private Pbl pbl;
    private List<Pbl> pbls;
    private Municipio municipio;
    private Valores valores;
    private int mes;
    
    private String nombreOperacion;
    private int operacion;

    public PblCT() {
        pbl = new Pbl();
        pbls = new ArrayList<>();
        municipio = new Municipio();
        valores = new Valores();
        
        nombreOperacion = "Registrar";
        operacion = 0;
    }

    public Pbl getPbl() {
        return pbl;
    }

    public void setPbl(Pbl pbl) {
        this.pbl = pbl;
    }

    public List<Pbl> getPbls() {
        return pbls;
    }

    public void setPbls(List<Pbl> pbls) {
        this.pbls = pbls;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Valores getValores() {
        return valores;
    }

    public void setValores(Valores valores) {
        this.valores = valores;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public String getNombreOperacion() {
        return nombreOperacion;
    }

    public void setNombreOperacion(String nombreOperacion) {
        this.nombreOperacion = nombreOperacion;
    }

    public int getOperacion() {
        return operacion;
    }

    public void setOperacion(int operacion) {
        this.operacion = operacion;
    }  
     
    
    //Metodos   
    public void consultarPbl() throws Exception {
        PblDao pblDao = new PblDao();
        ValoresDao valoresDao = new ValoresDao();
        
        //Verifica el año de consulta para determinar la forma en que se registra el pbl
        if (pbl.getAnio() >= 2012) {
            pbl.setTrimestreMes(mes);
        } else if (pbl.getAnio() < 2012) {
            pbl.setTrimestreMes(pblDao.obtenerTrimestre(mes, pbl.getAnio()));
        }
        
        //Trae los datos del pbl calculado
        pbls = pblDao.consultarPblSegunMunicipio(municipio, pbl);
        valores = valoresDao.consultarValores(pbl.getTrimestreMes(), pbl.getAnio());
    }
}
