/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.dao.TipoDao;
import com.planit.scr.modelos.Tipos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

/**
 *
 * @author Desarrollo_Planit
 */
public class TipoCT {

    private Tipos tipo;
    private List<Tipos> tipos;
    

    public TipoCT() {
        tipo = new Tipos();
        tipos = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        TipoDao tipoDao = new TipoDao();
        try {
            tipos = tipoDao.consultarTipos();
        } catch (Exception ex) {
            Logger.getLogger(TipoCT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Tipos getTipo() {
        return tipo;
    }

    public void setTipo(Tipos tipo) {
        this.tipo = tipo;
    }

    public List<Tipos> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipos> tipos) {
        this.tipos = tipos;
    }    
}
