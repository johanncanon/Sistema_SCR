/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.conexion;

import static com.planit.scr.conexion.ConexionSQL.clave;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author VaioDevelopment
 */
public class Conexion {

    static String clave = "yU7eywfXILoZjtaD";
    // static String clave = "123456";

    public CachedRowSet Function(String sql) {
        try {
            Class.forName("org.postgresql.Driver").newInstance();

            String url = "jdbc:postgresql://localhost:5432/SCR";
            Connection con = DriverManager.getConnection(url, "postgres", clave);
            Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = s.executeQuery(sql);

            CachedRowSet crs = new CachedRowSetImpl();
            crs.populate(rs);

            rs.close();
            s.close();
            con.close();

            return crs;

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |  SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public int StoreProcedure(String sql) {
        int respuesta = 0;
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            String url = "jdbc:postgresql://localhost:5432/SCR";
          
                 Connection con = DriverManager.getConnection(url, "postgres", clave);
                Statement s = con.createStatement();
                s.execute(sql);
                s.close();
                respuesta = 1;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }
}
