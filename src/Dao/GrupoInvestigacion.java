/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import conexion.Conexion;
import dto.Grupo;
import dto.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ingeniero sergio
 */
public class GrupoInvestigacion {
    
    
     public ArrayList<Grupo> devolverGrupos() throws SQLException{

          ArrayList<Grupo> Grupos=new ArrayList();
          Conexion con = new Conexion();
          Connection reg = con.conectar("");
          Statement stmt = reg.createStatement();
          ResultSet rs = stmt.executeQuery("select * from grupoinvestigacion");
                          while(rs.next()){ 
                     Grupos.add(new Grupo(rs.getString("idGrupo"),rs.getString("nombre")));
                              }
                con.cerrarConexion();
                          
                          return (Grupos);
      } 
}
