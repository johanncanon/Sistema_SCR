/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.dao.CamposDao;
import com.planit.scr.dao.MunicipiosDao;
import com.planit.scr.dao.PblDao;
import com.planit.scr.dao.ProduccionDao;
import com.planit.scr.dao.RegaliasDao;
import com.planit.scr.modelos.Campo;
import com.planit.scr.modelos.Municipio;
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
    private Municipio municipio;
    private Campo campo;
    private Regalias regalia;

    private List<Campo> campos;
    private List<Regalias> regalias;
    private final Statement st = ConexionSQL.conexion();

    protected int posicion = 0;
    private int mes;
    private int anio;

    public RegaliasCT() {

        pbl = new Pbl();
        municipio = new Municipio();
        campo = new Campo();
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

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Campo getCampo() {
        return campo;
    }

    public void setCampo(Campo campo) {
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

    public List<Campo> getCampos() {
        return campos;
    }

    public void setCampos(List<Campo> campos) {
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
        MunicipiosDao municipiosDao = new MunicipiosDao();
        municipio = municipiosDao.consultarMunicipio(municipio);
        campos = camposDao.consultarCamposSegunMunicipio(municipio);
        for (int i = 0; i < campos.size(); i++) {
            Regalias reg = new Regalias();
            reg.setCampo(campos.get(i));
            reg.setMunicipio(municipio);
            reg.setDepartamento(municipio.getDepartamento());
            reg.setAnio(anio);
            reg.setMes(mes);
            reg.getProduccion().setAnio(anio);
            reg.getProduccion().setMes(mes);
            reg.getProduccion().setIdcampo(campos.get(i));
            regalias.add(reg);
        }
    }

    public String calcularRegalias() throws Exception {        
        RegaliasDao regaliasDao = new RegaliasDao();
        ProduccionDao produccionDao = new ProduccionDao();       
               
        for (int i = 0; i < regalias.size(); i++) {

            //Calculamos todos los valores relacionados a la produccion
            regalias.get(i).getProduccion().setProduccionhmes(regalias.get(i).getProduccion().getProduccionhdia() * 30.0);
            regalias.get(i).getProduccion().setProducciongmes(regalias.get(i).getProduccion().getProducciongdia() * 30.0);
            regalias.get(i).getProduccion().setProducciontotaldia(
                    regalias.get(i).getProduccion().getProduccionhdia() + produccionDao.convertirABarrilesEquivalentes(regalias.get(i).getProduccion().getProducciongdia()));
            regalias.get(i).getProduccion().setProducciontotalmes(regalias.get(i).getProduccion().getProducciontotaldia() * 30.0);
            produccionDao.registrarProduccion(regalias.get(i).getProduccion());
            regalias.get(i).setProduccion(produccionDao.consultarProduccionCampo(regalias.get(i).getProduccion()));
        
        }
                
        for (int i = 0; i < regalias.size(); i++) {            
                        
            //Obtenemos el porcentaje correspondiente de regalias segun el tipo de contrato
            double porcentaje = regalias.get(i).getCampo().getContrato().getPorcentaje();
            regalias.get(i).setPorcregalias((porcentaje / 100));

            //Obtenemos el porcentaje al que equivale la produccion del campo con respecto al total del municipio
            double total = totalProdMesMunicipio(regalias);
            regalias.get(i).setPorcmunicipio((regalias.get(i).getProduccion().getProducciontotalmes() * 100) / total);

            //Obtenemos pbl correspondiente al mes seleccionado
            PblDao pblDao = new PblDao();
            Pbl pbl = new Pbl();
            pbl = pblDao.consultarPblSegunContrato(
                    regalias.get(i).getCampo().getContrato(),
                    pblDao.obtenerTrimestre(regalias.get(i).getMes(),
                    regalias.get(i).getAnio()), regalias.get(i).getAnio());
            regalias.get(i).setPrecio(pbl.getPrc());

            //Calculamos 
            regalias.get(i).setRegalias(regalias.get(i).getProduccion().getProducciontotalmes() * regalias.get(i).getPrecio() * regalias.get(i).getPorcregalias() * 2899.29);

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
        
        municipio = regalias.get(0).getMunicipio();
        regalia = regalias.get(0);
        consultarRegalias();
        return "ConsultarRegalias";
    }

    public double totalProdDiaMunicipio(List<Regalias> rg) {
        double total = 0;
        for (int i = 0; i < rg.size(); i++) {
            total = total + (rg.get(i).getProduccion().getProducciontotaldia());
        }
        return total;
    }

    public double totalProdMesMunicipio(List<Regalias> rg) {
        double total = 0;
        for (int i = 0; i < rg.size(); i++) {
            total = total + (rg.get(i).getProduccion().getProducciontotalmes());
        }
        return total;
    }

    //Metodos   
    public void consultarRegalias() throws Exception {
        regalia.setMunicipio(municipio);
        RegaliasDao regaliasDao = new RegaliasDao();
        regalias = regaliasDao.consultarRegalias(regalia);
    }
}
