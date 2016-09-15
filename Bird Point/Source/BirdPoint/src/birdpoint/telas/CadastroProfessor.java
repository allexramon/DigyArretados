/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.telas;

import birdpoint.cidade.Cidade;
import birdpoint.cidade.CidadeDAO;
import birdpoint.professor.Professor;
import birdpoint.professor.ProfessorDAO;
import birdpoint.professor.ProfessorTableModel;
import birdpoint.titulacao.Titulacao;
import birdpoint.titulacao.TitulacaoDAO;
import birdpoint.util.Util;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Adriano Lima
 */
public class CadastroProfessor extends javax.swing.JDialog {

    Professor professor = new Professor();
    ProfessorDAO professorDAO = new ProfessorDAO();

    List<Titulacao> titulacoes = new ArrayList<>();
    TitulacaoDAO titulacaoDAO = new TitulacaoDAO();

    List<Cidade> cidades = new ArrayList<>();
    CidadeDAO cidadeDAO = new CidadeDAO();

    public CadastroProfessor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        getRootPane().setDefaultButton(btSalvar);
        btLimparActionPerformed(null);
        listarCidades();
        listarTitulacoes();
    }

    public void listarCidades() {
        cidades = cidadeDAO.listar();
        for (Cidade cidades : cidades) {
            jcCidade.addItem(cidades.getNomeCidade());
        }
    }

    public void listarTitulacoes() {
        titulacoes = titulacaoDAO.listar();
        for (Titulacao titulacoes : titulacoes) {
            jcTitulacao.addItem(titulacoes.getNome());
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfRG = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfRua = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfBairro = new javax.swing.JTextField();
        jcCidade = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jcTitulacao = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jrAtivo = new javax.swing.JRadioButton();
        jrInativo = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tfNumero = new javax.swing.JTextField();
        btVoltar = new javax.swing.JButton();
        btLimpar = new javax.swing.JButton();
        btPesquisar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        tfTelefone = new javax.swing.JFormattedTextField();
        tfCPF = new javax.swing.JFormattedTextField();
        tfNome = new javax.swing.JTextField();
        jlCadProfessores = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/cadProfessor.png"))); // NOI18N
        jLabel1.setText("                                          Cadastro de Professores ");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(600, 421));
        setMinimumSize(new java.awt.Dimension(600, 421));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Nome:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 100, 60, 20);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("E-mail:");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(30, 130, 47, 17);

        tfEmail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        tfEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfEmailActionPerformed(evt);
            }
        });
        getContentPane().add(tfEmail);
        tfEmail.setBounds(90, 130, 250, 23);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Telefone:");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(350, 130, 64, 17);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Rua:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(40, 240, 40, 20);

        tfRG.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        getContentPane().add(tfRG);
        tfRG.setBounds(380, 200, 180, 22);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Bairro.:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(30, 270, 51, 20);

        tfRua.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        getContentPane().add(tfRua);
        tfRua.setBounds(90, 240, 210, 23);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Número:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(310, 240, 70, 20);

        tfBairro.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        getContentPane().add(tfBairro);
        tfBairro.setBounds(90, 270, 210, 23);

        jcCidade.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcCidade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-----" }));
        jcCidade.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        getContentPane().add(jcCidade);
        jcCidade.setBounds(90, 200, 210, 23);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Cidade:");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(30, 200, 60, 19);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Titulação:");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(20, 170, 67, 17);

        jcTitulacao.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcTitulacao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-----" }));
        jcTitulacao.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        getContentPane().add(jcTitulacao);
        jcTitulacao.setBounds(90, 170, 210, 23);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Situação:");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(320, 300, 64, 20);

        buttonGroup1.add(jrAtivo);
        jrAtivo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jrAtivo.setSelected(true);
        jrAtivo.setText("Ativo");
        getContentPane().add(jrAtivo);
        jrAtivo.setBounds(400, 300, 61, 25);

        buttonGroup1.add(jrInativo);
        jrInativo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jrInativo.setText("Inativo");
        jrInativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrInativoActionPerformed(evt);
            }
        });
        getContentPane().add(jrInativo);
        jrInativo.setBounds(470, 300, 75, 25);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("CPF.:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(340, 170, 40, 17);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("RG.:");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(340, 200, 30, 20);

        tfNumero.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        getContentPane().add(tfNumero);
        tfNumero.setBounds(380, 240, 50, 23);

        btVoltar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/voltar.png"))); // NOI18N
        btVoltar.setText("Voltar");
        btVoltar.setContentAreaFilled(false);
        btVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btVoltar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btVoltar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltarActionPerformed(evt);
            }
        });
        getContentPane().add(btVoltar);
        btVoltar.setBounds(20, 340, 90, 70);

        btLimpar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar.png"))); // NOI18N
        btLimpar.setText("Limpar");
        btLimpar.setContentAreaFilled(false);
        btLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btLimpar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btLimpar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparActionPerformed(evt);
            }
        });
        getContentPane().add(btLimpar);
        btLimpar.setBounds(180, 340, 80, 70);

        btPesquisar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar.png"))); // NOI18N
        btPesquisar.setText("Pesquisar");
        btPesquisar.setContentAreaFilled(false);
        btPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btPesquisar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btPesquisar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });
        getContentPane().add(btPesquisar);
        btPesquisar.setBounds(280, 340, 100, 69);

        btExcluir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/excluir.png"))); // NOI18N
        btExcluir.setText("Excluir");
        btExcluir.setContentAreaFilled(false);
        btExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btExcluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btExcluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(btExcluir);
        btExcluir.setBounds(390, 340, 80, 70);

        btSalvar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/salvar.png"))); // NOI18N
        btSalvar.setText("Salvar");
        btSalvar.setContentAreaFilled(false);
        btSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btSalvar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSalvar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btSalvar);
        btSalvar.setBounds(480, 340, 80, 70);

        tfTelefone.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        try {
            tfTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfTelefone.setToolTipText("");
        tfTelefone.setMinimumSize(new java.awt.Dimension(2, 19));
        tfTelefone.setName(""); // NOI18N
        tfTelefone.setPreferredSize(new java.awt.Dimension(2, 19));
        getContentPane().add(tfTelefone);
        tfTelefone.setBounds(420, 130, 140, 22);

        tfCPF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        try {
            tfCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfCPF.setToolTipText("");
        tfCPF.setMinimumSize(new java.awt.Dimension(2, 19));
        tfCPF.setName(""); // NOI18N
        tfCPF.setPreferredSize(new java.awt.Dimension(2, 19));
        getContentPane().add(tfCPF);
        tfCPF.setBounds(380, 170, 180, 22);

        tfNome.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        tfNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNomeActionPerformed(evt);
            }
        });
        getContentPane().add(tfNome);
        tfNome.setBounds(90, 100, 470, 23);

        jlCadProfessores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/cadProfessor.png"))); // NOI18N
        jlCadProfessores.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(jlCadProfessores);
        jlCadProfessores.setBounds(0, 0, 600, 420);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tfEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEmailActionPerformed

    private void jrInativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrInativoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrInativoActionPerformed

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        dispose();
    }//GEN-LAST:event_btVoltarActionPerformed

    private void btLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparActionPerformed
        Util.limparCamposGenerico(this);
        btExcluir.setEnabled(false);
        jrAtivo.setSelected(true);
        professor = new Professor();
    }//GEN-LAST:event_btLimparActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        List<Professor> lista;
        lista = (professorDAO.listar());
        ProfessorTableModel itm = new ProfessorTableModel(lista);
        Object objetoRetorno = PesquisaGenerica.exibeTela(itm, "Professor");
        if (objetoRetorno != null) {
            professor = professorDAO.consultarObjetoId("idProfessor", objetoRetorno);
            tfNome.setText(professor.getNomeProfessor());
            tfEmail.setText(professor.getEmailProfessor());
            tfTelefone.setText(professor.getTelefoneProfessor());
            jcTitulacao.setSelectedItem(professor.getTitulacaoProfessor());
            jcCidade.setSelectedItem(professor.getCidadeProfessor());
            tfCPF.setText(professor.getCpfProfessor());
            tfRG.setText(professor.getRGProfessor());
            tfRua.setText(professor.getRuaProfessor());
            tfBairro.setText(professor.getBairroProfessor());
            tfNumero.setText(String.valueOf(professor.getNumeroCasa()));
            if(professor.isSituacaoProfessor()){
                jrAtivo.setSelected(true);
            }else{
                jrInativo.setSelected(true);
            }
            btExcluir.setEnabled(true);
        }
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        if (professor.getIdProfessor() != 0) {
            if (JOptionPane.showConfirmDialog(rootPane, "Deseja excluir o(a) Professor(a) " + professor.getNomeProfessor()
                    + "?", "BirdPoint", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
                if (professorDAO.remover(professor)) {
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Não foi possível excluir o(a) Professor(a) " + professor.getNomeProfessor(),
                            "Erro ao Excluir", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "A exclusão foi cancelada!");
            }

        }
        btLimparActionPerformed(null);
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        if (Util.chkVazio(tfNome.getText(), tfCPF.getText(), String.valueOf(jcTitulacao.getSelectedItem()))) {
            professor.setNomeProfessor(tfNome.getText());
            professor.setEmailProfessor(tfEmail.getText());
            professor.setRGProfessor(tfRG.getText());
            professor.setCpfProfessor(tfCPF.getText());
            professor.setTelefoneProfessor(tfTelefone.getText());
            professor.setTitulacaoProfessor(String.valueOf(jcTitulacao.getSelectedItem()));
            professor.setCidadeProfessor(String.valueOf(jcCidade.getSelectedItem()));
            professor.setBairroProfessor(tfBairro.getText());
            professor.setRuaProfessor(tfRua.getText());
            try {
                professor.setNumeroCasa(Integer.parseInt(tfNumero.getText()));
            } catch (Exception e) {
            }

            if (jrAtivo.isSelected()) {
                professor.setSituacaoProfessor(true);
            } else {
                professor.setSituacaoProfessor(false);
            }
            professorDAO.salvar(professor);
            btLimparActionPerformed(null);
        }
    }//GEN-LAST:event_btSalvarActionPerformed

    private void tfNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNomeActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroProfessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroProfessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroProfessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroProfessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadastroProfessor dialog = new CadastroProfessor(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JComboBox jcCidade;
    private javax.swing.JComboBox jcTitulacao;
    private javax.swing.JLabel jlCadProfessores;
    private javax.swing.JRadioButton jrAtivo;
    private javax.swing.JRadioButton jrInativo;
    private javax.swing.JTextField tfBairro;
    private javax.swing.JFormattedTextField tfCPF;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfNumero;
    private javax.swing.JTextField tfRG;
    private javax.swing.JTextField tfRua;
    private javax.swing.JFormattedTextField tfTelefone;
    // End of variables declaration//GEN-END:variables
}
