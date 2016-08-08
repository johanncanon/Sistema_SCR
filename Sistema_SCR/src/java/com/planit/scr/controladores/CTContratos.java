/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Contratos;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Desarrollo_Planit
 */
public class CTContratos {
    
    private Contratos contrato;
    private final Statement st = ConexionSQL.conexion();

    public CTContratos() {
        contrato = new Contratos(); 
    }
    
    public void init(){
    
    }

    //Getter & Setter
    public Contratos getContrato() {
        return contrato;
    }

    public void setContrato(Contratos contrato) {
        this.contrato = contrato;
    }
    
    //Metodos 
    public void registrar() throws Exception{
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
    }       
}
