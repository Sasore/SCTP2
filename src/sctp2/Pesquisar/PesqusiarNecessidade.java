/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sctp2.Pesquisar;

import sctp2.Paciente.PesquisarPaciente;
import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import sctp2.BancodeDados.conexao;

/**
 *
 * @author Adriano
 */
public class PesqusiarNecessidade extends javax.swing.JFrame {

    /**
     * Creates new form PesqusiarNecessidade
     */
    public PesqusiarNecessidade() {
        initComponents();
        PesquisarPorNecessidade();
        this.setExtendedState(MAXIMIZED_BOTH);//inicia a janela maximizada
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboNecessidade = new javax.swing.JComboBox();
        jCBListaNegra = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTNotificacao = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pesquisar por Necessidade");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pesquisar paciente por necessidade");

        jLabel2.setText("Selecione uma necessidade");

        jComboNecessidade.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboNecessidade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Profilaxia Simples", "Raspagem E Polimento Coronário", "Cirurgia Periodontal", "Exodontia Simples", "Exodontia 3º Molar", "Exodontia Incluso", "Amálgama", "Resina", "RMF", "Endodontia Unirradicular", "Endodontia Birradicular", "Endodontia Trirradicular", "Coroa Total", "Ponte Fixa 3 Elementos", "Ponte Fixa 4 Elementos", "Ponte Fixa Mais que 4 Elementos", "PPR", "Prótese total (Par)", "Prótese + PPR", "Prótese (1)", "Terapia Periodontal de Suporte" }));
        jComboNecessidade.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jComboNecessidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboNecessidadeActionPerformed(evt);
            }
        });

        jCBListaNegra.setText("Incluir pacientes da lista negra");
        jCBListaNegra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBListaNegraActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(51, 102, 255));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Pesquisar");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 102, 255), 1, true));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "paciente", "telefone", "Lista Negra"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(1).setMinWidth(100);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(3).setMinWidth(10);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(10);
        }

        jTNotificacao.setEditable(false);
        jTNotificacao.setBackground(new java.awt.Color(255, 255, 255));
        jTNotificacao.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jTNotificacao.setForeground(new java.awt.Color(51, 102, 255));
        jTNotificacao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTNotificacao.setBorder(null);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jComboNecessidade, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jCBListaNegra, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTNotificacao))
                .addContainerGap(146, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboNecessidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBListaNegra)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTNotificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(51, 102, 255));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/left-arrow.png"))); // NOI18N
        jButton1.setText("Voltar");
        jButton1.setToolTipText("ir para menu principal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/detalhes.png"))); // NOI18N
        jMenu1.setText("Menu");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/house pequena.png"))); // NOI18N
        jMenuItem1.setText("Menu Principal");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fechar.png"))); // NOI18N
        jMenuItem2.setText("Sair do SCTP");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCBListaNegraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBListaNegraActionPerformed
PesquisarPorNecessidade();        // TODO add your handling code here:
    }//GEN-LAST:event_jCBListaNegraActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        sctp2.Principal.principal acesso = new sctp2.Principal.principal();
        acesso.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        sctp2.Principal.principal acesso = new sctp2.Principal.principal();
        acesso.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        PesquisarPorNecessidade();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboNecessidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboNecessidadeActionPerformed
