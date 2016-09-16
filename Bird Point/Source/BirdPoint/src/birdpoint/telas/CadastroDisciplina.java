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
import birdpoint.semestre.Semestre;
import birdpoint.semestre.SemestreDAO;
import birdpoint.semestre.SemestreTableModel;
import birdpoint.titulacao.Titulacao;
import birdpoint.titulacao.TitulacaoDAO;
import birdpoint.titulacao.TitulacaoTableModel;
import birdpoint.util.Util;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author AlexRamon
 */
public class CadastroDisciplina extends javax.swing.JDialog {

    Semestre semestre = new Semestre();
    SemestreDAO semestreDAO = new SemestreDAO();

    Disciplina disciplina = new Disciplina();
    DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

    Curso curso = new Curso();
    CursoDAO cursoDAO = new CursoDAO();

    public CadastroDisciplina(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        getRootPane().setDefaultButton(btSalvar);
        btLimparActionPerformed(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfNomeDisciplina = new javax.swing.JTextField();
        jlNome = new javax.swing.JLabel();
        btVoltar = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btLimpar = new javax.swing.JButton();
        btPesquisar = new javax.swing.JButton();
        tfNomeCurso = new javax.swing.JTextField();
        btCurso = new javax.swing.JButton();
        jlNome1 = new javax.swing.JLabel();
        jlNome2 = new javax.swing.JLabel();
        tfNomeSemestre = new javax.swing.JTextField();
        jlNome3 = new javax.swing.JLabel();
        btSemestre = new javax.swing.JButton();
        tfCargaHoraria = new javax.swing.JTextField();
        jLObrigatorioDisciplina = new javax.swing.JLabel();
        jLObrigatorioCurso = new javax.swing.JLabel();
        jLObrigatorioSemestre = new javax.swing.JLabel();
        jLObrigatorioCargaHoraria = new javax.swing.JLabel();
        jlCadTitulacao = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 421));
        setUndecorated(true);
        getContentPane().setLayout(null);

        tfNomeDisciplina.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfNomeDisciplina.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        getContentPane().add(tfNomeDisciplina);
        tfNomeDisciplina.setBounds(190, 140, 310, 30);

        jlNome.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jlNome.setText("Carga Horária.:");
        getContentPane().add(jlNome);
        jlNome.setBounds(70, 260, 120, 30);

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

        tfNomeCurso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfNomeCurso.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        getContentPane().add(tfNomeCurso);
        tfNomeCurso.setBounds(190, 180, 310, 30);

