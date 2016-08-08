/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Campos;
import com.planit.scr.modelos.Contratos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.PostConstruct;

/**
 *
 * @author Desarrollo_Planit
 */
public class CTCampos {

    private Campos campo;
    private final Statement st = ConexionSQL.conexion();

    public CTCampos() {
        campo = new Campos();
    }

    @PostConstruct
    public void init() {
    }

    public Campos getCampo() {
        return campo;
    }

    public void setCampo(Campos campo) {
        this.campo = campo;
    }

    //Metodos     
    public void registrar() throws Exception {

        try {
            try {
                String sql = "";
                st.execute(sql);
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        campo = new Campos();
    }

    public Campos consultarCampo(Campos c) throws Exception {
        Campos nuevocampo = new Campos();
        try {
            try {
                String sql = "";
                ResultSet rs = st.executeQuery(sql);
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        return nuevocampo;
    }
}
