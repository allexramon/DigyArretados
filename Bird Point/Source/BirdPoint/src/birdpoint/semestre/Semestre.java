/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.semestre;

import birdpoint.curso.Curso;
import javax.persistence.CascadeType;
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
public class Semestre {
    
    @Id
    @GeneratedValue
    private int idSemestre;
    
    @Column(length = 50, nullable = false)
    private String nomeSemestre;
    
    @OneToOne(fetch = FetchType.EAGER)
    private Curso curso;
    
    /**
     * @return the idSemestre
     */
    public int getIdSemestre() {
        return idSemestre;
    }

    /**
     * @param idSemestre the idSemestre to set
     */
    public void setIdSemestre(int idSemestre) {
        this.idSemestre = idSemestre;
    }

    /**
     * @return the nomeSemestre
     */
    public String getNomeSemestre() {
        return nomeSemestre;
    }

    /**
     * @param nomeSemestre the nomeSemestre to set
     */
    public void setNomeSemestre(String nomeSemestre) {
        this.nomeSemestre = nomeSemestre;
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
