/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Campos;
import com.planit.scr.modelos.Contratos;
import com.planit.scr.modelos.Municipios;
import com.planit.scr.modelos.Pbl;
import com.planit.scr.modelos.Produccion;
import com.planit.scr.modelos.Regalias;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class RegaliasCT {

    private Produccion produccion;
    private int vista;
    private Pbl pbl;
    private Municipios municipio;
    private Campos campo;
    private List<Campos> campos;
    private List<Contratos> contratos;
    private List<Regalias> regalias;

    private final Statement st = ConexionSQL.conexion();

    protected int posicion = 0;

    public RegaliasCT() {
        produccion = new Produccion();
        pbl = new Pbl();
        vista = 0;

        municipio = new Municipios();
        campo = new Campos();

        campos = new ArrayList<>();
        contratos = new ArrayList<>();
        regalias = new ArrayList<>();
    }

    public Produccion getProduccion() {
        return produccion;
    }

    public void setProduccion(Produccion produccion) {
        this.produccion = produccion;
    }

    public Pbl getPbl() {
        return pbl;
    }

    public void setPbl(Pbl pbl) {
        this.pbl = pbl;
    }

    public int getVista() {
        return vista;
    }

    public void setVista(int vista) {
        this.vista = vista;
    }

    public Municipios getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipios municipio) {
        this.municipio = municipio;
    }

    public Campos getCampo() {
        return campo;
    }

    public void setCampo(Campos campo) {
        this.campo = campo;
    }

    public List<Campos> getCampos() {
        return campos;
    }

    public void setCampos(List<Campos> campos) {
        this.campos = campos;
    }

    public List<Regalias> getRegalias() {
        return regalias;
    }

    public void setRegalias(List<Regalias> regalias) {
        this.regalias = regalias;
    }
    
      

    //Metodos
    public void calcularRegalias() throws Exception {
        MunicipioCT mct = new MunicipioCT();
        ContratoCT coct = new ContratoCT();
        CampoCT cct = new CampoCT();

        if (vista == 0) {
            vista = 1;
            municipio = mct.consultarMunicipio(municipio);
            contratos = coct.consultarContratosSegunMunicipio(municipio);
            campos = cct.consultarCampos(municipio);
            produccion.setIdcampo(campos.get(0));
        } else if (vista == 1) {
            ProduccionCT pct = new ProduccionCT();
            produccion.setFecha(new Date());
            pct.registrar(produccion);

            posicion++;
            if (posicion <= campos.size()) {
                if (posicion < campos.size()) {
                    produccion.setIdcampo(campos.get(posicion));

                } else if (posicion == campos.size()) {
                    vista = 2;
                    posicion = 0;
                    pbl.setIdcampo(campos.get(0));
                }
            }
        } else if (vista == 2) {
            PblCT pct = new PblCT();
            pbl = pct.calcularPBL(pbl);
            pct.registrarPbl(pbl);

            posicion++;
            if (posicion <= campos.size()) {
                if (posicion < campos.size()) {
                    pbl = new Pbl();
                    pbl.setIdcampo(campos.get(posicion));
                } else if (posicion == campos.size()) {
                    vista = 3;
                    posicion = 0;
                    for (int i = 0; i < campos.size(); i++) {
//                        regalias.add(new Regalias(campos.get(i), pbl, vista, i))
                    }
                }
            }
        } else if(vista == 3){
            
        }
    }

}
