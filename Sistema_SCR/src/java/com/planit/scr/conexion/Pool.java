/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.conexion;

/**
 *
 * @author VaioDevelopment
 */
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;




public class Pool {


    public DataSource dataSource;


    public String url = "jdbc:postgresql://localhost:5432/SCR";
    public String user = "postgres";
    //public String clave = "123456";
    public String clave = "yU7eywfXILoZjtaD";



    public Pool(){
        inicializaDataSource();
    }

    private void inicializaDataSource(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("org.postgresql.Driver");
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(clave);
        basicDataSource.setUrl(url);
        basicDataSource.setMaxActive(5);
        basicDataSource.setMaxIdle(5);
        dataSource = basicDataSource;
    }
}

