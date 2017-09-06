/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.calculo.horas.professor;

import birdpoint.disciplina.DisciplinaDAO;
import birdpoint.professor.ProfessorDAO;
import birdpoint.quadrohorarios.QuadroHorarios;
import birdpoint.quadrohorarios.QuadroHorariosDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adriano Lima
 */
public class CalcularHorasRN {

    private QuadroHorarios quadro = new QuadroHorarios();
    private QuadroHorariosDAO daoQuadros = new QuadroHorariosDAO();
    private ProfessorDAO daoProf = new ProfessorDAO();
    private DisciplinaDAO daoDisc = new DisciplinaDAO();
    private List<QuadroHorarios> lista = new ArrayList<>();

    //Variáveis que possuem os dados do quadro de horários dos professores individualmente
    private List<InforProfessor> listaInformacoesProfessores = new ArrayList<>();
    private InforProfessor informacoesProfessor = new InforProfessor();

    private String anoExercicio;

    public CalcularHorasRN(String anoExercicio) {
        this.anoExercicio = anoExercicio;
        carregarProfessoresEDisciplinas();
    }

    public void carregarProfessoresEDisciplinas() {
        lista = daoQuadros.checkExists("anoExercicio", anoExercicio);
        for (QuadroHorarios listaQuadro : lista) {
            boolean adiciona;
            for (int i = 0; i < listaQuadro.getOrdenacaoDisciplinas().length; i++) {
                adiciona = true;
                if (listaQuadro.getOrdenacaoDisciplinas()[i] != 0) {
                    for (int j = 0; j < getListaInformacoesProfessores().size(); j++) {
                        if (getListaInformacoesProfessores().get(j).getDisciplina().getIdDisciplina() == listaQuadro.getOrdenacaoDisciplinas()[i]
                                && getListaInformacoesProfessores().get(j).getProfessor().getIdProfessor() == listaQuadro.getOrdenacaoProfessores()[i]) {
                            adiciona = false;
                        }
                    }
                    if (adiciona) {
                        informacoesProfessor.setProfessor(daoProf.consultarObjetoId("idProfessor", listaQuadro.getOrdenacaoProfessores()[i]));
                        informacoesProfessor.setDisciplina(daoDisc.consultarObjetoId("idDisciplina", listaQuadro.getOrdenacaoDisciplinas()[i]));
                        informacoesProfessor.setCurso(listaQuadro.getCurso());
                        informacoesProfessor.setSemestre(listaQuadro.getSemestre());
                        informacoesProfessor.setTurno(listaQuadro.getTurno());
                        getListaInformacoesProfessores().add(informacoesProfessor);
                        informacoesProfessor = new InforProfessor();
                    }
                }
            }
        }
    }

    /**
     * @return the listaInformacoesProfessores
     */
    public List<InforProfessor> getListaInformacoesProfessores() {
        return listaInformacoesProfessores;
    }

}
