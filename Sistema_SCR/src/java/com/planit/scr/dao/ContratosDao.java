/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.controladores.MunicipioCT;
import com.planit.scr.controladores.TipoCT;
import com.planit.scr.modelos.Contratos;
import com.planit.scr.modelos.Municipios;
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
public class ContratosDao {

    private final Statement st = ConexionSQL.conexion();

    //Metodos 
    public void registrarContrato(Contratos contrato) throws Exception {
        Statement st = ConexionSQL.conexion();
        TipoDao tipoDao = new TipoDao();
        MunicipiosDao municipioDao = new MunicipiosDao();

        contrato.setIdtipo(tipoDao.consultarTipo(contrato.getIdtipo()));
        contrato.setIdmunicipio(municipioDao.consultarMunicipio(contrato.getIdmunicipio()));
        try {
            try {
                String sql = "INSERT INTO public.contratos(nombre, idmunicipio, idtipo)"
                        + " VALUES('" + contrato.getNombre() + "', " + contrato.getIdmunicipio().getIdmunicipio() + ", " + contrato.getIdtipo().getIdtipo() + ")";
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

    public Contratos consultarContrato(Contratos c) throws Exception {
        Statement st = ConexionSQL.conexion();
        Contratos nuevocontrato = new Contratos();
        TipoDao tipoDao = new TipoDao();
        MunicipiosDao municipioDao = new MunicipiosDao();
        try {
            try {
                String sql = "SELECT idcontrato, nombre, idmunicipio, idtipo, cib, car, cov FROM public.contratos"
                        + " WHERE idcontrato = " + c.getIdcontrato() + " or nombre = '" + c.getNombre() + "' ";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevocontrato = new Contratos(rs.getInt(1), rs.getString(2), tipoDao.consultarTipo(new Tipos(rs.getInt(4))), municipioDao.consultarMunicipio(new Municipios(rs.getInt(3))), rs.getInt(4), rs.getInt(5), rs.getInt(6));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        return nuevocontrato;
    }

    public List<Contratos> consultarContratos() throws Exception {
        Statement st = ConexionSQL.conexion();
        List<Contratos> listacontratos = new ArrayList<>();
        TipoDao tipoDao = new TipoDao();
        MunicipiosDao municipioDao = new MunicipiosDao();
        try {
            try {
                String sql = "SELECT idcontrato, nombre, idmunicipio, idtipo, cib, car, cov FROM public.contratos";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listacontratos.add(new Contratos(rs.getInt(1), rs.getString(2), tipoDao.consultarTipo(new Tipos(rs.getInt(4))), municipioDao.consultarMunicipio(new Municipios(rs.getInt(3))), rs.getInt(4), rs.getInt(5), rs.getInt(6)));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return listacontratos;
    }

    public List<Contratos> consultarContratosSegunMunicipio(Municipios municipio) throws Exception {
        Statement st = ConexionSQL.conexion();
        List<Contratos> listacontratos = new ArrayList<>();
        TipoDao tipoDao = new TipoDao();
        MunicipiosDao municipioDao = new MunicipiosDao();
        try {
            try {
                String sql = "SELECT idcontrato, nombre, idmunicipio, idtipo, cib, car, cov FROM public.contratos "
                        + "WHERE idmunicipio = " + municipio.getIdmunicipio() + "";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listacontratos.add(new Contratos(rs.getInt(1), rs.getString(2), tipoDao.consultarTipo(new Tipos(rs.getInt(4))), municipioDao.consultarMunicipio(new Municipios(rs.getInt(3))), rs.getInt(4), rs.getInt(5), rs.getInt(6)));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }finally {
            ConexionSQL.CerrarConexion();
        }
        return listacontratos;
    }
}
