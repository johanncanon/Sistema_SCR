/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.dao.CamposDao;
import com.planit.scr.modelos.Campos;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

/**
 *
 * @author Desarrollo_Planit
 */
public class CampoCT {

    private Campos campo;
    private List<Campos> campos;
   
    public CampoCT() {
        campo = new Campos();
        campos = new ArrayList<>();
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

    //Metodos     
    public void registrar() throws Exception {
        CamposDao camposDao = new CamposDao();
        camposDao.registrarCampo(campo);
        campo = new Campos();
        campos = camposDao.consultarCampos();
    }
    
   
   

}
