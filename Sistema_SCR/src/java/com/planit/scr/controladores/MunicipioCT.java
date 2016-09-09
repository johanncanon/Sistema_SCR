/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.dao.DepartamentosDao;
import com.planit.scr.dao.MunicipiosDao;
import com.planit.scr.modelos.Departamentos;
import com.planit.scr.modelos.Municipios;
import com.planit.scr.modelos.Municipios_;
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
    private List<Municipios> municipiosFiltrados;
   
    public MunicipioCT() {
        municipio = new Municipios();
        municipios = new ArrayList<>();
        municipiosFiltrados = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        MunicipiosDao municipioDao = new MunicipiosDao();
        try {
            municipios = municipioDao.consultarMunicipios();
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

    public List<Municipios> getMunicipiosFiltrados() {
        return municipiosFiltrados;
    }

    public void setMunicipiosFiltrados(List<Municipios> municipiosFiltrados) {
        this.municipiosFiltrados = municipiosFiltrados;
    }

    //Metodos 
    public void registrar() throws Exception {
        MunicipiosDao municipioDao = new MunicipiosDao();
        municipioDao.registrarMunicipio(municipio);
        municipio = new Municipios();
        municipios = municipioDao.consultarMunicipios();
    }

    public void cambioDepartamentos() throws Exception {
        MunicipiosDao municipioDao = new MunicipiosDao();
        if (municipio.getIddepartamento() != null && !municipio.getIddepartamento().getNombre().equals("")) {
            municipiosFiltrados = municipioDao.consultarMunicipiosSegunDepartamento(municipio.getIddepartamento());
        } else {
            municipiosFiltrados = new ArrayList<>();
        }
    }

}
