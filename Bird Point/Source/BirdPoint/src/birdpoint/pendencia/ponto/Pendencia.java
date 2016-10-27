/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.pendencia.ponto;

import birdpoint.curso.Curso;
import birdpoint.disciplina.Disciplina;
import birdpoint.horario.Horario;
import birdpoint.professor.Professor;
import birdpoint.semestre.Semestre;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Adriano Lima
 */
@Entity
public class Pendencia {

    @Id
    @GeneratedValue
    private int idPendencia;
    
    @Column(length = 100)
    private Date dataPontoCompleta;

    @Column(length = 10)
    private String anoExercicio;

    @Column(length = 10)
    private String turno;
    
    @Column(length = 60)
    private String tipoPendencia;
    
    @OneToOne(fetch = FetchType.EAGER)
    private Professor professor;

    @OneToOne(fetch = FetchType.EAGER)
    private Semestre semestre;

    @OneToOne(fetch = FetchType.EAGER)
    private Curso curso;

    /**
     * @return the idPendencia
     */
    public int getIdPendencia() {
        return idPendencia;
    }

    /**
     * @param idPendencia the idPendencia to set
     */
    public void setIdPendencia(int idPendencia) {
        this.idPendencia = idPendencia;
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
     * @return the turno
     */
    public String getTurno() {
        return turno;
    }

    /**
     * @param turno the turno to set
     */
    public void setTurno(String turno) {
        this.turno = turno;
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
     * @return the semestre
     */
    public Semestre getSemestre() {
        return semestre;
    }

    /**
     * @param semestre the semestre to set
     */
    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
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

    /**
     * @return the tipoPendencia
     */
    public String getTipoPendencia() {
        return tipoPendencia;
    }

    /**
     * @param tipoPendencia the tipoPendencia to set
     */
    public void setTipoPendencia(String tipoPendencia) {
        this.tipoPendencia = tipoPendencia;
    }

}
