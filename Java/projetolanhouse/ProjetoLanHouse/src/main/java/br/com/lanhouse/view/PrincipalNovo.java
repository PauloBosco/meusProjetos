/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.lanhouse.view;

import br.com.lanhouse.model.pessoas.Admin;
import br.com.lanhouse.model.pessoas.Cliente;
import br.com.lanhouse.model.pessoas.Usuario;

/**
 *
 * @author paulo
 */
public class PrincipalNovo extends javax.swing.JFrame {

    /**
     * Creates new form PrincipalNovo
     */
    public PrincipalNovo() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jNomeUser = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuCadastraUsuario = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuDeletarUsuario = new javax.swing.JMenuItem();
        jMenuColocarTempo = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuBuscaCliente = new javax.swing.JMenuItem();
        jMenuBuscaTodosOsClientes = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuCliente = new javax.swing.JMenu();
        jMenuSolicitarProduto = new javax.swing.JMenuItem();
        jMenuMeuRelatório = new javax.swing.JMenuItem();
        jMenuImprimir = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuSobre = new javax.swing.JMenuItem();
        jMenuSuporte = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CyberCafé.IO");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Bem vindo ");

        jLabel2.setText("Escolha uma das opções no menu acima");

        jNomeUser.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jNomeUser.setText("<NomeUsuario>");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/large_cybercafe.io_0.png"))); // NOI18N

        jMenu1.setText("Arquivo");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem2.setText("Sair");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenuCadastraUsuario.setText("Admin");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem3.setText("Cadastrar Usuario");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenuCadastraUsuario.add(jMenuItem3);

        jMenuDeletarUsuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuDeletarUsuario.setText("Deletar Usuario");
        jMenuDeletarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuDeletarUsuarioActionPerformed(evt);
            }
        });
        jMenuCadastraUsuario.add(jMenuDeletarUsuario);

        jMenuColocarTempo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuColocarTempo.setText("Colocar Tempo");
        jMenuColocarTempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuColocarTempoActionPerformed(evt);
            }
        });
        jMenuCadastraUsuario.add(jMenuColocarTempo);

        jMenu4.setText("Relatório");

        jMenuBuscaCliente.setText("Buscar Cliente");
        jMenuBuscaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuBuscaClienteActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuBuscaCliente);

        jMenuBuscaTodosOsClientes.setText("Buscar Todos os Clientes");
        jMenuBuscaTodosOsClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuBuscaTodosOsClientesActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuBuscaTodosOsClientes);

        jMenuCadastraUsuario.add(jMenu4);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem4.setText("Criar Uso");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenuCadastraUsuario.add(jMenuItem4);

        jMenuBar1.add(jMenuCadastraUsuario);

        jMenuCliente.setText("Cliente");

        jMenuSolicitarProduto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuSolicitarProduto.setText("Solicitar Produto");
        jMenuSolicitarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuSolicitarProdutoActionPerformed(evt);
            }
        });
        jMenuCliente.add(jMenuSolicitarProduto);

        jMenuMeuRelatório.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuMeuRelatório.setText("Meu Relatório");
        jMenuMeuRelatório.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuMeuRelatórioActionPerformed(evt);
            }
        });
        jMenuCliente.add(jMenuMeuRelatório);

        jMenuImprimir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuImprimir.setText("Imprimir");
        jMenuImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuImprimirActionPerformed(evt);
            }
        });
        jMenuCliente.add(jMenuImprimir);

        jMenuBar1.add(jMenuCliente);

        jMenu8.setText("Ajuda");

        jMenuSobre.setText("Sobre");
        jMenuSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuSobreActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuSobre);

        jMenuSuporte.setText("Suporte");
        jMenuSuporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuSuporteActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuSuporte);

        jMenuBar1.add(jMenu8);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jNomeUser)
                .addGap(122, 122, 122))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel2)))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jNomeUser))
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addContainerGap(129, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuImprimirActionPerformed
        ViewImprimir imprimir = new ViewImprimir(this, true);
        imprimir.setUser(user);
        imprimir.setVisible(true);
        
        
        
        
        
    }//GEN-LAST:event_jMenuImprimirActionPerformed

    private void jMenuSolicitarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuSolicitarProdutoActionPerformed
        ViewSolicitarProduto solicita = new ViewSolicitarProduto(this, true);
       
        solicita.setUser(user);
        
        solicita.setVisible(true);
       
    }//GEN-LAST:event_jMenuSolicitarProdutoActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        LoginNovo login = new LoginNovo(this, true);
        login.setVisible(true);
        
        user = login.getUser();
        if(login.getUser() instanceof Cliente){
            jMenuCadastraUsuario.setVisible(false);
        }else if(login.getUser() instanceof Admin){
            jMenuCliente.setVisible(false);
        }
            
        
        jNomeUser.setText(login.getUser().getNome());
        
        
        
    }//GEN-LAST:event_formWindowOpened
       
        
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuSuporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuSuporteActionPerformed
        ViewSuporte sup = new ViewSuporte(this, true);
        sup.setVisible(true);
    }//GEN-LAST:event_jMenuSuporteActionPerformed

    private void jMenuMeuRelatórioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuMeuRelatórioActionPerformed
        ViewClienteMeuRelatorio meuRelatorio = new ViewClienteMeuRelatorio(this, true);
        meuRelatorio.setUser(user);
        meuRelatorio.setVisible(true);
        
        
    }//GEN-LAST:event_jMenuMeuRelatórioActionPerformed

    private void jMenuSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuSobreActionPerformed
        ViewSobre sobre = new ViewSobre(this, true);
        sobre.setVisible(true);
    }//GEN-LAST:event_jMenuSobreActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        ViewCriarUso criarUso = new ViewCriarUso(this, true);
        criarUso.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuColocarTempoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuColocarTempoActionPerformed

        ViewColocarTempo colocaTempo = new ViewColocarTempo(this,true);
        colocaTempo.setVisible(true);

    }//GEN-LAST:event_jMenuColocarTempoActionPerformed

    private void jMenuDeletarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuDeletarUsuarioActionPerformed
        ViewDeletarUsuario delete = new ViewDeletarUsuario(this, true);

        delete.setUser(user);

        delete.setVisible(true);

    }//GEN-LAST:event_jMenuDeletarUsuarioActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed

        new ViewCadastrarUsuario(this,true).setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuBuscaTodosOsClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuBuscaTodosOsClientesActionPerformed
       ViewRelatorioBuscaTodosClientes buscaTodosClientes = new ViewRelatorioBuscaTodosClientes(this, true);
       buscaTodosClientes.setVisible(true);
    }//GEN-LAST:event_jMenuBuscaTodosOsClientesActionPerformed

    private void jMenuBuscaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuBuscaClienteActionPerformed
        ViewRelatorioBuscaCliente buscaCliente = new ViewRelatorioBuscaCliente(this, true);
        buscaCliente.setVisible(true);
    }//GEN-LAST:event_jMenuBuscaClienteActionPerformed

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
            java.util.logging.Logger.getLogger(PrincipalNovo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalNovo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalNovo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalNovo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalNovo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuBuscaCliente;
    private javax.swing.JMenuItem jMenuBuscaTodosOsClientes;
    private javax.swing.JMenu jMenuCadastraUsuario;
    private javax.swing.JMenu jMenuCliente;
    private javax.swing.JMenuItem jMenuColocarTempo;
    private javax.swing.JMenuItem jMenuDeletarUsuario;
    private javax.swing.JMenuItem jMenuImprimir;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuMeuRelatório;
    private javax.swing.JMenuItem jMenuSobre;
    private javax.swing.JMenuItem jMenuSolicitarProduto;
    private javax.swing.JMenuItem jMenuSuporte;
    public javax.swing.JLabel jNomeUser;
    // End of variables declaration//GEN-END:variables

    private Usuario user;

    public Usuario getUser(){
        return user;
    }
}
