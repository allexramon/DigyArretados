/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.professor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 *
 * @author Adriano Lima
 */
@Entity
public class Professor {

    @Id
    @GeneratedValue
    private int idProfessor;

    @Column(length = 100, nullable = false)
    private String nomeProfessor;

    @Column(length = 100)
    private String ruaProfessor;
    
    @Column(length = 100, nullable = false)
    private String cpfProfessor;
    
    @Column(length = 100)
    private String RGProfessor;

    @Column(length = 100)
    private String bairroProfessor;

    @Column(length = 100)
    private String telefoneProfessor;

    @Column(length = 100)
    private String emailProfessor;
    
    @Column
    private boolean receberEmail;

    @Column(nullable = false)
    private boolean situacaoProfessor;

    @Column(length = 100)
    private int numeroCasa;

    @Column(length = 100, nullable = false)
    private String titulacaoProfessor;
    
    @Column(length = 100)
    private String cidadeProfessor;
    
    @Column
    @Lob
    private byte[] digitalDireita;
    
    @Column
    @Lob
    private byte[] digitalEsquerda;
    
    @Column
    @Lob
    private byte[] fotoProf;

    /**
     * @return the idProfessor
     */
    public int getIdProfessor() {
        return idProfessor;
    }

    /**
     * @param idProfessor the idProfessor to set
     */
    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
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
    public String getTitulacaoProfessor() {
        return titulacaoProfessor;
    }

    /**
     * @param titulacaoProfessor the titulacaoProfessor to set
     */
    public void setTitulacaoProfessor(String titulacaoProfessor) {
        this.titulacaoProfessor = titulacaoProfessor;
    }

    /**
     * @return the cidadeProfessor
     */
    public String getCidadeProfessor() {
        return cidadeProfessor;
    }

    /**
     * @param cidadeProfessor the cidadeProfessor to set
     */
    public void setCidadeProfessor(String cidadeProfessor) {
        this.cidadeProfessor = cidadeProfessor;
    }

    /**
     * @return the digitalDireita
     */
    public byte[] getDigitalDireita() {
        return digitalDireita;
    }

    /**
     * @param digitalDireita the digitalDireita to set
     */
    public void setDigitalDireita(byte[] digitalDireita) {
        this.digitalDireita = digitalDireita;
    }

    /**
     * @return the digitalEsquerda
     */
    public byte[] getDigitalEsquerda() {
        return digitalEsquerda;
    }

    /**
     * @param digitalEsquerda the digitalEsquerda to set
     */
    public void setDigitalEsquerda(byte[] digitalEsquerda) {
        this.digitalEsquerda = digitalEsquerda;
    }

    /**
     * @return the fotoProf
     */
    public byte[] getFotoProf() {
        return fotoProf;
    }

    /**
     * @param fotoProf the fotoProf to set
     */
    public void setFotoProf(byte[] fotoProf) {
        this.fotoProf = fotoProf;
    }

    /**
     * @return the receberEmail
     */
    public boolean isReceberEmail() {
        return receberEmail;
    }

    /**
     * @param receberEmail the receberEmail to set
     */
    public void setReceberEmail(boolean receberEmail) {
        this.receberEmail = receberEmail;
    }


}
