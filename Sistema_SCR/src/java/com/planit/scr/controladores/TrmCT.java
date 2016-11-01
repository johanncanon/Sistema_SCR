/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import static co.com.sc.nexura.superfinanciera.action.generic.services.trm.test.TCRMTestClient.consultarTRM;
import com.planit.scr.clienttrm.action.Util;
import com.planit.scr.dao.TrmDao;
import com.planit.scr.modelos.Trm;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    public void init() {
        try {
            TrmDao trmDao = new TrmDao();
            try {
                registrarTrm();
                listatrm = trmDao.consultarTrm();
            } catch (Exception ex) {
                Logger.getLogger(TrmCT.class.getName()).log(Level.SEVERE, null, ex);
            }

            trmd = trmDao.consultarTrmWS();

        } catch (Exception ex) {
            Logger.getLogger(TrmCT.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void registrarTrm() throws Exception {
        int mes = 0, count = 0;
        Trm nuevotrm = new Trm();
        TrmDao trmDao = new TrmDao();
        Calendar calendar = Calendar.getInstance();
        nuevotrm = trmDao.consultarMaxTrm();
        if (!(nuevotrm.getFecha().toString().equals(Util.GetFechaActualDate().toString()))) {

            long dias = (Util.GetFechaActualDate().getTime() - nuevotrm.getFecha().getTime());

            dias = ((((dias / 1000) / 60) / 60) / 24);

            for (int i = 1; i <= dias; i++) {

                calendar.setTime(nuevotrm.getFecha()); // Configuramos la fecha que se recibe
                calendar.add(Calendar.DAY_OF_MONTH, i);
//                if (calendar.get(Calendar.DAY_OF_MONTH) == calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
//                    mes = calendar.get(Calendar.MONTH) + 1;
//                    count = 1;
//                } else if (count == 1) {
//                    mes = calendar.get(Calendar.MONTH) + 2;
//                    count = 0;
//                }else{
//                 mes = calendar.get(Calendar.MONTH) + 1;
//                }

                int dia = calendar.get(Calendar.DAY_OF_MONTH);
                mes = calendar.get(Calendar.MONTH) + 1;
                int anio = calendar.get(Calendar.YEAR);

                String fec = anio + "-" + mes + "-" + dia;

                double valorTrm = consultarTRM(fec);
//                double valorTrmd = Double.parseDouble(valorTrm);
                nuevotrm.setValor(valorTrm);
                trmDao.registrarTrm(nuevotrm, fec);
            }

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

//    //Metodos
//    public void registrar() throws Exception {
//        TrmDao trmDao = new TrmDao();
//        trmDao.registrarTrm(trm);
//        trm = new Trm();
//        listatrm = trmDao.consultarTrm();
//    }
}
