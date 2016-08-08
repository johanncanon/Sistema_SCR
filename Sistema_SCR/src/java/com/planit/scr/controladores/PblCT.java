/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.conexion.ConexionSQL;
import java.sql.Statement;

/**
 *
 * @author Desarrollo_Planit
 */
public class PblCT {

    private final Statement st = ConexionSQL.conexion();

    public PblCT() {
    }

}
