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
        try {
            Statement st = ConexionSQL.conexion();
            try {
                String sql = "INSERT INTO public.departamentos(nombre)"
                        + " VALUES('" + departamento.getNombre() + "')";
                st.execute(sql);
                st.close();
                ConexionSQL.CerrarConexion();
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Departamento> consultarDepartamentos() throws Exception {

        List<Departamento> listadepartamentos = new ArrayList<>();
        try {
            Statement st = ConexionSQL.conexion();
            try {
                String sql = "SELECT iddepartamento, nombre FROM public.departamentos";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listadepartamentos.add(new Departamento(rs.getInt(1), rs.getString(2)));
                }
                rs.close();
                st.close();
                ConexionSQL.CerrarConexion();
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return listadepartamentos;
    }

    public Departamento consultarDepartamento(Departamento dp) throws Exception {

        Departamento nuevodepartamento = new Departamento();
        try {
            Statement st = ConexionSQL.conexion();
            try {
                String sql = "SELECT iddepartamento, nombre FROM public.departamentos "
                        + "WHERE iddepartamento = '" + dp.getIddepartamento() + "' or nombre = '" + dp.getNombre() + "'";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevodepartamento = new Departamento(rs.getInt(1), rs.getString(2));
                }
                rs.close();
                st.close();
                ConexionSQL.CerrarConexion();
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return nuevodepartamento;
    }
}
