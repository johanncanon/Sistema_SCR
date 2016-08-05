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
import javax.annotation.PostConstruct;

/**
 *
 * @author Desarrollo_Planit
 */
public class CTDepartamentos {

    private Departamentos departamento;
    private final Statement st = ConexionSQL.conexion();

    public CTDepartamentos() {
        departamento = new Departamentos();
    }

    @PostConstruct
    public void init() {

    }

    public Departamentos getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamentos departamento) {
        this.departamento = departamento;
    }

    //Metodos 
    public void registrar() throws Exception {
        try {
            try {
                String sql = "INSERT INTO public.departamentos (nombre)"
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

    public Departamentos consultarDepartamento(Departamentos dp) throws Exception {
        Departamentos nuevodepartamento = new Departamentos();
        try {
            try {
                String sql = "";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    
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
