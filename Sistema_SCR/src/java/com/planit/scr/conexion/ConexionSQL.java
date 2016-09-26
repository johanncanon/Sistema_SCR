/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ContabilidadPC
 */
public class ConexionSQL {

    static String clave = "yU7eywfXILoZjtaD";
    //static String clave = "root";
    static Statement ST = null;
    static Connection cn = null;

    public static Statement conexion() {
        try {
            try {
                // CLASE USADA PARA EL DRIVER
                Class.forName("org.postgresql.Driver").newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            // RUTA DE LA BASE DE DATOS   jdbc:postgresql://192.168.100.251:5432/SCR                               
            String url = "jdbc:postgresql://192.168.100.251:5432/SCR";
            //String url = "jdbc:postgresql://localhost:5432/SCR";
            // CONECCION A LA BASE DE DATOS
            cn = DriverManager.getConnection(url, "postgres", clave);
            // TRAE LOS DATOS
            Statement st = cn.createStatement();
            ST = st;

            System.out.print("-------------------consulta BD:" + ST);
        } catch (ClassNotFoundException ex) {
            System.out.print("Error en el Driver");
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return ST;
    }

    public static Connection CerrarConexion() throws SQLException {
        cn.close();
        ST.close();
        return cn;
    }

}
