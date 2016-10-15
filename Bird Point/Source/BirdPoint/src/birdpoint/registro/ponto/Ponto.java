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

    @Column(length = 20, nullable = false)
    private Date dataRegistroPonto;

    @Column(length = 20, nullable = false)
    private Date horaEntrada;

    @Column(length = 20, nullable = false)
    private Date horaSaida;

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
     * @return the dataRegistroPonto
     */
    public Date getDataRegistroPonto() {
        return dataRegistroPonto;
    }

    /**
     * @param dataRegistroPonto the dataRegistroPonto to set
     */
    public void setDataRegistroPonto(Date dataRegistroPonto) {
        this.dataRegistroPonto = dataRegistroPonto;
    }

    /**
     * @return the horaEntrada
     */
    public Date getHoraEntrada() {
        return horaEntrada;
    }

    /**
     * @param horaEntrada the horaEntrada to set
     */
    public void setHoraEntrada(Date horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    /**
     * @return the horaSaida
     */
    public Date getHoraSaida() {
        return horaSaida;
    }

    /**
     * @param horaSaida the horaSaida to set
     */
    public void setHoraSaida(Date horaSaida) {
        this.horaSaida = horaSaida;
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
