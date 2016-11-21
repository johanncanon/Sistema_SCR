/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.Conexion;
import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.modelos.CalidadCrudo;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author VaioDevelopment
 */
public class CalidadCrudoDao {

    private Conexion cn = new Conexion();

    public int registrarCalidadCrudo(CalidadCrudo calidadCrudo) throws Exception {
        int resultado = 0;
        String sql = "INSERT INTO public.calidad_crudos (cal_ref_api_exportacion, cal_ref_api_refinado, "
                + "cal_ref_azufre_exportacion, cal_ref_azufre_refinado, "
                + "corr_ref_api_exportacion, corr_ref_api_refinado, "
                + "corr_ref_azufre_exportacion, corr_ref_azufre_refinado, "
                + "precio_referencia_exportacion, precio_referencia_refinado, "
                + "trimestre_mes, anio) "
                + "VALUES ('" + calidadCrudo.getCalRefApiExportacion() + "',"
                + "'" + calidadCrudo.getCalRefApiRefinado() + "',"
                + "'" + calidadCrudo.getCalRefAzufreExportacion() + "',"
                + "'" + calidadCrudo.getCalRefAzufreRefinado() + "',"
                + "'" + calidadCrudo.getCorrRefApiExportacion() + "',"
                + "'" + calidadCrudo.getCorrRefApiRefinado() + "',"
                + "'" + calidadCrudo.getCorrRefAzufreExportacion() + "',"
                + "'" + calidadCrudo.getCorrRefAzufreRefinado() + "',"
                + "'" + calidadCrudo.getPrecioReferenciaExportacion() + "',"
                + "'" + calidadCrudo.getPrecioReferenciaRefinado() + "',"
                + "'" + calidadCrudo.getTrimestre_mes() + "',"
                + "'" + calidadCrudo.getAnio() + "')";
        resultado = cn.StoreProcedure(sql);
        return resultado;
    }

    public int modificarCalidadCrudo(CalidadCrudo calidadCrudo) throws Exception {
        int resultado = 0;
        String sql = "UPDATE public.calidad_crudos SET "
                + "cal_ref_api_exportacion = '" + calidadCrudo.getCalRefApiExportacion() + "',"
                + "cal_ref_api_refinado = '" + calidadCrudo.getCalRefApiRefinado() + "',"
                + "cal_ref_azufre_exportacion = '" + calidadCrudo.getCalRefAzufreExportacion() + "',"
                + "cal_ref_azufre_refinado = '" + calidadCrudo.getCalRefAzufreRefinado() + "',"
                + "corr_ref_api_exportacion = '" + calidadCrudo.getCorrRefApiExportacion() + "',"
                + "corr_ref_api_refinado = '" + calidadCrudo.getCorrRefApiRefinado() + "',"
                + "corr_ref_azufre_exportacion = '" + calidadCrudo.getCorrRefAzufreExportacion() + "',"
                + "corr_ref_azufre_refinado = '" + calidadCrudo.getCorrRefAzufreRefinado() + "',"
                + "precio_referencia_exportacion = '" + calidadCrudo.getPrecioReferenciaExportacion() + "',"
                + "precio_referencia_refinado = '" + calidadCrudo.getPrecioReferenciaRefinado() + "',"
                + "trimestre_mes = '" + calidadCrudo.getTrimestre_mes() + "',"
                + "anio = '" + calidadCrudo.getAnio() + "' "
                + "WHERE idcalidadcrudo = '" + calidadCrudo.getIdCalidadcrudo() + "'";
        resultado = cn.StoreProcedure(sql);
        return resultado;
    }

    public int eliminarCalidadCrudo(CalidadCrudo calidadCrudo) throws Exception {
        int resultado = 0;
        String sql = "DELETE FROM public.calidad_crudos WHERE idcalidadcrudo = '" + calidadCrudo.getIdCalidadcrudo() + "'";
        resultado = cn.StoreProcedure(sql);
        return resultado;
    }

