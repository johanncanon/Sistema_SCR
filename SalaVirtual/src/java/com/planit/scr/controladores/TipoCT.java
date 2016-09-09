/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.conexion.ConexionSQL;
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
public class TipoCT {

    private Tipos tipo;
    private List<Tipos> tipos;
    private final Statement st = ConexionSQL.conexion();

    public TipoCT() {
        tipo = new Tipos();
        tipos = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        try {
            tipos = consultarTipos();
        } catch (Exception ex) {
            Logger.getLogger(TipoCT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Tipos getTipo() {
        return tipo;
    }

    public void setTipo(Tipos tipo) {
        this.tipo = tipo;
    }

    public List<Tipos> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipos> tipos) {
        this.tipos = tipos;
    }

    //Metodos
    public Tipos consultarTipo(Tipos t) throws Exception {
        Tipos nuevotipo = new Tipos();
        try {
            try {
                String sql = "SELECT idtipo, nombre FROM public.tipos "
                        + "WHERE idtipo = " + t.getIdtipo() + " or nombre = '" + t.getNombre() + "'";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevotipo = new Tipos(rs.getInt(1), rs.getString(2));
                }
            } catch (SQLException e) {
                nuevotipo = new Tipos();
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return nuevotipo;
    }

    public List<Tipos> consultarTipos() throws Exception {
        List<Tipos> listatipos = new ArrayList<>();
        try {
            try {
                String sql = "SELECT idtipo, nombre FROM public.tipos";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listatipos.add(new Tipos(rs.getInt(1), rs.getString(2)));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return listatipos;
    }
}
