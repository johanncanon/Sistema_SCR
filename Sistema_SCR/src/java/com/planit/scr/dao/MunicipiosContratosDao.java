/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Contrato;
import com.planit.scr.modelos.Municipio;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author VaioDevelopment
 */
public class MunicipiosContratosDao {

    public int registrarMunicipioContrato(Municipio municipío, Contrato contrato) throws Exception {

        int resultado = 0;
        try {
            Statement st = ConexionSQL.conexion();
            try {
                String sql = "INSERT INTO public.municipios_contratos (idmunicipio, idcontrato) "
                        + "VALUES('" + municipío.getIdmunicipio() + "', '" + contrato.getIdcontrato() + "')";
                st.execute(sql);
                resultado = 1;
                st.close();
                ConexionSQL.CerrarConexion();
            } catch (SQLException e) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return resultado;
    }

    public int eliminarMunicipioContrato(Municipio municipío, Contrato contrato) throws Exception {

        int resultado = 0;
        try {
            Statement st = ConexionSQL.conexion();
            try {
                String sql = "DELETE FROM public.municipios_contratos "
                        + "WHERE idmunicipio = '" + municipío.getIdmunicipio() + "' and idcontrato = '" + contrato.getIdcontrato() + "'";
                st.execute(sql);
                resultado = 1;
                st.close();
                ConexionSQL.CerrarConexion();
            } catch (SQLException e) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return resultado;
    }
}
