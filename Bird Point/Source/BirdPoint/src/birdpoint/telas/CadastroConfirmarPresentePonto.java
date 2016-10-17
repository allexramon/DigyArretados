/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.telas;

import birdpoint.professor.Professor;
import birdpoint.professor.ProfessorDAO;
import birdpoint.registro.ponto.Ponto;
import birdpoint.registro.ponto.PontoDAO;
import birdpoint.registro.ponto.PontoTableModel;
import birdpoint.util.LeitorBiometrico;
import birdpoint.util.Relogio;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPTemplate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Adriano Lima
 */
public class CadastroConfirmarPresentePonto extends javax.swing.JDialog {

    Professor professor = new Professor();
    ProfessorDAO professorDAO = new ProfessorDAO();

    SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatarHora = new SimpleDateFormat("HH");
    SimpleDateFormat formatarMinuto = new SimpleDateFormat("mm");
    Date dataHoraSistema;

    Ponto ponto = new Ponto();
    PontoDAO pontoDAO = new PontoDAO();

    //Lista de Pontos diários
    List<Ponto> listaPontos;

    //Lista de Professores diário
    List<Professor> listaProfessoresAula;

    //Lista para verificação do ponto
    List<Professor> lista;
    //Lista dos professores validados
    List<Ponto> listaPontosBatidos = new ArrayList<>();

    LeitorBiometrico digital = new LeitorBiometrico();
    DPFPTemplate templateDigital = DPFPGlobal.getTemplateFactory().createTemplate();

    public CadastroConfirmarPresentePonto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        lista = (professorDAO.listar());
        atualizarTabela();
        listaProfessores();
        mostrarHora();
        btPesquisar2.setVisible(false);

        //Sobrescrita para abrir o formulário antes de finalizar o construtor
        new Thread() {//instancia nova thread já implementando o método run()
            @Override
            public void run() {//sobrescreve o método run()
                btPesquisar2ActionPerformed(null);
            }//- Fim do Run
        }.start();//Fim Thread

