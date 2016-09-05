/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Valores;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

/**
 *
 * @author Desarrollo_Planit
 */
public class ValoresCT {

    private Valores valor;
    private List<Valores> valores;
    private final Statement st = ConexionSQL.conexion();

    public ValoresCT() {
        valor = new Valores();
        valores = new ArrayList<>();
        System.out.println("" + Thread.currentThread().getContextClassLoader().getResource("/").getPath());
    }

    public Valores getValor() {
        return valor;
    }

    public void setValor(Valores valor) {
        this.valor = valor;
    }

    public List<Valores> getValores() {
        return valores;
    }

    public void setValores(List<Valores> valores) {
        this.valores = valores;
    }

    @PostConstruct
    public void init() {
        try {
            valores = consultarValores();
        } catch (Exception ex) {
            Logger.getLogger(ContratoCT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ValoresCT(Valores valor, List<Valores> valores) {
        this.valor = valor;
        this.valores = valores;
    }

    public void registrarValores() throws Exception {

        Calendar C = Calendar.getInstance();
        int sAnio = C.get(Calendar.YEAR);
        double vt = ((valor.getV1() + valor.getV2()));

        try {
            try {
                String sql = "INSERT INTO public.valores (px, pf, v1, v2, trimestre, vt, ctmd, cmt, ctmc, cr, cce, ctme)"
                        + " VALUES(" + valor.getPx() + "," + valor.getPf() + ","
                        + " " + valor.getV1() + ","
                        + " " + valor.getV2() + ","
                        + " 'Trimestre " + valor.getTrimestre() + " del " + sAnio + " ', " + vt + ","
                        + " " + valor.getCtmd() + ","
                        + " " + valor.getCmt() + ","
                        + " " + valor.getCtmc() + ","
                        + " " + valor.getCr() + ","
                        + " " + valor.getCce() + ","
                        + " " + valor.getCtme() + ")";
                st.execute(sql);
            } catch (SQLException e) {
                throw e;
            }

            valor = new Valores();
            valores = consultarValores();

        } catch (Exception e) {
            throw e;
        }
    }

    public List<Valores> consultarValores() throws Exception {
        List<Valores> listaValores = new ArrayList<>();
        try {
            try {
                String sql = "SELECT idvalores, px, pf, v1, v2, trimestre, vt, ctmd, cmt, ctmc, cr, cce, ctme "
                        + "  FROM public.valores;";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listaValores.add(new Valores(rs.getInt(1), rs.getDouble(2), rs.getDouble(3), rs.getDouble(4), rs.getDouble(5), rs.getString(6), rs.getDouble(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10), rs.getDouble(11), rs.getDouble(12), rs.getDouble(13)));
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
