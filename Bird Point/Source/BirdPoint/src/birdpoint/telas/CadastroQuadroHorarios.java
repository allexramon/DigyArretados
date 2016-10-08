/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.telas;

import birdpoint.anoexercicio.AnoExercicio;
import birdpoint.anoexercicio.AnoExercicioDAO;
import birdpoint.curso.Curso;
import birdpoint.curso.CursoDAO;
import birdpoint.curso.CursoTableModel;
import birdpoint.disciplina.Disciplina;
import birdpoint.disciplina.DisciplinaDAO;
import birdpoint.disciplina.DisciplinaTableModel;
import birdpoint.gradecurricular.GradeCurricular;
import birdpoint.gradecurricular.GradeCurricularDAO;
import birdpoint.gradecurricular.GradeCurricularTableModel;
import birdpoint.horario.Horario;
import birdpoint.horario.HorarioDAO;
import birdpoint.horario.HorarioTableModel;
import birdpoint.professor.Professor;
import birdpoint.professor.ProfessorDAO;
import birdpoint.professor.ProfessorTableModel;
import birdpoint.quadrohorarios.QuadroHorarios;
import birdpoint.quadrohorarios.QuadroHorariosDAO;
import birdpoint.quadrohorarios.QuadroHorariosTableModel;
import birdpoint.semestre.Semestre;
import birdpoint.semestre.SemestreDAO;
import birdpoint.semestre.SemestreTableModel;
import birdpoint.util.Util;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CadastroQuadroHorarios extends javax.swing.JDialog {

    QuadroHorarios quadroHorarios;
    QuadroHorariosDAO quadroHorariosDAO = new QuadroHorariosDAO();

    AnoExercicioDAO anoExercicioDAO = new AnoExercicioDAO();

    Semestre semestre;
    SemestreDAO semestreDAO = new SemestreDAO();

    Disciplina disciplina;
    DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

    GradeCurricular gradeCurricular;
    GradeCurricularDAO gradeCurricularDAO = new GradeCurricularDAO();

    Horario horario;
    HorarioDAO horarioDAO = new HorarioDAO();

    Professor professor;
    ProfessorDAO professorDAO = new ProfessorDAO();

    Curso curso;
    CursoDAO cursoDAO = new CursoDAO();

//////////arrays para armazenar os horários////////////////////////////////
    int qtdAulas = 6;

    Horario[] horariosSegunda;
    Horario[] horariosTerca;
    Horario[] horariosQuarta;
    Horario[] horariosQuinta;
    Horario[] horariosSexta;
    Horario[] horariosSabado;

    Professor[] professoresSegunda;
    Professor[] professoresTerca;
    Professor[] professoresQuarta;
    Professor[] professoresQuinta;
    Professor[] professoresSexta;
    Professor[] professoresSabado;

    Disciplina[] disciplinasSegunda;
    Disciplina[] disciplinasTerca;
    Disciplina[] disciplinasQuarta;
    Disciplina[] disciplinasQuinta;
    Disciplina[] disciplinasSexta;
    Disciplina[] disciplinasSabado;

    // Arrays para armazenar de forma ordenada a sequencia dos horarios, disciplinas e professores
    int qtdHorDisPr = 36;

    int[] ordenacaoHorarios;
    int[] ordenacaoDisciplinas;
    int[] ordenacaoProfessores;

    // Lista para pesquisas logicais
    List<Horario> listaHorarios;
    List<Professor> listaProfessores;
    List<Disciplina> listaDisciplinas;
    List<QuadroHorarios> listaQuadroHorarios;
    List<AnoExercicio> listaAnoExercicios;

    // Método construtor da classe
    public CadastroQuadroHorarios(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        btLimparActionPerformed(null);
    }

    //Método para carregar as listas para pesquisas locais
    public void carregarListas() {
        listaHorarios = (horarioDAO.listar());
        listaProfessores = (professorDAO.listar());
        listaDisciplinas = (disciplinaDAO.listar());
        listaAnoExercicios = (anoExercicioDAO.listar());
    }

    public void carregarQuadroHorario() {
        listaQuadroHorarios = (quadroHorariosDAO.checkExistseq("anoExercicio", jcAnoExercicio.getSelectedItem()));
    }

    //Este método irá carregar o ano exercício atual
    public void carregarAnoExercicioAtual() {
        jcAnoExercicio.removeAllItems();
        jcAnoExercicio.addItem("-----");
        for (AnoExercicio anoExercicioAtual : listaAnoExercicios) {
            jcAnoExercicio.addItem(anoExercicioAtual.getNomeAnoExercicio());
        }
        for (AnoExercicio anoExercicioAtual : listaAnoExercicios) {
            if (anoExercicioAtual.isAnoExercicioAtual()) {
                jcAnoExercicio.setSelectedItem(anoExercicioAtual.getNomeAnoExercicio());
            }
        }
    }

    //Método para carregar o professor com código
    public Professor carregarProfessorLista(int o) {
        for (Professor listaProf : listaProfessores) {
            if (listaProf.getIdProfessor() == o) {
                return listaProf;
            }
        }
        JOptionPane.showMessageDialog(null, "Professor não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
        return null;
    }

    public Disciplina carregarDisciplinaLista(int o) {
        for (Disciplina listaDisp : listaDisciplinas) {
            if (listaDisp.getIdDisciplina() == o) {
                return listaDisp;
            }
        }
        return null;
    }

    public Horario carregarHorarioLista(int o) {
        for (Horario listaHorario : listaHorarios) {
            if (listaHorario.getIdHorario() == o) {
                return listaHorario;
            }
        }
        return null;
    }

    public void pesquisarProfessorCodigo(JTextField campo1, JTextField campo2) {
        if (jcAnoExercicio.getSelectedIndex() != 0 && jcTurno.getSelectedIndex() != 0 && !tfGradeCurricular.equals("") && !tfNomeCurso.equals("") && !tfNomeSemestre.equals("")) {
            try {
                professor = carregarProfessorLista(Integer.parseInt(campo1.getText()));
            } catch (Exception e) {
                professor = null;
            }
            if (professor != null) {
                campo2.setText(String.valueOf(professor.getNomeProfessor()));
                campo2.setCaretPosition(0);
            } else {
                campo1.setText("");
                campo2.setText("");
            }
        } else {
            campo1.setText("");
            JOptionPane.showMessageDialog(null, "Selecione o Curso, Semestre, Grade, Turno e Ano Exercício!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

//Este método limpara todos os campos do quadro de horários quando o Semestre, Grade, Curso, Turno ou Ano Exercício forem alterados
    public void limparCamposSeTrocarValoresDoQuadro() {
        Util.limparCamposJTabblePane(jTabbedPane1);
        horariosSegunda = new Horario[qtdAulas];
        horariosTerca = new Horario[qtdAulas];
        horariosQuarta = new Horario[qtdAulas];
        horariosQuinta = new Horario[qtdAulas];
        horariosSexta = new Horario[qtdAulas];
        horariosSabado = new Horario[qtdAulas];
        professoresSegunda = new Professor[qtdAulas];
        professoresTerca = new Professor[qtdAulas];
        professoresQuarta = new Professor[qtdAulas];
        professoresQuinta = new Professor[qtdAulas];
        professoresSexta = new Professor[qtdAulas];
        professoresSabado = new Professor[qtdAulas];
        disciplinasSegunda = new Disciplina[qtdAulas];
        disciplinasTerca = new Disciplina[qtdAulas];
        disciplinasQuarta = new Disciplina[qtdAulas];
        disciplinasQuinta = new Disciplina[qtdAulas];
        disciplinasSexta = new Disciplina[qtdAulas];
        disciplinasSabado = new Disciplina[qtdAulas];
        ordenacaoHorarios = new int[qtdHorDisPr];
        ordenacaoDisciplinas = new int[qtdHorDisPr];
        ordenacaoProfessores = new int[qtdHorDisPr];
        carregarQuadroHorario();
    }

    //Este método selecione alguma disciplina da lista
    public void selecionarDisciplina(JTextField campo) {
        if (jcAnoExercicio.getSelectedIndex() != 0 && jcTurno.getSelectedIndex() != 0 && !tfGradeCurricular.equals("") && !tfNomeCurso.equals("") && !tfNomeSemestre.equals("")) {
            List<Disciplina> listaFiltrada = new ArrayList<>();
            if (semestre.getIdSemestre() != 0) {
                listaFiltrada.clear();
                for (Disciplina disciplina1 : listaDisciplinas) {
                    if (disciplina1.getSemestre().getIdSemestre() == semestre.getIdSemestre() && disciplina1.getGradeCurricular().getIdGradeCurricular() == gradeCurricular.getIdGradeCurricular()) {
                        listaFiltrada.add(disciplina1);
                    }
                }
            }
            DisciplinaTableModel itm = new DisciplinaTableModel(listaFiltrada);
            Object objetoRetorno = PesquisaGenerica.exibeTela(itm, "Disciplina");
            if (objetoRetorno != null) {
                disciplina = carregarDisciplinaLista((int) objetoRetorno);
                campo.setText(disciplina.getNomeDisciplina());
                campo.setCaretPosition(0);
            } else {
                disciplina = null;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione o Curso, Semestre, Grade, Turno e Ano Exercício!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Este método selecione algum professor da lista 
    public void selecionarProfessor(JTextField campo1, JTextField campo2) {
        if (jcAnoExercicio.getSelectedIndex() != 0 && jcTurno.getSelectedIndex() != 0 && !tfGradeCurricular.equals("") && !tfNomeCurso.equals("") && !tfNomeSemestre.equals("")) {
            ProfessorTableModel itm = new ProfessorTableModel(listaProfessores);
            Object objetoRetorno = PesquisaGenerica.exibeTela(itm, "Professores");
            if (objetoRetorno != null) {
                professor = carregarProfessorLista((int) objetoRetorno);
                campo1.setText(String.valueOf(professor.getIdProfessor()));
                campo2.setText(professor.getNomeProfessor());
                campo2.setCaretPosition(0);
            } else {
                professor = null;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione o Curso, Semestre, Grade, Turno e Ano Exercício!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Este método seleciona algum horário da lista
    public void selecionarHorario(JTextField campo) {
        if (jcAnoExercicio.getSelectedIndex() != 0 && jcTurno.getSelectedIndex() != 0 && !tfGradeCurricular.equals("") && !tfNomeCurso.equals("") && !tfNomeSemestre.equals("")) {
            List<Horario> listaFiltrada = new ArrayList<>();
            for (Horario horario2 : listaHorarios) {
                if (horario2.getTurno().equalsIgnoreCase(String.valueOf(jcTurno.getSelectedItem()))) {
                    listaFiltrada.add(horario2);
                }
            }
            HorarioTableModel itm = new HorarioTableModel(listaFiltrada);
            Object objetoRetorno = PesquisaGenerica.exibeTela(itm, "Horários");
            if (objetoRetorno != null) {
                horario = carregarHorarioLista((int) objetoRetorno);
                campo.setText(horario.getHoraInicio());
            } else {
                horario = null;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione o Curso, Semestre, Grade, Turno e Ano Exercício!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Este método percorre todos os horários verificando se o professor está em outra disciplina no mesmo horário
    public boolean choqueHorarioProfessor(int codigoProfessor, int codigoHorario, String nomeDia, String nomeHorario) {
        // Condicação para verificar onde ele deve verificar o choque de horários
        int inicioVerificacao = 0;
        int fimVerificacao = 0;
        if (nomeDia.equalsIgnoreCase("Segunda-Feira")) {
            inicioVerificacao = 0;
            fimVerificacao = 6;
        } else if (nomeDia.equalsIgnoreCase("Terça-Feira")) {
            inicioVerificacao = 6;
            fimVerificacao = 12;
        } else if (nomeDia.equalsIgnoreCase("Quarta-Feira")) {
            inicioVerificacao = 12;
            fimVerificacao = 18;
        } else if (nomeDia.equalsIgnoreCase("Quinta-Feira")) {
            inicioVerificacao = 18;
            fimVerificacao = 24;
        } else if (nomeDia.equalsIgnoreCase("Sexta-Feira")) {
            inicioVerificacao = 24;
            fimVerificacao = 30;
        } else if (nomeDia.equalsIgnoreCase("Sábado")) {
            inicioVerificacao = 30;
            fimVerificacao = 36;
        }
        for (int j = 0; j < listaQuadroHorarios.size(); j++) {
            for (int i = inicioVerificacao; i < fimVerificacao; i++) {
                if ((listaQuadroHorarios.get(j).getOrdenacaoProfessores()[i] == codigoProfessor)
                        && (listaQuadroHorarios.get(j).getOrdenacaoHorarios()[i] == codigoHorario)) {
                    if ((!listaQuadroHorarios.get(j).getCurso().getNomeCurso().equals(curso.getNomeCurso()))
                            && (!listaQuadroHorarios.get(j).getSemestre().getNomeSemestre().equals(semestre.getNomeSemestre()))) {
                        Professor dadosProfessor = procurarProfessor(listaQuadroHorarios.get(j).getOrdenacaoProfessores()[i]);
                        Horario dadosHorario = procurarHorario(listaQuadroHorarios.get(j).getOrdenacaoHorarios()[i]);
                        Disciplina dadosDisciplina = procurarDisciplina(listaQuadroHorarios.get(j).getOrdenacaoDisciplinas()[i]);
                        JOptionPane.showMessageDialog(null, "Choque de Horários em: " + nomeDia + "      Horário: " + nomeHorario + "\n\nO(A) professor(a): " + dadosProfessor.getNomeProfessor()
                                + "\nEstá vinculado(a) no: " + listaQuadroHorarios.get(j).getSemestre().getNomeSemestre()
                                + "\nDo Curso de: " + listaQuadroHorarios.get(j).getCurso().getNomeCurso()
                                + "\nNo Horário de: " + dadosHorario.getHoraInicio() + " ás: " + dadosHorario.getHoraFim()
                                + "\nNa Disciplina: " + dadosDisciplina.getNomeDisciplina(), "Choque de Horários!!", JOptionPane.ERROR_MESSAGE);
                        return true;
                    } else if ((listaQuadroHorarios.get(j).getCurso().getNomeCurso().equals(curso.getNomeCurso()))
                            && (!listaQuadroHorarios.get(j).getSemestre().getNomeSemestre().equals(semestre.getNomeSemestre()))) {
                        Professor dadosProfessor = procurarProfessor(listaQuadroHorarios.get(j).getOrdenacaoProfessores()[i]);
                        Horario dadosHorario = procurarHorario(listaQuadroHorarios.get(j).getOrdenacaoHorarios()[i]);
                        Disciplina dadosDisciplina = procurarDisciplina(listaQuadroHorarios.get(j).getOrdenacaoDisciplinas()[i]);
                        JOptionPane.showMessageDialog(null, "Choque de Horários em: " + nomeDia + "      Horário: " + nomeHorario + "\n\nO(A) professor(a): " + dadosProfessor.getNomeProfessor()
                                + "\nEstá vinculado(a) no: " + listaQuadroHorarios.get(j).getSemestre().getNomeSemestre()
                                + "\nDo Curso de: " + listaQuadroHorarios.get(j).getCurso().getNomeCurso()
                                + "\nNo Horário de: " + dadosHorario.getHoraInicio() + " ás: " + dadosHorario.getHoraFim()
                                + "\nNa Disciplina: " + dadosDisciplina.getNomeDisciplina(), "Choque de Horários!!", JOptionPane.ERROR_MESSAGE);
                        return true;
                    }

                }
            }
        }
        return false;
    }

    // Este método verifica se existe algum horário cadastrado para o semestre selecionado
    public boolean verificarHorarioDuplicado() {
        if (quadroHorarios.getIdQuadroHorarios() != 0) {
            return false;
        }
        for (QuadroHorarios horarios : listaQuadroHorarios) {
            if ((horarios.getCurso().getNomeCurso().equals(curso.getNomeCurso()))
                    && (horarios.getSemestre().getNomeSemestre().equals(semestre.getNomeSemestre()))
                    && (horarios.getGradeCurricular().getNomeGradeCurricular().equals(gradeCurricular.getNomeGradeCurricular()))
                    && (horarios.getTurno().equals(jcTurno.getSelectedItem()))
                    && (horarios.getAnoExercicio().equals(jcAnoExercicio.getSelectedItem()))) {
                JOptionPane.showMessageDialog(null, "Este horário deste semestre já está cadastrado! Pesquise-o para alterar.", "Erro", JOptionPane.ERROR_MESSAGE);
                return true;
            }
        }
        return false;
    }

    // Método para capturar a sequencia de horários, disicplinas e professoroes escolhidos pelo usuario
    public void capturarOrdenacao() {
        for (int i = 0; i < qtdHorDisPr;) {
            for (int j = 0; j < qtdAulas; j++) {
                try {
                    if (i < 6) {
                        ordenacaoHorarios[i] = horariosSegunda[j].getIdHorario();
                        ordenacaoProfessores[i] = professoresSegunda[j].getIdProfessor();
                        ordenacaoDisciplinas[i] = disciplinasSegunda[j].getIdDisciplina();
                    } else if (i < 12) {
                        ordenacaoHorarios[i] = horariosTerca[j].getIdHorario();
                        ordenacaoProfessores[i] = professoresTerca[j].getIdProfessor();
                        ordenacaoDisciplinas[i] = disciplinasTerca[j].getIdDisciplina();
                    } else if (i < 18) {
                        ordenacaoHorarios[i] = horariosQuarta[j].getIdHorario();
                        ordenacaoProfessores[i] = professoresQuarta[j].getIdProfessor();
                        ordenacaoDisciplinas[i] = disciplinasQuarta[j].getIdDisciplina();
                    } else if (i < 24) {
                        ordenacaoHorarios[i] = horariosQuinta[j].getIdHorario();
                        ordenacaoProfessores[i] = professoresQuinta[j].getIdProfessor();
                        ordenacaoDisciplinas[i] = disciplinasQuinta[j].getIdDisciplina();
                    } else if (i < 30) {
                        ordenacaoHorarios[i] = horariosSexta[j].getIdHorario();
                        ordenacaoProfessores[i] = professoresSexta[j].getIdProfessor();
                        ordenacaoDisciplinas[i] = disciplinasSexta[j].getIdDisciplina();
                    } else if (i < 36) {
                        ordenacaoHorarios[i] = horariosSabado[j].getIdHorario();
                        ordenacaoProfessores[i] = professoresSabado[j].getIdProfessor();
                        ordenacaoDisciplinas[i] = disciplinasSabado[j].getIdDisciplina();
                    }

                } catch (Exception e) {
                    ordenacaoHorarios[i] = 0;
                    ordenacaoProfessores[i] = 0;
                    ordenacaoDisciplinas[i] = 0;
                }

                i++;
            }

        }
    }
    // Este método serve para percorrer e procurar algum horário na lista de horários

    public Horario procurarHorario(int codigo) {
        Horario horario = null;
        for (Horario horarioProcura : listaHorarios) {
            if (horarioProcura.getIdHorario() == codigo) {
                horario = horarioProcura;
                return horario;
            }
        }
        return null;
    }

    // Este método serve para percorrer e procurar algum professor na lista de professores
    public Professor procurarProfessor(int codigo) {
        Professor professor = null;
        for (Professor professorProcura : listaProfessores) {
            if (professorProcura.getIdProfessor() == codigo) {
                professor = professorProcura;
                return professor;
            }
        }
        return null;
    }

    // Este método serve para percorrer e procurar alguma disciplina na lista de disciplinas
    public Disciplina procurarDisciplina(int codigo) {
        Disciplina disciplina = null;
        for (Disciplina disciplinaProcura : listaDisciplinas) {
            if (disciplinaProcura.getIdDisciplina() == codigo) {
                disciplina = disciplinaProcura;
                return disciplina;
            }
        }
        return null;
    }

    // Este método irá ordenar as listas de horários, disciplinas e professores após serem pesquisados
    // Pois a ordenação deve ser a mesma que ele escolheu na hora de inserir o horário
    public void ordenarHorariosPesquisados() {
        ordenacaoHorarios = quadroHorarios.getOrdenacaoHorarios();
        ordenacaoDisciplinas = quadroHorarios.getOrdenacaoDisciplinas();
        ordenacaoProfessores = quadroHorarios.getOrdenacaoProfessores();

        for (int i = 0; i < qtdHorDisPr;) {
            for (int j = 0; j < qtdAulas; j++) {
                try {
                    if (i < 6) {
                        horariosSegunda[j] = procurarHorario(ordenacaoHorarios[i]);
                        professoresSegunda[j] = procurarProfessor(ordenacaoProfessores[i]);
                        disciplinasSegunda[j] = procurarDisciplina(ordenacaoDisciplinas[i]);
                    } else if (i < 12) {
                        horariosTerca[j] = procurarHorario(ordenacaoHorarios[i]);
                        professoresTerca[j] = procurarProfessor(ordenacaoProfessores[i]);
                        disciplinasTerca[j] = procurarDisciplina(ordenacaoDisciplinas[i]);
                    } else if (i < 18) {
                        horariosQuarta[j] = procurarHorario(ordenacaoHorarios[i]);
                        professoresQuarta[j] = procurarProfessor(ordenacaoProfessores[i]);
                        disciplinasQuarta[j] = procurarDisciplina(ordenacaoDisciplinas[i]);
                    } else if (i < 24) {
                        horariosQuinta[j] = procurarHorario(ordenacaoHorarios[i]);
                        professoresQuinta[j] = procurarProfessor(ordenacaoProfessores[i]);
                        disciplinasQuinta[j] = procurarDisciplina(ordenacaoDisciplinas[i]);
                    } else if (i < 30) {
                        horariosSexta[j] = procurarHorario(ordenacaoHorarios[i]);
                        professoresSexta[j] = procurarProfessor(ordenacaoProfessores[i]);
                        disciplinasSexta[j] = procurarDisciplina(ordenacaoDisciplinas[i]);
                    } else if (i < 36) {
                        horariosSabado[j] = procurarHorario(ordenacaoHorarios[i]);
                        professoresSabado[j] = procurarProfessor(ordenacaoProfessores[i]);
                        disciplinasSabado[j] = procurarDisciplina(ordenacaoDisciplinas[i]);
                    }

                } catch (Exception e) {
                }

                i++;
            }

        }
    }

    // Método para preencher os campos do quadro de horários
    public void preencherCamposComArraysQuadroHorarios() {
        try {
            segundaHorarioA.setText(horariosSegunda[0].getHoraInicio());
            segundaProfessorA.setText(professoresSegunda[0].getNomeProfessor());
            segundaDisciplinaA.setText(disciplinasSegunda[0].getNomeDisciplina());
            segundaCodigoProfessorA.setText(Integer.toString(professoresSegunda[0].getIdProfessor()));
        } catch (Exception e) {
            segundaHorarioA.setText("");
            segundaProfessorA.setText("");
            segundaDisciplinaA.setText("");
            segundaCodigoProfessorA.setText("");
        }

        try {
            segundaHorarioB.setText(horariosSegunda[1].getHoraInicio());
            segundaProfessorB.setText(professoresSegunda[1].getNomeProfessor());
            segundaDisciplinaB.setText(disciplinasSegunda[1].getNomeDisciplina());
            segundaCodigoProfessorB.setText(Integer.toString(professoresSegunda[1].getIdProfessor()));
        } catch (Exception e) {
            segundaHorarioB.setText("");
            segundaProfessorB.setText("");
            segundaDisciplinaB.setText("");
            segundaCodigoProfessorB.setText("");
        }

        try {
            segundaHorarioC.setText(horariosSegunda[2].getHoraInicio());
            segundaProfessorC.setText(professoresSegunda[2].getNomeProfessor());
            segundaDisciplinaC.setText(disciplinasSegunda[2].getNomeDisciplina());
            segundaCodigoProfessorC.setText(Integer.toString(professoresSegunda[2].getIdProfessor()));
        } catch (Exception e) {
            segundaHorarioC.setText("");
            segundaProfessorC.setText("");
            segundaDisciplinaC.setText("");
            segundaCodigoProfessorC.setText("");
        }

        try {
            segundaHorarioD.setText(horariosSegunda[3].getHoraInicio());
            segundaProfessorD.setText(professoresSegunda[3].getNomeProfessor());
            segundaDisciplinaD.setText(disciplinasSegunda[3].getNomeDisciplina());
            segundaCodigoProfessorD.setText(Integer.toString(professoresSegunda[3].getIdProfessor()));
        } catch (Exception e) {
            segundaHorarioD.setText("");
            segundaProfessorD.setText("");
            segundaDisciplinaD.setText("");
            segundaCodigoProfessorD.setText("");
        }

        try {
            segundaHorarioE.setText(horariosSegunda[4].getHoraInicio());
            segundaProfessorE.setText(professoresSegunda[4].getNomeProfessor());
            segundaDisciplinaE.setText(disciplinasSegunda[4].getNomeDisciplina());
            segundaCodigoProfessorE.setText(Integer.toString(professoresSegunda[4].getIdProfessor()));
        } catch (Exception e) {
            segundaHorarioE.setText("");
            segundaProfessorE.setText("");
            segundaDisciplinaE.setText("");
            segundaCodigoProfessorE.setText("");
        }

        try {
            segundaHorarioF.setText(horariosSegunda[5].getHoraInicio());
            segundaProfessorF.setText(professoresSegunda[5].getNomeProfessor());
            segundaDisciplinaF.setText(disciplinasSegunda[5].getNomeDisciplina());
            segundaCodigoProfessorF.setText(Integer.toString(professoresSegunda[5].getIdProfessor()));
        } catch (Exception e) {
            segundaHorarioF.setText("");
            segundaProfessorF.setText("");
            segundaDisciplinaF.setText("");
            segundaCodigoProfessorF.setText("");
        }
        try {
            tercaHorarioA.setText(horariosTerca[0].getHoraInicio());
            tercaProfessorA.setText(professoresTerca[0].getNomeProfessor());
            tercaDisciplinaA.setText(disciplinasTerca[0].getNomeDisciplina());
            tercaCodigoProfessorA.setText(Integer.toString(professoresTerca[0].getIdProfessor()));
        } catch (Exception e) {
            tercaHorarioA.setText("");
            tercaProfessorA.setText("");
            tercaDisciplinaA.setText("");
            tercaCodigoProfessorA.setText("");
        }

        try {
            tercaHorarioB.setText(horariosTerca[1].getHoraInicio());
            tercaProfessorB.setText(professoresTerca[1].getNomeProfessor());
            tercaDisciplinaB.setText(disciplinasTerca[1].getNomeDisciplina());
            tercaCodigoProfessorB.setText(Integer.toString(professoresTerca[1].getIdProfessor()));
        } catch (Exception e) {
            tercaHorarioB.setText("");
            tercaProfessorB.setText("");
            tercaDisciplinaB.setText("");
            tercaCodigoProfessorB.setText("");
        }

        try {
            tercaHorarioC.setText(horariosTerca[2].getHoraInicio());
            tercaProfessorC.setText(professoresTerca[2].getNomeProfessor());
            tercaDisciplinaC.setText(disciplinasTerca[2].getNomeDisciplina());
            tercaCodigoProfessorC.setText(Integer.toString(professoresTerca[2].getIdProfessor()));
        } catch (Exception e) {
            tercaHorarioC.setText("");
            tercaProfessorC.setText("");
            tercaDisciplinaC.setText("");
            tercaCodigoProfessorC.setText("");
        }

        try {
            tercaHorarioD.setText(horariosTerca[3].getHoraInicio());
            tercaProfessorD.setText(professoresTerca[3].getNomeProfessor());
            tercaDisciplinaD.setText(disciplinasTerca[3].getNomeDisciplina());
            tercaCodigoProfessorD.setText(Integer.toString(professoresTerca[3].getIdProfessor()));
        } catch (Exception e) {
            tercaHorarioD.setText("");
            tercaProfessorD.setText("");
            tercaDisciplinaD.setText("");
            tercaCodigoProfessorD.setText("");
        }

        try {
            tercaHorarioE.setText(horariosTerca[4].getHoraInicio());
            tercaProfessorE.setText(professoresTerca[4].getNomeProfessor());
            tercaDisciplinaE.setText(disciplinasTerca[4].getNomeDisciplina());
            tercaCodigoProfessorE.setText(Integer.toString(professoresTerca[4].getIdProfessor()));
        } catch (Exception e) {
            tercaHorarioE.setText("");
            tercaProfessorE.setText("");
            tercaDisciplinaE.setText("");
            tercaCodigoProfessorE.setText("");
        }

        try {
            tercaHorarioF.setText(horariosTerca[5].getHoraInicio());
            tercaProfessorF.setText(professoresTerca[5].getNomeProfessor());
            tercaDisciplinaF.setText(disciplinasTerca[5].getNomeDisciplina());
            tercaCodigoProfessorF.setText(Integer.toString(professoresTerca[5].getIdProfessor()));
        } catch (Exception e) {
            tercaHorarioF.setText("");
            tercaProfessorF.setText("");
            tercaDisciplinaF.setText("");
            tercaCodigoProfessorF.setText("");
        }
        try {
            quartaHorarioA.setText(horariosQuarta[0].getHoraInicio());
            quartaProfessorA.setText(professoresQuarta[0].getNomeProfessor());
            quartaDisciplinaA.setText(disciplinasQuarta[0].getNomeDisciplina());
            quartaCodigoProfessorA.setText(Integer.toString(professoresQuarta[0].getIdProfessor()));
        } catch (Exception e) {
            quartaHorarioA.setText("");
            quartaProfessorA.setText("");
            quartaDisciplinaA.setText("");
            quartaCodigoProfessorA.setText("");
        }

        try {
            quartaHorarioB.setText(horariosQuarta[1].getHoraInicio());
            quartaProfessorB.setText(professoresQuarta[1].getNomeProfessor());
            quartaDisciplinaB.setText(disciplinasQuarta[1].getNomeDisciplina());
            quartaCodigoProfessorB.setText(Integer.toString(professoresQuarta[1].getIdProfessor()));
        } catch (Exception e) {
            quartaHorarioB.setText("");
            quartaProfessorB.setText("");
            quartaDisciplinaB.setText("");
            quartaCodigoProfessorB.setText("");
        }

        try {
            quartaHorarioC.setText(horariosQuarta[2].getHoraInicio());
            quartaProfessorC.setText(professoresQuarta[2].getNomeProfessor());
            quartaDisciplinaC.setText(disciplinasQuarta[2].getNomeDisciplina());
            quartaCodigoProfessorC.setText(Integer.toString(professoresQuarta[2].getIdProfessor()));
        } catch (Exception e) {
            quartaHorarioC.setText("");
            quartaProfessorC.setText("");
            quartaDisciplinaC.setText("");
            quartaCodigoProfessorC.setText("");
        }

        try {
            quartaHorarioD.setText(horariosQuarta[3].getHoraInicio());
            quartaProfessorD.setText(professoresQuarta[3].getNomeProfessor());
            quartaDisciplinaD.setText(disciplinasQuarta[3].getNomeDisciplina());
            quartaCodigoProfessorD.setText(Integer.toString(professoresQuarta[3].getIdProfessor()));
        } catch (Exception e) {
            quartaHorarioD.setText("");
            quartaProfessorD.setText("");
            quartaDisciplinaD.setText("");
            quartaCodigoProfessorD.setText("");
        }

        try {
            quartaHorarioE.setText(horariosQuarta[4].getHoraInicio());
            quartaProfessorE.setText(professoresQuarta[4].getNomeProfessor());
            quartaDisciplinaE.setText(disciplinasQuarta[4].getNomeDisciplina());
            quartaCodigoProfessorE.setText(Integer.toString(professoresQuarta[4].getIdProfessor()));
        } catch (Exception e) {
            quartaHorarioE.setText("");
            quartaProfessorE.setText("");
            quartaDisciplinaE.setText("");
            quartaCodigoProfessorE.setText("");
        }

        try {
            quartaHorarioF.setText(horariosQuarta[5].getHoraInicio());
            quartaProfessorF.setText(professoresQuarta[5].getNomeProfessor());
            quartaDisciplinaF.setText(disciplinasQuarta[5].getNomeDisciplina());
            quartaCodigoProfessorF.setText(Integer.toString(professoresQuarta[5].getIdProfessor()));
        } catch (Exception e) {
            quartaHorarioF.setText("");
            quartaProfessorF.setText("");
            quartaDisciplinaF.setText("");
            quartaCodigoProfessorF.setText("");
        }
        try {
            quintaHorarioA.setText(horariosQuinta[0].getHoraInicio());
            quintaProfessorA.setText(professoresQuinta[0].getNomeProfessor());
            quintaDisciplinaA.setText(disciplinasQuinta[0].getNomeDisciplina());
            quintaCodigoProfessorA.setText(Integer.toString(professoresQuinta[0].getIdProfessor()));
        } catch (Exception e) {
            quintaHorarioA.setText("");
            quintaProfessorA.setText("");
            quintaDisciplinaA.setText("");
            quintaCodigoProfessorA.setText("");
        }

        try {
            quintaHorarioB.setText(horariosQuinta[1].getHoraInicio());
            quintaProfessorB.setText(professoresQuinta[1].getNomeProfessor());
            quintaDisciplinaB.setText(disciplinasQuinta[1].getNomeDisciplina());
            quintaCodigoProfessorB.setText(Integer.toString(professoresQuinta[1].getIdProfessor()));
        } catch (Exception e) {
            quintaHorarioB.setText("");
            quintaProfessorB.setText("");
            quintaDisciplinaB.setText("");
            quintaCodigoProfessorB.setText("");
        }

        try {
            quintaHorarioC.setText(horariosQuinta[2].getHoraInicio());
            quintaProfessorC.setText(professoresQuinta[2].getNomeProfessor());
            quintaDisciplinaC.setText(disciplinasQuinta[2].getNomeDisciplina());
            quintaCodigoProfessorC.setText(Integer.toString(professoresQuinta[2].getIdProfessor()));
        } catch (Exception e) {
            quintaHorarioC.setText("");
            quintaProfessorC.setText("");
            quintaDisciplinaC.setText("");
            quintaCodigoProfessorC.setText("");
        }

        try {
            quintaHorarioD.setText(horariosQuinta[3].getHoraInicio());
            quintaProfessorD.setText(professoresQuinta[3].getNomeProfessor());
            quintaDisciplinaD.setText(disciplinasQuinta[3].getNomeDisciplina());
            quintaCodigoProfessorD.setText(Integer.toString(professoresQuinta[3].getIdProfessor()));
        } catch (Exception e) {
            quintaHorarioD.setText("");
            quintaProfessorD.setText("");
            quintaDisciplinaD.setText("");
            quintaCodigoProfessorD.setText("");
        }

        try {
            quintaHorarioE.setText(horariosQuinta[4].getHoraInicio());
            quintaProfessorE.setText(professoresQuinta[4].getNomeProfessor());
            quintaDisciplinaE.setText(disciplinasQuinta[4].getNomeDisciplina());
            quintaCodigoProfessorE.setText(Integer.toString(professoresQuinta[4].getIdProfessor()));
        } catch (Exception e) {
            quintaHorarioE.setText("");
            quintaProfessorE.setText("");
            quintaDisciplinaE.setText("");
            quintaCodigoProfessorE.setText("");
        }

        try {
            quintaHorarioF.setText(horariosQuinta[5].getHoraInicio());
            quintaProfessorF.setText(professoresQuinta[5].getNomeProfessor());
            quintaDisciplinaF.setText(disciplinasQuinta[5].getNomeDisciplina());
            quintaCodigoProfessorF.setText(Integer.toString(professoresQuinta[5].getIdProfessor()));
        } catch (Exception e) {
            quintaHorarioF.setText("");
            quintaProfessorF.setText("");
            quintaDisciplinaF.setText("");
            quintaCodigoProfessorF.setText("");
        }
        try {
            sextaHorarioA.setText(horariosSexta[0].getHoraInicio());
            sextaProfessorA.setText(professoresSexta[0].getNomeProfessor());
            sextaDisciplinaA.setText(disciplinasSexta[0].getNomeDisciplina());
            sextaCodigoProfessorA.setText(Integer.toString(professoresSexta[0].getIdProfessor()));
        } catch (Exception e) {
            sextaHorarioA.setText("");
            sextaProfessorA.setText("");
            sextaDisciplinaA.setText("");
            sextaCodigoProfessorA.setText("");
        }

        try {
            sextaHorarioB.setText(horariosSexta[1].getHoraInicio());
            sextaProfessorB.setText(professoresSexta[1].getNomeProfessor());
            sextaDisciplinaB.setText(disciplinasSexta[1].getNomeDisciplina());
            sextaCodigoProfessorB.setText(Integer.toString(professoresSexta[1].getIdProfessor()));
        } catch (Exception e) {
            sextaHorarioB.setText("");
            sextaProfessorB.setText("");
            sextaDisciplinaB.setText("");
            sextaCodigoProfessorB.setText("");
        }

        try {
            sextaHorarioC.setText(horariosSexta[2].getHoraInicio());
            sextaProfessorC.setText(professoresSexta[2].getNomeProfessor());
            sextaDisciplinaC.setText(disciplinasSexta[2].getNomeDisciplina());
            sextaCodigoProfessorC.setText(Integer.toString(professoresSexta[2].getIdProfessor()));
        } catch (Exception e) {
            sextaHorarioC.setText("");
            sextaProfessorC.setText("");
            sextaDisciplinaC.setText("");
            sextaCodigoProfessorC.setText("");
        }

        try {
            sextaHorarioD.setText(horariosSexta[3].getHoraInicio());
            sextaProfessorD.setText(professoresSexta[3].getNomeProfessor());
            sextaDisciplinaD.setText(disciplinasSexta[3].getNomeDisciplina());
            sextaCodigoProfessorD.setText(Integer.toString(professoresSexta[3].getIdProfessor()));
        } catch (Exception e) {
            sextaHorarioD.setText("");
            sextaProfessorD.setText("");
            sextaDisciplinaD.setText("");
            sextaCodigoProfessorD.setText("");
        }

        try {
            sextaHorarioE.setText(horariosSexta[4].getHoraInicio());
            sextaProfessorE.setText(professoresSexta[4].getNomeProfessor());
            sextaDisciplinaE.setText(disciplinasSexta[4].getNomeDisciplina());
            sextaCodigoProfessorE.setText(Integer.toString(professoresSexta[4].getIdProfessor()));
        } catch (Exception e) {
            sextaHorarioE.setText("");
            sextaProfessorE.setText("");
            sextaDisciplinaE.setText("");
            sextaCodigoProfessorE.setText("");
        }

        try {
            sextaHorarioF.setText(horariosSexta[5].getHoraInicio());
            sextaProfessorF.setText(professoresSexta[5].getNomeProfessor());
            sextaDisciplinaF.setText(disciplinasSexta[5].getNomeDisciplina());
            sextaCodigoProfessorF.setText(Integer.toString(professoresSexta[5].getIdProfessor()));
        } catch (Exception e) {
            sextaHorarioF.setText("");
            sextaProfessorF.setText("");
            sextaDisciplinaF.setText("");
            sextaCodigoProfessorF.setText("");
        }
        try {
            sabadoHorarioA.setText(horariosSabado[0].getHoraInicio());
            sabadoProfessorA.setText(professoresSabado[0].getNomeProfessor());
            sabadoDisciplinaA.setText(disciplinasSabado[0].getNomeDisciplina());
            sabadoCodigoProfessorA.setText(Integer.toString(professoresSabado[0].getIdProfessor()));
        } catch (Exception e) {
            sabadoHorarioA.setText("");
            sabadoProfessorA.setText("");
            sabadoDisciplinaA.setText("");
            sabadoCodigoProfessorA.setText("");
        }

        try {
            sabadoHorarioB.setText(horariosSabado[1].getHoraInicio());
            sabadoProfessorB.setText(professoresSabado[1].getNomeProfessor());
            sabadoDisciplinaB.setText(disciplinasSabado[1].getNomeDisciplina());
            sabadoCodigoProfessorB.setText(Integer.toString(professoresSabado[1].getIdProfessor()));
        } catch (Exception e) {
            sabadoHorarioB.setText("");
            sabadoProfessorB.setText("");
            sabadoDisciplinaB.setText("");
            sabadoCodigoProfessorB.setText("");
        }

        try {
            sabadoHorarioC.setText(horariosSabado[2].getHoraInicio());
            sabadoProfessorC.setText(professoresSabado[2].getNomeProfessor());
            sabadoDisciplinaC.setText(disciplinasSabado[2].getNomeDisciplina());
            sabadoCodigoProfessorC.setText(Integer.toString(professoresSabado[2].getIdProfessor()));
        } catch (Exception e) {
            sabadoHorarioC.setText("");
            sabadoProfessorC.setText("");
            sabadoDisciplinaC.setText("");
            sabadoCodigoProfessorC.setText("");
        }

        try {
            sabadoHorarioD.setText(horariosSabado[3].getHoraInicio());
            sabadoProfessorD.setText(professoresSabado[3].getNomeProfessor());
            sabadoDisciplinaD.setText(disciplinasSabado[3].getNomeDisciplina());
            sabadoCodigoProfessorD.setText(Integer.toString(professoresSabado[3].getIdProfessor()));
        } catch (Exception e) {
            sabadoHorarioD.setText("");
            sabadoProfessorD.setText("");
            sabadoDisciplinaD.setText("");
            sabadoCodigoProfessorD.setText("");
        }

        try {
            sabadoHorarioE.setText(horariosSabado[4].getHoraInicio());
            sabadoProfessorE.setText(professoresSabado[4].getNomeProfessor());
            sabadoDisciplinaE.setText(disciplinasSabado[4].getNomeDisciplina());
            sabadoCodigoProfessorE.setText(Integer.toString(professoresSabado[4].getIdProfessor()));
        } catch (Exception e) {
            sabadoHorarioE.setText("");
            sabadoProfessorE.setText("");
            sabadoDisciplinaE.setText("");
            sabadoCodigoProfessorE.setText("");
        }

        try {
            sabadoHorarioF.setText(horariosSabado[5].getHoraInicio());
            sabadoProfessorF.setText(professoresSabado[5].getNomeProfessor());
            sabadoDisciplinaF.setText(disciplinasSabado[5].getNomeDisciplina());
            sabadoCodigoProfessorF.setText(Integer.toString(professoresSabado[5].getIdProfessor()));
        } catch (Exception e) {
            sabadoHorarioF.setText("");
            sabadoProfessorF.setText("");
            sabadoDisciplinaF.setText("");
            sabadoCodigoProfessorF.setText("");
        }

    }
    // Método para verificar se existe algum campo preenchido pela metade
    public boolean verificarHorariosVazios() {
        // Somente quando essa variável for igual a quantidade de campos de verificação o método retornará true
        // Pois somente quando retornar true, não haverá linhas do quadro de horários incompleta
        int verificarVazios = 0;
        if (Util.chkVazioCamposHorarios(segundaHorarioA.getText(), segundaProfessorA.getText(), segundaDisciplinaA.getText(), "Segunda-Feira", "A")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(segundaHorarioB.getText(), segundaProfessorB.getText(), segundaDisciplinaB.getText(), "Segunda-Feira", "B")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(segundaHorarioC.getText(), segundaProfessorC.getText(), segundaDisciplinaC.getText(), "Segunda-Feira", "C")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(segundaHorarioD.getText(), segundaProfessorD.getText(), segundaDisciplinaD.getText(), "Segunda-Feira", "D")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(segundaHorarioE.getText(), segundaProfessorE.getText(), segundaDisciplinaE.getText(), "Segunda-Feira", "E")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(segundaHorarioF.getText(), segundaProfessorF.getText(), segundaDisciplinaF.getText(), "Segunda-Feira", "F")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(tercaHorarioA.getText(), tercaProfessorA.getText(), tercaDisciplinaA.getText(), "Terca-Feira", "A")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(tercaHorarioB.getText(), tercaProfessorB.getText(), tercaDisciplinaB.getText(), "Terca-Feira", "B")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(tercaHorarioC.getText(), tercaProfessorC.getText(), tercaDisciplinaC.getText(), "Terca-Feira", "C")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(tercaHorarioD.getText(), tercaProfessorD.getText(), tercaDisciplinaD.getText(), "Terca-Feira", "D")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(tercaHorarioE.getText(), tercaProfessorE.getText(), tercaDisciplinaE.getText(), "Terca-Feira", "E")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(tercaHorarioF.getText(), tercaProfessorF.getText(), tercaDisciplinaF.getText(), "Terca-Feira", "F")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(quartaHorarioA.getText(), quartaProfessorA.getText(), quartaDisciplinaA.getText(), "Quarta-Feira", "A")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(quartaHorarioB.getText(), quartaProfessorB.getText(), quartaDisciplinaB.getText(), "Quarta-Feira", "B")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(quartaHorarioC.getText(), quartaProfessorC.getText(), quartaDisciplinaC.getText(), "Quarta-Feira", "C")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(quartaHorarioD.getText(), quartaProfessorD.getText(), quartaDisciplinaD.getText(), "Quarta-Feira", "D")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(quartaHorarioE.getText(), quartaProfessorE.getText(), quartaDisciplinaE.getText(), "Quarta-Feira", "E")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(quartaHorarioF.getText(), quartaProfessorF.getText(), quartaDisciplinaF.getText(), "Quarta-Feira", "F")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(quintaHorarioA.getText(), quintaProfessorA.getText(), quintaDisciplinaA.getText(), "Quinta-Feira", "A")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(quintaHorarioB.getText(), quintaProfessorB.getText(), quintaDisciplinaB.getText(), "Quinta-Feira", "B")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(quintaHorarioC.getText(), quintaProfessorC.getText(), quintaDisciplinaC.getText(), "Quinta-Feira", "C")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(quintaHorarioD.getText(), quintaProfessorD.getText(), quintaDisciplinaD.getText(), "Quinta-Feira", "D")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(quintaHorarioE.getText(), quintaProfessorE.getText(), quintaDisciplinaE.getText(), "Quinta-Feira", "E")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(quintaHorarioF.getText(), quintaProfessorF.getText(), quintaDisciplinaF.getText(), "Quinta-Feira", "F")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(sextaHorarioA.getText(), sextaProfessorA.getText(), sextaDisciplinaA.getText(), "Sexta-Feira", "A")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(sextaHorarioB.getText(), sextaProfessorB.getText(), sextaDisciplinaB.getText(), "Sexta-Feira", "B")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(sextaHorarioC.getText(), sextaProfessorC.getText(), sextaDisciplinaC.getText(), "Sexta-Feira", "C")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(sextaHorarioD.getText(), sextaProfessorD.getText(), sextaDisciplinaD.getText(), "Sexta-Feira", "D")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(sextaHorarioE.getText(), sextaProfessorE.getText(), sextaDisciplinaE.getText(), "Sexta-Feira", "E")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(sextaHorarioF.getText(), sextaProfessorF.getText(), sextaDisciplinaF.getText(), "Sexta-Feira", "F")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(sabadoHorarioA.getText(), sabadoProfessorA.getText(), sabadoDisciplinaA.getText(), "Sábado", "A")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(sabadoHorarioB.getText(), sabadoProfessorB.getText(), sabadoDisciplinaB.getText(), "Sábado", "B")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(sabadoHorarioC.getText(), sabadoProfessorC.getText(), sabadoDisciplinaC.getText(), "Sábado", "C")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(sabadoHorarioD.getText(), sabadoProfessorD.getText(), sabadoDisciplinaD.getText(), "Sábado", "D")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(sabadoHorarioE.getText(), sabadoProfessorE.getText(), sabadoDisciplinaE.getText(), "Sábado", "E")) {
            verificarVazios++;
        }
        if (Util.chkVazioCamposHorarios(sabadoHorarioF.getText(), sabadoProfessorF.getText(), sabadoDisciplinaF.getText(), "Sábado", "F")) {
            verificarVazios++;
        }

        if (verificarVazios == 36) {
            return true;
        } else {
            return false;
        }

    }
    
    //Método para percorrer o choque de horários em todos os campos
    public boolean verificarChoqueHorarios() {
        int contagemHorariosSemChoque = 0;
        try {
            if (choqueHorarioProfessor(professoresSegunda[0].getIdProfessor(), horariosSegunda[0].getIdHorario(), "Segunda-Feira", "A")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresSegunda[1].getIdProfessor(), horariosSegunda[1].getIdHorario(), "Segunda-Feira", "B")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresSegunda[2].getIdProfessor(), horariosSegunda[2].getIdHorario(), "Segunda-Feira", "C")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresSegunda[3].getIdProfessor(), horariosSegunda[3].getIdHorario(), "Segunda-Feira", "D")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresSegunda[4].getIdProfessor(), horariosSegunda[4].getIdHorario(), "Segunda-Feira", "E")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresSegunda[5].getIdProfessor(), horariosSegunda[5].getIdHorario(), "Segunda-Feira", "F")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresTerca[0].getIdProfessor(), horariosTerca[0].getIdHorario(), "Terça-Feira", "A")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresTerca[1].getIdProfessor(), horariosTerca[1].getIdHorario(), "Terça-Feira", "B")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresTerca[2].getIdProfessor(), horariosTerca[2].getIdHorario(), "Terça-Feira", "C")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresTerca[3].getIdProfessor(), horariosTerca[3].getIdHorario(), "Terça-Feira", "D")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresTerca[4].getIdProfessor(), horariosTerca[4].getIdHorario(), "Terça-Feira", "E")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresTerca[5].getIdProfessor(), horariosTerca[5].getIdHorario(), "Terça-Feira", "F")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresQuarta[0].getIdProfessor(), horariosQuarta[0].getIdHorario(), "Quarta-Feira", "A")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresQuarta[1].getIdProfessor(), horariosQuarta[1].getIdHorario(), "Quarta-Feira", "B")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresQuarta[2].getIdProfessor(), horariosQuarta[2].getIdHorario(), "Quarta-Feira", "C")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresQuarta[3].getIdProfessor(), horariosQuarta[3].getIdHorario(), "Quarta-Feira", "D")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresQuarta[4].getIdProfessor(), horariosQuarta[4].getIdHorario(), "Quarta-Feira", "E")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresQuarta[5].getIdProfessor(), horariosQuarta[5].getIdHorario(), "Quarta-Feira", "F")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresQuinta[0].getIdProfessor(), horariosQuinta[0].getIdHorario(), "Quinta-Feira", "A")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresQuinta[1].getIdProfessor(), horariosQuinta[1].getIdHorario(), "Quinta-Feira", "B")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresQuinta[2].getIdProfessor(), horariosQuinta[2].getIdHorario(), "Quinta-Feira", "C")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresQuinta[3].getIdProfessor(), horariosQuinta[3].getIdHorario(), "Quinta-Feira", "D")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresQuinta[4].getIdProfessor(), horariosQuinta[4].getIdHorario(), "Quinta-Feira", "E")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresQuinta[5].getIdProfessor(), horariosQuinta[5].getIdHorario(), "Quinta-Feira", "F")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresSexta[0].getIdProfessor(), horariosSexta[0].getIdHorario(), "Sexta-Feira", "A")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresSexta[1].getIdProfessor(), horariosSexta[1].getIdHorario(), "Sexta-Feira", "B")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresSexta[2].getIdProfessor(), horariosSexta[2].getIdHorario(), "Sexta-Feira", "C")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresSexta[3].getIdProfessor(), horariosSexta[3].getIdHorario(), "Sexta-Feira", "D")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresSexta[4].getIdProfessor(), horariosSexta[4].getIdHorario(), "Sexta-Feira", "E")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresSexta[5].getIdProfessor(), horariosSexta[5].getIdHorario(), "Sexta-Feira", "F")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresSabado[0].getIdProfessor(), horariosSabado[0].getIdHorario(), "Sábado", "A")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresSabado[1].getIdProfessor(), horariosSabado[1].getIdHorario(), "Sábado", "B")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresSabado[2].getIdProfessor(), horariosSabado[2].getIdHorario(), "Sábado", "C")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresSabado[3].getIdProfessor(), horariosSabado[3].getIdHorario(), "Sábado", "D")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresSabado[4].getIdProfessor(), horariosSabado[4].getIdHorario(), "Sábado", "E")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }
        try {
            if (choqueHorarioProfessor(professoresSabado[5].getIdProfessor(), horariosSabado[5].getIdHorario(), "Sábado", "F")) {
                contagemHorariosSemChoque++;
            }
        } catch (Exception e) {
        }

        if (contagemHorariosSemChoque == 0) {
            return false;
        } else {
            return true;
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfNomeCurso = new javax.swing.JTextField();
        jlNome2 = new javax.swing.JLabel();
        tfGradeCurricular = new javax.swing.JTextField();
        jlNome3 = new javax.swing.JLabel();
        tfNomeSemestre = new javax.swing.JTextField();
        btCurso = new javax.swing.JButton();
        btSemestre = new javax.swing.JButton();
        btVoltar = new javax.swing.JButton();
        btLimpar = new javax.swing.JButton();
        btPesquisar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        btGrade = new javax.swing.JButton();
        jcTurno = new javax.swing.JComboBox();
        jlNome4 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        segundaDisciplinaC = new javax.swing.JTextField();
        btCurso1 = new javax.swing.JButton();
        segundaHorarioB = new javax.swing.JTextField();
        btCurso2 = new javax.swing.JButton();
        segundaHorarioC = new javax.swing.JTextField();
        btCurso3 = new javax.swing.JButton();
        btCurso4 = new javax.swing.JButton();
        segundaHorarioF = new javax.swing.JTextField();
        segundaHorarioD = new javax.swing.JTextField();
        btCurso5 = new javax.swing.JButton();
        segundaHorarioE = new javax.swing.JTextField();
        btCurso6 = new javax.swing.JButton();
        segundaCodigoProfessorB = new javax.swing.JTextField();
        btCurso7 = new javax.swing.JButton();
        segundaProfessorB = new javax.swing.JTextField();
        btCurso8 = new javax.swing.JButton();
        segundaDisciplinaD = new javax.swing.JTextField();
        btCurso9 = new javax.swing.JButton();
        segundaDisciplinaE = new javax.swing.JTextField();
        btCurso10 = new javax.swing.JButton();
        segundaDisciplinaF = new javax.swing.JTextField();
        btCurso11 = new javax.swing.JButton();
        segundaDisciplinaB = new javax.swing.JTextField();
        btCurso12 = new javax.swing.JButton();
        segundaDisciplinaA = new javax.swing.JTextField();
        btCurso13 = new javax.swing.JButton();
        segundaProfessorC = new javax.swing.JTextField();
        btCurso14 = new javax.swing.JButton();
        segundaProfessorD = new javax.swing.JTextField();
        btCurso15 = new javax.swing.JButton();
        segundaProfessorE = new javax.swing.JTextField();
        btCurso16 = new javax.swing.JButton();
        segundaProfessorF = new javax.swing.JTextField();
        btCurso17 = new javax.swing.JButton();
        segundaProfessorA = new javax.swing.JTextField();
        btCurso18 = new javax.swing.JButton();
        segundaHorarioA = new javax.swing.JTextField();
        segundaCodigoProfessorC = new javax.swing.JTextField();
        segundaCodigoProfessorD = new javax.swing.JTextField();
        segundaCodigoProfessorE = new javax.swing.JTextField();
        segundaCodigoProfessorF = new javax.swing.JTextField();
        segundaCodigoProfessorA = new javax.swing.JTextField();
        btCurso109 = new javax.swing.JButton();
        btCurso110 = new javax.swing.JButton();
        btCurso111 = new javax.swing.JButton();
        btCurso112 = new javax.swing.JButton();
        btCurso113 = new javax.swing.JButton();
        btCurso114 = new javax.swing.JButton();
        jlquadro = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        tercaDisciplinaC = new javax.swing.JTextField();
        tercaHorarioC = new javax.swing.JTextField();
        btCurso19 = new javax.swing.JButton();
        tercaDisciplinaA = new javax.swing.JTextField();
        btCurso20 = new javax.swing.JButton();
        btCurso21 = new javax.swing.JButton();
        tercaDisciplinaD = new javax.swing.JTextField();
        btCurso22 = new javax.swing.JButton();
        tercaCodigoProfessorA = new javax.swing.JTextField();
        btCurso23 = new javax.swing.JButton();
        tercaProfessorD = new javax.swing.JTextField();
        tercaHorarioB = new javax.swing.JTextField();
        btCurso24 = new javax.swing.JButton();
        tercaCodigoProfessorE = new javax.swing.JTextField();
        btCurso25 = new javax.swing.JButton();
        btCurso26 = new javax.swing.JButton();
        tercaCodigoProfessorB = new javax.swing.JTextField();
        btCurso27 = new javax.swing.JButton();
        btCurso28 = new javax.swing.JButton();
        btCurso29 = new javax.swing.JButton();
        tercaProfessorA = new javax.swing.JTextField();
        tercaHorarioA = new javax.swing.JTextField();
        tercaHorarioD = new javax.swing.JTextField();
        tercaCodigoProfessorF = new javax.swing.JTextField();
        tercaProfessorF = new javax.swing.JTextField();
        tercaCodigoProfessorC = new javax.swing.JTextField();
        btCurso30 = new javax.swing.JButton();
        btCurso31 = new javax.swing.JButton();
        btCurso32 = new javax.swing.JButton();
        tercaProfessorB = new javax.swing.JTextField();
        tercaHorarioE = new javax.swing.JTextField();
        btCurso33 = new javax.swing.JButton();
        tercaProfessorC = new javax.swing.JTextField();
        tercaHorarioF = new javax.swing.JTextField();
        tercaDisciplinaB = new javax.swing.JTextField();
        tercaDisciplinaF = new javax.swing.JTextField();
        btCurso34 = new javax.swing.JButton();
        btCurso35 = new javax.swing.JButton();
        btCurso36 = new javax.swing.JButton();
        tercaDisciplinaE = new javax.swing.JTextField();
        tercaCodigoProfessorD = new javax.swing.JTextField();
        tercaProfessorE = new javax.swing.JTextField();
        btCurso115 = new javax.swing.JButton();
        btCurso116 = new javax.swing.JButton();
        btCurso117 = new javax.swing.JButton();
        btCurso118 = new javax.swing.JButton();
        btCurso119 = new javax.swing.JButton();
        btCurso120 = new javax.swing.JButton();
        jlquadro1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        quartaDisciplinaC = new javax.swing.JTextField();
        quartaHorarioC = new javax.swing.JTextField();
        btCurso37 = new javax.swing.JButton();
        quartaDisciplinaA = new javax.swing.JTextField();
        btCurso38 = new javax.swing.JButton();
        btCurso39 = new javax.swing.JButton();
        quartaDisciplinaD = new javax.swing.JTextField();
        btCurso40 = new javax.swing.JButton();
        quartaCodigoProfessorA = new javax.swing.JTextField();
        btCurso41 = new javax.swing.JButton();
        quartaProfessorD = new javax.swing.JTextField();
        quartaHorarioB = new javax.swing.JTextField();
        btCurso42 = new javax.swing.JButton();
        quartaCodigoProfessorE = new javax.swing.JTextField();
        btCurso43 = new javax.swing.JButton();
        btCurso44 = new javax.swing.JButton();
        quartaCodigoProfessorB = new javax.swing.JTextField();
        btCurso45 = new javax.swing.JButton();
        btCurso46 = new javax.swing.JButton();
        btCurso47 = new javax.swing.JButton();
        quartaProfessorA = new javax.swing.JTextField();
        quartaHorarioA = new javax.swing.JTextField();
        quartaHorarioD = new javax.swing.JTextField();
        quartaCodigoProfessorF = new javax.swing.JTextField();
        quartaProfessorF = new javax.swing.JTextField();
        quartaCodigoProfessorC = new javax.swing.JTextField();
        btCurso48 = new javax.swing.JButton();
        btCurso49 = new javax.swing.JButton();
        btCurso50 = new javax.swing.JButton();
        quartaProfessorB = new javax.swing.JTextField();
        quartaHorarioE = new javax.swing.JTextField();
        btCurso51 = new javax.swing.JButton();
        quartaProfessorC = new javax.swing.JTextField();
        quartaHorarioF = new javax.swing.JTextField();
        quartaDisciplinaB = new javax.swing.JTextField();
        quartaDisciplinaF = new javax.swing.JTextField();
        btCurso52 = new javax.swing.JButton();
        btCurso53 = new javax.swing.JButton();
        btCurso54 = new javax.swing.JButton();
        quartaDisciplinaE = new javax.swing.JTextField();
        quartaCodigoProfessorD = new javax.swing.JTextField();
        quartaProfessorE = new javax.swing.JTextField();
        btCurso121 = new javax.swing.JButton();
        btCurso122 = new javax.swing.JButton();
        btCurso123 = new javax.swing.JButton();
        btCurso124 = new javax.swing.JButton();
        btCurso125 = new javax.swing.JButton();
        btCurso126 = new javax.swing.JButton();
        jlquadro2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        quintaDisciplinaC = new javax.swing.JTextField();
        quintaHorarioC = new javax.swing.JTextField();
        btCurso55 = new javax.swing.JButton();
        quintaDisciplinaA = new javax.swing.JTextField();
        btCurso56 = new javax.swing.JButton();
        btCurso57 = new javax.swing.JButton();
        quintaDisciplinaD = new javax.swing.JTextField();
        btCurso58 = new javax.swing.JButton();
        quintaCodigoProfessorA = new javax.swing.JTextField();
        btCurso59 = new javax.swing.JButton();
        quintaProfessorD = new javax.swing.JTextField();
        quintaHorarioB = new javax.swing.JTextField();
        btCurso60 = new javax.swing.JButton();
        quintaCodigoProfessorE = new javax.swing.JTextField();
        btCurso61 = new javax.swing.JButton();
        btCurso62 = new javax.swing.JButton();
        quintaCodigoProfessorB = new javax.swing.JTextField();
        btCurso63 = new javax.swing.JButton();
        btCurso64 = new javax.swing.JButton();
        btCurso65 = new javax.swing.JButton();
        quintaProfessorA = new javax.swing.JTextField();
        quintaHorarioA = new javax.swing.JTextField();
        quintaHorarioD = new javax.swing.JTextField();
        quintaCodigoProfessorF = new javax.swing.JTextField();
        quintaProfessorF = new javax.swing.JTextField();
        quintaCodigoProfessorC = new javax.swing.JTextField();
        btCurso66 = new javax.swing.JButton();
        btCurso67 = new javax.swing.JButton();
        btCurso68 = new javax.swing.JButton();
        quintaProfessorB = new javax.swing.JTextField();
        quintaHorarioE = new javax.swing.JTextField();
        btCurso69 = new javax.swing.JButton();
        quintaProfessorC = new javax.swing.JTextField();
        quintaHorarioF = new javax.swing.JTextField();
        quintaDisciplinaB = new javax.swing.JTextField();
        quintaDisciplinaF = new javax.swing.JTextField();
        btCurso70 = new javax.swing.JButton();
        btCurso71 = new javax.swing.JButton();
        btCurso72 = new javax.swing.JButton();
        quintaDisciplinaE = new javax.swing.JTextField();
        quintaCodigoProfessorD = new javax.swing.JTextField();
        quintaProfessorE = new javax.swing.JTextField();
        btCurso127 = new javax.swing.JButton();
        btCurso128 = new javax.swing.JButton();
        btCurso129 = new javax.swing.JButton();
        btCurso130 = new javax.swing.JButton();
        btCurso131 = new javax.swing.JButton();
        btCurso132 = new javax.swing.JButton();
        jlquadro3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        sextaDisciplinaC = new javax.swing.JTextField();
        sextaHorarioC = new javax.swing.JTextField();
        btCurso73 = new javax.swing.JButton();
        sextaDisciplinaA = new javax.swing.JTextField();
        btCurso74 = new javax.swing.JButton();
        btCurso75 = new javax.swing.JButton();
        sextaDisciplinaD = new javax.swing.JTextField();
        btCurso76 = new javax.swing.JButton();
        sextaCodigoProfessorA = new javax.swing.JTextField();
        btCurso77 = new javax.swing.JButton();
        sextaProfessorD = new javax.swing.JTextField();
        sextaHorarioB = new javax.swing.JTextField();
        btCurso78 = new javax.swing.JButton();
        sextaCodigoProfessorE = new javax.swing.JTextField();
        btCurso79 = new javax.swing.JButton();
        btCurso80 = new javax.swing.JButton();
        sextaCodigoProfessorB = new javax.swing.JTextField();
        btCurso81 = new javax.swing.JButton();
        btCurso82 = new javax.swing.JButton();
        btCurso83 = new javax.swing.JButton();
        sextaProfessorA = new javax.swing.JTextField();
        sextaHorarioA = new javax.swing.JTextField();
        sextaHorarioD = new javax.swing.JTextField();
        sextaCodigoProfessorF = new javax.swing.JTextField();
        sextaProfessorF = new javax.swing.JTextField();
        sextaCodigoProfessorC = new javax.swing.JTextField();
        btCurso84 = new javax.swing.JButton();
        btCurso85 = new javax.swing.JButton();
        btCurso86 = new javax.swing.JButton();
        sextaProfessorB = new javax.swing.JTextField();
        sextaHorarioE = new javax.swing.JTextField();
        btCurso87 = new javax.swing.JButton();
        sextaProfessorC = new javax.swing.JTextField();
        sextaHorarioF = new javax.swing.JTextField();
        sextaDisciplinaB = new javax.swing.JTextField();
        sextaDisciplinaF = new javax.swing.JTextField();
        btCurso88 = new javax.swing.JButton();
        btCurso89 = new javax.swing.JButton();
        btCurso90 = new javax.swing.JButton();
        sextaDisciplinaE = new javax.swing.JTextField();
        sextaCodigoProfessorD = new javax.swing.JTextField();
        sextaProfessorE = new javax.swing.JTextField();
        btCurso133 = new javax.swing.JButton();
        btCurso134 = new javax.swing.JButton();
        btCurso135 = new javax.swing.JButton();
        btCurso136 = new javax.swing.JButton();
        btCurso137 = new javax.swing.JButton();
        btCurso138 = new javax.swing.JButton();
        jlquadro4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        sabadoDisciplinaC = new javax.swing.JTextField();
        sabadoHorarioC = new javax.swing.JTextField();
        btCurso91 = new javax.swing.JButton();
        sabadoDisciplinaA = new javax.swing.JTextField();
        btCurso92 = new javax.swing.JButton();
        btCurso93 = new javax.swing.JButton();
        sabadoDisciplinaD = new javax.swing.JTextField();
        btCurso94 = new javax.swing.JButton();
        sabadoCodigoProfessorA = new javax.swing.JTextField();
        btCurso95 = new javax.swing.JButton();
        sabadoProfessorD = new javax.swing.JTextField();
        sabadoHorarioB = new javax.swing.JTextField();
        btCurso96 = new javax.swing.JButton();
        sabadoCodigoProfessorE = new javax.swing.JTextField();
        btCurso97 = new javax.swing.JButton();
        btCurso98 = new javax.swing.JButton();
        sabadoCodigoProfessorB = new javax.swing.JTextField();
        btCurso99 = new javax.swing.JButton();
        btCurso100 = new javax.swing.JButton();
        btCurso101 = new javax.swing.JButton();
        sabadoProfessorA = new javax.swing.JTextField();
        sabadoHorarioA = new javax.swing.JTextField();
        sabadoHorarioD = new javax.swing.JTextField();
        sabadoCodigoProfessorF = new javax.swing.JTextField();
        sabadoProfessorF = new javax.swing.JTextField();
        sabadoCodigoProfessorC = new javax.swing.JTextField();
        btCurso102 = new javax.swing.JButton();
        btCurso103 = new javax.swing.JButton();
        btCurso104 = new javax.swing.JButton();
        sabadoProfessorB = new javax.swing.JTextField();
        sabadoHorarioE = new javax.swing.JTextField();
        btCurso105 = new javax.swing.JButton();
        sabadoProfessorC = new javax.swing.JTextField();
        sabadoHorarioF = new javax.swing.JTextField();
        sabadoDisciplinaB = new javax.swing.JTextField();
        sabadoDisciplinaF = new javax.swing.JTextField();
        btCurso106 = new javax.swing.JButton();
        btCurso107 = new javax.swing.JButton();
        btCurso108 = new javax.swing.JButton();
        sabadoDisciplinaE = new javax.swing.JTextField();
        sabadoCodigoProfessorD = new javax.swing.JTextField();
        sabadoProfessorE = new javax.swing.JTextField();
        btCurso139 = new javax.swing.JButton();
        btCurso140 = new javax.swing.JButton();
        btCurso141 = new javax.swing.JButton();
        btCurso142 = new javax.swing.JButton();
        btCurso143 = new javax.swing.JButton();
        btCurso144 = new javax.swing.JButton();
        jlquadro5 = new javax.swing.JLabel();
        jlNome6 = new javax.swing.JLabel();
        jlNome5 = new javax.swing.JLabel();
        jcAnoExercicio = new javax.swing.JComboBox();
        jlQuadroHorario = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 492));
        setModal(true);
        setUndecorated(true);
        getContentPane().setLayout(null);

        tfNomeCurso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tfNomeCurso.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tfNomeCurso.setEnabled(false);
        getContentPane().add(tfNomeCurso);
        tfNomeCurso.setBounds(120, 70, 360, 30);

        jlNome2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlNome2.setText("Turno.:");
        getContentPane().add(jlNome2);
        jlNome2.setBounds(350, 110, 70, 30);

        tfGradeCurricular.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tfGradeCurricular.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tfGradeCurricular.setEnabled(false);
        getContentPane().add(tfGradeCurricular);
        tfGradeCurricular.setBounds(580, 70, 170, 30);

        jlNome3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlNome3.setText("Semestre.:");
        getContentPane().add(jlNome3);
        jlNome3.setBounds(20, 110, 100, 30);

        tfNomeSemestre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tfNomeSemestre.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tfNomeSemestre.setEnabled(false);
        getContentPane().add(tfNomeSemestre);
        tfNomeSemestre.setBounds(120, 110, 200, 30);

        btCurso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso.setContentAreaFilled(false);
        btCurso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCursoActionPerformed(evt);
            }
        });
        getContentPane().add(btCurso);
        btCurso.setBounds(480, 70, 30, 30);

        btSemestre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btSemestre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btSemestre.setContentAreaFilled(false);
        btSemestre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btSemestre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSemestre.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btSemestre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSemestreActionPerformed(evt);
            }
        });
        getContentPane().add(btSemestre);
        btSemestre.setBounds(320, 110, 30, 29);

        btVoltar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/voltar.png"))); // NOI18N
        btVoltar.setText("Voltar");
        btVoltar.setContentAreaFilled(false);
        btVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btVoltar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btVoltar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltarActionPerformed(evt);
            }
        });
        getContentPane().add(btVoltar);
        btVoltar.setBounds(20, 420, 80, 60);

        btLimpar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar.png"))); // NOI18N
        btLimpar.setText("Limpar");
        btLimpar.setContentAreaFilled(false);
        btLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btLimpar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btLimpar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparActionPerformed(evt);
            }
        });
        getContentPane().add(btLimpar);
        btLimpar.setBounds(220, 420, 80, 60);

        btPesquisar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar.png"))); // NOI18N
        btPesquisar.setText("Pesquisar");
        btPesquisar.setContentAreaFilled(false);
        btPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btPesquisar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btPesquisar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });
        getContentPane().add(btPesquisar);
        btPesquisar.setBounds(380, 420, 100, 60);

        btExcluir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/excluir.png"))); // NOI18N
        btExcluir.setText("Excluir");
        btExcluir.setContentAreaFilled(false);
        btExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btExcluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btExcluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(btExcluir);
        btExcluir.setBounds(560, 420, 80, 60);

        btSalvar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/Salvar.png"))); // NOI18N
        btSalvar.setText("Salvar");
        btSalvar.setContentAreaFilled(false);
        btSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btSalvar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSalvar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btSalvar);
        btSalvar.setBounds(700, 420, 80, 60);

        btGrade.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btGrade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btGrade.setContentAreaFilled(false);
        btGrade.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btGrade.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btGrade.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btGrade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGradeActionPerformed(evt);
            }
        });
        getContentPane().add(btGrade);
        btGrade.setBounds(750, 70, 30, 30);

        jcTurno.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcTurno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-----", "Manhã", "Tarde", "Noite" }));
        jcTurno.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        jcTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcTurnoActionPerformed(evt);
            }
        });
        getContentPane().add(jcTurno);
        jcTurno.setBounds(420, 110, 90, 30);

        jlNome4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlNome4.setText("Curso.:");
        getContentPane().add(jlNome4);
        jlNome4.setBounds(50, 70, 70, 30);

        jTabbedPane1.setBackground(new java.awt.Color(244, 79, 3));
        jTabbedPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(750, 227));

        jPanel1.setLayout(null);

        segundaDisciplinaC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        segundaDisciplinaC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaC.setEnabled(false);
        jPanel1.add(segundaDisciplinaC);
        segundaDisciplinaC.setBounds(110, 100, 240, 20);

        btCurso1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso1.setContentAreaFilled(false);
        btCurso1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso1ActionPerformed(evt);
            }
        });
        jPanel1.add(btCurso1);
        btCurso1.setBounds(350, 100, 30, 20);

        segundaHorarioB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioB.setEnabled(false);
        jPanel1.add(segundaHorarioB);
        segundaHorarioB.setBounds(40, 70, 40, 19);

        btCurso2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso2.setContentAreaFilled(false);
        btCurso2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso2ActionPerformed(evt);
            }
        });
        jPanel1.add(btCurso2);
        btCurso2.setBounds(80, 70, 30, 20);

        segundaHorarioC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioC.setEnabled(false);
        jPanel1.add(segundaHorarioC);
        segundaHorarioC.setBounds(40, 100, 40, 20);

        btCurso3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso3.setContentAreaFilled(false);
        btCurso3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso3ActionPerformed(evt);
            }
        });
        jPanel1.add(btCurso3);
        btCurso3.setBounds(80, 100, 30, 20);

        btCurso4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso4.setContentAreaFilled(false);
        btCurso4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso4ActionPerformed(evt);
            }
        });
        jPanel1.add(btCurso4);
        btCurso4.setBounds(80, 190, 30, 20);

        segundaHorarioF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioF.setEnabled(false);
        jPanel1.add(segundaHorarioF);
        segundaHorarioF.setBounds(40, 190, 40, 20);

        segundaHorarioD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioD.setEnabled(false);
        jPanel1.add(segundaHorarioD);
        segundaHorarioD.setBounds(40, 130, 40, 20);

        btCurso5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso5.setContentAreaFilled(false);
        btCurso5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso5ActionPerformed(evt);
            }
        });
        jPanel1.add(btCurso5);
        btCurso5.setBounds(80, 130, 30, 20);

        segundaHorarioE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioE.setEnabled(false);
        jPanel1.add(segundaHorarioE);
        segundaHorarioE.setBounds(40, 160, 40, 20);

        btCurso6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso6.setContentAreaFilled(false);
        btCurso6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso6ActionPerformed(evt);
            }
        });
        jPanel1.add(btCurso6);
        btCurso6.setBounds(80, 160, 30, 20);

        segundaCodigoProfessorB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        segundaCodigoProfessorB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                segundaCodigoProfessorBFocusLost(evt);
            }
        });
        segundaCodigoProfessorB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorBActionPerformed(evt);
            }
        });
        segundaCodigoProfessorB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorBKeyTyped(evt);
            }
        });
        jPanel1.add(segundaCodigoProfessorB);
        segundaCodigoProfessorB.setBounds(390, 70, 30, 20);

        btCurso7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso7.setContentAreaFilled(false);
        btCurso7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso7ActionPerformed(evt);
            }
        });
        jPanel1.add(btCurso7);
        btCurso7.setBounds(80, 40, 30, 20);

        segundaProfessorB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        segundaProfessorB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorB.setEnabled(false);
        jPanel1.add(segundaProfessorB);
        segundaProfessorB.setBounds(430, 70, 240, 20);

        btCurso8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso8.setContentAreaFilled(false);
        btCurso8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso8ActionPerformed(evt);
            }
        });
        jPanel1.add(btCurso8);
        btCurso8.setBounds(670, 70, 30, 20);

        segundaDisciplinaD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        segundaDisciplinaD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaD.setEnabled(false);
        jPanel1.add(segundaDisciplinaD);
        segundaDisciplinaD.setBounds(110, 130, 240, 20);

        btCurso9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso9.setContentAreaFilled(false);
        btCurso9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso9ActionPerformed(evt);
            }
        });
        jPanel1.add(btCurso9);
        btCurso9.setBounds(350, 130, 30, 20);

        segundaDisciplinaE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        segundaDisciplinaE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaE.setEnabled(false);
        jPanel1.add(segundaDisciplinaE);
        segundaDisciplinaE.setBounds(110, 160, 240, 20);

        btCurso10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso10.setContentAreaFilled(false);
        btCurso10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso10ActionPerformed(evt);
            }
        });
        jPanel1.add(btCurso10);
        btCurso10.setBounds(350, 160, 30, 20);

        segundaDisciplinaF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        segundaDisciplinaF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaF.setEnabled(false);
        jPanel1.add(segundaDisciplinaF);
        segundaDisciplinaF.setBounds(110, 190, 240, 20);

        btCurso11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso11.setContentAreaFilled(false);
        btCurso11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso11ActionPerformed(evt);
            }
        });
        jPanel1.add(btCurso11);
        btCurso11.setBounds(350, 190, 30, 20);

        segundaDisciplinaB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        segundaDisciplinaB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaB.setEnabled(false);
        jPanel1.add(segundaDisciplinaB);
        segundaDisciplinaB.setBounds(110, 70, 240, 20);

        btCurso12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso12.setContentAreaFilled(false);
        btCurso12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso12ActionPerformed(evt);
            }
        });
        jPanel1.add(btCurso12);
        btCurso12.setBounds(350, 70, 30, 20);

        segundaDisciplinaA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        segundaDisciplinaA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaA.setEnabled(false);
        segundaDisciplinaA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaDisciplinaAActionPerformed(evt);
            }
        });
        jPanel1.add(segundaDisciplinaA);
        segundaDisciplinaA.setBounds(110, 40, 240, 20);

        btCurso13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso13.setContentAreaFilled(false);
        btCurso13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso13ActionPerformed(evt);
            }
        });
        jPanel1.add(btCurso13);
        btCurso13.setBounds(350, 40, 30, 20);

        segundaProfessorC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        segundaProfessorC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorC.setEnabled(false);
        jPanel1.add(segundaProfessorC);
        segundaProfessorC.setBounds(430, 100, 240, 20);

        btCurso14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso14.setContentAreaFilled(false);
        btCurso14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso14.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso14ActionPerformed(evt);
            }
        });
        jPanel1.add(btCurso14);
        btCurso14.setBounds(670, 100, 30, 20);

        segundaProfessorD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        segundaProfessorD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorD.setEnabled(false);
        jPanel1.add(segundaProfessorD);
        segundaProfessorD.setBounds(430, 130, 240, 20);

        btCurso15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso15.setContentAreaFilled(false);
        btCurso15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso15.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso15ActionPerformed(evt);
            }
        });
        jPanel1.add(btCurso15);
        btCurso15.setBounds(670, 130, 30, 20);

        segundaProfessorE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        segundaProfessorE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorE.setEnabled(false);
        jPanel1.add(segundaProfessorE);
        segundaProfessorE.setBounds(430, 160, 240, 20);

        btCurso16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso16.setContentAreaFilled(false);
        btCurso16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso16.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso16ActionPerformed(evt);
            }
        });
        jPanel1.add(btCurso16);
        btCurso16.setBounds(670, 160, 30, 20);

        segundaProfessorF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        segundaProfessorF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorF.setEnabled(false);
        jPanel1.add(segundaProfessorF);
        segundaProfessorF.setBounds(430, 190, 240, 20);

        btCurso17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso17.setContentAreaFilled(false);
        btCurso17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso17.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso17ActionPerformed(evt);
            }
        });
        jPanel1.add(btCurso17);
        btCurso17.setBounds(670, 190, 30, 20);

        segundaProfessorA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        segundaProfessorA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorA.setEnabled(false);
        jPanel1.add(segundaProfessorA);
        segundaProfessorA.setBounds(430, 40, 240, 20);

        btCurso18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso18.setContentAreaFilled(false);
        btCurso18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso18.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso18ActionPerformed(evt);
            }
        });
        jPanel1.add(btCurso18);
        btCurso18.setBounds(670, 40, 30, 20);

        segundaHorarioA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioA.setEnabled(false);
        jPanel1.add(segundaHorarioA);
        segundaHorarioA.setBounds(40, 40, 40, 20);

        segundaCodigoProfessorC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        segundaCodigoProfessorC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                segundaCodigoProfessorCFocusLost(evt);
            }
        });
        segundaCodigoProfessorC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorCActionPerformed(evt);
            }
        });
        segundaCodigoProfessorC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorCKeyTyped(evt);
            }
        });
        jPanel1.add(segundaCodigoProfessorC);
        segundaCodigoProfessorC.setBounds(390, 100, 30, 20);

        segundaCodigoProfessorD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        segundaCodigoProfessorD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorD.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                segundaCodigoProfessorDFocusLost(evt);
            }
        });
        segundaCodigoProfessorD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorDActionPerformed(evt);
            }
        });
        segundaCodigoProfessorD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorDKeyTyped(evt);
            }
        });
        jPanel1.add(segundaCodigoProfessorD);
        segundaCodigoProfessorD.setBounds(390, 130, 30, 20);

        segundaCodigoProfessorE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        segundaCodigoProfessorE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorE.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                segundaCodigoProfessorEFocusLost(evt);
            }
        });
        segundaCodigoProfessorE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorEActionPerformed(evt);
            }
        });
        segundaCodigoProfessorE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorEKeyTyped(evt);
            }
        });
        jPanel1.add(segundaCodigoProfessorE);
        segundaCodigoProfessorE.setBounds(390, 160, 30, 20);

        segundaCodigoProfessorF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        segundaCodigoProfessorF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                segundaCodigoProfessorFFocusLost(evt);
            }
        });
        segundaCodigoProfessorF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorFActionPerformed(evt);
            }
        });
        segundaCodigoProfessorF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorFKeyTyped(evt);
            }
        });
        jPanel1.add(segundaCodigoProfessorF);
        segundaCodigoProfessorF.setBounds(390, 190, 30, 20);

        segundaCodigoProfessorA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        segundaCodigoProfessorA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                segundaCodigoProfessorAFocusLost(evt);
            }
        });
        segundaCodigoProfessorA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorAActionPerformed(evt);
            }
        });
        segundaCodigoProfessorA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorAKeyTyped(evt);
            }
        });
        jPanel1.add(segundaCodigoProfessorA);
        segundaCodigoProfessorA.setBounds(390, 40, 30, 20);

        btCurso109.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso109.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso109.setContentAreaFilled(false);
        btCurso109.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso109.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso109.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso109.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso109ActionPerformed(evt);
            }
        });
        jPanel1.add(btCurso109);
        btCurso109.setBounds(710, 70, 30, 20);

        btCurso110.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso110.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso110.setContentAreaFilled(false);
        btCurso110.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso110.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso110.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso110.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso110ActionPerformed(evt);
            }
        });
        jPanel1.add(btCurso110);
        btCurso110.setBounds(710, 100, 30, 20);

        btCurso111.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso111.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso111.setContentAreaFilled(false);
        btCurso111.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso111.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso111.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso111.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso111ActionPerformed(evt);
            }
        });
        jPanel1.add(btCurso111);
        btCurso111.setBounds(710, 130, 30, 20);

        btCurso112.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso112.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso112.setContentAreaFilled(false);
        btCurso112.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso112.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso112.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso112.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso112ActionPerformed(evt);
            }
        });
        jPanel1.add(btCurso112);
        btCurso112.setBounds(710, 160, 30, 20);

        btCurso113.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso113.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso113.setContentAreaFilled(false);
        btCurso113.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso113.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso113.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso113.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso113ActionPerformed(evt);
            }
        });
        jPanel1.add(btCurso113);
        btCurso113.setBounds(710, 190, 30, 20);

        btCurso114.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso114.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso114.setContentAreaFilled(false);
        btCurso114.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso114.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso114.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso114.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso114ActionPerformed(evt);
            }
        });
        jPanel1.add(btCurso114);
        btCurso114.setBounds(710, 40, 30, 20);

        jlquadro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/quadro.png"))); // NOI18N
        jPanel1.add(jlquadro);
        jlquadro.setBounds(0, 0, 750, 230);

        jTabbedPane1.addTab("Segunda-Feira", jPanel1);

        jPanel2.setLayout(null);

        tercaDisciplinaC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tercaDisciplinaC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaDisciplinaC.setEnabled(false);
        jPanel2.add(tercaDisciplinaC);
        tercaDisciplinaC.setBounds(110, 100, 240, 20);

        tercaHorarioC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tercaHorarioC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaHorarioC.setEnabled(false);
        jPanel2.add(tercaHorarioC);
        tercaHorarioC.setBounds(40, 100, 40, 20);

        btCurso19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso19.setContentAreaFilled(false);
        btCurso19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso19.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso19ActionPerformed(evt);
            }
        });
        jPanel2.add(btCurso19);
        btCurso19.setBounds(670, 130, 30, 20);

        tercaDisciplinaA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tercaDisciplinaA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaDisciplinaA.setEnabled(false);
        tercaDisciplinaA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tercaDisciplinaAActionPerformed(evt);
            }
        });
        jPanel2.add(tercaDisciplinaA);
        tercaDisciplinaA.setBounds(110, 40, 240, 20);

        btCurso20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso20.setContentAreaFilled(false);
        btCurso20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso20.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso20ActionPerformed(evt);
            }
        });
        jPanel2.add(btCurso20);
        btCurso20.setBounds(80, 130, 30, 20);

        btCurso21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso21.setContentAreaFilled(false);
        btCurso21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso21.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso21ActionPerformed(evt);
            }
        });
        jPanel2.add(btCurso21);
        btCurso21.setBounds(670, 70, 30, 20);

        tercaDisciplinaD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tercaDisciplinaD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaDisciplinaD.setEnabled(false);
        jPanel2.add(tercaDisciplinaD);
        tercaDisciplinaD.setBounds(110, 130, 240, 20);

        btCurso22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso22.setContentAreaFilled(false);
        btCurso22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso22.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso22.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso22ActionPerformed(evt);
            }
        });
        jPanel2.add(btCurso22);
        btCurso22.setBounds(350, 160, 30, 20);

        tercaCodigoProfessorA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tercaCodigoProfessorA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaCodigoProfessorA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tercaCodigoProfessorAFocusLost(evt);
            }
        });
        tercaCodigoProfessorA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tercaCodigoProfessorAActionPerformed(evt);
            }
        });
        tercaCodigoProfessorA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tercaCodigoProfessorAKeyTyped(evt);
            }
        });
        jPanel2.add(tercaCodigoProfessorA);
        tercaCodigoProfessorA.setBounds(390, 40, 30, 20);

        btCurso23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso23.setContentAreaFilled(false);
        btCurso23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso23.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso23ActionPerformed(evt);
            }
        });
        jPanel2.add(btCurso23);
        btCurso23.setBounds(350, 100, 30, 20);

        tercaProfessorD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tercaProfessorD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaProfessorD.setEnabled(false);
        jPanel2.add(tercaProfessorD);
        tercaProfessorD.setBounds(430, 130, 240, 20);

        tercaHorarioB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tercaHorarioB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaHorarioB.setEnabled(false);
        jPanel2.add(tercaHorarioB);
        tercaHorarioB.setBounds(40, 70, 40, 19);

        btCurso24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso24.setContentAreaFilled(false);
        btCurso24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso24.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso24ActionPerformed(evt);
            }
        });
        jPanel2.add(btCurso24);
        btCurso24.setBounds(80, 160, 30, 20);

        tercaCodigoProfessorE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tercaCodigoProfessorE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaCodigoProfessorE.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tercaCodigoProfessorEFocusLost(evt);
            }
        });
        tercaCodigoProfessorE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tercaCodigoProfessorEActionPerformed(evt);
            }
        });
        tercaCodigoProfessorE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tercaCodigoProfessorEKeyTyped(evt);
            }
        });
        jPanel2.add(tercaCodigoProfessorE);
        tercaCodigoProfessorE.setBounds(390, 160, 30, 20);

        btCurso25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso25.setContentAreaFilled(false);
        btCurso25.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso25.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso25ActionPerformed(evt);
            }
        });
        jPanel2.add(btCurso25);
        btCurso25.setBounds(350, 40, 30, 20);

        btCurso26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso26.setContentAreaFilled(false);
        btCurso26.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso26.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso26ActionPerformed(evt);
            }
        });
        jPanel2.add(btCurso26);
        btCurso26.setBounds(350, 70, 30, 20);

        tercaCodigoProfessorB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tercaCodigoProfessorB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaCodigoProfessorB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tercaCodigoProfessorBFocusLost(evt);
            }
        });
        tercaCodigoProfessorB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tercaCodigoProfessorBActionPerformed(evt);
            }
        });
        tercaCodigoProfessorB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tercaCodigoProfessorBKeyTyped(evt);
            }
        });
        jPanel2.add(tercaCodigoProfessorB);
        tercaCodigoProfessorB.setBounds(390, 70, 30, 20);

        btCurso27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso27.setContentAreaFilled(false);
        btCurso27.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso27.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso27.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso27ActionPerformed(evt);
            }
        });
        jPanel2.add(btCurso27);
        btCurso27.setBounds(80, 70, 30, 20);

        btCurso28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso28.setContentAreaFilled(false);
        btCurso28.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso28.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso28.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso28ActionPerformed(evt);
            }
        });
        jPanel2.add(btCurso28);
        btCurso28.setBounds(670, 190, 30, 20);

        btCurso29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso29.setContentAreaFilled(false);
        btCurso29.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso29.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso29.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso29ActionPerformed(evt);
            }
        });
        jPanel2.add(btCurso29);
        btCurso29.setBounds(80, 40, 30, 20);

        tercaProfessorA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tercaProfessorA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaProfessorA.setEnabled(false);
        jPanel2.add(tercaProfessorA);
        tercaProfessorA.setBounds(430, 40, 240, 20);

        tercaHorarioA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tercaHorarioA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaHorarioA.setEnabled(false);
        jPanel2.add(tercaHorarioA);
        tercaHorarioA.setBounds(40, 40, 40, 20);

        tercaHorarioD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tercaHorarioD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaHorarioD.setEnabled(false);
        jPanel2.add(tercaHorarioD);
        tercaHorarioD.setBounds(40, 130, 40, 20);

        tercaCodigoProfessorF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tercaCodigoProfessorF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaCodigoProfessorF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tercaCodigoProfessorFFocusLost(evt);
            }
        });
        tercaCodigoProfessorF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tercaCodigoProfessorFActionPerformed(evt);
            }
        });
        tercaCodigoProfessorF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tercaCodigoProfessorFKeyTyped(evt);
            }
        });
        jPanel2.add(tercaCodigoProfessorF);
        tercaCodigoProfessorF.setBounds(390, 190, 30, 20);

        tercaProfessorF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tercaProfessorF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaProfessorF.setEnabled(false);
        jPanel2.add(tercaProfessorF);
        tercaProfessorF.setBounds(430, 190, 240, 20);

        tercaCodigoProfessorC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tercaCodigoProfessorC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaCodigoProfessorC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tercaCodigoProfessorCFocusLost(evt);
            }
        });
        tercaCodigoProfessorC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tercaCodigoProfessorCActionPerformed(evt);
            }
        });
        tercaCodigoProfessorC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tercaCodigoProfessorCKeyTyped(evt);
            }
        });
        jPanel2.add(tercaCodigoProfessorC);
        tercaCodigoProfessorC.setBounds(390, 100, 30, 20);

        btCurso30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso30.setContentAreaFilled(false);
        btCurso30.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso30.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso30ActionPerformed(evt);
            }
        });
        jPanel2.add(btCurso30);
        btCurso30.setBounds(670, 40, 30, 20);

        btCurso31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso31.setContentAreaFilled(false);
        btCurso31.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso31.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso31.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso31ActionPerformed(evt);
            }
        });
        jPanel2.add(btCurso31);
        btCurso31.setBounds(80, 100, 30, 20);

        btCurso32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso32.setContentAreaFilled(false);
        btCurso32.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso32.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso32.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso32ActionPerformed(evt);
            }
        });
        jPanel2.add(btCurso32);
        btCurso32.setBounds(670, 160, 30, 20);

        tercaProfessorB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tercaProfessorB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaProfessorB.setEnabled(false);
        jPanel2.add(tercaProfessorB);
        tercaProfessorB.setBounds(430, 70, 240, 20);

        tercaHorarioE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tercaHorarioE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaHorarioE.setEnabled(false);
        jPanel2.add(tercaHorarioE);
        tercaHorarioE.setBounds(40, 160, 40, 20);

        btCurso33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso33.setContentAreaFilled(false);
        btCurso33.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso33.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso33.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso33ActionPerformed(evt);
            }
        });
        jPanel2.add(btCurso33);
        btCurso33.setBounds(80, 190, 30, 20);

        tercaProfessorC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tercaProfessorC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaProfessorC.setEnabled(false);
        jPanel2.add(tercaProfessorC);
        tercaProfessorC.setBounds(430, 100, 240, 20);

        tercaHorarioF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tercaHorarioF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaHorarioF.setEnabled(false);
        jPanel2.add(tercaHorarioF);
        tercaHorarioF.setBounds(40, 190, 40, 20);

        tercaDisciplinaB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tercaDisciplinaB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaDisciplinaB.setEnabled(false);
        jPanel2.add(tercaDisciplinaB);
        tercaDisciplinaB.setBounds(110, 70, 240, 20);

        tercaDisciplinaF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tercaDisciplinaF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaDisciplinaF.setEnabled(false);
        jPanel2.add(tercaDisciplinaF);
        tercaDisciplinaF.setBounds(110, 190, 240, 20);

        btCurso34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso34.setContentAreaFilled(false);
        btCurso34.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso34.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso34.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso34ActionPerformed(evt);
            }
        });
        jPanel2.add(btCurso34);
        btCurso34.setBounds(350, 130, 30, 20);

        btCurso35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso35.setContentAreaFilled(false);
        btCurso35.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso35.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso35.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso35ActionPerformed(evt);
            }
        });
        jPanel2.add(btCurso35);
        btCurso35.setBounds(350, 190, 30, 20);

        btCurso36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso36.setContentAreaFilled(false);
        btCurso36.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso36.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso36.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso36ActionPerformed(evt);
            }
        });
        jPanel2.add(btCurso36);
        btCurso36.setBounds(670, 100, 30, 20);

        tercaDisciplinaE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tercaDisciplinaE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaDisciplinaE.setEnabled(false);
        jPanel2.add(tercaDisciplinaE);
        tercaDisciplinaE.setBounds(110, 160, 240, 20);

        tercaCodigoProfessorD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tercaCodigoProfessorD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaCodigoProfessorD.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tercaCodigoProfessorDFocusLost(evt);
            }
        });
        tercaCodigoProfessorD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tercaCodigoProfessorDActionPerformed(evt);
            }
        });
        tercaCodigoProfessorD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tercaCodigoProfessorDKeyTyped(evt);
            }
        });
        jPanel2.add(tercaCodigoProfessorD);
        tercaCodigoProfessorD.setBounds(390, 130, 30, 20);

        tercaProfessorE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tercaProfessorE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaProfessorE.setEnabled(false);
        jPanel2.add(tercaProfessorE);
        tercaProfessorE.setBounds(430, 160, 240, 20);

        btCurso115.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso115.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso115.setContentAreaFilled(false);
        btCurso115.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso115.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso115.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso115.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso115ActionPerformed(evt);
            }
        });
        jPanel2.add(btCurso115);
        btCurso115.setBounds(710, 190, 30, 20);

        btCurso116.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso116.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso116.setContentAreaFilled(false);
        btCurso116.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso116.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso116.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso116.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso116ActionPerformed(evt);
            }
        });
        jPanel2.add(btCurso116);
        btCurso116.setBounds(710, 160, 30, 20);

        btCurso117.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso117.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso117.setContentAreaFilled(false);
        btCurso117.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso117.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso117.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso117.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso117ActionPerformed(evt);
            }
        });
        jPanel2.add(btCurso117);
        btCurso117.setBounds(710, 130, 30, 20);

        btCurso118.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso118.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso118.setContentAreaFilled(false);
        btCurso118.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso118.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso118.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso118.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso118ActionPerformed(evt);
            }
        });
        jPanel2.add(btCurso118);
        btCurso118.setBounds(710, 100, 30, 20);

        btCurso119.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso119.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso119.setContentAreaFilled(false);
        btCurso119.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso119.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso119.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso119.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso119ActionPerformed(evt);
            }
        });
        jPanel2.add(btCurso119);
        btCurso119.setBounds(710, 70, 30, 20);

        btCurso120.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso120.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso120.setContentAreaFilled(false);
        btCurso120.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso120.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso120.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso120.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso120ActionPerformed(evt);
            }
        });
        jPanel2.add(btCurso120);
        btCurso120.setBounds(710, 40, 30, 20);

        jlquadro1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/quadro.png"))); // NOI18N
        jPanel2.add(jlquadro1);
        jlquadro1.setBounds(0, 0, 750, 230);

        jTabbedPane1.addTab("Terça-Feira", jPanel2);

        jPanel3.setLayout(null);

        quartaDisciplinaC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quartaDisciplinaC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaDisciplinaC.setEnabled(false);
        jPanel3.add(quartaDisciplinaC);
        quartaDisciplinaC.setBounds(110, 100, 240, 20);

        quartaHorarioC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quartaHorarioC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaHorarioC.setEnabled(false);
        jPanel3.add(quartaHorarioC);
        quartaHorarioC.setBounds(40, 100, 40, 20);

        btCurso37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso37.setContentAreaFilled(false);
        btCurso37.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso37.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso37.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso37ActionPerformed(evt);
            }
        });
        jPanel3.add(btCurso37);
        btCurso37.setBounds(670, 130, 30, 20);

        quartaDisciplinaA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quartaDisciplinaA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaDisciplinaA.setEnabled(false);
        quartaDisciplinaA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quartaDisciplinaAActionPerformed(evt);
            }
        });
        jPanel3.add(quartaDisciplinaA);
        quartaDisciplinaA.setBounds(110, 40, 240, 20);

        btCurso38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso38.setContentAreaFilled(false);
        btCurso38.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso38.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso38.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso38ActionPerformed(evt);
            }
        });
        jPanel3.add(btCurso38);
        btCurso38.setBounds(80, 130, 30, 20);

        btCurso39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso39.setContentAreaFilled(false);
        btCurso39.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso39.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso39.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso39ActionPerformed(evt);
            }
        });
        jPanel3.add(btCurso39);
        btCurso39.setBounds(670, 70, 30, 20);

        quartaDisciplinaD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quartaDisciplinaD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaDisciplinaD.setEnabled(false);
        jPanel3.add(quartaDisciplinaD);
        quartaDisciplinaD.setBounds(110, 130, 240, 20);

        btCurso40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso40.setContentAreaFilled(false);
        btCurso40.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso40.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso40.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso40ActionPerformed(evt);
            }
        });
        jPanel3.add(btCurso40);
        btCurso40.setBounds(350, 160, 30, 20);

        quartaCodigoProfessorA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quartaCodigoProfessorA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaCodigoProfessorA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                quartaCodigoProfessorAFocusLost(evt);
            }
        });
        quartaCodigoProfessorA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quartaCodigoProfessorAActionPerformed(evt);
            }
        });
        quartaCodigoProfessorA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                quartaCodigoProfessorAKeyTyped(evt);
            }
        });
        jPanel3.add(quartaCodigoProfessorA);
        quartaCodigoProfessorA.setBounds(390, 40, 30, 20);

        btCurso41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso41.setContentAreaFilled(false);
        btCurso41.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso41.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso41.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso41ActionPerformed(evt);
            }
        });
        jPanel3.add(btCurso41);
        btCurso41.setBounds(350, 100, 30, 20);

        quartaProfessorD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quartaProfessorD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaProfessorD.setEnabled(false);
        jPanel3.add(quartaProfessorD);
        quartaProfessorD.setBounds(430, 130, 240, 20);

        quartaHorarioB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quartaHorarioB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaHorarioB.setEnabled(false);
        jPanel3.add(quartaHorarioB);
        quartaHorarioB.setBounds(40, 70, 40, 19);

        btCurso42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso42.setContentAreaFilled(false);
        btCurso42.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso42.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso42.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso42ActionPerformed(evt);
            }
        });
        jPanel3.add(btCurso42);
        btCurso42.setBounds(80, 160, 30, 20);

        quartaCodigoProfessorE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quartaCodigoProfessorE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaCodigoProfessorE.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                quartaCodigoProfessorEFocusLost(evt);
            }
        });
        quartaCodigoProfessorE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quartaCodigoProfessorEActionPerformed(evt);
            }
        });
        quartaCodigoProfessorE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                quartaCodigoProfessorEKeyTyped(evt);
            }
        });
        jPanel3.add(quartaCodigoProfessorE);
        quartaCodigoProfessorE.setBounds(390, 160, 30, 20);

        btCurso43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso43.setContentAreaFilled(false);
        btCurso43.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso43.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso43.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso43ActionPerformed(evt);
            }
        });
        jPanel3.add(btCurso43);
        btCurso43.setBounds(350, 40, 30, 20);

        btCurso44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso44.setContentAreaFilled(false);
        btCurso44.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso44.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso44.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso44ActionPerformed(evt);
            }
        });
        jPanel3.add(btCurso44);
        btCurso44.setBounds(350, 70, 30, 20);

        quartaCodigoProfessorB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quartaCodigoProfessorB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaCodigoProfessorB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                quartaCodigoProfessorBFocusLost(evt);
            }
        });
        quartaCodigoProfessorB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quartaCodigoProfessorBActionPerformed(evt);
            }
        });
        quartaCodigoProfessorB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                quartaCodigoProfessorBKeyTyped(evt);
            }
        });
        jPanel3.add(quartaCodigoProfessorB);
        quartaCodigoProfessorB.setBounds(390, 70, 30, 20);

        btCurso45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso45.setContentAreaFilled(false);
        btCurso45.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso45.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso45.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso45ActionPerformed(evt);
            }
        });
        jPanel3.add(btCurso45);
        btCurso45.setBounds(80, 70, 30, 20);

        btCurso46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso46.setContentAreaFilled(false);
        btCurso46.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso46.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso46.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso46ActionPerformed(evt);
            }
        });
        jPanel3.add(btCurso46);
        btCurso46.setBounds(670, 190, 30, 20);

        btCurso47.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso47.setContentAreaFilled(false);
        btCurso47.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso47.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso47.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso47ActionPerformed(evt);
            }
        });
        jPanel3.add(btCurso47);
        btCurso47.setBounds(80, 40, 30, 20);

        quartaProfessorA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quartaProfessorA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaProfessorA.setEnabled(false);
        jPanel3.add(quartaProfessorA);
        quartaProfessorA.setBounds(430, 40, 240, 20);

        quartaHorarioA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quartaHorarioA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaHorarioA.setEnabled(false);
        jPanel3.add(quartaHorarioA);
        quartaHorarioA.setBounds(40, 40, 40, 20);

        quartaHorarioD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quartaHorarioD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaHorarioD.setEnabled(false);
        jPanel3.add(quartaHorarioD);
        quartaHorarioD.setBounds(40, 130, 40, 20);

        quartaCodigoProfessorF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quartaCodigoProfessorF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaCodigoProfessorF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                quartaCodigoProfessorFFocusLost(evt);
            }
        });
        quartaCodigoProfessorF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quartaCodigoProfessorFActionPerformed(evt);
            }
        });
        quartaCodigoProfessorF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                quartaCodigoProfessorFKeyTyped(evt);
            }
        });
        jPanel3.add(quartaCodigoProfessorF);
        quartaCodigoProfessorF.setBounds(390, 190, 30, 20);

        quartaProfessorF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quartaProfessorF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaProfessorF.setEnabled(false);
        jPanel3.add(quartaProfessorF);
        quartaProfessorF.setBounds(430, 190, 240, 20);

        quartaCodigoProfessorC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quartaCodigoProfessorC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaCodigoProfessorC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                quartaCodigoProfessorCFocusLost(evt);
            }
        });
        quartaCodigoProfessorC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quartaCodigoProfessorCActionPerformed(evt);
            }
        });
        quartaCodigoProfessorC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                quartaCodigoProfessorCKeyTyped(evt);
            }
        });
        jPanel3.add(quartaCodigoProfessorC);
        quartaCodigoProfessorC.setBounds(390, 100, 30, 20);

        btCurso48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso48.setContentAreaFilled(false);
        btCurso48.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso48.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso48.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso48ActionPerformed(evt);
            }
        });
        jPanel3.add(btCurso48);
        btCurso48.setBounds(670, 40, 30, 20);

        btCurso49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso49.setContentAreaFilled(false);
        btCurso49.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso49.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso49.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso49ActionPerformed(evt);
            }
        });
        jPanel3.add(btCurso49);
        btCurso49.setBounds(80, 100, 30, 20);

        btCurso50.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso50.setContentAreaFilled(false);
        btCurso50.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso50.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso50.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso50ActionPerformed(evt);
            }
        });
        jPanel3.add(btCurso50);
        btCurso50.setBounds(670, 160, 30, 20);

        quartaProfessorB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quartaProfessorB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaProfessorB.setEnabled(false);
        jPanel3.add(quartaProfessorB);
        quartaProfessorB.setBounds(430, 70, 240, 20);

        quartaHorarioE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quartaHorarioE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaHorarioE.setEnabled(false);
        jPanel3.add(quartaHorarioE);
        quartaHorarioE.setBounds(40, 160, 40, 20);

        btCurso51.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso51.setContentAreaFilled(false);
        btCurso51.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso51.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso51.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso51ActionPerformed(evt);
            }
        });
        jPanel3.add(btCurso51);
        btCurso51.setBounds(80, 190, 30, 20);

        quartaProfessorC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quartaProfessorC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaProfessorC.setEnabled(false);
        jPanel3.add(quartaProfessorC);
        quartaProfessorC.setBounds(430, 100, 240, 20);

        quartaHorarioF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quartaHorarioF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaHorarioF.setEnabled(false);
        jPanel3.add(quartaHorarioF);
        quartaHorarioF.setBounds(40, 190, 40, 20);

        quartaDisciplinaB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quartaDisciplinaB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaDisciplinaB.setEnabled(false);
        jPanel3.add(quartaDisciplinaB);
        quartaDisciplinaB.setBounds(110, 70, 240, 20);

        quartaDisciplinaF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quartaDisciplinaF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaDisciplinaF.setEnabled(false);
        jPanel3.add(quartaDisciplinaF);
        quartaDisciplinaF.setBounds(110, 190, 240, 20);

        btCurso52.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso52.setContentAreaFilled(false);
        btCurso52.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso52.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso52.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso52ActionPerformed(evt);
            }
        });
        jPanel3.add(btCurso52);
        btCurso52.setBounds(350, 130, 30, 20);

        btCurso53.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso53.setContentAreaFilled(false);
        btCurso53.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso53.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso53.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso53ActionPerformed(evt);
            }
        });
        jPanel3.add(btCurso53);
        btCurso53.setBounds(350, 190, 30, 20);

        btCurso54.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso54.setContentAreaFilled(false);
        btCurso54.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso54.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso54.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso54ActionPerformed(evt);
            }
        });
        jPanel3.add(btCurso54);
        btCurso54.setBounds(670, 100, 30, 20);

        quartaDisciplinaE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quartaDisciplinaE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaDisciplinaE.setEnabled(false);
        jPanel3.add(quartaDisciplinaE);
        quartaDisciplinaE.setBounds(110, 160, 240, 20);

        quartaCodigoProfessorD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quartaCodigoProfessorD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaCodigoProfessorD.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                quartaCodigoProfessorDFocusLost(evt);
            }
        });
        quartaCodigoProfessorD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quartaCodigoProfessorDActionPerformed(evt);
            }
        });
        quartaCodigoProfessorD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                quartaCodigoProfessorDKeyTyped(evt);
            }
        });
        jPanel3.add(quartaCodigoProfessorD);
        quartaCodigoProfessorD.setBounds(390, 130, 30, 20);

        quartaProfessorE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quartaProfessorE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaProfessorE.setEnabled(false);
        jPanel3.add(quartaProfessorE);
        quartaProfessorE.setBounds(430, 160, 240, 20);

        btCurso121.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso121.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso121.setContentAreaFilled(false);
        btCurso121.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso121.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso121.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso121.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso121ActionPerformed(evt);
            }
        });
        jPanel3.add(btCurso121);
        btCurso121.setBounds(710, 190, 30, 20);

        btCurso122.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso122.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso122.setContentAreaFilled(false);
        btCurso122.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso122.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso122.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso122.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso122ActionPerformed(evt);
            }
        });
        jPanel3.add(btCurso122);
        btCurso122.setBounds(710, 160, 30, 20);

        btCurso123.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso123.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso123.setContentAreaFilled(false);
        btCurso123.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso123.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso123.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso123.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso123ActionPerformed(evt);
            }
        });
        jPanel3.add(btCurso123);
        btCurso123.setBounds(710, 130, 30, 20);

        btCurso124.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso124.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso124.setContentAreaFilled(false);
        btCurso124.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso124.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso124.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso124.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso124ActionPerformed(evt);
            }
        });
        jPanel3.add(btCurso124);
        btCurso124.setBounds(710, 100, 30, 20);

        btCurso125.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso125.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso125.setContentAreaFilled(false);
        btCurso125.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso125.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso125.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso125.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso125ActionPerformed(evt);
            }
        });
        jPanel3.add(btCurso125);
        btCurso125.setBounds(710, 70, 30, 20);

        btCurso126.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso126.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso126.setContentAreaFilled(false);
        btCurso126.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso126.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso126.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso126.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso126ActionPerformed(evt);
            }
        });
        jPanel3.add(btCurso126);
        btCurso126.setBounds(710, 40, 30, 20);

        jlquadro2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/quadro.png"))); // NOI18N
        jPanel3.add(jlquadro2);
        jlquadro2.setBounds(0, 0, 750, 230);

        jTabbedPane1.addTab("Quarta-Feira", jPanel3);

        jPanel4.setLayout(null);

        quintaDisciplinaC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quintaDisciplinaC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaDisciplinaC.setEnabled(false);
        jPanel4.add(quintaDisciplinaC);
        quintaDisciplinaC.setBounds(110, 100, 240, 20);

        quintaHorarioC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quintaHorarioC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaHorarioC.setEnabled(false);
        jPanel4.add(quintaHorarioC);
        quintaHorarioC.setBounds(40, 100, 40, 20);

        btCurso55.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso55.setContentAreaFilled(false);
        btCurso55.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso55.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso55.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso55ActionPerformed(evt);
            }
        });
        jPanel4.add(btCurso55);
        btCurso55.setBounds(670, 130, 30, 20);

        quintaDisciplinaA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quintaDisciplinaA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaDisciplinaA.setEnabled(false);
        quintaDisciplinaA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quintaDisciplinaAActionPerformed(evt);
            }
        });
        jPanel4.add(quintaDisciplinaA);
        quintaDisciplinaA.setBounds(110, 40, 240, 20);

        btCurso56.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso56.setContentAreaFilled(false);
        btCurso56.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso56.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso56.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso56ActionPerformed(evt);
            }
        });
        jPanel4.add(btCurso56);
        btCurso56.setBounds(80, 130, 30, 20);

        btCurso57.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso57.setContentAreaFilled(false);
        btCurso57.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso57.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso57.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso57ActionPerformed(evt);
            }
        });
        jPanel4.add(btCurso57);
        btCurso57.setBounds(670, 70, 30, 20);

        quintaDisciplinaD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quintaDisciplinaD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaDisciplinaD.setEnabled(false);
        jPanel4.add(quintaDisciplinaD);
        quintaDisciplinaD.setBounds(110, 130, 240, 20);

        btCurso58.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso58.setContentAreaFilled(false);
        btCurso58.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso58.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso58.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso58ActionPerformed(evt);
            }
        });
        jPanel4.add(btCurso58);
        btCurso58.setBounds(350, 160, 30, 20);

        quintaCodigoProfessorA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quintaCodigoProfessorA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaCodigoProfessorA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                quintaCodigoProfessorAFocusLost(evt);
            }
        });
        quintaCodigoProfessorA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quintaCodigoProfessorAActionPerformed(evt);
            }
        });
        quintaCodigoProfessorA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                quintaCodigoProfessorAKeyTyped(evt);
            }
        });
        jPanel4.add(quintaCodigoProfessorA);
        quintaCodigoProfessorA.setBounds(390, 40, 30, 20);

        btCurso59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso59.setContentAreaFilled(false);
        btCurso59.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso59.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso59.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso59ActionPerformed(evt);
            }
        });
        jPanel4.add(btCurso59);
        btCurso59.setBounds(350, 100, 30, 20);

        quintaProfessorD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quintaProfessorD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaProfessorD.setEnabled(false);
        jPanel4.add(quintaProfessorD);
        quintaProfessorD.setBounds(430, 130, 240, 20);

        quintaHorarioB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quintaHorarioB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaHorarioB.setEnabled(false);
        jPanel4.add(quintaHorarioB);
        quintaHorarioB.setBounds(40, 70, 40, 19);

        btCurso60.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso60.setContentAreaFilled(false);
        btCurso60.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso60.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso60.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso60ActionPerformed(evt);
            }
        });
        jPanel4.add(btCurso60);
        btCurso60.setBounds(80, 160, 30, 20);

        quintaCodigoProfessorE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quintaCodigoProfessorE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaCodigoProfessorE.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                quintaCodigoProfessorEFocusLost(evt);
            }
        });
        quintaCodigoProfessorE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quintaCodigoProfessorEActionPerformed(evt);
            }
        });
        quintaCodigoProfessorE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                quintaCodigoProfessorEKeyTyped(evt);
            }
        });
        jPanel4.add(quintaCodigoProfessorE);
        quintaCodigoProfessorE.setBounds(390, 160, 30, 20);

        btCurso61.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso61.setContentAreaFilled(false);
        btCurso61.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso61.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso61.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso61ActionPerformed(evt);
            }
        });
        jPanel4.add(btCurso61);
        btCurso61.setBounds(350, 40, 30, 20);

        btCurso62.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso62.setContentAreaFilled(false);
        btCurso62.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso62.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso62.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso62ActionPerformed(evt);
            }
        });
        jPanel4.add(btCurso62);
        btCurso62.setBounds(350, 70, 30, 20);

        quintaCodigoProfessorB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quintaCodigoProfessorB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaCodigoProfessorB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                quintaCodigoProfessorBFocusLost(evt);
            }
        });
        quintaCodigoProfessorB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quintaCodigoProfessorBActionPerformed(evt);
            }
        });
        quintaCodigoProfessorB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                quintaCodigoProfessorBKeyTyped(evt);
            }
        });
        jPanel4.add(quintaCodigoProfessorB);
        quintaCodigoProfessorB.setBounds(390, 70, 30, 20);

        btCurso63.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso63.setContentAreaFilled(false);
        btCurso63.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso63.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso63.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso63ActionPerformed(evt);
            }
        });
        jPanel4.add(btCurso63);
        btCurso63.setBounds(80, 70, 30, 20);

        btCurso64.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso64.setContentAreaFilled(false);
        btCurso64.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso64.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso64.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso64ActionPerformed(evt);
            }
        });
        jPanel4.add(btCurso64);
        btCurso64.setBounds(670, 190, 30, 20);

        btCurso65.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso65.setContentAreaFilled(false);
        btCurso65.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso65.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso65.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso65ActionPerformed(evt);
            }
        });
        jPanel4.add(btCurso65);
        btCurso65.setBounds(80, 40, 30, 20);

        quintaProfessorA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quintaProfessorA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaProfessorA.setEnabled(false);
        jPanel4.add(quintaProfessorA);
        quintaProfessorA.setBounds(430, 40, 240, 20);

        quintaHorarioA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quintaHorarioA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaHorarioA.setEnabled(false);
        jPanel4.add(quintaHorarioA);
        quintaHorarioA.setBounds(40, 40, 40, 20);

        quintaHorarioD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quintaHorarioD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaHorarioD.setEnabled(false);
        jPanel4.add(quintaHorarioD);
        quintaHorarioD.setBounds(40, 130, 40, 20);

        quintaCodigoProfessorF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quintaCodigoProfessorF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaCodigoProfessorF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                quintaCodigoProfessorFFocusLost(evt);
            }
        });
        quintaCodigoProfessorF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quintaCodigoProfessorFActionPerformed(evt);
            }
        });
        quintaCodigoProfessorF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                quintaCodigoProfessorFKeyTyped(evt);
            }
        });
        jPanel4.add(quintaCodigoProfessorF);
        quintaCodigoProfessorF.setBounds(390, 190, 30, 20);

        quintaProfessorF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quintaProfessorF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaProfessorF.setEnabled(false);
        jPanel4.add(quintaProfessorF);
        quintaProfessorF.setBounds(430, 190, 240, 20);

        quintaCodigoProfessorC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quintaCodigoProfessorC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaCodigoProfessorC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                quintaCodigoProfessorCFocusLost(evt);
            }
        });
        quintaCodigoProfessorC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quintaCodigoProfessorCActionPerformed(evt);
            }
        });
        quintaCodigoProfessorC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                quintaCodigoProfessorCKeyTyped(evt);
            }
        });
        jPanel4.add(quintaCodigoProfessorC);
        quintaCodigoProfessorC.setBounds(390, 100, 30, 20);

        btCurso66.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso66.setContentAreaFilled(false);
        btCurso66.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso66.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso66.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso66ActionPerformed(evt);
            }
        });
        jPanel4.add(btCurso66);
        btCurso66.setBounds(670, 40, 30, 20);

        btCurso67.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso67.setContentAreaFilled(false);
        btCurso67.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso67.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso67.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso67ActionPerformed(evt);
            }
        });
        jPanel4.add(btCurso67);
        btCurso67.setBounds(80, 100, 30, 20);

        btCurso68.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso68.setContentAreaFilled(false);
        btCurso68.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso68.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso68.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso68.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso68ActionPerformed(evt);
            }
        });
        jPanel4.add(btCurso68);
        btCurso68.setBounds(670, 160, 30, 20);

        quintaProfessorB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quintaProfessorB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaProfessorB.setEnabled(false);
        jPanel4.add(quintaProfessorB);
        quintaProfessorB.setBounds(430, 70, 240, 20);

        quintaHorarioE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quintaHorarioE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaHorarioE.setEnabled(false);
        jPanel4.add(quintaHorarioE);
        quintaHorarioE.setBounds(40, 160, 40, 20);

        btCurso69.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso69.setContentAreaFilled(false);
        btCurso69.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso69.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso69.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso69.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso69ActionPerformed(evt);
            }
        });
        jPanel4.add(btCurso69);
        btCurso69.setBounds(80, 190, 30, 20);

        quintaProfessorC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quintaProfessorC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaProfessorC.setEnabled(false);
        jPanel4.add(quintaProfessorC);
        quintaProfessorC.setBounds(430, 100, 240, 20);

        quintaHorarioF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quintaHorarioF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaHorarioF.setEnabled(false);
        jPanel4.add(quintaHorarioF);
        quintaHorarioF.setBounds(40, 190, 40, 20);

        quintaDisciplinaB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quintaDisciplinaB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaDisciplinaB.setEnabled(false);
        jPanel4.add(quintaDisciplinaB);
        quintaDisciplinaB.setBounds(110, 70, 240, 20);

        quintaDisciplinaF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quintaDisciplinaF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaDisciplinaF.setEnabled(false);
        jPanel4.add(quintaDisciplinaF);
        quintaDisciplinaF.setBounds(110, 190, 240, 20);

        btCurso70.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso70.setContentAreaFilled(false);
        btCurso70.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso70.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso70.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso70.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso70ActionPerformed(evt);
            }
        });
        jPanel4.add(btCurso70);
        btCurso70.setBounds(350, 130, 30, 20);

        btCurso71.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso71.setContentAreaFilled(false);
        btCurso71.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso71.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso71.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso71.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso71ActionPerformed(evt);
            }
        });
        jPanel4.add(btCurso71);
        btCurso71.setBounds(350, 190, 30, 20);

        btCurso72.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso72.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso72.setContentAreaFilled(false);
        btCurso72.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso72.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso72.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso72ActionPerformed(evt);
            }
        });
        jPanel4.add(btCurso72);
        btCurso72.setBounds(670, 100, 30, 20);

        quintaDisciplinaE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quintaDisciplinaE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaDisciplinaE.setEnabled(false);
        jPanel4.add(quintaDisciplinaE);
        quintaDisciplinaE.setBounds(110, 160, 240, 20);

        quintaCodigoProfessorD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quintaCodigoProfessorD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaCodigoProfessorD.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                quintaCodigoProfessorDFocusLost(evt);
            }
        });
        quintaCodigoProfessorD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quintaCodigoProfessorDActionPerformed(evt);
            }
        });
        quintaCodigoProfessorD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                quintaCodigoProfessorDKeyTyped(evt);
            }
        });
        jPanel4.add(quintaCodigoProfessorD);
        quintaCodigoProfessorD.setBounds(390, 130, 30, 20);

        quintaProfessorE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quintaProfessorE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaProfessorE.setEnabled(false);
        jPanel4.add(quintaProfessorE);
        quintaProfessorE.setBounds(430, 160, 240, 20);

        btCurso127.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso127.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso127.setContentAreaFilled(false);
        btCurso127.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso127.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso127.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso127.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso127ActionPerformed(evt);
            }
        });
        jPanel4.add(btCurso127);
        btCurso127.setBounds(710, 190, 30, 20);

        btCurso128.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso128.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso128.setContentAreaFilled(false);
        btCurso128.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso128.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso128.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso128.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso128ActionPerformed(evt);
            }
        });
        jPanel4.add(btCurso128);
        btCurso128.setBounds(710, 160, 30, 20);

        btCurso129.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso129.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso129.setContentAreaFilled(false);
        btCurso129.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso129.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso129.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso129.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso129ActionPerformed(evt);
            }
        });
        jPanel4.add(btCurso129);
        btCurso129.setBounds(710, 130, 30, 20);

        btCurso130.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso130.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso130.setContentAreaFilled(false);
        btCurso130.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso130.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso130.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso130.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso130ActionPerformed(evt);
            }
        });
        jPanel4.add(btCurso130);
        btCurso130.setBounds(710, 100, 30, 20);

        btCurso131.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso131.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso131.setContentAreaFilled(false);
        btCurso131.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso131.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso131.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso131.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso131ActionPerformed(evt);
            }
        });
        jPanel4.add(btCurso131);
        btCurso131.setBounds(710, 70, 30, 20);

        btCurso132.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso132.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso132.setContentAreaFilled(false);
        btCurso132.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso132.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso132.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso132.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso132ActionPerformed(evt);
            }
        });
        jPanel4.add(btCurso132);
        btCurso132.setBounds(710, 40, 30, 20);

        jlquadro3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/quadro.png"))); // NOI18N
        jPanel4.add(jlquadro3);
        jlquadro3.setBounds(0, 0, 750, 230);

        jTabbedPane1.addTab("Quinta-Feira", jPanel4);

        jPanel5.setLayout(null);

        sextaDisciplinaC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sextaDisciplinaC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaDisciplinaC.setEnabled(false);
        jPanel5.add(sextaDisciplinaC);
        sextaDisciplinaC.setBounds(110, 100, 240, 20);

        sextaHorarioC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sextaHorarioC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaHorarioC.setEnabled(false);
        jPanel5.add(sextaHorarioC);
        sextaHorarioC.setBounds(40, 100, 40, 20);

        btCurso73.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso73.setContentAreaFilled(false);
        btCurso73.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso73.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso73.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso73.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso73ActionPerformed(evt);
            }
        });
        jPanel5.add(btCurso73);
        btCurso73.setBounds(670, 130, 30, 20);

        sextaDisciplinaA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sextaDisciplinaA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaDisciplinaA.setEnabled(false);
        sextaDisciplinaA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sextaDisciplinaAActionPerformed(evt);
            }
        });
        jPanel5.add(sextaDisciplinaA);
        sextaDisciplinaA.setBounds(110, 40, 240, 20);

        btCurso74.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso74.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso74.setContentAreaFilled(false);
        btCurso74.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso74.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso74.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso74.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso74ActionPerformed(evt);
            }
        });
        jPanel5.add(btCurso74);
        btCurso74.setBounds(80, 130, 30, 20);

        btCurso75.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso75.setContentAreaFilled(false);
        btCurso75.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso75.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso75.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso75.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso75ActionPerformed(evt);
            }
        });
        jPanel5.add(btCurso75);
        btCurso75.setBounds(670, 70, 30, 20);

        sextaDisciplinaD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sextaDisciplinaD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaDisciplinaD.setEnabled(false);
        jPanel5.add(sextaDisciplinaD);
        sextaDisciplinaD.setBounds(110, 130, 240, 20);

        btCurso76.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso76.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso76.setContentAreaFilled(false);
        btCurso76.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso76.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso76.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso76.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso76ActionPerformed(evt);
            }
        });
        jPanel5.add(btCurso76);
        btCurso76.setBounds(350, 160, 30, 20);

        sextaCodigoProfessorA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sextaCodigoProfessorA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaCodigoProfessorA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                sextaCodigoProfessorAFocusLost(evt);
            }
        });
        sextaCodigoProfessorA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sextaCodigoProfessorAActionPerformed(evt);
            }
        });
        sextaCodigoProfessorA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sextaCodigoProfessorAKeyTyped(evt);
            }
        });
        jPanel5.add(sextaCodigoProfessorA);
        sextaCodigoProfessorA.setBounds(390, 40, 30, 20);

        btCurso77.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso77.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso77.setContentAreaFilled(false);
        btCurso77.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso77.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso77.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso77.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso77ActionPerformed(evt);
            }
        });
        jPanel5.add(btCurso77);
        btCurso77.setBounds(350, 100, 30, 20);

        sextaProfessorD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sextaProfessorD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaProfessorD.setEnabled(false);
        jPanel5.add(sextaProfessorD);
        sextaProfessorD.setBounds(430, 130, 240, 20);

        sextaHorarioB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sextaHorarioB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaHorarioB.setEnabled(false);
        jPanel5.add(sextaHorarioB);
        sextaHorarioB.setBounds(40, 70, 40, 19);

        btCurso78.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso78.setContentAreaFilled(false);
        btCurso78.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso78.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso78.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso78.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso78ActionPerformed(evt);
            }
        });
        jPanel5.add(btCurso78);
        btCurso78.setBounds(80, 160, 30, 20);

        sextaCodigoProfessorE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sextaCodigoProfessorE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaCodigoProfessorE.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                sextaCodigoProfessorEFocusLost(evt);
            }
        });
        sextaCodigoProfessorE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sextaCodigoProfessorEActionPerformed(evt);
            }
        });
        sextaCodigoProfessorE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sextaCodigoProfessorEKeyTyped(evt);
            }
        });
        jPanel5.add(sextaCodigoProfessorE);
        sextaCodigoProfessorE.setBounds(390, 160, 30, 20);

        btCurso79.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso79.setContentAreaFilled(false);
        btCurso79.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso79.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso79.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso79.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso79ActionPerformed(evt);
            }
        });
        jPanel5.add(btCurso79);
        btCurso79.setBounds(350, 40, 30, 20);

        btCurso80.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso80.setContentAreaFilled(false);
        btCurso80.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso80.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso80.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso80.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso80ActionPerformed(evt);
            }
        });
        jPanel5.add(btCurso80);
        btCurso80.setBounds(350, 70, 30, 20);

        sextaCodigoProfessorB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sextaCodigoProfessorB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaCodigoProfessorB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                sextaCodigoProfessorBFocusLost(evt);
            }
        });
        sextaCodigoProfessorB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sextaCodigoProfessorBActionPerformed(evt);
            }
        });
        sextaCodigoProfessorB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sextaCodigoProfessorBKeyTyped(evt);
            }
        });
        jPanel5.add(sextaCodigoProfessorB);
        sextaCodigoProfessorB.setBounds(390, 70, 30, 20);

        btCurso81.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso81.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso81.setContentAreaFilled(false);
        btCurso81.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso81.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso81.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso81.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso81ActionPerformed(evt);
            }
        });
        jPanel5.add(btCurso81);
        btCurso81.setBounds(80, 70, 30, 20);

        btCurso82.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso82.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso82.setContentAreaFilled(false);
        btCurso82.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso82.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso82.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso82.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso82ActionPerformed(evt);
            }
        });
        jPanel5.add(btCurso82);
        btCurso82.setBounds(670, 190, 30, 20);

        btCurso83.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso83.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso83.setContentAreaFilled(false);
        btCurso83.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso83.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso83.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso83.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso83ActionPerformed(evt);
            }
        });
        jPanel5.add(btCurso83);
        btCurso83.setBounds(80, 40, 30, 20);

        sextaProfessorA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sextaProfessorA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaProfessorA.setEnabled(false);
        sextaProfessorA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sextaProfessorAActionPerformed(evt);
            }
        });
        jPanel5.add(sextaProfessorA);
        sextaProfessorA.setBounds(430, 40, 240, 20);

        sextaHorarioA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sextaHorarioA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaHorarioA.setEnabled(false);
        jPanel5.add(sextaHorarioA);
        sextaHorarioA.setBounds(40, 40, 40, 20);

        sextaHorarioD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sextaHorarioD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaHorarioD.setEnabled(false);
        jPanel5.add(sextaHorarioD);
        sextaHorarioD.setBounds(40, 130, 40, 20);

        sextaCodigoProfessorF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sextaCodigoProfessorF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaCodigoProfessorF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                sextaCodigoProfessorFFocusLost(evt);
            }
        });
        sextaCodigoProfessorF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sextaCodigoProfessorFActionPerformed(evt);
            }
        });
        sextaCodigoProfessorF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sextaCodigoProfessorFKeyTyped(evt);
            }
        });
        jPanel5.add(sextaCodigoProfessorF);
        sextaCodigoProfessorF.setBounds(390, 190, 30, 20);

        sextaProfessorF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sextaProfessorF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaProfessorF.setEnabled(false);
        jPanel5.add(sextaProfessorF);
        sextaProfessorF.setBounds(430, 190, 240, 20);

        sextaCodigoProfessorC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sextaCodigoProfessorC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaCodigoProfessorC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                sextaCodigoProfessorCFocusLost(evt);
            }
        });
        sextaCodigoProfessorC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sextaCodigoProfessorCActionPerformed(evt);
            }
        });
        sextaCodigoProfessorC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sextaCodigoProfessorCKeyTyped(evt);
            }
        });
        jPanel5.add(sextaCodigoProfessorC);
        sextaCodigoProfessorC.setBounds(390, 100, 30, 20);

        btCurso84.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso84.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso84.setContentAreaFilled(false);
        btCurso84.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso84.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso84.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso84.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso84ActionPerformed(evt);
            }
        });
        jPanel5.add(btCurso84);
        btCurso84.setBounds(670, 40, 30, 20);

        btCurso85.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso85.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso85.setContentAreaFilled(false);
        btCurso85.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso85.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso85.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso85.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso85ActionPerformed(evt);
            }
        });
        jPanel5.add(btCurso85);
        btCurso85.setBounds(80, 100, 30, 20);

        btCurso86.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso86.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso86.setContentAreaFilled(false);
        btCurso86.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso86.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso86.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso86.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso86ActionPerformed(evt);
            }
        });
        jPanel5.add(btCurso86);
        btCurso86.setBounds(670, 160, 30, 20);

        sextaProfessorB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sextaProfessorB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaProfessorB.setEnabled(false);
        jPanel5.add(sextaProfessorB);
        sextaProfessorB.setBounds(430, 70, 240, 20);

        sextaHorarioE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sextaHorarioE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaHorarioE.setEnabled(false);
        jPanel5.add(sextaHorarioE);
        sextaHorarioE.setBounds(40, 160, 40, 20);

        btCurso87.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso87.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso87.setContentAreaFilled(false);
        btCurso87.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso87.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso87.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso87.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso87ActionPerformed(evt);
            }
        });
        jPanel5.add(btCurso87);
        btCurso87.setBounds(80, 190, 30, 20);

        sextaProfessorC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sextaProfessorC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaProfessorC.setEnabled(false);
        jPanel5.add(sextaProfessorC);
        sextaProfessorC.setBounds(430, 100, 240, 20);

        sextaHorarioF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sextaHorarioF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaHorarioF.setEnabled(false);
        jPanel5.add(sextaHorarioF);
        sextaHorarioF.setBounds(40, 190, 40, 20);

        sextaDisciplinaB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sextaDisciplinaB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaDisciplinaB.setEnabled(false);
        jPanel5.add(sextaDisciplinaB);
        sextaDisciplinaB.setBounds(110, 70, 240, 20);

        sextaDisciplinaF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sextaDisciplinaF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaDisciplinaF.setEnabled(false);
        jPanel5.add(sextaDisciplinaF);
        sextaDisciplinaF.setBounds(110, 190, 240, 20);

        btCurso88.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso88.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso88.setContentAreaFilled(false);
        btCurso88.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso88.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso88.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso88.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso88ActionPerformed(evt);
            }
        });
        jPanel5.add(btCurso88);
        btCurso88.setBounds(350, 130, 30, 20);

        btCurso89.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso89.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso89.setContentAreaFilled(false);
        btCurso89.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso89.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso89.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso89.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso89ActionPerformed(evt);
            }
        });
        jPanel5.add(btCurso89);
        btCurso89.setBounds(350, 190, 30, 20);

        btCurso90.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso90.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso90.setContentAreaFilled(false);
        btCurso90.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso90.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso90.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso90.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso90ActionPerformed(evt);
            }
        });
        jPanel5.add(btCurso90);
        btCurso90.setBounds(670, 100, 30, 20);

        sextaDisciplinaE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sextaDisciplinaE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaDisciplinaE.setEnabled(false);
        jPanel5.add(sextaDisciplinaE);
        sextaDisciplinaE.setBounds(110, 160, 240, 20);

        sextaCodigoProfessorD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sextaCodigoProfessorD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaCodigoProfessorD.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                sextaCodigoProfessorDFocusLost(evt);
            }
        });
        sextaCodigoProfessorD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sextaCodigoProfessorDActionPerformed(evt);
            }
        });
        sextaCodigoProfessorD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sextaCodigoProfessorDKeyTyped(evt);
            }
        });
        jPanel5.add(sextaCodigoProfessorD);
        sextaCodigoProfessorD.setBounds(390, 130, 30, 20);

        sextaProfessorE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sextaProfessorE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaProfessorE.setEnabled(false);
        jPanel5.add(sextaProfessorE);
        sextaProfessorE.setBounds(430, 160, 240, 20);

        btCurso133.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso133.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso133.setContentAreaFilled(false);
        btCurso133.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso133.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso133.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso133.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso133ActionPerformed(evt);
            }
        });
        jPanel5.add(btCurso133);
        btCurso133.setBounds(710, 190, 30, 20);

        btCurso134.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso134.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso134.setContentAreaFilled(false);
        btCurso134.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso134.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso134.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso134.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso134ActionPerformed(evt);
            }
        });
        jPanel5.add(btCurso134);
        btCurso134.setBounds(710, 160, 30, 20);

        btCurso135.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso135.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso135.setContentAreaFilled(false);
        btCurso135.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso135.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso135.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso135.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso135ActionPerformed(evt);
            }
        });
        jPanel5.add(btCurso135);
        btCurso135.setBounds(710, 130, 30, 20);

        btCurso136.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso136.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso136.setContentAreaFilled(false);
        btCurso136.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso136.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso136.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso136.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso136ActionPerformed(evt);
            }
        });
        jPanel5.add(btCurso136);
        btCurso136.setBounds(710, 100, 30, 20);

        btCurso137.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso137.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso137.setContentAreaFilled(false);
        btCurso137.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso137.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso137.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso137.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso137ActionPerformed(evt);
            }
        });
        jPanel5.add(btCurso137);
        btCurso137.setBounds(710, 70, 30, 20);

        btCurso138.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso138.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso138.setContentAreaFilled(false);
        btCurso138.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso138.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso138.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso138.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso138ActionPerformed(evt);
            }
        });
        jPanel5.add(btCurso138);
        btCurso138.setBounds(710, 40, 30, 20);

        jlquadro4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/quadro.png"))); // NOI18N
        jPanel5.add(jlquadro4);
        jlquadro4.setBounds(0, 0, 750, 230);

        jTabbedPane1.addTab("Sexta-Feira", jPanel5);

        jPanel6.setLayout(null);

        sabadoDisciplinaC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sabadoDisciplinaC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoDisciplinaC.setEnabled(false);
        jPanel6.add(sabadoDisciplinaC);
        sabadoDisciplinaC.setBounds(110, 100, 240, 20);

        sabadoHorarioC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sabadoHorarioC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoHorarioC.setEnabled(false);
        jPanel6.add(sabadoHorarioC);
        sabadoHorarioC.setBounds(40, 100, 40, 20);

        btCurso91.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso91.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso91.setContentAreaFilled(false);
        btCurso91.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso91.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso91.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso91.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso91ActionPerformed(evt);
            }
        });
        jPanel6.add(btCurso91);
        btCurso91.setBounds(670, 130, 30, 20);

        sabadoDisciplinaA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sabadoDisciplinaA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoDisciplinaA.setEnabled(false);
        sabadoDisciplinaA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sabadoDisciplinaAActionPerformed(evt);
            }
        });
        jPanel6.add(sabadoDisciplinaA);
        sabadoDisciplinaA.setBounds(110, 40, 240, 20);

        btCurso92.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso92.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso92.setContentAreaFilled(false);
        btCurso92.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso92.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso92.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso92.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso92ActionPerformed(evt);
            }
        });
        jPanel6.add(btCurso92);
        btCurso92.setBounds(80, 130, 30, 20);

        btCurso93.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso93.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso93.setContentAreaFilled(false);
        btCurso93.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso93.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso93.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso93.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso93ActionPerformed(evt);
            }
        });
        jPanel6.add(btCurso93);
        btCurso93.setBounds(670, 70, 30, 20);

        sabadoDisciplinaD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sabadoDisciplinaD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoDisciplinaD.setEnabled(false);
        jPanel6.add(sabadoDisciplinaD);
        sabadoDisciplinaD.setBounds(110, 130, 240, 20);

        btCurso94.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso94.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso94.setContentAreaFilled(false);
        btCurso94.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso94.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso94.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso94.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso94ActionPerformed(evt);
            }
        });
        jPanel6.add(btCurso94);
        btCurso94.setBounds(350, 160, 30, 20);

        sabadoCodigoProfessorA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sabadoCodigoProfessorA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoCodigoProfessorA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                sabadoCodigoProfessorAFocusLost(evt);
            }
        });
        sabadoCodigoProfessorA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sabadoCodigoProfessorAActionPerformed(evt);
            }
        });
        sabadoCodigoProfessorA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sabadoCodigoProfessorAKeyTyped(evt);
            }
        });
        jPanel6.add(sabadoCodigoProfessorA);
        sabadoCodigoProfessorA.setBounds(390, 40, 30, 20);

        btCurso95.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso95.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso95.setContentAreaFilled(false);
        btCurso95.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso95.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso95.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso95.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso95ActionPerformed(evt);
            }
        });
        jPanel6.add(btCurso95);
        btCurso95.setBounds(350, 100, 30, 20);

        sabadoProfessorD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sabadoProfessorD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoProfessorD.setEnabled(false);
        jPanel6.add(sabadoProfessorD);
        sabadoProfessorD.setBounds(430, 130, 240, 20);

        sabadoHorarioB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sabadoHorarioB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoHorarioB.setEnabled(false);
        jPanel6.add(sabadoHorarioB);
        sabadoHorarioB.setBounds(40, 70, 40, 19);

        btCurso96.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso96.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso96.setContentAreaFilled(false);
        btCurso96.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso96.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso96.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso96.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso96ActionPerformed(evt);
            }
        });
        jPanel6.add(btCurso96);
        btCurso96.setBounds(80, 160, 30, 20);

        sabadoCodigoProfessorE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sabadoCodigoProfessorE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoCodigoProfessorE.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                sabadoCodigoProfessorEFocusLost(evt);
            }
        });
        sabadoCodigoProfessorE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sabadoCodigoProfessorEActionPerformed(evt);
            }
        });
        sabadoCodigoProfessorE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sabadoCodigoProfessorEKeyTyped(evt);
            }
        });
        jPanel6.add(sabadoCodigoProfessorE);
        sabadoCodigoProfessorE.setBounds(390, 160, 30, 20);

        btCurso97.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso97.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso97.setContentAreaFilled(false);
        btCurso97.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso97.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso97.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso97.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso97ActionPerformed(evt);
            }
        });
        jPanel6.add(btCurso97);
        btCurso97.setBounds(350, 40, 30, 20);

        btCurso98.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso98.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso98.setContentAreaFilled(false);
        btCurso98.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso98.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso98.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso98.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso98ActionPerformed(evt);
            }
        });
        jPanel6.add(btCurso98);
        btCurso98.setBounds(350, 70, 30, 20);

        sabadoCodigoProfessorB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sabadoCodigoProfessorB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoCodigoProfessorB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                sabadoCodigoProfessorBFocusLost(evt);
            }
        });
        sabadoCodigoProfessorB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sabadoCodigoProfessorBActionPerformed(evt);
            }
        });
        sabadoCodigoProfessorB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sabadoCodigoProfessorBKeyTyped(evt);
            }
        });
        jPanel6.add(sabadoCodigoProfessorB);
        sabadoCodigoProfessorB.setBounds(390, 70, 30, 20);

        btCurso99.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso99.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso99.setContentAreaFilled(false);
        btCurso99.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso99.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso99.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso99.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso99ActionPerformed(evt);
            }
        });
        jPanel6.add(btCurso99);
        btCurso99.setBounds(80, 70, 30, 20);

        btCurso100.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso100.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso100.setContentAreaFilled(false);
        btCurso100.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso100.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso100.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso100ActionPerformed(evt);
            }
        });
        jPanel6.add(btCurso100);
        btCurso100.setBounds(670, 190, 30, 20);

        btCurso101.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso101.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso101.setContentAreaFilled(false);
        btCurso101.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso101.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso101.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso101.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso101ActionPerformed(evt);
            }
        });
        jPanel6.add(btCurso101);
        btCurso101.setBounds(80, 40, 30, 20);

        sabadoProfessorA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sabadoProfessorA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoProfessorA.setEnabled(false);
        jPanel6.add(sabadoProfessorA);
        sabadoProfessorA.setBounds(430, 40, 240, 20);

        sabadoHorarioA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sabadoHorarioA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoHorarioA.setEnabled(false);
        jPanel6.add(sabadoHorarioA);
        sabadoHorarioA.setBounds(40, 40, 40, 20);

        sabadoHorarioD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sabadoHorarioD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoHorarioD.setEnabled(false);
        jPanel6.add(sabadoHorarioD);
        sabadoHorarioD.setBounds(40, 130, 40, 20);

        sabadoCodigoProfessorF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sabadoCodigoProfessorF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoCodigoProfessorF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                sabadoCodigoProfessorFFocusLost(evt);
            }
        });
        sabadoCodigoProfessorF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sabadoCodigoProfessorFActionPerformed(evt);
            }
        });
        sabadoCodigoProfessorF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sabadoCodigoProfessorFKeyTyped(evt);
            }
        });
        jPanel6.add(sabadoCodigoProfessorF);
        sabadoCodigoProfessorF.setBounds(390, 190, 30, 20);

        sabadoProfessorF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sabadoProfessorF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoProfessorF.setEnabled(false);
        jPanel6.add(sabadoProfessorF);
        sabadoProfessorF.setBounds(430, 190, 240, 20);

        sabadoCodigoProfessorC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sabadoCodigoProfessorC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoCodigoProfessorC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                sabadoCodigoProfessorCFocusLost(evt);
            }
        });
        sabadoCodigoProfessorC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sabadoCodigoProfessorCActionPerformed(evt);
            }
        });
        sabadoCodigoProfessorC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sabadoCodigoProfessorCKeyTyped(evt);
            }
        });
        jPanel6.add(sabadoCodigoProfessorC);
        sabadoCodigoProfessorC.setBounds(390, 100, 30, 20);

        btCurso102.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso102.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso102.setContentAreaFilled(false);
        btCurso102.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso102.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso102.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso102.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso102ActionPerformed(evt);
            }
        });
        jPanel6.add(btCurso102);
        btCurso102.setBounds(670, 40, 30, 20);

        btCurso103.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso103.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso103.setContentAreaFilled(false);
        btCurso103.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso103.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso103.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso103.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso103ActionPerformed(evt);
            }
        });
        jPanel6.add(btCurso103);
        btCurso103.setBounds(80, 100, 30, 20);

        btCurso104.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso104.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso104.setContentAreaFilled(false);
        btCurso104.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso104.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso104.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso104.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso104ActionPerformed(evt);
            }
        });
        jPanel6.add(btCurso104);
        btCurso104.setBounds(670, 160, 30, 20);

        sabadoProfessorB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sabadoProfessorB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoProfessorB.setEnabled(false);
        jPanel6.add(sabadoProfessorB);
        sabadoProfessorB.setBounds(430, 70, 240, 20);

        sabadoHorarioE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sabadoHorarioE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoHorarioE.setEnabled(false);
        jPanel6.add(sabadoHorarioE);
        sabadoHorarioE.setBounds(40, 160, 40, 20);

        btCurso105.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso105.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso105.setContentAreaFilled(false);
        btCurso105.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso105.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso105.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso105.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso105ActionPerformed(evt);
            }
        });
        jPanel6.add(btCurso105);
        btCurso105.setBounds(80, 190, 30, 20);

        sabadoProfessorC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sabadoProfessorC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoProfessorC.setEnabled(false);
        jPanel6.add(sabadoProfessorC);
        sabadoProfessorC.setBounds(430, 100, 240, 20);

        sabadoHorarioF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sabadoHorarioF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoHorarioF.setEnabled(false);
        jPanel6.add(sabadoHorarioF);
        sabadoHorarioF.setBounds(40, 190, 40, 20);

        sabadoDisciplinaB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sabadoDisciplinaB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoDisciplinaB.setEnabled(false);
        jPanel6.add(sabadoDisciplinaB);
        sabadoDisciplinaB.setBounds(110, 70, 240, 20);

        sabadoDisciplinaF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sabadoDisciplinaF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoDisciplinaF.setEnabled(false);
        jPanel6.add(sabadoDisciplinaF);
        sabadoDisciplinaF.setBounds(110, 190, 240, 20);

        btCurso106.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso106.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso106.setContentAreaFilled(false);
        btCurso106.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso106.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso106.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso106.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso106ActionPerformed(evt);
            }
        });
        jPanel6.add(btCurso106);
        btCurso106.setBounds(350, 130, 30, 20);

        btCurso107.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso107.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso107.setContentAreaFilled(false);
        btCurso107.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso107.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso107.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso107.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso107ActionPerformed(evt);
            }
        });
        jPanel6.add(btCurso107);
        btCurso107.setBounds(350, 190, 30, 20);

        btCurso108.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso108.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso108.setContentAreaFilled(false);
        btCurso108.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso108.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso108.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso108.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso108ActionPerformed(evt);
            }
        });
        jPanel6.add(btCurso108);
        btCurso108.setBounds(670, 100, 30, 20);

        sabadoDisciplinaE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sabadoDisciplinaE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoDisciplinaE.setEnabled(false);
        jPanel6.add(sabadoDisciplinaE);
        sabadoDisciplinaE.setBounds(110, 160, 240, 20);

        sabadoCodigoProfessorD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sabadoCodigoProfessorD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoCodigoProfessorD.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                sabadoCodigoProfessorDFocusLost(evt);
            }
        });
        sabadoCodigoProfessorD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sabadoCodigoProfessorDActionPerformed(evt);
            }
        });
        sabadoCodigoProfessorD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sabadoCodigoProfessorDKeyTyped(evt);
            }
        });
        jPanel6.add(sabadoCodigoProfessorD);
        sabadoCodigoProfessorD.setBounds(390, 130, 30, 20);

        sabadoProfessorE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sabadoProfessorE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoProfessorE.setEnabled(false);
        jPanel6.add(sabadoProfessorE);
        sabadoProfessorE.setBounds(430, 160, 240, 20);

        btCurso139.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso139.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso139.setContentAreaFilled(false);
        btCurso139.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso139.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso139.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso139.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso139ActionPerformed(evt);
            }
        });
        jPanel6.add(btCurso139);
        btCurso139.setBounds(710, 190, 30, 20);

        btCurso140.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso140.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso140.setContentAreaFilled(false);
        btCurso140.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso140.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso140.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso140.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso140ActionPerformed(evt);
            }
        });
        jPanel6.add(btCurso140);
        btCurso140.setBounds(710, 160, 30, 20);

        btCurso141.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso141.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso141.setContentAreaFilled(false);
        btCurso141.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso141.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso141.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso141.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso141ActionPerformed(evt);
            }
        });
        jPanel6.add(btCurso141);
        btCurso141.setBounds(710, 130, 30, 20);

        btCurso142.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso142.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso142.setContentAreaFilled(false);
        btCurso142.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso142.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso142.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso142.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso142ActionPerformed(evt);
            }
        });
        jPanel6.add(btCurso142);
        btCurso142.setBounds(710, 100, 30, 20);

        btCurso143.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso143.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso143.setContentAreaFilled(false);
        btCurso143.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso143.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso143.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso143.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso143ActionPerformed(evt);
            }
        });
        jPanel6.add(btCurso143);
        btCurso143.setBounds(710, 70, 30, 20);

        btCurso144.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso144.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso144.setContentAreaFilled(false);
        btCurso144.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso144.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso144.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso144.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso144ActionPerformed(evt);
            }
        });
        jPanel6.add(btCurso144);
        btCurso144.setBounds(710, 40, 30, 20);

        jlquadro5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/quadro.png"))); // NOI18N
        jPanel6.add(jlquadro5);
        jlquadro5.setBounds(0, 0, 750, 230);

        jTabbedPane1.addTab("Sábado", jPanel6);

        getContentPane().add(jTabbedPane1);
        jTabbedPane1.setBounds(20, 150, 753, 263);

        jlNome6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlNome6.setText("Grade.:");
        getContentPane().add(jlNome6);
        jlNome6.setBounds(510, 70, 70, 30);

        jlNome5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlNome5.setText("Ano Exercício.:");
        getContentPane().add(jlNome5);
        jlNome5.setBounds(520, 110, 140, 30);

        jcAnoExercicio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcAnoExercicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-----" }));
        jcAnoExercicio.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        jcAnoExercicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcAnoExercicioActionPerformed(evt);
            }
        });
        getContentPane().add(jcAnoExercicio);
        jcAnoExercicio.setBounds(660, 110, 90, 30);

        jlQuadroHorario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/quadrohorario.png"))); // NOI18N
        jlQuadroHorario.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jlQuadroHorario.setMaximumSize(new java.awt.Dimension(600, 520));
        jlQuadroHorario.setMinimumSize(new java.awt.Dimension(600, 520));
        jlQuadroHorario.setPreferredSize(new java.awt.Dimension(600, 520));
        getContentPane().add(jlQuadroHorario);
        jlQuadroHorario.setBounds(0, 0, 800, 492);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCursoActionPerformed
        btLimparActionPerformed(null);
        if (semestre.getIdSemestre() != 0) {
            semestre = new Semestre();
            tfNomeSemestre.setText("");
        }
        List<Curso> lista;
        lista = (cursoDAO.listar());
        CursoTableModel itm = new CursoTableModel(lista);
        Object objetoRetorno = PesquisaGenerica.exibeTela(itm, "Curso");
        if (objetoRetorno != null) {
            curso = cursoDAO.consultarObjetoId("idCurso", objetoRetorno);
            tfNomeCurso.setText(curso.getNomeCurso());
        }
        if (semestre.getIdSemestre() != 0) {
            tfNomeSemestre.setText("");
        }
    }//GEN-LAST:event_btCursoActionPerformed

    private void btSemestreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSemestreActionPerformed
        limparCamposSeTrocarValoresDoQuadro();
        if (curso.getIdCurso() != 0) {
            List<Semestre> lista;
            lista = (semestreDAO.listar());
            List<Semestre> listaFiltrada = new ArrayList<>();
            for (Semestre semestre1 : lista) {
                if (semestre1.getCurso().getIdCurso() == curso.getIdCurso()) {
                    listaFiltrada.add(semestre1);
                }
            }
            SemestreTableModel itm = new SemestreTableModel(listaFiltrada);
            Object objetoRetorno = PesquisaGenerica.exibeTela(itm, "Semestre");
            if (objetoRetorno != null) {
                semestre = semestreDAO.consultarObjetoId("idSemestre", objetoRetorno);
                tfNomeSemestre.setText(semestre.getNomeSemestre());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione o Curso!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btSemestreActionPerformed

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        dispose();
    }//GEN-LAST:event_btVoltarActionPerformed

    private void btLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparActionPerformed
        Util.limparCamposGenerico(this);
        Util.limparCamposJTabblePane(jTabbedPane1);
        //Inicialização de objetos
        quadroHorarios = new QuadroHorarios();
        semestre = new Semestre();
        disciplina = new Disciplina();
        gradeCurricular = new GradeCurricular();
        horario = new Horario();
        professor = new Professor();
        curso = new Curso();
        horariosSegunda = new Horario[qtdAulas];
        horariosTerca = new Horario[qtdAulas];
        horariosQuarta = new Horario[qtdAulas];
        horariosQuinta = new Horario[qtdAulas];
        horariosSexta = new Horario[qtdAulas];
        horariosSabado = new Horario[qtdAulas];
        professoresSegunda = new Professor[qtdAulas];
        professoresTerca = new Professor[qtdAulas];
        professoresQuarta = new Professor[qtdAulas];
        professoresQuinta = new Professor[qtdAulas];
        professoresSexta = new Professor[qtdAulas];
        professoresSabado = new Professor[qtdAulas];
        disciplinasSegunda = new Disciplina[qtdAulas];
        disciplinasTerca = new Disciplina[qtdAulas];
        disciplinasQuarta = new Disciplina[qtdAulas];
        disciplinasQuinta = new Disciplina[qtdAulas];
        disciplinasSexta = new Disciplina[qtdAulas];
        disciplinasSabado = new Disciplina[qtdAulas];
        ordenacaoHorarios = new int[qtdHorDisPr];
        ordenacaoDisciplinas = new int[qtdHorDisPr];
        ordenacaoProfessores = new int[qtdHorDisPr];
        btCurso.setEnabled(true);
        btSemestre.setEnabled(true);
        btGrade.setEnabled(true);
        jcTurno.setEnabled(true);
        jcAnoExercicio.setEnabled(true);
        btExcluir.setEnabled(false);
        carregarListas();
        carregarAnoExercicioAtual();
        carregarQuadroHorario();
        jTabbedPane1.setSelectedComponent(jPanel1);
    }//GEN-LAST:event_btLimparActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        if ((!tfNomeCurso.getText().equals("")) && (!jcAnoExercicio.getSelectedItem().equals("-----"))) {
            List<QuadroHorarios> listaQuadroHorariosInicial = quadroHorariosDAO.checkExistseq("anoExercicio", jcAnoExercicio.getSelectedItem());
            List<QuadroHorarios> listaFiltrada = new ArrayList();
            for (QuadroHorarios quadroH : listaQuadroHorariosInicial) {
                if ((quadroH.getAnoExercicio().equals(jcAnoExercicio.getSelectedItem()))
                        && quadroH.getCurso().getNomeCurso().equals(tfNomeCurso.getText())) {
                    listaFiltrada.add(quadroH);
                }
            }
            QuadroHorariosTableModel itm = new QuadroHorariosTableModel(listaFiltrada);
            Object objetoRetorno = PesquisaGenerica.exibeTela(itm, "Quadro Horários");
            if (objetoRetorno != null) {
                quadroHorarios = quadroHorariosDAO.consultarObjetoId("idQuadroHorarios", objetoRetorno);
                curso = quadroHorarios.getCurso();
                semestre = quadroHorarios.getSemestre();
                gradeCurricular = quadroHorarios.getGradeCurricular();
                jcAnoExercicio.setSelectedItem(quadroHorarios.getAnoExercicio());
                jcTurno.setSelectedItem(quadroHorarios.getTurno());
                tfNomeCurso.setText(curso.getNomeCurso());
                tfNomeSemestre.setText(semestre.getNomeSemestre());
                tfGradeCurricular.setText(gradeCurricular.getNomeGradeCurricular());
                ordenarHorariosPesquisados();
                preencherCamposComArraysQuadroHorarios();
                btCurso.setEnabled(false);
                btSemestre.setEnabled(false);
                btGrade.setEnabled(false);
                jcTurno.setEnabled(false);
                jcAnoExercicio.setEnabled(false);
                btExcluir.setEnabled(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione o Curso e Ano Exercício!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        Object[] options = {"Sim", "Não"};
        if (quadroHorarios.getIdQuadroHorarios() != 0) {
            if (JOptionPane.showOptionDialog(rootPane, "Deseja excluir o Quadro de Horários do " + quadroHorarios.getSemestre().getNomeSemestre()
                    + "\n do Curso de " + quadroHorarios.getCurso().getNomeCurso()
                    + "?", "BirdPoint", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]) == JOptionPane.YES_OPTION) {
                if (quadroHorariosDAO.remover(quadroHorarios)) {
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Não foi possível excluir o Quadro Horários do " + quadroHorarios.getSemestre().getNomeSemestre()
                            + "\n do Curso de " + quadroHorarios.getCurso().getNomeCurso(),
                            "Erro ao Excluir", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "A exclusão foi cancelada!");
            }

        }
        btLimparActionPerformed(null);
    }//GEN-LAST:event_btExcluirActionPerformed


    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        if (Util.chkVazio(tfGradeCurricular.getText(), tfNomeCurso.getText(), tfNomeSemestre.getText(), String.valueOf(jcAnoExercicio.getSelectedItem()), String.valueOf(jcTurno.getSelectedItem()))) {
            if (verificarHorariosVazios()) {
                if (!verificarHorarioDuplicado()) {
                    if (!verificarChoqueHorarios()) {
                        quadroHorarios.setAnoExercicio(String.valueOf(jcAnoExercicio.getSelectedItem()));
                        quadroHorarios.setTurno(String.valueOf(jcTurno.getSelectedItem()));
                        quadroHorarios.setCurso(curso);
                        quadroHorarios.setSemestre(semestre);
                        quadroHorarios.setGradeCurricular(gradeCurricular);
                        capturarOrdenacao();
                        quadroHorarios.setOrdenacaoHorarios(ordenacaoHorarios);
                        quadroHorarios.setOrdenacaoDisciplinas(ordenacaoDisciplinas);
                        quadroHorarios.setOrdenacaoProfessores(ordenacaoProfessores);
                        quadroHorariosDAO.salvar(quadroHorarios);
                        btLimparActionPerformed(null);
                    }
                }
            }
        }
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btGradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGradeActionPerformed
        limparCamposSeTrocarValoresDoQuadro();
        if (curso.getIdCurso() != 0) {
            List<GradeCurricular> lista;
            lista = (gradeCurricularDAO.listar());
            List<GradeCurricular> listaFiltrada = new ArrayList<>();
            for (GradeCurricular gradeCurricular : lista) {
                if (gradeCurricular.getCurso().getIdCurso() == curso.getIdCurso()) {
                    listaFiltrada.add(gradeCurricular);
                }
            }
            GradeCurricularTableModel itm = new GradeCurricularTableModel(listaFiltrada);
            Object objetoRetorno = PesquisaGenerica.exibeTela(itm, "Grade Curricular");
            if (objetoRetorno != null) {
                gradeCurricular = gradeCurricularDAO.consultarObjetoId("idGradeCurricular", objetoRetorno);
                tfGradeCurricular.setText(gradeCurricular.getNomeGradeCurricular());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione o Curso!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btGradeActionPerformed

    private void btCurso1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso1ActionPerformed
        selecionarDisciplina(segundaDisciplinaC);
        disciplinasSegunda[2] = disciplina;
    }//GEN-LAST:event_btCurso1ActionPerformed

    private void btCurso2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso2ActionPerformed
        selecionarHorario(segundaHorarioB);
        horariosSegunda[1] = horario;
    }//GEN-LAST:event_btCurso2ActionPerformed

    private void btCurso3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso3ActionPerformed
        selecionarHorario(segundaHorarioC);
        horariosSegunda[2] = horario;
    }//GEN-LAST:event_btCurso3ActionPerformed

    private void btCurso4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso4ActionPerformed
        selecionarHorario(segundaHorarioF);
        horariosSegunda[5] = horario;
    }//GEN-LAST:event_btCurso4ActionPerformed

    private void btCurso5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso5ActionPerformed
        selecionarHorario(segundaHorarioD);
        horariosSegunda[3] = horario;
    }//GEN-LAST:event_btCurso5ActionPerformed

    private void btCurso6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso6ActionPerformed
        selecionarHorario(segundaHorarioE);
        horariosSegunda[4] = horario;
    }//GEN-LAST:event_btCurso6ActionPerformed

    private void btCurso7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso7ActionPerformed
        selecionarHorario(segundaHorarioA);
        horariosSegunda[0] = horario;
    }//GEN-LAST:event_btCurso7ActionPerformed

    private void btCurso8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso8ActionPerformed
        selecionarProfessor(segundaCodigoProfessorB, segundaProfessorB);
        professoresSegunda[1] = professor;
    }//GEN-LAST:event_btCurso8ActionPerformed

    private void btCurso9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso9ActionPerformed
        selecionarDisciplina(segundaDisciplinaD);
        disciplinasSegunda[3] = disciplina;
    }//GEN-LAST:event_btCurso9ActionPerformed

    private void btCurso10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso10ActionPerformed
        selecionarDisciplina(segundaDisciplinaE);
        disciplinasSegunda[4] = disciplina;
    }//GEN-LAST:event_btCurso10ActionPerformed

    private void btCurso11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso11ActionPerformed
        selecionarDisciplina(segundaDisciplinaF);
        disciplinasSegunda[5] = disciplina;
    }//GEN-LAST:event_btCurso11ActionPerformed

    private void btCurso12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso12ActionPerformed
        selecionarDisciplina(segundaDisciplinaB);
        disciplinasSegunda[1] = disciplina;
    }//GEN-LAST:event_btCurso12ActionPerformed

    private void btCurso13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso13ActionPerformed
        selecionarDisciplina(segundaDisciplinaA);
        disciplinasSegunda[0] = disciplina;
    }//GEN-LAST:event_btCurso13ActionPerformed

    private void btCurso14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso14ActionPerformed
        selecionarProfessor(segundaCodigoProfessorC, segundaProfessorC);
        professoresSegunda[2] = professor;
    }//GEN-LAST:event_btCurso14ActionPerformed

    private void btCurso15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso15ActionPerformed
        selecionarProfessor(segundaCodigoProfessorD, segundaProfessorD);
        professoresSegunda[3] = professor;
    }//GEN-LAST:event_btCurso15ActionPerformed

    private void btCurso16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso16ActionPerformed
        selecionarProfessor(segundaCodigoProfessorE, segundaProfessorE);
        professoresSegunda[4] = professor;
    }//GEN-LAST:event_btCurso16ActionPerformed

    private void btCurso17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso17ActionPerformed
        selecionarProfessor(segundaCodigoProfessorF, segundaProfessorF);
        professoresSegunda[5] = professor;
    }//GEN-LAST:event_btCurso17ActionPerformed

    private void btCurso18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso18ActionPerformed
        selecionarProfessor(segundaCodigoProfessorA, segundaProfessorA);
        professoresSegunda[0] = professor;
    }//GEN-LAST:event_btCurso18ActionPerformed

    private void jcTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcTurnoActionPerformed
        limparCamposSeTrocarValoresDoQuadro();
    }//GEN-LAST:event_jcTurnoActionPerformed

    private void segundaDisciplinaAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaDisciplinaAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaDisciplinaAActionPerformed

    private void segundaCodigoProfessorBKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorBKeyTyped
        Util.limite3Caracteres(evt, segundaCodigoProfessorB);
    }//GEN-LAST:event_segundaCodigoProfessorBKeyTyped

    private void segundaCodigoProfessorBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorBActionPerformed

    }//GEN-LAST:event_segundaCodigoProfessorBActionPerformed

    private void segundaCodigoProfessorCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorCActionPerformed

    private void segundaCodigoProfessorCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorCKeyTyped
        Util.limite3Caracteres(evt, segundaCodigoProfessorC);
    }//GEN-LAST:event_segundaCodigoProfessorCKeyTyped

    private void segundaCodigoProfessorDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorDActionPerformed

    private void segundaCodigoProfessorDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorDKeyTyped
        Util.limite3Caracteres(evt, segundaCodigoProfessorD);
    }//GEN-LAST:event_segundaCodigoProfessorDKeyTyped

    private void segundaCodigoProfessorEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorEActionPerformed

    private void segundaCodigoProfessorEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorEKeyTyped
        Util.limite3Caracteres(evt, segundaCodigoProfessorE);
    }//GEN-LAST:event_segundaCodigoProfessorEKeyTyped

    private void segundaCodigoProfessorFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorFActionPerformed

    private void segundaCodigoProfessorFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorFKeyTyped
        Util.limite3Caracteres(evt, segundaCodigoProfessorF);
    }//GEN-LAST:event_segundaCodigoProfessorFKeyTyped

    private void segundaCodigoProfessorAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorAActionPerformed

    private void segundaCodigoProfessorAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorAKeyTyped
        Util.limite3Caracteres(evt, segundaCodigoProfessorA);
    }//GEN-LAST:event_segundaCodigoProfessorAKeyTyped

    private void btCurso19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso19ActionPerformed
        selecionarProfessor(tercaCodigoProfessorD, tercaProfessorD);
        professoresTerca[3] = professor;
    }//GEN-LAST:event_btCurso19ActionPerformed

    private void tercaDisciplinaAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tercaDisciplinaAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tercaDisciplinaAActionPerformed

    private void btCurso20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso20ActionPerformed
        selecionarHorario(tercaHorarioD);
        horariosTerca[3] = horario;
    }//GEN-LAST:event_btCurso20ActionPerformed

    private void btCurso21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso21ActionPerformed
        selecionarProfessor(tercaCodigoProfessorB, tercaProfessorB);
        professoresTerca[1] = professor;
    }//GEN-LAST:event_btCurso21ActionPerformed

    private void btCurso22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso22ActionPerformed
        selecionarDisciplina(tercaDisciplinaE);
        disciplinasTerca[4] = disciplina;
    }//GEN-LAST:event_btCurso22ActionPerformed

    private void tercaCodigoProfessorAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tercaCodigoProfessorAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tercaCodigoProfessorAActionPerformed

    private void tercaCodigoProfessorAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tercaCodigoProfessorAKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tercaCodigoProfessorAKeyTyped

    private void btCurso23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso23ActionPerformed
        selecionarDisciplina(tercaDisciplinaC);
        disciplinasTerca[2] = disciplina;
    }//GEN-LAST:event_btCurso23ActionPerformed

    private void btCurso24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso24ActionPerformed
        selecionarHorario(tercaHorarioE);
        horariosTerca[4] = horario;
    }//GEN-LAST:event_btCurso24ActionPerformed

    private void tercaCodigoProfessorEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tercaCodigoProfessorEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tercaCodigoProfessorEActionPerformed

    private void tercaCodigoProfessorEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tercaCodigoProfessorEKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tercaCodigoProfessorEKeyTyped

    private void btCurso25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso25ActionPerformed
        selecionarDisciplina(tercaDisciplinaA);
        disciplinasTerca[0] = disciplina;
    }//GEN-LAST:event_btCurso25ActionPerformed

    private void btCurso26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso26ActionPerformed
        selecionarDisciplina(tercaDisciplinaB);
        disciplinasTerca[1] = disciplina;
    }//GEN-LAST:event_btCurso26ActionPerformed

    private void tercaCodigoProfessorBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tercaCodigoProfessorBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tercaCodigoProfessorBActionPerformed

    private void tercaCodigoProfessorBKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tercaCodigoProfessorBKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tercaCodigoProfessorBKeyTyped

    private void btCurso27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso27ActionPerformed
        selecionarHorario(tercaHorarioB);
        horariosTerca[1] = horario;
    }//GEN-LAST:event_btCurso27ActionPerformed

    private void btCurso28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso28ActionPerformed
        selecionarProfessor(tercaCodigoProfessorF, tercaProfessorF);
        professoresTerca[5] = professor;
    }//GEN-LAST:event_btCurso28ActionPerformed

    private void btCurso29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso29ActionPerformed
        selecionarHorario(tercaHorarioA);
        horariosTerca[0] = horario;
    }//GEN-LAST:event_btCurso29ActionPerformed

    private void tercaCodigoProfessorFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tercaCodigoProfessorFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tercaCodigoProfessorFActionPerformed

    private void tercaCodigoProfessorFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tercaCodigoProfessorFKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tercaCodigoProfessorFKeyTyped

    private void tercaCodigoProfessorCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tercaCodigoProfessorCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tercaCodigoProfessorCActionPerformed

    private void tercaCodigoProfessorCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tercaCodigoProfessorCKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tercaCodigoProfessorCKeyTyped

    private void btCurso30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso30ActionPerformed
        selecionarProfessor(tercaCodigoProfessorA, tercaProfessorA);
        professoresTerca[0] = professor;
    }//GEN-LAST:event_btCurso30ActionPerformed

    private void btCurso31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso31ActionPerformed
        selecionarHorario(tercaHorarioC);
        horariosTerca[2] = horario;
    }//GEN-LAST:event_btCurso31ActionPerformed

    private void btCurso32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso32ActionPerformed
        selecionarProfessor(tercaCodigoProfessorE, tercaProfessorE);
        professoresTerca[4] = professor;
    }//GEN-LAST:event_btCurso32ActionPerformed

    private void btCurso33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso33ActionPerformed
        selecionarHorario(tercaHorarioF);
        horariosTerca[5] = horario;
    }//GEN-LAST:event_btCurso33ActionPerformed

    private void btCurso34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso34ActionPerformed
        selecionarDisciplina(tercaDisciplinaD);
        disciplinasTerca[3] = disciplina;
    }//GEN-LAST:event_btCurso34ActionPerformed

    private void btCurso35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso35ActionPerformed
        selecionarDisciplina(tercaDisciplinaF);
        disciplinasTerca[5] = disciplina;
    }//GEN-LAST:event_btCurso35ActionPerformed

    private void btCurso36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso36ActionPerformed
        selecionarProfessor(tercaCodigoProfessorC, tercaProfessorC);
        professoresTerca[2] = professor;
    }//GEN-LAST:event_btCurso36ActionPerformed

    private void tercaCodigoProfessorDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tercaCodigoProfessorDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tercaCodigoProfessorDActionPerformed

    private void tercaCodigoProfessorDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tercaCodigoProfessorDKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tercaCodigoProfessorDKeyTyped

    private void btCurso37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso37ActionPerformed
        selecionarProfessor(quartaCodigoProfessorD, quartaProfessorD);
        professoresQuarta[3] = professor;
    }//GEN-LAST:event_btCurso37ActionPerformed

    private void quartaDisciplinaAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quartaDisciplinaAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quartaDisciplinaAActionPerformed

    private void btCurso38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso38ActionPerformed
        selecionarHorario(quartaHorarioD);
        horariosQuarta[3] = horario;
    }//GEN-LAST:event_btCurso38ActionPerformed

    private void btCurso39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso39ActionPerformed
        selecionarProfessor(quartaCodigoProfessorB, quartaProfessorB);
        professoresQuarta[1] = professor;
    }//GEN-LAST:event_btCurso39ActionPerformed

    private void btCurso40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso40ActionPerformed
        selecionarDisciplina(quartaDisciplinaE);
        disciplinasQuarta[4] = disciplina;
    }//GEN-LAST:event_btCurso40ActionPerformed

    private void quartaCodigoProfessorAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quartaCodigoProfessorAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quartaCodigoProfessorAActionPerformed

    private void quartaCodigoProfessorAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quartaCodigoProfessorAKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_quartaCodigoProfessorAKeyTyped

    private void btCurso41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso41ActionPerformed
        selecionarDisciplina(quartaDisciplinaC);
        disciplinasQuarta[2] = disciplina;
    }//GEN-LAST:event_btCurso41ActionPerformed

    private void btCurso42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso42ActionPerformed
        selecionarHorario(quartaHorarioE);
        horariosQuarta[4] = horario;
    }//GEN-LAST:event_btCurso42ActionPerformed

    private void quartaCodigoProfessorEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quartaCodigoProfessorEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quartaCodigoProfessorEActionPerformed

    private void quartaCodigoProfessorEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quartaCodigoProfessorEKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_quartaCodigoProfessorEKeyTyped

    private void btCurso43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso43ActionPerformed
        selecionarDisciplina(quartaDisciplinaA);
        disciplinasQuarta[0] = disciplina;
    }//GEN-LAST:event_btCurso43ActionPerformed

    private void btCurso44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso44ActionPerformed
        selecionarDisciplina(quartaDisciplinaB);
        disciplinasQuarta[1] = disciplina;
    }//GEN-LAST:event_btCurso44ActionPerformed

    private void quartaCodigoProfessorBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quartaCodigoProfessorBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quartaCodigoProfessorBActionPerformed

    private void quartaCodigoProfessorBKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quartaCodigoProfessorBKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_quartaCodigoProfessorBKeyTyped

    private void btCurso45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso45ActionPerformed
        selecionarHorario(quartaHorarioB);
        horariosQuarta[1] = horario;
    }//GEN-LAST:event_btCurso45ActionPerformed

    private void btCurso46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso46ActionPerformed
        selecionarProfessor(quartaCodigoProfessorF, quartaProfessorF);
        professoresQuarta[5] = professor;
    }//GEN-LAST:event_btCurso46ActionPerformed

    private void btCurso47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso47ActionPerformed
        selecionarHorario(quartaHorarioA);
        horariosQuarta[0] = horario;
    }//GEN-LAST:event_btCurso47ActionPerformed

    private void quartaCodigoProfessorFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quartaCodigoProfessorFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quartaCodigoProfessorFActionPerformed

    private void quartaCodigoProfessorFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quartaCodigoProfessorFKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_quartaCodigoProfessorFKeyTyped

    private void quartaCodigoProfessorCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quartaCodigoProfessorCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quartaCodigoProfessorCActionPerformed

    private void quartaCodigoProfessorCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quartaCodigoProfessorCKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_quartaCodigoProfessorCKeyTyped

    private void btCurso48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso48ActionPerformed
        selecionarProfessor(quartaCodigoProfessorA, quartaProfessorA);
        professoresQuarta[0] = professor;
    }//GEN-LAST:event_btCurso48ActionPerformed

    private void btCurso49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso49ActionPerformed
        selecionarHorario(quartaHorarioC);
        horariosQuarta[2] = horario;
    }//GEN-LAST:event_btCurso49ActionPerformed

    private void btCurso50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso50ActionPerformed
        selecionarProfessor(quartaCodigoProfessorE, quartaProfessorE);
        professoresQuarta[4] = professor;
    }//GEN-LAST:event_btCurso50ActionPerformed

    private void btCurso51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso51ActionPerformed
        selecionarHorario(quartaHorarioF);
        horariosQuarta[5] = horario;
    }//GEN-LAST:event_btCurso51ActionPerformed

    private void btCurso52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso52ActionPerformed
        selecionarDisciplina(quartaDisciplinaD);
        disciplinasQuarta[3] = disciplina;
    }//GEN-LAST:event_btCurso52ActionPerformed

    private void btCurso53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso53ActionPerformed
        selecionarDisciplina(quartaDisciplinaF);
        disciplinasQuarta[5] = disciplina;
    }//GEN-LAST:event_btCurso53ActionPerformed

    private void btCurso54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso54ActionPerformed
        selecionarProfessor(quartaCodigoProfessorC, quartaProfessorC);
        professoresQuarta[2] = professor;
    }//GEN-LAST:event_btCurso54ActionPerformed

    private void quartaCodigoProfessorDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quartaCodigoProfessorDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quartaCodigoProfessorDActionPerformed

    private void quartaCodigoProfessorDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quartaCodigoProfessorDKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_quartaCodigoProfessorDKeyTyped

    private void btCurso55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso55ActionPerformed
        selecionarProfessor(quintaCodigoProfessorD, quintaProfessorD);
        professoresQuinta[3] = professor;
    }//GEN-LAST:event_btCurso55ActionPerformed

    private void quintaDisciplinaAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quintaDisciplinaAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quintaDisciplinaAActionPerformed

    private void btCurso56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso56ActionPerformed
        selecionarHorario(quintaHorarioD);
        horariosQuinta[3] = horario;
    }//GEN-LAST:event_btCurso56ActionPerformed

    private void btCurso57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso57ActionPerformed
        selecionarProfessor(quintaCodigoProfessorB, quintaProfessorB);
        professoresQuinta[1] = professor;
    }//GEN-LAST:event_btCurso57ActionPerformed

    private void btCurso58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso58ActionPerformed
        selecionarDisciplina(quintaDisciplinaE);
        disciplinasQuinta[4] = disciplina;
    }//GEN-LAST:event_btCurso58ActionPerformed

    private void quintaCodigoProfessorAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quintaCodigoProfessorAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quintaCodigoProfessorAActionPerformed

    private void quintaCodigoProfessorAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quintaCodigoProfessorAKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_quintaCodigoProfessorAKeyTyped

    private void btCurso59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso59ActionPerformed
        selecionarDisciplina(quintaDisciplinaC);
        disciplinasQuinta[2] = disciplina;
    }//GEN-LAST:event_btCurso59ActionPerformed

    private void btCurso60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso60ActionPerformed
        selecionarHorario(quintaHorarioE);
        horariosQuinta[4] = horario;
    }//GEN-LAST:event_btCurso60ActionPerformed

    private void quintaCodigoProfessorEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quintaCodigoProfessorEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quintaCodigoProfessorEActionPerformed

    private void quintaCodigoProfessorEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quintaCodigoProfessorEKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_quintaCodigoProfessorEKeyTyped

    private void btCurso61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso61ActionPerformed
        selecionarDisciplina(quintaDisciplinaA);
        disciplinasQuinta[0] = disciplina;
    }//GEN-LAST:event_btCurso61ActionPerformed

    private void btCurso62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso62ActionPerformed
        selecionarDisciplina(quintaDisciplinaB);
        disciplinasQuinta[1] = disciplina;
    }//GEN-LAST:event_btCurso62ActionPerformed

    private void quintaCodigoProfessorBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quintaCodigoProfessorBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quintaCodigoProfessorBActionPerformed

    private void quintaCodigoProfessorBKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quintaCodigoProfessorBKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_quintaCodigoProfessorBKeyTyped

    private void btCurso63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso63ActionPerformed
        selecionarHorario(quintaHorarioB);
        horariosQuinta[1] = horario;
    }//GEN-LAST:event_btCurso63ActionPerformed

    private void btCurso64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso64ActionPerformed
        selecionarProfessor(quintaCodigoProfessorF, quintaProfessorF);
        professoresQuinta[5] = professor;
    }//GEN-LAST:event_btCurso64ActionPerformed

    private void btCurso65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso65ActionPerformed
        selecionarHorario(quintaHorarioA);
        horariosQuinta[0] = horario;
    }//GEN-LAST:event_btCurso65ActionPerformed

    private void quintaCodigoProfessorFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quintaCodigoProfessorFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quintaCodigoProfessorFActionPerformed

    private void quintaCodigoProfessorFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quintaCodigoProfessorFKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_quintaCodigoProfessorFKeyTyped

    private void quintaCodigoProfessorCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quintaCodigoProfessorCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quintaCodigoProfessorCActionPerformed

    private void quintaCodigoProfessorCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quintaCodigoProfessorCKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_quintaCodigoProfessorCKeyTyped

    private void btCurso66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso66ActionPerformed
        selecionarProfessor(quintaCodigoProfessorA, quintaProfessorA);
        professoresQuinta[0] = professor;
    }//GEN-LAST:event_btCurso66ActionPerformed

    private void btCurso67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso67ActionPerformed
        selecionarHorario(quintaHorarioC);
        horariosQuinta[2] = horario;
    }//GEN-LAST:event_btCurso67ActionPerformed

    private void btCurso68ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso68ActionPerformed
        selecionarProfessor(quintaCodigoProfessorE, quintaProfessorE);
        professoresQuinta[4] = professor;
    }//GEN-LAST:event_btCurso68ActionPerformed

    private void btCurso69ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso69ActionPerformed
        selecionarHorario(quintaHorarioF);
        horariosQuinta[5] = horario;
    }//GEN-LAST:event_btCurso69ActionPerformed

    private void btCurso70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso70ActionPerformed
        selecionarDisciplina(quintaDisciplinaD);
        disciplinasQuinta[3] = disciplina;
    }//GEN-LAST:event_btCurso70ActionPerformed

    private void btCurso71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso71ActionPerformed
        selecionarDisciplina(quintaDisciplinaF);
        disciplinasQuinta[5] = disciplina;
    }//GEN-LAST:event_btCurso71ActionPerformed

    private void btCurso72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso72ActionPerformed
        selecionarProfessor(quintaCodigoProfessorC, quintaProfessorC);
        professoresQuinta[2] = professor;
    }//GEN-LAST:event_btCurso72ActionPerformed

    private void quintaCodigoProfessorDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quintaCodigoProfessorDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quintaCodigoProfessorDActionPerformed

    private void quintaCodigoProfessorDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quintaCodigoProfessorDKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_quintaCodigoProfessorDKeyTyped

    private void btCurso73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso73ActionPerformed
        selecionarProfessor(sextaCodigoProfessorD, sextaProfessorD);
        professoresSexta[3] = professor;
    }//GEN-LAST:event_btCurso73ActionPerformed

    private void sextaDisciplinaAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sextaDisciplinaAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sextaDisciplinaAActionPerformed

    private void btCurso74ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso74ActionPerformed
        selecionarHorario(sextaHorarioD);
        horariosSexta[3] = horario;
    }//GEN-LAST:event_btCurso74ActionPerformed

    private void btCurso75ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso75ActionPerformed
        selecionarProfessor(sextaCodigoProfessorB, sextaProfessorB);
        professoresSexta[1] = professor;
    }//GEN-LAST:event_btCurso75ActionPerformed

    private void btCurso76ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso76ActionPerformed
        selecionarDisciplina(sextaDisciplinaE);
        disciplinasSexta[4] = disciplina;
    }//GEN-LAST:event_btCurso76ActionPerformed

    private void sextaCodigoProfessorAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sextaCodigoProfessorAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sextaCodigoProfessorAActionPerformed

    private void sextaCodigoProfessorAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sextaCodigoProfessorAKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_sextaCodigoProfessorAKeyTyped

    private void btCurso77ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso77ActionPerformed
        selecionarDisciplina(sextaDisciplinaC);
        disciplinasSexta[2] = disciplina;
    }//GEN-LAST:event_btCurso77ActionPerformed

    private void btCurso78ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso78ActionPerformed
        selecionarHorario(sextaHorarioE);
        horariosSexta[4] = horario;
    }//GEN-LAST:event_btCurso78ActionPerformed

    private void sextaCodigoProfessorEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sextaCodigoProfessorEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sextaCodigoProfessorEActionPerformed

    private void sextaCodigoProfessorEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sextaCodigoProfessorEKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_sextaCodigoProfessorEKeyTyped

    private void btCurso79ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso79ActionPerformed
        selecionarDisciplina(sextaDisciplinaA);
        disciplinasSexta[0] = disciplina;
    }//GEN-LAST:event_btCurso79ActionPerformed

    private void btCurso80ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso80ActionPerformed
        selecionarDisciplina(sextaDisciplinaB);
        disciplinasSexta[1] = disciplina;
    }//GEN-LAST:event_btCurso80ActionPerformed

    private void sextaCodigoProfessorBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sextaCodigoProfessorBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sextaCodigoProfessorBActionPerformed

    private void sextaCodigoProfessorBKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sextaCodigoProfessorBKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_sextaCodigoProfessorBKeyTyped

    private void btCurso81ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso81ActionPerformed
        selecionarHorario(sextaHorarioB);
        horariosSexta[1] = horario;
    }//GEN-LAST:event_btCurso81ActionPerformed

    private void btCurso82ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso82ActionPerformed
        selecionarProfessor(sextaCodigoProfessorF, sextaProfessorF);
        professoresSexta[5] = professor;
    }//GEN-LAST:event_btCurso82ActionPerformed

    private void btCurso83ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso83ActionPerformed
        selecionarHorario(sextaHorarioA);
        horariosSexta[0] = horario;
    }//GEN-LAST:event_btCurso83ActionPerformed

    private void sextaCodigoProfessorFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sextaCodigoProfessorFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sextaCodigoProfessorFActionPerformed

    private void sextaCodigoProfessorFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sextaCodigoProfessorFKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_sextaCodigoProfessorFKeyTyped

    private void sextaCodigoProfessorCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sextaCodigoProfessorCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sextaCodigoProfessorCActionPerformed

    private void sextaCodigoProfessorCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sextaCodigoProfessorCKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_sextaCodigoProfessorCKeyTyped

    private void btCurso84ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso84ActionPerformed
        selecionarProfessor(sextaCodigoProfessorA, sextaProfessorA);
        professoresSexta[0] = professor;
    }//GEN-LAST:event_btCurso84ActionPerformed

    private void btCurso85ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso85ActionPerformed
        selecionarHorario(sextaHorarioC);
        horariosSexta[2] = horario;
    }//GEN-LAST:event_btCurso85ActionPerformed

    private void btCurso86ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso86ActionPerformed
        selecionarProfessor(sextaCodigoProfessorE, sextaProfessorE);
        professoresSexta[4] = professor;
    }//GEN-LAST:event_btCurso86ActionPerformed

    private void btCurso87ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso87ActionPerformed
        selecionarHorario(sextaHorarioF);
        horariosSexta[5] = horario;
    }//GEN-LAST:event_btCurso87ActionPerformed

    private void btCurso88ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso88ActionPerformed
        selecionarDisciplina(sextaDisciplinaD);
        disciplinasSexta[3] = disciplina;
    }//GEN-LAST:event_btCurso88ActionPerformed

    private void btCurso89ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso89ActionPerformed
        selecionarDisciplina(sextaDisciplinaF);
        disciplinasSexta[5] = disciplina;
    }//GEN-LAST:event_btCurso89ActionPerformed

    private void btCurso90ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso90ActionPerformed
        selecionarProfessor(sextaCodigoProfessorC, sextaProfessorC);
        professoresSexta[2] = professor;
    }//GEN-LAST:event_btCurso90ActionPerformed

    private void sextaCodigoProfessorDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sextaCodigoProfessorDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sextaCodigoProfessorDActionPerformed

    private void sextaCodigoProfessorDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sextaCodigoProfessorDKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_sextaCodigoProfessorDKeyTyped

    private void btCurso91ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso91ActionPerformed
        selecionarProfessor(sabadoCodigoProfessorD, sabadoProfessorD);
        professoresSabado[3] = professor;
    }//GEN-LAST:event_btCurso91ActionPerformed

    private void sabadoDisciplinaAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sabadoDisciplinaAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sabadoDisciplinaAActionPerformed

    private void btCurso92ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso92ActionPerformed
        selecionarHorario(sabadoHorarioD);
        horariosSabado[3] = horario;
    }//GEN-LAST:event_btCurso92ActionPerformed

    private void btCurso93ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso93ActionPerformed
        selecionarProfessor(sabadoCodigoProfessorB, sabadoProfessorB);
        professoresSabado[1] = professor;
    }//GEN-LAST:event_btCurso93ActionPerformed

    private void btCurso94ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso94ActionPerformed
        selecionarDisciplina(sabadoDisciplinaE);
        disciplinasSabado[4] = disciplina;
    }//GEN-LAST:event_btCurso94ActionPerformed

    private void sabadoCodigoProfessorAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sabadoCodigoProfessorAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sabadoCodigoProfessorAActionPerformed

    private void sabadoCodigoProfessorAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sabadoCodigoProfessorAKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_sabadoCodigoProfessorAKeyTyped

    private void btCurso95ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso95ActionPerformed
        selecionarDisciplina(sabadoDisciplinaC);
        disciplinasSabado[2] = disciplina;
    }//GEN-LAST:event_btCurso95ActionPerformed

    private void btCurso96ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso96ActionPerformed
        selecionarHorario(sabadoHorarioE);
        horariosSabado[4] = horario;
    }//GEN-LAST:event_btCurso96ActionPerformed

    private void sabadoCodigoProfessorEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sabadoCodigoProfessorEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sabadoCodigoProfessorEActionPerformed

    private void sabadoCodigoProfessorEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sabadoCodigoProfessorEKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_sabadoCodigoProfessorEKeyTyped

    private void btCurso97ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso97ActionPerformed
        selecionarDisciplina(sabadoDisciplinaA);
        disciplinasSabado[0] = disciplina;
    }//GEN-LAST:event_btCurso97ActionPerformed

    private void btCurso98ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso98ActionPerformed
        selecionarDisciplina(sabadoDisciplinaB);
        disciplinasSabado[1] = disciplina;
    }//GEN-LAST:event_btCurso98ActionPerformed

    private void sabadoCodigoProfessorBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sabadoCodigoProfessorBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sabadoCodigoProfessorBActionPerformed

    private void sabadoCodigoProfessorBKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sabadoCodigoProfessorBKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_sabadoCodigoProfessorBKeyTyped

    private void btCurso99ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso99ActionPerformed
        selecionarHorario(sabadoHorarioB);
        horariosSabado[1] = horario;

    }//GEN-LAST:event_btCurso99ActionPerformed

    private void btCurso100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso100ActionPerformed
        selecionarProfessor(sabadoCodigoProfessorF, sabadoProfessorF);
        professoresSabado[5] = professor;
    }//GEN-LAST:event_btCurso100ActionPerformed

    private void btCurso101ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso101ActionPerformed
        selecionarHorario(sabadoHorarioA);
        horariosSabado[0] = horario;
    }//GEN-LAST:event_btCurso101ActionPerformed

    private void sabadoCodigoProfessorFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sabadoCodigoProfessorFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sabadoCodigoProfessorFActionPerformed

    private void sabadoCodigoProfessorFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sabadoCodigoProfessorFKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_sabadoCodigoProfessorFKeyTyped

    private void sabadoCodigoProfessorCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sabadoCodigoProfessorCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sabadoCodigoProfessorCActionPerformed

    private void sabadoCodigoProfessorCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sabadoCodigoProfessorCKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_sabadoCodigoProfessorCKeyTyped

    private void btCurso102ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso102ActionPerformed
        selecionarProfessor(sabadoCodigoProfessorA, sabadoProfessorA);
        professoresSabado[0] = professor;
    }//GEN-LAST:event_btCurso102ActionPerformed

    private void btCurso103ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso103ActionPerformed
        selecionarHorario(sabadoHorarioC);
        horariosSabado[2] = horario;
    }//GEN-LAST:event_btCurso103ActionPerformed

    private void btCurso104ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso104ActionPerformed
        selecionarProfessor(sabadoCodigoProfessorE, sabadoProfessorE);
        professoresSabado[4] = professor;
    }//GEN-LAST:event_btCurso104ActionPerformed

    private void btCurso105ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso105ActionPerformed
        selecionarHorario(sabadoHorarioF);
        horariosSabado[5] = horario;
    }//GEN-LAST:event_btCurso105ActionPerformed

    private void btCurso106ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso106ActionPerformed
        selecionarDisciplina(sabadoDisciplinaD);
        disciplinasSabado[3] = disciplina;
    }//GEN-LAST:event_btCurso106ActionPerformed

    private void btCurso107ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso107ActionPerformed
        selecionarDisciplina(sabadoDisciplinaF);
        disciplinasSabado[5] = disciplina;
    }//GEN-LAST:event_btCurso107ActionPerformed

    private void btCurso108ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso108ActionPerformed
        selecionarProfessor(sabadoCodigoProfessorC, sabadoProfessorC);
        professoresSabado[2] = professor;
    }//GEN-LAST:event_btCurso108ActionPerformed

    private void sabadoCodigoProfessorDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sabadoCodigoProfessorDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sabadoCodigoProfessorDActionPerformed

    private void sabadoCodigoProfessorDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sabadoCodigoProfessorDKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_sabadoCodigoProfessorDKeyTyped

    private void segundaCodigoProfessorAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorAFocusLost
        pesquisarProfessorCodigo(segundaCodigoProfessorA, segundaProfessorA);
        professoresSegunda[0] = professor;

    }//GEN-LAST:event_segundaCodigoProfessorAFocusLost

    private void segundaCodigoProfessorBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorBFocusLost
        pesquisarProfessorCodigo(segundaCodigoProfessorB, segundaProfessorB);
        professoresSegunda[1] = professor;
    }//GEN-LAST:event_segundaCodigoProfessorBFocusLost

    private void segundaCodigoProfessorCFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorCFocusLost
        pesquisarProfessorCodigo(segundaCodigoProfessorC, segundaProfessorC);
        professoresSegunda[2] = professor;
    }//GEN-LAST:event_segundaCodigoProfessorCFocusLost

    private void segundaCodigoProfessorDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorDFocusLost
        pesquisarProfessorCodigo(segundaCodigoProfessorD, segundaProfessorD);
        professoresSegunda[3] = professor;
    }//GEN-LAST:event_segundaCodigoProfessorDFocusLost

    private void segundaCodigoProfessorEFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorEFocusLost
        pesquisarProfessorCodigo(segundaCodigoProfessorE, segundaProfessorE);
        professoresSegunda[4] = professor;
    }//GEN-LAST:event_segundaCodigoProfessorEFocusLost

    private void segundaCodigoProfessorFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorFFocusLost
        pesquisarProfessorCodigo(segundaCodigoProfessorF, segundaProfessorF);
        professoresSegunda[5] = professor;
    }//GEN-LAST:event_segundaCodigoProfessorFFocusLost

    private void jcAnoExercicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcAnoExercicioActionPerformed
        limparCamposSeTrocarValoresDoQuadro();
    }//GEN-LAST:event_jcAnoExercicioActionPerformed

    private void tercaCodigoProfessorAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tercaCodigoProfessorAFocusLost
        pesquisarProfessorCodigo(tercaCodigoProfessorA, tercaProfessorA);
        professoresTerca[0] = professor;
    }//GEN-LAST:event_tercaCodigoProfessorAFocusLost

    private void tercaCodigoProfessorBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tercaCodigoProfessorBFocusLost
        pesquisarProfessorCodigo(tercaCodigoProfessorB, tercaProfessorB);
        professoresTerca[1] = professor;
    }//GEN-LAST:event_tercaCodigoProfessorBFocusLost

    private void tercaCodigoProfessorCFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tercaCodigoProfessorCFocusLost
        pesquisarProfessorCodigo(tercaCodigoProfessorC, tercaProfessorC);
        professoresTerca[2] = professor;
    }//GEN-LAST:event_tercaCodigoProfessorCFocusLost

    private void tercaCodigoProfessorDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tercaCodigoProfessorDFocusLost
        pesquisarProfessorCodigo(tercaCodigoProfessorD, tercaProfessorD);
        professoresTerca[3] = professor;
    }//GEN-LAST:event_tercaCodigoProfessorDFocusLost

    private void tercaCodigoProfessorEFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tercaCodigoProfessorEFocusLost
        pesquisarProfessorCodigo(tercaCodigoProfessorE, tercaProfessorE);
        professoresTerca[4] = professor;
    }//GEN-LAST:event_tercaCodigoProfessorEFocusLost

    private void tercaCodigoProfessorFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tercaCodigoProfessorFFocusLost
        pesquisarProfessorCodigo(tercaCodigoProfessorF, tercaProfessorF);
        professoresTerca[5] = professor;
    }//GEN-LAST:event_tercaCodigoProfessorFFocusLost

    private void quartaCodigoProfessorAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_quartaCodigoProfessorAFocusLost
        pesquisarProfessorCodigo(quartaCodigoProfessorA, quartaProfessorA);
        professoresQuarta[0] = professor;
    }//GEN-LAST:event_quartaCodigoProfessorAFocusLost

    private void quartaCodigoProfessorBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_quartaCodigoProfessorBFocusLost
        pesquisarProfessorCodigo(quartaCodigoProfessorB, quartaProfessorB);
        professoresQuarta[1] = professor;
    }//GEN-LAST:event_quartaCodigoProfessorBFocusLost

    private void quartaCodigoProfessorCFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_quartaCodigoProfessorCFocusLost
        pesquisarProfessorCodigo(quartaCodigoProfessorC, quartaProfessorC);
        professoresQuarta[2] = professor;
    }//GEN-LAST:event_quartaCodigoProfessorCFocusLost

    private void quartaCodigoProfessorDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_quartaCodigoProfessorDFocusLost
        pesquisarProfessorCodigo(quartaCodigoProfessorD, quartaProfessorD);
        professoresQuarta[3] = professor;
    }//GEN-LAST:event_quartaCodigoProfessorDFocusLost

    private void quartaCodigoProfessorEFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_quartaCodigoProfessorEFocusLost
        pesquisarProfessorCodigo(quartaCodigoProfessorE, quartaProfessorE);
        professoresQuarta[4] = professor;
    }//GEN-LAST:event_quartaCodigoProfessorEFocusLost

    private void quartaCodigoProfessorFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_quartaCodigoProfessorFFocusLost
        pesquisarProfessorCodigo(quartaCodigoProfessorF, quartaProfessorF);
        professoresQuarta[5] = professor;
    }//GEN-LAST:event_quartaCodigoProfessorFFocusLost

    private void quintaCodigoProfessorAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_quintaCodigoProfessorAFocusLost
        pesquisarProfessorCodigo(quintaCodigoProfessorA, quintaProfessorA);
        professoresQuinta[0] = professor;
    }//GEN-LAST:event_quintaCodigoProfessorAFocusLost

    private void quintaCodigoProfessorBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_quintaCodigoProfessorBFocusLost
        pesquisarProfessorCodigo(quintaCodigoProfessorB, quintaProfessorB);
        professoresQuinta[1] = professor;
    }//GEN-LAST:event_quintaCodigoProfessorBFocusLost

    private void quintaCodigoProfessorCFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_quintaCodigoProfessorCFocusLost
        pesquisarProfessorCodigo(quintaCodigoProfessorC, quintaProfessorC);
        professoresQuinta[2] = professor;
    }//GEN-LAST:event_quintaCodigoProfessorCFocusLost

    private void quintaCodigoProfessorDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_quintaCodigoProfessorDFocusLost
        pesquisarProfessorCodigo(quintaCodigoProfessorD, quintaProfessorD);
        professoresQuinta[3] = professor;
    }//GEN-LAST:event_quintaCodigoProfessorDFocusLost

    private void quintaCodigoProfessorEFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_quintaCodigoProfessorEFocusLost
        pesquisarProfessorCodigo(quintaCodigoProfessorE, quintaProfessorE);
        professoresQuinta[4] = professor;
    }//GEN-LAST:event_quintaCodigoProfessorEFocusLost

    private void quintaCodigoProfessorFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_quintaCodigoProfessorFFocusLost
        pesquisarProfessorCodigo(quintaCodigoProfessorF, quintaProfessorF);
        professoresQuinta[5] = professor;
    }//GEN-LAST:event_quintaCodigoProfessorFFocusLost

    private void sextaCodigoProfessorAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sextaCodigoProfessorAFocusLost
        pesquisarProfessorCodigo(sextaCodigoProfessorA, sextaProfessorA);
        professoresSexta[0] = professor;
    }//GEN-LAST:event_sextaCodigoProfessorAFocusLost

    private void sextaCodigoProfessorBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sextaCodigoProfessorBFocusLost
        pesquisarProfessorCodigo(sextaCodigoProfessorB, sextaProfessorB);
        professoresSexta[1] = professor;
    }//GEN-LAST:event_sextaCodigoProfessorBFocusLost

    private void sextaCodigoProfessorCFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sextaCodigoProfessorCFocusLost
        pesquisarProfessorCodigo(sextaCodigoProfessorC, sextaProfessorC);
        professoresSexta[2] = professor;
    }//GEN-LAST:event_sextaCodigoProfessorCFocusLost

    private void sextaCodigoProfessorDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sextaCodigoProfessorDFocusLost
        pesquisarProfessorCodigo(sextaCodigoProfessorD, sextaProfessorD);
        professoresSexta[3] = professor;
    }//GEN-LAST:event_sextaCodigoProfessorDFocusLost

    private void sextaCodigoProfessorEFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sextaCodigoProfessorEFocusLost
        pesquisarProfessorCodigo(sextaCodigoProfessorE, sextaProfessorE);
        professoresSexta[4] = professor;
    }//GEN-LAST:event_sextaCodigoProfessorEFocusLost

    private void sextaCodigoProfessorFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sextaCodigoProfessorFFocusLost
        pesquisarProfessorCodigo(sextaCodigoProfessorF, sextaProfessorF);
        professoresSexta[5] = professor;
    }//GEN-LAST:event_sextaCodigoProfessorFFocusLost

    private void sabadoCodigoProfessorAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sabadoCodigoProfessorAFocusLost
        pesquisarProfessorCodigo(sabadoCodigoProfessorA, sabadoProfessorA);
        professoresSabado[0] = professor;
    }//GEN-LAST:event_sabadoCodigoProfessorAFocusLost

    private void sabadoCodigoProfessorBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sabadoCodigoProfessorBFocusLost
        pesquisarProfessorCodigo(sabadoCodigoProfessorB, sabadoProfessorB);
        professoresSabado[1] = professor;
    }//GEN-LAST:event_sabadoCodigoProfessorBFocusLost

    private void sabadoCodigoProfessorCFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sabadoCodigoProfessorCFocusLost
        pesquisarProfessorCodigo(sabadoCodigoProfessorC, sabadoProfessorC);
        professoresSabado[2] = professor;
    }//GEN-LAST:event_sabadoCodigoProfessorCFocusLost

    private void sabadoCodigoProfessorDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sabadoCodigoProfessorDFocusLost
        pesquisarProfessorCodigo(sabadoCodigoProfessorD, sabadoProfessorD);
        professoresSabado[3] = professor;
    }//GEN-LAST:event_sabadoCodigoProfessorDFocusLost

    private void sabadoCodigoProfessorEFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sabadoCodigoProfessorEFocusLost
        pesquisarProfessorCodigo(sabadoCodigoProfessorE, sabadoProfessorE);
        professoresSabado[4] = professor;
    }//GEN-LAST:event_sabadoCodigoProfessorEFocusLost

    private void sabadoCodigoProfessorFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sabadoCodigoProfessorFFocusLost
        pesquisarProfessorCodigo(sabadoCodigoProfessorF, sabadoProfessorF);
        professoresSabado[5] = professor;
    }//GEN-LAST:event_sabadoCodigoProfessorFFocusLost

    private void sextaProfessorAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sextaProfessorAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sextaProfessorAActionPerformed

    private void btCurso109ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso109ActionPerformed
        professoresSegunda[1] = null;
        disciplinasSegunda[1] = null;
        horariosSegunda[1] = null;
        segundaHorarioB.setText("");
        segundaDisciplinaB.setText("");
        segundaCodigoProfessorB.setText("");
        segundaProfessorB.setText("");
    }//GEN-LAST:event_btCurso109ActionPerformed

    private void btCurso110ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso110ActionPerformed
        professoresSegunda[2] = null;
        disciplinasSegunda[2] = null;
        horariosSegunda[2] = null;
        segundaHorarioC.setText("");
        segundaDisciplinaC.setText("");
        segundaCodigoProfessorC.setText("");
        segundaProfessorC.setText("");
    }//GEN-LAST:event_btCurso110ActionPerformed

    private void btCurso111ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso111ActionPerformed
        professoresSegunda[3] = null;
        disciplinasSegunda[3] = null;
        horariosSegunda[3] = null;
        segundaHorarioD.setText("");
        segundaDisciplinaD.setText("");
        segundaCodigoProfessorD.setText("");
        segundaProfessorD.setText("");
    }//GEN-LAST:event_btCurso111ActionPerformed

    private void btCurso112ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso112ActionPerformed
        professoresSegunda[4] = null;
        disciplinasSegunda[4] = null;
        horariosSegunda[4] = null;
        segundaHorarioE.setText("");
        segundaDisciplinaE.setText("");
        segundaCodigoProfessorE.setText("");
        segundaProfessorE.setText("");
    }//GEN-LAST:event_btCurso112ActionPerformed

    private void btCurso113ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso113ActionPerformed
        professoresSegunda[5] = null;
        disciplinasSegunda[5] = null;
        horariosSegunda[5] = null;
        segundaHorarioF.setText("");
        segundaDisciplinaF.setText("");
        segundaCodigoProfessorF.setText("");
        segundaProfessorF.setText("");
    }//GEN-LAST:event_btCurso113ActionPerformed

    private void btCurso114ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso114ActionPerformed
        professoresSegunda[0] = null;
        disciplinasSegunda[0] = null;
        horariosSegunda[0] = null;
        segundaHorarioA.setText("");
        segundaDisciplinaA.setText("");
        segundaCodigoProfessorA.setText("");
        segundaProfessorA.setText("");
    }//GEN-LAST:event_btCurso114ActionPerformed

    private void btCurso115ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso115ActionPerformed
        professoresTerca[5] = null;
        disciplinasTerca[5] = null;
        horariosTerca[5] = null;
        tercaHorarioF.setText("");
        tercaDisciplinaF.setText("");
        tercaCodigoProfessorF.setText("");
        tercaProfessorF.setText("");
    }//GEN-LAST:event_btCurso115ActionPerformed

    private void btCurso116ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso116ActionPerformed
        professoresTerca[4] = null;
        disciplinasTerca[4] = null;
        horariosTerca[4] = null;
        tercaHorarioE.setText("");
        tercaDisciplinaE.setText("");
        tercaCodigoProfessorE.setText("");
        tercaProfessorE.setText("");
    }//GEN-LAST:event_btCurso116ActionPerformed

    private void btCurso117ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso117ActionPerformed
        professoresTerca[3] = null;
        disciplinasTerca[3] = null;
        horariosTerca[3] = null;
        tercaHorarioD.setText("");
        tercaDisciplinaD.setText("");
        tercaCodigoProfessorD.setText("");
        tercaProfessorD.setText("");
    }//GEN-LAST:event_btCurso117ActionPerformed

    private void btCurso118ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso118ActionPerformed
        professoresTerca[2] = null;
        disciplinasTerca[2] = null;
        horariosTerca[2] = null;
        tercaHorarioC.setText("");
        tercaDisciplinaC.setText("");
        tercaCodigoProfessorC.setText("");
        tercaProfessorC.setText("");
    }//GEN-LAST:event_btCurso118ActionPerformed

    private void btCurso119ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso119ActionPerformed
        professoresTerca[1] = null;
        disciplinasTerca[1] = null;
        horariosTerca[1] = null;
        tercaHorarioB.setText("");
        tercaDisciplinaB.setText("");
        tercaCodigoProfessorB.setText("");
        tercaProfessorB.setText("");
    }//GEN-LAST:event_btCurso119ActionPerformed

    private void btCurso120ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso120ActionPerformed
        professoresTerca[0] = null;
        disciplinasTerca[0] = null;
        horariosTerca[0] = null;
        tercaHorarioA.setText("");
        tercaDisciplinaA.setText("");
        tercaCodigoProfessorA.setText("");
        tercaProfessorA.setText("");
    }//GEN-LAST:event_btCurso120ActionPerformed

    private void btCurso121ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso121ActionPerformed
        professoresQuarta[5] = null;
        disciplinasQuarta[5] = null;
        horariosQuarta[5] = null;
        quartaHorarioF.setText("");
        quartaDisciplinaF.setText("");
        quartaCodigoProfessorF.setText("");
        quartaProfessorF.setText("");
    }//GEN-LAST:event_btCurso121ActionPerformed

    private void btCurso122ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso122ActionPerformed
        professoresQuarta[4] = null;
        disciplinasQuarta[4] = null;
        horariosQuarta[4] = null;
        quartaHorarioE.setText("");
        quartaDisciplinaE.setText("");
        quartaCodigoProfessorE.setText("");
        quartaProfessorE.setText("");
    }//GEN-LAST:event_btCurso122ActionPerformed

    private void btCurso123ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso123ActionPerformed
        professoresQuarta[3] = null;
        disciplinasQuarta[3] = null;
        horariosQuarta[3] = null;
        quartaHorarioD.setText("");
        quartaDisciplinaD.setText("");
        quartaCodigoProfessorD.setText("");
        quartaProfessorD.setText("");
    }//GEN-LAST:event_btCurso123ActionPerformed

    private void btCurso124ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso124ActionPerformed
        professoresQuarta[2] = null;
        disciplinasQuarta[2] = null;
        horariosQuarta[2] = null;
        quartaHorarioC.setText("");
        quartaDisciplinaC.setText("");
        quartaCodigoProfessorC.setText("");
        quartaProfessorC.setText("");
    }//GEN-LAST:event_btCurso124ActionPerformed

    private void btCurso125ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso125ActionPerformed
        professoresQuarta[1] = null;
        disciplinasQuarta[1] = null;
        horariosQuarta[1] = null;
        quartaHorarioB.setText("");
        quartaDisciplinaB.setText("");
        quartaCodigoProfessorB.setText("");
        quartaProfessorB.setText("");
    }//GEN-LAST:event_btCurso125ActionPerformed

    private void btCurso126ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso126ActionPerformed
        professoresQuarta[0] = null;
        disciplinasQuarta[0] = null;
        horariosQuarta[0] = null;
        quartaHorarioA.setText("");
        quartaDisciplinaA.setText("");
        quartaCodigoProfessorA.setText("");
        quartaProfessorA.setText("");
    }//GEN-LAST:event_btCurso126ActionPerformed

    private void btCurso127ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso127ActionPerformed
        professoresQuinta[5] = null;
        disciplinasQuinta[5] = null;
        horariosQuinta[5] = null;
        quintaHorarioF.setText("");
        quintaDisciplinaF.setText("");
        quintaCodigoProfessorF.setText("");
        quintaProfessorF.setText("");
    }//GEN-LAST:event_btCurso127ActionPerformed

    private void btCurso128ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso128ActionPerformed
        professoresQuinta[4] = null;
        disciplinasQuinta[4] = null;
        horariosQuinta[4] = null;
        quintaHorarioE.setText("");
        quintaDisciplinaE.setText("");
        quintaCodigoProfessorE.setText("");
        quintaProfessorE.setText("");
    }//GEN-LAST:event_btCurso128ActionPerformed

    private void btCurso129ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso129ActionPerformed
        professoresQuinta[3] = null;
        disciplinasQuinta[3] = null;
        horariosQuinta[3] = null;
        quintaHorarioD.setText("");
        quintaDisciplinaD.setText("");
        quintaCodigoProfessorD.setText("");
        quintaProfessorD.setText("");
    }//GEN-LAST:event_btCurso129ActionPerformed

    private void btCurso130ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso130ActionPerformed
        professoresQuinta[2] = null;
        disciplinasQuinta[2] = null;
        horariosQuinta[2] = null;
        quintaHorarioC.setText("");
        quintaDisciplinaC.setText("");
        quintaCodigoProfessorC.setText("");
        quintaProfessorC.setText("");
    }//GEN-LAST:event_btCurso130ActionPerformed

    private void btCurso131ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso131ActionPerformed
        professoresQuinta[1] = null;
        disciplinasQuinta[1] = null;
        horariosQuinta[1] = null;
        quintaHorarioB.setText("");
        quintaDisciplinaB.setText("");
        quintaCodigoProfessorB.setText("");
        quintaProfessorB.setText("");

    }//GEN-LAST:event_btCurso131ActionPerformed

    private void btCurso132ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso132ActionPerformed
        professoresQuinta[0] = null;
        disciplinasQuinta[0] = null;
        horariosQuinta[0] = null;
        quintaHorarioA.setText("");
        quintaDisciplinaA.setText("");
        quintaCodigoProfessorA.setText("");
        quintaProfessorA.setText("");
    }//GEN-LAST:event_btCurso132ActionPerformed

    private void btCurso133ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso133ActionPerformed
        professoresSexta[5] = null;
        disciplinasSexta[5] = null;
        horariosSexta[5] = null;
        sextaHorarioF.setText("");
        sextaDisciplinaF.setText("");
        sextaCodigoProfessorF.setText("");
        sextaProfessorF.setText("");
    }//GEN-LAST:event_btCurso133ActionPerformed

    private void btCurso134ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso134ActionPerformed
        professoresSexta[4] = null;
        disciplinasSexta[4] = null;
        horariosSexta[4] = null;
        sextaHorarioE.setText("");
        sextaDisciplinaE.setText("");
        sextaCodigoProfessorE.setText("");
        sextaProfessorE.setText("");
    }//GEN-LAST:event_btCurso134ActionPerformed

    private void btCurso135ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso135ActionPerformed
        professoresSexta[3] = null;
        disciplinasSexta[3] = null;
        horariosSexta[3] = null;
        sextaHorarioD.setText("");
        sextaDisciplinaD.setText("");
        sextaCodigoProfessorD.setText("");
        sextaProfessorD.setText("");
    }//GEN-LAST:event_btCurso135ActionPerformed

    private void btCurso136ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso136ActionPerformed
        professoresSexta[2] = null;
        disciplinasSexta[2] = null;
        horariosSexta[2] = null;
        sextaHorarioC.setText("");
        sextaDisciplinaC.setText("");
        sextaCodigoProfessorC.setText("");
        sextaProfessorC.setText("");

    }//GEN-LAST:event_btCurso136ActionPerformed

    private void btCurso137ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso137ActionPerformed
        professoresSexta[1] = null;
        disciplinasSexta[1] = null;
        horariosSexta[1] = null;
        sextaHorarioB.setText("");
        sextaDisciplinaB.setText("");
        sextaCodigoProfessorB.setText("");
        sextaProfessorB.setText("");
    }//GEN-LAST:event_btCurso137ActionPerformed

    private void btCurso138ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso138ActionPerformed
        professoresSexta[0] = null;
        disciplinasSexta[0] = null;
        horariosSexta[0] = null;
        sextaHorarioA.setText("");
        sextaDisciplinaA.setText("");
        sextaCodigoProfessorA.setText("");
        sextaProfessorA.setText("");
    }//GEN-LAST:event_btCurso138ActionPerformed

    private void btCurso139ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso139ActionPerformed
        professoresSabado[5] = null;
        disciplinasSabado[5] = null;
        horariosSabado[5] = null;
        sabadoHorarioF.setText("");
        sabadoDisciplinaF.setText("");
        sabadoCodigoProfessorF.setText("");
        sabadoProfessorF.setText("");
    }//GEN-LAST:event_btCurso139ActionPerformed

    private void btCurso140ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso140ActionPerformed
        professoresSabado[4] = null;
        disciplinasSabado[4] = null;
        horariosSabado[4] = null;
        sabadoHorarioE.setText("");
        sabadoDisciplinaE.setText("");
        sabadoCodigoProfessorE.setText("");
        sabadoProfessorE.setText("");

    }//GEN-LAST:event_btCurso140ActionPerformed

    private void btCurso141ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso141ActionPerformed
        professoresSabado[3] = null;
        disciplinasSabado[3] = null;
        horariosSabado[3] = null;
        sabadoHorarioD.setText("");
        sabadoDisciplinaD.setText("");
        sabadoCodigoProfessorD.setText("");
        sabadoProfessorD.setText("");
    }//GEN-LAST:event_btCurso141ActionPerformed

    private void btCurso142ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso142ActionPerformed
        professoresSabado[2] = null;
        disciplinasSabado[2] = null;
        horariosSabado[2] = null;
        sabadoHorarioC.setText("");
        sabadoDisciplinaC.setText("");
        sabadoCodigoProfessorC.setText("");
        sabadoProfessorC.setText("");
    }//GEN-LAST:event_btCurso142ActionPerformed

    private void btCurso143ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso143ActionPerformed
        professoresSabado[1] = null;
        disciplinasSabado[1] = null;
        horariosSabado[1] = null;
        sabadoHorarioB.setText("");
        sabadoDisciplinaB.setText("");
        sabadoCodigoProfessorB.setText("");
        sabadoProfessorB.setText("");
    }//GEN-LAST:event_btCurso143ActionPerformed

    private void btCurso144ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso144ActionPerformed
        professoresSabado[0] = null;
        disciplinasSabado[0] = null;
        horariosSabado[0] = null;
        sabadoHorarioA.setText("");
        sabadoDisciplinaA.setText("");
        sabadoCodigoProfessorA.setText("");
        sabadoProfessorA.setText("");
    }//GEN-LAST:event_btCurso144ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastroQuadroHorarios.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroQuadroHorarios.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroQuadroHorarios.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroQuadroHorarios.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadastroQuadroHorarios dialog = new CadastroQuadroHorarios(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCurso;
    private javax.swing.JButton btCurso1;
    private javax.swing.JButton btCurso10;
    private javax.swing.JButton btCurso100;
    private javax.swing.JButton btCurso101;
    private javax.swing.JButton btCurso102;
    private javax.swing.JButton btCurso103;
    private javax.swing.JButton btCurso104;
    private javax.swing.JButton btCurso105;
    private javax.swing.JButton btCurso106;
    private javax.swing.JButton btCurso107;
    private javax.swing.JButton btCurso108;
    private javax.swing.JButton btCurso109;
    private javax.swing.JButton btCurso11;
    private javax.swing.JButton btCurso110;
    private javax.swing.JButton btCurso111;
    private javax.swing.JButton btCurso112;
    private javax.swing.JButton btCurso113;
    private javax.swing.JButton btCurso114;
    private javax.swing.JButton btCurso115;
    private javax.swing.JButton btCurso116;
    private javax.swing.JButton btCurso117;
    private javax.swing.JButton btCurso118;
    private javax.swing.JButton btCurso119;
    private javax.swing.JButton btCurso12;
    private javax.swing.JButton btCurso120;
    private javax.swing.JButton btCurso121;
    private javax.swing.JButton btCurso122;
    private javax.swing.JButton btCurso123;
    private javax.swing.JButton btCurso124;
    private javax.swing.JButton btCurso125;
    private javax.swing.JButton btCurso126;
    private javax.swing.JButton btCurso127;
    private javax.swing.JButton btCurso128;
    private javax.swing.JButton btCurso129;
    private javax.swing.JButton btCurso13;
    private javax.swing.JButton btCurso130;
    private javax.swing.JButton btCurso131;
    private javax.swing.JButton btCurso132;
    private javax.swing.JButton btCurso133;
    private javax.swing.JButton btCurso134;
    private javax.swing.JButton btCurso135;
    private javax.swing.JButton btCurso136;
    private javax.swing.JButton btCurso137;
    private javax.swing.JButton btCurso138;
    private javax.swing.JButton btCurso139;
    private javax.swing.JButton btCurso14;
    private javax.swing.JButton btCurso140;
    private javax.swing.JButton btCurso141;
    private javax.swing.JButton btCurso142;
    private javax.swing.JButton btCurso143;
    private javax.swing.JButton btCurso144;
    private javax.swing.JButton btCurso15;
    private javax.swing.JButton btCurso16;
    private javax.swing.JButton btCurso17;
    private javax.swing.JButton btCurso18;
    private javax.swing.JButton btCurso19;
    private javax.swing.JButton btCurso2;
    private javax.swing.JButton btCurso20;
    private javax.swing.JButton btCurso21;
    private javax.swing.JButton btCurso22;
    private javax.swing.JButton btCurso23;
    private javax.swing.JButton btCurso24;
    private javax.swing.JButton btCurso25;
    private javax.swing.JButton btCurso26;
    private javax.swing.JButton btCurso27;
    private javax.swing.JButton btCurso28;
    private javax.swing.JButton btCurso29;
    private javax.swing.JButton btCurso3;
    private javax.swing.JButton btCurso30;
    private javax.swing.JButton btCurso31;
    private javax.swing.JButton btCurso32;
    private javax.swing.JButton btCurso33;
    private javax.swing.JButton btCurso34;
    private javax.swing.JButton btCurso35;
    private javax.swing.JButton btCurso36;
    private javax.swing.JButton btCurso37;
    private javax.swing.JButton btCurso38;
    private javax.swing.JButton btCurso39;
    private javax.swing.JButton btCurso4;
    private javax.swing.JButton btCurso40;
    private javax.swing.JButton btCurso41;
    private javax.swing.JButton btCurso42;
    private javax.swing.JButton btCurso43;
    private javax.swing.JButton btCurso44;
    private javax.swing.JButton btCurso45;
    private javax.swing.JButton btCurso46;
    private javax.swing.JButton btCurso47;
    private javax.swing.JButton btCurso48;
    private javax.swing.JButton btCurso49;
    private javax.swing.JButton btCurso5;
    private javax.swing.JButton btCurso50;
    private javax.swing.JButton btCurso51;
    private javax.swing.JButton btCurso52;
    private javax.swing.JButton btCurso53;
    private javax.swing.JButton btCurso54;
    private javax.swing.JButton btCurso55;
    private javax.swing.JButton btCurso56;
    private javax.swing.JButton btCurso57;
    private javax.swing.JButton btCurso58;
    private javax.swing.JButton btCurso59;
    private javax.swing.JButton btCurso6;
    private javax.swing.JButton btCurso60;
    private javax.swing.JButton btCurso61;
    private javax.swing.JButton btCurso62;
    private javax.swing.JButton btCurso63;
    private javax.swing.JButton btCurso64;
    private javax.swing.JButton btCurso65;
    private javax.swing.JButton btCurso66;
    private javax.swing.JButton btCurso67;
    private javax.swing.JButton btCurso68;
    private javax.swing.JButton btCurso69;
    private javax.swing.JButton btCurso7;
    private javax.swing.JButton btCurso70;
    private javax.swing.JButton btCurso71;
    private javax.swing.JButton btCurso72;
    private javax.swing.JButton btCurso73;
    private javax.swing.JButton btCurso74;
    private javax.swing.JButton btCurso75;
    private javax.swing.JButton btCurso76;
    private javax.swing.JButton btCurso77;
    private javax.swing.JButton btCurso78;
    private javax.swing.JButton btCurso79;
    private javax.swing.JButton btCurso8;
    private javax.swing.JButton btCurso80;
    private javax.swing.JButton btCurso81;
    private javax.swing.JButton btCurso82;
    private javax.swing.JButton btCurso83;
    private javax.swing.JButton btCurso84;
    private javax.swing.JButton btCurso85;
    private javax.swing.JButton btCurso86;
    private javax.swing.JButton btCurso87;
    private javax.swing.JButton btCurso88;
    private javax.swing.JButton btCurso89;
    private javax.swing.JButton btCurso9;
    private javax.swing.JButton btCurso90;
    private javax.swing.JButton btCurso91;
    private javax.swing.JButton btCurso92;
    private javax.swing.JButton btCurso93;
    private javax.swing.JButton btCurso94;
    private javax.swing.JButton btCurso95;
    private javax.swing.JButton btCurso96;
    private javax.swing.JButton btCurso97;
    private javax.swing.JButton btCurso98;
    private javax.swing.JButton btCurso99;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btGrade;
    private javax.swing.JButton btLimpar;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JButton btSalvar;
    private javax.swing.JButton btSemestre;
    private javax.swing.JButton btVoltar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox jcAnoExercicio;
    private javax.swing.JComboBox jcTurno;
    private javax.swing.JLabel jlNome2;
    private javax.swing.JLabel jlNome3;
    private javax.swing.JLabel jlNome4;
    private javax.swing.JLabel jlNome5;
    private javax.swing.JLabel jlNome6;
    private javax.swing.JLabel jlQuadroHorario;
    private javax.swing.JLabel jlquadro;
    private javax.swing.JLabel jlquadro1;
    private javax.swing.JLabel jlquadro2;
    private javax.swing.JLabel jlquadro3;
    private javax.swing.JLabel jlquadro4;
    private javax.swing.JLabel jlquadro5;
    private javax.swing.JTextField quartaCodigoProfessorA;
    private javax.swing.JTextField quartaCodigoProfessorB;
    private javax.swing.JTextField quartaCodigoProfessorC;
    private javax.swing.JTextField quartaCodigoProfessorD;
    private javax.swing.JTextField quartaCodigoProfessorE;
    private javax.swing.JTextField quartaCodigoProfessorF;
    private javax.swing.JTextField quartaDisciplinaA;
    private javax.swing.JTextField quartaDisciplinaB;
    private javax.swing.JTextField quartaDisciplinaC;
    private javax.swing.JTextField quartaDisciplinaD;
    private javax.swing.JTextField quartaDisciplinaE;
    private javax.swing.JTextField quartaDisciplinaF;
    private javax.swing.JTextField quartaHorarioA;
    private javax.swing.JTextField quartaHorarioB;
    private javax.swing.JTextField quartaHorarioC;
    private javax.swing.JTextField quartaHorarioD;
    private javax.swing.JTextField quartaHorarioE;
    private javax.swing.JTextField quartaHorarioF;
    private javax.swing.JTextField quartaProfessorA;
    private javax.swing.JTextField quartaProfessorB;
    private javax.swing.JTextField quartaProfessorC;
    private javax.swing.JTextField quartaProfessorD;
    private javax.swing.JTextField quartaProfessorE;
    private javax.swing.JTextField quartaProfessorF;
    private javax.swing.JTextField quintaCodigoProfessorA;
    private javax.swing.JTextField quintaCodigoProfessorB;
    private javax.swing.JTextField quintaCodigoProfessorC;
    private javax.swing.JTextField quintaCodigoProfessorD;
    private javax.swing.JTextField quintaCodigoProfessorE;
    private javax.swing.JTextField quintaCodigoProfessorF;
    private javax.swing.JTextField quintaDisciplinaA;
    private javax.swing.JTextField quintaDisciplinaB;
    private javax.swing.JTextField quintaDisciplinaC;
    private javax.swing.JTextField quintaDisciplinaD;
    private javax.swing.JTextField quintaDisciplinaE;
    private javax.swing.JTextField quintaDisciplinaF;
    private javax.swing.JTextField quintaHorarioA;
    private javax.swing.JTextField quintaHorarioB;
    private javax.swing.JTextField quintaHorarioC;
    private javax.swing.JTextField quintaHorarioD;
    private javax.swing.JTextField quintaHorarioE;
    private javax.swing.JTextField quintaHorarioF;
    private javax.swing.JTextField quintaProfessorA;
    private javax.swing.JTextField quintaProfessorB;
    private javax.swing.JTextField quintaProfessorC;
    private javax.swing.JTextField quintaProfessorD;
    private javax.swing.JTextField quintaProfessorE;
    private javax.swing.JTextField quintaProfessorF;
    private javax.swing.JTextField sabadoCodigoProfessorA;
    private javax.swing.JTextField sabadoCodigoProfessorB;
    private javax.swing.JTextField sabadoCodigoProfessorC;
    private javax.swing.JTextField sabadoCodigoProfessorD;
    private javax.swing.JTextField sabadoCodigoProfessorE;
    private javax.swing.JTextField sabadoCodigoProfessorF;
    private javax.swing.JTextField sabadoDisciplinaA;
    private javax.swing.JTextField sabadoDisciplinaB;
    private javax.swing.JTextField sabadoDisciplinaC;
    private javax.swing.JTextField sabadoDisciplinaD;
    private javax.swing.JTextField sabadoDisciplinaE;
    private javax.swing.JTextField sabadoDisciplinaF;
    private javax.swing.JTextField sabadoHorarioA;
    private javax.swing.JTextField sabadoHorarioB;
    private javax.swing.JTextField sabadoHorarioC;
    private javax.swing.JTextField sabadoHorarioD;
    private javax.swing.JTextField sabadoHorarioE;
    private javax.swing.JTextField sabadoHorarioF;
    private javax.swing.JTextField sabadoProfessorA;
    private javax.swing.JTextField sabadoProfessorB;
    private javax.swing.JTextField sabadoProfessorC;
    private javax.swing.JTextField sabadoProfessorD;
    private javax.swing.JTextField sabadoProfessorE;
    private javax.swing.JTextField sabadoProfessorF;
    private javax.swing.JTextField segundaCodigoProfessorA;
    private javax.swing.JTextField segundaCodigoProfessorB;
    private javax.swing.JTextField segundaCodigoProfessorC;
    private javax.swing.JTextField segundaCodigoProfessorD;
    private javax.swing.JTextField segundaCodigoProfessorE;
    private javax.swing.JTextField segundaCodigoProfessorF;
    private javax.swing.JTextField segundaDisciplinaA;
    private javax.swing.JTextField segundaDisciplinaB;
    private javax.swing.JTextField segundaDisciplinaC;
    private javax.swing.JTextField segundaDisciplinaD;
    private javax.swing.JTextField segundaDisciplinaE;
    private javax.swing.JTextField segundaDisciplinaF;
    private javax.swing.JTextField segundaHorarioA;
    private javax.swing.JTextField segundaHorarioB;
    private javax.swing.JTextField segundaHorarioC;
    private javax.swing.JTextField segundaHorarioD;
    private javax.swing.JTextField segundaHorarioE;
    private javax.swing.JTextField segundaHorarioF;
    private javax.swing.JTextField segundaProfessorA;
    private javax.swing.JTextField segundaProfessorB;
    private javax.swing.JTextField segundaProfessorC;
    private javax.swing.JTextField segundaProfessorD;
    private javax.swing.JTextField segundaProfessorE;
    private javax.swing.JTextField segundaProfessorF;
    private javax.swing.JTextField sextaCodigoProfessorA;
    private javax.swing.JTextField sextaCodigoProfessorB;
    private javax.swing.JTextField sextaCodigoProfessorC;
    private javax.swing.JTextField sextaCodigoProfessorD;
    private javax.swing.JTextField sextaCodigoProfessorE;
    private javax.swing.JTextField sextaCodigoProfessorF;
    private javax.swing.JTextField sextaDisciplinaA;
    private javax.swing.JTextField sextaDisciplinaB;
    private javax.swing.JTextField sextaDisciplinaC;
    private javax.swing.JTextField sextaDisciplinaD;
    private javax.swing.JTextField sextaDisciplinaE;
    private javax.swing.JTextField sextaDisciplinaF;
    private javax.swing.JTextField sextaHorarioA;
    private javax.swing.JTextField sextaHorarioB;
    private javax.swing.JTextField sextaHorarioC;
    private javax.swing.JTextField sextaHorarioD;
    private javax.swing.JTextField sextaHorarioE;
    private javax.swing.JTextField sextaHorarioF;
    private javax.swing.JTextField sextaProfessorA;
    private javax.swing.JTextField sextaProfessorB;
    private javax.swing.JTextField sextaProfessorC;
    private javax.swing.JTextField sextaProfessorD;
    private javax.swing.JTextField sextaProfessorE;
    private javax.swing.JTextField sextaProfessorF;
    private javax.swing.JTextField tercaCodigoProfessorA;
    private javax.swing.JTextField tercaCodigoProfessorB;
    private javax.swing.JTextField tercaCodigoProfessorC;
    private javax.swing.JTextField tercaCodigoProfessorD;
    private javax.swing.JTextField tercaCodigoProfessorE;
    private javax.swing.JTextField tercaCodigoProfessorF;
    private javax.swing.JTextField tercaDisciplinaA;
    private javax.swing.JTextField tercaDisciplinaB;
    private javax.swing.JTextField tercaDisciplinaC;
    private javax.swing.JTextField tercaDisciplinaD;
    private javax.swing.JTextField tercaDisciplinaE;
    private javax.swing.JTextField tercaDisciplinaF;
    private javax.swing.JTextField tercaHorarioA;
    private javax.swing.JTextField tercaHorarioB;
    private javax.swing.JTextField tercaHorarioC;
    private javax.swing.JTextField tercaHorarioD;
    private javax.swing.JTextField tercaHorarioE;
    private javax.swing.JTextField tercaHorarioF;
    private javax.swing.JTextField tercaProfessorA;
    private javax.swing.JTextField tercaProfessorB;
    private javax.swing.JTextField tercaProfessorC;
    private javax.swing.JTextField tercaProfessorD;
    private javax.swing.JTextField tercaProfessorE;
    private javax.swing.JTextField tercaProfessorF;
    private javax.swing.JTextField tfGradeCurricular;
    private javax.swing.JTextField tfNomeCurso;
    private javax.swing.JTextField tfNomeSemestre;
    // End of variables declaration//GEN-END:variables
}
