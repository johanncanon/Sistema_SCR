/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Campo;
import com.planit.scr.modelos.Produccion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author VaioDevelopment
 */
public class ProduccionDao {

    //Calculos
    public double convertirABarrilesEquivalentes(double produccionGas) {
        double barrilesequivalentes;
        barrilesequivalentes = (produccionGas / (double) 5700);
        return barrilesequivalentes;
    }

    //Metodos
    public void registrarProduccion(Produccion p) throws Exception {
        Statement st = ConexionSQL.conexion();
        try {
            try {
                String sql = "INSERT INTO public.produccion (idcampo, produccionhdia, produccionhmes, producciongdia, producciongmes, producciontotaldia, producciontotalmes, mes, anio)"
                        + " VALUES('" + p.getIdcampo().getIdcampo() + "', "
                        + "'" + p.getProduccionhdia() + "', "
                        + "'" + p.getProduccionhmes() + "', "
                        + "'" + p.getProducciongdia() + "', "
                        + "'" + p.getProducciongmes() + "', "
                        + "'" + p.getProducciontotaldia() + "', "
                        + "'" + p.getProducciontotalmes() + "', "
                        + "'" + p.getMes() + "', "
                        + "'" + p.getAnio() + "')";
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

    public Produccion consultarProduccionCampo(Produccion p) throws Exception {
        Statement st = ConexionSQL.conexion();
        Produccion produccion = new Produccion();
        CamposDao camposDao = new CamposDao();
        try {
            try {
                String sql = "SELECT idproduccion, idcampo, produccionhdia, produccionhmes, producciongdia, producciongmes, producciontotaldia, producciontotalmes, mes, anio "
                        + "FROM public.produccion where (anio = '" + p.getAnio() + "' AND mes = '" + p.getMes() + "' AND idcampo = '" + p.getIdcampo().getIdcampo() + "') OR idproduccion = '" + p.getIdproduccion() + "'";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    produccion = new Produccion(rs.getInt(1),
                            rs.getDouble(3),
                            rs.getDouble(4),
                            rs.getDouble(5),
                            rs.getDouble(6),
                            rs.getDouble(7),
                            rs.getInt(9),
                            rs.getInt(10),
                            rs.getDouble(8),
                            camposDao.consultarCampo(new Campo(rs.getInt(2))));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        return produccion;
    }
}
