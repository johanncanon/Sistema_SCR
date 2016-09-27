/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Campos;
import com.planit.scr.modelos.Departamentos;
import com.planit.scr.modelos.Regalias;
import com.planit.scr.modelos.Municipios;
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
                String sql = "INSERT INTO public.regalias (iddepartamento, idmunicipio, idcampo, tipohidrocarburo,"
                        + " porcmunicipio, porcregalias, depproductor, munproductor, depnoproductor, puertos, anio, mes, precio, regalias, idproduccion)"
                        + " VALUES(" + regalia.getIddepartamento().getIddepartamento() + ","
                        + "" + regalia.getIdmunicipio().getIdmunicipio() + ","
                        + "" + regalia.getIdcampo().getIdcampo() + ","
                        + "'" + regalia.getTipohidrocarburo() + "',"
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
                        + "" + regalia.getIdproduccion().getIdproduccion() + ")";

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

        regalia.setIdmunicipio(municipiosDao.consultarMunicipio(regalia.getIdmunicipio()));
        try {
            try {
                String sql = "SELECT idregalias, iddepartamento, idmunicipio, idcampo, tipohidrocarburo, porcmunicipio, porcregalias, depproductor, munproductor, depnoproductor, puertos, anio, mes, precio, regalias, idproduccion"
                        + " FROM public.regalias"
                        + " WHERE idmunicipio = " + regalia.getIdmunicipio().getIdmunicipio() + " AND anio = " + regalia.getAnio() + " and mes = " + regalia.getMes() + "";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    regalias.add(new Regalias(rs.getInt(1),
                            rs.getString(5),                            
                            rs.getDouble(6),
                            rs.getDouble(7),
                            rs.getInt(8),
                            rs.getInt(9),
                            rs.getInt(10),
                            rs.getInt(11),
                            rs.getInt(12),
                            rs.getInt(13),
                            rs.getDouble(14),
                            rs.getDouble(15),
                            campoDao.consultarCampo(new Campos(rs.getInt(4))),
                            departamentosDao.consultarDepartamento(new Departamentos(rs.getInt(2))),
                            municipiosDao.consultarMunicipio(new Municipios(rs.getInt(3))),
                            new Produccion(rs.getInt(16))));
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
