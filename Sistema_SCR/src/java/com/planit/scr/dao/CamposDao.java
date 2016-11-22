/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.Pool;
import com.planit.scr.modelos.Campo;
import com.planit.scr.modelos.CampoCompleto;
import com.planit.scr.modelos.Contrato;
import com.planit.scr.modelos.Municipio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VaioDevelopment
 */
public class CamposDao {

    private final MunicipiosDao municipioDao = new MunicipiosDao();
    private final ContratosDao contratosDao = new ContratosDao();
    private final Pool pool = new Pool();

    public int registrarCampo(Campo campo) throws Exception {
        int resultado = 0;
        try {
            Connection con = pool.dataSource.getConnection();
            Statement st = con.createStatement();
            try {
                String sql = "INSERT INTO public.campos("
                        + "           nombre, porcentaje)"
                        + " VALUES('" + campo.getNombre() + "', '" + campo.getPorcentaje() + "')";
                st.execute(sql);
                st.close();
                con.close();
                resultado = 1;
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    public int modificarCampo(Campo campo) throws Exception {
        int resultado = 0;
        try {
            Connection con = pool.dataSource.getConnection();
            Statement st = con.createStatement();
            try {
                String sql = "UPDATE public.campos "
                        + " SET nombre = '" + campo.getNombre() + "', porcentaje = '" + campo.getPorcentaje() + "'"
                        + " WHERE idcampo = '" + campo.getIdcampo() + "'";
                st.execute(sql);
                st.close();
                con.close();
                resultado = 1;
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    public int eliminarCampo(Campo campo) throws Exception {
        int resultado = 0;
        try {
            Connection con = pool.dataSource.getConnection();
            Statement st = con.createStatement();
            try {
                String sql = "DELETE FROM public.campos "
                        + "WHERE idcampo = '" + campo.getIdcampo() + "'";
                st.execute(sql);
                st.close();
                con.close();
                resultado = 1;
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    public Campo consultarCampo(Campo campo) throws Exception {
        Campo nuevocampo = new Campo();
        try {
            Connection con = pool.dataSource.getConnection();
            Statement st = con.createStatement();
            try {
                String sql = "SELECT idcampo, nombre, porcentaje FROM public.campos"
                        + " WHERE idcampo = " + campo.getIdcampo() + " or nombre = '" + campo.getNombre() + "'";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevocampo = new Campo(rs.getInt(1), rs.getString(2), rs.getDouble(3));
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
        return nuevocampo;
    }

    public List<Campo> consultarCampos() throws Exception {
        List<Campo> listacampos = new ArrayList<>();
        try {
            Connection con = pool.dataSource.getConnection();
            Statement st = con.createStatement();
            try {
                String sql = "SELECT idcampo, nombre, porcentaje FROM public.campos";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listacampos.add(new Campo(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
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
        return listacampos;
    }

    public List<Campo> buscarCampos(String valor) throws Exception {
        List<Campo> listacampos = new ArrayList<>();
        try {
            Connection con = pool.dataSource.getConnection();
            Statement st = con.createStatement();
            try {
                String sql = "SELECT c.idcampo, c.nombre, c.porcentaje FROM public.campos as c, public.contratos as cr, public.campos_contratos as cc "
                        + "WHERE cc.idcontrato = cr.idcontrato AND c.idcampo = cc.idcampo (cr.nombre LIKE '%" + valor + "%' OR c.nombre LIKE '%" + valor + "%')";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listacampos.add(new Campo(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
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
        return listacampos;
    }

    public List<CampoCompleto> consultarCamposSegunMunicipio(Municipio municipio) throws Exception {
        List<CampoCompleto> listacampos = new ArrayList<>();
        try {
            Connection con = pool.dataSource.getConnection();
            Statement st = con.createStatement();
            try {
                municipio = municipioDao.consultarMunicipio(municipio);
                String sql = "SELECT c.idcampo, c.nombre, cc.idcontrato, c.porcentaje FROM public.campos as c, public.municipios_contratos as mc, public.campos_contratos as cc"
                        + " WHERE mc.idmunicipio = " + municipio.getIdmunicipio() + " and cc.idcontrato = mc.idcontrato and cc.idcampo = c.idcampo";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listacampos.add(new CampoCompleto(rs.getInt(1), rs.getString(2), contratosDao.consultarContrato(new Contrato(rs.getInt(3))), rs.getDouble(4)));
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
        return listacampos;
    }
}
