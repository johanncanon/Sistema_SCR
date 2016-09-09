/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Campos;
import com.planit.scr.modelos.Pbl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VaioDevelopment
 */
public class PblDao {

    
    public void registrarPbl(Pbl pbl) throws Exception {
        Statement st = ConexionSQL.conexion();
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
        } finally {
            ConexionSQL.CerrarConexion();
        }
    }

    public List<Campos> consultarCampos() throws Exception {
        Statement st = ConexionSQL.conexion();
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
        } finally {
            ConexionSQL.CerrarConexion();
        }
        return listaCampos;
    }
}
