/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.dao.CamposDao;
import com.planit.scr.dao.ContratosDao;
import com.planit.scr.dao.PblDao;
import com.planit.scr.dao.RegaliasDao;
import com.planit.scr.modelos.Campos;
import com.planit.scr.modelos.Municipios;
import com.planit.scr.modelos.Pbl;
import com.planit.scr.modelos.Regalias;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class RegaliasCT {

    private Pbl pbl;
    private Municipios municipio;
    private Campos campo;
    private Regalias regalia;

    private List<Campos> campos;
    private List<Regalias> regalias;
    private final Statement st = ConexionSQL.conexion();

    protected int posicion = 0;
    private int mes;
    private int anio;

    public RegaliasCT() {

        pbl = new Pbl();
        municipio = new Municipios();
        campo = new Campos();
        campos = new ArrayList<>();
        regalias = new ArrayList<>();
        regalia = new Regalias();
    }

    public Pbl getPbl() {
        return pbl;
    }

    public void setPbl(Pbl pbl) {
        this.pbl = pbl;
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

    public List<Campos> getCampos() {
        return campos;
    }

    public void setCampos(List<Campos> campos) {
        this.campos = campos;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Regalias getRegalia() {
        return regalia;
    }

    public void setRegalia(Regalias regalia) {
        this.regalia = regalia;
    }
    
    

    //metodos 
    public void consultarCamposSegunMunicipio() throws Exception {
        CamposDao camposDao = new CamposDao();
        campos = camposDao.consultarCamposSegunMunicipio(municipio);
        for (int i = 0; i < campos.size(); i++) {
            Regalias regalia = new Regalias();
            regalia.setIdcampo(campos.get(i));
            regalia.setIdmunicipio(campos.get(i).getIdcontrato().getIdmunicipio());
            regalia.setIddepartamento(campos.get(i).getIdcontrato().getIdmunicipio().getIddepartamento());
            regalia.setAnio(anio);
            regalia.setMes(mes);
            regalias.add(regalia);
        }
    }

    public void calcularRegalias() throws Exception {
        ContratosDao contratosDao = new ContratosDao();
        RegaliasDao regaliasDao = new RegaliasDao();
        double barrilesequivalentes;
        for (int i = 0; i < regalias.size(); i++) {

            //Validamos si el hidrocarburo el gas o petroleo
            if (regalias.get(i).getTipohidrocarburo().equals("G")) { //Convertimos a barriles en caso de ser gas
                barrilesequivalentes = (regalias.get(i).getProddia() / (double) 5700);
                regalias.get(i).setProddia(barrilesequivalentes);
                regalias.get(i).setProdmes(barrilesequivalentes * (double) 30);
            } else {
                regalias.get(i).setProdmes(regalias.get(i).getProddia() * (double) 30);
            }

            //Obtenemos el porcentaje correspondiente de regalias segun el tipo de contrato
            double porcentaje = contratosDao.consultarContrato(regalias.get(i).getIdcampo().getIdcontrato()).getPorcentaje();
            regalias.get(i).setPorcregalias((porcentaje / 100));

            //Obtenemos el porcentaje al que equivale la produccion del campo con respecto al total del municipio
            double total = totalProdMesMunicipio(regalias);
            regalias.get(i).setPorcmunicipio((regalias.get(i).getProdmes() * 100) / total);

            //Obtenemos pbl correspondiente al mes seleccionado
            PblDao pblDao = new PblDao();
            Pbl pbl = new Pbl();
            pbl = pblDao.consultarPblSegunContrato(regalias.get(i).getIdcampo().getIdcontrato(), pblDao.obtenerTrimestre(regalias.get(i).getMes()), regalias.get(i).getAnio());
            regalias.get(i).setPrecio(pbl.getPrc());

            //Calculamos 
            regalias.get(i).setRegalias(regalias.get(i).getProdmes() * regalias.get(i).getPrecio() * regalias.get(i).getPorcregalias() * 2899.29);

            //Hacemos division de regalias
            total = totalProdDiaMunicipio(regalias);
            if (total < 10000) {
                Double valor = (regalias.get(i).getRegalias() * 52) / 100;
                regalias.get(i).setDepproductor(valor.intValue());
                valor = (regalias.get(i).getRegalias() * 32) / 100;
                regalias.get(i).setMunproductor(valor.intValue());
                valor = (regalias.get(i).getRegalias() * 8) / 100;
                regalias.get(i).setPuertos(valor.intValue());

            } else if (total > 10000 && total < 20000) {

                Double valor = ((regalias.get(i).getRegalias() * 47.5) / 100);
                regalias.get(i).setDepproductor(valor.intValue());
                valor = (regalias.get(i).getRegalias() * 25) / 100;
                regalias.get(i).setMunproductor(valor.intValue());
                valor = (regalias.get(i).getRegalias() * 8) / 100;
                regalias.get(i).setPuertos(valor.intValue());

            } else if (total > 20000 && total < 50000) {

                Double valor = ((20000 * 47.5) / 100);
                regalias.get(i).setDepproductor(valor.intValue());
                valor = (double) ((20000 * 25) / 100);
                regalias.get(i).setMunproductor(valor.intValue());
                regalias.get(i).setPuertos((20000 * 8) / 100);

                valor = (((regalias.get(i).getRegalias() - 20000) * 47.5) / 100);
                regalias.get(i).setDepproductor(regalias.get(i).getDepproductor() + valor.intValue());
                valor = (((regalias.get(i).getRegalias() - 20000) * 12.5) / 100);
                regalias.get(i).setMunproductor(regalias.get(i).getMunproductor() + valor.intValue());
                valor = (((regalias.get(i).getRegalias() - 20000) * 8) / 100);
                regalias.get(i).setPuertos(regalias.get(i).getPuertos() + valor.intValue());

            }
            regalias.get(i).setDepnoproductor(0); //declaramos valor a departamentos no productores
            regaliasDao.registrarRegalias(regalias.get(i));
        }
        regalias = new ArrayList<>();
    }

    public double totalProdDiaMunicipio(List<Regalias> rg) {
        double total = 0;
        for (int i = 0; i < rg.size(); i++) {
            total = total + (rg.get(i).getProddia());
        }
        return total;
    }

    public double totalProdMesMunicipio(List<Regalias> rg) {
        double total = 0;
        for (int i = 0; i < rg.size(); i++) {
            total = total + (rg.get(i).getProddia() * (double) 30);
        }
        return total;
    }
    
        //Metodos   
    public void consultarRegalias() throws Exception {
        regalia.setIdmunicipio(municipio);
        RegaliasDao regaliasDao = new RegaliasDao();
        regalias = regaliasDao.consultarRegalias(regalia);       
    }

//    //Metodos
//    public String calcularRegalias() throws Exception {
//        MunicipioCT mct = new MunicipioCT();
//        CampoCT cct = new CampoCT();
//        String ruta = "";
//
//            municipio = mct.consultarMunicipio(municipio);
//            campos = cct.consultarCampos(municipio);
//            produccion.setIdcampo(campos.get(0));
//            ProduccionCT pct = new ProduccionCT();
//            produccion.setFecha(new Date());
//            pct.registrar(produccion);
//            producciones.add(produccion);
//            posicion++;
//            if (posicion <= campos.size()) {
//                if (posicion < campos.size()) {
//                    produccion = new Produccion();
//                    produccion.setIdcampo(campos.get(posicion));
//
//                } else if (posicion == campos.size()) {
//                     posicion = 0;
//                    pbl.setIdcampo(campos.get(0));
//                }
//            }
//
//            PblCT pclt = new PblCT();
//            pbl = pclt.calcularPBL(pbl);
//            if (mes <= 3) {
//                pbl.setTrimestre(1);
//            } else if (mes > 3 && mes <= 6) {
//                pbl.setTrimestre(2);
//            } else if (mes > 6 && mes <= 9) {
//                pbl.setTrimestre(3);
//            } else if (mes > 9 && mes <= 12) {
//                pbl.setTrimestre(4);
//            }
//            pclt.registrarPbl(pbl);
//            pbls.add(pbl);
//
//            pbl = new Pbl();
//            posicion++;
//            if (posicion <= campos.size()) {
//                if (posicion < campos.size()) {
//                    pbl.setIdcampo(campos.get(posicion));
//                } else if (posicion == campos.size()) {
//                    ruta = "Resultado";
//                    posicion = 0;
//                    for (int i = 0; i < campos.size(); i++) {
//
//                        double prodMes = producciones.get(i).getProduccion() * 30;
//                        double porcentaje = (double) 20 / 100;
//                        double precioLiquidacion = pbls.get(i).getPrc();
//                        double regalia = prodMes * precioLiquidacion * porcentaje * 3200;
//
//                        regalias.add(new Regalias(campos.get(i), producciones.get(i), pbls.get(i), 20, regalia));
//                    }
//                }
//            }
//        
//
//        return ruta;
//    }
}
