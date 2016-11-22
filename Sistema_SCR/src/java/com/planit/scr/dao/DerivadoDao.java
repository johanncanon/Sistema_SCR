/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.Pool;
import com.planit.scr.modelos.Derivado;
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
public class DerivadoDao {

    private final Pool pool = new Pool();

    public Derivado consultarDerivado(Derivado derivado) throws Exception {
        Derivado resultado = new Derivado();
        try {
            Connection con = pool.dataSource.getConnection();
            Statement st = con.createStatement();
            try {
                String sql = "SELECT idderivado, nombre FROM public.derivados WHERE idderivado = '" + derivado.getIdderivado() + "'";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    resultado = new Derivado(rs.getInt(1), rs.getString(2));
                }
                rs.close();
                st.execute(sql);
                rs.close();
                st.close();
                con.close();
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    public List<Derivado> consultarDerivados() throws Exception {
        List<Derivado> resultado = new ArrayList<>();
        try {
            Connection con = pool.dataSource.getConnection();
            Statement st = con.createStatement();
            try {
                String sql = "SELECT idderivado, nombre FROM public.derivados";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    resultado.add(new Derivado(rs.getInt(1), rs.getString(2)));
                }
                st.execute(sql);
                rs.close();
                st.close();
                con.close();
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
}
