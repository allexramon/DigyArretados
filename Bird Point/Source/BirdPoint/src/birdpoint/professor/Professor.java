/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.professor;

import birdpoint.cidade.Cidade;
import birdpoint.titulacao.Titulacao;
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
public class Professor {

    @Id
    @GeneratedValue
    private int IdProfessor;

    @Column(length = 100, nullable = false)
    private String nomeProfessor;

    @Column(length = 100, nullable = false)
    private String ruaProfessor;
    
    @Column(length = 100, nullable = false)
    private String cpfProfessor;
    
    @Column(length = 100, nullable = false)
    private String RGProfessor;

    @Column(length = 100, nullable = false)
    private String bairroProfessor;

    @Column(length = 100, nullable = false)
    private String telefoneProfessor;

    @Column(length = 100, nullable = false)
    private String emailProfessor;

    @Column(length = 100, nullable = false)
    private String fotoProfessor;

    @Column(nullable = false)
    private boolean situacaoProfessor;

    @Column(length = 100, nullable = false)
    private int numeroCasa;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Titulacao titulacaoProfessor;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Cidade cidadeProfessor;

    /**
     * @return the IdProfessor
     */
    public int getIdProfessor() {
        return IdProfessor;
    }

    /**
     * @param IdProfessor the IdProfessor to set
     */
    public void setIdProfessor(int IdProfessor) {
        this.IdProfessor = IdProfessor;
    }

    /**
     * @return the nomeProfessor
     */
    public String getNomeProfessor() {
        return nomeProfessor;
    }

    /**
     * @param nomeProfessor the nomeProfessor to set
     */
    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    /**
     * @return the ruaProfessor
     */
    public String getRuaProfessor() {
        return ruaProfessor;
    }

    /**
     * @param ruaProfessor the ruaProfessor to set
     */
    public void setRuaProfessor(String ruaProfessor) {
        this.ruaProfessor = ruaProfessor;
    }

    /**
     * @return the bairroProfessor
     */
    public String getBairroProfessor() {
        return bairroProfessor;
    }

    /**
     * @param bairroProfessor the bairroProfessor to set
     */
    public void setBairroProfessor(String bairroProfessor) {
        this.bairroProfessor = bairroProfessor;
    }

    /**
     * @return the telefoneProfessor
     */
    public String getTelefoneProfessor() {
        return telefoneProfessor;
    }

    /**
     * @param telefoneProfessor the telefoneProfessor to set
     */
    public void setTelefoneProfessor(String telefoneProfessor) {
        this.telefoneProfessor = telefoneProfessor;
    }

    /**
     * @return the emailProfessor
     */
    public String getEmailProfessor() {
        return emailProfessor;
    }

    /**
     * @param emailProfessor the emailProfessor to set
     */
    public void setEmailProfessor(String emailProfessor) {
        this.emailProfessor = emailProfessor;
    }

    /**
     * @return the fotoProfessor
     */
    public String getFotoProfessor() {
        return fotoProfessor;
    }

    /**
     * @param fotoProfessor the fotoProfessor to set
     */
    public void setFotoProfessor(String fotoProfessor) {
        this.fotoProfessor = fotoProfessor;
    }

    /**
     * @return the situacaoProfessor
     */
    public boolean isSituacaoProfessor() {
        return situacaoProfessor;
    }

    /**
     * @param situacaoProfessor the situacaoProfessor to set
     */
    public void setSituacaoProfessor(boolean situacaoProfessor) {
        this.situacaoProfessor = situacaoProfessor;
    }

    /**
     * @return the numeroCasa
     */
    public int getNumeroCasa() {
        return numeroCasa;
    }

    /**
     * @param numeroCasa the numeroCasa to set
     */
    public void setNumeroCasa(int numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    /**
     * @return the titulacaoProfessor
     */
    public Titulacao getTitulacaoProfessor() {
        return titulacaoProfessor;
    }

    /**
     * @param titulacaoProfessor the titulacaoProfessor to set
     */
    public void setTitulacaoProfessor(Titulacao titulacaoProfessor) {
        this.titulacaoProfessor = titulacaoProfessor;
    }

    /**
     * @return the cidadeProfessor
     */
    public Cidade getCidadeProfessor() {
        return cidadeProfessor;
    }

    /**
     * @param cidadeProfessor the cidadeProfessor to set
     */
    public void setCidadeProfessor(Cidade cidadeProfessor) {
        this.cidadeProfessor = cidadeProfessor;
    }

    /**
     * @return the cpfProfessor
     */
    public String getCpfProfessor() {
        return cpfProfessor;
    }

    /**
     * @param cpfProfessor the cpfProfessor to set
     */
    public void setCpfProfessor(String cpfProfessor) {
        this.cpfProfessor = cpfProfessor;
    }

    /**
     * @return the RGProfessor
     */
    public String getRGProfessor() {
        return RGProfessor;
    }

    /**
     * @param RGProfessor the RGProfessor to set
     */
    public void setRGProfessor(String RGProfessor) {
        this.RGProfessor = RGProfessor;
    }

}
