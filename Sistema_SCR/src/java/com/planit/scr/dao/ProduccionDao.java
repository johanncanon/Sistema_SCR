/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.Pool;
import com.planit.scr.metodos.Redondear;
import com.planit.scr.modelos.Campo;
import com.planit.scr.modelos.Contrato;
import com.planit.scr.modelos.Municipio;
import com.planit.scr.modelos.Produccion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author VaioDevelopment
 */
public class ProduccionDao {

    private final Pool pool = new Pool();

    //Calculos
    public double convertirABarrilesEquivalentes(double produccionGas) {
        double barrilesequivalentes;
        barrilesequivalentes = (produccionGas / (double) 5700);
        return barrilesequivalentes;
    }

    //Metodos
    public int registrarProduccion(Produccion p) throws Exception {
        int resultado = 0;
        try {
            Connection con = pool.dataSource.getConnection();
            Statement st = con.createStatement();
            try {
                String sql = "INSERT INTO public.produccion (idcampo, produccionhdia, produccionhmes, producciongdia, producciongmes, producciontotaldia, producciontotalmes, mes, anio, idmunicipio, idcontrato)"
                        + " VALUES('" + p.getCampo().getIdcampo() + "', "
                        + "'" + p.getProduccionhdia() + "', "
                        + "'" + p.getProduccionhmes() + "', "
                        + "'" + p.getProducciongdia() + "', "
                        + "'" + p.getProducciongmes() + "', "
                        + "'" + Redondear.redondear(p.getProducciontotaldia(), 3) + "', "
                        + "'" + Redondear.redondear(p.getProducciontotalmes(), 3) + "', "
                        + "'" + p.getMes() + "', "
                        + "'" + p.getAnio() + "',"
                        + "'" + p.getMunicipio().getIdmunicipio() + "',"
                        + "'" + p.getContrato().getIdcontrato() + "')";
                st.execute(sql);
                resultado = 1;
                st.close();
                con.close();
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    public Produccion consultarProduccionCampoContrato(Produccion p) throws Exception {

        Produccion produccion = new Produccion();
        CamposDao camposDao = new CamposDao();
        MunicipiosDao municipiosDao = new MunicipiosDao();
        ContratosDao contratosDao = new ContratosDao();
        try {
            Connection con = pool.dataSource.getConnection();
            Statement st = con.createStatement();
            try {
                String sql = "SELECT idproduccion, idcampo, produccionhdia, produccionhmes, producciongdia, producciongmes, producciontotaldia, producciontotalmes, mes, anio, idmunicipio, idcontrato "
                        + "FROM public.produccion where (anio = '" + p.getAnio() + "' AND mes = '" + p.getMes() + "' AND idcampo = '" + p.getCampo().getIdcampo() + "' AND idcontrato = '" + p.getContrato().getIdcontrato() + "' AND idmunicipio = '" + p.getMunicipio().getIdmunicipio() + "') OR idproduccion = '" + p.getIdproduccion() + "'";
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
                            camposDao.consultarCampo(new Campo(rs.getInt(2))),
                            contratosDao.consultarContrato(new Contrato(rs.getInt(12))),
                            municipiosDao.consultarMunicipio(new Municipio(rs.getInt(11))));
                }
                rs.close();
                st.close();
                con.close();
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return produccion;
    }

    public boolean verificarRegistroProduccionMunicipio(Municipio municipio, int anio, int mes) throws Exception {

        boolean valor = false;
        try {
            Connection con = pool.dataSource.getConnection();
            Statement st = con.createStatement();
            try {
                String sql = "SELECT Distinct true FROM public.produccion where exists(select * from public.produccion where idmunicipio = '" + municipio.getIdmunicipio() + "' AND anio = '" + anio + "' AND mes = '" + mes + "')";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    valor = rs.getBoolean(1);
                }
                rs.close();
                st.close();
                con.close();
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return valor;
    }
}
