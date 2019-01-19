/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import conexion.Conexion;
import dto.Aula;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class AulaDao {
    
    
    public ArrayList<Aula> extraerAulas() throws SQLException{
         ArrayList<Aula> resp=new ArrayList<>();
        Conexion con = new Conexion();
        Connection reg = con.conectar("");
        Statement stmt = reg.createStatement();
        ResultSet rs = stmt.executeQuery("select * from aula");
                          while(rs.next()){
                  resp.add(new Aula(rs.getString("Nombre_Aula"),rs.getString("Descripcion")));
                              }
         con.cerrarConexion();
                          
                          return (resp);
    }
    
    
}
