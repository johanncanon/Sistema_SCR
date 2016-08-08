/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.conexion.ConexionSQL;
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
    private final Statement st = ConexionSQL.conexion();

    public ContratoCT() {
        contrato = new Contratos();
        contratos = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        try {
            contratos = consultarContratos();
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
        TipoCT tct = new TipoCT();
        MunicipioCT mct = new MunicipioCT();

        contrato.setIdtipo(tct.consultarTipo(contrato.getIdtipo()));
        contrato.setIdmunicipio(mct.consultarMunicipio(contrato.getIdmunicipio()));
        try {
            try {
                String sql = "INSERT INTO public.contratos(nombre, idmunicipio, idtipo)"
                        + " VALUES('" + contrato.getNombre() + "', " + contrato.getIdmunicipio().getIdmunicipio() + ", " + contrato.getIdtipo().getIdtipo() + ")";
                st.execute(sql);
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        contrato = new Contratos();
        contratos = consultarContratos();
    }

    public Contratos consultarContrato(Contratos c) throws Exception {
        Contratos nuevocontrato = new Contratos();
        TipoCT tct = new TipoCT();
        MunicipioCT mct = new MunicipioCT();
        try {
            try {
                String sql = "SELECT idcontrato, nombre, idmunicipio, idtipo FROM public.contratos"
                        + " WHERE idcontrato = " + c.getIdcontrato() + " or nombre = '" + c.getNombre() + "' ";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevocontrato = new Contratos(rs.getInt(1), rs.getString(2), tct.consultarTipo(new Tipos(rs.getInt(4))), mct.consultarMunicipio(new Municipios(rs.getInt(3))));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return nuevocontrato;
    }

    public List<Contratos> consultarContratos() throws Exception {
        List<Contratos> listacontratos = new ArrayList<>();
        TipoCT tct = new TipoCT();
        MunicipioCT mct = new MunicipioCT();
        try {
            try {
                String sql = "SELECT idcontrato, nombre, idmunicipio, idtipo FROM public.contratos";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listacontratos.add(new Contratos(rs.getInt(1), rs.getString(2), tct.consultarTipo(new Tipos(rs.getInt(4))), mct.consultarMunicipio(new Municipios(rs.getInt(3)))));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return listacontratos;
    }

}
