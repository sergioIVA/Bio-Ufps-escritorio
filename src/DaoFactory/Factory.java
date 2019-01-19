/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoFactory;


import Dao.AsistenciaPrograma;
import Dao.AulaDao;
import Dao.GrupoInvestigacion;
import Dao.UsuarioDao;
import dto.Grupo;

/**
 *
 * @author sergio
 */
public class Factory {
    
    
    public UsuarioDao Usuario(){
     return new UsuarioDao();
    }
    
    public AsistenciaPrograma AsistenciaPrograma(){
     return new AsistenciaPrograma();
    }
    
    public AulaDao Aula(){
    return new AulaDao();
    }
    
    public GrupoInvestigacion Grupo(){
    return new GrupoInvestigacion();
    }
  
}