        // Essa Thread verifica o horário para preencher os campos de ponto no banco de dados
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    preencherListaPontosLimpos();
                    try {
                        sleep(300000);
                    } catch (Exception e) {
                    }
                }
            }
        }.start();

    }

    // Este método irá gerar a lista de pontos diária
    private void preencherListaPontosLimpos() {
        listarPontos();
        dataHoraSistema = new Date();
        int hora = Integer.parseInt(formatarHora.format(dataHoraSistema));
        int minutos = Integer.parseInt(formatarMinuto.format(dataHoraSistema));
        String turno;
        if (listaPontos.isEmpty()) {
            for (Professor professorLista : listaProfessoresAula) {
                ponto.setProfessor(professorLista);
                if ((hora >= 00 && hora <= 12) || (hora == 13 && minutos <= 20)) {
                    turno = "Manhã";
                } else if ((hora >= 13 && hora <= 16) || (hora == 17 && minutos <= 30)) {
                    turno = "Tarde";
                } else {
                    turno = "Noite";
                }
                ponto.setTurnoPonto(turno);
                ponto.setDataPonto(formatarData.format(dataHoraSistema));
                pontoDAO.salvar(ponto);
                limparCampos();
            }
        }
    }

    // Este método carrega o ponto do professor que está colocando a digital
    private Ponto carregarPonto(int idProfessor) {
        for (Ponto listaPonto : listaPontos) {
            if (listaPonto.getProfessor().getIdProfessor() == idProfessor) {
                return listaPonto;
            }
        }
        return null;
    }

    private void atualizarTabela() {
        PontoTableModel pontosBatidosTableModel = new PontoTableModel(listaPontosBatidos);
        tbProfessoresPonto.setModel(pontosBatidosTableModel);
        tbProfessoresPonto.getColumnModel().getColumn(0).setPreferredWidth(300);
    }

    // Método para listar os pontos diários
    public void listarPontos() {
        dataHoraSistema = new Date();
        String turno;
        listaPontos = pontoDAO.checkExists("dataPonto", formatarData.format(dataHoraSistema));
        List<Ponto> listaFiltradaPontos = new ArrayList<>();
        dataHoraSistema = new Date();
        int hora = Integer.parseInt(formatarHora.format(dataHoraSistema));
        int minutos = Integer.parseInt(formatarMinuto.format(dataHoraSistema));

        if ((hora >= 00 && hora <= 12) || (hora == 13 && minutos <= 20)) {
            turno = "Manhã";
        } else if ((hora >= 13 && hora <= 16) || (hora == 17 && minutos <= 30)) {
            turno = "Tarde";
        } else {
            turno = "Noite";
        }
        if (!listaPontos.isEmpty()) {
            for (Ponto filtro : listaPontos) {
                if (filtro.getTurnoPonto().equals(turno)) {
                    listaFiltradaPontos.add(filtro);
                }
            }
            listaPontos = listaFiltradaPontos;
        }
    }

    public void listaProfessores() {
        listaProfessoresAula = professorDAO.listar();
    }

    public int verificarSeEntradaOuSaida(int idProfessor) {
        for (Ponto listaPonto : listaPontos) {
            if (listaPonto.getProfessor().getIdProfessor() == idProfessor) {
                if (listaPonto.getHoraEntrada() == null) {
                    return 1;
                } else if (listaPonto.getHoraSaida() == null) {
                    return 2;
                } else {
                    return 3;
                }
            }
        }
        return 0;
    }

    public void mostrarHora() {
        Relogio ah = new Relogio(tfHora);
        ah.mostrarData(true);
        Thread thHora = ah;
        thHora.start();
    }

    private void telaMensagemPonto(Professor professor, boolean verificarEntradaOuSaida) {
        new MensagemPonto(null, rootPaneCheckingEnabled, professor, verificarEntradaOuSaida).setVisible(true);
    }

    private void salvarPonto(Professor professor) {
        ponto = carregarPonto(professor.getIdProfessor());
        dataHoraSistema = new Date();
        new Thread() {//instancia nova thread já implementando o método run()
            @Override
            public void run() {//sobrescreve o método run()
                if (verificarSeEntradaOuSaida(professor.getIdProfessor()) == 1) {
                    ponto.setHoraEntrada(dataHoraSistema);
                    telaMensagemPonto(professor, true);
                } else if (verificarSeEntradaOuSaida(professor.getIdProfessor()) == 2) {
                    ponto.setHoraSaida(dataHoraSistema);
                    telaMensagemPonto(professor, false);
                } else if (verificarSeEntradaOuSaida(professor.getIdProfessor()) == 3) {
                    ponto.setHoraEntrada(dataHoraSistema);
                    ponto.setHoraSaida(null);
                    telaMensagemPonto(professor, true);
                }
            }//- Fim do Run
        }.start();//Fim Thread

        ponto.setProfessor(professor);
        pontoDAO.salvar(ponto);
        listaPontosBatidos.add(0, ponto);
        limparCampos();
    }

    private void limparCampos() {
        ponto = new Ponto();
        professor = new Professor();
        listarPontos();
    }

    private void compararDigital() {
        professor = new Professor();
        professor = digital.verificarSeCadastrado(null, lista);
        if (professor != null) {
            salvarPonto(professor);
            atualizarTabela();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 421));
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
        btVoltar.setBounds(20, 340, 90, 70);

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
        btPesquisar2.setBounds(210, 340, 160, 69);

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
        jScrollPane1.setBounds(30, 110, 540, 230);

        jlProfessorNaoLocalizado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(jlProfessorNaoLocalizado);
        jlProfessorNaoLocalizado.setBounds(160, 94, 290, 20);

        tfHora.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        tfHora.setText("Hora.:");
        getContentPane().add(tfHora);
        tfHora.setBounds(400, 90, 180, 19);

        jlCadProfessores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/cadProfessor.png"))); // NOI18N
        jlCadProfessores.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(jlCadProfessores);
        jlCadProfessores.setBounds(0, 0, 600, 420);

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
            java.util.logging.Logger.getLogger(CadastroConfirmarPresentePonto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroConfirmarPresentePonto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroConfirmarPresentePonto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroConfirmarPresentePonto.class
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

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadastroConfirmarPresentePonto dialog = new CadastroConfirmarPresentePonto(new javax.swing.JFrame(), true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlCadProfessores;
    private javax.swing.JLabel jlProfessorNaoLocalizado;
    private javax.swing.JFileChooser selecionarFoto;
    private javax.swing.JTable tbProfessoresPonto;
    private javax.swing.JLabel tfHora;
    // End of variables declaration//GEN-END:variables
}
