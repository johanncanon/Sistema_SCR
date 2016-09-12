/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;
import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Regalias;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author VaioDevelopment
 */
public class RegaliasDao {
    
    public void registrarRegalias(Regalias regalia) throws Exception{
        Statement st = ConexionSQL.conexion();
        try {
            try {
                String sql = "INSERT INTO public.regalias (iddepartamento, idmunicipio, idcampo, tipohidrocarburo,"
                        + " proddia, prodmes, porcmunicipio, porcregalias, depproductor, munproductor, depnoproductor, puertos, anio, mes, precio, regalias)"
                        + " VALUES("+regalia.getIddepartamento().getIddepartamento()+","
                + ""+regalia.getIdmunicipio().getIdmunicipio()+","
                + ""+regalia.getIdcampo().getIdcampo()+","
                + "'"+regalia.getTipohidrocarburo()+"',"
                + ""+regalia.getProddia()+","
                + ""+regalia.getProdmes()+","
                + ""+regalia.getPorcmunicipio()+","
                + ""+regalia.getPorcregalias()+","
                + ""+regalia.getDepproductor()+","
                + ""+regalia.getMunproductor()+","
                + ""+regalia.getDepnoproductor()+","
                + ""+regalia.getPuertos()+","
                + ""+regalia.getAnio()+","
                + ""+regalia.getMes()+","
                + ""+regalia.getPrecio()+","
                + ""+regalia.getRegalias()+")";
           
                st.execute(sql);
                
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally{
            ConexionSQL.CerrarConexion();
        }
    }
    
}
