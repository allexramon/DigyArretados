/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.pendencia.ponto;

import birdpoint.anoexercicio.AnoExercicio;
import birdpoint.anoexercicio.AnoExercicioDAO;
import birdpoint.quadrohorarios.QuadroHorarios;
import birdpoint.quadrohorarios.QuadroHorariosDAO;
import birdpoint.registro.ponto.Ponto;
import birdpoint.registro.ponto.PontoDAO;
import birdpoint.util.GenericDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Nóbrega
 */
public class PendenciaRN {

    Pendencia pendencia = new Pendencia();
    PendenciaDAO pendenciaDAO = new PendenciaDAO();
    List<Pendencia> listaPendencia = new ArrayList<>();

    AnoExercicio anoExercicioAtual = new AnoExercicio();
    AnoExercicioDAO anoExercicioDAO = new AnoExercicioDAO();
    List<AnoExercicio> listaAnoExercicios;

    QuadroHorariosDAO quadroDAO = new QuadroHorariosDAO();
    List<QuadroHorarios> listaQuadroHorarios;

    PontoDAO pontoDAO = new PontoDAO();
    List<Ponto> listaPontos;

    Date dataAtual;
    SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy");

    public PendenciaRN() {
        listaAnoExercicios = anoExercicioDAO.listar();
        carregarAnoExercicioAtual();
        listaQuadroHorarios = quadroDAO.checkExists("anoExercicio", anoExercicioAtual.getNomeAnoExercicio());
        atualizarDataHora();
        listaPontos = pontoDAO.checkExists("dataPontoDiario", formatarData.format(dataAtual));
    }

    public void atualizarDataHora() {
        dataAtual = new Date();
    }

    public void verificarPendenciaEntradaSemSaida() {
        int verificarPendencia = 0;
        for (Ponto pontoGeral : listaPontos) {
            for (Ponto percorrerPonto : listaPontos) {
                if (pontoGeral.getProfessor().equals(percorrerPonto.getProfessor())) {
                    verificarPendencia++;
                }
            }
            if (verificarPendencia % 2 != 0) {
                pendencia.setAnoExercicio(pontoGeral.getAnoExercicio());
                pendencia.setCurso(null);
                pendencia.setDataPontoCompleta(pontoGeral.getDataPontoCompleta());
                pendencia.setProfessor(pontoGeral.getProfessor());
                pendencia.setSemestre(null);
                pendencia.setTurno(null);
                pendencia.setTipoPendencia("Não consta registro da saída");
                pendenciaDAO.adicionar(pendencia);
                verificarPendencia = 0;
            }
        }
    }

    //Método parar carregar o ano exercício atual
    public void carregarAnoExercicioAtual() {
        for (AnoExercicio anoExercicio1 : listaAnoExercicios) {
            if (anoExercicio1.isAnoExercicioAtual()) {
                anoExercicioAtual = anoExercicio1;
            }
        }
    }

}
