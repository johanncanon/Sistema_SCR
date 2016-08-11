/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Pbl;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Desarrollo_Planit
 */
public class PblCT {

    private final Statement st = ConexionSQL.conexion();

    public PblCT() {
    }

    public void registrarPbl(Pbl pbl) throws Exception {
        try {
            try {
                String sql = "INSERT INTO public.pbl (v1, v2, vt, pf, px, ctc, cmt, ctmc, ctmd, cr, ct1, cce, ctme, ct2, idcampo, trimestre, prc)"
                        + " VALUES(" + pbl.getV1() + "," + pbl.getV2() + ","
                        + " " + pbl.getVt() + ", " + pbl.getPf() + ","
                        + " " + pbl.getPx() + ", " + pbl.getCtc() + ","
                        + " " + pbl.getCmt() + ", " + pbl.getCtmc() + ","
                        + " " + pbl.getCtmd() + ", " + pbl.getCr() + ","
                        + " " + pbl.getCt1() + ", " + pbl.getCce() + ","
                        + " " + pbl.getCtme() + ", " + pbl.getCt2() + ","
                        + " " + pbl.getIdcampo().getIdcampo() + ", " + pbl.getTrimestre() + ","
                        + " " + pbl.getPrc() + ")";
                st.execute(sql);
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Pbl calcularPBL(Pbl pbl){
        pbl.setVt(pbl.getV1() + pbl.getV2());
        pbl.setCt1(pbl.getCtc() + pbl.getCtmc() + pbl.getCtmd() + pbl.getCmt() + pbl.getCr());
        pbl.setCt2(pbl.getCce() + pbl.getCtme());
        pbl.setPrc((pbl.getPf() - pbl.getCt1())*(pbl.getV1()/pbl.getVt()) + (pbl.getPx() - pbl.getCt2())*(pbl.getV2()/pbl.getVt()));
        return pbl;
    }            
}
