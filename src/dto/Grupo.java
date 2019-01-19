/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author ingeniero sergio
 */
public class Grupo {
    
    private String idGrupo;
    private String nombre;

    public Grupo() {
    }
    
    public Grupo(String idGrupo, String nombre) {
        this.idGrupo = idGrupo;
        this.nombre = nombre;
    }
   
    public String getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    
}
