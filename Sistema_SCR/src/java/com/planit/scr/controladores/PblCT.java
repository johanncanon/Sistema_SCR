/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.dao.PblDao;
import com.planit.scr.modelos.Campos;
import com.planit.scr.modelos.Pbl;
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
public class PblCT {

    private Campos campo;
    private List<Campos> campos;
   

    public PblCT() {
    }

    public PblCT(Campos campo, List<Campos> campos) {
        this.campo = campo;
        this.campos = campos;
    }

    public Campos getCampo() {
        return campo;
    }

    public void setCampo(Campos campo) {
        this.campo = campo;
    }

    public List<Campos> getCampos() {
        return campos;
    }

    public void setCampos(List<Campos> campos) {
        this.campos = campos;
    }

    @PostConstruct
    public void init() {
        PblDao pblDao = new PblDao();
        try {
            campos = pblDao.consultarCampos();
        } catch (Exception ex) {
            Logger.getLogger(ContratoCT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void registrarPbl(Pbl pbl) throws Exception {
        PblDao pblDao = new PblDao();
        pblDao.registrarPbl(pbl);
    }

    

    public Pbl calcularPBL(Pbl pbl) {
//        pbl.setVt(pbl.getV1() + pbl.getV2());
//        pbl.setCt1(pbl.getCtc() + pbl.getCtmc() + pbl.getCtmd() + pbl.getCmt() + pbl.getCr());
//        pbl.setCt2(pbl.getCce() + pbl.getCtme());
//        pbl.setPrc((pbl.getPf() - pbl.getCt1()) * (pbl.getV1() / pbl.getVt()) + (pbl.getPx() - pbl.getCt2()) * (pbl.getV2() / pbl.getVt()));
        return pbl;
    }
}
