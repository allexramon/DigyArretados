/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.telas;

import birdpoint.usuario.Usuario;
import birdpoint.usuario.UsuarioDAO;
import birdpoint.usuario.UsuarioTableModel;
import birdpoint.util.Util;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Adriano Lima
 */
public class CadastroUsuario extends javax.swing.JDialog {

    Usuario usuario = new Usuario();
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    /**
     * Creates new form TelaCadastroUsuario
     */
    public CadastroUsuario(java.awt.Frame parent, boolean modal) {
        initComponents();
        btExcluir.setEnabled(false);
        setModal(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlLogin = new javax.swing.JLabel();
        jlSenha = new javax.swing.JLabel();
        jlTipoAcesso = new javax.swing.JLabel();
        tfLogin = new javax.swing.JTextField();
        tfSenha = new javax.swing.JPasswordField();
        tfTipoDeUsuario = new javax.swing.JComboBox();
        jlNomeUsuario = new javax.swing.JLabel();
        tfNomeUsuario = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btLimpar = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(900, 450));
        setMinimumSize(new java.awt.Dimension(900, 450));
        setModal(true);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        jlLogin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlLogin.setText("Login.:");
        getContentPane().add(jlLogin);
        jlLogin.setBounds(110, 180, 50, 17);

        jlSenha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlSenha.setText("Senha.:");
        getContentPane().add(jlSenha);
        jlSenha.setBounds(110, 210, 52, 17);

        jlTipoAcesso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlTipoAcesso.setText("Tipo de Acesso.:");
        getContentPane().add(jlTipoAcesso);
        jlTipoAcesso.setBounds(50, 240, 112, 20);

        tfLogin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(tfLogin);
        tfLogin.setBounds(170, 180, 340, 23);

        tfSenha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(tfSenha);
        tfSenha.setBounds(170, 210, 340, 20);

        tfTipoDeUsuario.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tfTipoDeUsuario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-----", "Administrador", "Coordenador", "Copex", "RH" }));
        tfTipoDeUsuario.setAutoscrolls(true);
        getContentPane().add(tfTipoDeUsuario);
        tfTipoDeUsuario.setBounds(170, 240, 140, 20);

        jlNomeUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlNomeUsuario.setText("Nome do Usuário.:");
        getContentPane().add(jlNomeUsuario);
        jlNomeUsuario.setBounds(30, 150, 140, 20);

        tfNomeUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfNomeUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNomeUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(tfNomeUsuario);
        tfNomeUsuario.setBounds(170, 150, 340, 23);

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/voltar.png"))); // NOI18N
        jButton3.setText("Voltar");
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(jButton3);
        jButton3.setBounds(790, 330, 90, 100);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar.png"))); // NOI18N
        jButton2.setText("Pesquisar");
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(380, 330, 100, 109);

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
        btExcluir.setBounds(500, 330, 80, 100);

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
        btLimpar.setBounds(280, 330, 90, 100);

        btSalvar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/salvar_1.png"))); // NOI18N
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
        btSalvar.setBounds(600, 330, 90, 100);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/cadUsuario.png"))); // NOI18N
        getContentPane().add(jLabel6);
        jLabel6.setBounds(0, 0, 900, 450);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        List<Usuario> lista;
        lista = (usuarioDAO.listar());
        UsuarioTableModel itm = new UsuarioTableModel(lista);
        Object objetoRetorno = PesquisaGenerica.exibeTela(itm, "Usuario");
        if (objetoRetorno != null) {
            usuario = usuarioDAO.consultarObjetoId("idUsuario", objetoRetorno);
            tfLogin.setText(usuario.getLoginUsuario());
            tfNomeUsuario.setText(usuario.getNomeUsuario());
            tfTipoDeUsuario.setSelectedItem(usuario.getTipoDeAcessoUsuario());
            btExcluir.setEnabled(true);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
       if (usuario.getIdUsuario() != 0) {
                if (JOptionPane.showConfirmDialog(rootPane, "Deseja excluir o Usuário " + usuario.getLoginUsuario()
                        + "?", "BirdPoint", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
                    if (usuarioDAO.remover(usuario)) {
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Não foi possível excluir o Usuário " + usuario.getLoginUsuario(),
                                "Erro ao Excluir", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(rootPane, "A exclusão foi cancelada!");
                }

            }
        btLimparActionPerformed(null);
        
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparActionPerformed
       Util.limparCamposGenerico(this);
       btExcluir.setEnabled(false);
       usuario = new Usuario();
    }//GEN-LAST:event_btLimparActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        if(Util.chkVazio(tfLogin.getText(),tfNomeUsuario.getText(),tfSenha.getText(),String.valueOf(tfTipoDeUsuario.getSelectedItem()))){
            usuario.setLoginUsuario(tfLogin.getText());
            usuario.setNomeUsuario(tfNomeUsuario.getText());
            usuario.setSenhaUsuario(tfSenha.getText());
            usuario.setTipoDeAcessoUsuario(String.valueOf(tfTipoDeUsuario.getSelectedItem()));
            usuarioDAO.salvar(usuario);
            btLimparActionPerformed(null);
        }
    }//GEN-LAST:event_btSalvarActionPerformed

    private void tfNomeUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNomeUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNomeUsuarioActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadastroUsuario dialog = new CadastroUsuario(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btSalvar;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jlLogin;
    private javax.swing.JLabel jlNomeUsuario;
    private javax.swing.JLabel jlSenha;
    private javax.swing.JLabel jlTipoAcesso;
    private javax.swing.JTextField tfLogin;
    private javax.swing.JTextField tfNomeUsuario;
    private javax.swing.JPasswordField tfSenha;
    private javax.swing.JComboBox tfTipoDeUsuario;
    // End of variables declaration//GEN-END:variables
}
