/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.Pool;
import com.planit.scr.modelos.Contrato;
import com.planit.scr.modelos.Departamento;
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
public class MunicipiosDao {

    private final Pool pool = new Pool(); 

    public void registrarMunicipio(Municipio municipio) throws Exception {        
        try {
            Connection con = pool.dataSource.getConnection();
            Statement st = con.createStatement();
            try {
                String sql = "INSERT INTO public.municipios (nombre, iddepartamento)"
                        + " VALUES ('" + municipio.getNombre() + "'," + municipio.getDepartamento().getIddepartamento() + ")";
                st.execute(sql);
                st.close();
                con.close();
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Municipio consultarMunicipio(Municipio m) throws Exception {
        Municipio nuevomunicipio = new Municipio();
        try {
            Connection con = pool.dataSource.getConnection();
            Statement st = con.createStatement();
            try {
                String sql = "SELECT m.idmunicipio, m.nombre, m.iddepartamento, d.nombre FROM public.municipios as m, public.departamentos as d "
                        + "WHERE m.idmunicipio = " + m.getIdmunicipio() + " or m.nombre = '" + m.getNombre() + "' and"
                        + "m.iddepartamento = d.iddepartamento";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevomunicipio = new Municipio(rs.getInt(1), rs.getString(2), new Departamento(rs.getInt(3), rs.getString(4)));
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
        return nuevomunicipio;
    }

    public List<Municipio> consultarMunicipios() throws Exception {

        List<Municipio> listamunicipios = new ArrayList<>();
        DepartamentosDao departamentoDao = new DepartamentosDao();
        try {
            Connection con = pool.dataSource.getConnection();
            Statement st = con.createStatement();
            try {
                String sql = "SELECT m.idmunicipio, m.nombre, m.iddepartamento FROM public.municipios as m, public.departamentos as d where d.iddepartamento = m.iddepartamento order by nombre asc";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listamunicipios.add(new Municipio(rs.getInt(1), rs.getString(2), new Departamento(rs.getInt(3), rs.getString(4))));
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
        return listamunicipios;
    }

    public List<Municipio> consultarMunicipiosSegunContrato(Contrato contrato) throws Exception {

        List<Municipio> lista = new ArrayList<>();
        ContratosDao contratosDao = new ContratosDao();
        contrato = contratosDao.consultarContrato(contrato);
        try {
            Connection con = pool.dataSource.getConnection();
            Statement st = con.createStatement();
            try {
                String sql = "SELECT m.idmunicipio, m.nombre, m.iddepartamento, d.nombre FROM public.municipios as m, public.municipios_contratos as mc, public.departamentos as d "
                        + "WHERE mc.idcontrato = '" + contrato.getIdcontrato() + "' and  mc.idmunicipio = m.idmunicipio and m.iddepartamento = d.iddepartamento";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    lista.add(new Municipio(rs.getInt(1), rs.getString(2), new Departamento(rs.getInt(3), rs.getString(4))));
                }
                rs.close();
                st.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("" + e.getMessage());
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    public List<Municipio> consultarMunicipiosSegunDepartamento(Departamento dep) throws Exception {

        List<Municipio> lista = new ArrayList<>();
        DepartamentosDao departamentodao = new DepartamentosDao();
        dep = departamentodao.consultarDepartamento(dep);
        try {
            Connection con = pool.dataSource.getConnection();
            Statement st = con.createStatement();            
            try {
                String sql = "SELECT m.idmunicipio, m.nombre, m.iddepartamento, d.departamento FROM public.municipios as m, public.departamentos as d "
                        + "WHERE iddepartamento = " + dep.getIddepartamento() + " or nombre = '" + dep.getNombre() + "' and d.iddepartamento = m.iddepartamento";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    lista.add(new Municipio(rs.getInt(1), rs.getString(2),  new Departamento(rs.getInt(3), rs.getString(4))));
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
        return lista;
    }
}
