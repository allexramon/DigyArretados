/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.gradecurricular;

import birdpoint.curso.Curso;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Adriano Lima
 */
@Entity
public class GradeCurricular {
    
    @Id
    @GeneratedValue
    private int idGradeCurricular;
    
    @Column(length = 50, nullable = false)
    private String nomeGradeCurricular;
    
    @OneToOne(fetch = FetchType.EAGER)
    private Curso curso;

    /**
     * @return the idGradeCurricular
     */
    public int getIdGradeCurricular() {
        return idGradeCurricular;
    }

    /**
     * @param idGradeCurricular the idGradeCurricular to set
     */
    public void setIdGradeCurricular(int idGradeCurricular) {
        this.idGradeCurricular = idGradeCurricular;
    }

    /**
     * @return the nomeGradeCurricular
     */
    public String getNomeGradeCurricular() {
        return nomeGradeCurricular;
    }

    /**
     * @param nomeGradeCurricular the nomeGradeCurricular to set
     */
    public void setNomeGradeCurricular(String nomeGradeCurricular) {
        this.nomeGradeCurricular = nomeGradeCurricular;
    }

    /**
     * @return the curso
     */
    public Curso getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    

}
