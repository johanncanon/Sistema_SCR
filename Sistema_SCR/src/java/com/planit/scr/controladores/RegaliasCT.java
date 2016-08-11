/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Campos;
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

    protected List<Produccion> producciones;
    protected List<Pbl> pbls;
    protected List<Campos> campos;   
    private List<Regalias> regalias;

    private final Statement st = ConexionSQL.conexion();

    protected int posicion = 0;
    private int mes = 0;

    public RegaliasCT() {
        produccion = new Produccion();
        pbl = new Pbl();
        vista = 0;

        municipio = new Municipios();
        campo = new Campos();

        campos = new ArrayList<>();        
        regalias = new ArrayList<>();
        pbls = new ArrayList<>();
        producciones = new ArrayList<>();
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

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public List<Regalias> getRegalias() {
        return regalias;
    }

    public void setRegalias(List<Regalias> regalias) {
        this.regalias = regalias;
    }
    
    

    //Metodos
    public String calcularRegalias() throws Exception {
        MunicipioCT mct = new MunicipioCT();       
        CampoCT cct = new CampoCT();
        String ruta = "";
        if (vista == 0) {
            vista = 1;
            municipio = mct.consultarMunicipio(municipio);            
            campos = cct.consultarCampos(municipio);
            produccion.setIdcampo(campos.get(0));
        } else if (vista == 1) {
            ProduccionCT pct = new ProduccionCT();
            produccion.setFecha(new Date());
            pct.registrar(produccion);
            producciones.add(produccion);
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
            if (mes <= 3) {
                pbl.setTrimestre(1);
            } else if (mes > 3 && mes <= 6) {
                pbl.setTrimestre(2);
            } else if (mes > 6 && mes <= 9) {
                pbl.setTrimestre(3);
            } else if (mes > 9 && mes <= 12) {
                pbl.setTrimestre(4);
            }
            pct.registrarPbl(pbl);
            pbls.add(pbl);

            pbl = new Pbl();
            posicion++;
            if (posicion <= campos.size()) {
                if (posicion < campos.size()) {                   
                    pbl.setIdcampo(campos.get(posicion));
                } else if (posicion == campos.size()) {
                    ruta = "Resultado";
                    posicion = 0;
                    for (int i = 0; i < campos.size(); i++) {
                        double regalia = (((double)(producciones.get(i).getProduccion() * 30) * pbls.get(i).getPrc()) * (double)(20 / 100) * 1750);
                        regalias.add(new Regalias(campos.get(i), producciones.get(i), pbls.get(i), 20, (int) regalia));
                    }
                }
            }
        }
        
        return ruta;
    }

}
