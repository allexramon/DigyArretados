/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.telas;

import birdpoint.anoexercicio.AnoExercicio;
import birdpoint.anoexercicio.AnoExercicioDAO;
import birdpoint.anoexercicio.AnoExercicioTableModel;
import birdpoint.util.Util;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Adriano Lima
 */
public class CadastroAnoExercicio extends javax.swing.JDialog {

    AnoExercicio anoExercicio = new AnoExercicio();
    AnoExercicio anoExercicioAtual = new AnoExercicio();
    AnoExercicioDAO anoExercicioDAO = new AnoExercicioDAO();
    List<AnoExercicio> listaAnoExercicio;

    public CadastroAnoExercicio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        getRootPane().setDefaultButton(btSalvar);
        btLimparActionPerformed(null);
    }

    public void carregarListaAnoExercicio() {
        listaAnoExercicio = anoExercicioDAO.listar();
    }

    public boolean verificarSeAnoExercicioAtual() {
        for (AnoExercicio anoExercicio1 : listaAnoExercicio) {
            if (anoExercicio1.isAnoExercicioAtual()) {
                anoExercicio1.setAnoExercicioAtual(false);
                anoExercicioAtual = anoExercicio1;
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        btVoltar = new javax.swing.JButton();
        btLimpar = new javax.swing.JButton();
        btPesquisar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        jLObrigatorioCidade = new javax.swing.JLabel();
        tfAnoExercicio = new javax.swing.JTextField();
        jcAnoExercicio = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jlCadCidade = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 421));
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
        btVoltar.setBounds(30, 310, 90, 70);

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
        btLimpar.setBounds(190, 310, 80, 70);

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
        btPesquisar.setBounds(290, 310, 100, 69);

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
        btExcluir.setBounds(400, 310, 80, 70);

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
        btSalvar.setBounds(490, 310, 80, 70);

        jLObrigatorioCidade.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLObrigatorioCidade.setForeground(new java.awt.Color(204, 0, 0));
        jLObrigatorioCidade.setText("*");
        getContentPane().add(jLObrigatorioCidade);
        jLObrigatorioCidade.setBounds(410, 130, 20, 30);

        tfAnoExercicio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfAnoExercicio.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tfAnoExercicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfAnoExercicioKeyTyped(evt);
            }
        });
        getContentPane().add(tfAnoExercicio);
        tfAnoExercicio.setBounds(220, 140, 190, 30);

        jcAnoExercicio.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jcAnoExercicio.setContentAreaFilled(false);
        getContentPane().add(jcAnoExercicio);
        jcAnoExercicio.setBounds(220, 190, 20, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel6.setText("Ano Exercício Atual.:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(60, 190, 160, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel7.setText("Ano Exercício.:");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(100, 140, 120, 30);

        jlCadCidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/cadAnoExercicio.png"))); // NOI18N
        jlCadCidade.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jlCadCidade.setMaximumSize(new java.awt.Dimension(602, 423));
        jlCadCidade.setMinimumSize(new java.awt.Dimension(602, 423));
        jlCadCidade.setPreferredSize(new java.awt.Dimension(602, 423));
        getContentPane().add(jlCadCidade);
        jlCadCidade.setBounds(0, 0, 600, 420);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        dispose();
    }//GEN-LAST:event_btVoltarActionPerformed

    private void btLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparActionPerformed
        Util.limparCamposGenerico(this);
        btExcluir.setEnabled(false);
        anoExercicio = new AnoExercicio();
        carregarListaAnoExercicio();
    }//GEN-LAST:event_btLimparActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        List<AnoExercicio> lista;
        lista = (anoExercicioDAO.listar());
        AnoExercicioTableModel itm = new AnoExercicioTableModel(lista);
        Object objetoRetorno = PesquisaGenerica.exibeTela(itm, "Ano Exercício");
        if (objetoRetorno != null) {
            anoExercicio = anoExercicioDAO.consultarObjetoId("idAnoExercicio", objetoRetorno);
            tfAnoExercicio.setText(anoExercicio.getNomeAnoExercicio());
            if (anoExercicio.isAnoExercicioAtual()) {
                jcAnoExercicio.setSelected(true);
            }
            btExcluir.setEnabled(true);
        }
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        Object[] options = {"Sim", "Não"};
        if (anoExercicio.getIdAnoExercicio() != 0) {
            if (JOptionPane.showOptionDialog(rootPane, "Deseja excluir o Ano Exercício " + anoExercicio.getNomeAnoExercicio()
                    + "?", "BirdPoint", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]) == JOptionPane.YES_OPTION) {
                if (anoExercicioDAO.remover(anoExercicio)) {
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Não foi possível excluir o Ano Exercício " + anoExercicio.getNomeAnoExercicio(),
                            "Erro ao Excluir", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "A exclusão foi cancelada!");
            }

        }
        btLimparActionPerformed(null);
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        if (Util.chkVazio(tfAnoExercicio.getText())) {
            if (jcAnoExercicio.isSelected()) {
                if (verificarSeAnoExercicioAtual()) {
                    anoExercicioDAO.atualizar(anoExercicioAtual);
                }
            }
            anoExercicio.setNomeAnoExercicio(tfAnoExercicio.getText());
            anoExercicio.setAnoExercicioAtual(jcAnoExercicio.isSelected());
            anoExercicioDAO.salvar(anoExercicio);
            btLimparActionPerformed(null);
        }
    }//GEN-LAST:event_btSalvarActionPerformed

    private void tfAnoExercicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfAnoExercicioKeyTyped
        int ascii = evt.getKeyChar();
        if (!(ascii >= 48 && ascii <= 57) && !(ascii == 46) && !(ascii == evt.VK_BACK_SPACE)) {
            evt.consume();
        }
        if (tfAnoExercicio.getText().length() >= 6) {
            evt.consume();
        }
    }//GEN-LAST:event_tfAnoExercicioKeyTyped

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
            java.util.logging.Logger.getLogger(CadastroAnoExercicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroAnoExercicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroAnoExercicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroAnoExercicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                CadastroAnoExercicio dialog = new CadastroAnoExercicio(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btSalvar;
    private javax.swing.JButton btVoltar;
    private javax.swing.JLabel jLObrigatorioCidade;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JCheckBox jcAnoExercicio;
    private javax.swing.JLabel jlCadCidade;
    private javax.swing.JTextField tfAnoExercicio;
    // End of variables declaration//GEN-END:variables
}
