/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.telas;

import birdpoint.anoexercicio.AnoExercicio;
import birdpoint.anoexercicio.AnoExercicioDAO;
import birdpoint.email.Email;
import birdpoint.professor.Professor;
import birdpoint.professor.ProfessorDAO;
import birdpoint.quadrohorarios.QuadroHorarios;
import birdpoint.quadrohorarios.QuadroHorariosDAO;
import birdpoint.registro.ponto.Ponto;
import birdpoint.registro.ponto.PontoDAO;
import birdpoint.registro.ponto.PontoTableModel;
import birdpoint.util.LeitorBiometrico;
import birdpoint.util.Relogio;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPTemplate;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CadastroPontoEletronico extends javax.swing.JDialog {

    Email email = new Email();
    boolean enviouEmail = false;

    List<QuadroHorarios> listaQuadroHorarios;
    QuadroHorariosDAO quadroDAO = new QuadroHorariosDAO();

    List<Professor> listaProfessores;
    Professor professor = new Professor();
    ProfessorDAO professorDAO = new ProfessorDAO();

    List<Ponto> listaPontoLocal = new ArrayList<>();
    List<Ponto> listaPontosDiario = new ArrayList<>();
    Ponto ponto = new Ponto();
    PontoDAO pontoDAO = new PontoDAO();

    List<AnoExercicio> listaAnosExercicio;
    AnoExercicio anoExercicio = new AnoExercicio();
    AnoExercicioDAO anoExercicioDAO = new AnoExercicioDAO();

    SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatarHora = new SimpleDateFormat("HH");
    SimpleDateFormat formatarMinuto = new SimpleDateFormat("mm");
    SimpleDateFormat formatarDiaSemana = new SimpleDateFormat("E");
    SimpleDateFormat formatarHoraCompleta = new SimpleDateFormat("HH:mm:ss");
    Date dataHoraSistema;

    LeitorBiometrico digital = new LeitorBiometrico();
    DPFPTemplate templateDigital = DPFPGlobal.getTemplateFactory().createTemplate();

    List<Integer> listaCodigoProfessoresManha = new ArrayList<>();
    List<Integer> listaCodigoProfessoresTarde = new ArrayList<>();
    List<Integer> listaCodigoProfessoresNoite = new ArrayList<>();

    List<Professor> listaProfessoresManha = new ArrayList<>();
    List<Professor> listaProfessoresTarde = new ArrayList<>();
    List<Professor> listaProfessoresNoite = new ArrayList<>();

    List<Ponto> listaPontoTabela = new ArrayList<>();

    public CadastroPontoEletronico(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        dataHoraSistema = new Date();
        listaPontosDiario = pontoDAO.checkExistseq("dataPonto", formatarData.format(dataHoraSistema));
        listaProfessores = professorDAO.listar();
        carregarAnoExercicioAtual();
        listaQuadroHorarios = quadroDAO.checkExists("anoExercicio", anoExercicio.getNomeAnoExercicio());
        mostrarHora();
        btPesquisar2.setVisible(false);
        atualizarTabela();

        // Só cadastra o ponto diário se não tiver sido cadastrado naquele dia ainda
        if (listaPontosDiario.isEmpty()) {
            cadastrarPontoDiario();
        }

        new Thread() {
            @Override
            public void run() {
                btPesquisar2ActionPerformed(null);
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        carregarAnoExercicioAtual();
                        System.out.println("DeuCerto");
                        dataHoraSistema = new Date();
                        int hora = Integer.parseInt(formatarHora.format(dataHoraSistema));
                        if (hora == 23 && enviouEmail == false) {
                            email.enviarEmail();
                            enviouEmail = true;
                        } else if (hora < 23) {
                            enviouEmail = false;
                        }
                        sleep(900000);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(CadastroPontoEletronico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();

    }

    public void atualizarTabela() {
        PontoTableModel modeloTabela = new PontoTableModel(listaPontoTabela);
        tbProfessoresPonto.setModel(modeloTabela);
        tbProfessoresPonto.getColumnModel().getColumn(0).setPreferredWidth(300);
    }

    // Este método registrará o ponto do professor
    public void registrarPresentePonto(Professor professor) {
        boolean verificarSeAtualizou = false;
        ponto = new Ponto();
        List<Ponto> listaPontosProfessor = pontoDAO.checkExistseq("dataPonto", formatarData.format(dataHoraSistema));
        List<Ponto> listaFiltrada = new ArrayList<>();
        for (Ponto pontoLocal : listaPontosProfessor) {
            if (pontoLocal.getProfessor().getIdProfessor() == professor.getIdProfessor()) {
                listaFiltrada.add(pontoLocal);
            }
        }
        dataHoraSistema = new Date();
        for (Ponto pontoLocal : listaFiltrada) {
            if (pontoLocal.getTurnoPonto().equalsIgnoreCase(carregarTurno())) {
                verificarSeAtualizou = true;
                ponto = pontoLocal;
                if (ponto.getHoraEntradaPonto() == null) {
                    ponto.setHoraEntradaPonto(Time.valueOf(formatarHoraCompleta.format(dataHoraSistema)));
                } else if (ponto.getHoraEntradaPonto() != null && ponto.getHoraSaidaPonto() == null) {
                    ponto.setHoraSaidaPonto(Time.valueOf(formatarHoraCompleta.format(dataHoraSistema)));
                } else {
                    ponto.setHoraEntradaPonto(Time.valueOf(formatarHoraCompleta.format(dataHoraSistema)));
                    ponto.setHoraSaidaPonto(null);
                }
                listaPontoTabela.add(0, ponto);
                atualizarTabela();
                pontoDAO.atualizar(ponto);
                abrirTelaMensagemPonto(ponto);
            }
        }
        if (verificarSeAtualizou == false) {
            ponto.setAnoExercicio(anoExercicio);
            ponto.setDataPonto(formatarData.format(dataHoraSistema));
            ponto.setDiaDaSemana(formatarDiaSemana.format(dataHoraSistema));
            ponto.setProfessor(professor);
            ponto.setTurnoPonto(carregarTurno());
            ponto.setHoraEntradaPonto(Time.valueOf(formatarHoraCompleta.format(dataHoraSistema)));
            listaPontoTabela.add(0, ponto);
            atualizarTabela();
            pontoDAO.adicionar(ponto);
            abrirTelaMensagemPonto(ponto);
        }

    }

    public void abrirTelaMensagemPonto(Ponto ponto) {
        String entradaOuSaida = "";
        if (ponto.getHoraSaidaPonto() == null) {
            entradaOuSaida = "Entrada";
        } else {
            entradaOuSaida = "Saída";
        }
        new MensagemPonto(null, true, ponto.getProfessor(), entradaOuSaida).setVisible(true);
    }

// Este método retorna o turno baseado no horário atual
    public String carregarTurno() {
        dataHoraSistema = new Date();
        int hora = Integer.parseInt(formatarHora.format(dataHoraSistema));
        int minuto = Integer.parseInt(formatarMinuto.format(dataHoraSistema));
        if ((hora >= 5 && hora <= 12) || ((hora == 13) && minuto <= 15)) {
            return "Manhã";
        } else if ((hora >= 13 && hora <= 16) || ((hora == 17) && minuto <= 20)) {
            return "Tarde";
        } else {
            return "Noite";
        }
    }

//este método deve preencher a lista de codigo de professores sem que eles se repitam
    public void preencherListaProfessor(int[] arrayProfessores, String turno) {
        dataHoraSistema = new Date();
        for (int i = 0; i < arrayProfessores.length; i++) {
            if (arrayProfessores[i] != 0) {
                if (turno.equalsIgnoreCase("Manhã")) {
                    if (!listaCodigoProfessoresManha.contains(arrayProfessores[i])) {
                        listaCodigoProfessoresManha.add(arrayProfessores[i]);
                    }
                } else if (turno.equalsIgnoreCase("Tarde")) {
                    if (!listaCodigoProfessoresTarde.contains(arrayProfessores[i])) {
                        listaCodigoProfessoresTarde.add(arrayProfessores[i]);
                    }
                } else if (turno.equalsIgnoreCase("Noite")) {
                    if (!listaCodigoProfessoresNoite.contains(arrayProfessores[i])) {
                        listaCodigoProfessoresNoite.add(arrayProfessores[i]);
                    }
                }
            }
        }
    }

    //Este método captura somente o codigo dos professores daquele dia, exemplo, segunda feira
    public int[] capturarProfessoresDoDia(int[] arrayParametro) {
        dataHoraSistema = new Date();
        List<Integer> listaCodigo = new ArrayList();
        if (formatarDiaSemana.format(dataHoraSistema).equalsIgnoreCase("Seg")) {
            for (int i = 0; i < 6; i++) {
                listaCodigo.add(arrayParametro[i]);
            }
        } else if (formatarDiaSemana.format(dataHoraSistema).equalsIgnoreCase("Ter")) {
            for (int i = 6; i < 12; i++) {
                listaCodigo.add(arrayParametro[i]);
            }
        } else if (formatarDiaSemana.format(dataHoraSistema).equalsIgnoreCase("Qua")) {
            for (int i = 12; i < 18; i++) {
                listaCodigo.add(arrayParametro[i]);
            }
        } else if (formatarDiaSemana.format(dataHoraSistema).equalsIgnoreCase("Qui")) {
            for (int i = 18; i < 24; i++) {
                listaCodigo.add(arrayParametro[i]);
            }
        } else if (formatarDiaSemana.format(dataHoraSistema).equalsIgnoreCase("Sex")) {
            for (int i = 24; i < 30; i++) {
                listaCodigo.add(arrayParametro[i]);
            }
        } else if (formatarDiaSemana.format(dataHoraSistema).equalsIgnoreCase("Sáb")) {
            for (int i = 30; i < 36; i++) {
                listaCodigo.add(arrayParametro[i]);
            }
        }
        int[] arrayRetorno = new int[6];
        try {

            for (int i = 0; i < arrayRetorno.length; i++) {
                arrayRetorno[i] = listaCodigo.get(i);
            }
        } catch (Exception e) {
        }

        return arrayRetorno;
    }

    public void cadastrarPontoDiario() {
        dataHoraSistema = new Date();
        for (QuadroHorarios quadroHorario : listaQuadroHorarios) {
            int[] arrayProfessoresDiario = capturarProfessoresDoDia(quadroHorario.getOrdenacaoProfessores());
            if (quadroHorario.getTurno().equalsIgnoreCase("Manhã")) {
                preencherListaProfessor(arrayProfessoresDiario, "Manhã");
            } else if (quadroHorario.getTurno().equalsIgnoreCase("Tarde")) {
                preencherListaProfessor(arrayProfessoresDiario, "Tarde");
            } else if (quadroHorario.getTurno().equalsIgnoreCase("Noite")) {
                preencherListaProfessor(arrayProfessoresDiario, "Noite");
            }
        }
        carregarProfessoresNaLista();
        for (Professor profManha : listaProfessoresManha) {
            ponto.setAnoExercicio(anoExercicio);
            ponto.setDataPonto(formatarData.format(dataHoraSistema));
            ponto.setProfessor(profManha);
            ponto.setTurnoPonto("Manhã");
            ponto.setDiaDaSemana(formatarDiaSemana.format(dataHoraSistema));
            pontoDAO.adicionar(ponto);
            ponto = new Ponto();
        }
        for (Professor profTarde : listaProfessoresTarde) {
            ponto.setAnoExercicio(anoExercicio);
            ponto.setDataPonto(formatarData.format(dataHoraSistema));
            ponto.setProfessor(profTarde);
            ponto.setTurnoPonto("Tarde");
            ponto.setDiaDaSemana(formatarDiaSemana.format(dataHoraSistema));
            pontoDAO.adicionar(ponto);
            ponto = new Ponto();
        }
        for (Professor profNoite : listaProfessoresNoite) {
            ponto.setAnoExercicio(anoExercicio);
            ponto.setDataPonto(formatarData.format(dataHoraSistema));
            ponto.setProfessor(profNoite);
            ponto.setTurnoPonto("Noite");
            ponto.setDiaDaSemana(formatarDiaSemana.format(dataHoraSistema));
            pontoDAO.adicionar(ponto);
            ponto = new Ponto();
        }
    }

    public void carregarProfessoresNaLista() {
        for (Integer profManha : listaCodigoProfessoresManha) {
            listaProfessoresManha.add(carregarProfessor(profManha));
        }
        for (Integer profTarde : listaCodigoProfessoresTarde) {
            listaProfessoresTarde.add(carregarProfessor(profTarde));
        }
        for (Integer profNoite : listaCodigoProfessoresNoite) {
            listaProfessoresNoite.add(carregarProfessor(profNoite));
        }
    }

    public Professor carregarProfessor(int id) {
        for (Professor professor : listaProfessores) {
            if (professor.getIdProfessor() == id) {
                return professor;
            }
        }
        return null;
    }

    public void carregarAnoExercicioAtual() {
        listaAnosExercicio = anoExercicioDAO.listar();
        for (AnoExercicio anoExercicio1 : listaAnosExercicio) {
            if (anoExercicio1.isAnoExercicioAtual()) {
                anoExercicio = anoExercicio1;
                return;
            }
        }
    }

    public void mostrarHora() {
        Relogio ah = new Relogio(tfHora);
        ah.mostrarData(true);
        Thread thHora = ah;
        thHora.start();
    }

    private void telaMensagemPonto(Professor professor, String verificarEntradaOuSaida) {
        new MensagemPonto(null, rootPaneCheckingEnabled, professor, verificarEntradaOuSaida).setVisible(true);
    }

    // Este método compara a digital inserida no leitor
    private void compararDigital() {
        professor = new Professor();
        professor = digital.verificarSeCadastrado(null, listaProfessores);
        if (professor != null) {
            registrarPresentePonto(professor);
            jlProfessorNaoLocalizado.setText("");

        } else {
            jlProfessorNaoLocalizado.setText("Professor não localizado!");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        selecionarFoto = new javax.swing.JFileChooser();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        btVoltar = new javax.swing.JButton();
        btPesquisar2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProfessoresPonto = new javax.swing.JTable();
        jlProfessorNaoLocalizado = new javax.swing.JLabel();
        tfHora = new javax.swing.JLabel();
        jlCadProfessores = new javax.swing.JLabel();

        selecionarFoto.setMaximumSize(new java.awt.Dimension(580, 245));
        selecionarFoto.setMinimumSize(new java.awt.Dimension(550, 245));
        selecionarFoto.setPreferredSize(new java.awt.Dimension(520, 320));

        jInternalFrame1.setVisible(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(600, 410));
        setMinimumSize(new java.awt.Dimension(600, 410));
        setUndecorated(true);
        setResizable(false);
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
        btVoltar.setBounds(90, 330, 90, 70);

        btPesquisar2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btPesquisar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar.png"))); // NOI18N
        btPesquisar2.setText("Verificar Digital");
        btPesquisar2.setContentAreaFilled(false);
        btPesquisar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btPesquisar2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btPesquisar2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btPesquisar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisar2ActionPerformed(evt);
            }
        });
        getContentPane().add(btPesquisar2);
        btPesquisar2.setBounds(250, 330, 160, 69);

        tbProfessoresPonto.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        tbProfessoresPonto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbProfessoresPonto);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 110, 560, 220);

        jlProfessorNaoLocalizado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(jlProfessorNaoLocalizado);
        jlProfessorNaoLocalizado.setBounds(160, 94, 290, 20);

        tfHora.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        tfHora.setText("Hora.:");
        getContentPane().add(tfHora);
        tfHora.setBounds(400, 90, 180, 19);

        jlCadProfessores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/CadastroDePonto.png"))); // NOI18N
        jlCadProfessores.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(jlCadProfessores);
        jlCadProfessores.setBounds(0, 0, 600, 410);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        dispose();
    }//GEN-LAST:event_btVoltarActionPerformed

    private void btPesquisar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisar2ActionPerformed
        compararDigital();
        new Thread() {
            @Override
            public void run() {
                btPesquisar2ActionPerformed(evt);
            }
        }.start();
    }//GEN-LAST:event_btPesquisar2ActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroPontoEletronico.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroPontoEletronico.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroPontoEletronico.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroPontoEletronico.class
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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                CadastroPontoEletronico dialog = new CadastroPontoEletronico(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btPesquisar2;
    private javax.swing.JButton btVoltar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlCadProfessores;
    private javax.swing.JLabel jlProfessorNaoLocalizado;
    private javax.swing.JFileChooser selecionarFoto;
    private javax.swing.JTable tbProfessoresPonto;
    private javax.swing.JLabel tfHora;
    // End of variables declaration//GEN-END:variables
}