    public List<CalidadCrudo> consultarCalidadCrudos() throws Exception {
        List<CalidadCrudo> resultado = new ArrayList<>();
        String sql = "SELECT idcalidadcrudo, cal_ref_api_exportacion, cal_ref_api_refinado, "
                + "cal_ref_azufre_exportacion, cal_ref_azufre_refinado, "
                + "corr_ref_api_exportacion, corr_ref_api_refinado, "
                + "corr_ref_azufre_exportacion, corr_ref_azufre_refinado, "
                + "precio_referencia_exportacion, precio_referencia_refinado, "
                + "trimestre_mes, anio FROM public.calidad_crudos";
        CachedRowSet crs = new CachedRowSetImpl();
        crs = cn.Function(sql);
        while (crs.next()) {
            resultado.add(new CalidadCrudo(crs.getInt(1),
                    crs.getDouble(2),
                    crs.getDouble(3),
                    crs.getDouble(4),
                    crs.getDouble(5),
                    crs.getDouble(6),
                    crs.getDouble(7),
                    crs.getDouble(8),
                    crs.getDouble(9),
                    crs.getDouble(10),
                    crs.getDouble(11),
                    crs.getInt(12),
                    crs.getInt(13)));
        }
        return resultado;
    }

    public CalidadCrudo consultarCalidadCrudo(CalidadCrudo calidadCrudo) throws Exception {
        CalidadCrudo resultado = new CalidadCrudo();
        String sql = "SELECT idcalidadcrudo, cal_ref_api_exportacion, cal_ref_api_refinado, "
                + "cal_ref_azufre_exportacion, cal_ref_azufre_refinado, "
                + "corr_ref_api_exportacion, corr_ref_api_refinado, "
                + "corr_ref_azufre_exportacion, corr_ref_azufre_refinado, "
                + "precio_referencia_exportacion, precio_referencia_refinado, "
                + "trimestre_mes, anio FROM public.calidad_crudos "
                + "WHERE anio = '" + calidadCrudo.getAnio() + "' AND trimestre_mes = '" + calidadCrudo.getTrimestre_mes() + "' or idcalidadCrudo = '" + calidadCrudo.getIdCalidadcrudo() + "'";
        CachedRowSet crs = new CachedRowSetImpl();
        crs = cn.Function(sql);
        while (crs.next()) {
            resultado = new CalidadCrudo(crs.getInt(1),
                    crs.getDouble(2),
                    crs.getDouble(3),
                    crs.getDouble(4),
                    crs.getDouble(5),
                    crs.getDouble(6),
                    crs.getDouble(7),
                    crs.getDouble(8),
                    crs.getDouble(9),
                    crs.getDouble(10),
                    crs.getDouble(11),
                    crs.getInt(12),
                    crs.getInt(13));
        }
        return resultado;
    }

    public double consultarPrecioReferenciaExportacion(int anio, int trimestre_mes) throws Exception {
        double valor = 0;
        CalidadCrudo resultado = new CalidadCrudo();
        String sql = "SELECT idcalidadcrudo, cal_ref_api_exportacion, cal_ref_api_refinado, "
                + "cal_ref_azufre_exportacion, cal_ref_azufre_refinado, "
                + "corr_ref_api_exportacion, corr_ref_api_refinado, "
                + "corr_ref_azufre_exportacion, corr_ref_azufre_refinado, "
                + "precio_referencia_exportacion, precio_referencia_refinado, "
                + "trimestre_mes, anio FROM public.calidad_crudos "
                + "WHERE anio = '" + anio + "' AND trimestre_mes = '" + trimestre_mes + "'";
        CachedRowSet crs = new CachedRowSetImpl();
        crs = cn.Function(sql);
        while (crs.next()) {
            resultado = new CalidadCrudo(crs.getInt(1),
                    crs.getDouble(2),
                    crs.getDouble(3),
                    crs.getDouble(4),
                    crs.getDouble(5),
                    crs.getDouble(6),
                    crs.getDouble(7),
                    crs.getDouble(8),
                    crs.getDouble(9),
                    crs.getDouble(10),
                    crs.getDouble(11),
                    crs.getInt(12),
                    crs.getInt(13));
        }
        valor = resultado.getPrecioReferenciaExportacion();
        return valor;
    }

}
