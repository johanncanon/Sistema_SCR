/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Departamento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VaioDevelopment
 */
public class DepartamentosDao {
   
    public void registrarDepartamento(Departamento departamento) throws Exception {
        Statement st = ConexionSQL.conexion();
        try {
            try {
                String sql = "INSERT INTO public.departamentos(nombre)"
                        + " VALUES('" + departamento.getNombre() + "')";
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

    public List<Departamento> consultarDepartamentos() throws Exception {
        Statement st = ConexionSQL.conexion();
        List<Departamento> listadepartamentos = new ArrayList<>();
        try {
            try {
                String sql = "SELECT iddepartamento, nombre FROM public.departamentos";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listadepartamentos.add(new Departamento(rs.getInt(1), rs.getString(2)));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        return listadepartamentos;
    }

    public Departamento consultarDepartamento(Departamento dp) throws Exception {
        Statement st = ConexionSQL.conexion();
        Departamento nuevodepartamento = new Departamento();
        try {
            try {
                String sql = "SELECT iddepartamento, nombre FROM public.departamentos "
                        + "WHERE iddepartamento = '" + dp.getIddepartamento() + "' or nombre = '" + dp.getNombre() + "'";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevodepartamento = new Departamento(rs.getInt(1), rs.getString(2));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        return nuevodepartamento;
    }
}
