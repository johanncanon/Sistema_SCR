/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Campos;
import com.planit.scr.modelos.Contratos;
import com.planit.scr.modelos.Municipios;
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
public class CampoCT {

    private Campos campo;
    private List<Campos> campos;
    private final Statement st = ConexionSQL.conexion();

    public CampoCT() {
        campo = new Campos();
        campos = new ArrayList<>();
        System.out.println("" + Thread.currentThread().getContextClassLoader().getResource("/").getPath());
    }

    @PostConstruct
    public void init() {
        try {
            campos = consultarCampos();
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
        System.out.println("" + Thread.currentThread().getContextClassLoader().getResource("/").getPath());
        ContratoCT ct = new ContratoCT();
        campo.setIdcontrato(ct.consultarContrato(campo.getIdcontrato()));
        try {
            try {
                String sql = "INSERT INTO public.campos(nombre, idcontrato)"
                        + " VALUES('" + campo.getNombre() + "', '" + campo.getIdcontrato().getIdcontrato() + "')";
                st.execute(sql);
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        campo = new Campos();
        campos = consultarCampos();
    }

    public Campos consultarCampo(Campos c) throws Exception {
        Campos nuevocampo = new Campos();
        ContratoCT ct = new ContratoCT();
        try {
            try {
                String sql = "SELECT idcampo, nombre, idcontrato FROM public.campos"
                        + " WHERE idcampo = " + c.getIdcampo() + " or nombre= '" + c.getNombre() + "'";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevocampo = new Campos(rs.getInt(1), rs.getString(2), ct.consultarContrato(new Contratos(rs.getInt(3))));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return nuevocampo;
    }

    public List<Campos> consultarCampos() throws Exception {
        List<Campos> listacampos = new ArrayList<>();
        ContratoCT ct = new ContratoCT();
        try {
            try {
                String sql = "SELECT idcampo, nombre, idcontrato FROM public.campos";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listacampos.add(new Campos(rs.getInt(1), rs.getString(2), ct.consultarContrato(new Contratos(rs.getInt(3)))));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return listacampos;
    }

    public List<Campos> consultarCampos(Municipios municipio) throws Exception {
        List<Campos> listacampos = new ArrayList<>();
        ContratoCT ct = new ContratoCT();
        try {
            try {
                String sql = "SELECT campos.idcampo, campos.nombre, campos.idcontrato FROM public.campos as campos, public.contratos as contratos, public.municipios as municipios"
                        + " WHERE campos.idcontrato = contratos.idcontrato and contratos.idmunicipio = municipios.idmunicipio and municipios.idmunicipio = " + municipio.getIdmunicipio() + "";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listacampos.add(new Campos(rs.getInt(1), rs.getString(2), ct.consultarContrato(new Contratos(rs.getInt(3)))));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return listacampos;
    }
}
