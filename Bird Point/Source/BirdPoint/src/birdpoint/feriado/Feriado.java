/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.feriado;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Adriano Lima
 */
@Entity
public class Feriado {
    
    @Id
    @GeneratedValue
    private int idFeriado;
    
    @Column(length = 100,nullable = false)
    private String nomeFeriado;
    
    @Column(length = 15,nullable = false)
    private String dataFeriado;

    /**
     * @return the idFeriado
     */
    public int getIdFeriado() {
        return idFeriado;
    }

    /**
     * @param idFeriado the idFeriado to set
     */
    public void setIdFeriado(int idFeriado) {
        this.idFeriado = idFeriado;
    }

    /**
     * @return the nomeFeriado
     */
    public String getNomeFeriado() {
        return nomeFeriado;
    }

    /**
     * @param nomeFeriado the nomeFeriado to set
     */
    public void setNomeFeriado(String nomeFeriado) {
        this.nomeFeriado = nomeFeriado;
    }

    /**
     * @return the dataFeriado
     */
    public String getDataFeriado() {
        return dataFeriado;
    }

    /**
     * @param dataFeriado the dataFeriado to set
     */
    public void setDataFeriado(String dataFeriado) {
        this.dataFeriado = dataFeriado;
    }
    
    
}
