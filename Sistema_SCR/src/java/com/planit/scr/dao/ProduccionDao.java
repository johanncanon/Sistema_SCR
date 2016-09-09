/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Produccion;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author VaioDevelopment
 */
public class ProduccionDao {

    //Metodos
    public void registrarProduccion(Produccion p) throws Exception {
        Statement st = ConexionSQL.conexion();
        try {
            try {
                String sql = "INSERT INTO public.produccion (idcampo, fecha, produccion)"
                        + " VALUES('" + p.getIdcampo().getIdcampo() + "', '" + p.getFecha() + "', '" + p.getProduccion() + "')";
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
}
