/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Formularios.Asistencia;
import conexion.Conexion;
import dto.AsistenciaP;
import dto.Aula;
import dto.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class AsistenciaPrograma {
    
    
   
    public boolean registrarAsistencia(String Aula,String Descripcion,Usuario usu,Integer Grupo) throws SQLException{
    
         java.util.Date utilDate = new java.util.Date(); //fecha actual
         long lnMilisegundos = utilDate.getTime();
         java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);
         java.sql.Time sqlTime = new java.sql.Time(lnMilisegundos);// tiempo
        
        Conexion con = new Conexion();
        Connection reg = con.conectar("");
        String sql = "INSERT INTO asistenciaprograma(Fecha,hora_Entrada,hora_Salida,Aula_Nombre,Descripcion,Usuario_cedula,Grupo)values (?,?,?,?,?,?,?)";
        PreparedStatement pst = reg.prepareStatement(sql);
        pst.setDate(1,sqlDate);
        pst.setTime(2, sqlTime);
        pst.setTime(3,null);
        pst.setString(4,Aula);
        pst.setString(5,Descripcion);
        pst.setString(6,usu.getCedula());
        System.out.println(""+Grupo);
                   if(Grupo==-1){
                    pst.setNull(7,java.sql.Types.INTEGER);
                   }
                   else{
                   pst.setInt(7,Grupo);
                   }
        int n = pst.executeUpdate();
        con.cerrarConexion();
                 if(n>0){
                 return true;
                 }
                return false;
        
    }
    
    
    public  boolean marcarSalida(String cedula) throws SQLException, SQLException, SQLException, SQLException, SQLException, SQLException{
        
        java.util.Date utilDate = new java.util.Date(); //fecha actual
        long lnMilisegundos = utilDate.getTime();
        java.sql.Time sqlTime = new java.sql.Time(lnMilisegundos);
        
        Conexion con = new Conexion();
        Connection reg = con.conectar("");
        Statement pst = reg.createStatement();
        String sql ="update asistenciaprograma set hora_Salida='"+sqlTime+"' where Usuario_cedula='"+cedula+"' && hora_Salida is null"; 
        int n=pst.executeUpdate(sql);
        con.cerrarConexion();
                         if(n>0){
                         return true;
                         }
                         return false;    
    }
    
    
   
    public  AsistenciaP marcacionSalida2(String cedula) throws SQLException{
         Conexion con = new Conexion();
        Connection reg = con.conectar("");
        Statement pst = reg.createStatement();
        String sql="select * from asistenciaprograma where Usuario_cedula='"+cedula+"' && hora_Salida is null"; 
        ResultSet rs=pst.executeQuery(sql);
        
                               if(!rs.next()){
                                   System.out.println("entro al nulo");
                               return null;
                               } 
                               
        System.out.println("entro a registrar la fecha Salida");
         java.util.Date utilDate = new java.util.Date(); //fecha actual
        long lnMilisegundos = utilDate.getTime();
        java.sql.Time sqlTime = new java.sql.Time(lnMilisegundos);
        AsistenciaP a=new AsistenciaP(rs.getDate("Fecha"),rs.getTime("hora_Entrada"),sqlTime,rs.getString("Aula_Nombre"),rs.getString("Descripcion"));   
       
        String sql2="update asistenciaprograma set hora_Salida='"+sqlTime+"' where Usuario_cedula='"+cedula+"' && hora_Salida is null";
        pst.executeUpdate(sql2);
        con.cerrarConexion();
        
               return (a);
        
    }
     
    
    
    
    public  boolean marcarSalidaCodigo(String codigo) throws SQLException{
       
        String cedula=this.buscarCedula(codigo);
                   if(cedula.isEmpty()){
                     return false;
                      }
             return  this.marcarSalida(cedula);       
    }
     
    
    private String buscarCedula(String codigo) throws SQLException{
        String cedula="";
        ArrayList<Usuario> resp=new ArrayList<>();
        Conexion con = new Conexion();
        Connection reg = con.conectar("");
        Statement stmt = reg.createStatement();
        ResultSet rs = stmt.executeQuery("select * from usuario where codigo='"+codigo+"'");
                          if(rs.next()){
                    cedula=rs.getString("cedula");
                              }
         con.cerrarConexion();
                            return cedula;             
    
    }
    
    
}
