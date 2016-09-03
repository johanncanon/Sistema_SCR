/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Pbl;
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
public class ValoresCT {

    private Pbl pbl;
    private List<Pbl> pbls;
    private final Statement st = ConexionSQL.conexion();

    public ValoresCT() {
        pbl = new Pbl();
        pbls = new ArrayList<>();
        System.out.println("" + Thread.currentThread().getContextClassLoader().getResource("/").getPath());
    }

    public Pbl getPbl() {
        return pbl;
    }

    public void setPbl(Pbl pbl) {
        this.pbl = pbl;
    }

    public List<Pbl> getPbls() {
        return pbls;
    }

    public void setPbls(List<Pbl> pbls) {
        this.pbls = pbls;
    }
    
     

    @PostConstruct
    public void init() {
        try {
            pbls = consultarValores();
        } catch (Exception ex) {
            Logger.getLogger(ContratoCT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ValoresCT(Pbl pbl, List<Pbl> pbls) {
        this.pbl = pbl;
        this.pbls = pbls;
    }

    public void registrarValores() throws Exception {
        try {
            try {
                String sql = "INSERT INTO public.valores (v1, v2, pf, px)"
                        + " VALUES(" + pbl.getV1() + "," + pbl.getV2() + ","
                        + " " + pbl.getPf() + ","
                        + " " + pbl.getPx() + ")";
                st.execute(sql);
            } catch (SQLException e) {
                throw e;
            }

            pbl = new Pbl();
            pbls = consultarValores();

        } catch (Exception e) {
            throw e;
        }
    }

    public List<Pbl> consultarValores() throws Exception {
        List<Pbl> listaValores = new ArrayList<>();
        try {
            try {
                String sql = "SELECT v1, v2, pf, px FROM public.valores";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listaValores.add(new Pbl(rs.getDouble(1), rs.getDouble(2), rs.getDouble(3), rs.getDouble(4)));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return listaValores;
    }

}
