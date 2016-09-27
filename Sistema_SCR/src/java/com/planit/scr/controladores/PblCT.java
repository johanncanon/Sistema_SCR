/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.dao.PblDao;
import com.planit.scr.dao.ValoresDao;
import com.planit.scr.modelos.Valores;
import com.planit.scr.modelos.Municipios;
import com.planit.scr.modelos.Pbl;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

/**
 *
 * @author Desarrollo_Planit
 */
public class PblCT {

    private Pbl pbl;
    private List<Pbl> pbls;
    private Municipios municipio;
    private Valores valores;
    private int mes;

    public PblCT() {
        pbl = new Pbl();
        pbls = new ArrayList<>();
        municipio = new Municipios();
        valores = new Valores();
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

    public Municipios getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipios municipio) {
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
