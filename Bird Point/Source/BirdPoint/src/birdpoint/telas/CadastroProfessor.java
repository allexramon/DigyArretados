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
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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

    BufferedImage bi;
    File file;

    public CadastroProfessor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        btLimparActionPerformed(null);
        listarCidades();
        listarTitulacoes();
        this.getRootPane().setDefaultButton(btSalvar);
    }

    public void listarCidades() {
        jcCidade.removeAllItems();
        jcCidade.addItem("-----");
        cidades = cidadeDAO.listar();
        for (Cidade cidades : cidades) {
            jcCidade.addItem(cidades.getNomeCidade());
        }
    }

    public void listarTitulacoes() {
        jcTitulacao.removeAllItems();
        jcTitulacao.addItem("-----");
        titulacoes = titulacaoDAO.listar();
        for (Titulacao titulacoes : titulacoes) {
            jcTitulacao.addItem(titulacoes.getNome());
        }
    }

    private void selecionarFoto() {

        selecionarFoto.setFileFilter(new javax.swing.filechooser.FileFilter() {
            public boolean accept(File f) {
                return (f.getName().endsWith(".jpg")) || f.isDirectory();
            }

            public String getDescription() {
                return "*.jpg";
            }
        });

        int returnVal = selecionarFoto.showOpenDialog(this);
        if (returnVal == selecionarFoto.APPROVE_OPTION) {
            try {
                file = selecionarFoto.getSelectedFile();
                bi = ImageIO.read(file);//carrega a imagem real num buffer  
                BufferedImage aux = new BufferedImage(90, 100, bi.getType());//cria um buffer auxiliar com o tamanho desejado    
                Graphics2D g = aux.createGraphics();//pega a classe graphics do aux para edicao    
                AffineTransform at = AffineTransform.getScaleInstance((double) 90 / bi.getWidth(), (double) 100 / bi.getHeight());//cria a transformacao  
                g.drawRenderedImage(bi, at);//pinta e transforma a imagem real no auxiliar 
                File verificarExisteFoto = new File("fotos/" + file.getName());
                if (verificarExisteFoto.exists()) {
                    JOptionPane.showMessageDialog(rootPane, "Já existe uma foto com esse nome!\n Altere o nome da foto selecionada!",
                            "Erro ao carregar imagem", JOptionPane.ERROR_MESSAGE);
                } else {
                    ImageIcon foto = new ImageIcon();
                    foto.setImage(aux);
                    btFoto.setIcon(foto);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Não foi possível carregar essa imagem",
                        "Erro ao carregar imagem", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    private void carregarFoto(String caminho) {
        try {
            bi = ImageIO.read(new File(caminho));//carrega a imagem real num buffer  
            BufferedImage aux = new BufferedImage(90, 100, bi.getType());//cria um buffer auxiliar com o tamanho desejado    
            Graphics2D g = aux.createGraphics();//pega a classe graphics do aux para edicao    
            AffineTransform at = AffineTransform.getScaleInstance((double) 90 / bi.getWidth(), (double) 100 / bi.getHeight());//cria a transformacao  
            g.drawRenderedImage(bi, at);//pinta e transforma a imagem real no auxiliar 
            ImageIcon foto = new ImageIcon();
            foto.setImage(aux);
            btFoto.setIcon(foto);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Não foi possível carregar a foto do professor",
                    "Erro ao carregar imagem", JOptionPane.ERROR_MESSAGE);
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
        jLObrigatorioNome = new javax.swing.JLabel();
        jLObrigatorioCpf = new javax.swing.JLabel();
        jLObrigatorioTitulacao = new javax.swing.JLabel();
        btAdd21 = new javax.swing.JButton();
        btAdd22 = new javax.swing.JButton();
        btFoto = new javax.swing.JButton();
        jlCadProfessores = new javax.swing.JLabel();

        selecionarFoto.setMaximumSize(new java.awt.Dimension(580, 245));
        selecionarFoto.setMinimumSize(new java.awt.Dimension(550, 245));
        selecionarFoto.setPreferredSize(new java.awt.Dimension(520, 320));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 421));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Nome:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(140, 100, 60, 20);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("E-mail:");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(140, 130, 47, 17);

        tfEmail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        tfEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfEmailActionPerformed(evt);
            }
        });
        getContentPane().add(tfEmail);
        tfEmail.setBounds(200, 130, 360, 23);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Telefone:");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(140, 160, 64, 17);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Rua.:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(50, 270, 40, 20);

        tfRG.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        getContentPane().add(tfRG);
        tfRG.setBounds(380, 230, 180, 22);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Bairro.:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(30, 300, 60, 20);

        tfRua.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        getContentPane().add(tfRua);
        tfRua.setBounds(100, 270, 320, 23);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Número.:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(430, 270, 70, 20);

        tfBairro.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        getContentPane().add(tfBairro);
        tfBairro.setBounds(100, 300, 320, 23);

        jcCidade.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcCidade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-----" }));
        jcCidade.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        getContentPane().add(jcCidade);
        jcCidade.setBounds(100, 230, 200, 23);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Cidade.:");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(30, 230, 60, 19);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Titulação.:");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(20, 200, 80, 17);

        jcTitulacao.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcTitulacao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-----" }));
        jcTitulacao.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        getContentPane().add(jcTitulacao);
        jcTitulacao.setBounds(100, 200, 200, 23);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Situação.:");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(340, 160, 70, 20);

        buttonGroup1.add(jrAtivo);
        jrAtivo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jrAtivo.setSelected(true);
        jrAtivo.setText("Ativo");
        getContentPane().add(jrAtivo);
        jrAtivo.setBounds(420, 160, 61, 25);

        buttonGroup1.add(jrInativo);
        jrInativo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jrInativo.setText("Inativo");
        jrInativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrInativoActionPerformed(evt);
            }
        });
        getContentPane().add(jrInativo);
        jrInativo.setBounds(490, 160, 75, 25);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("CPF.:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(340, 200, 40, 17);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("RG.:");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(340, 230, 30, 20);

        tfNumero.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        getContentPane().add(tfNumero);
        tfNumero.setBounds(500, 270, 60, 23);

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
        btLimpar.setBounds(180, 340, 80, 70);

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
        btPesquisar.setBounds(280, 340, 100, 69);

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
        btExcluir.setBounds(390, 340, 80, 70);

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
        tfTelefone.setBounds(210, 160, 120, 22);

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
        tfCPF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCPFFocusLost(evt);
            }
        });
        getContentPane().add(tfCPF);
        tfCPF.setBounds(380, 200, 180, 22);

        tfNome.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        tfNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNomeActionPerformed(evt);
            }
        });
        getContentPane().add(tfNome);
        tfNome.setBounds(200, 100, 360, 23);

        jLObrigatorioNome.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLObrigatorioNome.setForeground(new java.awt.Color(204, 0, 0));
        jLObrigatorioNome.setText("*");
        getContentPane().add(jLObrigatorioNome);
        jLObrigatorioNome.setBounds(560, 90, 20, 30);

        jLObrigatorioCpf.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLObrigatorioCpf.setForeground(new java.awt.Color(204, 0, 0));
        jLObrigatorioCpf.setText("*");
        getContentPane().add(jLObrigatorioCpf);
        jLObrigatorioCpf.setBounds(560, 190, 20, 30);

        jLObrigatorioTitulacao.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLObrigatorioTitulacao.setForeground(new java.awt.Color(204, 0, 0));
        jLObrigatorioTitulacao.setText("*");
        getContentPane().add(jLObrigatorioTitulacao);
        jLObrigatorioTitulacao.setBounds(300, 190, 10, 30);

        btAdd21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/adicionar20.png"))); // NOI18N
        btAdd21.setContentAreaFilled(false);
        btAdd21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAdd21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdd21ActionPerformed(evt);
            }
        });
        getContentPane().add(btAdd21);
        btAdd21.setBounds(300, 200, 40, 20);

        btAdd22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/adicionar20.png"))); // NOI18N
        btAdd22.setContentAreaFilled(false);
        btAdd22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAdd22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdd22ActionPerformed(evt);
            }
        });
        getContentPane().add(btAdd22);
        btAdd22.setBounds(300, 230, 40, 20);

        btFoto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btFoto.setIcon(new javax.swing.ImageIcon("C:\\Users\\Adriano Lima\\Desktop\\DigyArretados\\Bird Point\\Source\\BirdPoint\\fotos\\fotoMinha.jpg")); // NOI18N
        btFoto.setContentAreaFilled(false);
        btFoto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btFoto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btFoto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFotoActionPerformed(evt);
            }
        });
        getContentPane().add(btFoto);
        btFoto.setBounds(40, 90, 90, 100);

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
        tfCPF.setEnabled(true);
        btFoto.setIcon(new ImageIcon(getClass().getResource("/birdpoint/imagens/default.jpg")));
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
            carregarFoto(professor.getFotoProfessor());
            if (professor.isSituacaoProfessor()) {
                jrAtivo.setSelected(true);
            } else {
                jrInativo.setSelected(true);
            }
            btExcluir.setEnabled(true);
            tfCPF.setEnabled(false);
        }
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        Object[] options = {"Sim", "Não"};
        if (professor.getIdProfessor() != 0) {
            if (JOptionPane.showOptionDialog(rootPane, "Deseja excluir o(a) Professor(a) " + professor.getNomeProfessor()
                    + "?", "BirdPoint", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]) == JOptionPane.YES_OPTION) {
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
        if (professorDAO.consultarValorRepetido("cpfProfessor", tfCPF.getText()) && professor.getIdProfessor() == 0) {
            JOptionPane.showMessageDialog(rootPane, "O CPF '" + tfCPF.getText() + "' já está cadastrado!",
                    "Erro ao salvar", JOptionPane.ERROR_MESSAGE);
        } else if (Util.chkVazio(tfNome.getText(), tfCPF.getText(), String.valueOf(jcTitulacao.getSelectedItem()))) {
            professor.setNomeProfessor(tfNome.getText().toUpperCase());
            professor.setEmailProfessor(tfEmail.getText());
            professor.setRGProfessor(tfRG.getText());
            professor.setCpfProfessor(tfCPF.getText());
            professor.setTelefoneProfessor(tfTelefone.getText());
            professor.setTitulacaoProfessor(String.valueOf(jcTitulacao.getSelectedItem()));
            professor.setCidadeProfessor(String.valueOf(jcCidade.getSelectedItem()));
            professor.setBairroProfessor(tfBairro.getText().toUpperCase());
            professor.setRuaProfessor(tfRua.getText().toUpperCase());
            try {
                professor.setNumeroCasa(Integer.parseInt(tfNumero.getText()));
            } catch (Exception e) {
            }

            if (jrAtivo.isSelected()) {
                professor.setSituacaoProfessor(true);
            } else {
                professor.setSituacaoProfessor(false);
            }
            try {
                ImageIO.write(bi, "jpg", new File("fotos/" + file.getName()));
                professor.setFotoProfessor("fotos/" + file.getName());
            } catch (Exception e) {
            }

            professorDAO.salvar(professor);
            btLimparActionPerformed(null);
        }
    }//GEN-LAST:event_btSalvarActionPerformed

    private void tfNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNomeActionPerformed

    private void tfCPFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCPFFocusLost
        if (!tfCPF.getText().equals("   .   .   -  ")) {
            if (!Util.CPF(tfCPF.getText().toString().replaceAll("\\D*", ""))) {
                JOptionPane.showMessageDialog(rootPane, "Este CPF não é válido, redigite!",
                        "ERRO", JOptionPane.ERROR_MESSAGE);
                tfCPF.setText("");
                tfCPF.requestFocus();
            }
        } else {

        }
    }//GEN-LAST:event_tfCPFFocusLost

    private void btAdd21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdd21ActionPerformed
        Object objeto = CadastroTitulacao.exibeTela();
        listarTitulacoes();
    }//GEN-LAST:event_btAdd21ActionPerformed

    private void btAdd22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdd22ActionPerformed
        Object objeto = CadastroCidade.exibeTela();
        listarCidades();
    }//GEN-LAST:event_btAdd22ActionPerformed

    private void btFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFotoActionPerformed
        selecionarFoto();
    }//GEN-LAST:event_btFotoActionPerformed

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
    private javax.swing.JButton btAdd21;
    private javax.swing.JButton btAdd22;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btFoto;
    private javax.swing.JButton btLimpar;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JButton btSalvar;
    private javax.swing.JButton btVoltar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLObrigatorioCpf;
    private javax.swing.JLabel jLObrigatorioNome;
    private javax.swing.JLabel jLObrigatorioTitulacao;
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
    private javax.swing.JComboBox jcCidade;
    private javax.swing.JComboBox jcTitulacao;
    private javax.swing.JLabel jlCadProfessores;
    private javax.swing.JRadioButton jrAtivo;
    private javax.swing.JRadioButton jrInativo;
    private javax.swing.JFileChooser selecionarFoto;
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
