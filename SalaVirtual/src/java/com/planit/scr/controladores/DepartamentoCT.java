/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Departamentos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

/**
 *
 * @author Desarrollo_Planit
 */
public class DepartamentoCT {

    private Departamentos departamento;
    private List<Departamentos> departamentos;
    private final Statement st = ConexionSQL.conexion();

    public DepartamentoCT() {
        departamento = new Departamentos();
        departamentos = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        try {
            departamentos = consultarDepartamentos();
        } catch (Exception ex) {
            Logger.getLogger(DepartamentoCT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Departamentos getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamentos departamento) {
        this.departamento = departamento;
    }

    public List<Departamentos> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamentos> departamentos) {
        this.departamentos = departamentos;
    }

    //Metodos 
    public void registrar() throws Exception {
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
        } 
        departamento = new Departamentos();
        departamentos = consultarDepartamentos();
    }
     
        
    public List<Departamentos> consultarDepartamentos() throws Exception {
        List<Departamentos> listadepartamentos = new ArrayList<>();
        try {
            try {
                String sql = "SELECT iddepartamento, nombre FROM public.departamentos";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listadepartamentos.add(new Departamentos(rs.getInt(1), rs.getString(2)));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } 
        return listadepartamentos;
    }

    public Departamentos consultarDepartamento(Departamentos dp) throws Exception {
        Departamentos nuevodepartamento = new Departamentos();
        try {
            try {
                String sql = "SELECT iddepartamento, nombre FROM public.departamentos "
                        + "WHERE iddepartamento = '" + dp.getIddepartamento() + "' or nombre = '" + dp.getNombre() + "'";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevodepartamento = new Departamentos(rs.getInt(1), rs.getString(2));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } 
        return nuevodepartamento;
    }
}
