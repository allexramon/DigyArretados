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
import birdpoint.gradecurricular.GradeCurricular;
import birdpoint.gradecurricular.GradeCurricularDAO;
import birdpoint.gradecurricular.GradeCurricularTableModel;
import birdpoint.horario.Horario;
import birdpoint.horario.HorarioDAO;
import birdpoint.horario.HorarioTableModel;
import birdpoint.professor.Professor;
import birdpoint.professor.ProfessorDAO;
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

    public void selecionarHorario(JTextField campo) {
        if (jcTurno.getSelectedIndex() != 0) {
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
            JOptionPane.showMessageDialog(null, "Selecione o Turno!", "Erro", JOptionPane.ERROR_MESSAGE);
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
        segundaHorarioA = new javax.swing.JTextField();
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
        jlquadro = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        tercaHorarioA = new javax.swing.JTextField();
        btCurso19 = new javax.swing.JButton();
        tercaHorarioB = new javax.swing.JTextField();
        btCurso20 = new javax.swing.JButton();
        tercaHorarioC = new javax.swing.JTextField();
        btCurso21 = new javax.swing.JButton();
        tercaHorarioD = new javax.swing.JTextField();
        btCurso22 = new javax.swing.JButton();
        tercaHorarioE = new javax.swing.JTextField();
        btCurso23 = new javax.swing.JButton();
        tercaHorarioF = new javax.swing.JTextField();
        btCurso24 = new javax.swing.JButton();
        tercaDisciplinaA = new javax.swing.JTextField();
        btCurso25 = new javax.swing.JButton();
        tercaDisciplinaB = new javax.swing.JTextField();
        btCurso26 = new javax.swing.JButton();
        tercaDisciplinaC = new javax.swing.JTextField();
        btCurso27 = new javax.swing.JButton();
        tercaDisciplinaD = new javax.swing.JTextField();
        btCurso28 = new javax.swing.JButton();
        tercaDisciplinaE = new javax.swing.JTextField();
        btCurso29 = new javax.swing.JButton();
        tercaDisciplinaF = new javax.swing.JTextField();
        btCurso30 = new javax.swing.JButton();
        tercaProfessorA = new javax.swing.JTextField();
        btCurso31 = new javax.swing.JButton();
        tercaProfessorB = new javax.swing.JTextField();
        btCurso32 = new javax.swing.JButton();
        tercaProfessorC = new javax.swing.JTextField();
        btCurso33 = new javax.swing.JButton();
        tercaProfessorD = new javax.swing.JTextField();
        btCurso34 = new javax.swing.JButton();
        tercaProfessorE = new javax.swing.JTextField();
        btCurso35 = new javax.swing.JButton();
        tercaProfessorF = new javax.swing.JTextField();
        btCurso36 = new javax.swing.JButton();
        jlquadro6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        quartaHorarioA = new javax.swing.JTextField();
        btCurso37 = new javax.swing.JButton();
        quartaHorarioB = new javax.swing.JTextField();
        btCurso38 = new javax.swing.JButton();
        quartaHorarioC = new javax.swing.JTextField();
        btCurso39 = new javax.swing.JButton();
        quartaHorarioD = new javax.swing.JTextField();
        btCurso40 = new javax.swing.JButton();
        quartaHorarioE = new javax.swing.JTextField();
        btCurso41 = new javax.swing.JButton();
        quartaHorarioF = new javax.swing.JTextField();
        btCurso42 = new javax.swing.JButton();
        quartaDisciplinaA = new javax.swing.JTextField();
        btCurso43 = new javax.swing.JButton();
        quartaDisciplinaB = new javax.swing.JTextField();
        btCurso44 = new javax.swing.JButton();
        quartaDisciplinaC = new javax.swing.JTextField();
        btCurso45 = new javax.swing.JButton();
        quartaDisciplinaD = new javax.swing.JTextField();
        btCurso46 = new javax.swing.JButton();
        quartaDisciplinaE = new javax.swing.JTextField();
        btCurso47 = new javax.swing.JButton();
        quartaDisciplinaF = new javax.swing.JTextField();
        btCurso48 = new javax.swing.JButton();
        quartaProfessorA = new javax.swing.JTextField();
        btCurso49 = new javax.swing.JButton();
        quartaProfessorB = new javax.swing.JTextField();
        btCurso50 = new javax.swing.JButton();
        quartaProfessorC = new javax.swing.JTextField();
        btCurso51 = new javax.swing.JButton();
        quartaProfessorD = new javax.swing.JTextField();
        btCurso52 = new javax.swing.JButton();
        quartaProfessorE = new javax.swing.JTextField();
        btCurso53 = new javax.swing.JButton();
        quartaProfessorF = new javax.swing.JTextField();
        btCurso54 = new javax.swing.JButton();
        jlquadro7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        quintaHorarioA = new javax.swing.JTextField();
        btCurso55 = new javax.swing.JButton();
        quintaHorarioB = new javax.swing.JTextField();
        btCurso56 = new javax.swing.JButton();
        quintaHorarioC = new javax.swing.JTextField();
        btCurso57 = new javax.swing.JButton();
        quintaHorarioD = new javax.swing.JTextField();
        btCurso58 = new javax.swing.JButton();
        quintaHorarioE = new javax.swing.JTextField();
        btCurso59 = new javax.swing.JButton();
        quintaHorarioF = new javax.swing.JTextField();
        btCurso60 = new javax.swing.JButton();
        quintaDisciplinaA = new javax.swing.JTextField();
        btCurso61 = new javax.swing.JButton();
        quintaDisciplinaB = new javax.swing.JTextField();
        btCurso62 = new javax.swing.JButton();
        quintaDisciplinaC = new javax.swing.JTextField();
        btCurso63 = new javax.swing.JButton();
        quintaDisciplinaD = new javax.swing.JTextField();
        btCurso64 = new javax.swing.JButton();
        quintaDisciplinaE = new javax.swing.JTextField();
        btCurso65 = new javax.swing.JButton();
        quintaDisciplinaF = new javax.swing.JTextField();
        btCurso66 = new javax.swing.JButton();
        quintaProfessorA = new javax.swing.JTextField();
        btCurso67 = new javax.swing.JButton();
        quintaProfessorB = new javax.swing.JTextField();
        btCurso68 = new javax.swing.JButton();
        quintaProfessorC = new javax.swing.JTextField();
        btCurso69 = new javax.swing.JButton();
        quintaProfessorD = new javax.swing.JTextField();
        btCurso70 = new javax.swing.JButton();
        quintaProfessorE = new javax.swing.JTextField();
        btCurso71 = new javax.swing.JButton();
        quintaProfessorF = new javax.swing.JTextField();
        btCurso72 = new javax.swing.JButton();
        jlquadro8 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        sextaHorarioA = new javax.swing.JTextField();
        btCurso73 = new javax.swing.JButton();
        sextaHorarioB = new javax.swing.JTextField();
        btCurso74 = new javax.swing.JButton();
        sextaHorarioC = new javax.swing.JTextField();
        btCurso75 = new javax.swing.JButton();
        sextaHorarioD = new javax.swing.JTextField();
        btCurso76 = new javax.swing.JButton();
        sextaHorarioE = new javax.swing.JTextField();
        btCurso77 = new javax.swing.JButton();
        sextaHorarioF = new javax.swing.JTextField();
        btCurso78 = new javax.swing.JButton();
        sextaDisciplinaA = new javax.swing.JTextField();
        btCurso79 = new javax.swing.JButton();
        sextaDisciplinaB = new javax.swing.JTextField();
        btCurso80 = new javax.swing.JButton();
        sextaDisciplinaC = new javax.swing.JTextField();
        btCurso81 = new javax.swing.JButton();
        sextaDisciplinaD = new javax.swing.JTextField();
        btCurso82 = new javax.swing.JButton();
        sextaDisciplinaE = new javax.swing.JTextField();
        btCurso83 = new javax.swing.JButton();
        sextaDisciplinaF = new javax.swing.JTextField();
        btCurso84 = new javax.swing.JButton();
        sextaProfessorA = new javax.swing.JTextField();
        btCurso85 = new javax.swing.JButton();
        sextaProfessorB = new javax.swing.JTextField();
        btCurso86 = new javax.swing.JButton();
        sextaProfessorC = new javax.swing.JTextField();
        btCurso87 = new javax.swing.JButton();
        sextaProfessorD = new javax.swing.JTextField();
        btCurso88 = new javax.swing.JButton();
        sextaProfessorE = new javax.swing.JTextField();
        btCurso89 = new javax.swing.JButton();
        sextaProfessorF = new javax.swing.JTextField();
        btCurso90 = new javax.swing.JButton();
        jlquadro9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        sabadoHorarioA = new javax.swing.JTextField();
        btCurso91 = new javax.swing.JButton();
        sabadoHorarioB = new javax.swing.JTextField();
        btCurso92 = new javax.swing.JButton();
        sabadoHorarioC = new javax.swing.JTextField();
        btCurso93 = new javax.swing.JButton();
        sabadoHorarioD = new javax.swing.JTextField();
        btCurso94 = new javax.swing.JButton();
        sabadoHorarioE = new javax.swing.JTextField();
        btCurso95 = new javax.swing.JButton();
        sabadoHorarioF = new javax.swing.JTextField();
        btCurso96 = new javax.swing.JButton();
        sabadoDisciplinaA = new javax.swing.JTextField();
        btCurso97 = new javax.swing.JButton();
        sabadoDisciplinaB = new javax.swing.JTextField();
        btCurso98 = new javax.swing.JButton();
        sabadoDisciplinaC = new javax.swing.JTextField();
        btCurso99 = new javax.swing.JButton();
        sabadoDisciplinaD = new javax.swing.JTextField();
        btCurso100 = new javax.swing.JButton();
        sabadoDisciplinaE = new javax.swing.JTextField();
        btCurso101 = new javax.swing.JButton();
        sabadoDisciplinaF = new javax.swing.JTextField();
        btCurso102 = new javax.swing.JButton();
        sabadoProfessorA = new javax.swing.JTextField();
        btCurso103 = new javax.swing.JButton();
        sabadoProfessorB = new javax.swing.JTextField();
        btCurso104 = new javax.swing.JButton();
        sabadoProfessorC = new javax.swing.JTextField();
        btCurso105 = new javax.swing.JButton();
        sabadoProfessorD = new javax.swing.JTextField();
        btCurso106 = new javax.swing.JButton();
        sabadoProfessorE = new javax.swing.JTextField();
        btCurso107 = new javax.swing.JButton();
        sabadoProfessorF = new javax.swing.JTextField();
        btCurso108 = new javax.swing.JButton();
        jlquadro10 = new javax.swing.JLabel();
        jlNome5 = new javax.swing.JLabel();
        jlNome6 = new javax.swing.JLabel();
        jlQuadroHorario = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 520));
        setUndecorated(true);
        getContentPane().setLayout(null);

        tfNomeCurso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfNomeCurso.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tfNomeCurso.setEnabled(false);
        getContentPane().add(tfNomeCurso);
        tfNomeCurso.setBounds(110, 100, 270, 20);

        jlNome2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jlNome2.setText("Turno.:");
        getContentPane().add(jlNome2);
        jlNome2.setBounds(410, 130, 60, 30);

        tfGradeCurricular.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfGradeCurricular.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tfGradeCurricular.setEnabled(false);
        getContentPane().add(tfGradeCurricular);
        tfGradeCurricular.setBounds(470, 100, 80, 20);

        jlNome3.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jlNome3.setText("Semestre.:");
        getContentPane().add(jlNome3);
        jlNome3.setBounds(20, 120, 90, 30);

        tfNomeSemestre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfNomeSemestre.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tfNomeSemestre.setEnabled(false);
        getContentPane().add(tfNomeSemestre);
        tfNomeSemestre.setBounds(110, 130, 270, 20);

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
        btCurso.setBounds(380, 100, 30, 20);

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
        btSemestre.setBounds(380, 130, 30, 20);

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
        btVoltar.setBounds(30, 440, 80, 70);

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
        btLimpar.setBounds(190, 440, 80, 70);

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
        btPesquisar.setBounds(290, 440, 100, 69);

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
        btExcluir.setBounds(400, 440, 80, 70);

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
        btSalvar.setBounds(490, 440, 80, 70);

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
        btGrade.setBounds(550, 100, 30, 20);

        jcTurno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcTurno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-----", "Manhã", "Tarde", "Noite" }));
        jcTurno.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        jcTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcTurnoActionPerformed(evt);
            }
        });
        getContentPane().add(jcTurno);
        jcTurno.setBounds(470, 130, 80, 30);

        jlNome4.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jlNome4.setText("Curso.:");
        getContentPane().add(jlNome4);
        jlNome4.setBounds(50, 100, 60, 20);

        jTabbedPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(545, 302));

        jPanel1.setLayout(null);

        segundaDisciplinaC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaC.setEnabled(false);
        jPanel1.add(segundaDisciplinaC);
        segundaDisciplinaC.setBounds(100, 100, 220, 20);

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
        btCurso1.setBounds(320, 100, 30, 20);

        segundaHorarioB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioB.setEnabled(false);
        jPanel1.add(segundaHorarioB);
        segundaHorarioB.setBounds(30, 70, 40, 19);

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
        btCurso2.setBounds(70, 70, 30, 20);

        segundaHorarioC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioC.setEnabled(false);
        jPanel1.add(segundaHorarioC);
        segundaHorarioC.setBounds(30, 100, 40, 20);

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
        btCurso3.setBounds(70, 100, 30, 20);

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
        btCurso4.setBounds(70, 190, 30, 20);

        segundaHorarioF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioF.setEnabled(false);
        jPanel1.add(segundaHorarioF);
        segundaHorarioF.setBounds(30, 190, 40, 20);

        segundaHorarioD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioD.setEnabled(false);
        jPanel1.add(segundaHorarioD);
        segundaHorarioD.setBounds(30, 130, 40, 20);

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
        btCurso5.setBounds(70, 130, 30, 20);

        segundaHorarioE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioE.setEnabled(false);
        jPanel1.add(segundaHorarioE);
        segundaHorarioE.setBounds(30, 160, 40, 20);

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
        btCurso6.setBounds(70, 160, 30, 20);

        segundaHorarioA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaHorarioA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaHorarioA.setEnabled(false);
        jPanel1.add(segundaHorarioA);
        segundaHorarioA.setBounds(30, 40, 40, 20);

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
        btCurso7.setBounds(70, 40, 30, 20);

        segundaProfessorB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorB.setEnabled(false);
        jPanel1.add(segundaProfessorB);
        segundaProfessorB.setBounds(360, 70, 150, 20);

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
        btCurso8.setBounds(510, 70, 30, 20);

        segundaDisciplinaD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaD.setEnabled(false);
        jPanel1.add(segundaDisciplinaD);
        segundaDisciplinaD.setBounds(100, 130, 220, 20);

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
        btCurso9.setBounds(320, 130, 30, 20);

        segundaDisciplinaE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaE.setEnabled(false);
        jPanel1.add(segundaDisciplinaE);
        segundaDisciplinaE.setBounds(100, 160, 220, 20);

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
        btCurso10.setBounds(320, 160, 30, 20);

        segundaDisciplinaF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaF.setEnabled(false);
        jPanel1.add(segundaDisciplinaF);
        segundaDisciplinaF.setBounds(100, 190, 220, 20);

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
        btCurso11.setBounds(320, 190, 30, 20);

        segundaDisciplinaB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaB.setEnabled(false);
        jPanel1.add(segundaDisciplinaB);
        segundaDisciplinaB.setBounds(100, 70, 220, 20);

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
        btCurso12.setBounds(320, 70, 30, 20);

        segundaDisciplinaA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaDisciplinaA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaDisciplinaA.setEnabled(false);
        jPanel1.add(segundaDisciplinaA);
        segundaDisciplinaA.setBounds(100, 40, 220, 20);

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
        btCurso13.setBounds(320, 40, 30, 20);

        segundaProfessorC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorC.setEnabled(false);
        jPanel1.add(segundaProfessorC);
        segundaProfessorC.setBounds(360, 100, 150, 20);

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
        btCurso14.setBounds(510, 100, 30, 20);

        segundaProfessorD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorD.setEnabled(false);
        jPanel1.add(segundaProfessorD);
        segundaProfessorD.setBounds(360, 130, 150, 20);

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
        btCurso15.setBounds(510, 130, 30, 20);

        segundaProfessorE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorE.setEnabled(false);
        jPanel1.add(segundaProfessorE);
        segundaProfessorE.setBounds(360, 160, 150, 20);

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
        btCurso16.setBounds(510, 160, 30, 20);

        segundaProfessorF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorF.setEnabled(false);
        jPanel1.add(segundaProfessorF);
        segundaProfessorF.setBounds(360, 190, 150, 20);

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
        btCurso17.setBounds(510, 190, 30, 20);

        segundaProfessorA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        segundaProfessorA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        segundaProfessorA.setEnabled(false);
        jPanel1.add(segundaProfessorA);
        segundaProfessorA.setBounds(360, 40, 150, 20);

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
        btCurso18.setBounds(510, 40, 30, 20);

        jlquadro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/quadro1.png"))); // NOI18N
        jPanel1.add(jlquadro);
        jlquadro.setBounds(0, 0, 540, 230);

        jTabbedPane1.addTab("Segunda-Feira", jPanel1);

        jPanel2.setLayout(null);

        tercaHorarioA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tercaHorarioA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaHorarioA.setEnabled(false);
        jPanel2.add(tercaHorarioA);
        tercaHorarioA.setBounds(30, 40, 40, 20);

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
        btCurso19.setBounds(70, 40, 30, 20);

        tercaHorarioB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tercaHorarioB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaHorarioB.setEnabled(false);
        jPanel2.add(tercaHorarioB);
        tercaHorarioB.setBounds(30, 70, 40, 19);

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
        btCurso20.setBounds(70, 70, 30, 20);

        tercaHorarioC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tercaHorarioC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaHorarioC.setEnabled(false);
        jPanel2.add(tercaHorarioC);
        tercaHorarioC.setBounds(30, 100, 40, 20);

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
        btCurso21.setBounds(70, 100, 30, 20);

        tercaHorarioD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tercaHorarioD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaHorarioD.setEnabled(false);
        jPanel2.add(tercaHorarioD);
        tercaHorarioD.setBounds(30, 130, 40, 20);

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
        btCurso22.setBounds(70, 130, 30, 20);

        tercaHorarioE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tercaHorarioE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaHorarioE.setEnabled(false);
        jPanel2.add(tercaHorarioE);
        tercaHorarioE.setBounds(30, 160, 40, 20);

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
        btCurso23.setBounds(70, 160, 30, 20);

        tercaHorarioF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tercaHorarioF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaHorarioF.setEnabled(false);
        jPanel2.add(tercaHorarioF);
        tercaHorarioF.setBounds(30, 190, 40, 20);

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
        btCurso24.setBounds(70, 190, 30, 20);

        tercaDisciplinaA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tercaDisciplinaA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaDisciplinaA.setEnabled(false);
        jPanel2.add(tercaDisciplinaA);
        tercaDisciplinaA.setBounds(100, 40, 220, 20);

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
        btCurso25.setBounds(320, 40, 30, 20);

        tercaDisciplinaB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tercaDisciplinaB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaDisciplinaB.setEnabled(false);
        jPanel2.add(tercaDisciplinaB);
        tercaDisciplinaB.setBounds(100, 70, 220, 20);

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
        btCurso26.setBounds(320, 70, 30, 20);

        tercaDisciplinaC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tercaDisciplinaC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaDisciplinaC.setEnabled(false);
        jPanel2.add(tercaDisciplinaC);
        tercaDisciplinaC.setBounds(100, 100, 220, 20);

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
        btCurso27.setBounds(320, 100, 30, 20);

        tercaDisciplinaD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tercaDisciplinaD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaDisciplinaD.setEnabled(false);
        jPanel2.add(tercaDisciplinaD);
        tercaDisciplinaD.setBounds(100, 130, 220, 20);

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
        btCurso28.setBounds(320, 130, 30, 20);

        tercaDisciplinaE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tercaDisciplinaE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaDisciplinaE.setEnabled(false);
        jPanel2.add(tercaDisciplinaE);
        tercaDisciplinaE.setBounds(100, 160, 220, 20);

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
        btCurso29.setBounds(320, 160, 30, 20);

        tercaDisciplinaF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tercaDisciplinaF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaDisciplinaF.setEnabled(false);
        jPanel2.add(tercaDisciplinaF);
        tercaDisciplinaF.setBounds(100, 190, 220, 20);

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
        btCurso30.setBounds(320, 190, 30, 20);

        tercaProfessorA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tercaProfessorA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaProfessorA.setEnabled(false);
        jPanel2.add(tercaProfessorA);
        tercaProfessorA.setBounds(360, 40, 150, 20);

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
        btCurso31.setBounds(510, 40, 30, 20);

        tercaProfessorB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tercaProfessorB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaProfessorB.setEnabled(false);
        jPanel2.add(tercaProfessorB);
        tercaProfessorB.setBounds(360, 70, 150, 20);

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
        btCurso32.setBounds(510, 70, 30, 20);

        tercaProfessorC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tercaProfessorC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaProfessorC.setEnabled(false);
        jPanel2.add(tercaProfessorC);
        tercaProfessorC.setBounds(360, 100, 150, 20);

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
        btCurso33.setBounds(510, 100, 30, 20);

        tercaProfessorD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tercaProfessorD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaProfessorD.setEnabled(false);
        jPanel2.add(tercaProfessorD);
        tercaProfessorD.setBounds(360, 130, 150, 20);

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
        btCurso34.setBounds(510, 130, 30, 20);

        tercaProfessorE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tercaProfessorE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaProfessorE.setEnabled(false);
        jPanel2.add(tercaProfessorE);
        tercaProfessorE.setBounds(360, 160, 150, 20);

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
        btCurso35.setBounds(510, 160, 30, 20);

        tercaProfessorF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tercaProfessorF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tercaProfessorF.setEnabled(false);
        jPanel2.add(tercaProfessorF);
        tercaProfessorF.setBounds(360, 190, 150, 20);

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
        btCurso36.setBounds(510, 190, 30, 20);

        jlquadro6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/quadro1.png"))); // NOI18N
        jPanel2.add(jlquadro6);
        jlquadro6.setBounds(0, 0, 540, 230);

        jTabbedPane1.addTab("Terça-Feira", jPanel2);

        jPanel3.setLayout(null);

        quartaHorarioA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quartaHorarioA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaHorarioA.setEnabled(false);
        jPanel3.add(quartaHorarioA);
        quartaHorarioA.setBounds(30, 40, 40, 20);

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
        btCurso37.setBounds(70, 40, 30, 20);

        quartaHorarioB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quartaHorarioB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaHorarioB.setEnabled(false);
        jPanel3.add(quartaHorarioB);
        quartaHorarioB.setBounds(30, 70, 40, 19);

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
        btCurso38.setBounds(70, 70, 30, 20);

        quartaHorarioC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quartaHorarioC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaHorarioC.setEnabled(false);
        jPanel3.add(quartaHorarioC);
        quartaHorarioC.setBounds(30, 100, 40, 20);

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
        btCurso39.setBounds(70, 100, 30, 20);

        quartaHorarioD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quartaHorarioD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaHorarioD.setEnabled(false);
        jPanel3.add(quartaHorarioD);
        quartaHorarioD.setBounds(30, 130, 40, 20);

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
        btCurso40.setBounds(70, 130, 30, 20);

        quartaHorarioE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quartaHorarioE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaHorarioE.setEnabled(false);
        jPanel3.add(quartaHorarioE);
        quartaHorarioE.setBounds(30, 160, 40, 20);

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
        btCurso41.setBounds(70, 160, 30, 20);

        quartaHorarioF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quartaHorarioF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaHorarioF.setEnabled(false);
        jPanel3.add(quartaHorarioF);
        quartaHorarioF.setBounds(30, 190, 40, 20);

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
        btCurso42.setBounds(70, 190, 30, 20);

        quartaDisciplinaA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quartaDisciplinaA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaDisciplinaA.setEnabled(false);
        jPanel3.add(quartaDisciplinaA);
        quartaDisciplinaA.setBounds(100, 40, 220, 20);

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
        btCurso43.setBounds(320, 40, 30, 20);

        quartaDisciplinaB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quartaDisciplinaB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaDisciplinaB.setEnabled(false);
        jPanel3.add(quartaDisciplinaB);
        quartaDisciplinaB.setBounds(100, 70, 220, 20);

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
        btCurso44.setBounds(320, 70, 30, 20);

        quartaDisciplinaC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quartaDisciplinaC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaDisciplinaC.setEnabled(false);
        jPanel3.add(quartaDisciplinaC);
        quartaDisciplinaC.setBounds(100, 100, 220, 20);

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
        btCurso45.setBounds(320, 100, 30, 20);

        quartaDisciplinaD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quartaDisciplinaD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaDisciplinaD.setEnabled(false);
        jPanel3.add(quartaDisciplinaD);
        quartaDisciplinaD.setBounds(100, 130, 220, 20);

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
        btCurso46.setBounds(320, 130, 30, 20);

        quartaDisciplinaE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quartaDisciplinaE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaDisciplinaE.setEnabled(false);
        jPanel3.add(quartaDisciplinaE);
        quartaDisciplinaE.setBounds(100, 160, 220, 20);

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
        btCurso47.setBounds(320, 160, 30, 20);

        quartaDisciplinaF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quartaDisciplinaF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaDisciplinaF.setEnabled(false);
        jPanel3.add(quartaDisciplinaF);
        quartaDisciplinaF.setBounds(100, 190, 220, 20);

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
        btCurso48.setBounds(320, 190, 30, 20);

        quartaProfessorA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quartaProfessorA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaProfessorA.setEnabled(false);
        jPanel3.add(quartaProfessorA);
        quartaProfessorA.setBounds(360, 40, 150, 20);

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
        btCurso49.setBounds(510, 40, 30, 20);

        quartaProfessorB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quartaProfessorB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaProfessorB.setEnabled(false);
        jPanel3.add(quartaProfessorB);
        quartaProfessorB.setBounds(360, 70, 150, 20);

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
        btCurso50.setBounds(510, 70, 30, 20);

        quartaProfessorC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quartaProfessorC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaProfessorC.setEnabled(false);
        jPanel3.add(quartaProfessorC);
        quartaProfessorC.setBounds(360, 100, 150, 20);

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
        btCurso51.setBounds(510, 100, 30, 20);

        quartaProfessorD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quartaProfessorD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaProfessorD.setEnabled(false);
        jPanel3.add(quartaProfessorD);
        quartaProfessorD.setBounds(360, 130, 150, 20);

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
        btCurso52.setBounds(510, 130, 30, 20);

        quartaProfessorE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quartaProfessorE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaProfessorE.setEnabled(false);
        jPanel3.add(quartaProfessorE);
        quartaProfessorE.setBounds(360, 160, 150, 20);

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
        btCurso53.setBounds(510, 160, 30, 20);

        quartaProfessorF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quartaProfessorF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quartaProfessorF.setEnabled(false);
        jPanel3.add(quartaProfessorF);
        quartaProfessorF.setBounds(360, 190, 150, 20);

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
        btCurso54.setBounds(510, 190, 30, 20);

        jlquadro7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/quadro1.png"))); // NOI18N
        jPanel3.add(jlquadro7);
        jlquadro7.setBounds(0, 0, 540, 230);

        jTabbedPane1.addTab("Quarta-Feira", jPanel3);

        jPanel4.setLayout(null);

        quintaHorarioA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quintaHorarioA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaHorarioA.setEnabled(false);
        jPanel4.add(quintaHorarioA);
        quintaHorarioA.setBounds(30, 40, 40, 20);

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
        btCurso55.setBounds(70, 40, 30, 20);

        quintaHorarioB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quintaHorarioB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaHorarioB.setEnabled(false);
        jPanel4.add(quintaHorarioB);
        quintaHorarioB.setBounds(30, 70, 40, 19);

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
        btCurso56.setBounds(70, 70, 30, 20);

        quintaHorarioC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quintaHorarioC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaHorarioC.setEnabled(false);
        jPanel4.add(quintaHorarioC);
        quintaHorarioC.setBounds(30, 100, 40, 20);

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
        btCurso57.setBounds(70, 100, 30, 20);

        quintaHorarioD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quintaHorarioD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaHorarioD.setEnabled(false);
        jPanel4.add(quintaHorarioD);
        quintaHorarioD.setBounds(30, 130, 40, 20);

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
        btCurso58.setBounds(70, 130, 30, 20);

        quintaHorarioE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quintaHorarioE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaHorarioE.setEnabled(false);
        jPanel4.add(quintaHorarioE);
        quintaHorarioE.setBounds(30, 160, 40, 20);

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
        btCurso59.setBounds(70, 160, 30, 20);

        quintaHorarioF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quintaHorarioF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaHorarioF.setEnabled(false);
        jPanel4.add(quintaHorarioF);
        quintaHorarioF.setBounds(30, 190, 40, 20);

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
        btCurso60.setBounds(70, 190, 30, 20);

        quintaDisciplinaA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quintaDisciplinaA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaDisciplinaA.setEnabled(false);
        jPanel4.add(quintaDisciplinaA);
        quintaDisciplinaA.setBounds(100, 40, 220, 20);

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
        btCurso61.setBounds(320, 40, 30, 20);

        quintaDisciplinaB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quintaDisciplinaB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaDisciplinaB.setEnabled(false);
        jPanel4.add(quintaDisciplinaB);
        quintaDisciplinaB.setBounds(100, 70, 220, 20);

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
        btCurso62.setBounds(320, 70, 30, 20);

        quintaDisciplinaC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quintaDisciplinaC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaDisciplinaC.setEnabled(false);
        jPanel4.add(quintaDisciplinaC);
        quintaDisciplinaC.setBounds(100, 100, 220, 20);

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
        btCurso63.setBounds(320, 100, 30, 20);

        quintaDisciplinaD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quintaDisciplinaD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaDisciplinaD.setEnabled(false);
        jPanel4.add(quintaDisciplinaD);
        quintaDisciplinaD.setBounds(100, 130, 220, 20);

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
        btCurso64.setBounds(320, 130, 30, 20);

        quintaDisciplinaE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quintaDisciplinaE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaDisciplinaE.setEnabled(false);
        jPanel4.add(quintaDisciplinaE);
        quintaDisciplinaE.setBounds(100, 160, 220, 20);

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
        btCurso65.setBounds(320, 160, 30, 20);

        quintaDisciplinaF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quintaDisciplinaF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaDisciplinaF.setEnabled(false);
        jPanel4.add(quintaDisciplinaF);
        quintaDisciplinaF.setBounds(100, 190, 220, 20);

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
        btCurso66.setBounds(320, 190, 30, 20);

        quintaProfessorA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quintaProfessorA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaProfessorA.setEnabled(false);
        jPanel4.add(quintaProfessorA);
        quintaProfessorA.setBounds(360, 40, 150, 20);

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
        btCurso67.setBounds(510, 40, 30, 20);

        quintaProfessorB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quintaProfessorB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaProfessorB.setEnabled(false);
        jPanel4.add(quintaProfessorB);
        quintaProfessorB.setBounds(360, 70, 150, 20);

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
        btCurso68.setBounds(510, 70, 30, 20);

        quintaProfessorC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quintaProfessorC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaProfessorC.setEnabled(false);
        jPanel4.add(quintaProfessorC);
        quintaProfessorC.setBounds(360, 100, 150, 20);

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
        btCurso69.setBounds(510, 100, 30, 20);

        quintaProfessorD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quintaProfessorD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaProfessorD.setEnabled(false);
        jPanel4.add(quintaProfessorD);
        quintaProfessorD.setBounds(360, 130, 150, 20);

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
        btCurso70.setBounds(510, 130, 30, 20);

        quintaProfessorE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quintaProfessorE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaProfessorE.setEnabled(false);
        jPanel4.add(quintaProfessorE);
        quintaProfessorE.setBounds(360, 160, 150, 20);

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
        btCurso71.setBounds(510, 160, 30, 20);

        quintaProfessorF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quintaProfessorF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        quintaProfessorF.setEnabled(false);
        jPanel4.add(quintaProfessorF);
        quintaProfessorF.setBounds(360, 190, 150, 20);

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
        btCurso72.setBounds(510, 190, 30, 20);

        jlquadro8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/quadro1.png"))); // NOI18N
        jPanel4.add(jlquadro8);
        jlquadro8.setBounds(0, 0, 540, 230);

        jTabbedPane1.addTab("Quinta-Feira", jPanel4);

        jPanel5.setLayout(null);

        sextaHorarioA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sextaHorarioA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaHorarioA.setEnabled(false);
        jPanel5.add(sextaHorarioA);
        sextaHorarioA.setBounds(30, 40, 40, 20);

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
        btCurso73.setBounds(70, 40, 30, 20);

        sextaHorarioB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sextaHorarioB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaHorarioB.setEnabled(false);
        jPanel5.add(sextaHorarioB);
        sextaHorarioB.setBounds(30, 70, 40, 19);

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
        btCurso74.setBounds(70, 70, 30, 20);

        sextaHorarioC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sextaHorarioC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaHorarioC.setEnabled(false);
        jPanel5.add(sextaHorarioC);
        sextaHorarioC.setBounds(30, 100, 40, 20);

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
        btCurso75.setBounds(70, 100, 30, 20);

        sextaHorarioD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sextaHorarioD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaHorarioD.setEnabled(false);
        jPanel5.add(sextaHorarioD);
        sextaHorarioD.setBounds(30, 130, 40, 20);

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
        btCurso76.setBounds(70, 130, 30, 20);

        sextaHorarioE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sextaHorarioE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaHorarioE.setEnabled(false);
        jPanel5.add(sextaHorarioE);
        sextaHorarioE.setBounds(30, 160, 40, 20);

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
        btCurso77.setBounds(70, 160, 30, 20);

        sextaHorarioF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sextaHorarioF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaHorarioF.setEnabled(false);
        jPanel5.add(sextaHorarioF);
        sextaHorarioF.setBounds(30, 190, 40, 20);

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
        btCurso78.setBounds(70, 190, 30, 20);

        sextaDisciplinaA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sextaDisciplinaA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaDisciplinaA.setEnabled(false);
        jPanel5.add(sextaDisciplinaA);
        sextaDisciplinaA.setBounds(100, 40, 220, 20);

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
        btCurso79.setBounds(320, 40, 30, 20);

        sextaDisciplinaB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sextaDisciplinaB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaDisciplinaB.setEnabled(false);
        jPanel5.add(sextaDisciplinaB);
        sextaDisciplinaB.setBounds(100, 70, 220, 20);

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
        btCurso80.setBounds(320, 70, 30, 20);

        sextaDisciplinaC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sextaDisciplinaC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaDisciplinaC.setEnabled(false);
        jPanel5.add(sextaDisciplinaC);
        sextaDisciplinaC.setBounds(100, 100, 220, 20);

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
        btCurso81.setBounds(320, 100, 30, 20);

        sextaDisciplinaD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sextaDisciplinaD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaDisciplinaD.setEnabled(false);
        jPanel5.add(sextaDisciplinaD);
        sextaDisciplinaD.setBounds(100, 130, 220, 20);

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
        btCurso82.setBounds(320, 130, 30, 20);

        sextaDisciplinaE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sextaDisciplinaE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaDisciplinaE.setEnabled(false);
        jPanel5.add(sextaDisciplinaE);
        sextaDisciplinaE.setBounds(100, 160, 220, 20);

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
        btCurso83.setBounds(320, 160, 30, 20);

        sextaDisciplinaF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sextaDisciplinaF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaDisciplinaF.setEnabled(false);
        jPanel5.add(sextaDisciplinaF);
        sextaDisciplinaF.setBounds(100, 190, 220, 20);

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
        btCurso84.setBounds(320, 190, 30, 20);

        sextaProfessorA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sextaProfessorA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaProfessorA.setEnabled(false);
        jPanel5.add(sextaProfessorA);
        sextaProfessorA.setBounds(360, 40, 150, 20);

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
        btCurso85.setBounds(510, 40, 30, 20);

        sextaProfessorB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sextaProfessorB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaProfessorB.setEnabled(false);
        jPanel5.add(sextaProfessorB);
        sextaProfessorB.setBounds(360, 70, 150, 20);

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
        btCurso86.setBounds(510, 70, 30, 20);

        sextaProfessorC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sextaProfessorC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaProfessorC.setEnabled(false);
        jPanel5.add(sextaProfessorC);
        sextaProfessorC.setBounds(360, 100, 150, 20);

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
        btCurso87.setBounds(510, 100, 30, 20);

        sextaProfessorD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sextaProfessorD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaProfessorD.setEnabled(false);
        jPanel5.add(sextaProfessorD);
        sextaProfessorD.setBounds(360, 130, 150, 20);

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
        btCurso88.setBounds(510, 130, 30, 20);

        sextaProfessorE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sextaProfessorE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaProfessorE.setEnabled(false);
        jPanel5.add(sextaProfessorE);
        sextaProfessorE.setBounds(360, 160, 150, 20);

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
        btCurso89.setBounds(510, 160, 30, 20);

        sextaProfessorF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sextaProfessorF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sextaProfessorF.setEnabled(false);
        jPanel5.add(sextaProfessorF);
        sextaProfessorF.setBounds(360, 190, 150, 20);

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
        btCurso90.setBounds(510, 190, 30, 20);

        jlquadro9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/quadro1.png"))); // NOI18N
        jPanel5.add(jlquadro9);
        jlquadro9.setBounds(0, 0, 540, 230);

        jTabbedPane1.addTab("Sexta-Feira", jPanel5);

        jPanel6.setLayout(null);

        sabadoHorarioA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sabadoHorarioA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoHorarioA.setEnabled(false);
        jPanel6.add(sabadoHorarioA);
        sabadoHorarioA.setBounds(30, 40, 40, 20);

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
        btCurso91.setBounds(70, 40, 30, 20);

        sabadoHorarioB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sabadoHorarioB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoHorarioB.setEnabled(false);
        jPanel6.add(sabadoHorarioB);
        sabadoHorarioB.setBounds(30, 70, 40, 19);

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
        btCurso92.setBounds(70, 70, 30, 20);

        sabadoHorarioC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sabadoHorarioC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoHorarioC.setEnabled(false);
        jPanel6.add(sabadoHorarioC);
        sabadoHorarioC.setBounds(30, 100, 40, 20);

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
        btCurso93.setBounds(70, 100, 30, 20);

        sabadoHorarioD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sabadoHorarioD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoHorarioD.setEnabled(false);
        jPanel6.add(sabadoHorarioD);
        sabadoHorarioD.setBounds(30, 130, 40, 20);

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
        btCurso94.setBounds(70, 130, 30, 20);

        sabadoHorarioE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sabadoHorarioE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoHorarioE.setEnabled(false);
        jPanel6.add(sabadoHorarioE);
        sabadoHorarioE.setBounds(30, 160, 40, 20);

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
        btCurso95.setBounds(70, 160, 30, 20);

        sabadoHorarioF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sabadoHorarioF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoHorarioF.setEnabled(false);
        jPanel6.add(sabadoHorarioF);
        sabadoHorarioF.setBounds(30, 190, 40, 20);

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
        btCurso96.setBounds(70, 190, 30, 20);

        sabadoDisciplinaA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sabadoDisciplinaA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoDisciplinaA.setEnabled(false);
        jPanel6.add(sabadoDisciplinaA);
        sabadoDisciplinaA.setBounds(100, 40, 220, 20);

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
        btCurso97.setBounds(320, 40, 30, 20);

        sabadoDisciplinaB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sabadoDisciplinaB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoDisciplinaB.setEnabled(false);
        jPanel6.add(sabadoDisciplinaB);
        sabadoDisciplinaB.setBounds(100, 70, 220, 20);

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
        btCurso98.setBounds(320, 70, 30, 20);

        sabadoDisciplinaC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sabadoDisciplinaC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoDisciplinaC.setEnabled(false);
        jPanel6.add(sabadoDisciplinaC);
        sabadoDisciplinaC.setBounds(100, 100, 220, 20);

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
        btCurso99.setBounds(320, 100, 30, 20);

        sabadoDisciplinaD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sabadoDisciplinaD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoDisciplinaD.setEnabled(false);
        jPanel6.add(sabadoDisciplinaD);
        sabadoDisciplinaD.setBounds(100, 130, 220, 20);

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
        btCurso100.setBounds(320, 130, 30, 20);

        sabadoDisciplinaE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sabadoDisciplinaE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoDisciplinaE.setEnabled(false);
        jPanel6.add(sabadoDisciplinaE);
        sabadoDisciplinaE.setBounds(100, 160, 220, 20);

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
        btCurso101.setBounds(320, 160, 30, 20);

        sabadoDisciplinaF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sabadoDisciplinaF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoDisciplinaF.setEnabled(false);
        jPanel6.add(sabadoDisciplinaF);
        sabadoDisciplinaF.setBounds(100, 190, 220, 20);

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
        btCurso102.setBounds(320, 190, 30, 20);

        sabadoProfessorA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sabadoProfessorA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoProfessorA.setEnabled(false);
        jPanel6.add(sabadoProfessorA);
        sabadoProfessorA.setBounds(360, 40, 150, 20);

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
        btCurso103.setBounds(510, 40, 30, 20);

        sabadoProfessorB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sabadoProfessorB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoProfessorB.setEnabled(false);
        jPanel6.add(sabadoProfessorB);
        sabadoProfessorB.setBounds(360, 70, 150, 20);

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
        btCurso104.setBounds(510, 70, 30, 20);

        sabadoProfessorC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sabadoProfessorC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoProfessorC.setEnabled(false);
        jPanel6.add(sabadoProfessorC);
        sabadoProfessorC.setBounds(360, 100, 150, 20);

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
        btCurso105.setBounds(510, 100, 30, 20);

        sabadoProfessorD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sabadoProfessorD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoProfessorD.setEnabled(false);
        jPanel6.add(sabadoProfessorD);
        sabadoProfessorD.setBounds(360, 130, 150, 20);

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
        btCurso106.setBounds(510, 130, 30, 20);

        sabadoProfessorE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sabadoProfessorE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoProfessorE.setEnabled(false);
        jPanel6.add(sabadoProfessorE);
        sabadoProfessorE.setBounds(360, 160, 150, 20);

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
        btCurso107.setBounds(510, 160, 30, 20);

        sabadoProfessorF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sabadoProfessorF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        sabadoProfessorF.setEnabled(false);
        jPanel6.add(sabadoProfessorF);
        sabadoProfessorF.setBounds(360, 190, 150, 20);

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
        btCurso108.setBounds(510, 190, 30, 20);

        jlquadro10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/quadro1.png"))); // NOI18N
        jPanel6.add(jlquadro10);
        jlquadro10.setBounds(0, 0, 540, 230);

        jTabbedPane1.addTab("Sábado", jPanel6);

        getContentPane().add(jTabbedPane1);
        jTabbedPane1.setBounds(30, 170, 542, 263);

        jlNome5.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jlNome5.setText("Curso.:");
        getContentPane().add(jlNome5);
        jlNome5.setBounds(50, 100, 60, 20);

        jlNome6.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jlNome6.setText("Grade.:");
        getContentPane().add(jlNome6);
        jlNome6.setBounds(410, 100, 60, 20);

        jlQuadroHorario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/quadrohorario1.png"))); // NOI18N
        jlQuadroHorario.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jlQuadroHorario.setMaximumSize(new java.awt.Dimension(600, 520));
        jlQuadroHorario.setMinimumSize(new java.awt.Dimension(600, 520));
        jlQuadroHorario.setPreferredSize(new java.awt.Dimension(600, 520));
        getContentPane().add(jlQuadroHorario);
        jlQuadroHorario.setBounds(0, 0, 600, 520);

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
        // TODO add your handling code here:
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
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso8ActionPerformed

    private void btCurso9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso9ActionPerformed

    private void btCurso10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso10ActionPerformed

    private void btCurso11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso11ActionPerformed

    private void btCurso12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso12ActionPerformed

    private void btCurso13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso13ActionPerformed

    private void btCurso14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso14ActionPerformed

    private void btCurso15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso15ActionPerformed

    private void btCurso16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso16ActionPerformed

    private void btCurso17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso17ActionPerformed

    private void btCurso18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso18ActionPerformed

    private void btCurso19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso19ActionPerformed

    private void btCurso20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso20ActionPerformed

    private void btCurso21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso21ActionPerformed

    private void btCurso22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso22ActionPerformed

    private void btCurso23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso23ActionPerformed

    private void btCurso24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso24ActionPerformed

    private void btCurso25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso25ActionPerformed

    private void btCurso26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso26ActionPerformed

    private void btCurso27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso27ActionPerformed

    private void btCurso28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso28ActionPerformed

    private void btCurso29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso29ActionPerformed

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

    private void btCurso37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso37ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso37ActionPerformed

    private void btCurso38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso38ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso38ActionPerformed

    private void btCurso39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso39ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso39ActionPerformed

    private void btCurso40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso40ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso40ActionPerformed

    private void btCurso41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso41ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso41ActionPerformed

    private void btCurso42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso42ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso42ActionPerformed

    private void btCurso43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso43ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso43ActionPerformed

    private void btCurso44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso44ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso44ActionPerformed

    private void btCurso45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso45ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso45ActionPerformed

    private void btCurso46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso46ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso46ActionPerformed

    private void btCurso47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso47ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso47ActionPerformed

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

    private void btCurso55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso55ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso55ActionPerformed

    private void btCurso56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso56ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso56ActionPerformed

    private void btCurso57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso57ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso57ActionPerformed

    private void btCurso58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso58ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso58ActionPerformed

    private void btCurso59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso59ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso59ActionPerformed

    private void btCurso60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso60ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso60ActionPerformed

    private void btCurso61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso61ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso61ActionPerformed

    private void btCurso62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso62ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso62ActionPerformed

    private void btCurso63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso63ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso63ActionPerformed

    private void btCurso64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso64ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso64ActionPerformed

    private void btCurso65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso65ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso65ActionPerformed

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

    private void btCurso73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso73ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso73ActionPerformed

    private void btCurso74ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso74ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso74ActionPerformed

    private void btCurso75ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso75ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso75ActionPerformed

    private void btCurso76ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso76ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso76ActionPerformed

    private void btCurso77ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso77ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso77ActionPerformed

    private void btCurso78ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso78ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso78ActionPerformed

    private void btCurso79ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso79ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso79ActionPerformed

    private void btCurso80ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso80ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso80ActionPerformed

    private void btCurso81ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso81ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso81ActionPerformed

    private void btCurso82ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso82ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso82ActionPerformed

    private void btCurso83ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso83ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso83ActionPerformed

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

    private void btCurso91ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso91ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso91ActionPerformed

    private void btCurso92ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso92ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso92ActionPerformed

    private void btCurso93ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso93ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso93ActionPerformed

    private void btCurso94ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso94ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso94ActionPerformed

    private void btCurso95ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso95ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso95ActionPerformed

    private void btCurso96ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso96ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso96ActionPerformed

    private void btCurso97ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso97ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso97ActionPerformed

    private void btCurso98ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso98ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso98ActionPerformed

    private void btCurso99ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso99ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso99ActionPerformed

    private void btCurso100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso100ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso100ActionPerformed

    private void btCurso101ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso101ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso101ActionPerformed

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
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso106ActionPerformed

    private void btCurso107ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso107ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso107ActionPerformed

    private void btCurso108ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso108ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCurso108ActionPerformed

    private void jcTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcTurnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcTurnoActionPerformed

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
    private javax.swing.JComboBox jcTurno;
    private javax.swing.JLabel jlNome2;
    private javax.swing.JLabel jlNome3;
    private javax.swing.JLabel jlNome4;
    private javax.swing.JLabel jlNome5;
    private javax.swing.JLabel jlNome6;
    private javax.swing.JLabel jlQuadroHorario;
    private javax.swing.JLabel jlquadro;
    private javax.swing.JLabel jlquadro10;
    private javax.swing.JLabel jlquadro6;
    private javax.swing.JLabel jlquadro7;
    private javax.swing.JLabel jlquadro8;
    private javax.swing.JLabel jlquadro9;
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
