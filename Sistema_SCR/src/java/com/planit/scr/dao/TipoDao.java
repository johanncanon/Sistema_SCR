/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Tipos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VaioDevelopment
 */
public class TipoDao {

    //Metodos
    public Tipos consultarTipo(Tipos t) throws Exception {
        Statement st = ConexionSQL.conexion();
        Tipos nuevotipo = new Tipos();
        try {
            try {
                String sql = "SELECT idtipo, nombre FROM public.tipos "
                        + "WHERE idtipo = " + t.getIdtipo() + " or nombre = '" + t.getNombre() + "'";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevotipo = new Tipos(rs.getInt(1), rs.getString(2));
                }
            } catch (SQLException e) {
                nuevotipo = new Tipos();
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        return nuevotipo;
    }

    public List<Tipos> consultarTipos() throws Exception {
        Statement st = ConexionSQL.conexion();
        List<Tipos> listatipos = new ArrayList<>();
        try {
            try {
                String sql = "SELECT idtipo, nombre FROM public.tipos";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listatipos.add(new Tipos(rs.getInt(1), rs.getString(2)));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        return listatipos;
    }
}
