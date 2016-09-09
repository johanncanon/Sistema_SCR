/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Trm;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VaioDevelopment
 */
public class TrmDao {

    //Metodos
    public void registrarTrm(Trm trm) throws Exception {
        Statement st = ConexionSQL.conexion();
        try {
            try {
                String sql = "INSERT INTO public.trm(fecha, valor) "
                        + "VALUES('" + trm.getFecha().toString() + "','" + trm.getValor() + "')";
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

    public List<Trm> consultarTrm() throws Exception {
        Statement st = ConexionSQL.conexion();
        List<Trm> lista = new ArrayList<>();
        try {
            try {
                String sql = "SELECT idtrm, fecha, valor FROM public.trm";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    lista.add(new Trm(rs.getInt(1), rs.getDate(2), rs.getInt(3)));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        return lista;
    }

    public Trm consultarTrm(Trm tr) throws Exception {
        Statement st = ConexionSQL.conexion();
        Trm nuevotrm = new Trm();
        try {
            try {
                String sql = "SELECT idtrm, fecha, valor FROM public.trm "
                        + "WHERE idtrm = " + tr.getIdtrm() + " or fecha = '" + tr.getFecha() + "'";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevotrm = new Trm(rs.getInt(1), rs.getDate(2), rs.getInt(3));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        return nuevotrm;
    }
}
