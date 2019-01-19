/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import conexion.Conexion;
import dto.Usuario;
import java.io.InputStream;
import java.sql.Blob;
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
public class UsuarioDao {
    
    
    public boolean registrarUsuario(Usuario e) throws SQLException{
        Conexion con = new Conexion();
        int n;
       try{
        Connection reg = con.conectar("");
        String sql = "INSERT INTO usuario(cedula,codigo,codigo_huellero,nombre,tipoUsuario,tipoDocente,correo,cargo,telefono,foto)values (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst = reg.prepareStatement(sql);
        pst.setString(1, e.getCedula());
        pst.setString(2,e.getCodigo());
        pst.setBytes(3,e.getHuella());
        pst.setString(4,e.getNombre());
        pst.setString(5,e.getTipoUsuario());
        pst.setString(6,e.getTipoDocente());
        pst.setString(7,e.getCorreo());
        pst.setString(8,e.getCargo());
        pst.setString(9,e.getTelefono());
        pst.setBinaryStream(10,e.getFoto());
         n = pst.executeUpdate();
        con.cerrarConexion();
       
       } catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException a){
         throw new  SQLException("Cedula  o Codigo YA EXISTE");
       }
     
                 if(n>0){
                 return true;
                 }
                return false;
    }
   
      public Usuario devolverUsuarioCedula(String cedula) throws SQLException{
          Usuario usu=null;
          Conexion con = new Conexion();
          Connection reg = con.conectar("");
          Statement stmt = reg.createStatement();
          ResultSet rs = stmt.executeQuery("select * from usuario where cedula='"+cedula+"'");
                          if(rs.next()){ 
               /**               
                Blob blob = rs.getBlob("codigo_huellero"); 
                int blobLength = (int) blob.length();
                byte[] blobAsBytes = blob.getBytes(1, blobLength);
          **/
               byte[] blobAsBytes=null;
                 InputStream valor=null;
                                    if(!rs.getString("foto").isEmpty()){
                                       valor= rs.getBinaryStream("foto");
                                    }
                usu= new Usuario(rs.getString("cedula"),rs.getString("codigo"),blobAsBytes,
                          rs.getString("nombre"),rs.getString("tipoUsuario"),rs.getString("tipoDocente"),
                          rs.getString("correo"),rs.getString("cargo"),rs.getString("telefono"),valor);
                              }
                con.cerrarConexion();
                          
                          return (usu);
      } 
      
      public  Usuario devoverUsuarioCodigo(String codigo) throws SQLException{
          
          Usuario usu=null;
          Conexion con = new Conexion();
          Connection reg = con.conectar("");
          Statement stmt = reg.createStatement();
          ResultSet rs = stmt.executeQuery("select * from usuario where codigo='"+codigo+"'");
                          while(rs.next()){
               /**               
                Blob blob = rs.getBlob("codigo_huellero"); 
                int blobLength = (int) blob.length(); 
                byte[] blobAsBytes = blob.getBytes(1, blobLength);
              **/ 
                byte[] blobAsBytes=null;
                InputStream valor=null;
                                    if(!rs.getString("foto").isEmpty()){
                                       valor= rs.getBinaryStream("foto");
                                    }
                     
                usu= new Usuario(rs.getString("cedula"),rs.getString("codigo"),blobAsBytes,
                          rs.getString("nombre"),rs.getString("tipoUsuario"),rs.getString("tipoDocente"),
                          rs.getString("correo"),rs.getString("cargo"),rs.getString("telefono"),valor);
                              }
                con.cerrarConexion();
                          
                          return (usu);
      }
    
    public ArrayList<Usuario> devolverUsuarios() throws SQLException{
         ArrayList<Usuario> resp=new ArrayList<>();
        Conexion con = new Conexion();
        Connection reg = con.conectar("");
        Statement stmt = reg.createStatement();
        ResultSet rs = stmt.executeQuery("select * from usuario");
                          while(rs.next()){
                              
                Blob blob = rs.getBlob("codigo_huellero"); 
                byte[] blobAsBytes=null;
                                if(blob!=null){
                 int blobLength = (int) blob.length(); 
                  blobAsBytes = blob.getBytes(1, blobLength); 
                          }
                Blob blob2 = rs.getBlob("foto"); 
                  InputStream valor=null;
                                    if(!rs.getString("foto").isEmpty()){
                                       valor= rs.getBinaryStream("foto");
                                    }          
                resp.add(new Usuario(rs.getString("cedula"),rs.getString("codigo"),blobAsBytes,
                          rs.getString("nombre"),rs.getString("tipoUsuario"),rs.getString("tipoDocente"),
                          rs.getString("correo"),rs.getString("cargo"),rs.getString("telefono"),valor));
                              }
         con.cerrarConexion();
                          
                          return (resp);
    }

    
    
    
    
}
