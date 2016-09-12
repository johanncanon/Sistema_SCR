/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Campos;
import com.planit.scr.modelos.Contratos;
import com.planit.scr.modelos.Municipios;
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
 
    public void registrarCampo(Campos campo) throws Exception {
        Statement st = ConexionSQL.conexion();
        ContratosDao contratosDao = new ContratosDao();
        campo.setIdcontrato(contratosDao.consultarContrato(campo.getIdcontrato()));
        try {
            try {
                String sql = "INSERT INTO public.campos("
                        + "           nombre, idcontrato)"
                        + " VALUES('" + campo.getNombre() + "', '" + campo.getIdcontrato().getIdcontrato() + "')";
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

    public Campos consultarCampo(Campos campo) throws Exception {
        Statement st = ConexionSQL.conexion();
        Campos nuevocampo = new Campos();
        ContratosDao contratosDao = new ContratosDao();
        try {
            try {
                String sql = "SELECT idcampo, nombre, idcontrato FROM public.campos"
                        + " WHERE idcampo = " + campo.getIdcampo() + " or nombre= '" + campo.getNombre() + "'";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevocampo = new Campos(rs.getInt(1), rs.getString(2), contratosDao.consultarContrato(new Contratos(rs.getInt(3))));
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

    public List<Campos> consultarCampos() throws Exception {
        Statement st = ConexionSQL.conexion();
        List<Campos> listacampos = new ArrayList<>();
        ContratosDao contratosDao = new ContratosDao();
        try {
            try {
                String sql = "SELECT idcampo, nombre,idcontrato FROM public.campos";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listacampos.add(new Campos(rs.getInt(1), rs.getString(2), contratosDao.consultarContrato(new Contratos(rs.getInt(3)))));
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

    public List<Campos> consultarCamposSegunMunicipio(Municipios municipio) throws Exception {
        Statement st = ConexionSQL.conexion();
        List<Campos> listacampos = new ArrayList<>();
        ContratosDao contratosDao = new ContratosDao();
        MunicipiosDao municipioDao = new MunicipiosDao();
        municipio = municipioDao.consultarMunicipio(municipio);
        try {
            try {
                String sql = "SELECT campos.idcampo, campos.nombre, campos.idcontrato FROM public.campos as campos, public.contratos as contratos, public.municipios as municipios"
                        + " WHERE campos.idcontrato = contratos.idcontrato and contratos.idmunicipio = municipios.idmunicipio and municipios.idmunicipio = " + municipio.getIdmunicipio() + "";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listacampos.add(new Campos(rs.getInt(1), rs.getString(2), contratosDao.consultarContrato(new Contratos(rs.getInt(3)))));
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
