/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.dao.CamposDao;
import com.planit.scr.dao.ContratosDao;
import com.planit.scr.dao.MunicipiosDao;
import com.planit.scr.modelos.Campo;
import com.planit.scr.modelos.Contrato;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Desarrollo_Planit
 */
public class CampoCT {

    private Campo campo;
    private List<Campo> campos;
    private List<Contrato> contratos;

    private int operacion;
    private String nombreOperacion;

    public CampoCT() {
        campo = new Campo();
        campos = new ArrayList<>();
        contratos = new ArrayList<>();

        nombreOperacion = "Registrar";
        operacion = 0;

        System.out.println("" + Thread.currentThread().getContextClassLoader().getResource("/").getPath());
    }

    @PostConstruct
    public void init() {
        CamposDao camposDao = new CamposDao();
        try {
            campos = camposDao.consultarCampos();
        } catch (Exception ex) {
            Logger.getLogger(CampoCT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Campo getCampo() {
        return campo;
    }

    public void setCampo(Campo campo) {
        this.campo = campo;
    }

    public List<Campo> getCampos() {
        return campos;
    }

    public void setCampos(List<Campo> campos) {
        this.campos = campos;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    public int getOperacion() {
        return operacion;
    }

    public void setOperacion(int operacion) {
        this.operacion = operacion;
    }

    public String getNombreOperacion() {
        return nombreOperacion;
    }

    public void setNombreOperacion(String nombreOperacion) {
        this.nombreOperacion = nombreOperacion;
    }

    //Metodos     
    public void registrar() throws Exception {
        CamposDao camposDao = new CamposDao();
        ContratosDao contratosDao = new ContratosDao();

        campo.setContrato(contratosDao.consultarContrato(campo.getContrato()));
        int registrar = camposDao.registrarCampo(campo);
        if (registrar == 1) {

        } else if (registrar == 0) {

        }
        campos = camposDao.consultarCampos();
        campo = new Campo();
    }

    public void modificar() throws Exception {
        ContratosDao contratosDao = new ContratosDao();
        CamposDao camposDao = new CamposDao();
        campo.setContrato(contratosDao.consultarContrato(campo.getContrato()));
        int modificar = camposDao.modificarCampo(campo);
        if (modificar == 1) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Campo modificado correctamente");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else if (modificar == 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un erro al momento de modificar el campo");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        campo = new Campo();
        campos = camposDao.consultarCampos();
    }

    public void eliminar() throws Exception {
        CamposDao camposDao = new CamposDao();
        int eliminar = camposDao.eliminarCampo(campo);
        if (eliminar == 1) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Se elimino el campo correctamente");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else if (eliminar == 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al momento de eliminar el campo");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        campo = new Campo();
        campos = camposDao.consultarCampos();
    }

    public void metodo() throws Exception {
        if (operacion == 0) {
            registrar();
        } else if (operacion == 1) {
            modificar();
            //Reiniciamos banderas
            nombreOperacion = "Registrar";
            operacion = 0;
        }
    }

    public void seleccionarCRUD(int i) throws Exception {
        operacion = i;
        if (operacion == 1) {
            nombreOperacion = "Modificar";
        }
    }

    public void cancelar() throws Exception {
        CamposDao camposDao = new CamposDao();
        nombreOperacion = "Registrar";
        operacion = 0;
        campos = camposDao.consultarCampos();
        campo = new Campo();
    }
}
