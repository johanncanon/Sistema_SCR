/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Produccion;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.PostConstruct;

/**
 *
 * @author Desarrollo_Planit
 */
public class ProduccionCT {

    private Produccion produccion;
    private final Statement st = ConexionSQL.conexion();

    public ProduccionCT() {
        produccion = new Produccion();
    }

    @PostConstruct
    public void init() {

    }

    public Produccion getProduccion() {
        return produccion;
    }

    public void setProduccion(Produccion produccion) {
        this.produccion = produccion;
    }

    //Metodos
    public void registrar(Produccion p) throws Exception {
        try {
            try {
                String sql = "INSERT INTO public.produccion (idcampo, fecha, produccion)"
                        + " VALUES('" + p.getIdcampo().getIdcampo() + "', '" + p.getFecha() + "', '" + p.getProduccion() + "')";
                st.execute(sql);
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
