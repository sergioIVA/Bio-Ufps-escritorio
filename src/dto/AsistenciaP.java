/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author ingeniero sergio
 */
public class AsistenciaP {
    
    private Date Fecha;
    private java.sql.Time  horaEntrada;
    private java.sql.Time  horaSalida;
    private String Aula;
    private String motivo;

    public AsistenciaP(Date Fecha, Time horaEntrada, Time horaSalida, String Aula, String motivo) {
        this.Fecha = Fecha;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.Aula = Aula;
        this.motivo = motivo;
    }

    
    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public Time getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Time horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Time getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Time horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getAula() {
        return Aula;
    }

    public void setAula(String Aula) {
        this.Aula = Aula;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
    
    
    
    
    
}
