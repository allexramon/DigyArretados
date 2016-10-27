/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.registro.ponto;

import birdpoint.professor.Professor;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Adriano Lima
 */
@Entity
public class Ponto {

    @Id
    @GeneratedValue
    private int idPonto;

    @Column(length = 100)
    private Date dataPontoCompleta;
    
    @Column(length = 12)
    private String dataPontoDiario;
    
    @Column(length = 10)
    private String tipoBatida;
    

    @Column(length = 5)
    private String anoExercicio;
    
    @OneToOne
    private Professor professor;

    /**
     * @return the idPonto
     */
    public int getIdPonto() {
        return idPonto;
    }

    /**
     * @param idPonto the idPonto to set
     */
    public void setIdPonto(int idPonto) {
        this.idPonto = idPonto;
    }

    /**
     * @return the dataPontoCompleta
     */
    public Date getDataPontoCompleta() {
        return dataPontoCompleta;
    }

    /**
     * @param dataPontoCompleta the dataPontoCompleta to set
     */
    public void setDataPontoCompleta(Date dataPontoCompleta) {
        this.dataPontoCompleta = dataPontoCompleta;
    }

    /**
     * @return the dataPontoDiario
     */
    public String getDataPontoDiario() {
        return dataPontoDiario;
    }

    /**
     * @param dataPontoDiario the dataPontoDiario to set
     */
    public void setDataPontoDiario(String dataPontoDiario) {
        this.dataPontoDiario = dataPontoDiario;
    }

    /**
     * @return the tipoBatida
     */
    public String getTipoBatida() {
        return tipoBatida;
    }

    /**
     * @param tipoBatida the tipoBatida to set
     */
    public void setTipoBatida(String tipoBatida) {
        this.tipoBatida = tipoBatida;
    }

    /**
     * @return the anoExercicio
     */
    public String getAnoExercicio() {
        return anoExercicio;
    }

    /**
     * @param anoExercicio the anoExercicio to set
     */
    public void setAnoExercicio(String anoExercicio) {
        this.anoExercicio = anoExercicio;
    }

    /**
     * @return the professor
     */
    public Professor getProfessor() {
        return professor;
    }

    /**
     * @param professor the professor to set
     */
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

}
