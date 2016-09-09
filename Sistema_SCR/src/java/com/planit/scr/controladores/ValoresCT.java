/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.dao.ValoresDao;
import com.planit.scr.modelos.Valores;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

/**
 *
 * @author Desarrollo_Planit
 */
public class ValoresCT {

    private Valores valor;
    private List<Valores> valores;

    public ValoresCT() {
        valor = new Valores();
        valores = new ArrayList<>();
        System.out.println("" + Thread.currentThread().getContextClassLoader().getResource("/").getPath());
    }

    public Valores getValor() {
        return valor;
    }

    public void setValor(Valores valor) {
        this.valor = valor;
    }

    public List<Valores> getValores() {
        return valores;
    }

    public void setValores(List<Valores> valores) {
        this.valores = valores;
    }

    @PostConstruct
    public void init() {
        ValoresDao valorDao = new ValoresDao();
        try {
            valores = valorDao.consultarValores();
        } catch (Exception ex) {
            Logger.getLogger(ContratoCT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ValoresCT(Valores valor, List<Valores> valores) {
        this.valor = valor;
        this.valores = valores;
    }

    public void registrarValores() throws Exception {
        ValoresDao valorDao = new ValoresDao();
        valorDao.registrarValores(valor);
        valor = new Valores();
        valores = valorDao.consultarValores();
    }  

}
