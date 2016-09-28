/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.dao.TrmDao;
import com.planit.scr.modelos.Trm;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

/**
 *
 * @author Desarrollo_Planit
 */
public class TrmCT {

    private Trm trm;
    private List<Trm> listatrm;
    private String trmd;
   

    public TrmCT() {
        trm = new Trm();
        listatrm = new ArrayList<>();
        trmd = "";
    }
    
    @PostConstruct
    public void init(){
        try {
            TrmDao trmDao = new TrmDao();
            try {
                listatrm = trmDao.consultarTrm();
            } catch (Exception ex) {
                Logger.getLogger(TrmCT.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            trmd = trmDao.consultarTrmWS();
            
            
        } catch (Exception ex) {
            Logger.getLogger(TrmCT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public Trm getTrm() {
        return trm;
    }

    public void setTrm(Trm trm) {
        this.trm = trm;
    }

    public List<Trm> getListatrm() {
        return listatrm;
    }

    public void setListatrm(List<Trm> listatrm) {
        this.listatrm = listatrm;
    }

    public String getTrmd() {
        return trmd;
    }

    public void setTrmd(String trmd) {
        this.trmd = trmd;
    }
    
    

    //Metodos
    public void registrar() throws Exception {
        TrmDao trmDao = new TrmDao();
        trmDao.registrarTrm(trm);
        trm = new Trm();
        listatrm = trmDao.consultarTrm();
    }

   

    
}
