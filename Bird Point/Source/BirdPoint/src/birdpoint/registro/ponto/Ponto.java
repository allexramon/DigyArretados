/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.registro.ponto;

import birdpoint.anoexercicio.AnoExercicio;
import birdpoint.professor.Professor;
import java.sql.Time;
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
    
    @Column
    private Date dataPontoCompleta;

    @Column
    private String dataPonto;

    @Column
    private Time horaEntradaPonto;

    @Column
    private Time horaSaidaPonto;
    
    @Column
    private String diaDaSemana;

    @Column(length = 2000)
    private String justificativaPonto;

    @Column(length = 10)
    private String turnoPonto;

    @Column
    private boolean emailPonto;

    @Column
    private boolean atestadoPonto;

    @OneToOne
    private AnoExercicio anoExercicio;

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
     * @return the dataPonto
     */
    public String getDataPonto() {
        return dataPonto;
    }

    /**
     * @param dataPonto the dataPonto to set
     */
    public void setDataPonto(String dataPonto) {
        this.dataPonto = dataPonto;
    }

    /**
     * @return the horaEntradaPonto
     */
    public Time getHoraEntradaPonto() {
        return horaEntradaPonto;
    }

    /**
     * @param horaEntradaPonto the horaEntradaPonto to set
     */
    public void setHoraEntradaPonto(Time horaEntradaPonto) {
        this.horaEntradaPonto = horaEntradaPonto;
    }

    /**
     * @return the horaSaidaPonto
     */
    public Time getHoraSaidaPonto() {
        return horaSaidaPonto;
    }

    /**
     * @param horaSaidaPonto the horaSaidaPonto to set
     */
    public void setHoraSaidaPonto(Time horaSaidaPonto) {
        this.horaSaidaPonto = horaSaidaPonto;
    }

    /**
     * @return the diaDaSemana
     */
    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    /**
     * @param diaDaSemana the diaDaSemana to set
     */
    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    /**
     * @return the justificativaPonto
     */
    public String getJustificativaPonto() {
        return justificativaPonto;
    }

    /**
     * @param justificativaPonto the justificativaPonto to set
     */
    public void setJustificativaPonto(String justificativaPonto) {
        this.justificativaPonto = justificativaPonto;
    }

    /**
     * @return the turnoPonto
     */
    public String getTurnoPonto() {
        return turnoPonto;
    }

    /**
     * @param turnoPonto the turnoPonto to set
     */
    public void setTurnoPonto(String turnoPonto) {
        this.turnoPonto = turnoPonto;
    }

    /**
     * @return the emailPonto
     */
    public boolean isEmailPonto() {
        return emailPonto;
    }

    /**
     * @param emailPonto the emailPonto to set
     */
    public void setEmailPonto(boolean emailPonto) {
        this.emailPonto = emailPonto;
    }

    /**
     * @return the atestadoPonto
     */
    public boolean isAtestadoPonto() {
        return atestadoPonto;
    }

    /**
     * @param atestadoPonto the atestadoPonto to set
     */
    public void setAtestadoPonto(boolean atestadoPonto) {
        this.atestadoPonto = atestadoPonto;
    }

    /**
     * @return the anoExercicio
     */
    public AnoExercicio getAnoExercicio() {
        return anoExercicio;
    }

    /**
     * @param anoExercicio the anoExercicio to set
     */
    public void setAnoExercicio(AnoExercicio anoExercicio) {
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


}
