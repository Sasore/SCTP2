/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sctp2.PacotedeLogin;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sctp2.BancodeDados.conexao;

public class Logar extends javax.swing.JFrame {

    public Logar() {
        initComponents();
        this.setLocation(465, 80);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jNome = new javax.swing.JTextField();
        jSenha = new javax.swing.JPasswordField();
        jBEntrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SCTP");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel1KeyPressed(evt);
            }
        });

        jNome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jNome.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jNome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jNomeMouseClicked(evt);
            }
        });

        jSenha.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jSenha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSenhaMouseClicked(evt);
            }
        });
        jSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSenhaActionPerformed(evt);
            }
        });
        jSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jSenhaKeyPressed(evt);
            }
        });

        jBEntrar.setBackground(new java.awt.Color(51, 102, 255));
        jBEntrar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBEntrar.setForeground(new java.awt.Color(255, 255, 255));
        jBEntrar.setText("Entrar");
        jBEntrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEntrarActionPerformed(evt);
            }
        });
        jBEntrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBEntrarKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Senha");

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Usuário");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/dente.jpg"))); // NOI18N

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 102, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("SCTP");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jNome)
                    .addComponent(jSenha)
                    .addComponent(jBEntrar, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(124, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jNome, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyPressed

    }//GEN-LAST:event_jPanel1KeyPressed

    private void jBEntrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBEntrarKeyPressed
        boolean retornoCampoVazio;
        retornoCampoVazio = VerificaCamposVazio();
        if (retornoCampoVazio == false) {
            conexao acesso = new conexao();//chamada a função de login do banco de dados
            Boolean retorno;
            try {
                retorno = acesso.logar(jNome.getText(), jSenha.getText());
                if (retorno == true) {
                    sctp2.Principal.principal abrir = new sctp2.Principal.principal();//instancia a chamada a tela principal se o login estiver correto
                    abrir.setVisible(true);
                    this.setVisible(false);
                } else {
                    if (retorno == false) {
                        JOptionPane.showMessageDialog(rootPane, "O Nome de Usuário ou a senha está errada, tente novamente!");
                    }
                    System.out.println("falha ao logar");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Logar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jBEntrarKeyPressed

    private void jBEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEntrarActionPerformed
        boolean retornoCampoVazio;
        retornoCampoVazio = VerificaCamposVazio();
        if (retornoCampoVazio == false) {
            conexao acesso = new conexao();//chamada a função de login do banco de dados
            Boolean retorno;
            try {
                retorno = acesso.logar(jNome.getText(), jSenha.getText());
                if (retorno == true) {
                    sctp2.Principal.principal abrir = new sctp2.Principal.principal();//instancia a chamada a tela principal se o login estiver correto
                    abrir.setVisible(true);
                    this.setVisible(false);
                } else {
                    if (retorno == false) {
                        JOptionPane.showMessageDialog(rootPane, "O Nome de Usuário ou a senha digitados está errado, tente novamente!");
                    }
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Logar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jBEntrarActionPerformed

    private void jSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSenhaKeyPressed

    }//GEN-LAST:event_jSenhaKeyPressed

    private void jSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSenhaActionPerformed
        boolean retornoCampoVazio;
        retornoCampoVazio = VerificaCamposVazio();
        if (retornoCampoVazio == false) {
            conexao acesso = new conexao();//chamada a função de login do banco de dados
            Boolean retorno;
            try {
                retorno = acesso.logar(jNome.getText(), jSenha.getText());
                if (retorno == true) {
                    sctp2.Principal.principal abrir = new sctp2.Principal.principal();//instancia a chamada a tela principal se o login estiver correto
                    abrir.setVisible(true);
                    this.setVisible(false);
                } else {
                    if (retorno == false) {
                        JOptionPane.showMessageDialog(rootPane, "O Nome de Usuário ou a senha está errada, tente novamente!");
                    }
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Logar.class.getName()).log(Level.SEVERE, null, ex);
            }         // TODO add your handling code here:
        }
    }//GEN-LAST:event_jSenhaActionPerformed

    private void jSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSenhaMouseClicked

    }//GEN-LAST:event_jSenhaMouseClicked

    private void jNomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jNomeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jNomeMouseClicked

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
            java.util.logging.Logger.getLogger(Logar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Logar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Logar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Logar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Logar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBEntrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jNome;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jSenha;
    // End of variables declaration//GEN-END:variables

    private boolean VerificaCamposVazio() {
        if ((jSenha.getText().equals("")) || (jSenha.getText().equals(""))) {
            JOptionPane.showMessageDialog(rootPane, "Por favor, preencha todos os campos!");
            return true;
        }

        return false;//os campos estão preenchidos
    }
}
