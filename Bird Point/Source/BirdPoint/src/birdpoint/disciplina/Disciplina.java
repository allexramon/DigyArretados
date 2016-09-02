/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.disciplina;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Adriano Lima
 */
@Entity
public class Disciplina {
    
    @Id
    @GeneratedValue
    private int idDisciplina;
    
    @Column(length = 50,nullable = false)
    private String nomeDisciplina;
    
    @Column(length = 50,nullable = false)
    private String nomeCurso;
    
    @Column(length = 50,nullable = false)
    private int cargaHoraria;
    
    @Column(length = 50,nullable = false)
    private int semestreCurso;

    /**
     * @return the idDisciplina
     */
    public int getIdDisciplina() {
        return idDisciplina;
    }

    /**
     * @param idDisciplina the idDisciplina to set
     */
    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    /**
     * @return the nomeDisciplina
     */
    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    /**
     * @param nomeDisciplina the nomeDisciplina to set
     */
    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    /**
     * @return the nomeCurso
     */
    public String getNomeCurso() {
        return nomeCurso;
    }

    /**
     * @param nomeCurso the nomeCurso to set
     */
    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    /**
     * @return the cargaHoraria
     */
    public int getCargaHoraria() {
        return cargaHoraria;
    }

    /**
     * @param cargaHoraria the cargaHoraria to set
     */
    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    /**
     * @return the semestreCurso
     */
    public int getSemestreCurso() {
        return semestreCurso;
    }

    /**
     * @param semestreCurso the semestreCurso to set
     */
    public void setSemestreCurso(int semestreCurso) {
        this.semestreCurso = semestreCurso;
    }
    
}
