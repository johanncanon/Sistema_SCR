/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.Pool;
import com.planit.scr.modelos.Departamento;
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
public class DepartamentosDao {

    private final Pool pool = new Pool();
    
    public void registrarDepartamento(Departamento departamento) throws Exception {
        try {
            Connection con = pool.dataSource.getConnection();
            Statement st = con.createStatement();
            try {
                String sql = "INSERT INTO public.departamentos(nombre)"
                        + " VALUES('" + departamento.getNombre() + "')";
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

    public List<Departamento> consultarDepartamentos() throws Exception {

        List<Departamento> listadepartamentos = new ArrayList<>();
        try {
            Connection con = pool.dataSource.getConnection();
            Statement st = con.createStatement();
            try {
                String sql = "SELECT iddepartamento, nombre FROM public.departamentos";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listadepartamentos.add(new Departamento(rs.getInt(1), rs.getString(2)));
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
        return listadepartamentos;
    }

    public Departamento consultarDepartamento(Departamento dp) throws Exception {

        Departamento nuevodepartamento = new Departamento();
        try {
            Connection con = pool.dataSource.getConnection();
            Statement st = con.createStatement();
            try {
                String sql = "SELECT iddepartamento, nombre FROM public.departamentos "
                        + "WHERE iddepartamento = '" + dp.getIddepartamento() + "' or nombre = '" + dp.getNombre() + "'";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevodepartamento = new Departamento(rs.getInt(1), rs.getString(2));
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
        return nuevodepartamento;
    }
}