        btCurso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/sub_pesquisar.png"))); // NOI18N
        btCurso.setContentAreaFilled(false);
        btCurso.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btCurso.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCursoActionPerformed(evt);
            }
        });
        getContentPane().add(btCurso);
        btCurso.setBounds(500, 180, 30, 30);

        jlNome1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jlNome1.setText("Nome da Disciplina.:");
        getContentPane().add(jlNome1);
        jlNome1.setBounds(30, 140, 160, 30);

        jlNome2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jlNome2.setText("Curso.:");
        getContentPane().add(jlNome2);
        jlNome2.setBounds(130, 180, 60, 30);

        tfNomeSemestre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfNomeSemestre.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        getContentPane().add(tfNomeSemestre);
        tfNomeSemestre.setBounds(190, 220, 310, 30);

        jlNome3.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jlNome3.setText("Semestre.:");
        getContentPane().add(jlNome3);
        jlNome3.setBounds(100, 220, 90, 30);

        btSemestre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btSemestre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/sub_pesquisar.png"))); // NOI18N
        btSemestre.setContentAreaFilled(false);
        btSemestre.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btSemestre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSemestre.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btSemestre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSemestreActionPerformed(evt);
            }
        });
        getContentPane().add(btSemestre);
        btSemestre.setBounds(500, 220, 30, 30);

        tfCargaHoraria.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfCargaHoraria.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        getContentPane().add(tfCargaHoraria);
        tfCargaHoraria.setBounds(190, 260, 310, 30);

        jLObrigatorioDisciplina.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLObrigatorioDisciplina.setForeground(new java.awt.Color(204, 0, 0));
        jLObrigatorioDisciplina.setText("*");
        getContentPane().add(jLObrigatorioDisciplina);
        jLObrigatorioDisciplina.setBounds(500, 130, 20, 30);

        jLObrigatorioCurso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLObrigatorioCurso.setForeground(new java.awt.Color(204, 0, 0));
        jLObrigatorioCurso.setText("*");
        getContentPane().add(jLObrigatorioCurso);
        jLObrigatorioCurso.setBounds(500, 170, 20, 30);

        jLObrigatorioSemestre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLObrigatorioSemestre.setForeground(new java.awt.Color(204, 0, 0));
        jLObrigatorioSemestre.setText("*");
        getContentPane().add(jLObrigatorioSemestre);
        jLObrigatorioSemestre.setBounds(500, 210, 20, 30);

        jLObrigatorioCargaHoraria.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLObrigatorioCargaHoraria.setForeground(new java.awt.Color(204, 0, 0));
        jLObrigatorioCargaHoraria.setText("*");
        getContentPane().add(jLObrigatorioCargaHoraria);
        jLObrigatorioCargaHoraria.setBounds(500, 250, 20, 30);

        jlCadTitulacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/CadastroDisciplina.png"))); // NOI18N
        jlCadTitulacao.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(jlCadTitulacao);
        jlCadTitulacao.setBounds(0, 0, 600, 420);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        dispose();
    }//GEN-LAST:event_btVoltarActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        if (Util.chkVazio(tfNomeDisciplina.getText(), tfNomeCurso.getText(), tfCargaHoraria.getText(), tfNomeSemestre.getText())) {
            disciplina.setNomeDisciplina(tfNomeDisciplina.getText());
            disciplina.setCargaHoraria(Integer.parseInt(tfCargaHoraria.getText()));
            disciplina.setSemestre(semestre);
            disciplinaDAO.salvar(disciplina);
            btLimparActionPerformed(null);
        }
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        Object[] options = {"Sim", "Não"};
        if (disciplina.getIdDisciplina() != 0) {
            if (JOptionPane.showOptionDialog(null, "Deseja excluir a Disciplina " + disciplina.getNomeDisciplina()
                    + "?", "BirdPoint", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]) == JOptionPane.YES_OPTION) {
                if (disciplinaDAO.remover(disciplina)) {
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Não foi possível excluir a Disciplina " + semestre.getNomeSemestre(),
                            "Erro ao Excluir", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "A exclusão foi cancelada!");
            }

        }
        btLimparActionPerformed(null);

    }//GEN-LAST:event_btExcluirActionPerformed

    private void btLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparActionPerformed
        Util.limparCamposGenerico(this);
        btExcluir.setEnabled(false);
        tfNomeCurso.setEnabled(false);
        tfNomeSemestre.setEnabled(false);
        curso = new Curso();
        semestre = new Semestre();
        disciplina = new Disciplina();
    }//GEN-LAST:event_btLimparActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        if (curso.getIdCurso() != 0) {
            List<Disciplina> lista;
            lista = (disciplinaDAO.listar());
            List<Disciplina> listaFiltrada = new ArrayList<>();
            for (Disciplina disciplina1 : lista) {
                if (disciplina1.getSemestre().getCurso().getIdCurso() == curso.getIdCurso()) {
                    listaFiltrada.add(disciplina1);
                }
            }
            if (semestre.getIdSemestre() != 0) {
                listaFiltrada.clear();
                for (Disciplina disciplina1 : lista) {
                    if (disciplina1.getSemestre().getIdSemestre() == semestre.getIdSemestre()) {
                        listaFiltrada.add(disciplina1);
                    }
                }
            }
            DisciplinaTableModel itm = new DisciplinaTableModel(listaFiltrada);
            Object objetoRetorno = PesquisaGenerica.exibeTela(itm, "Disciplina");
            if (objetoRetorno != null) {
                disciplina = disciplinaDAO.consultarObjetoId("idDisciplina", objetoRetorno);
                tfNomeDisciplina.setText(disciplina.getNomeDisciplina());
                tfNomeCurso.setText(disciplina.getSemestre().getCurso().getNomeCurso());
                tfNomeSemestre.setText(disciplina.getSemestre().getNomeSemestre());
                tfCargaHoraria.setText(String.valueOf(disciplina.getCargaHoraria()));
                semestre = disciplina.getSemestre();
                curso = disciplina.getSemestre().getCurso();
                btExcluir.setEnabled(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione o Curso!", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCursoActionPerformed
        if(semestre.getIdSemestre()!=0){
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
            java.util.logging.Logger.getLogger(CadastroDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadastroDisciplina dialog = new CadastroDisciplina(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btLimpar;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JButton btSalvar;
    private javax.swing.JButton btSemestre;
    private javax.swing.JButton btVoltar;
    private javax.swing.JLabel jLObrigatorioCargaHoraria;
    private javax.swing.JLabel jLObrigatorioCurso;
    private javax.swing.JLabel jLObrigatorioDisciplina;
    private javax.swing.JLabel jLObrigatorioSemestre;
    private javax.swing.JLabel jlCadTitulacao;
    private javax.swing.JLabel jlNome;
    private javax.swing.JLabel jlNome1;
    private javax.swing.JLabel jlNome2;
    private javax.swing.JLabel jlNome3;
    private javax.swing.JTextField tfCargaHoraria;
    private javax.swing.JTextField tfNomeCurso;
    private javax.swing.JTextField tfNomeDisciplina;
    private javax.swing.JTextField tfNomeSemestre;
    // End of variables declaration//GEN-END:variables
}