/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Campo;
import com.planit.scr.modelos.Contrato;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author VaioDevelopment
 */
public class CamposContratosDao {

    public int registrarMunicipioContrato(Campo campo, Contrato contrato) throws Exception {

        int resultado = 0;
        try {
            Statement st = ConexionSQL.conexion();
            try {
                String sql = "INSERT INTO public.campos_contratos (idcampo, idcontrato) "
                        + "VALUES('" + campo.getIdcampo() + "', '" + contrato.getIdcontrato() + "')";
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

    public int eliminarCamposContrato(Campo campo, Contrato contrato) throws Exception {

        int resultado = 0;
        try {
            Statement st = ConexionSQL.conexion();
            try {
                String sql = "DELETE FROM public.campos_contratos "
                        + "WHERE idcampo = '" + campo.getIdcampo() + "' and idcontrato = '" + contrato.getIdcontrato() + "'";
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