PesquisarPorNecessidade();        // TODO add your handling code here:
    }//GEN-LAST:event_jComboNecessidadeActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        String id= (String) jTable1.getValueAt(jTable1.getSelectedRow(),0);
                sctp2.Paciente.DadosDoPaciente acesso;
        try {
            acesso = new sctp2.Paciente.DadosDoPaciente(id);
              acesso.setVisible(true);
            this.setVisible(false);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PesquisarPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(PesqusiarNecessidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PesqusiarNecessidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PesqusiarNecessidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PesqusiarNecessidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PesqusiarNecessidade().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCBListaNegra;
    private javax.swing.JComboBox jComboNecessidade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTNotificacao;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private String Necessidade() {

        String verificaNecessidade = null;
        Integer necessidade = (Integer) jComboNecessidade.getSelectedIndex();
        switch (necessidade) {
            case 0:
                verificaNecessidade = "nec_ProfilaxiaSimples";
                break;
            case 1:
                verificaNecessidade = "nec_RaspagemPolimentoCoronario";
                break;
            case 2:
                verificaNecessidade = "nec_CirurgiaPeriodontal";
                break;
            case 3:
                verificaNecessidade = "nec_ExodontiaSimples";
                break;
            case 4:
                verificaNecessidade = "nec_ExodontiaMolar";
                break;
            case 5:
                verificaNecessidade = "nec_ExodontiaIncluso";
                break;
            case 6:
                verificaNecessidade = "nec_Amalgama";
                break;
            case 7:
                verificaNecessidade = "nec_Resina";
                break;
            case 8:
                verificaNecessidade = "nec_RMF";
                break;
            case 9:
                verificaNecessidade = "nec_Endodontiauniebirradicular";
                break;
            case 10:
                verificaNecessidade = "nec_EndodontiaBirradicular";
                break;
            case 11:
                verificaNecessidade = "nec_EndodontiaTrirradicular";
                break;
            case 12:
                verificaNecessidade = "nec_CoroaTotal";
                break;
            case 13:
                verificaNecessidade = "nec_PonteFixa3Elementos";
                break;
            case 14:
                verificaNecessidade = "nec_Pontefixa4elementos";
                break;
            case 15:
                verificaNecessidade = "nec_Pontefixamaisque4elementos";
                break;
            case 16:
                verificaNecessidade = "nec_PPR";
                break;
            case 17:
                verificaNecessidade = "nec_ProteseTotalPar";
                break;
            case 18:
                verificaNecessidade = "nec_ProtesePPR";
                break;
            case 19:
                verificaNecessidade = "nec_Protese";
                break;
            case 20:
                verificaNecessidade = "nec_TerapiaPeriodontal";
                break;
            default:
                verificaNecessidade = "Errado";

        }
        return verificaNecessidade;
    }

    private void LimpaTabela() {
        //limpa pesquisas existentes
        jTable1.setSelectionBackground(Color.BLUE);//defini a cor de seleção da tabela para azul
        DefaultTableModel tabela;
        tabela = (DefaultTableModel) jTable1.getModel();
        tabela.setNumRows(0);
        //********************************************
    }

    private void PesquisarPorNecessidade() {
        conexao acesso = new conexao();
        DefaultTableModel valor = (DefaultTableModel) jTable1.getModel();//criando a chave valor para o objeto tabela
        String necessidade = Necessidade();
        ArrayList<Pesquisar> ListarPesquisa = new ArrayList<Pesquisar>();
        LimpaTabela();
        try {
            ListarPesquisa = acesso.PesquisarNecessidade(necessidade, jCBListaNegra.isSelected());//necessidade() é uma função que retorna a string paraconsulta no banco de dados
            if (ListarPesquisa.size() == 0) {
                jTNotificacao.setForeground(Color.red);
                jTNotificacao.setText("Nenhum paciente encontrado");
            } else {
                for (int i = 0; i < ListarPesquisa.size(); i++) {
                    int[] retorno = acesso.PesquisarProntuarioPaciente(ListarPesquisa.get(i).getRg());
                    if(ListarPesquisa.get(i).getListaNegra()==0)valor.addRow((new String[]{Integer.toString(ListarPesquisa.get(i).getCodigo()), ListarPesquisa.get(i).getNome(), ListarPesquisa.get(i).getTelefone(), "Não"}));
                    if(ListarPesquisa.get(i).getListaNegra()==1)valor.addRow((new String[]{Integer.toString(ListarPesquisa.get(i).getCodigo()), ListarPesquisa.get(i).getNome(), ListarPesquisa.get(i).getTelefone(), "Sim"}));
                    jTNotificacao.setForeground(Color.BLUE);
                    jTNotificacao.setText("Clique no nome para ver mais detalhes");
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PesqusiarNecessidade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
