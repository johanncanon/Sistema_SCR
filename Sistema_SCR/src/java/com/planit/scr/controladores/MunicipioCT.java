/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Departamentos;
import com.planit.scr.modelos.Municipios;
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
public class MunicipioCT {

    private Municipios municipio;
    private List<Municipios> municipios;
    private final Statement st = ConexionSQL.conexion();

    public MunicipioCT() {
        municipio = new Municipios();
        municipios = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        try {
            municipios = consultarMunicipios();
        } catch (Exception ex) {
            Logger.getLogger(MunicipioCT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Municipios getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipios municipio) {
        this.municipio = municipio;
    }

    public List<Municipios> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipios> municipios) {
        this.municipios = municipios;
    }

    //Metodos 
    public void registrar() throws Exception {
        DepartamentoCT dct = new DepartamentoCT();
        municipio.setIddepartamento(dct.consultarDepartamento(municipio.getIddepartamento()));
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
        }
        municipio = new Municipios();
        municipios = consultarMunicipios();
    }

    public Municipios consultarMunicipio(Municipios m) throws Exception {
        Municipios nuevomunicipio = new Municipios();
        DepartamentoCT dct = new DepartamentoCT();
        try {
            try {
                String sql = "SELECT idmunicipio, nombre, iddepartamento FROM public.municipios "
                        + "WHERE idmunicipio = " + m.getIdmunicipio() + " or nombre = '" + m.getNombre() + "'";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevomunicipio = new Municipios(rs.getInt(1), rs.getString(2), dct.consultarDepartamento(new Departamentos(rs.getInt(3))));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return nuevomunicipio;
    }

    public List<Municipios> consultarMunicipios() throws Exception {
        List<Municipios> listamunicipios = new ArrayList<>();
        DepartamentoCT dct = new DepartamentoCT();
        try {
            try {
                String sql = "SELECT idmunicipio, nombre, iddepartamento FROM public.municipios";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listamunicipios.add(new Municipios(rs.getInt(1), rs.getString(2), dct.consultarDepartamento(new Departamentos(rs.getInt(3)))));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return listamunicipios;
    }
}
