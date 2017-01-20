/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.anoexercicio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Adriano Lima
 */
@Entity
public class AnoExercicio {

    @Id
    @GeneratedValue
    private int idAnoExercicio;

    @Column(length = 10, nullable = false)
    private String nomeAnoExercicio;

    @Column(nullable = false)
    private boolean anoExercicioAtual;
    
    @Column(nullable = false)
    private boolean gerarHorarioAutomatico;

    /**
     * @return the idAnoExercicio
     */
    public int getIdAnoExercicio() {
        return idAnoExercicio;
    }

    /**
     * @param idAnoExercicio the idAnoExercicio to set
     */
    public void setIdAnoExercicio(int idAnoExercicio) {
        this.idAnoExercicio = idAnoExercicio;
    }

    /**
     * @return the nomeAnoExercicio
     */
    public String getNomeAnoExercicio() {
        return nomeAnoExercicio;
    }

    /**
     * @param nomeAnoExercicio the nomeAnoExercicio to set
     */
    public void setNomeAnoExercicio(String nomeAnoExercicio) {
        this.nomeAnoExercicio = nomeAnoExercicio;
    }

    /**
     * @return the anoExercicioAtual
     */
    public boolean isAnoExercicioAtual() {
        return anoExercicioAtual;
    }

    /**
     * @param anoExercicioAtual the anoExercicioAtual to set
     */
    public void setAnoExercicioAtual(boolean anoExercicioAtual) {
        this.anoExercicioAtual = anoExercicioAtual;
    }

    /**
     * @return the gerarHorarioAutomatico
     */
    public boolean isGerarHorarioAutomatico() {
        return gerarHorarioAutomatico;
    }

    /**
     * @param gerarHorarioAutomatico the gerarHorarioAutomatico to set
     */
    public void setGerarHorarioAutomatico(boolean gerarHorarioAutomatico) {
        this.gerarHorarioAutomatico = gerarHorarioAutomatico;
    }

  
}
