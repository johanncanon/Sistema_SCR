/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.Pool;
import com.planit.scr.metodos.Redondear;
import com.planit.scr.modelos.Campo;
import com.planit.scr.modelos.Contrato;
import com.planit.scr.modelos.Departamento;
import com.planit.scr.modelos.Regalias;
import com.planit.scr.modelos.Municipio;
import com.planit.scr.modelos.Produccion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VaioDevelopment
 */
public class RegaliasDao {

    private final Pool pool = new Pool();

    public int registrarRegalias(Regalias regalia) throws Exception {
        int resultado = 0;
        try {
            Connection con = pool.dataSource.getConnection();
            Statement st = con.createStatement();
            try {
                String sql = "INSERT INTO public.regalias (iddepartamento, idmunicipio, idcampo, idcontrato,"
                        + " porcmunicipio, porcregalias, depproductor, munproductor, munnoproductor, puertos, anio, mes, precio, regalias, idproduccion, fondonacional)"
                        + " VALUES(" + regalia.getDepartamento().getIddepartamento() + ", "
                        + "'" + regalia.getMunicipio().getIdmunicipio() + "', "
                        + "'" + regalia.getCampo().getIdcampo() + "', "
                        + "'" + regalia.getContrato().getIdcontrato() + "', "
                        + "'" + Redondear.redondear(regalia.getPorcmunicipio(), 3) + "', "
                        + "'" + regalia.getPorcregalias() + "', "
                        + "'" + Redondear.redondear(regalia.getDepproductor(), 3) + "', "
                        + "'" + Redondear.redondear(regalia.getMunproductor(), 3) + "', "
                        + "'" + Redondear.redondear(regalia.getMunnoproductor(), 3) + "', "
                        + "'" + Redondear.redondear(regalia.getPuertos(), 3) + "', "
                        + "'" + regalia.getAnio() + "', "
                        + "'" + regalia.getMes() + "', "
                        + "'" + Redondear.redondear(regalia.getPrecio(), 3) + "', "
                        + "'" + Redondear.redondear(regalia.getRegalias(), 3) + "', "
                        + "'" + regalia.getProduccion().getIdproduccion() + "', "
                        + "'" + Redondear.redondear(regalia.getFondonacional(), 3) + "')";
                st.execute(sql);                
                st.close();
                con.close();
                resultado = 1;
            } catch (SQLException e) {
                System.out.println("Error sql" + e);
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    public List<Regalias> consultarRegalias(Regalias regalia) throws Exception {
        List<Regalias> regalias = new ArrayList<>();
        DepartamentosDao departamentosDao = new DepartamentosDao();
        MunicipiosDao municipiosDao = new MunicipiosDao();
        CamposDao campoDao = new CamposDao();
        ProduccionDao produccionDao = new ProduccionDao();
        ContratosDao contratosDao = new ContratosDao();
        try {
            Connection con = pool.dataSource.getConnection();
            Statement st = con.createStatement();
            try {
                String sql = "SELECT idregalias, iddepartamento, idmunicipio, idcampo, porcmunicipio, porcregalias, depproductor, munproductor, munnoproductor, puertos, anio, mes, precio, regalias, idproduccion, fondonacional, idcontrato"
                        + " FROM public.regalias"
                        + " WHERE idmunicipio = " + regalia.getMunicipio().getIdmunicipio() + " AND anio = " + regalia.getAnio() + " and mes = " + regalia.getMes() + "";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    regalias.add(new Regalias(rs.getInt(1),
                            rs.getDouble(5),
                            rs.getDouble(6),
                            rs.getDouble(7),
                            rs.getDouble(8),
                            rs.getDouble(9),
                            rs.getDouble(10),
                            rs.getInt(11),
                            rs.getInt(12),
                            rs.getDouble(13),
                            rs.getDouble(14),
                            campoDao.consultarCampo(new Campo(rs.getInt(4))),
                            departamentosDao.consultarDepartamento(new Departamento(rs.getInt(2))),
                            municipiosDao.consultarMunicipio(new Municipio(rs.getInt(3))),
                            contratosDao.consultarContrato(new Contrato(rs.getInt(17))),
                            produccionDao.consultarProduccionCampoContrato(new Produccion(rs.getInt(15))),
                            rs.getDouble(16)));
                }
                rs.close();
                st.close();
                con.close();
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return regalias;
    }

    public boolean verificarCalculoRegalias(Regalias regalia) throws Exception {
        boolean valor = false;
        try {
            Connection con = pool.dataSource.getConnection();
            Statement st = con.createStatement();
            try {
                String sql = "SELECT DISTINCT true FROM public.regalias WHERE exists(SELECT *"
                        + " FROM public.regalias"
                        + " WHERE idmunicipio = " + regalia.getMunicipio().getIdmunicipio() + " AND anio = " + regalia.getAnio() + " and mes = " + regalia.getMes() + ")";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    valor = rs.getBoolean(1);
                }
                rs.close();
                st.close();
                con.close();
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return valor;
    }
}
