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
import com.planit.scr.dao.TrmDao;
import com.planit.scr.modelos.Campo;
import com.planit.scr.modelos.Municipio;
import com.planit.scr.modelos.Pbl;
import com.planit.scr.modelos.Regalias;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

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
        regalias = new ArrayList<>();
        ProduccionDao produccionDao = new ProduccionDao();
        if (!produccionDao.verificarRegistroProduccionMunicipio(municipio, anio, mes)) {
            MunicipiosDao municipiosDao = new MunicipiosDao();
            municipio = municipiosDao.consultarMunicipio(municipio);
            campos = camposDao.consultarCamposSegunMunicipio(municipio);
            if (!campos.isEmpty()) {
                for (int i = 0; i < campos.size(); i++) {
                    Regalias reg = new Regalias();
                    reg.setCampo(campos.get(i));
                    reg.setMunicipio(municipio);
                    reg.setDepartamento(municipio.getDepartamento());
                    reg.setAnio(anio);
                    reg.setMes(mes);
                    reg.getProduccion().setAnio(anio);
                    reg.getProduccion().setMes(mes);
                    reg.getProduccion().setCampo(campos.get(i));
                    reg.getProduccion().setMunicipio(municipio);
                    regalias.add(reg);
                }
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "No existen campos registrados para el municipio");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ya ha sido calculada una distribucion de regalias para este municipio");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String calcularRegalias() throws Exception {
        String ruta = "";
        FacesMessage message = new FacesMessage();

        RegaliasDao regaliasDao = new RegaliasDao();
        ProduccionDao produccionDao = new ProduccionDao();

        Calendar cal = new GregorianCalendar(anio, mes - 1, 1);
        int diasMes = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < regalias.size(); i++) {
            //Calculamos todos los valores relacionados a la produccion
            regalias.get(i).getProduccion().setProduccionhmes(regalias.get(i).getProduccion().getProduccionhdia() * diasMes);
            regalias.get(i).getProduccion().setProducciongmes(regalias.get(i).getProduccion().getProducciongdia() * diasMes);
            regalias.get(i).getProduccion().setProducciontotaldia(regalias.get(i).getProduccion().getProduccionhdia() + produccionDao.convertirABarrilesEquivalentes(regalias.get(i).getProduccion().getProducciongdia()));
            regalias.get(i).getProduccion().setProducciontotalmes(regalias.get(i).getProduccion().getProducciontotaldia() * diasMes);

            produccionDao.registrarProduccion(regalias.get(i).getProduccion());
        }

        for (int i = 0; i < regalias.size(); i++) {
            regalias.get(i).setProduccion(produccionDao.consultarProduccionCampo(regalias.get(i).getProduccion()));

            //Obtenemos el porcentaje correspondiente de regalias segun el tipo de contrato
            double porcentaje = regalias.get(i).getCampo().getContrato().getPorcentaje();
            regalias.get(i).setPorcregalias((porcentaje / 100));

            //Obtenemos el porcentaje al que equivale la produccion del campo con respecto al total del municipio
            double total = totalProdMesMunicipio(regalias);
            regalias.get(i).setPorcmunicipio((regalias.get(i).getProduccion().getProducciontotalmes() * 100) / total);

            //Obtenemos pbl correspondiente al mes seleccionado
            PblDao pblDao = new PblDao();
            Pbl pbl = new Pbl();
            pbl = pblDao.consultarPblSegunContrato(regalias.get(i).getCampo().getContrato(),
                    pblDao.obtenerTrimestre(regalias.get(i).getMes(), regalias.get(i).getAnio()), regalias.get(i).getAnio());

            if (pbl.getIdpbl() != 0) {
                regalias.get(i).setPrecio(pbl.getPrc());
                //Calculamos 
                double bpdm = promedioDiarioMensual(regalias);
                TrmDao trmDao = new TrmDao();
                double trm = trmDao.consultarPromedioMensualTrm(mes, anio);
                if (trm != 0) {
                    regalias.get(i).setRegalias(regalias.get(i).getProduccion().getProducciontotalmes() * regalias.get(i).getPrecio() * regalias.get(i).getPorcregalias() * trm);

                    //Hacemos division de regalias
                    if (bpdm <= 10000) {
                        Double valor = (regalias.get(i).getRegalias() * 52) / 100;
                        regalias.get(i).setDepproductor(valor);
                        valor = (regalias.get(i).getRegalias() * 32) / 100;
                        regalias.get(i).setMunproductor(valor);
                        valor = (regalias.get(i).getRegalias() * 8) / 100;
                        regalias.get(i).setPuertos(valor);
                        regalias.get(i).setFondonacional(valor);

                    } else if (bpdm > 10000 && bpdm <= 20000) {

                        double valorA = (((10000) * 52) / 100);
                        double valorB = (((regalias.get(i).getRegalias() - 10000) * 47.5) / 100);
                        regalias.get(i).setDepproductor(valorA + valorB);
                        valorA = ((10000 * 32) / 100);
                        valorB = (((regalias.get(i).getRegalias() - 10000) * 25) / 100);
                        regalias.get(i).setMunproductor(valorA + valorB);
                        valorA = ((10000 * 8) / 100);
                        valorB = (((regalias.get(i).getRegalias() - 10000) * 8) / 100);
                        regalias.get(i).setPuertos(valorA + valorB);
                        valorB = (((regalias.get(i).getRegalias() - 10000) * 19.5) / 100);
                        regalias.get(i).setFondonacional(valorA + valorB);

                    } else if (bpdm > 20000 && bpdm <= 50000) {

                        double valorA = (((10000) * 52) / 100);
                        double valorB = (((20000) * 47.5) / 100);
                        double valorC = (((regalias.get(i).getRegalias() - 20000) * 47.5) / 100);
                        regalias.get(i).setDepproductor(valorA + valorB + valorC);
                        valorA = ((10000 * 32) / 100);
                        valorB = (((20000) * 25) / 100);
                        valorC = (((regalias.get(i).getRegalias() - 20000) * 12.5) / 100);
                        regalias.get(i).setMunproductor(valorA + valorB);
                        valorA = ((10000 * 8) / 100);
                        valorB = (((20000) * 8) / 100);
                        valorC = (((regalias.get(i).getRegalias() - 20000) * 8) / 100);
                        regalias.get(i).setPuertos(valorA + valorB);
                        valorB = (((20000) * 19.5) / 100);
                        valorC = (((regalias.get(i).getRegalias() - 20000) * 32) / 100);
                        regalias.get(i).setFondonacional(valorA + valorB + valorC);
                    }
                    regalias.get(i).setDepnoproductor(0); //declaramos valor a departamentos no productores
                    int r = regaliasDao.registrarRegalias(regalias.get(i));
                    if (r == 0) {
                        message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ocurrio un error al registrar la distribucion de regalias");
                        ruta = "";
                    } else if (r == 1) {
                        ruta = "ConsultarRegalias";
                    }
                } else {
                    message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ocurrio un error al consultar el trm del mes respectivo");
                }
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "No existen valores registrados de pbl para el mes y año seleccionado");
            }
        }
        consultarRegalias();
        FacesContext.getCurrentInstance().addMessage(null, message);
        return ruta;
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

    public double promedioDiarioMensual(List<Regalias> rg) {
        double bpdm = 0;
        double sumatoria = 0;
        for (int i = 0; i < rg.size(); i++) {
            sumatoria = sumatoria + rg.get(i).getProduccion().getProducciontotalmes();
        }
        Calendar cal = new GregorianCalendar(anio, mes - 1, 1);
        int diasMes = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        bpdm = sumatoria / diasMes;
        return bpdm;
    }

    //Metodos   
    public void consultarRegalias() throws Exception {
        regalia.setMunicipio(municipio);
        RegaliasDao regaliasDao = new RegaliasDao();
        regalias = regaliasDao.consultarRegalias(regalia);
    }

    public String redondear(double valor, int digitos) {
        String val = valor + "";
        BigDecimal big = new BigDecimal(val);
        big = big.setScale(digitos, RoundingMode.HALF_UP);
        return big.toString();
    }
}
