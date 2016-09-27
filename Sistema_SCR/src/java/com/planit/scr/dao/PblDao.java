/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.Contratos;
import com.planit.scr.modelos.Pbl;
import com.planit.scr.modelos.Valores;
import com.planit.scr.modelos.Municipios;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VaioDevelopment
 */
public class PblDao {

    public void registrarPbl(Valores valores) throws Exception {
        Statement st = ConexionSQL.conexion();
        ContratosDao contratosDao = new ContratosDao();
        List<Contratos> contratos = contratosDao.consultarContratos();
        Pbl pbl = new Pbl();
        try {
            try {
                for (int i = 0; i < contratos.size(); i++) {
                    pbl = new Pbl();
                    pbl.setCtc((valores.getCtc() * contratos.get(i).getMedio()));
                    pbl.setCt1((pbl.getCtc() + valores.getCtmc() + valores.getCtmd() + valores.getCmt() + valores.getCr()));
                    pbl.setCce((valores.getCce() * contratos.get(i).getCov()));
                    pbl.setCt2((pbl.getCce() + valores.getCtme()));
                    pbl.setRefinacion(((valores.getPf() - pbl.getCt1()) * (valores.getV1() / valores.getVt())));
                    pbl.setExportacion(((valores.getPx() - pbl.getCt2()) * (valores.getV2() / valores.getVt())));
                    pbl.setPrc(pbl.getRefinacion() + pbl.getExportacion());
                    pbl.setIdcontrato(contratos.get(i));
                    pbl.setAnio(valores.getAnio());
                    pbl.setTrimestreMes(valores.getTrimestreMes());

                    String sql = "INSERT INTO public.pbl (ctc, ct1, cce, ct2, trimestre_mes, prc, anio, refinacion, exportacion, idcontrato)"
                            + " VALUES(" + pbl.getCtc() + ","
                            + "" + pbl.getCt1() + ","
                            + " " + pbl.getCce() + ","
                            + " " + pbl.getCt2() + ","
                            + " " + pbl.getTrimestreMes() + ","
                            + " " + pbl.getPrc() + ","
                            + " " + pbl.getAnio() + ","
                            + " " + pbl.getRefinacion() + ","
                            + " " + pbl.getExportacion() + ","
                            + " " + pbl.getIdcontrato().getIdcontrato() + ")";
                    st.execute(sql);
                }

            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
    }

    public List<Pbl> consultarPblSegunMunicipio(Municipios municipio, Pbl pbl) throws Exception {
        List<Pbl> pbls = new ArrayList<>();
        Statement st = ConexionSQL.conexion();
        ContratosDao contratosDao = new ContratosDao();
        MunicipiosDao municipiosDao = new MunicipiosDao();
        municipio = municipiosDao.consultarMunicipio(municipio);
        try {
            try {
                String sql = "SELECT p.idpbl, p.ctc, p.ct1, p.cce, p.ct2, p.trimestre_mes, p.prc, p.refinacion, p.exportacion, p.idcontrato, p.anio"
                        + " FROM public.pbl as p, public.contratos as c, public.municipios as m"
                        + " WHERE p.anio = " + pbl.getAnio() + " AND p.trimestre_mes = " + pbl.getTrimestreMes() + " AND"
                        + " p.idcontrato = c.idcontrato AND c.idmunicipio = m.idmunicipio AND m.idmunicipio = " + municipio.getIdmunicipio() + " ";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    pbls.add(new Pbl(rs.getInt(1),
                            rs.getDouble(2),
                            rs.getDouble(3),
                            rs.getDouble(4),
                            rs.getDouble(5),
                            rs.getInt(6),
                            rs.getDouble(7),
                            rs.getDouble(8),
                            rs.getDouble(9),
                            rs.getInt(11),
                            contratosDao.consultarContrato(new Contratos(rs.getInt(10)))));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        return pbls;
    }

    public Pbl consultarPblSegunContrato(Contratos contrato, int trimestre_mes, int anio) throws Exception {
        Pbl pbl = new Pbl();
        Statement st = ConexionSQL.conexion();
        ContratosDao contratosDao = new ContratosDao();

        try {
            try {
                String sql = "SELECT p.idpbl, p.ctc, p.ct1, p.cce, p.ct2, p.trimestre_mes, p.prc, p.refinacion, p.exportacion, p.idcontrato, p.anio"
                        + " FROM public.pbl as p"
                        + " WHERE p.anio = " + anio + " AND p.trimestre_mes = " + trimestre_mes + " AND"
                        + " p.idcontrato = " + contrato.getIdcontrato() + " ";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    pbl = new Pbl(rs.getInt(1),
                            rs.getDouble(2),
                            rs.getDouble(3),
                            rs.getDouble(4),
                            rs.getDouble(5),
                            rs.getInt(6),
                            rs.getDouble(7),
                            rs.getDouble(8),
                            rs.getDouble(9),
                            rs.getInt(11),
                            contratosDao.consultarContrato(new Contratos(rs.getInt(10))));
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConexionSQL.CerrarConexion();
        }
        return pbl;
    }

    //Calculos
    public int obtenerTrimestre(int mes, int anio) {
        int valor = 0;
        if (anio <= 2012) {
            if (mes >= 1 && mes <= 3) {
                valor = 1;
            } else if (mes >= 4 && mes <= 6) {
                valor = 2;
            } else if (mes >= 7 && mes <= 9) {
                valor = 3;
            } else if (mes >= 10 && mes <= 12) {
                valor = 4;
            }
        }else{
            valor = mes;
        } 
    return valor ;
}
}
