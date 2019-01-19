/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.InputStream;

/**
 *
 * @author sergio
 */
public class Usuario {
   
    private String Cedula;
    private String Codigo;
    private byte[] huella;
    private String Nombre;
    private String tipoUsuario;
    private String tipoDocente;
    private String correo;
    private String cargo;
    private String telefono;
    private  InputStream foto;

   

    public Usuario(String Cedula, String Codigo, byte[] huella, String Nombre, String tipoUsuario, String tipoDocente, String correo, String cargo, String telefono, InputStream foto) {
        System.out.println("constructor de imagen"+foto);
        this.Cedula = Cedula;
        this.Codigo = Codigo;
        this.huella = huella;
        this.Nombre = Nombre;
        this.tipoUsuario = tipoUsuario;
        this.tipoDocente = tipoDocente;
        this.correo = correo;
        this.cargo = cargo;
        this.telefono = telefono;
        this.foto=foto;
    }

    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getTipoDocente() {
        return tipoDocente;
    }

    public void setTipoDocente(String tipoDocente) {
        this.tipoDocente = tipoDocente;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

 
    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

  
     public byte[] getHuella() {
        return huella;
    }

    public void setHuella(byte[] huella) {
        this.huella = huella;
    }
}
