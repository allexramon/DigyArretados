/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.telas;

import birdpoint.anoexercicio.AnoExercicio;
import birdpoint.anoexercicio.AnoExercicioDAO;
import birdpoint.calculo.horas.professor.CalcularHorasRN;
import birdpoint.calculo.horas.professor.CalculoHorasProfessor;
import birdpoint.calculo.horas.professor.InforProfessor;
import birdpoint.professor.Professor;
import birdpoint.professor.ProfessorDAO;
import birdpoint.professor.ProfessorTableModel;
import birdpoint.titulacao.TitulacaoDAO;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AlexRamon
 */
public class CadastroDespesasProfessor extends javax.swing.JDialog {

    Professor professor = new Professor();
    CalculoHorasProfessor calcHorasProf = new CalculoHorasProfessor();
    CalcularHorasRN calcHorasRN;
    double valorHoraAula;

    ProfessorDAO daoProf = new ProfessorDAO();
    AnoExercicioDAO daoAnoExer = new AnoExercicioDAO();
    TitulacaoDAO titulacaoDAO = new TitulacaoDAO();

    List<AnoExercicio> listaAnosExercicios = new ArrayList<>();

    public CadastroDespesasProfessor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        carregarAnoExercicioAtual();
        try {
            calcHorasRN = new CalcularHorasRN(String.valueOf(jcAnoExercicio.getSelectedItem()));
        } catch (Exception e) {
        }
        btLimparActionPerformed(null);
    }

    public void carregarAnoExercicioAtual() {
        listaAnosExercicios = daoAnoExer.listar();
        for (AnoExercicio anoExercicio : listaAnosExercicios) {
            jcAnoExercicio.addItem(anoExercicio.getNomeAnoExercicio());
            if (anoExercicio.isAnoExercicioAtual()) {
                jcAnoExercicio.setSelectedItem(anoExercicio.getNomeAnoExercicio());
            }
        }
    }

    public void pesquisarProfessor() {
        List<Professor> lista;
        lista = (daoProf.listar());
        ProfessorTableModel itm = new ProfessorTableModel(lista);
        Object objetoRetorno = PesquisaGenerica.exibeTela(itm, "Professor");
        if (objetoRetorno != null) {
            professor = daoProf.consultarObjetoId("idProfessor", objetoRetorno);
            tfNomeProfessor.setText(professor.getNomeProfessor());
            tfTitulacao.setText(professor.getTitulacaoProfessor());
            tfValorHoraAula.setText(consultarHoraAula(professor.getTitulacaoProfessor()));
            valorHoraAula = Double.parseDouble(tfValorHoraAula.getText());
            selecionarDisciplinasProfessor(professor.getIdProfessor());
        }
    }

    public String consultarHoraAula(String titulacao) {
        return String.valueOf(titulacaoDAO.consultarObjetoId("nome", titulacao).getValorTitulacao());
    }

    public void selecionarDisciplinasProfessor(int idProfessor) {
        int CHTemp;
        for (InforProfessor inforProfessor : calcHorasRN.getListaInformacoesProfessores()) {
            if (inforProfessor.getCurso().getNomeCurso().equalsIgnoreCase("Administração")
                    && inforProfessor.getProfessor().getIdProfessor() == professor.getIdProfessor()) {
                CHTemp = Integer.parseInt(tfCHADM.getText());
                CHTemp += inforProfessor.getDisciplina().getCargaHoraria();
                tfCHADM.setText(String.valueOf(CHTemp));
                tfValorADM.setText(String.valueOf("R$: " + calcularHoraAula(CHTemp)));
            } else if (inforProfessor.getCurso().getNomeCurso().equalsIgnoreCase("Análise e Desenvolvimento de Sistemas")
                    && inforProfessor.getProfessor().getIdProfessor() == professor.getIdProfessor()) {
                CHTemp = Integer.parseInt(tfCHADS.getText());
                CHTemp += inforProfessor.getDisciplina().getCargaHoraria();
                tfCHADS.setText(String.valueOf(CHTemp));
                tfValorADS.setText(String.valueOf("R$: " + calcularHoraAula(CHTemp)));
            } else if (inforProfessor.getCurso().getNomeCurso().equalsIgnoreCase("Ciências Contábeis")
                    && inforProfessor.getProfessor().getIdProfessor() == professor.getIdProfessor()) {
                CHTemp = Integer.parseInt(tfCHCIC.getText());
                CHTemp += inforProfessor.getDisciplina().getCargaHoraria();
                tfCHCIC.setText(String.valueOf(CHTemp));
                tfValorCIC.setText(String.valueOf("R$: " + calcularHoraAula(CHTemp)));
            } else if (inforProfessor.getCurso().getNomeCurso().equalsIgnoreCase("Serviço Social")
                    && inforProfessor.getProfessor().getIdProfessor() == professor.getIdProfessor()) {
                CHTemp = Integer.parseInt(tfCHSSO.getText());
                CHTemp += inforProfessor.getDisciplina().getCargaHoraria();
                tfCHSSO.setText(String.valueOf(CHTemp));
                tfValorSSO.setText(String.valueOf("R$: " + calcularHoraAula(CHTemp)));
            } else if (inforProfessor.getCurso().getNomeCurso().equalsIgnoreCase("Educação Física")
                    && inforProfessor.getProfessor().getIdProfessor() == professor.getIdProfessor()) {
                CHTemp = Integer.parseInt(tfCHEDF.getText());
                CHTemp += inforProfessor.getDisciplina().getCargaHoraria();
                tfCHEDF.setText(String.valueOf(CHTemp));
                tfValorEDF.setText(String.valueOf("R$: " + calcularHoraAula(CHTemp)));
            } else if (inforProfessor.getCurso().getNomeCurso().equalsIgnoreCase("Enfermagem")
                    && inforProfessor.getProfessor().getIdProfessor() == professor.getIdProfessor()) {
                CHTemp = Integer.parseInt(tfCHENF.getText());
                CHTemp += inforProfessor.getDisciplina().getCargaHoraria();
                tfCHENF.setText(String.valueOf(CHTemp));
                tfValorENF.setText(String.valueOf("R$: " + calcularHoraAula(CHTemp)));
            } else if (inforProfessor.getCurso().getNomeCurso().equalsIgnoreCase("Fisioterapia")
                    && inforProfessor.getProfessor().getIdProfessor() == professor.getIdProfessor()) {
                CHTemp = Integer.parseInt(tfCHFIS.getText());
                CHTemp += inforProfessor.getDisciplina().getCargaHoraria();
                tfCHFIS.setText(String.valueOf(CHTemp));
                tfValorFIS.setText(String.valueOf("R$: " + calcularHoraAula(CHTemp)));
            } else if (inforProfessor.getCurso().getNomeCurso().equalsIgnoreCase("Psicologia")
                    && inforProfessor.getProfessor().getIdProfessor() == professor.getIdProfessor()) {
                CHTemp = Integer.parseInt(tfCHPSI.getText());
                CHTemp += inforProfessor.getDisciplina().getCargaHoraria();
                tfCHPSI.setText(String.valueOf(CHTemp));
                tfValorPSI.setText(String.valueOf("R$: " + calcularHoraAula(CHTemp)));
            }
        }
        calcularValores();
    }

    public void calcularValores() {
        int CHTemp = Integer.parseInt(tfCHADM.getText())
                + Integer.parseInt(tfCHADS.getText())
                + Integer.parseInt(tfCHCIC.getText())
                + Integer.parseInt(tfCHSSO.getText())
                + Integer.parseInt(tfCHEDF.getText())
                + Integer.parseInt(tfCHENF.getText())
                + Integer.parseInt(tfCHFIS.getText())
                + Integer.parseInt(tfCHPSI.getText());
        tfCHTotal.setText(String.valueOf(CHTemp));
        tfValorTotal.setText(calcularHoraAula(CHTemp));
    }

    public String calcularHoraAula(int qtdHoras) {
        double valorTemp = (((qtdHoras / 20) * 4.5) * valorHoraAula);
        valorTemp = ((valorTemp / 6) + valorTemp);
        DecimalFormat df = new DecimalFormat("0.##");
        String valorFormatado = String.valueOf(df.format(valorTemp));
        return valorFormatado;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btVoltar = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btLimpar = new javax.swing.JButton();
        btPesquisar = new javax.swing.JButton();
        jlNome2 = new javax.swing.JLabel();
        tfNomeProfessor = new javax.swing.JTextField();
        jLObrigatorioSemestre = new javax.swing.JLabel();
        btPesqusar1 = new javax.swing.JButton();
        tfCHADS = new javax.swing.JTextField();
        jLObrigatorioCurso3 = new javax.swing.JLabel();
        jlNome4 = new javax.swing.JLabel();
        tfCHADM = new javax.swing.JTextField();
        jlNome5 = new javax.swing.JLabel();
        tfCHCIC = new javax.swing.JTextField();
        tfValorADS = new javax.swing.JLabel();
        jlNome8 = new javax.swing.JLabel();
        tfCHSSO = new javax.swing.JTextField();
        jlNome9 = new javax.swing.JLabel();
        tfValorTotal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jcAnoExercicio = new javax.swing.JComboBox();
        tfCHEDF = new javax.swing.JTextField();
        jlNome12 = new javax.swing.JLabel();
        jlNome13 = new javax.swing.JLabel();
        tfCHENF = new javax.swing.JTextField();
        jlNome14 = new javax.swing.JLabel();
        tfCHFIS = new javax.swing.JTextField();
        tfCHPSI = new javax.swing.JTextField();
        jlNome15 = new javax.swing.JLabel();
        jLObrigatorioSemestre1 = new javax.swing.JLabel();
        jlNome10 = new javax.swing.JLabel();
        tfValorCIC = new javax.swing.JLabel();
        tfValorSSO = new javax.swing.JLabel();
        tfValorEDF = new javax.swing.JLabel();
        tfValorENF = new javax.swing.JLabel();
        tfValorFIS = new javax.swing.JLabel();
        tfValorPSI = new javax.swing.JLabel();
        jlNome23 = new javax.swing.JLabel();
        jlNome6 = new javax.swing.JLabel();
        tfCHTotal = new javax.swing.JTextField();
        tfValorHoraAula = new javax.swing.JLabel();
        tfValorADM = new javax.swing.JLabel();
        tfTitulacao = new javax.swing.JLabel();
        jlNome27 = new javax.swing.JLabel();
        jlCadTitulacao = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 482));
        setUndecorated(true);
        getContentPane().setLayout(null);

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
        btVoltar.setBounds(20, 390, 90, 70);

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
        btSalvar.setBounds(480, 390, 80, 70);

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
        btExcluir.setBounds(390, 390, 80, 70);

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
        btLimpar.setBounds(180, 390, 80, 70);

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
        btPesquisar.setBounds(280, 390, 100, 69);

        jlNome2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jlNome2.setText("Professor.:");
        getContentPane().add(jlNome2);
        jlNome2.setBounds(20, 70, 90, 30);

        tfNomeProfessor.setEditable(false);
        tfNomeProfessor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfNomeProfessor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tfNomeProfessor.setEnabled(false);
        getContentPane().add(tfNomeProfessor);
        tfNomeProfessor.setBounds(110, 70, 240, 30);

        jLObrigatorioSemestre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLObrigatorioSemestre.setForeground(new java.awt.Color(204, 0, 0));
        jLObrigatorioSemestre.setText("*");
        getContentPane().add(jLObrigatorioSemestre);
        jLObrigatorioSemestre.setBounds(580, 60, 10, 30);

        btPesqusar1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btPesqusar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btPesqusar1.setContentAreaFilled(false);
        btPesqusar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btPesqusar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btPesqusar1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btPesqusar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesqusar1ActionPerformed(evt);
            }
        });
        getContentPane().add(btPesqusar1);
        btPesqusar1.setBounds(350, 70, 30, 30);

        tfCHADS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfCHADS.setText("0");
        tfCHADS.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tfCHADS.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCHADSFocusLost(evt);
            }
        });
        tfCHADS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfCHADSKeyTyped(evt);
            }
        });
        getContentPane().add(tfCHADS);
        tfCHADS.setBounds(110, 190, 70, 30);

        jLObrigatorioCurso3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLObrigatorioCurso3.setForeground(new java.awt.Color(204, 0, 0));
        jLObrigatorioCurso3.setText("*");
        getContentPane().add(jLObrigatorioCurso3);
        jLObrigatorioCurso3.setBounds(630, 160, 10, 50);

        jlNome4.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jlNome4.setText("C.H ADM.:");
        getContentPane().add(jlNome4);
        jlNome4.setBounds(30, 150, 80, 30);

        tfCHADM.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfCHADM.setText("0");
        tfCHADM.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tfCHADM.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCHADMFocusLost(evt);
            }
        });
        tfCHADM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfCHADMKeyTyped(evt);
            }
        });
        getContentPane().add(tfCHADM);
        tfCHADM.setBounds(110, 150, 70, 30);

        jlNome5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jlNome5.setText("Valor Total.: R$ ");
        getContentPane().add(jlNome5);
        jlNome5.setBounds(90, 350, 140, 30);

        tfCHCIC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfCHCIC.setText("0");
        tfCHCIC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tfCHCIC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCHCICFocusLost(evt);
            }
        });
        tfCHCIC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfCHCICKeyTyped(evt);
            }
        });
        getContentPane().add(tfCHCIC);
        tfCHCIC.setBounds(110, 230, 70, 30);

        tfValorADS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(tfValorADS);
        tfValorADS.setBounds(190, 190, 100, 30);

        jlNome8.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jlNome8.setText("C.H SSO.:");
        jlNome8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jlNome8FocusLost(evt);
            }
        });
        getContentPane().add(jlNome8);
        jlNome8.setBounds(30, 270, 80, 30);

        tfCHSSO.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfCHSSO.setText("0");
        tfCHSSO.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tfCHSSO.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCHSSOFocusLost(evt);
            }
        });
        tfCHSSO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfCHSSOKeyTyped(evt);
            }
        });
        getContentPane().add(tfCHSSO);
        tfCHSSO.setBounds(110, 270, 70, 30);

        jlNome9.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jlNome9.setText("C.H ADS.:");
        getContentPane().add(jlNome9);
        jlNome9.setBounds(30, 190, 80, 30);

        tfValorTotal.setEditable(false);
        tfValorTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfValorTotal.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tfValorTotal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfValorTotalFocusLost(evt);
            }
        });
        tfValorTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfValorTotalKeyTyped(evt);
            }
        });
        getContentPane().add(tfValorTotal);
        tfValorTotal.setBounds(230, 350, 90, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Ano Exercício.:");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(380, 70, 110, 30);

        jcAnoExercicio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcAnoExercicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-----" }));
        jcAnoExercicio.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        getContentPane().add(jcAnoExercicio);
        jcAnoExercicio.setBounds(490, 70, 90, 30);

        tfCHEDF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfCHEDF.setText("0");
        tfCHEDF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tfCHEDF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCHEDFFocusLost(evt);
            }
        });
        tfCHEDF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfCHEDFKeyTyped(evt);
            }
        });
        getContentPane().add(tfCHEDF);
        tfCHEDF.setBounds(410, 150, 70, 30);

        jlNome12.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jlNome12.setText("C.H EDF.:");
        getContentPane().add(jlNome12);
        jlNome12.setBounds(330, 150, 80, 30);

        jlNome13.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jlNome13.setText("C.H ENF.:");
        getContentPane().add(jlNome13);
        jlNome13.setBounds(330, 190, 80, 30);

        tfCHENF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfCHENF.setText("0");
        tfCHENF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tfCHENF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCHENFFocusLost(evt);
            }
        });
        tfCHENF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfCHENFKeyTyped(evt);
            }
        });
        getContentPane().add(tfCHENF);
        tfCHENF.setBounds(410, 190, 70, 30);

        jlNome14.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jlNome14.setText("C.H FIS.:");
        getContentPane().add(jlNome14);
        jlNome14.setBounds(330, 230, 70, 30);

        tfCHFIS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfCHFIS.setText("0");
        tfCHFIS.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tfCHFIS.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCHFISFocusLost(evt);
            }
        });
        tfCHFIS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfCHFISKeyTyped(evt);
            }
        });
        getContentPane().add(tfCHFIS);
        tfCHFIS.setBounds(410, 230, 70, 30);

        tfCHPSI.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfCHPSI.setText("0");
        tfCHPSI.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tfCHPSI.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCHPSIFocusLost(evt);
            }
        });
        tfCHPSI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfCHPSIKeyTyped(evt);
            }
        });
        getContentPane().add(tfCHPSI);
        tfCHPSI.setBounds(410, 270, 70, 30);

        jlNome15.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jlNome15.setText("C.H PSI.:");
        jlNome15.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jlNome15FocusLost(evt);
            }
        });
        getContentPane().add(jlNome15);
        jlNome15.setBounds(330, 270, 80, 30);

        jLObrigatorioSemestre1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLObrigatorioSemestre1.setForeground(new java.awt.Color(204, 0, 0));
        jLObrigatorioSemestre1.setText("*");
        getContentPane().add(jLObrigatorioSemestre1);
        jLObrigatorioSemestre1.setBounds(350, 60, 10, 30);

        jlNome10.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jlNome10.setText("C.H CIC.:");
        getContentPane().add(jlNome10);
        jlNome10.setBounds(30, 230, 70, 30);

        tfValorCIC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(tfValorCIC);
        tfValorCIC.setBounds(190, 230, 100, 30);

        tfValorSSO.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(tfValorSSO);
        tfValorSSO.setBounds(190, 270, 100, 30);

        tfValorEDF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(tfValorEDF);
        tfValorEDF.setBounds(490, 150, 90, 30);

        tfValorENF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(tfValorENF);
        tfValorENF.setBounds(490, 190, 90, 30);

        tfValorFIS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(tfValorFIS);
        tfValorFIS.setBounds(490, 230, 90, 30);

        tfValorPSI.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(tfValorPSI);
        tfValorPSI.setBounds(490, 270, 90, 30);

        jlNome23.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jlNome23.setText("Valor H/A.:");
        getContentPane().add(jlNome23);
        jlNome23.setBounds(310, 110, 90, 30);

        jlNome6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jlNome6.setText("C.H Total.:");
        getContentPane().add(jlNome6);
        jlNome6.setBounds(130, 310, 90, 30);

        tfCHTotal.setEditable(false);
        tfCHTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfCHTotal.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tfCHTotal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCHTotalFocusLost(evt);
            }
        });
        tfCHTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfCHTotalKeyTyped(evt);
            }
        });
        getContentPane().add(tfCHTotal);
        tfCHTotal.setBounds(230, 310, 90, 30);

        tfValorHoraAula.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(tfValorHoraAula);
        tfValorHoraAula.setBounds(410, 110, 100, 30);

        tfValorADM.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(tfValorADM);
        tfValorADM.setBounds(190, 150, 100, 30);

        tfTitulacao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(tfTitulacao);
        tfTitulacao.setBounds(110, 110, 190, 30);

        jlNome27.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jlNome27.setText("Titulação.:");
        getContentPane().add(jlNome27);
        jlNome27.setBounds(20, 110, 90, 30);

        jlCadTitulacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/cad de despesas por curso.jpg"))); // NOI18N
        jlCadTitulacao.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jlCadTitulacao.setMaximumSize(new java.awt.Dimension(600, 482));
        jlCadTitulacao.setMinimumSize(new java.awt.Dimension(600, 482));
        jlCadTitulacao.setPreferredSize(new java.awt.Dimension(600, 482));
        getContentPane().add(jlCadTitulacao);
        jlCadTitulacao.setBounds(0, 0, 600, 482);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        dispose();
    }//GEN-LAST:event_btVoltarActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed

    }//GEN-LAST:event_btSalvarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed


    }//GEN-LAST:event_btExcluirActionPerformed

    private void btLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparActionPerformed

    }//GEN-LAST:event_btLimparActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed

    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btPesqusar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesqusar1ActionPerformed
        pesquisarProfessor();
    }//GEN-LAST:event_btPesqusar1ActionPerformed

    private void tfCHADMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCHADMKeyTyped
        int ascii = evt.getKeyChar();
        if (!(ascii >= 48 && ascii <= 57) && !(ascii == 46) && !(ascii == evt.VK_BACK_SPACE)) {
            evt.consume();
        }
        if (tfCHADM.getText().length() >= 9) {
            evt.consume();
        }
    }//GEN-LAST:event_tfCHADMKeyTyped

    private void tfCHADSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCHADSKeyTyped
        int ascii = evt.getKeyChar();
        if (!(ascii >= 48 && ascii <= 57) && !(ascii == 46) && !(ascii == evt.VK_BACK_SPACE)) {
            evt.consume();
        }
        if (tfCHADS.getText().length() >= 9) {
            evt.consume();
        }
    }//GEN-LAST:event_tfCHADSKeyTyped

    private void tfCHCICKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCHCICKeyTyped
        int ascii = evt.getKeyChar();
        if (!(ascii >= 48 && ascii <= 57) && !(ascii == 46) && !(ascii == evt.VK_BACK_SPACE)) {
            evt.consume();
        }
        if (tfCHCIC.getText().length() >= 9) {
            evt.consume();
        }
    }//GEN-LAST:event_tfCHCICKeyTyped

    private void tfCHSSOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCHSSOKeyTyped
        int ascii = evt.getKeyChar();
        if (!(ascii >= 48 && ascii <= 57) && !(ascii == 46) && !(ascii == evt.VK_BACK_SPACE)) {
            evt.consume();
        }
        if (tfCHSSO.getText().length() >= 9) {
            evt.consume();
        }
    }//GEN-LAST:event_tfCHSSOKeyTyped

    private void tfCHADMFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCHADMFocusLost

    }//GEN-LAST:event_tfCHADMFocusLost

    private void tfCHADSFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCHADSFocusLost

    }//GEN-LAST:event_tfCHADSFocusLost

    private void tfCHCICFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCHCICFocusLost

    }//GEN-LAST:event_tfCHCICFocusLost

    private void jlNome8FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jlNome8FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jlNome8FocusLost

    private void tfCHSSOFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCHSSOFocusLost

    }//GEN-LAST:event_tfCHSSOFocusLost

    private void tfValorTotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfValorTotalKeyTyped
        int ascii = evt.getKeyChar();
        if (!(ascii >= 48 && ascii <= 57) && !(ascii == 46) && !(ascii == evt.VK_BACK_SPACE)) {
            evt.consume();
        }
        if (tfValorTotal.getText().length() >= 9) {
            evt.consume();
        }
    }//GEN-LAST:event_tfValorTotalKeyTyped

    private void tfValorTotalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfValorTotalFocusLost

    }//GEN-LAST:event_tfValorTotalFocusLost

    private void tfCHEDFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCHEDFFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCHEDFFocusLost

    private void tfCHEDFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCHEDFKeyTyped
        int ascii = evt.getKeyChar();
        if (!(ascii >= 48 && ascii <= 57) && !(ascii == 46) && !(ascii == evt.VK_BACK_SPACE)) {
            evt.consume();
        }
        if (tfCHEDF.getText().length() >= 9) {
            evt.consume();
        }
    }//GEN-LAST:event_tfCHEDFKeyTyped

    private void tfCHENFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCHENFFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCHENFFocusLost

    private void tfCHENFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCHENFKeyTyped
        int ascii = evt.getKeyChar();
        if (!(ascii >= 48 && ascii <= 57) && !(ascii == 46) && !(ascii == evt.VK_BACK_SPACE)) {
            evt.consume();
        }
        if (tfCHENF.getText().length() >= 9) {
            evt.consume();
        }
    }//GEN-LAST:event_tfCHENFKeyTyped

    private void tfCHFISFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCHFISFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCHFISFocusLost

    private void tfCHFISKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCHFISKeyTyped
        int ascii = evt.getKeyChar();
        if (!(ascii >= 48 && ascii <= 57) && !(ascii == 46) && !(ascii == evt.VK_BACK_SPACE)) {
            evt.consume();
        }
        if (tfCHFIS.getText().length() >= 9) {
            evt.consume();
        }
    }//GEN-LAST:event_tfCHFISKeyTyped

    private void tfCHPSIFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCHPSIFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCHPSIFocusLost

    private void tfCHPSIKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCHPSIKeyTyped
        int ascii = evt.getKeyChar();
        if (!(ascii >= 48 && ascii <= 57) && !(ascii == 46) && !(ascii == evt.VK_BACK_SPACE)) {
            evt.consume();
        }
        if (tfCHFIS.getText().length() >= 9) {
            evt.consume();
        }
    }//GEN-LAST:event_tfCHPSIKeyTyped

    private void jlNome15FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jlNome15FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jlNome15FocusLost

    private void tfCHTotalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCHTotalFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCHTotalFocusLost

    private void tfCHTotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCHTotalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCHTotalKeyTyped

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
            java.util.logging.Logger.getLogger(CadastroDespesasProfessor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroDespesasProfessor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroDespesasProfessor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroDespesasProfessor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadastroDespesasProfessor dialog = new CadastroDespesasProfessor(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btLimpar;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JButton btPesqusar1;
    private javax.swing.JButton btSalvar;
    private javax.swing.JButton btVoltar;
    private javax.swing.JLabel jLObrigatorioCurso3;
    private javax.swing.JLabel jLObrigatorioSemestre;
    private javax.swing.JLabel jLObrigatorioSemestre1;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JComboBox jcAnoExercicio;
    private javax.swing.JLabel jlCadTitulacao;
    private javax.swing.JLabel jlNome10;
    private javax.swing.JLabel jlNome12;
    private javax.swing.JLabel jlNome13;
    private javax.swing.JLabel jlNome14;
    private javax.swing.JLabel jlNome15;
    private javax.swing.JLabel jlNome2;
    private javax.swing.JLabel jlNome23;
    private javax.swing.JLabel jlNome27;
    private javax.swing.JLabel jlNome4;
    private javax.swing.JLabel jlNome5;
    private javax.swing.JLabel jlNome6;
    private javax.swing.JLabel jlNome8;
    private javax.swing.JLabel jlNome9;
    private javax.swing.JTextField tfCHADM;
    private javax.swing.JTextField tfCHADS;
    private javax.swing.JTextField tfCHCIC;
    private javax.swing.JTextField tfCHEDF;
    private javax.swing.JTextField tfCHENF;
    private javax.swing.JTextField tfCHFIS;
    private javax.swing.JTextField tfCHPSI;
    private javax.swing.JTextField tfCHSSO;
    private javax.swing.JTextField tfCHTotal;
    private javax.swing.JTextField tfNomeProfessor;
    private javax.swing.JLabel tfTitulacao;
    private javax.swing.JLabel tfValorADM;
    private javax.swing.JLabel tfValorADS;
    private javax.swing.JLabel tfValorCIC;
    private javax.swing.JLabel tfValorEDF;
    private javax.swing.JLabel tfValorENF;
    private javax.swing.JLabel tfValorFIS;
    private javax.swing.JLabel tfValorHoraAula;
    private javax.swing.JLabel tfValorPSI;
    private javax.swing.JLabel tfValorSSO;
    private javax.swing.JTextField tfValorTotal;
    // End of variables declaration//GEN-END:variables
}
