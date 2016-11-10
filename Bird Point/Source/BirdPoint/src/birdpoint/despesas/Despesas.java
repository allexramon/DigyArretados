/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.despesas;

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
public class Despesas {
    
    @Id
    @GeneratedValue
    private int idDespesa;
    
    @Column(length = 15, nullable = false)
    private String mesDispesa;
    
    @Column(length = 5, nullable = false)
    private int anoDispesa;
    
    @Column(length = 50, nullable = false)
    private double totalDespesa;
    
    @Column(length = 50, nullable = false)
    private double resultadoDespesa;
    
    @Column(length = 50, nullable = false)
    private double receitaCurso;
    
    @Column(length = 50, nullable = false)
    private double salarioProf;
    
    @Column(length = 50, nullable = false)
    private double salarioCoor;
    
    @Column(length = 50, nullable = false)
    private double salarioTecn;
    
    @Column(length = 50, nullable = false)
    private double despesasOperacionais;
    
    @Column(length = 50, nullable = false)
    private double despesasDeEncargos;
    
    @Column(length = 50, nullable = false)
    private double despesasGerais;
    
    @OneToOne(fetch = FetchType.EAGER)
    private Curso curso;

    /**
     * @return the idDespesa
     */
    public int getIdDespesa() {
        return idDespesa;
    }

    /**
     * @param idDespesa the idDespesa to set
     */
    public void setIdDespesa(int idDespesa) {
        this.idDespesa = idDespesa;
    }

    /**
     * @return the receitaCurso
     */
    public double getReceitaCurso() {
        return receitaCurso;
    }

    /**
     * @param receitaCurso the receitaCurso to set
     */
    public void setReceitaCurso(double receitaCurso) {
        this.receitaCurso = receitaCurso;
    }

    /**
     * @return the salarioProf
     */
    public double getSalarioProf() {
        return salarioProf;
    }

    /**
     * @param salarioProf the salarioProf to set
     */
    public void setSalarioProf(double salarioProf) {
        this.salarioProf = salarioProf;
    }

    /**
     * @return the salarioCoor
     */
    public double getSalarioCoor() {
        return salarioCoor;
    }

    /**
     * @param salarioCoor the salarioCoor to set
     */
    public void setSalarioCoor(double salarioCoor) {
        this.salarioCoor = salarioCoor;
    }

    /**
     * @return the salarioTecn
     */
    public double getSalarioTecn() {
        return salarioTecn;
    }

    /**
     * @param salarioTecn the salarioTecn to set
     */
    public void setSalarioTecn(double salarioTecn) {
        this.salarioTecn = salarioTecn;
    }

    /**
     * @return the despesasOperacionais
     */
    public double getDespesasOperacionais() {
        return despesasOperacionais;
    }

    /**
     * @param despesasOperacionais the despesasOperacionais to set
     */
    public void setDespesasOperacionais(double despesasOperacionais) {
        this.despesasOperacionais = despesasOperacionais;
    }

    /**
     * @return the despesasDeEncargos
     */
    public double getDespesasDeEncargos() {
        return despesasDeEncargos;
    }

    /**
     * @param despesasDeEncargos the despesasDeEncargos to set
     */
    public void setDespesasDeEncargos(double despesasDeEncargos) {
        this.despesasDeEncargos = despesasDeEncargos;
    }

    /**
     * @return the despesasGerais
     */
    public double getDespesasGerais() {
        return despesasGerais;
    }

    /**
     * @param despesasGerais the despesasGerais to set
     */
    public void setDespesasGerais(double despesasGerais) {
        this.despesasGerais = despesasGerais;
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
     * @return the mesDispesa
     */
    public String getMesDispesa() {
        return mesDispesa;
    }

    /**
     * @param mesDispesa the mesDispesa to set
     */
    public void setMesDispesa(String mesDispesa) {
        this.mesDispesa = mesDispesa;
    }

    /**
     * @return the anoDispesa
     */
    public int getAnoDispesa() {
        return anoDispesa;
    }

    /**
     * @param anoDispesa the anoDispesa to set
     */
    public void setAnoDispesa(int anoDispesa) {
        this.anoDispesa = anoDispesa;
    }

    /**
     * @return the totalDespesa
     */
    public double getTotalDespesa() {
        return totalDespesa;
    }

    /**
     * @param totalDespesa the totalDespesa to set
     */
    public void setTotalDespesa(double totalDespesa) {
        this.totalDespesa = totalDespesa;
    }

    /**
     * @return the resultadoDespesa
     */
    public double getResultadoDespesa() {
        return resultadoDespesa;
    }

    /**
     * @param resultadoDespesa the resultadoDespesa to set
     */
    public void setResultadoDespesa(double resultadoDespesa) {
        this.resultadoDespesa = resultadoDespesa;
    }
    
}
