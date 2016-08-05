/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Municipios;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.PostConstruct;

/**
 *
 * @author Desarrollo_Planit
 */
public class CTMunicipios {

    private Municipios municipio;
    private final Statement st = ConexionSQL.conexion();

    public CTMunicipios() {
        municipio = new Municipios();
    }

    @PostConstruct
    public void init() {

    }

    public Municipios getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipios municipio) {
        this.municipio = municipio;
    }

    //Metodos 
    public void registrar() throws Exception {
        try {
            try {
                String sql = "INSERT INTO public.municipios (nombre, iddepartamento)"
                        + " VALUES ('" + municipio.getNombre() + "','" + municipio.getIddepartamento().getIddepartamento() + "')";
                st.execute(sql);
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        municipio = new Municipios();
    }

}
