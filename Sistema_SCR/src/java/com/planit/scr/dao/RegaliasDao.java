/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Campo;
import com.planit.scr.modelos.Departamento;
import com.planit.scr.modelos.Regalias;
import com.planit.scr.modelos.Municipio;
import com.planit.scr.modelos.Produccion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VaioDevelopment
 */
public class RegaliasDao {

    public void registrarRegalias(Regalias regalia) throws Exception {
        Statement st = ConexionSQL.conexion();
        try {
            try {
                String sql = "INSERT INTO public.regalias (iddepartamento, idmunicipio, idcampo,"
                        + " porcmunicipio, porcregalias, depproductor, munproductor, depnoproductor, puertos, anio, mes, precio, regalias, idproduccion)"
                        + " VALUES(" + regalia.getDepartamento().getIddepartamento() + ","
                        + "" + regalia.getMunicipio().getIdmunicipio() + ","
                        + "" + regalia.getCampo().getIdcampo() + ","     
                        + "" + regalia.getPorcmunicipio() + ","
                        + "" + regalia.getPorcregalias() + ","
                        + "" + regalia.getDepproductor() + ","
                        + "" + regalia.getMunproductor() + ","
                        + "" + regalia.getDepnoproductor() + ","
                        + "" + regalia.getPuertos() + ","
                        + "" + regalia.getAnio() + ","
                        + "" + regalia.getMes() + ","
                        + "" + regalia.getPrecio() + ","
                        + "" + regalia.getRegalias() + ","
                        + "" + regalia.getProduccion().getIdproduccion() + ")";

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

    public List<Regalias> consultarRegalias(Regalias regalia) throws Exception {
        List<Regalias> regalias = new ArrayList<>();
        Statement st = ConexionSQL.conexion();
        DepartamentosDao departamentosDao = new DepartamentosDao();
        MunicipiosDao municipiosDao = new MunicipiosDao();
        CamposDao campoDao = new CamposDao();
        ProduccionDao produccionDao = new ProduccionDao();

        regalia.setMunicipio(municipiosDao.consultarMunicipio(regalia.getMunicipio()));
        try {
            try {
                String sql = "SELECT idregalias, iddepartamento, idmunicipio, idcampo, porcmunicipio, porcregalias, depproductor, munproductor, depnoproductor, puertos, anio, mes, precio, regalias, idproduccion"
                        + " FROM public.regalias"
                        + " WHERE idmunicipio = " + regalia.getMunicipio().getIdmunicipio() + " AND anio = " + regalia.getAnio() + " and mes = " + regalia.getMes() + "";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    regalias.add(new Regalias(rs.getInt(1),                                               
                            rs.getDouble(5),
                            rs.getDouble(6),
                            rs.getInt(7),
                            rs.getInt(8),
                            rs.getInt(9),
                            rs.getInt(10),
                            rs.getInt(11),
                            rs.getInt(12),
                            rs.getDouble(13),
                            rs.getDouble(14),
                            campoDao.consultarCampo(new Campo(rs.getInt(4))),
                            departamentosDao.consultarDepartamento(new Departamento(rs.getInt(2))),
                            municipiosDao.consultarMunicipio(new Municipio(rs.getInt(3))),
                            produccionDao.consultarProduccionCampo(new Produccion(rs.getInt(15)))));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        return regalias;
    }
}
