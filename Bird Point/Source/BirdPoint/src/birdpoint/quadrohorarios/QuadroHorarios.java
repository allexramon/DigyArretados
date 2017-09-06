/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.quadrohorarios;

import birdpoint.curso.Curso;
import birdpoint.gradecurricular.GradeCurricular;
import birdpoint.semestre.Semestre;
import java.util.Arrays;
import java.util.Objects;
import javax.persistence.CollectionTable;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.idQuadroHorarios;
        hash = 89 * hash + Objects.hashCode(this.anoExercicio);
        hash = 89 * hash + Objects.hashCode(this.turno);
        hash = 89 * hash + Objects.hashCode(this.curso);
        hash = 89 * hash + Objects.hashCode(this.semestre);
        hash = 89 * hash + Objects.hashCode(this.gradeCurricular);
        hash = 89 * hash + Arrays.hashCode(this.ordenacaoHorarios);
        hash = 89 * hash + Arrays.hashCode(this.ordenacaoProfessores);
        hash = 89 * hash + Arrays.hashCode(this.ordenacaoDisciplinas);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final QuadroHorarios other = (QuadroHorarios) obj;
        if (this.idQuadroHorarios != other.idQuadroHorarios) {
            return false;
        }
        if (!Objects.equals(this.anoExercicio, other.anoExercicio)) {
            return false;
        }
        if (!Objects.equals(this.turno, other.turno)) {
            return false;
        }
        if (!Objects.equals(this.curso, other.curso)) {
            return false;
        }
        if (!Objects.equals(this.semestre, other.semestre)) {
            return false;
        }
        if (!Objects.equals(this.gradeCurricular, other.gradeCurricular)) {
            return false;
        }
        if (!Arrays.equals(this.ordenacaoHorarios, other.ordenacaoHorarios)) {
            return false;
        }
        if (!Arrays.equals(this.ordenacaoProfessores, other.ordenacaoProfessores)) {
            return false;
        }
        if (!Arrays.equals(this.ordenacaoDisciplinas, other.ordenacaoDisciplinas)) {
            return false;
        }
        return true;
    }

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
