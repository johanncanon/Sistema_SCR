/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Campo;
import com.planit.scr.modelos.Contrato;
import com.planit.scr.modelos.Municipio;
import com.planit.scr.modelos.Tipo;
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

    //Metodos 
    public int registrarContrato(Contrato contrato) throws Exception {
        Statement st = ConexionSQL.conexion();
        TipoDao tipoDao = new TipoDao();
        int resultado = 0;
        contrato.setTipo(tipoDao.consultarTipo(contrato.getTipo()));
        try {
            try {
                String sql = "INSERT INTO public.contratos(nombre, idtipo, cib, car, cov)"
                        + " VALUES('" + contrato.getNombre() + "',"
                        + " " + contrato.getTipo().getIdtipo() + ","
                        + " " + contrato.getCib() + ","
                        + " " + contrato.getCar() + ","
                        + " " + contrato.getCov() + ")";
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

    public int modificarContrato(Contrato contrato) throws Exception {
        Statement st = ConexionSQL.conexion();
        TipoDao tipoDao = new TipoDao();
        int resultado = 0;
        contrato.setTipo(tipoDao.consultarTipo(contrato.getTipo()));
        try {
            try {
                String sql = "UPDATE public.contratos SET nombre = '" + contrato.getNombre() + "',"
                        + " idtipo = " + contrato.getTipo().getIdtipo() + ","
                        + " cib = " + contrato.getCib() + ","
                        + " car = " + contrato.getCar() + ", "
                        + " cov = " + contrato.getCov() + ""
                        + " WHERE idcontrato = '" + contrato.getIdcontrato() + "'";
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

    public int eliminarContrato(Contrato contrato) throws Exception {
        Statement st = ConexionSQL.conexion();
        TipoDao tipoDao = new TipoDao();
        int resultado = 0;
        contrato.setTipo(tipoDao.consultarTipo(contrato.getTipo()));
        try {
            try {
                String sql = "DELETE FROM public.contratos WHERE idcontrato = '" + contrato.getIdcontrato() + "'";
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

    public Contrato consultarContrato(Contrato c) throws Exception {
        Statement st = ConexionSQL.conexion();
        Contrato nuevocontrato = new Contrato();
        TipoDao tipoDao = new TipoDao();
        try {
            try {
                String sql = "SELECT idcontrato, nombre, idtipo, cib, car, cov FROM public.contratos"
                        + " WHERE idcontrato = " + c.getIdcontrato() + " or nombre = '" + c.getNombre() + "' ";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevocontrato = new Contrato(rs.getInt(1), rs.getString(2), rs.getInt(4), rs.getInt(5), rs.getInt(6), tipoDao.consultarTipo(new Tipo(rs.getInt(3))));
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

    public Contrato consultarContratoSegunCampo(Campo campo) throws Exception {
        Statement st = ConexionSQL.conexion();
        Contrato nuevocontrato = new Contrato();
        TipoDao tipoDao = new TipoDao();
        try {
            try {
                String sql = "SELECT idcontrato, nombre, idtipo, cib, car, covFROM public.contratos as ct, public.campos as c"
                        + " WHERE c.idcampo = " + campo.getIdcampo() + " and c.idcontrato = ct.idcontrato";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevocontrato = new Contrato(rs.getInt(1), rs.getString(2), rs.getInt(4), rs.getInt(5), rs.getInt(6), tipoDao.consultarTipo(new Tipo(rs.getInt(3))));
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

    public List<Contrato> consultarContratos() throws Exception {
        Statement st = ConexionSQL.conexion();
        List<Contrato> listacontratos = new ArrayList<>();
        TipoDao tipoDao = new TipoDao();
        try {
            try {
                String sql = "SELECT idcontrato, nombre, idtipo, cib, car, cov FROM public.contratos";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listacontratos.add(new Contrato(rs.getInt(1), rs.getString(2), rs.getInt(4), rs.getInt(5), rs.getInt(6), tipoDao.consultarTipo(new Tipo(rs.getInt(3)))));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return listacontratos;
    }

    public List<Contrato> consultarContratosSegunCampo(Campo campo) throws Exception {
        Statement st = ConexionSQL.conexion();
        List<Contrato> listacontratos = new ArrayList<>();
        TipoDao tipoDao = new TipoDao();
        try {
            try {
                String sql = "SELECT cr.idcontrato, cr.nombre, cr.idtipo, cr.cib, cr.car, cr.cov FROM public.contratos as cr, public.campos_contratos as cc "
                        + "WHERE cr.idcontrato = cc.idcontrato and cc.idcampo = '" + campo.getIdcampo() + "'";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listacontratos.add(new Contrato(rs.getInt(1), rs.getString(2), rs.getInt(4), rs.getInt(5), rs.getInt(6), tipoDao.consultarTipo(new Tipo(rs.getInt(3)))));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return listacontratos;
    }

    public List<Contrato> consultarContratosSegunMunicipio(Municipio municipio) throws Exception {
        Statement st = ConexionSQL.conexion();
        List<Contrato> listacontratos = new ArrayList<>();
        TipoDao tipoDao = new TipoDao();
        try {
            try {
                String sql = "SELECT c.idcontrato, c.nombre, c.idtipo, c.cib, c.car, c.cov FROM public.contratos as c, public.municipios_contratos as mc "
                        + "WHERE mc.idmunicipio = " + municipio.getIdmunicipio() + " and mc.idcontrato = c.idcontrato ";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listacontratos.add(new Contrato(rs.getInt(1), rs.getString(2), rs.getInt(4), rs.getInt(5), rs.getInt(6), tipoDao.consultarTipo(new Tipo(rs.getInt(3)))));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        return listacontratos;
    }

    public List<Contrato> buscarContratos(String valor) throws Exception {
        Statement st = ConexionSQL.conexion();
        List<Contrato> listacontratos = new ArrayList<>();
        TipoDao tipoDao = new TipoDao();
        try {
            try {
                String sql = "SELECT c.idcontrato, c.nombre, c.idtipo, c.cib, c.car, c.cov FROM public.tipos as t, public.contratos as c, public.municipios_contratos as mc, public.municipios as m "
                        + "WHERE mc.idmunicipio = m.idmunicipio and mc.idcontrato = c.idcontrato and c.idtipo = t.idtipo and (c.nombre LIKE '%" + valor + "%' or t.nombre LIKE '%" + valor + "%' or m.nombre LIKE '%" + valor + "%')";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listacontratos.add(new Contrato(rs.getInt(1), rs.getString(2), rs.getInt(4), rs.getInt(5), rs.getInt(6), tipoDao.consultarTipo(new Tipo(rs.getInt(3)))));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        return listacontratos;
    }
}
