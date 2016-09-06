/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Campos;
import com.planit.scr.modelos.Pbl;
import com.planit.scr.modelos.Valores;
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
    private final Statement st = ConexionSQL.conexion();

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
        try {
            campos = consultarCampos();
        } catch (Exception ex) {
            Logger.getLogger(ContratoCT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void registrarPbl(Pbl pbl) throws Exception {
        try {
            try {
                String sql = "";
//                String sql = "INSERT INTO public.pbl (v1, v2, vt, pf, px, ctc, cmt, ctmc, ctmd, cr, ct1, cce, ctme, ct2, idcampo, trimestre, prc)"
//                        + " VALUES(" + pbl.getV1() + "," + pbl.getV2() + ","
//                        + " " + pbl.getVt() + ", " + pbl.getPf() + ","
//                        + " " + pbl.getPx() + ", " + pbl.getCtc() + ","
//                        + " " + pbl.getCmt() + ", " + pbl.getCtmc() + ","
//                        + " " + pbl.getCtmd() + ", " + pbl.getCr() + ","
//                        + " " + pbl.getCt1() + ", " + pbl.getCce() + ","
//                        + " " + pbl.getCtme() + ", " + pbl.getCt2() + ","
//                        + " " + pbl.getIdcampo().getIdcampo() + ", " + pbl.getTrimestre() + ","
//                        + " " + pbl.getPrc() + ")";
                st.execute(sql);
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Campos> consultarCampos() throws Exception {
        List<Campos> listaCampos = new ArrayList<>();
        try {
            try {
                String sql = "SELECT idcampo, nombre, cib, car, cov"
                        + "  FROM public.campos;";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listaCampos.add(new Campos(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return listaCampos;
    }

    public Pbl calcularPBL(Pbl pbl) {
//        pbl.setVt(pbl.getV1() + pbl.getV2());
//        pbl.setCt1(pbl.getCtc() + pbl.getCtmc() + pbl.getCtmd() + pbl.getCmt() + pbl.getCr());
//        pbl.setCt2(pbl.getCce() + pbl.getCtme());
//        pbl.setPrc((pbl.getPf() - pbl.getCt1()) * (pbl.getV1() / pbl.getVt()) + (pbl.getPx() - pbl.getCt2()) * (pbl.getV2() / pbl.getVt()));
        return pbl;
    }
}
