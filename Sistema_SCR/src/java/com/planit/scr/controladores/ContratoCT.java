/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.dao.ContratosDao;
import com.planit.scr.modelos.Contratos;
import com.planit.scr.modelos.Municipios;
import com.planit.scr.modelos.Tipos;
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
public class ContratoCT {

    private Contratos contrato;
    private List<Contratos> contratos;

    public ContratoCT() {
        contrato = new Contratos();
        contratos = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        ContratosDao contratosDao = new ContratosDao();
        try {
            contratos = contratosDao.consultarContratos();
        } catch (Exception ex) {
            Logger.getLogger(ContratoCT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Getter & Setter
    public Contratos getContrato() {
        return contrato;
    }

    public void setContrato(Contratos contrato) {
        this.contrato = contrato;
    }

    public List<Contratos> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contratos> contratos) {
        this.contratos = contratos;
    }

    //Metodos 
    public void registrar() throws Exception {
        ContratosDao contratoDao = new ContratosDao();
        contratoDao.registrarContrato(contrato);
        contrato = new Contratos();
        contratos = contratoDao.consultarContratos();
    }
}
