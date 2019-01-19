/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import DaoFactory.Factory;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import dto.AsistenciaP;
import dto.Aula;
import dto.Grupo;
import dto.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class Negocio {
    private  Factory baseDatos;
    
    
    public Negocio(){
    this.baseDatos=new Factory();
    }
 
    public boolean registrarUsuario(Usuario e) throws SQLException{
       return  baseDatos.Usuario().registrarUsuario(e);
    }
   
   
    /**
    public byte [] cosultarHuella(String cedula) throws SQLException{
      return this.baseDatos.Usuario().extraerHuella(cedula);
    }
    **/ 

    /**
      public byte [] cosultarHuellaUsuario(String cedula) throws SQLException{
      return this.baseDatos.Externo().extraerHuella(cedula);
    }
    **/ 
      
    public ArrayList<Usuario> devolverUsuarios() throws SQLException{
         return (this.baseDatos.Usuario().devolverUsuarios());
    }
    
    public  AsistenciaP  registrarSalida(String cedula) throws SQLException{
            return (this.baseDatos.AsistenciaPrograma().marcacionSalida2(cedula));
    }
    /**
    public ArrayList<Usuario> devolverEstudiante() throws SQLException{
         return (this.baseDatos.Usuario().extraerEstudiantes());
    }
    **/
   
    public boolean registrarAsistenciaPrograma(String aula,String Descripcion,Usuario e,int Grupo) throws SQLException{
        return this.baseDatos.AsistenciaPrograma().registrarAsistencia(aula, Descripcion, e,Grupo);
    }
   
    
    public ArrayList<Aula> mostrarAulas() throws SQLException{
     return this.baseDatos.Aula().extraerAulas();
    }
    
    public boolean marcacionSalidaCodigo(String codigo) throws SQLException{
        return this.baseDatos.AsistenciaPrograma().marcarSalidaCodigo(codigo);
    }
    
    public boolean marcacionSalidaCedula(String cedula) throws SQLException{
        return this.baseDatos.AsistenciaPrograma().marcarSalida(cedula);
    }
    
    public Usuario devolverUsuarioCodigo(String codigo) throws SQLException{
       return this.baseDatos.Usuario().devoverUsuarioCodigo(codigo); 
    }
    
    public Usuario deovolverUsuarioCedula(String cedula) throws SQLException{
       return this.baseDatos.Usuario().devolverUsuarioCedula(cedula);
    }
    /**
    public boolean registrarAsistenciaExterno(String aula,String Descripcion,Externo ex) throws SQLException{
     return this.baseDatos.AsistenciaExterna().registrarExterna(aula, Descripcion,ex);
    }
    **/ 
    
    public ArrayList<Grupo> devolverGrupo() throws SQLException{
       return this.baseDatos.Grupo().devolverGrupos();
    }
    
}
