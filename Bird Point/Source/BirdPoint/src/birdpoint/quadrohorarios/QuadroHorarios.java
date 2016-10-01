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

    @OneToOne(fetch = FetchType.EAGER)
    private Curso curso;

    @OneToOne(fetch = FetchType.EAGER)
    private Semestre semestre;

    @OneToOne(fetch = FetchType.EAGER)
    private GradeCurricular grade;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "quadrohorarios_HorariosSegunda",
            joinColumns
            = @JoinColumn(name = "idQuadroHorarios"),
            inverseJoinColumns
            = @JoinColumn(name = "idHorario"))
    private List<Horario> horariosSegunda;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "quadrohorarios_HorariosTerca",
            joinColumns
            = @JoinColumn(name = "idQuadroHorarios"),
            inverseJoinColumns
            = @JoinColumn(name = "idHorario"))
    private List<Horario> horariosTerca;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "quadrohorarios_HorariosQuarta",
            joinColumns
            = @JoinColumn(name = "idQuadroHorarios"),
            inverseJoinColumns
            = @JoinColumn(name = "idHorario"))
    private List<Horario> horariosQuarta;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "quadrohorarios_HorariosQuinta",
            joinColumns
            = @JoinColumn(name = "idQuadroHorarios"),
            inverseJoinColumns
            = @JoinColumn(name = "idHorario"))
    private List<Horario> horariosQuinta;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "quadrohorarios_HorariosSexta",
            joinColumns
            = @JoinColumn(name = "idQuadroHorarios"),
            inverseJoinColumns
            = @JoinColumn(name = "idHorario"))
    private List<Horario> horariosSexta;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "quadrohorarios_HorariosSabado",
            joinColumns
            = @JoinColumn(name = "idQuadroHorarios"),
            inverseJoinColumns
            = @JoinColumn(name = "idHorario"))
    private List<Horario> horariosSabado;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "quadrohorarios_ProfessoresSegunda",
            joinColumns
            = @JoinColumn(name = "idQuadroHorarios"),
            inverseJoinColumns
            = @JoinColumn(name = "idProfessor"))
    private List<Professor> professoresSegunda;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "quadrohorarios_ProfessoresTerca",
            joinColumns
            = @JoinColumn(name = "idQuadroHorarios"),
            inverseJoinColumns
            = @JoinColumn(name = "idProfessor"))
    private List<Professor> professoresTerca;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "quadrohorarios_ProfessoresQuarta",
            joinColumns
            = @JoinColumn(name = "idQuadroHorarios"),
            inverseJoinColumns
            = @JoinColumn(name = "idProfessor"))
    private List<Professor> professoresQuarta;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "quadrohorarios_ProfessoresQuinta",
            joinColumns
            = @JoinColumn(name = "idQuadroHorarios"),
            inverseJoinColumns
            = @JoinColumn(name = "idProfessor"))
    private List<Professor> professoresQuinta;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "quadrohorarios_ProfessoresSexta",
            joinColumns
            = @JoinColumn(name = "idQuadroHorarios"),
            inverseJoinColumns
            = @JoinColumn(name = "idProfessor"))
    private List<Professor> professoresSexta;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "quadrohorarios_ProfessoresSabado",
            joinColumns
            = @JoinColumn(name = "idQuadroHorarios"),
            inverseJoinColumns
            = @JoinColumn(name = "idProfessor"))
    private List<Professor> professoresSabado;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "quadrohorarios_DisciplinasSegunda",
            joinColumns
            = @JoinColumn(name = "idQuadroHorarios"),
            inverseJoinColumns
            = @JoinColumn(name = "idDisciplina"))
    private List<Disciplina> disciplinasSegunda;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "quadrohorarios_DisciplinasTerca",
            joinColumns
            = @JoinColumn(name = "idQuadroHorarios"),
            inverseJoinColumns
            = @JoinColumn(name = "idDisciplina"))
    private List<Disciplina> disciplinasTerca;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "quadrohorarios_DisciplinasQuarta",
            joinColumns
            = @JoinColumn(name = "idQuadroHorarios"),
            inverseJoinColumns
            = @JoinColumn(name = "idDisciplina"))
    private List<Disciplina> disciplinasQuarta;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "quadrohorarios_DisciplinasQuinta",
            joinColumns
            = @JoinColumn(name = "idQuadroHorarios"),
            inverseJoinColumns
            = @JoinColumn(name = "idDisciplina"))
    private List<Disciplina> disciplinasQuinta;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "quadrohorarios_DisciplinasSexta",
            joinColumns
            = @JoinColumn(name = "idQuadroHorarios"),
            inverseJoinColumns
            = @JoinColumn(name = "idDisciplina"))
    private List<Disciplina> disciplinasSexta;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "quadrohorarios_DisciplinasSabado",
            joinColumns
            = @JoinColumn(name = "idQuadroHorarios"),
            inverseJoinColumns
            = @JoinColumn(name = "idDisciplina"))
    private List<Disciplina> disciplinasSabado;

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
     * @return the horariosSegunda
     */
    public List<Horario> getHorariosSegunda() {
        return horariosSegunda;
    }

    /**
     * @param horariosSegunda the horariosSegunda to set
     */
    public void setHorariosSegunda(List<Horario> horariosSegunda) {
        this.horariosSegunda = horariosSegunda;
    }

    /**
     * @return the horariosTerca
     */
    public List<Horario> getHorariosTerca() {
        return horariosTerca;
    }

    /**
     * @param horariosTerca the horariosTerca to set
     */
    public void setHorariosTerca(List<Horario> horariosTerca) {
        this.horariosTerca = horariosTerca;
    }

    /**
     * @return the horariosQuarta
     */
    public List<Horario> getHorariosQuarta() {
        return horariosQuarta;
    }

    /**
     * @param horariosQuarta the horariosQuarta to set
     */
    public void setHorariosQuarta(List<Horario> horariosQuarta) {
        this.horariosQuarta = horariosQuarta;
    }

    /**
     * @return the horariosQuinta
     */
    public List<Horario> getHorariosQuinta() {
        return horariosQuinta;
    }

    /**
     * @param horariosQuinta the horariosQuinta to set
     */
    public void setHorariosQuinta(List<Horario> horariosQuinta) {
        this.horariosQuinta = horariosQuinta;
    }

    /**
     * @return the horariosSexta
     */
    public List<Horario> getHorariosSexta() {
        return horariosSexta;
    }

    /**
     * @param horariosSexta the horariosSexta to set
     */
    public void setHorariosSexta(List<Horario> horariosSexta) {
        this.horariosSexta = horariosSexta;
    }

    /**
     * @return the horariosSabado
     */
    public List<Horario> getHorariosSabado() {
        return horariosSabado;
    }

    /**
     * @param horariosSabado the horariosSabado to set
     */
    public void setHorariosSabado(List<Horario> horariosSabado) {
        this.horariosSabado = horariosSabado;
    }

    /**
     * @return the professoresSegunda
     */
    public List<Professor> getProfessoresSegunda() {
        return professoresSegunda;
    }

    /**
     * @param professoresSegunda the professoresSegunda to set
     */
    public void setProfessoresSegunda(List<Professor> professoresSegunda) {
        this.professoresSegunda = professoresSegunda;
    }

    /**
     * @return the professoresTerca
     */
    public List<Professor> getProfessoresTerca() {
        return professoresTerca;
    }

    /**
     * @param professoresTerca the professoresTerca to set
     */
    public void setProfessoresTerca(List<Professor> professoresTerca) {
        this.professoresTerca = professoresTerca;
    }

    /**
     * @return the professoresQuarta
     */
    public List<Professor> getProfessoresQuarta() {
        return professoresQuarta;
    }

    /**
     * @param professoresQuarta the professoresQuarta to set
     */
    public void setProfessoresQuarta(List<Professor> professoresQuarta) {
        this.professoresQuarta = professoresQuarta;
    }

    /**
     * @return the professoresQuinta
     */
    public List<Professor> getProfessoresQuinta() {
        return professoresQuinta;
    }

    /**
     * @param professoresQuinta the professoresQuinta to set
     */
    public void setProfessoresQuinta(List<Professor> professoresQuinta) {
        this.professoresQuinta = professoresQuinta;
    }

    /**
     * @return the professoresSexta
     */
    public List<Professor> getProfessoresSexta() {
        return professoresSexta;
    }

    /**
     * @param professoresSexta the professoresSexta to set
     */
    public void setProfessoresSexta(List<Professor> professoresSexta) {
        this.professoresSexta = professoresSexta;
    }

    /**
     * @return the professoresSabado
     */
    public List<Professor> getProfessoresSabado() {
        return professoresSabado;
    }

    /**
     * @param professoresSabado the professoresSabado to set
     */
    public void setProfessoresSabado(List<Professor> professoresSabado) {
        this.professoresSabado = professoresSabado;
    }

    /**
     * @return the disciplinasSegunda
     */
    public List<Disciplina> getDisciplinasSegunda() {
        return disciplinasSegunda;
    }

    /**
     * @param disciplinasSegunda the disciplinasSegunda to set
     */
    public void setDisciplinasSegunda(List<Disciplina> disciplinasSegunda) {
        this.disciplinasSegunda = disciplinasSegunda;
    }

    /**
     * @return the disciplinasTerca
     */
    public List<Disciplina> getDisciplinasTerca() {
        return disciplinasTerca;
    }

    /**
     * @param disciplinasTerca the disciplinasTerca to set
     */
    public void setDisciplinasTerca(List<Disciplina> disciplinasTerca) {
        this.disciplinasTerca = disciplinasTerca;
    }

    /**
     * @return the disciplinasQuarta
     */
    public List<Disciplina> getDisciplinasQuarta() {
        return disciplinasQuarta;
    }

    /**
     * @param disciplinasQuarta the disciplinasQuarta to set
     */
    public void setDisciplinasQuarta(List<Disciplina> disciplinasQuarta) {
        this.disciplinasQuarta = disciplinasQuarta;
    }

    /**
     * @return the disciplinasQuinta
     */
    public List<Disciplina> getDisciplinasQuinta() {
        return disciplinasQuinta;
    }

    /**
     * @param disciplinasQuinta the disciplinasQuinta to set
     */
    public void setDisciplinasQuinta(List<Disciplina> disciplinasQuinta) {
        this.disciplinasQuinta = disciplinasQuinta;
    }

    /**
     * @return the disciplinasSexta
     */
    public List<Disciplina> getDisciplinasSexta() {
        return disciplinasSexta;
    }

    /**
     * @param disciplinasSexta the disciplinasSexta to set
     */
    public void setDisciplinasSexta(List<Disciplina> disciplinasSexta) {
        this.disciplinasSexta = disciplinasSexta;
    }

    /**
     * @return the disciplinasSabado
     */
    public List<Disciplina> getDisciplinasSabado() {
        return disciplinasSabado;
    }

    /**
     * @param disciplinasSabado the disciplinasSabado to set
     */
    public void setDisciplinasSabado(List<Disciplina> disciplinasSabado) {
        this.disciplinasSabado = disciplinasSabado;
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

 
}
