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

    public int registrarCampo(Campo campo) throws Exception {
        int resultado = 0;
        Statement st = ConexionSQL.conexion();
        try {
            try {
                String sql = "INSERT INTO public.campos("
                        + "           nombre, idcontrato)"
                        + " VALUES('" + campo.getNombre() + "', '" + campo.getContrato().getIdcontrato() + "')";
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

    public int modificarCampo(Campo campo) throws Exception {
        int resultado = 0;
        Statement st = ConexionSQL.conexion();
        try {
            try {
                String sql = "UPDATE public.campos "
                        + " SET nombre = '" + campo.getNombre() + "', idcontrato = '" + campo.getContrato().getIdcontrato() + "'"
                        + " WHERE idcampo = '" + campo.getIdcampo() + "'";
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

    public int eliminarCampo(Campo campo) throws Exception {
        int resultado = 0;
        Statement st = ConexionSQL.conexion();
        try {
            try {
                String sql = "DELETE FROM public.campos "
                        + "WHERE idcampo = '" + campo.getIdcampo() + "'";
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
    
    public Campo consultarCampo(Campo campo) throws Exception {
        Statement st = ConexionSQL.conexion();
        ContratosDao contratosDao = new ContratosDao();
        Campo nuevocampo = new Campo();
        try {
            try {
                String sql = "SELECT idcampo, nombre, idcontrato FROM public.campos"
                        + " WHERE idcampo = " + campo.getIdcampo() + " or nombre = '" + campo.getNombre() + "'";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevocampo = new Campo(rs.getInt(1), rs.getString(2), contratosDao.consultarContrato(new Contrato(rs.getInt(3))));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        return nuevocampo;
    }

    public List<Campo> consultarCampos() throws Exception {
        Statement st = ConexionSQL.conexion();
         ContratosDao contratosDao = new ContratosDao();
        List<Campo> listacampos = new ArrayList<>();
        try {
            try {
                String sql = "SELECT idcampo, nombre, idcontrato FROM public.campos";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listacampos.add(new Campo(rs.getInt(1), rs.getString(2), contratosDao.consultarContrato(new Contrato(rs.getInt(3)))));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        return listacampos;
    }

    public List<Campo> consultarCamposSegunMunicipio(Municipio municipio) throws Exception {
        Statement st = ConexionSQL.conexion();
        List<Campo> listacampos = new ArrayList<>();
        MunicipiosDao municipioDao = new MunicipiosDao();
        municipio = municipioDao.consultarMunicipio(municipio);
        ContratosDao contratosDao = new ContratosDao();
        try {
            try {
                String sql = "SELECT c.idcampo, c.nombre, c.idcontrato FROM public.campos as c, public.municipios_contratos as mc"
                        + " WHERE mc.idmunicipio = " + municipio.getIdmunicipio() + " and c.idcontrato = mc.idcontrato";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listacampos.add(new Campo(rs.getInt(1), rs.getString(2), contratosDao.consultarContrato(new Contrato(rs.getInt(3)))));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        return listacampos;
    }
}
