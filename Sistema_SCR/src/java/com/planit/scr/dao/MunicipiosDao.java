/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Contrato;
import com.planit.scr.modelos.Departamento;
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
public class MunicipiosDao {

    public void registrarMunicipio(Municipio municipio) throws Exception {
        Statement st = ConexionSQL.conexion();
        DepartamentosDao departamentoDao = new DepartamentosDao();
        municipio.setDepartamento(departamentoDao.consultarDepartamento(municipio.getDepartamento()));
        try {
            try {
                String sql = "INSERT INTO public.municipios (nombre, iddepartamento)"
                        + " VALUES ('" + municipio.getNombre() + "'," + municipio.getDepartamento().getIddepartamento() + ")";
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

    public Municipio consultarMunicipio(Municipio m) throws Exception {
        Statement st = ConexionSQL.conexion();
        Municipio nuevomunicipio = new Municipio();
        DepartamentosDao departamentoDao = new DepartamentosDao();
        try {
            try {
                String sql = "SELECT idmunicipio, nombre, iddepartamento FROM public.municipios "
                        + "WHERE idmunicipio = " + m.getIdmunicipio() + " or nombre = '" + m.getNombre() + "'";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevomunicipio = new Municipio(rs.getInt(1), rs.getString(2), departamentoDao.consultarDepartamento(new Departamento(rs.getInt(3))));
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

    public List<Municipio> consultarMunicipios() throws Exception {
        Statement st = ConexionSQL.conexion();
        List<Municipio> listamunicipios = new ArrayList<>();
        DepartamentosDao departamentoDao = new DepartamentosDao();
        try {
            try {
                String sql = "SELECT idmunicipio, nombre, iddepartamento FROM public.municipios";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listamunicipios.add(new Municipio(rs.getInt(1), rs.getString(2), departamentoDao.consultarDepartamento(new Departamento(rs.getInt(3)))));
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

    public List<Municipio> consultarMunicipiosSegunContrato(Contrato contrato) throws Exception {
        Statement st = ConexionSQL.conexion();
        List<Municipio> lista = new ArrayList<>();
        ContratosDao contratosDao = new ContratosDao();
        contrato = contratosDao.consultarContrato(contrato);
        DepartamentosDao departamentoDao = new DepartamentosDao();
        try {
            try {
                String sql = "SELECT m.idmunicipio, m.nombre, m.iddepartamento FROM public.municipios as m, public.municipios_contratos as mc "
                        + "WHERE mc.idcontrato = '" + contrato.getIdcontrato() + "' and  mc.idmunicipio = m.idmunicipio";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    lista.add(new Municipio(rs.getInt(1), rs.getString(2), departamentoDao.consultarDepartamento(new Departamento(rs.getInt(3)))));
                }
            } catch (SQLException e) {
                System.out.println("" + e.getMessage() );
                throw e;                
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        return lista;
    }

    public List<Municipio> consultarMunicipiosSegunDepartamento(Departamento dep) throws Exception {
        Statement st = ConexionSQL.conexion();
        List<Municipio> lista = new ArrayList<>();
        DepartamentosDao departamentodao = new DepartamentosDao();
        dep = departamentodao.consultarDepartamento(dep);
        try {
            try {
                String sql = "SELECT idmunicipio, nombre, iddepartamento FROM public.municipios "
                        + "WHERE iddepartamento = " + dep.getIddepartamento() + " or nombre = '" + dep.getNombre() + "'";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    lista.add(new Municipio(rs.getInt(1), rs.getString(2), departamentodao.consultarDepartamento(new Departamento(rs.getInt(3)))));
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
