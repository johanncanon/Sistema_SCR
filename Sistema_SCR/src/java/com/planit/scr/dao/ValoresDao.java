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
import java.util.List;

/**
 *
 * @author VaioDevelopment
 */
public class ValoresDao {

    public int registrarValores(Valores valor) throws Exception {
        int resultado  = 0;
        Statement st = ConexionSQL.conexion();       
        try {
            try {
                String sql = "INSERT INTO public.valores (v1, v2, trimestre_mes, vt, ctc, ctmd, cmt, ctmc, cr, cce, ctme, anio)"
                        + " VALUES(" + valor.getV1() + ","
                        + " " + valor.getV2() + ","
                        + " " + valor.getTrimestreMes() + ","
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
                resultado = 1;
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        return resultado;
    }

    public int modificarValores(Valores valor) throws Exception {
        int resultado = 0;
        Statement st = ConexionSQL.conexion();        
        try {
            try {
                String sql = "UPDATE public.valores SET "
                        + " v1 = " + valor.getV1() + ","
                        + " v2 = " + valor.getV2() + ","
                        + " trimestre_mes = " + valor.getTrimestreMes() + ", "
                        + " vt = " + valor.getVt() + ","
                        + " ctc = " + valor.getCtc() + ","
                        + " ctmd = " + valor.getCtmd() + ","
                        + " cmt = " + valor.getCmt() + ","
                        + " ctmc = " + valor.getCtmc() + ","
                        + " cr = " + valor.getCr() + ","
                        + " cce = " + valor.getCce() + ","
                        + " ctme = " + valor.getCtme() + ","
                        + " anio = '" + valor.getAnio() + "'"
                        + " WHERE idvalores = '" + valor.getIdvalores() + "'";

                st.execute(sql);
                resultado = 1;
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        return resultado;
    }
    
    public int eliminarValores(Valores valor) throws Exception{
        int resultado = 0;
        Statement st = ConexionSQL.conexion();        
        try {
            try {
                String sql = "DELETE FROM public.valores"
                        + " WHERE idvalores = '" + valor.getIdvalores() + "'";

                st.execute(sql);
                resultado = 1;
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        return resultado;
    }

    public List<Valores> consultarValores() throws Exception {
        Statement st = ConexionSQL.conexion();
        List<Valores> listaValores = new ArrayList<>();
        try {
            try {
                String sql = "SELECT idvalores, v1, v2, vt, ctmd, cmt, ctmc,cr, cce, ctme, ctc, trimestre_mes, anio FROM public.valores";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listaValores.add(new Valores(rs.getInt(1), rs.getDouble(2), rs.getDouble(3), rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10), rs.getDouble(11), rs.getInt(12), rs.getInt(13)));
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

    public Valores consultarValores(int trimestreMes, int anio) throws Exception {
        Statement st = ConexionSQL.conexion();
        Valores valores = new Valores();
        try {
            try {
                String sql = "SELECT idvalores, v1, v2, vt, ctmd, cmt, ctmc, cr, cce, ctme, ctc, trimestre_mes, anio FROM public.valores"
                        + " WHERE anio = " + anio + " AND trimestre_mes = " + trimestreMes + "";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    valores = new Valores(rs.getInt(1), rs.getDouble(2), rs.getDouble(3), rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10), rs.getDouble(11), rs.getInt(12), rs.getInt(13));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        return valores;
    }
}
