/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sctp2.Paciente;


import java.awt.Color;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import sctp2.BancodeDados.*;
import sctp2.ClassesdeControle.TratamentosNecessarios;

/**
 *
 * @author soare
 */
public class FinalizarTratamento extends javax.swing.JFrame {

    /**
     * Creates new form FinalizarTratamentpo
     */
    private static String rg;

    public FinalizarTratamento(String rg) throws ClassNotFoundException, SQLException {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);//inicia a janela maximizada
        this.rg = rg;
        this.setTitle("SCTP: Finalizar Tratamento");
        MostrarTratamentos(rg);
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
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jNotificacao = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jFinalizaTratamento = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 102, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Selecione os tratamentos que deseja finalizar");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tratamento", "Selecionar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(90);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(15);
        }

        jNotificacao.setEditable(false);
        jNotificacao.setBackground(new java.awt.Color(255, 255, 255));
        jNotificacao.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        jNotificacao.setForeground(new java.awt.Color(51, 102, 255));
        jNotificacao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jNotificacao.setBorder(null);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(274, 274, 274)
                .addComponent(jNotificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 817, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(137, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 817, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(188, 188, 188))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jNotificacao)
                .addGap(25, 25, 25))
        );

        jPanel1.setBackground(new java.awt.Color(51, 102, 255));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/left-arrow.png"))); // NOI18N
        jButton1.setText("Voltar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jFinalizaTratamento.setBackground(new java.awt.Color(255, 255, 255));
        jFinalizaTratamento.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jFinalizaTratamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fim.png"))); // NOI18N
        jFinalizaTratamento.setText("Finalizar tratamentos Selecionados");
        jFinalizaTratamento.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 1, true));
        jFinalizaTratamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFinalizaTratamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jFinalizaTratamento, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(190, 190, 190))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                    .addComponent(jFinalizaTratamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1248, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/detalhes.png"))); // NOI18N
        jMenu1.setText("Menu");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_HOME, 0));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/dente branco.png"))); // NOI18N
        jMenuItem1.setText("Menu Principal");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/iconSearch.png"))); // NOI18N
        jMenuItem3.setText("Pesquisar paciente");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/iconSearch.png"))); // NOI18N
        jMenuItem5.setText("Pesquisar prontuários");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/1473476015_Close_Icon.png"))); // NOI18N
        jMenuItem2.setText("Sair");
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
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        sctp2.Principal.principal acesso = new sctp2.Principal.principal();
        acesso.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DadosDoPaciente acesso;
        try {
            acesso = new DadosDoPaciente(rg);
            acesso.setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FinalizarTratamento.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jFinalizaTratamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFinalizaTratamentoActionPerformed
        try {
            FinalizaTratamento();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FinalizarTratamento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FinalizarTratamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jFinalizaTratamentoActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
this.setVisible(false);        // TODO add your handling code here:
new sctp2.Paciente.PesquisarPaciente().setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
this.setVisible(false);        // TODO add your handling code here:
new sctp2.Prontuarios.Prontuario().setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

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
            java.util.logging.Logger.getLogger(FinalizarTratamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FinalizarTratamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FinalizarTratamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FinalizarTratamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FinalizarTratamento(rg).setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FinalizarTratamento.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(FinalizarTratamento.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jFinalizaTratamento;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JTextField jNotificacao;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private void MostrarTratamentos(String rg) throws ClassNotFoundException, SQLException {
        sctp2.BancodeDados.conexao conexao = new sctp2.BancodeDados.conexao();
        ArrayList<TratamentosNecessarios> ListarTratamentos = new ArrayList<>();
        ListarTratamentos = conexao.NecessidadesdoPaciente(rg);//função que retorna quais necessidades do paciente
        if(ListarTratamentos.isEmpty())jNotificacao.setText("Não há tratamentos sendo feitos no momento!");
        int[] tratamento = new int[27];
        tratamento[0] = ListarTratamentos.get(0).getProfilaxiaSimples();
        tratamento[1] = ListarTratamentos.get(0).getRaspagemEPoliCCoronario();
        tratamento[2] = ListarTratamentos.get(0).getCirugiaPeridontal();
        tratamento[3] = ListarTratamentos.get(0).getExodontiaSimples();
        tratamento[4] = ListarTratamentos.get(0).getExodontia3Molar();
        tratamento[5] = ListarTratamentos.get(0).getExodontiaIncluso();
        tratamento[6] = ListarTratamentos.get(0).getAmalgama();
        tratamento[7] = ListarTratamentos.get(0).getResina();
        tratamento[8] = ListarTratamentos.get(0).getRmf();
        tratamento[9] = ListarTratamentos.get(0).getEndontiaUnirradicular();
        tratamento[10] = ListarTratamentos.get(0).getEndodontiaTrirradicular();
        tratamento[11] = ListarTratamentos.get(0).getCoroaTotal();
        tratamento[12] = ListarTratamentos.get(0).getPonteFixa3Elementos();
        tratamento[13] = ListarTratamentos.get(0).getPonteFixa4Elementos();
        tratamento[14] = ListarTratamentos.get(0).getPonteFixaMaisQue4Elementos();
        tratamento[15] = ListarTratamentos.get(0).getPpr();
        tratamento[16] = ListarTratamentos.get(0).getProtesetotal();
        tratamento[17] = ListarTratamentos.get(0).getProtese_ppr();
        tratamento[18] = ListarTratamentos.get(0).getProtese();
        tratamento[19] = ListarTratamentos.get(0).getTerrapiaOeriodDeSuporte();
        tratamento[20] = ListarTratamentos.get(0).getEndodontiaBirradicular();
        tratamento[21] = ListarTratamentos.get(0).getDtm();
        tratamento[22] = ListarTratamentos.get(0).getEstomatologia();
        tratamento[23] = ListarTratamentos.get(0).getPonteFixa();
        tratamento[24] = ListarTratamentos.get(0).getPonteFixaMaisQueTresElementos();
        tratamento[25] = ListarTratamentos.get(0).getRaspagemSub();
        tratamento[26] = ListarTratamentos.get(0).getRaspagemSupra();
        DefaultTableModel valor = (DefaultTableModel) jTable1.getModel();//criando a chave valor para o objeto tabela
        //obs: AO ADICIONAR NOVOS TRATAMENTOS CERTIFIQUE-SE DE NÃO DEIXAR ESPAÇOS SOBRANDO
        if (tratamento[0] == 1) {
            valor.addRow((new String[]{"Profilaxia Simples"}));
        }
        if (tratamento[1] == 1) {
            valor.addRow((new String[]{"Raspagem e Poli. Coronário"}));
        }
        if (tratamento[2] == 1) {
            valor.addRow((new String[]{"Cirugia Peridontal"}));
        }
        if (tratamento[3] == 1) {
            valor.addRow((new String[]{"Exodontia Simples"}));
        }
        if (tratamento[4] == 1) {
            valor.addRow((new String[]{"Exodontia 3º Molar"}));
        }
        if (tratamento[5] == 1) {
            valor.addRow((new String[]{"Exodontia Incluso"}));
        }
        if (tratamento[6] == 1) {
            valor.addRow((new String[]{"Amalgama"}));
        }
        if (tratamento[7] == 1) {
            valor.addRow((new String[]{"Resina"}));
        }
        if (tratamento[8] == 1) {
            valor.addRow((new String[]{"RMF"}));
        }
        if (tratamento[9] == 1) {
            valor.addRow((new String[]{"EndontiaUnirradicular"}));
        }
        if (tratamento[10] == 1) {
            valor.addRow((new String[]{"Endodontia Trirradicular"}));
        }
        if (tratamento[11] == 1) {
            valor.addRow((new String[]{"Coroa Total"}));
        }
        if (tratamento[12] == 1) {
            valor.addRow((new String[]{"PonteFixa 3 Elementos"}));
        }
        if (tratamento[13] == 1) {
            valor.addRow((new String[]{"PonteFixa 4 Elementos"}));
        }
        if (tratamento[14] == 1) {
            valor.addRow((new String[]{"PonteFixaMaisQue 4 Elementos"}));
        }
        if (tratamento[15] == 1) {
            valor.addRow((new String[]{"PPR"}));
        }
        if (tratamento[16] == 1) {
            valor.addRow((new String[]{"Protese total"}));
        }
        if (tratamento[17] == 1) {
            valor.addRow((new String[]{"Protese PPR"}));
        }
        if (tratamento[18] == 1) {
            valor.addRow((new String[]{"Protese"}));
        }
        if (tratamento[19] == 1) {
            valor.addRow((new String[]{"Terapia Periodontal"}));
        }
        if (tratamento[20] == 1) {
            valor.addRow((new String[]{"Endodontia Birradicular"}));
        }
        if (tratamento[21] == 1) {
            valor.addRow((new String[]{"DTM"}));
        }
        if (tratamento[22] == 1) {
            valor.addRow((new String[]{"Estomatologia"}));
        }
        if (tratamento[23] == 1) {
            valor.addRow((new String[]{"Ponte Fixa"}));
        }
        if (tratamento[24] == 1) {
            valor.addRow((new String[]{"Ponte Fixa Mais Que Três Elementos"}));
        }
        if (tratamento[25] == 1) {
            valor.addRow((new String[]{"Raspagem Sub"}));
        }
        if (tratamento[26] == 1) {
            valor.addRow((new String[]{"Raspagem Supra"}));
        }
        if(jTable1.getRowCount()==0)jFinalizaTratamento.setEnabled(false);

    }

    private void FinalizaTratamento() throws ClassNotFoundException, SQLException {
        conexao acesso= new conexao();
        boolean retorno;
        int numeroLinhas = jTable1.getRowCount();
        ArrayList<String> tratamentosList = new ArrayList<>();
        ArrayList<String> tratamentosConvertidos = new ArrayList<>();
        for (int i = 0; i < numeroLinhas; i++) {
            final Object valor = jTable1.getValueAt(i, 1);
            if (valor == null) {
                System.out.println("");
            } else if (valor.toString().isEmpty()) {
                System.out.println(" ");
            } else 
            {
                tratamentosList.add(jTable1.getValueAt(i, 0).toString());
            }
        }
        tratamentosConvertidos=converteValores(tratamentosList);
        retorno=acesso.FinalizaTratamento(tratamentosConvertidos, rg);
        if(retorno==false){
            retorno=acesso.VerificaAlta(rg);
            jNotificacao.setText("Tratamento(s) finalizados com sucesso!");
            AtualizaTabela();
        }
        
        

    }

    private ArrayList<String> converteValores(ArrayList tratamentos) {
        //-----------------------------------------------------------
      ArrayList<String> tratamentosConvertidos= new ArrayList<>() ;
      for(int i=0;i<tratamentos.size();i++){
          if(tratamentos.get(i).toString().trim().equals("Profilaxia Simples"))tratamentosConvertidos.add("nec_ProfilaxiaSimples");
          else
                if(tratamentos.get(i).toString().trim().equals("Raspagem e Poli. Coronário"))tratamentosConvertidos.add("nec_RaspagemPolimentoCoronario");
          else
                    if(tratamentos.get(i).toString().trim().equals("Cirugia Peridontal"))tratamentosConvertidos.add("nec_CirurgiaPeriodontal");
            else
                        if(tratamentos.get(i).toString().trim().equals("Exodontia Simples"))tratamentosConvertidos.add("nec_ExodontiaSimples");
            else
                            if(tratamentos.get(i).toString().trim().equals("Exodontia 3º Molar"))tratamentosConvertidos.add("nec_ExodontiaMolar");
            else
                                if(tratamentos.get(i).toString().trim().equals("Exodontia Incluso"))tratamentosConvertidos.add("nec_ExodontiaIncluso");
            else
                                    if(tratamentos.get(i).toString().trim().equals("Amalgama"))tratamentosConvertidos.add("nec_Amalgama");
            else
                                        if(tratamentos.get(i).toString().trim().equals("Resina"))tratamentosConvertidos.add("nec_Resina");
            else
                                            if(tratamentos.get(i).toString().trim().equals("RMF"))tratamentosConvertidos.add("nec_RMF");
            else
                                                if(tratamentos.get(i).toString().trim().equals("EndontiaUnirradicular"))tratamentosConvertidos.add("nec_Endodontiauniebirradicular");
            else
                                                    if(tratamentos.get(i).toString().trim().equals("Endodontia Trirradicular"))tratamentosConvertidos.add("nec_EndodontiaTrirradicular");
            else
                                                        if(tratamentos.get(i).toString().trim().equals("Coroa Total"))tratamentosConvertidos.add("nec_CoroaTotal");
            else
                                                            if(tratamentos.get(i).toString().trim().equals("PonteFixa 3 Elementos"))tratamentosConvertidos.add("nec_PonteFixa3Elementos");
            else
                                                                if(tratamentos.get(i).toString().trim().equals("PonteFixa 4 Elementos"))tratamentosConvertidos.add("nec_Pontefixa4elementos");
            else
                                                                    if(tratamentos.get(i).toString().trim().equals("PonteFixaMaisQue 4 Elementos"))tratamentosConvertidos.add("nec_Pontefixamaisque4elementos");
            else
                                                                        if(tratamentos.get(i).toString().trim().equals("PPR"))tratamentosConvertidos.add("nec_PPR");
            else
                                                                            if(tratamentos.get(i).toString().trim().equals("Protese total"))tratamentosConvertidos.add("nec_ProteseTotalPar");
            else
                                                                                if(tratamentos.get(i).toString().trim().equals("Protese PPR"))tratamentosConvertidos.add("nec_ProtesePPR");
            else
                                                                                    if(tratamentos.get(i).toString().trim().equals("Protese"))tratamentosConvertidos.add("nec_Protese");
            else
                                                                                        if(tratamentos.get(i).toString().trim().equals("Terapia Periodontal"))tratamentosConvertidos.add("nec_TerapiaPeriodontal");
            else
                                                                                            if(tratamentos.get(i).toString().trim().equals("Endodontia Birradicular"))tratamentosConvertidos.add("nec_EndodontiaBirradicular");
            else
                                                                                                if(tratamentos.get(i).toString().trim().equals("DTM"))tratamentosConvertidos.add("nec_DTM");
            else
                                                                                                    if(tratamentos.get(i).toString().trim().equals("Estomatologia"))tratamentosConvertidos.add("nec_Estomatologia");
            else
                                                                                                    if(tratamentos.get(i).toString().trim().equals("Ponte Fixa"))tratamentosConvertidos.add("nec_PonteFixa");
            else
                                                                                                    if(tratamentos.get(i).toString().trim().equals("Ponte Fixa Mais Que Três Elementos"))tratamentosConvertidos.add("nec_PonteFixaMaisQueTresElementos");
            else
                                                                                                    if(tratamentos.get(i).toString().trim().equals("Raspagem Sub"))tratamentosConvertidos.add("nec_RaspagemSub");
            else
                                                                                                    if(tratamentos.get(i).toString().trim().equals("Raspagem Supra"))tratamentosConvertidos.add("nec_RaspagemSupra");
          
          
      }
          
      
      return tratamentosConvertidos;
    }

    private void LimpaTabela() {
        //limpa pesquisas existentes
        jTable1.setSelectionBackground(Color.BLUE);//defini a cor de seleção da tabela para azul
        DefaultTableModel tabela;
        tabela = (DefaultTableModel) jTable1.getModel();
        tabela.setNumRows(0);
        //********************************************
    }

    private void AtualizaTabela() throws ClassNotFoundException, SQLException {
    LimpaTabela();
    MostrarTratamentos(rg);
    }

}
