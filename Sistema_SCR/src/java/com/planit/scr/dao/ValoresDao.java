/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Valores;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author VaioDevelopment
 */
public class ValoresDao {

    public void registrarValores(Valores valor) throws Exception {
        Statement st = ConexionSQL.conexion();
        Calendar C = Calendar.getInstance();
        double vt = ((valor.getV1() + valor.getV2()));
        try {
            try {
                String sql = "INSERT INTO public.valores (px, pf, v1, v2, trimestre, vt, ctc, ctmd, cmt, ctmc, cr, cce, ctme, anio)"
                        + " VALUES(" + valor.getPx() + ","
                        + " " + valor.getPf() + ","
                        + " " + valor.getV1() + ","
                        + " " + valor.getV2() + ","
                        + " 'Trimestre " + valor.getTrimestre() + "',"
                        + " " + valor.getVt() + ","
                        + " " + valor.getCtc() + ","
                        + " " + valor.getCtmd() + ","
                        + " " + valor.getCmt() + ","
                        + " " + valor.getCtmc() + ","
                        + " " + valor.getCr() + ","
                        + " " + valor.getCce() + ","
                        + " " + valor.getCtme() + ","
                        + " '" + valor.getAnio() + "')";

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

    public List<Valores> consultarValores() throws Exception {
        Statement st = ConexionSQL.conexion();
        List<Valores> listaValores = new ArrayList<>();
        try {
            try {
                String sql = "SELECT idvalores, px, pf, v1, v2, trimestre, vt, ctc, ctmd, cmt, ctmc, cr, cce, ctme, anio FROM public.valores";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listaValores.add(new Valores(rs.getInt(1), rs.getDouble(2), rs.getDouble(3), rs.getDouble(4), rs.getDouble(5), rs.getString(6), rs.getDouble(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10), rs.getDouble(11), rs.getDouble(12), rs.getDouble(13), rs.getDouble(14), rs.getString(15)));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        return listaValores;
    }
}
