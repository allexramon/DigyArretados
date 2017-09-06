/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.calculo.horas.professor;

import birdpoint.professor.Professor;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class CalculoHorasProfessor {

    @Id
    @GeneratedValue
    private int idCalcHoras;

    private int qtdHorasSemestre;

    private double valorTotalSemestre;
    
    private String anoExercicio;

    //Essa variável armazena a qtd de horas dividida por curso, sendo:
    // 1 ADM / 2 ADS / 3 CIC / 4 SSO / 5 EDF / 6 ENF / 7 FIS / 8 PSI
    @CollectionTable
    private int qtdHorasPorCurso;

    //Essa variável armazena o valor de horas por curso:
    // 1 ADM / 2 ADS / 3 CIC / 4 SSO / 5 EDF / 6 ENF / 7 FIS / 8 PSI
    @CollectionTable
    private double valorHorasCurso;

    //Essa variável armazena o valor de horas por curso:
    // 1 ADM / 2 ADS / 3 CIC / 4 SSO / 5 EDF / 6 ENF / 7 FIS / 8 PSI
    @CollectionTable
    private String nomeDisciplinas;

    @OneToOne(fetch = FetchType.EAGER)
    private Professor professor;

    /**
     * @return the idCalcHoras
     */
    public int getIdCalcHoras() {
        return idCalcHoras;
    }

    /**
     * @param idCalcHoras the idCalcHoras to set
     */
    public void setIdCalcHoras(int idCalcHoras) {
        this.idCalcHoras = idCalcHoras;
    }

    /**
     * @return the qtdHorasSemestre
     */
    public int getQtdHorasSemestre() {
        return qtdHorasSemestre;
    }

    /**
     * @param qtdHorasSemestre the qtdHorasSemestre to set
     */
    public void setQtdHorasSemestre(int qtdHorasSemestre) {
        this.qtdHorasSemestre = qtdHorasSemestre;
    }

    /**
     * @return the valorTotalSemestre
     */
    public double getValorTotalSemestre() {
        return valorTotalSemestre;
    }

    /**
     * @param valorTotalSemestre the valorTotalSemestre to set
     */
    public void setValorTotalSemestre(double valorTotalSemestre) {
        this.valorTotalSemestre = valorTotalSemestre;
    }

    /**
     * @return the qtdHorasPorCurso
     */
    public int getQtdHorasPorCurso() {
        return qtdHorasPorCurso;
    }

    /**
     * @param qtdHorasPorCurso the qtdHorasPorCurso to set
     */
    public void setQtdHorasPorCurso(int qtdHorasPorCurso) {
        this.qtdHorasPorCurso = qtdHorasPorCurso;
    }

    /**
     * @return the valorHorasCurso
     */
    public double getValorHorasCurso() {
        return valorHorasCurso;
    }

    /**
     * @param valorHorasCurso the valorHorasCurso to set
     */
    public void setValorHorasCurso(double valorHorasCurso) {
        this.valorHorasCurso = valorHorasCurso;
    }

    /**
     * @return the nomeDisciplinas
     */
    public String getNomeDisciplinas() {
        return nomeDisciplinas;
    }

    /**
     * @param nomeDisciplinas the nomeDisciplinas to set
     */
    public void setNomeDisciplinas(String nomeDisciplinas) {
        this.nomeDisciplinas = nomeDisciplinas;
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
