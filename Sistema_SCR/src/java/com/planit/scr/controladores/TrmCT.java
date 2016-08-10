/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Trm;
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
public class TrmCT {

    private Trm trm;
    private List<Trm> listatrm;
    private final Statement st = ConexionSQL.conexion();

    public TrmCT() {
        trm = new Trm();
        listatrm = new ArrayList<>();
    }
    
    @PostConstruct
    public void init(){
        try {
            listatrm = consultarTrm();
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

    //Metodos
    public void registrar() throws Exception {
        try {
            try {
                String sql = "INSERT INTO public.trm(fecha, valor) "
                        + "VALUES('" + trm.getFecha().toString() + "','" + trm.getValor() + "')";
                st.execute(sql);
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        trm = new Trm();
        listatrm = consultarTrm();
    }

    public List<Trm> consultarTrm() throws Exception {
        List<Trm> lista = new ArrayList<>();
        try {
            try {
                String sql = "SELECT idtrm, fecha, valor FROM public.trm";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    lista.add(new Trm(rs.getInt(1), rs.getDate(2), rs.getInt(3)));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    public Trm consultarTrm(Trm tr) throws Exception {
        Trm nuevotrm = new Trm();
        try {
            try {
                String sql = "SELECT idtrm, fecha, valor FROM public.trm "
                        + "WHERE idtrm = " + tr.getIdtrm() + " or fecha = '" + tr.getFecha() + "'";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevotrm = new Trm(rs.getInt(1), rs.getDate(2), rs.getInt(3));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return nuevotrm;
    }
}
