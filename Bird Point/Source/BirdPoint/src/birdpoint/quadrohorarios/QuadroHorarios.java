/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.quadrohorarios;

import birdpoint.curso.Curso;
import birdpoint.disciplina.Disciplina;
import birdpoint.gradecurricular.GradeCurricular;
import birdpoint.horario.Horario;
import birdpoint.professor.Professor;
import birdpoint.semestre.Semestre;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Adriano Lima
 */
@Entity
public class QuadroHorarios {

    @Id
    @GeneratedValue
    private int idQuadroHorarios;

    @Column(length = 10)
    private String anoExercicio;

    @Column(length = 10)
    private String turno;

    @OneToOne(fetch = FetchType.EAGER)
    private Curso curso;

    @OneToOne(fetch = FetchType.EAGER)
    private Semestre semestre;

    @OneToOne(fetch = FetchType.EAGER)
    private GradeCurricular gradeCurricular;

    @CollectionTable
    private int[] ordenacaoHorarios;

    @CollectionTable
    private int[] ordenacaoProfessores;

    @CollectionTable
    private int[] ordenacaoDisciplinas;

    /**
     * @return the idQuadroHorarios
     */
    public int getIdQuadroHorarios() {
        return idQuadroHorarios;
    }

    /**
     * @param idQuadroHorarios the idQuadroHorarios to set
     */
    public void setIdQuadroHorarios(int idQuadroHorarios) {
        this.idQuadroHorarios = idQuadroHorarios;
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
     * @return the gradeCurricular
     */
    public GradeCurricular getGradeCurricular() {
        return gradeCurricular;
    }

    /**
     * @param gradeCurricular the gradeCurricular to set
     */
    public void setGradeCurricular(GradeCurricular gradeCurricular) {
        this.gradeCurricular = gradeCurricular;
    }

    /**
     * @return the ordenacaoHorarios
     */
    public int[] getOrdenacaoHorarios() {
        return ordenacaoHorarios;
    }

    /**
     * @param ordenacaoHorarios the ordenacaoHorarios to set
     */
    public void setOrdenacaoHorarios(int[] ordenacaoHorarios) {
        this.ordenacaoHorarios = ordenacaoHorarios;
    }

    /**
     * @return the ordenacaoProfessores
     */
    public int[] getOrdenacaoProfessores() {
        return ordenacaoProfessores;
    }

    /**
     * @param ordenacaoProfessores the ordenacaoProfessores to set
     */
    public void setOrdenacaoProfessores(int[] ordenacaoProfessores) {
        this.ordenacaoProfessores = ordenacaoProfessores;
    }

    /**
     * @return the ordenacaoDisciplinas
     */
    public int[] getOrdenacaoDisciplinas() {
        return ordenacaoDisciplinas;
    }

    /**
     * @param ordenacaoDisciplinas the ordenacaoDisciplinas to set
     */
    public void setOrdenacaoDisciplinas(int[] ordenacaoDisciplinas) {
        this.ordenacaoDisciplinas = ordenacaoDisciplinas;
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

}
