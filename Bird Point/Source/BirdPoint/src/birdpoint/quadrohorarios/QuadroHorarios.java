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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

    @OneToOne(fetch = FetchType.EAGER)
    private Curso curso;

    @OneToOne(fetch = FetchType.EAGER)
    private Semestre semestre;

    @OneToOne(fetch = FetchType.EAGER)
    private GradeCurricular grade;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(
            joinColumns
            = @JoinColumn(name = "idQuadroHorarios"),
            inverseJoinColumns
            = @JoinColumn(name = "idHorario"))
    private List<Horario> horarios;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(
            joinColumns
            = @JoinColumn(name = "idQuadroHorarios"),
            inverseJoinColumns
            = @JoinColumn(name = "idProfessor"))
    private List<Professor> professores;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(
            joinColumns
            = @JoinColumn(name = "idQuadroHorarios"),
            inverseJoinColumns
            = @JoinColumn(name = "idDisciplina"))
    private List<Disciplina> disciplinas;

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
     * @return the grade
     */
    public GradeCurricular getGrade() {
        return grade;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(GradeCurricular grade) {
        this.grade = grade;
    }

    /**
     * @return the horarios
     */
    public List<Horario> getHorarios() {
        return horarios;
    }

    /**
     * @param horarios the horarios to set
     */
    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    /**
     * @return the professores
     */
    public List<Professor> getProfessores() {
        return professores;
    }

    /**
     * @param professores the professores to set
     */
    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    /**
     * @return the disciplinas
     */
    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    /**
     * @param disciplinas the disciplinas to set
     */
    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

}
