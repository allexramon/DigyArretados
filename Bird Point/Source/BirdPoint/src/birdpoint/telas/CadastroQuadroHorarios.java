/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.telas;

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
import birdpoint.semestre.Semestre;
import birdpoint.semestre.SemestreDAO;
import birdpoint.semestre.SemestreTableModel;
import birdpoint.util.Util;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author AlexRamon
 */
public class CadastroQuadroHorarios extends javax.swing.JDialog {

    Semestre semestre = new Semestre();
    SemestreDAO semestreDAO = new SemestreDAO();

    Disciplina disciplina = new Disciplina();
    DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

    GradeCurricular gradeCurricular = new GradeCurricular();
    GradeCurricularDAO gradeCurricularDAO = new GradeCurricularDAO();

    Horario horario = new Horario();
    HorarioDAO horarioDAO = new HorarioDAO();

    Professor professor = new Professor();
    ProfessorDAO professorDAO = new ProfessorDAO();

    Curso curso = new Curso();
    CursoDAO cursoDAO = new CursoDAO();

    List<Horario> listaHorarios;
    List<Professor> listaProfessores;
    List<Disciplina> listaDisciplinas;

    public CadastroQuadroHorarios(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        carregarListas();
    }

    public void carregarListas() {
        listaHorarios = (horarioDAO.listar());
        listaProfessores = (professorDAO.listar());
        listaDisciplinas = (disciplinaDAO.listar());
    }

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
                campo2.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione o Curso, Semestre, Grade, Turno e Ano Exercício!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

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
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione o Curso, Semestre, Grade, Turno e Ano Exercício!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void selecionarProfessor(JTextField campo1, JTextField campo2) {
        if (jcAnoExercicio.getSelectedIndex() != 0 && jcTurno.getSelectedIndex() != 0 && !tfGradeCurricular.equals("") && !tfNomeCurso.equals("") && !tfNomeSemestre.equals("")) {
            ProfessorTableModel itm = new ProfessorTableModel(listaProfessores);
            Object objetoRetorno = PesquisaGenerica.exibeTela(itm, "Professores");
            if (objetoRetorno != null) {
                professor = carregarProfessorLista((int) objetoRetorno);
                campo1.setText(String.valueOf(professor.getIdProfessor()));
                campo2.setText(professor.getNomeProfessor());
                campo2.setCaretPosition(0);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione o Curso, Semestre, Grade, Turno e Ano Exercício!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

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
                horario = horarioDAO.consultarObjetoId("idHorario", objetoRetorno);
                campo.setText(horario.getHoraInicio());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione o Curso, Semestre, Grade, Turno e Ano Exercício!", "Erro", JOptionPane.ERROR_MESSAGE);
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
        jlquadro = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        segundaDisciplinaC1 = new javax.swing.JTextField();
        segundaHorarioC1 = new javax.swing.JTextField();
        btCurso19 = new javax.swing.JButton();
        segundaDisciplinaA1 = new javax.swing.JTextField();
        btCurso20 = new javax.swing.JButton();
        btCurso21 = new javax.swing.JButton();
        segundaDisciplinaD1 = new javax.swing.JTextField();
        btCurso22 = new javax.swing.JButton();
        segundaCodigoProfessorA1 = new javax.swing.JTextField();
        btCurso23 = new javax.swing.JButton();
        segundaProfessorD1 = new javax.swing.JTextField();
        segundaHorarioB1 = new javax.swing.JTextField();
        btCurso24 = new javax.swing.JButton();
        segundaCodigoProfessorE1 = new javax.swing.JTextField();
        btCurso25 = new javax.swing.JButton();
        btCurso26 = new javax.swing.JButton();
        segundaCodigoProfessorB1 = new javax.swing.JTextField();
        btCurso27 = new javax.swing.JButton();
        btCurso28 = new javax.swing.JButton();
        btCurso29 = new javax.swing.JButton();
        segundaProfessorA1 = new javax.swing.JTextField();
        segundaHorarioA1 = new javax.swing.JTextField();
        segundaHorarioD1 = new javax.swing.JTextField();
        segundaCodigoProfessorF1 = new javax.swing.JTextField();
        segundaProfessorF1 = new javax.swing.JTextField();
        segundaCodigoProfessorC1 = new javax.swing.JTextField();
        btCurso30 = new javax.swing.JButton();
        btCurso31 = new javax.swing.JButton();
        btCurso32 = new javax.swing.JButton();
        segundaProfessorB1 = new javax.swing.JTextField();
        segundaHorarioE1 = new javax.swing.JTextField();
        btCurso33 = new javax.swing.JButton();
        segundaProfessorC1 = new javax.swing.JTextField();
        segundaHorarioF1 = new javax.swing.JTextField();
        segundaDisciplinaB1 = new javax.swing.JTextField();
        segundaDisciplinaF1 = new javax.swing.JTextField();
        btCurso34 = new javax.swing.JButton();
        btCurso35 = new javax.swing.JButton();
        btCurso36 = new javax.swing.JButton();
        segundaDisciplinaE1 = new javax.swing.JTextField();
        segundaCodigoProfessorD1 = new javax.swing.JTextField();
        segundaProfessorE1 = new javax.swing.JTextField();
        jlquadro1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        segundaDisciplinaC2 = new javax.swing.JTextField();
        segundaHorarioC2 = new javax.swing.JTextField();
        btCurso37 = new javax.swing.JButton();
        segundaDisciplinaA2 = new javax.swing.JTextField();
        btCurso38 = new javax.swing.JButton();
        btCurso39 = new javax.swing.JButton();
        segundaDisciplinaD2 = new javax.swing.JTextField();
        btCurso40 = new javax.swing.JButton();
        segundaCodigoProfessorA2 = new javax.swing.JTextField();
        btCurso41 = new javax.swing.JButton();
        segundaProfessorD2 = new javax.swing.JTextField();
        segundaHorarioB2 = new javax.swing.JTextField();
        btCurso42 = new javax.swing.JButton();
        segundaCodigoProfessorE2 = new javax.swing.JTextField();
        btCurso43 = new javax.swing.JButton();
        btCurso44 = new javax.swing.JButton();
        segundaCodigoProfessorB2 = new javax.swing.JTextField();
        btCurso45 = new javax.swing.JButton();
        btCurso46 = new javax.swing.JButton();
        btCurso47 = new javax.swing.JButton();
        segundaProfessorA2 = new javax.swing.JTextField();
        segundaHorarioA2 = new javax.swing.JTextField();
        segundaHorarioD2 = new javax.swing.JTextField();
        segundaCodigoProfessorF2 = new javax.swing.JTextField();
        segundaProfessorF2 = new javax.swing.JTextField();
        segundaCodigoProfessorC2 = new javax.swing.JTextField();
        btCurso48 = new javax.swing.JButton();
        btCurso49 = new javax.swing.JButton();
        btCurso50 = new javax.swing.JButton();
        segundaProfessorB2 = new javax.swing.JTextField();
        segundaHorarioE2 = new javax.swing.JTextField();
        btCurso51 = new javax.swing.JButton();
        segundaProfessorC2 = new javax.swing.JTextField();
        segundaHorarioF2 = new javax.swing.JTextField();
        segundaDisciplinaB2 = new javax.swing.JTextField();
        segundaDisciplinaF2 = new javax.swing.JTextField();
        btCurso52 = new javax.swing.JButton();
        btCurso53 = new javax.swing.JButton();
        btCurso54 = new javax.swing.JButton();
        segundaDisciplinaE2 = new javax.swing.JTextField();
        segundaCodigoProfessorD2 = new javax.swing.JTextField();
        segundaProfessorE2 = new javax.swing.JTextField();
        jlquadro2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        segundaDisciplinaC3 = new javax.swing.JTextField();
        segundaHorarioC3 = new javax.swing.JTextField();
        btCurso55 = new javax.swing.JButton();
        segundaDisciplinaA3 = new javax.swing.JTextField();
        btCurso56 = new javax.swing.JButton();
        btCurso57 = new javax.swing.JButton();
        segundaDisciplinaD3 = new javax.swing.JTextField();
        btCurso58 = new javax.swing.JButton();
        segundaCodigoProfessorA3 = new javax.swing.JTextField();
        btCurso59 = new javax.swing.JButton();
        segundaProfessorD3 = new javax.swing.JTextField();
        segundaHorarioB3 = new javax.swing.JTextField();
        btCurso60 = new javax.swing.JButton();
        segundaCodigoProfessorE3 = new javax.swing.JTextField();
        btCurso61 = new javax.swing.JButton();
        btCurso62 = new javax.swing.JButton();
        segundaCodigoProfessorB3 = new javax.swing.JTextField();
        btCurso63 = new javax.swing.JButton();
        btCurso64 = new javax.swing.JButton();
        btCurso65 = new javax.swing.JButton();
        segundaProfessorA3 = new javax.swing.JTextField();
        segundaHorarioA3 = new javax.swing.JTextField();
        segundaHorarioD3 = new javax.swing.JTextField();
        segundaCodigoProfessorF3 = new javax.swing.JTextField();
        segundaProfessorF3 = new javax.swing.JTextField();
        segundaCodigoProfessorC3 = new javax.swing.JTextField();
        btCurso66 = new javax.swing.JButton();
        btCurso67 = new javax.swing.JButton();
        btCurso68 = new javax.swing.JButton();
        segundaProfessorB3 = new javax.swing.JTextField();
        segundaHorarioE3 = new javax.swing.JTextField();
        btCurso69 = new javax.swing.JButton();
        segundaProfessorC3 = new javax.swing.JTextField();
        segundaHorarioF3 = new javax.swing.JTextField();
        segundaDisciplinaB3 = new javax.swing.JTextField();
        segundaDisciplinaF3 = new javax.swing.JTextField();
        btCurso70 = new javax.swing.JButton();
        btCurso71 = new javax.swing.JButton();
        btCurso72 = new javax.swing.JButton();
        segundaDisciplinaE3 = new javax.swing.JTextField();
        segundaCodigoProfessorD3 = new javax.swing.JTextField();
        segundaProfessorE3 = new javax.swing.JTextField();
        jlquadro3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        segundaDisciplinaC4 = new javax.swing.JTextField();
        segundaHorarioC4 = new javax.swing.JTextField();
        btCurso73 = new javax.swing.JButton();
        segundaDisciplinaA4 = new javax.swing.JTextField();
        btCurso74 = new javax.swing.JButton();
        btCurso75 = new javax.swing.JButton();
        segundaDisciplinaD4 = new javax.swing.JTextField();
        btCurso76 = new javax.swing.JButton();
        segundaCodigoProfessorA4 = new javax.swing.JTextField();
        btCurso77 = new javax.swing.JButton();
        segundaProfessorD4 = new javax.swing.JTextField();
        segundaHorarioB4 = new javax.swing.JTextField();
        btCurso78 = new javax.swing.JButton();
        segundaCodigoProfessorE4 = new javax.swing.JTextField();
        btCurso79 = new javax.swing.JButton();
        btCurso80 = new javax.swing.JButton();
        segundaCodigoProfessorB4 = new javax.swing.JTextField();
        btCurso81 = new javax.swing.JButton();
        btCurso82 = new javax.swing.JButton();
        btCurso83 = new javax.swing.JButton();
        segundaProfessorA4 = new javax.swing.JTextField();
        segundaHorarioA4 = new javax.swing.JTextField();
        segundaHorarioD4 = new javax.swing.JTextField();
        segundaCodigoProfessorF4 = new javax.swing.JTextField();
        segundaProfessorF4 = new javax.swing.JTextField();
        segundaCodigoProfessorC4 = new javax.swing.JTextField();
        btCurso84 = new javax.swing.JButton();
        btCurso85 = new javax.swing.JButton();
        btCurso86 = new javax.swing.JButton();
        segundaProfessorB4 = new javax.swing.JTextField();
        segundaHorarioE4 = new javax.swing.JTextField();
        btCurso87 = new javax.swing.JButton();
        segundaProfessorC4 = new javax.swing.JTextField();
        segundaHorarioF4 = new javax.swing.JTextField();
        segundaDisciplinaB4 = new javax.swing.JTextField();
        segundaDisciplinaF4 = new javax.swing.JTextField();
        btCurso88 = new javax.swing.JButton();
        btCurso89 = new javax.swing.JButton();
        btCurso90 = new javax.swing.JButton();
        segundaDisciplinaE4 = new javax.swing.JTextField();
        segundaCodigoProfessorD4 = new javax.swing.JTextField();
        segundaProfessorE4 = new javax.swing.JTextField();
        jlquadro4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        segundaDisciplinaC5 = new javax.swing.JTextField();
        segundaHorarioC5 = new javax.swing.JTextField();
        btCurso91 = new javax.swing.JButton();
        segundaDisciplinaA5 = new javax.swing.JTextField();
        btCurso92 = new javax.swing.JButton();
        btCurso93 = new javax.swing.JButton();
        segundaDisciplinaD5 = new javax.swing.JTextField();
        btCurso94 = new javax.swing.JButton();
        segundaCodigoProfessorA5 = new javax.swing.JTextField();
        btCurso95 = new javax.swing.JButton();
        segundaProfessorD5 = new javax.swing.JTextField();
        segundaHorarioB5 = new javax.swing.JTextField();
        btCurso96 = new javax.swing.JButton();
        segundaCodigoProfessorE5 = new javax.swing.JTextField();
        btCurso97 = new javax.swing.JButton();
        btCurso98 = new javax.swing.JButton();
        segundaCodigoProfessorB5 = new javax.swing.JTextField();
        btCurso99 = new javax.swing.JButton();
        btCurso100 = new javax.swing.JButton();
        btCurso101 = new javax.swing.JButton();
        segundaProfessorA5 = new javax.swing.JTextField();
        segundaHorarioA5 = new javax.swing.JTextField();
        segundaHorarioD5 = new javax.swing.JTextField();
        segundaCodigoProfessorF5 = new javax.swing.JTextField();
        segundaProfessorF5 = new javax.swing.JTextField();
        segundaCodigoProfessorC5 = new javax.swing.JTextField();
        btCurso102 = new javax.swing.JButton();
        btCurso103 = new javax.swing.JButton();
        btCurso104 = new javax.swing.JButton();
        segundaProfessorB5 = new javax.swing.JTextField();
        segundaHorarioE5 = new javax.swing.JTextField();
        btCurso105 = new javax.swing.JButton();
        segundaProfessorC5 = new javax.swing.JTextField();
        segundaHorarioF5 = new javax.swing.JTextField();
        segundaDisciplinaB5 = new javax.swing.JTextField();
        segundaDisciplinaF5 = new javax.swing.JTextField();
        btCurso106 = new javax.swing.JButton();
        btCurso107 = new javax.swing.JButton();
        btCurso108 = new javax.swing.JButton();
        segundaDisciplinaE5 = new javax.swing.JTextField();
        segundaCodigoProfessorD5 = new javax.swing.JTextField();
        segundaProfessorE5 = new javax.swing.JTextField();
        jlquadro5 = new javax.swing.JLabel();
        jlNome6 = new javax.swing.JLabel();
        jlNome5 = new javax.swing.JLabel();
        jcAnoExercicio = new javax.swing.JComboBox();
        jlQuadroHorario = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 492));
        setMinimumSize(new java.awt.Dimension(800, 492));
        setModal(true);
        setUndecorated(true);
        getContentPane().setLayout(null);

        tfNomeCurso.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tfNomeCurso.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tfNomeCurso.setEnabled(false);
        getContentPane().add(tfNomeCurso);
        tfNomeCurso.setBounds(120, 70, 360, 30);

        jlNome2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlNome2.setText("Turno.:");
        getContentPane().add(jlNome2);
        jlNome2.setBounds(350, 110, 70, 30);

        tfGradeCurricular.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tfGradeCurricular.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tfGradeCurricular.setEnabled(false);
        getContentPane().add(tfGradeCurricular);
        tfGradeCurricular.setBounds(580, 70, 170, 30);

        jlNome3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlNome3.setText("Semestre.:");
        getContentPane().add(jlNome3);
        jlNome3.setBounds(20, 110, 100, 30);

        tfNomeSemestre.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
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

        jcTurno.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
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

        segundaDisciplinaC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        segundaCodigoProfessorB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        segundaProfessorB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorB.setEnabled(false);
        jPanel1.add(segundaProfessorB);
        segundaProfessorB.setBounds(430, 70, 290, 20);

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
        btCurso8.setBounds(720, 70, 30, 20);

        segundaDisciplinaD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        segundaDisciplinaE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        segundaDisciplinaF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        segundaDisciplinaB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        segundaDisciplinaA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        segundaProfessorC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorC.setEnabled(false);
        jPanel1.add(segundaProfessorC);
        segundaProfessorC.setBounds(430, 100, 290, 20);

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
        btCurso14.setBounds(720, 100, 30, 20);

        segundaProfessorD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorD.setEnabled(false);
        jPanel1.add(segundaProfessorD);
        segundaProfessorD.setBounds(430, 130, 290, 20);

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
        btCurso15.setBounds(720, 130, 30, 20);

        segundaProfessorE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorE.setEnabled(false);
        jPanel1.add(segundaProfessorE);
        segundaProfessorE.setBounds(430, 160, 290, 20);

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
        btCurso16.setBounds(720, 160, 30, 20);

        segundaProfessorF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorF.setEnabled(false);
        jPanel1.add(segundaProfessorF);
        segundaProfessorF.setBounds(430, 190, 290, 20);

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
        btCurso17.setBounds(720, 190, 30, 20);

        segundaProfessorA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorA.setEnabled(false);
        jPanel1.add(segundaProfessorA);
        segundaProfessorA.setBounds(430, 40, 290, 20);

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
        btCurso18.setBounds(720, 40, 30, 20);

        segundaHorarioA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioA.setEnabled(false);
        jPanel1.add(segundaHorarioA);
        segundaHorarioA.setBounds(40, 40, 40, 20);

        segundaCodigoProfessorC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        segundaCodigoProfessorD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        segundaCodigoProfessorE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        segundaCodigoProfessorF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        segundaCodigoProfessorA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        jlquadro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/quadro.png"))); // NOI18N
        jPanel1.add(jlquadro);
        jlquadro.setBounds(0, 0, 750, 230);

        jTabbedPane1.addTab("Segunda-Feira", jPanel1);

        jPanel2.setLayout(null);

        segundaDisciplinaC1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaC1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaC1.setEnabled(false);
        jPanel2.add(segundaDisciplinaC1);
        segundaDisciplinaC1.setBounds(110, 100, 240, 20);

        segundaHorarioC1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioC1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioC1.setEnabled(false);
        jPanel2.add(segundaHorarioC1);
        segundaHorarioC1.setBounds(40, 100, 40, 20);

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
        btCurso19.setBounds(720, 130, 30, 20);

        segundaDisciplinaA1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaA1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaA1.setEnabled(false);
        segundaDisciplinaA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaDisciplinaA1ActionPerformed(evt);
            }
        });
        jPanel2.add(segundaDisciplinaA1);
        segundaDisciplinaA1.setBounds(110, 40, 240, 20);

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
        btCurso21.setBounds(720, 70, 30, 20);

        segundaDisciplinaD1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaD1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaD1.setEnabled(false);
        jPanel2.add(segundaDisciplinaD1);
        segundaDisciplinaD1.setBounds(110, 130, 240, 20);

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

        segundaCodigoProfessorA1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorA1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorA1ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorA1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorA1KeyTyped(evt);
            }
        });
        jPanel2.add(segundaCodigoProfessorA1);
        segundaCodigoProfessorA1.setBounds(390, 40, 30, 20);

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

        segundaProfessorD1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorD1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorD1.setEnabled(false);
        jPanel2.add(segundaProfessorD1);
        segundaProfessorD1.setBounds(430, 130, 290, 20);

        segundaHorarioB1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioB1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioB1.setEnabled(false);
        jPanel2.add(segundaHorarioB1);
        segundaHorarioB1.setBounds(40, 70, 40, 19);

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

        segundaCodigoProfessorE1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorE1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorE1ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorE1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorE1KeyTyped(evt);
            }
        });
        jPanel2.add(segundaCodigoProfessorE1);
        segundaCodigoProfessorE1.setBounds(390, 160, 30, 20);

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

        segundaCodigoProfessorB1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorB1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorB1ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorB1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorB1KeyTyped(evt);
            }
        });
        jPanel2.add(segundaCodigoProfessorB1);
        segundaCodigoProfessorB1.setBounds(390, 70, 30, 20);

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
        btCurso28.setBounds(720, 190, 30, 20);

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

        segundaProfessorA1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorA1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorA1.setEnabled(false);
        jPanel2.add(segundaProfessorA1);
        segundaProfessorA1.setBounds(430, 40, 290, 20);

        segundaHorarioA1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioA1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioA1.setEnabled(false);
        jPanel2.add(segundaHorarioA1);
        segundaHorarioA1.setBounds(40, 40, 40, 20);

        segundaHorarioD1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioD1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioD1.setEnabled(false);
        jPanel2.add(segundaHorarioD1);
        segundaHorarioD1.setBounds(40, 130, 40, 20);

        segundaCodigoProfessorF1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorF1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorF1ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorF1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorF1KeyTyped(evt);
            }
        });
        jPanel2.add(segundaCodigoProfessorF1);
        segundaCodigoProfessorF1.setBounds(390, 190, 30, 20);

        segundaProfessorF1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorF1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorF1.setEnabled(false);
        jPanel2.add(segundaProfessorF1);
        segundaProfessorF1.setBounds(430, 190, 290, 20);

        segundaCodigoProfessorC1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorC1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorC1ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorC1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorC1KeyTyped(evt);
            }
        });
        jPanel2.add(segundaCodigoProfessorC1);
        segundaCodigoProfessorC1.setBounds(390, 100, 30, 20);

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
        btCurso30.setBounds(720, 40, 30, 20);

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
        btCurso32.setBounds(720, 160, 30, 20);

        segundaProfessorB1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorB1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorB1.setEnabled(false);
        jPanel2.add(segundaProfessorB1);
        segundaProfessorB1.setBounds(430, 70, 290, 20);

        segundaHorarioE1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioE1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioE1.setEnabled(false);
        jPanel2.add(segundaHorarioE1);
        segundaHorarioE1.setBounds(40, 160, 40, 20);

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

        segundaProfessorC1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorC1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorC1.setEnabled(false);
        jPanel2.add(segundaProfessorC1);
        segundaProfessorC1.setBounds(430, 100, 290, 20);

        segundaHorarioF1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioF1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioF1.setEnabled(false);
        jPanel2.add(segundaHorarioF1);
        segundaHorarioF1.setBounds(40, 190, 40, 20);

        segundaDisciplinaB1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaB1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaB1.setEnabled(false);
        jPanel2.add(segundaDisciplinaB1);
        segundaDisciplinaB1.setBounds(110, 70, 240, 20);

        segundaDisciplinaF1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaF1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaF1.setEnabled(false);
        jPanel2.add(segundaDisciplinaF1);
        segundaDisciplinaF1.setBounds(110, 190, 240, 20);

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
        btCurso36.setBounds(720, 100, 30, 20);

        segundaDisciplinaE1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaE1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaE1.setEnabled(false);
        jPanel2.add(segundaDisciplinaE1);
        segundaDisciplinaE1.setBounds(110, 160, 240, 20);

        segundaCodigoProfessorD1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorD1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorD1ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorD1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorD1KeyTyped(evt);
            }
        });
        jPanel2.add(segundaCodigoProfessorD1);
        segundaCodigoProfessorD1.setBounds(390, 130, 30, 20);

        segundaProfessorE1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorE1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorE1.setEnabled(false);
        jPanel2.add(segundaProfessorE1);
        segundaProfessorE1.setBounds(430, 160, 290, 20);

        jlquadro1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/quadro.png"))); // NOI18N
        jPanel2.add(jlquadro1);
        jlquadro1.setBounds(0, 0, 750, 230);

        jTabbedPane1.addTab("Terça-Feira", jPanel2);

        jPanel3.setLayout(null);

        segundaDisciplinaC2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaC2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaC2.setEnabled(false);
        jPanel3.add(segundaDisciplinaC2);
        segundaDisciplinaC2.setBounds(110, 100, 240, 20);

        segundaHorarioC2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioC2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioC2.setEnabled(false);
        jPanel3.add(segundaHorarioC2);
        segundaHorarioC2.setBounds(40, 100, 40, 20);

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
        btCurso37.setBounds(720, 130, 30, 20);

        segundaDisciplinaA2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaA2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaA2.setEnabled(false);
        segundaDisciplinaA2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaDisciplinaA2ActionPerformed(evt);
            }
        });
        jPanel3.add(segundaDisciplinaA2);
        segundaDisciplinaA2.setBounds(110, 40, 240, 20);

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
        btCurso39.setBounds(720, 70, 30, 20);

        segundaDisciplinaD2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaD2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaD2.setEnabled(false);
        jPanel3.add(segundaDisciplinaD2);
        segundaDisciplinaD2.setBounds(110, 130, 240, 20);

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

        segundaCodigoProfessorA2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorA2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorA2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorA2ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorA2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorA2KeyTyped(evt);
            }
        });
        jPanel3.add(segundaCodigoProfessorA2);
        segundaCodigoProfessorA2.setBounds(390, 40, 30, 20);

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

        segundaProfessorD2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorD2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorD2.setEnabled(false);
        jPanel3.add(segundaProfessorD2);
        segundaProfessorD2.setBounds(430, 130, 290, 20);

        segundaHorarioB2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioB2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioB2.setEnabled(false);
        jPanel3.add(segundaHorarioB2);
        segundaHorarioB2.setBounds(40, 70, 40, 19);

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

        segundaCodigoProfessorE2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorE2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorE2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorE2ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorE2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorE2KeyTyped(evt);
            }
        });
        jPanel3.add(segundaCodigoProfessorE2);
        segundaCodigoProfessorE2.setBounds(390, 160, 30, 20);

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

        segundaCodigoProfessorB2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorB2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorB2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorB2ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorB2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorB2KeyTyped(evt);
            }
        });
        jPanel3.add(segundaCodigoProfessorB2);
        segundaCodigoProfessorB2.setBounds(390, 70, 30, 20);

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
        btCurso46.setBounds(720, 190, 30, 20);

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

        segundaProfessorA2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorA2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorA2.setEnabled(false);
        jPanel3.add(segundaProfessorA2);
        segundaProfessorA2.setBounds(430, 40, 290, 20);

        segundaHorarioA2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioA2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioA2.setEnabled(false);
        jPanel3.add(segundaHorarioA2);
        segundaHorarioA2.setBounds(40, 40, 40, 20);

        segundaHorarioD2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioD2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioD2.setEnabled(false);
        jPanel3.add(segundaHorarioD2);
        segundaHorarioD2.setBounds(40, 130, 40, 20);

        segundaCodigoProfessorF2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorF2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorF2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorF2ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorF2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorF2KeyTyped(evt);
            }
        });
        jPanel3.add(segundaCodigoProfessorF2);
        segundaCodigoProfessorF2.setBounds(390, 190, 30, 20);

        segundaProfessorF2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorF2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorF2.setEnabled(false);
        jPanel3.add(segundaProfessorF2);
        segundaProfessorF2.setBounds(430, 190, 290, 20);

        segundaCodigoProfessorC2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorC2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorC2ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorC2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorC2KeyTyped(evt);
            }
        });
        jPanel3.add(segundaCodigoProfessorC2);
        segundaCodigoProfessorC2.setBounds(390, 100, 30, 20);

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
        btCurso48.setBounds(720, 40, 30, 20);

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
        btCurso50.setBounds(720, 160, 30, 20);

        segundaProfessorB2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorB2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorB2.setEnabled(false);
        jPanel3.add(segundaProfessorB2);
        segundaProfessorB2.setBounds(430, 70, 290, 20);

        segundaHorarioE2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioE2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioE2.setEnabled(false);
        jPanel3.add(segundaHorarioE2);
        segundaHorarioE2.setBounds(40, 160, 40, 20);

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

        segundaProfessorC2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorC2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorC2.setEnabled(false);
        jPanel3.add(segundaProfessorC2);
        segundaProfessorC2.setBounds(430, 100, 290, 20);

        segundaHorarioF2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioF2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioF2.setEnabled(false);
        jPanel3.add(segundaHorarioF2);
        segundaHorarioF2.setBounds(40, 190, 40, 20);

        segundaDisciplinaB2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaB2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaB2.setEnabled(false);
        jPanel3.add(segundaDisciplinaB2);
        segundaDisciplinaB2.setBounds(110, 70, 240, 20);

        segundaDisciplinaF2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaF2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaF2.setEnabled(false);
        jPanel3.add(segundaDisciplinaF2);
        segundaDisciplinaF2.setBounds(110, 190, 240, 20);

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
        btCurso54.setBounds(720, 100, 30, 20);

        segundaDisciplinaE2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaE2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaE2.setEnabled(false);
        jPanel3.add(segundaDisciplinaE2);
        segundaDisciplinaE2.setBounds(110, 160, 240, 20);

        segundaCodigoProfessorD2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorD2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorD2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorD2ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorD2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorD2KeyTyped(evt);
            }
        });
        jPanel3.add(segundaCodigoProfessorD2);
        segundaCodigoProfessorD2.setBounds(390, 130, 30, 20);

        segundaProfessorE2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorE2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorE2.setEnabled(false);
        jPanel3.add(segundaProfessorE2);
        segundaProfessorE2.setBounds(430, 160, 290, 20);

        jlquadro2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/quadro.png"))); // NOI18N
        jPanel3.add(jlquadro2);
        jlquadro2.setBounds(0, 0, 750, 230);

        jTabbedPane1.addTab("Quarta-Feira", jPanel3);

        jPanel4.setLayout(null);

        segundaDisciplinaC3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaC3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaC3.setEnabled(false);
        jPanel4.add(segundaDisciplinaC3);
        segundaDisciplinaC3.setBounds(110, 100, 240, 20);

        segundaHorarioC3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioC3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioC3.setEnabled(false);
        jPanel4.add(segundaHorarioC3);
        segundaHorarioC3.setBounds(40, 100, 40, 20);

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
        btCurso55.setBounds(720, 130, 30, 20);

        segundaDisciplinaA3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaA3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaA3.setEnabled(false);
        segundaDisciplinaA3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaDisciplinaA3ActionPerformed(evt);
            }
        });
        jPanel4.add(segundaDisciplinaA3);
        segundaDisciplinaA3.setBounds(110, 40, 240, 20);

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
        btCurso57.setBounds(720, 70, 30, 20);

        segundaDisciplinaD3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaD3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaD3.setEnabled(false);
        jPanel4.add(segundaDisciplinaD3);
        segundaDisciplinaD3.setBounds(110, 130, 240, 20);

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

        segundaCodigoProfessorA3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorA3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorA3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorA3ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorA3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorA3KeyTyped(evt);
            }
        });
        jPanel4.add(segundaCodigoProfessorA3);
        segundaCodigoProfessorA3.setBounds(390, 40, 30, 20);

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

        segundaProfessorD3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorD3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorD3.setEnabled(false);
        jPanel4.add(segundaProfessorD3);
        segundaProfessorD3.setBounds(430, 130, 290, 20);

        segundaHorarioB3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioB3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioB3.setEnabled(false);
        jPanel4.add(segundaHorarioB3);
        segundaHorarioB3.setBounds(40, 70, 40, 19);

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

        segundaCodigoProfessorE3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorE3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorE3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorE3ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorE3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorE3KeyTyped(evt);
            }
        });
        jPanel4.add(segundaCodigoProfessorE3);
        segundaCodigoProfessorE3.setBounds(390, 160, 30, 20);

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

        segundaCodigoProfessorB3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorB3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorB3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorB3ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorB3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorB3KeyTyped(evt);
            }
        });
        jPanel4.add(segundaCodigoProfessorB3);
        segundaCodigoProfessorB3.setBounds(390, 70, 30, 20);

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
        btCurso64.setBounds(720, 190, 30, 20);

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

        segundaProfessorA3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorA3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorA3.setEnabled(false);
        jPanel4.add(segundaProfessorA3);
        segundaProfessorA3.setBounds(430, 40, 290, 20);

        segundaHorarioA3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioA3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioA3.setEnabled(false);
        jPanel4.add(segundaHorarioA3);
        segundaHorarioA3.setBounds(40, 40, 40, 20);

        segundaHorarioD3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioD3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioD3.setEnabled(false);
        jPanel4.add(segundaHorarioD3);
        segundaHorarioD3.setBounds(40, 130, 40, 20);

        segundaCodigoProfessorF3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorF3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorF3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorF3ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorF3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorF3KeyTyped(evt);
            }
        });
        jPanel4.add(segundaCodigoProfessorF3);
        segundaCodigoProfessorF3.setBounds(390, 190, 30, 20);

        segundaProfessorF3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorF3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorF3.setEnabled(false);
        jPanel4.add(segundaProfessorF3);
        segundaProfessorF3.setBounds(430, 190, 290, 20);

        segundaCodigoProfessorC3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorC3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorC3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorC3ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorC3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorC3KeyTyped(evt);
            }
        });
        jPanel4.add(segundaCodigoProfessorC3);
        segundaCodigoProfessorC3.setBounds(390, 100, 30, 20);

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
        btCurso66.setBounds(720, 40, 30, 20);

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
        btCurso68.setBounds(720, 160, 30, 20);

        segundaProfessorB3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorB3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorB3.setEnabled(false);
        jPanel4.add(segundaProfessorB3);
        segundaProfessorB3.setBounds(430, 70, 290, 20);

        segundaHorarioE3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioE3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioE3.setEnabled(false);
        jPanel4.add(segundaHorarioE3);
        segundaHorarioE3.setBounds(40, 160, 40, 20);

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

        segundaProfessorC3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorC3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorC3.setEnabled(false);
        jPanel4.add(segundaProfessorC3);
        segundaProfessorC3.setBounds(430, 100, 290, 20);

        segundaHorarioF3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioF3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioF3.setEnabled(false);
        jPanel4.add(segundaHorarioF3);
        segundaHorarioF3.setBounds(40, 190, 40, 20);

        segundaDisciplinaB3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaB3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaB3.setEnabled(false);
        jPanel4.add(segundaDisciplinaB3);
        segundaDisciplinaB3.setBounds(110, 70, 240, 20);

        segundaDisciplinaF3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaF3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaF3.setEnabled(false);
        jPanel4.add(segundaDisciplinaF3);
        segundaDisciplinaF3.setBounds(110, 190, 240, 20);

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
        btCurso72.setBounds(720, 100, 30, 20);

        segundaDisciplinaE3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaE3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaE3.setEnabled(false);
        jPanel4.add(segundaDisciplinaE3);
        segundaDisciplinaE3.setBounds(110, 160, 240, 20);

        segundaCodigoProfessorD3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorD3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorD3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorD3ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorD3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorD3KeyTyped(evt);
            }
        });
        jPanel4.add(segundaCodigoProfessorD3);
        segundaCodigoProfessorD3.setBounds(390, 130, 30, 20);

        segundaProfessorE3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorE3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorE3.setEnabled(false);
        jPanel4.add(segundaProfessorE3);
        segundaProfessorE3.setBounds(430, 160, 290, 20);

        jlquadro3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/quadro.png"))); // NOI18N
        jPanel4.add(jlquadro3);
        jlquadro3.setBounds(0, 0, 750, 230);

        jTabbedPane1.addTab("Quinta-Feira", jPanel4);

        jPanel5.setLayout(null);

        segundaDisciplinaC4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaC4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaC4.setEnabled(false);
        jPanel5.add(segundaDisciplinaC4);
        segundaDisciplinaC4.setBounds(110, 100, 240, 20);

        segundaHorarioC4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioC4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioC4.setEnabled(false);
        jPanel5.add(segundaHorarioC4);
        segundaHorarioC4.setBounds(40, 100, 40, 20);

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
        btCurso73.setBounds(720, 130, 30, 20);

        segundaDisciplinaA4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaA4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaA4.setEnabled(false);
        segundaDisciplinaA4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaDisciplinaA4ActionPerformed(evt);
            }
        });
        jPanel5.add(segundaDisciplinaA4);
        segundaDisciplinaA4.setBounds(110, 40, 240, 20);

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
        btCurso75.setBounds(720, 70, 30, 20);

        segundaDisciplinaD4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaD4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaD4.setEnabled(false);
        jPanel5.add(segundaDisciplinaD4);
        segundaDisciplinaD4.setBounds(110, 130, 240, 20);

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

        segundaCodigoProfessorA4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorA4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorA4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorA4ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorA4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorA4KeyTyped(evt);
            }
        });
        jPanel5.add(segundaCodigoProfessorA4);
        segundaCodigoProfessorA4.setBounds(390, 40, 30, 20);

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

        segundaProfessorD4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorD4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorD4.setEnabled(false);
        jPanel5.add(segundaProfessorD4);
        segundaProfessorD4.setBounds(430, 130, 290, 20);

        segundaHorarioB4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioB4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioB4.setEnabled(false);
        jPanel5.add(segundaHorarioB4);
        segundaHorarioB4.setBounds(40, 70, 40, 19);

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

        segundaCodigoProfessorE4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorE4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorE4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorE4ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorE4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorE4KeyTyped(evt);
            }
        });
        jPanel5.add(segundaCodigoProfessorE4);
        segundaCodigoProfessorE4.setBounds(390, 160, 30, 20);

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

        segundaCodigoProfessorB4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorB4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorB4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorB4ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorB4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorB4KeyTyped(evt);
            }
        });
        jPanel5.add(segundaCodigoProfessorB4);
        segundaCodigoProfessorB4.setBounds(390, 70, 30, 20);

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
        btCurso82.setBounds(720, 190, 30, 20);

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

        segundaProfessorA4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorA4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorA4.setEnabled(false);
        jPanel5.add(segundaProfessorA4);
        segundaProfessorA4.setBounds(430, 40, 290, 20);

        segundaHorarioA4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioA4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioA4.setEnabled(false);
        jPanel5.add(segundaHorarioA4);
        segundaHorarioA4.setBounds(40, 40, 40, 20);

        segundaHorarioD4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioD4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioD4.setEnabled(false);
        jPanel5.add(segundaHorarioD4);
        segundaHorarioD4.setBounds(40, 130, 40, 20);

        segundaCodigoProfessorF4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorF4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorF4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorF4ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorF4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorF4KeyTyped(evt);
            }
        });
        jPanel5.add(segundaCodigoProfessorF4);
        segundaCodigoProfessorF4.setBounds(390, 190, 30, 20);

        segundaProfessorF4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorF4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorF4.setEnabled(false);
        jPanel5.add(segundaProfessorF4);
        segundaProfessorF4.setBounds(430, 190, 290, 20);

        segundaCodigoProfessorC4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorC4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorC4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorC4ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorC4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorC4KeyTyped(evt);
            }
        });
        jPanel5.add(segundaCodigoProfessorC4);
        segundaCodigoProfessorC4.setBounds(390, 100, 30, 20);

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
        btCurso84.setBounds(720, 40, 30, 20);

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
        btCurso86.setBounds(720, 160, 30, 20);

        segundaProfessorB4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorB4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorB4.setEnabled(false);
        jPanel5.add(segundaProfessorB4);
        segundaProfessorB4.setBounds(430, 70, 290, 20);

        segundaHorarioE4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioE4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioE4.setEnabled(false);
        jPanel5.add(segundaHorarioE4);
        segundaHorarioE4.setBounds(40, 160, 40, 20);

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

        segundaProfessorC4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorC4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorC4.setEnabled(false);
        jPanel5.add(segundaProfessorC4);
        segundaProfessorC4.setBounds(430, 100, 290, 20);

        segundaHorarioF4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioF4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioF4.setEnabled(false);
        jPanel5.add(segundaHorarioF4);
        segundaHorarioF4.setBounds(40, 190, 40, 20);

        segundaDisciplinaB4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaB4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaB4.setEnabled(false);
        jPanel5.add(segundaDisciplinaB4);
        segundaDisciplinaB4.setBounds(110, 70, 240, 20);

        segundaDisciplinaF4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaF4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaF4.setEnabled(false);
        jPanel5.add(segundaDisciplinaF4);
        segundaDisciplinaF4.setBounds(110, 190, 240, 20);

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
        btCurso90.setBounds(720, 100, 30, 20);

        segundaDisciplinaE4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaE4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaE4.setEnabled(false);
        jPanel5.add(segundaDisciplinaE4);
        segundaDisciplinaE4.setBounds(110, 160, 240, 20);

        segundaCodigoProfessorD4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorD4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorD4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorD4ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorD4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorD4KeyTyped(evt);
            }
        });
        jPanel5.add(segundaCodigoProfessorD4);
        segundaCodigoProfessorD4.setBounds(390, 130, 30, 20);

        segundaProfessorE4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorE4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorE4.setEnabled(false);
        jPanel5.add(segundaProfessorE4);
        segundaProfessorE4.setBounds(430, 160, 290, 20);

        jlquadro4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/quadro.png"))); // NOI18N
        jPanel5.add(jlquadro4);
        jlquadro4.setBounds(0, 0, 750, 230);

        jTabbedPane1.addTab("Sexta-Feira", jPanel5);

        jPanel6.setLayout(null);

        segundaDisciplinaC5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaC5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaC5.setEnabled(false);
        jPanel6.add(segundaDisciplinaC5);
        segundaDisciplinaC5.setBounds(110, 100, 240, 20);

        segundaHorarioC5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioC5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioC5.setEnabled(false);
        jPanel6.add(segundaHorarioC5);
        segundaHorarioC5.setBounds(40, 100, 40, 20);

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
        btCurso91.setBounds(720, 130, 30, 20);

        segundaDisciplinaA5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaA5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaA5.setEnabled(false);
        segundaDisciplinaA5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaDisciplinaA5ActionPerformed(evt);
            }
        });
        jPanel6.add(segundaDisciplinaA5);
        segundaDisciplinaA5.setBounds(110, 40, 240, 20);

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
        btCurso93.setBounds(720, 70, 30, 20);

        segundaDisciplinaD5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaD5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaD5.setEnabled(false);
        jPanel6.add(segundaDisciplinaD5);
        segundaDisciplinaD5.setBounds(110, 130, 240, 20);

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

        segundaCodigoProfessorA5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorA5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorA5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorA5ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorA5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorA5KeyTyped(evt);
            }
        });
        jPanel6.add(segundaCodigoProfessorA5);
        segundaCodigoProfessorA5.setBounds(390, 40, 30, 20);

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

        segundaProfessorD5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorD5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorD5.setEnabled(false);
        jPanel6.add(segundaProfessorD5);
        segundaProfessorD5.setBounds(430, 130, 290, 20);

        segundaHorarioB5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioB5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioB5.setEnabled(false);
        jPanel6.add(segundaHorarioB5);
        segundaHorarioB5.setBounds(40, 70, 40, 19);

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

        segundaCodigoProfessorE5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorE5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorE5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorE5ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorE5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorE5KeyTyped(evt);
            }
        });
        jPanel6.add(segundaCodigoProfessorE5);
        segundaCodigoProfessorE5.setBounds(390, 160, 30, 20);

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

        segundaCodigoProfessorB5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorB5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorB5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorB5ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorB5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorB5KeyTyped(evt);
            }
        });
        jPanel6.add(segundaCodigoProfessorB5);
        segundaCodigoProfessorB5.setBounds(390, 70, 30, 20);

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
        btCurso100.setBounds(720, 190, 30, 20);

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

        segundaProfessorA5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorA5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorA5.setEnabled(false);
        jPanel6.add(segundaProfessorA5);
        segundaProfessorA5.setBounds(430, 40, 290, 20);

        segundaHorarioA5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioA5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioA5.setEnabled(false);
        jPanel6.add(segundaHorarioA5);
        segundaHorarioA5.setBounds(40, 40, 40, 20);

        segundaHorarioD5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioD5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioD5.setEnabled(false);
        jPanel6.add(segundaHorarioD5);
        segundaHorarioD5.setBounds(40, 130, 40, 20);

        segundaCodigoProfessorF5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorF5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorF5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorF5ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorF5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorF5KeyTyped(evt);
            }
        });
        jPanel6.add(segundaCodigoProfessorF5);
        segundaCodigoProfessorF5.setBounds(390, 190, 30, 20);

        segundaProfessorF5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorF5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorF5.setEnabled(false);
        jPanel6.add(segundaProfessorF5);
        segundaProfessorF5.setBounds(430, 190, 290, 20);

        segundaCodigoProfessorC5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorC5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorC5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorC5ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorC5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorC5KeyTyped(evt);
            }
        });
        jPanel6.add(segundaCodigoProfessorC5);
        segundaCodigoProfessorC5.setBounds(390, 100, 30, 20);

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
        btCurso102.setBounds(720, 40, 30, 20);

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
        btCurso104.setBounds(720, 160, 30, 20);

        segundaProfessorB5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorB5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorB5.setEnabled(false);
        jPanel6.add(segundaProfessorB5);
        segundaProfessorB5.setBounds(430, 70, 290, 20);

        segundaHorarioE5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioE5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioE5.setEnabled(false);
        jPanel6.add(segundaHorarioE5);
        segundaHorarioE5.setBounds(40, 160, 40, 20);

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

        segundaProfessorC5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorC5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorC5.setEnabled(false);
        jPanel6.add(segundaProfessorC5);
        segundaProfessorC5.setBounds(430, 100, 290, 20);

        segundaHorarioF5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioF5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioF5.setEnabled(false);
        jPanel6.add(segundaHorarioF5);
        segundaHorarioF5.setBounds(40, 190, 40, 20);

        segundaDisciplinaB5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaB5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaB5.setEnabled(false);
        jPanel6.add(segundaDisciplinaB5);
        segundaDisciplinaB5.setBounds(110, 70, 240, 20);

        segundaDisciplinaF5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaF5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaF5.setEnabled(false);
        jPanel6.add(segundaDisciplinaF5);
        segundaDisciplinaF5.setBounds(110, 190, 240, 20);

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
        btCurso108.setBounds(720, 100, 30, 20);

        segundaDisciplinaE5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaE5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaE5.setEnabled(false);
        jPanel6.add(segundaDisciplinaE5);
        segundaDisciplinaE5.setBounds(110, 160, 240, 20);

        segundaCodigoProfessorD5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaCodigoProfessorD5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaCodigoProfessorD5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaCodigoProfessorD5ActionPerformed(evt);
            }
        });
        segundaCodigoProfessorD5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundaCodigoProfessorD5KeyTyped(evt);
            }
        });
        jPanel6.add(segundaCodigoProfessorD5);
        segundaCodigoProfessorD5.setBounds(390, 130, 30, 20);

        segundaProfessorE5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorE5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorE5.setEnabled(false);
        jPanel6.add(segundaProfessorE5);
        segundaProfessorE5.setBounds(430, 160, 290, 20);

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

        jcAnoExercicio.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jcAnoExercicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-----", "2016.2", "2017.1", "2017.2" }));
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
    }//GEN-LAST:event_btLimparActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed

    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed

    }//GEN-LAST:event_btExcluirActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed

    }//GEN-LAST:event_btSalvarActionPerformed

    private void btGradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGradeActionPerformed
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
    }//GEN-LAST:event_btCurso1ActionPerformed

    private void btCurso2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso2ActionPerformed
        selecionarHorario(segundaHorarioB);
    }//GEN-LAST:event_btCurso2ActionPerformed

    private void btCurso3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso3ActionPerformed
        selecionarHorario(segundaHorarioC);
    }//GEN-LAST:event_btCurso3ActionPerformed

    private void btCurso4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso4ActionPerformed
        selecionarHorario(segundaHorarioF);
    }//GEN-LAST:event_btCurso4ActionPerformed

    private void btCurso5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso5ActionPerformed
        selecionarHorario(segundaHorarioD);
    }//GEN-LAST:event_btCurso5ActionPerformed

    private void btCurso6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso6ActionPerformed
        selecionarHorario(segundaHorarioE);
    }//GEN-LAST:event_btCurso6ActionPerformed

    private void btCurso7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso7ActionPerformed
        selecionarHorario(segundaHorarioA);
    }//GEN-LAST:event_btCurso7ActionPerformed

    private void btCurso8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso8ActionPerformed
        selecionarProfessor(segundaCodigoProfessorB, segundaProfessorB);
    }//GEN-LAST:event_btCurso8ActionPerformed

    private void btCurso9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso9ActionPerformed
        selecionarDisciplina(segundaDisciplinaD);
    }//GEN-LAST:event_btCurso9ActionPerformed

    private void btCurso10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso10ActionPerformed
        selecionarDisciplina(segundaDisciplinaE);
    }//GEN-LAST:event_btCurso10ActionPerformed

    private void btCurso11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso11ActionPerformed
        selecionarDisciplina(segundaDisciplinaF);
    }//GEN-LAST:event_btCurso11ActionPerformed

    private void btCurso12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso12ActionPerformed
        selecionarDisciplina(segundaDisciplinaB);
    }//GEN-LAST:event_btCurso12ActionPerformed

    private void btCurso13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso13ActionPerformed
        selecionarDisciplina(segundaDisciplinaA);
    }//GEN-LAST:event_btCurso13ActionPerformed

    private void btCurso14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso14ActionPerformed
        selecionarProfessor(segundaCodigoProfessorC, segundaProfessorC);
    }//GEN-LAST:event_btCurso14ActionPerformed

    private void btCurso15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso15ActionPerformed
        selecionarProfessor(segundaCodigoProfessorD, segundaProfessorD);
    }//GEN-LAST:event_btCurso15ActionPerformed

    private void btCurso16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso16ActionPerformed
        selecionarProfessor(segundaCodigoProfessorE, segundaProfessorE);
    }//GEN-LAST:event_btCurso16ActionPerformed

    private void btCurso17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso17ActionPerformed
        selecionarProfessor(segundaCodigoProfessorF, segundaProfessorF);
    }//GEN-LAST:event_btCurso17ActionPerformed

    private void btCurso18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso18ActionPerformed
        selecionarProfessor(segundaCodigoProfessorA, segundaProfessorA);
    }//GEN-LAST:event_btCurso18ActionPerformed

    private void jcTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcTurnoActionPerformed
        // TODO add your handling code here:
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
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso19ActionPerformed

    private void segundaDisciplinaA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaDisciplinaA1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaDisciplinaA1ActionPerformed

    private void btCurso20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso20ActionPerformed

    private void btCurso21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso21ActionPerformed

    private void btCurso22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso22ActionPerformed

    private void segundaCodigoProfessorA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorA1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorA1ActionPerformed

    private void segundaCodigoProfessorA1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorA1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorA1KeyTyped

    private void btCurso23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso23ActionPerformed

    private void btCurso24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso24ActionPerformed

    private void segundaCodigoProfessorE1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorE1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorE1ActionPerformed

    private void segundaCodigoProfessorE1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorE1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorE1KeyTyped

    private void btCurso25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso25ActionPerformed

    private void btCurso26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso26ActionPerformed

    private void segundaCodigoProfessorB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorB1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorB1ActionPerformed

    private void segundaCodigoProfessorB1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorB1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorB1KeyTyped

    private void btCurso27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso27ActionPerformed

    private void btCurso28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso28ActionPerformed

    private void btCurso29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso29ActionPerformed

    private void segundaCodigoProfessorF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorF1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorF1ActionPerformed

    private void segundaCodigoProfessorF1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorF1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorF1KeyTyped

    private void segundaCodigoProfessorC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorC1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorC1ActionPerformed

    private void segundaCodigoProfessorC1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorC1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorC1KeyTyped

    private void btCurso30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso30ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso30ActionPerformed

    private void btCurso31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso31ActionPerformed

    private void btCurso32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso32ActionPerformed

    private void btCurso33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso33ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso33ActionPerformed

    private void btCurso34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso34ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso34ActionPerformed

    private void btCurso35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso35ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso35ActionPerformed

    private void btCurso36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso36ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso36ActionPerformed

    private void segundaCodigoProfessorD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorD1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorD1ActionPerformed

    private void segundaCodigoProfessorD1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorD1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorD1KeyTyped

    private void btCurso37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso37ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso37ActionPerformed

    private void segundaDisciplinaA2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaDisciplinaA2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaDisciplinaA2ActionPerformed

    private void btCurso38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso38ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso38ActionPerformed

    private void btCurso39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso39ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso39ActionPerformed

    private void btCurso40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso40ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso40ActionPerformed

    private void segundaCodigoProfessorA2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorA2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorA2ActionPerformed

    private void segundaCodigoProfessorA2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorA2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorA2KeyTyped

    private void btCurso41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso41ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso41ActionPerformed

    private void btCurso42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso42ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso42ActionPerformed

    private void segundaCodigoProfessorE2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorE2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorE2ActionPerformed

    private void segundaCodigoProfessorE2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorE2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorE2KeyTyped

    private void btCurso43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso43ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso43ActionPerformed

    private void btCurso44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso44ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso44ActionPerformed

    private void segundaCodigoProfessorB2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorB2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorB2ActionPerformed

    private void segundaCodigoProfessorB2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorB2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorB2KeyTyped

    private void btCurso45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso45ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso45ActionPerformed

    private void btCurso46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso46ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso46ActionPerformed

    private void btCurso47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso47ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso47ActionPerformed

    private void segundaCodigoProfessorF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorF2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorF2ActionPerformed

    private void segundaCodigoProfessorF2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorF2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorF2KeyTyped

    private void segundaCodigoProfessorC2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorC2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorC2ActionPerformed

    private void segundaCodigoProfessorC2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorC2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorC2KeyTyped

    private void btCurso48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso48ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso48ActionPerformed

    private void btCurso49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso49ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso49ActionPerformed

    private void btCurso50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso50ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso50ActionPerformed

    private void btCurso51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso51ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso51ActionPerformed

    private void btCurso52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso52ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso52ActionPerformed

    private void btCurso53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso53ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso53ActionPerformed

    private void btCurso54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso54ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso54ActionPerformed

    private void segundaCodigoProfessorD2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorD2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorD2ActionPerformed

    private void segundaCodigoProfessorD2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorD2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorD2KeyTyped

    private void btCurso55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso55ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso55ActionPerformed

    private void segundaDisciplinaA3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaDisciplinaA3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaDisciplinaA3ActionPerformed

    private void btCurso56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso56ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso56ActionPerformed

    private void btCurso57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso57ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso57ActionPerformed

    private void btCurso58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso58ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso58ActionPerformed

    private void segundaCodigoProfessorA3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorA3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorA3ActionPerformed

    private void segundaCodigoProfessorA3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorA3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorA3KeyTyped

    private void btCurso59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso59ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso59ActionPerformed

    private void btCurso60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso60ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso60ActionPerformed

    private void segundaCodigoProfessorE3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorE3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorE3ActionPerformed

    private void segundaCodigoProfessorE3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorE3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorE3KeyTyped

    private void btCurso61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso61ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso61ActionPerformed

    private void btCurso62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso62ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso62ActionPerformed

    private void segundaCodigoProfessorB3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorB3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorB3ActionPerformed

    private void segundaCodigoProfessorB3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorB3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorB3KeyTyped

    private void btCurso63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso63ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso63ActionPerformed

    private void btCurso64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso64ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso64ActionPerformed

    private void btCurso65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso65ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso65ActionPerformed

    private void segundaCodigoProfessorF3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorF3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorF3ActionPerformed

    private void segundaCodigoProfessorF3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorF3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorF3KeyTyped

    private void segundaCodigoProfessorC3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorC3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorC3ActionPerformed

    private void segundaCodigoProfessorC3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorC3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorC3KeyTyped

    private void btCurso66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso66ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso66ActionPerformed

    private void btCurso67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso67ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso67ActionPerformed

    private void btCurso68ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso68ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso68ActionPerformed

    private void btCurso69ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso69ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso69ActionPerformed

    private void btCurso70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso70ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso70ActionPerformed

    private void btCurso71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso71ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso71ActionPerformed

    private void btCurso72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso72ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso72ActionPerformed

    private void segundaCodigoProfessorD3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorD3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorD3ActionPerformed

    private void segundaCodigoProfessorD3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorD3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorD3KeyTyped

    private void btCurso73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso73ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso73ActionPerformed

    private void segundaDisciplinaA4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaDisciplinaA4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaDisciplinaA4ActionPerformed

    private void btCurso74ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso74ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso74ActionPerformed

    private void btCurso75ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso75ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso75ActionPerformed

    private void btCurso76ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso76ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso76ActionPerformed

    private void segundaCodigoProfessorA4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorA4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorA4ActionPerformed

    private void segundaCodigoProfessorA4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorA4KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorA4KeyTyped

    private void btCurso77ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso77ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso77ActionPerformed

    private void btCurso78ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso78ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso78ActionPerformed

    private void segundaCodigoProfessorE4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorE4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorE4ActionPerformed

    private void segundaCodigoProfessorE4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorE4KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorE4KeyTyped

    private void btCurso79ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso79ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso79ActionPerformed

    private void btCurso80ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso80ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso80ActionPerformed

    private void segundaCodigoProfessorB4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorB4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorB4ActionPerformed

    private void segundaCodigoProfessorB4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorB4KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorB4KeyTyped

    private void btCurso81ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso81ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso81ActionPerformed

    private void btCurso82ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso82ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso82ActionPerformed

    private void btCurso83ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso83ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso83ActionPerformed

    private void segundaCodigoProfessorF4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorF4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorF4ActionPerformed

    private void segundaCodigoProfessorF4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorF4KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorF4KeyTyped

    private void segundaCodigoProfessorC4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorC4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorC4ActionPerformed

    private void segundaCodigoProfessorC4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorC4KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorC4KeyTyped

    private void btCurso84ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso84ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso84ActionPerformed

    private void btCurso85ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso85ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso85ActionPerformed

    private void btCurso86ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso86ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso86ActionPerformed

    private void btCurso87ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso87ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso87ActionPerformed

    private void btCurso88ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso88ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso88ActionPerformed

    private void btCurso89ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso89ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso89ActionPerformed

    private void btCurso90ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso90ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso90ActionPerformed

    private void segundaCodigoProfessorD4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorD4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorD4ActionPerformed

    private void segundaCodigoProfessorD4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorD4KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorD4KeyTyped

    private void btCurso91ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso91ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso91ActionPerformed

    private void segundaDisciplinaA5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaDisciplinaA5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaDisciplinaA5ActionPerformed

    private void btCurso92ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso92ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso92ActionPerformed

    private void btCurso93ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso93ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso93ActionPerformed

    private void btCurso94ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso94ActionPerformed

    }//GEN-LAST:event_btCurso94ActionPerformed

    private void segundaCodigoProfessorA5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorA5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorA5ActionPerformed

    private void segundaCodigoProfessorA5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorA5KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorA5KeyTyped

    private void btCurso95ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso95ActionPerformed

    }//GEN-LAST:event_btCurso95ActionPerformed

    private void btCurso96ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso96ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso96ActionPerformed

    private void segundaCodigoProfessorE5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorE5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorE5ActionPerformed

    private void segundaCodigoProfessorE5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorE5KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorE5KeyTyped

    private void btCurso97ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso97ActionPerformed

    }//GEN-LAST:event_btCurso97ActionPerformed

    private void btCurso98ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso98ActionPerformed

    }//GEN-LAST:event_btCurso98ActionPerformed

    private void segundaCodigoProfessorB5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorB5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorB5ActionPerformed

    private void segundaCodigoProfessorB5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorB5KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorB5KeyTyped

    private void btCurso99ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso99ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso99ActionPerformed

    private void btCurso100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso100ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso100ActionPerformed

    private void btCurso101ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso101ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso101ActionPerformed

    private void segundaCodigoProfessorF5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorF5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorF5ActionPerformed

    private void segundaCodigoProfessorF5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorF5KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorF5KeyTyped

    private void segundaCodigoProfessorC5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorC5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorC5ActionPerformed

    private void segundaCodigoProfessorC5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorC5KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorC5KeyTyped

    private void btCurso102ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso102ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso102ActionPerformed

    private void btCurso103ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso103ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso103ActionPerformed

    private void btCurso104ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso104ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso104ActionPerformed

    private void btCurso105ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso105ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso105ActionPerformed

    private void btCurso106ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso106ActionPerformed

    }//GEN-LAST:event_btCurso106ActionPerformed

    private void btCurso107ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso107ActionPerformed

    }//GEN-LAST:event_btCurso107ActionPerformed

    private void btCurso108ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso108ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso108ActionPerformed

    private void segundaCodigoProfessorD5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorD5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorD5ActionPerformed

    private void segundaCodigoProfessorD5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorD5KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_segundaCodigoProfessorD5KeyTyped

    private void segundaCodigoProfessorAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorAFocusLost
        pesquisarProfessorCodigo(segundaCodigoProfessorA, segundaProfessorA);
    }//GEN-LAST:event_segundaCodigoProfessorAFocusLost

    private void segundaCodigoProfessorBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorBFocusLost
        pesquisarProfessorCodigo(segundaCodigoProfessorB, segundaProfessorB);
    }//GEN-LAST:event_segundaCodigoProfessorBFocusLost

    private void segundaCodigoProfessorCFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorCFocusLost
        pesquisarProfessorCodigo(segundaCodigoProfessorC, segundaProfessorC);
    }//GEN-LAST:event_segundaCodigoProfessorCFocusLost

    private void segundaCodigoProfessorDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorDFocusLost
        pesquisarProfessorCodigo(segundaCodigoProfessorD, segundaProfessorD);
    }//GEN-LAST:event_segundaCodigoProfessorDFocusLost

    private void segundaCodigoProfessorEFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorEFocusLost
        pesquisarProfessorCodigo(segundaCodigoProfessorE, segundaProfessorE);
    }//GEN-LAST:event_segundaCodigoProfessorEFocusLost

    private void segundaCodigoProfessorFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_segundaCodigoProfessorFFocusLost
        pesquisarProfessorCodigo(segundaCodigoProfessorF, segundaProfessorF);
    }//GEN-LAST:event_segundaCodigoProfessorFFocusLost

    private void jcAnoExercicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcAnoExercicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcAnoExercicioActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroQuadroHorarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroQuadroHorarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroQuadroHorarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroQuadroHorarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JButton btCurso11;
    private javax.swing.JButton btCurso12;
    private javax.swing.JButton btCurso13;
    private javax.swing.JButton btCurso14;
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
    private javax.swing.JTextField segundaCodigoProfessorA;
    private javax.swing.JTextField segundaCodigoProfessorA1;
    private javax.swing.JTextField segundaCodigoProfessorA2;
    private javax.swing.JTextField segundaCodigoProfessorA3;
    private javax.swing.JTextField segundaCodigoProfessorA4;
    private javax.swing.JTextField segundaCodigoProfessorA5;
    private javax.swing.JTextField segundaCodigoProfessorB;
    private javax.swing.JTextField segundaCodigoProfessorB1;
    private javax.swing.JTextField segundaCodigoProfessorB2;
    private javax.swing.JTextField segundaCodigoProfessorB3;
    private javax.swing.JTextField segundaCodigoProfessorB4;
    private javax.swing.JTextField segundaCodigoProfessorB5;
    private javax.swing.JTextField segundaCodigoProfessorC;
    private javax.swing.JTextField segundaCodigoProfessorC1;
    private javax.swing.JTextField segundaCodigoProfessorC2;
    private javax.swing.JTextField segundaCodigoProfessorC3;
    private javax.swing.JTextField segundaCodigoProfessorC4;
    private javax.swing.JTextField segundaCodigoProfessorC5;
    private javax.swing.JTextField segundaCodigoProfessorD;
    private javax.swing.JTextField segundaCodigoProfessorD1;
    private javax.swing.JTextField segundaCodigoProfessorD2;
    private javax.swing.JTextField segundaCodigoProfessorD3;
    private javax.swing.JTextField segundaCodigoProfessorD4;
    private javax.swing.JTextField segundaCodigoProfessorD5;
    private javax.swing.JTextField segundaCodigoProfessorE;
    private javax.swing.JTextField segundaCodigoProfessorE1;
    private javax.swing.JTextField segundaCodigoProfessorE2;
    private javax.swing.JTextField segundaCodigoProfessorE3;
    private javax.swing.JTextField segundaCodigoProfessorE4;
    private javax.swing.JTextField segundaCodigoProfessorE5;
    private javax.swing.JTextField segundaCodigoProfessorF;
    private javax.swing.JTextField segundaCodigoProfessorF1;
    private javax.swing.JTextField segundaCodigoProfessorF2;
    private javax.swing.JTextField segundaCodigoProfessorF3;
    private javax.swing.JTextField segundaCodigoProfessorF4;
    private javax.swing.JTextField segundaCodigoProfessorF5;
    private javax.swing.JTextField segundaDisciplinaA;
    private javax.swing.JTextField segundaDisciplinaA1;
    private javax.swing.JTextField segundaDisciplinaA2;
    private javax.swing.JTextField segundaDisciplinaA3;
    private javax.swing.JTextField segundaDisciplinaA4;
    private javax.swing.JTextField segundaDisciplinaA5;
    private javax.swing.JTextField segundaDisciplinaB;
    private javax.swing.JTextField segundaDisciplinaB1;
    private javax.swing.JTextField segundaDisciplinaB2;
    private javax.swing.JTextField segundaDisciplinaB3;
    private javax.swing.JTextField segundaDisciplinaB4;
    private javax.swing.JTextField segundaDisciplinaB5;
    private javax.swing.JTextField segundaDisciplinaC;
    private javax.swing.JTextField segundaDisciplinaC1;
    private javax.swing.JTextField segundaDisciplinaC2;
    private javax.swing.JTextField segundaDisciplinaC3;
    private javax.swing.JTextField segundaDisciplinaC4;
    private javax.swing.JTextField segundaDisciplinaC5;
    private javax.swing.JTextField segundaDisciplinaD;
    private javax.swing.JTextField segundaDisciplinaD1;
    private javax.swing.JTextField segundaDisciplinaD2;
    private javax.swing.JTextField segundaDisciplinaD3;
    private javax.swing.JTextField segundaDisciplinaD4;
    private javax.swing.JTextField segundaDisciplinaD5;
    private javax.swing.JTextField segundaDisciplinaE;
    private javax.swing.JTextField segundaDisciplinaE1;
    private javax.swing.JTextField segundaDisciplinaE2;
    private javax.swing.JTextField segundaDisciplinaE3;
    private javax.swing.JTextField segundaDisciplinaE4;
    private javax.swing.JTextField segundaDisciplinaE5;
    private javax.swing.JTextField segundaDisciplinaF;
    private javax.swing.JTextField segundaDisciplinaF1;
    private javax.swing.JTextField segundaDisciplinaF2;
    private javax.swing.JTextField segundaDisciplinaF3;
    private javax.swing.JTextField segundaDisciplinaF4;
    private javax.swing.JTextField segundaDisciplinaF5;
    private javax.swing.JTextField segundaHorarioA;
    private javax.swing.JTextField segundaHorarioA1;
    private javax.swing.JTextField segundaHorarioA2;
    private javax.swing.JTextField segundaHorarioA3;
    private javax.swing.JTextField segundaHorarioA4;
    private javax.swing.JTextField segundaHorarioA5;
    private javax.swing.JTextField segundaHorarioB;
    private javax.swing.JTextField segundaHorarioB1;
    private javax.swing.JTextField segundaHorarioB2;
    private javax.swing.JTextField segundaHorarioB3;
    private javax.swing.JTextField segundaHorarioB4;
    private javax.swing.JTextField segundaHorarioB5;
    private javax.swing.JTextField segundaHorarioC;
    private javax.swing.JTextField segundaHorarioC1;
    private javax.swing.JTextField segundaHorarioC2;
    private javax.swing.JTextField segundaHorarioC3;
    private javax.swing.JTextField segundaHorarioC4;
    private javax.swing.JTextField segundaHorarioC5;
    private javax.swing.JTextField segundaHorarioD;
    private javax.swing.JTextField segundaHorarioD1;
    private javax.swing.JTextField segundaHorarioD2;
    private javax.swing.JTextField segundaHorarioD3;
    private javax.swing.JTextField segundaHorarioD4;
    private javax.swing.JTextField segundaHorarioD5;
    private javax.swing.JTextField segundaHorarioE;
    private javax.swing.JTextField segundaHorarioE1;
    private javax.swing.JTextField segundaHorarioE2;
    private javax.swing.JTextField segundaHorarioE3;
    private javax.swing.JTextField segundaHorarioE4;
    private javax.swing.JTextField segundaHorarioE5;
    private javax.swing.JTextField segundaHorarioF;
    private javax.swing.JTextField segundaHorarioF1;
    private javax.swing.JTextField segundaHorarioF2;
    private javax.swing.JTextField segundaHorarioF3;
    private javax.swing.JTextField segundaHorarioF4;
    private javax.swing.JTextField segundaHorarioF5;
    private javax.swing.JTextField segundaProfessorA;
    private javax.swing.JTextField segundaProfessorA1;
    private javax.swing.JTextField segundaProfessorA2;
    private javax.swing.JTextField segundaProfessorA3;
    private javax.swing.JTextField segundaProfessorA4;
    private javax.swing.JTextField segundaProfessorA5;
    private javax.swing.JTextField segundaProfessorB;
    private javax.swing.JTextField segundaProfessorB1;
    private javax.swing.JTextField segundaProfessorB2;
    private javax.swing.JTextField segundaProfessorB3;
    private javax.swing.JTextField segundaProfessorB4;
    private javax.swing.JTextField segundaProfessorB5;
    private javax.swing.JTextField segundaProfessorC;
    private javax.swing.JTextField segundaProfessorC1;
    private javax.swing.JTextField segundaProfessorC2;
    private javax.swing.JTextField segundaProfessorC3;
    private javax.swing.JTextField segundaProfessorC4;
    private javax.swing.JTextField segundaProfessorC5;
    private javax.swing.JTextField segundaProfessorD;
    private javax.swing.JTextField segundaProfessorD1;
    private javax.swing.JTextField segundaProfessorD2;
    private javax.swing.JTextField segundaProfessorD3;
    private javax.swing.JTextField segundaProfessorD4;
    private javax.swing.JTextField segundaProfessorD5;
    private javax.swing.JTextField segundaProfessorE;
    private javax.swing.JTextField segundaProfessorE1;
    private javax.swing.JTextField segundaProfessorE2;
    private javax.swing.JTextField segundaProfessorE3;
    private javax.swing.JTextField segundaProfessorE4;
    private javax.swing.JTextField segundaProfessorE5;
    private javax.swing.JTextField segundaProfessorF;
    private javax.swing.JTextField segundaProfessorF1;
    private javax.swing.JTextField segundaProfessorF2;
    private javax.swing.JTextField segundaProfessorF3;
    private javax.swing.JTextField segundaProfessorF4;
    private javax.swing.JTextField segundaProfessorF5;
    private javax.swing.JTextField tfGradeCurricular;
    private javax.swing.JTextField tfNomeCurso;
    private javax.swing.JTextField tfNomeSemestre;
    // End of variables declaration//GEN-END:variables
}
