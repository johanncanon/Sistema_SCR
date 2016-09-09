/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.controladores.DepartamentoCT;
import com.planit.scr.modelos.Departamentos;
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
public class MunicipiosDao {

    public void registrarMunicipio(Municipios municipio) throws Exception {
        Statement st = ConexionSQL.conexion();
        DepartamentosDao departamentoDao = new DepartamentosDao();
        municipio.setIddepartamento(departamentoDao.consultarDepartamento(municipio.getIddepartamento()));
        try {
            try {
                String sql = "INSERT INTO public.municipios (nombre, iddepartamento)"
                        + " VALUES ('" + municipio.getNombre() + "'," + municipio.getIddepartamento().getIddepartamento() + ")";
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

    public Municipios consultarMunicipio(Municipios m) throws Exception {
        Statement st = ConexionSQL.conexion();
        Municipios nuevomunicipio = new Municipios();
        DepartamentosDao departamentoDao = new DepartamentosDao();
        try {
            try {
                String sql = "SELECT idmunicipio, nombre, iddepartamento FROM public.municipios "
                        + "WHERE idmunicipio = " + m.getIdmunicipio() + " or nombre = '" + m.getNombre() + "'";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevomunicipio = new Municipios(rs.getInt(1), rs.getString(2), departamentoDao.consultarDepartamento(new Departamentos(rs.getInt(3))));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        return nuevomunicipio;
    }

    public List<Municipios> consultarMunicipios() throws Exception {
        Statement st = ConexionSQL.conexion();
        List<Municipios> listamunicipios = new ArrayList<>();
        DepartamentosDao departamentoDao = new DepartamentosDao();
        try {
            try {
                String sql = "SELECT idmunicipio, nombre, iddepartamento FROM public.municipios";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listamunicipios.add(new Municipios(rs.getInt(1), rs.getString(2), departamentoDao.consultarDepartamento(new Departamentos(rs.getInt(3)))));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        return listamunicipios;
    }

    public List<Municipios> consultarMunicipiosSegunDepartamento(Departamentos dep) throws Exception {
        Statement st = ConexionSQL.conexion();
        List<Municipios> lista = new ArrayList<>();
        DepartamentosDao departamentodao = new DepartamentosDao();
        dep = departamentodao.consultarDepartamento(dep);
        try {
            try {
                String sql = "SELECT idmunicipio, nombre, iddepartamento FROM public.municipios "
                        + "WHERE iddepartamento = " + dep.getIddepartamento() + "";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    lista.add(new Municipios(rs.getInt(1), rs.getString(2), departamentodao.consultarDepartamento(new Departamentos(rs.getInt(3)))));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        return lista;
    }
}
