/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.usandoguibuilder;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

/**
 *
 * @author Matheus Cabral
 */
public class TelaJogo extends javax.swing.JFrame {
    private RegrasDoJogo regras;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaJogo.class.getName());

    
    /**
     * Creates new form TelaJogo
     */
    public TelaJogo() {
        initComponents();
        PainelJogo.setBackground(new Color(100,100,100));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("JOGO - carcará vs cabrito");

        regras = new RegrasDoJogo();
        atualizarTela();
        
        this.add(PainelJogo);
        this.setVisible(true);
    }

    /**
     * //começo de código gerado por IA
     *
     * Método Refatorado: Em vez de procurar na tela, perguntamos às Regras.
     * Retorna o JPanel onde o Cabrito está atualmente.
     *
     * //Fim de código gerado por IA
     */
    public JPanel posicaoAtualCabritoJogo() {

        //Pergunta onde o cabrito est´na lógica
        RegrasDoJogo.Posicao posicaoLogica = regras.getPosicaoCabrito();

        //Converte a posição logica no painel
        return getPainel(posicaoLogica);
    }

// NEW - Controlador - recebe o click, processa na regra e atualiza
    private void realizarJogada(RegrasDoJogo.Posicao posicaoClicada){
        try{
            regras.processarJogada(posicaoClicada);
            atualizarTela();

            if (regras.isjogoFinalizado()){
                JOptionPane.showMessageDialog(this, regras.getMensagemVitoria());
            }
        } catch (MovimentoInvalidoException e){
            JOptionPane.showMessageDialog(this,e.getMessage(), "Jogada inválida",JOptionPane.WARNING_MESSAGE);

        }
    }
    
    // NEW - Limpa o tabuleiro e coloca as peças onde a regra diz que estão
    private void atualizarTela(){
        limparPainel(PosicaoSuperiorEsquerda);
        limparPainel(PosicaoSuperiorDireita);
        limparPainel(PosicaoSuperiorCentral);
        limparPainel(PosicaoCentral);
        limparPainel(PosicaoInferiorEsquerda);
        limparPainel(PosicaoInferiorDireita);

        //adicionar as peças em novas posições
        adicionarPeca(regras.getPosicaoCabrito(), new Cabrito());
        adicionarPeca(regras.getPosicaoCarcara(), new Carcara());

        //atualizar a tela do usuário
        PainelJogo.revalidate();
        PainelJogo.repaint();
    }

    // NEW - Limpar um painel específico
    private void limparPainel(JPanel p){
        if (p != null){
            p.removeAll();
            p.revalidate();
            p.repaint();
        }

    }

    //New - Auxiliar para colocar a peça no painel
    private void adicionarPeca(RegrasDoJogo.Posicao pos, JPanel peca){
        JPanel painelDestino = getPainel(pos);
        if (painelDestino != null){
            painelDestino.add(peca);
        }
    }

    //New - Converte o enum da regra para o componente da tela
    private JPanel getPainel(RegrasDoJogo.Posicao pos){
        switch (pos){
            case SUPERIOR_ESQUERDA: return PosicaoSuperiorEsquerda;
            case SUPERIOR_DIREITA: return PosicaoSuperiorDireita;
            case SUPERIOR_CENTRAL: return PosicaoSuperiorCentral;
            case CENTRAL: return PosicaoCentral;
            case INFERIOR_ESQUERDA: return PosicaoInferiorEsquerda;
            case INFERIOR_DIREITA: return PosicaoInferiorDireita;
            default : return null;
        }

    }

   // click no botão CENTRAL
    private void PosicaoCentralMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PosicaoCentralMouseClicked
        realizarJogada(RegrasDoJogo.Posicao.CENTRAL);
    }

    //click no botão inferior DIREITA
    private void PosicaoInferiorDireitaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PosicaoInferiorDireitaMouseClicked
        realizarJogada(RegrasDoJogo.Posicao.INFERIOR_DIREITA);
    }

    private void PosicaoSuperiorCentralMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PosicaoSuperiorCentralMouseClicked
        realizarJogada(RegrasDoJogo.Posicao.SUPERIOR_CENTRAL);
    }

    private void PosicaoInferiorEsquerdaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PosicaoInferiorEsquerdaMouseClicked
        realizarJogada(RegrasDoJogo.Posicao.INFERIOR_ESQUERDA);
    }

    private void PosicaoSuperiorDireitaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PosicaoSuperiorDireitaMouseClicked
        realizarJogada(RegrasDoJogo.Posicao.SUPERIOR_DIREITA);
    }

    private void PosicaoSuperiorEsquerdaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PosicaoSuperiorEsquerdaMouseClicked
        realizarJogada(RegrasDoJogo.Posicao.SUPERIOR_ESQUERDA);
    }


    // CORREÇÃO 4: O Método initComponents restaurado (O NetBeans precisa disto!)
    //começo de código gerado por IA ()
    @SuppressWarnings("unchecked")
    private void initComponents() {

        PainelJogo = new javax.swing.JPanel();
        PosicaoSuperiorEsquerda = new com.mycompany.usandoguibuilder.PanelPaint();
        PosicaoSuperiorDireita = new com.mycompany.usandoguibuilder.PanelPaint();
        PosicaoCentral = new com.mycompany.usandoguibuilder.PanelPaint();
        PosicaoSuperiorCentral = new com.mycompany.usandoguibuilder.PanelPaint();
        PosicaoInferiorDireita = new com.mycompany.usandoguibuilder.PanelPaint();
        PosicaoInferiorEsquerda = new com.mycompany.usandoguibuilder.PanelPaint();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));

        PainelJogo.setBackground(new java.awt.Color(100, 100, 100));
        PainelJogo.setPreferredSize(new java.awt.Dimension(800, 600));

        PosicaoSuperiorEsquerda.setName("posicao superior esquerda");
        PosicaoSuperiorEsquerda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PosicaoSuperiorEsquerdaMouseClicked(evt);
            }
        });

        PosicaoSuperiorDireita.setName("posicao superior direita");
        PosicaoSuperiorDireita.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PosicaoSuperiorDireitaMouseClicked(evt);
            }
        });

        PosicaoCentral.setName("centro do jogo");
        PosicaoCentral.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PosicaoCentralMouseClicked(evt);
            }
        });

        PosicaoSuperiorCentral.setName("posicao superior central");
        PosicaoSuperiorCentral.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PosicaoSuperiorCentralMouseClicked(evt);
            }
        });

        PosicaoInferiorDireita.setName("posicao inferior direita");
        PosicaoInferiorDireita.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PosicaoInferiorDireitaMouseClicked(evt);
            }
        });
        PosicaoInferiorDireita.setLayout(new java.awt.GridLayout(1, 0));

        PosicaoInferiorEsquerda.setName("posicao inferior esquerda");
        PosicaoInferiorEsquerda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PosicaoInferiorEsquerdaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PainelJogoLayout = new javax.swing.GroupLayout(PainelJogo);
        PainelJogo.setLayout(PainelJogoLayout);
        PainelJogoLayout.setHorizontalGroup(
                PainelJogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PainelJogoLayout.createSequentialGroup()
                                .addGroup(PainelJogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(PainelJogoLayout.createSequentialGroup()
                                                .addGap(120, 120, 120)
                                                .addComponent(PosicaoSuperiorEsquerda, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(324, 324, 324)
                                                .addComponent(PosicaoSuperiorDireita, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(PainelJogoLayout.createSequentialGroup()
                                                .addGroup(PainelJogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(PainelJogoLayout.createSequentialGroup()
                                                                .addGap(320, 320, 320)
                                                                .addGroup(PainelJogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(PosicaoSuperiorCentral, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(PosicaoCentral, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(52, 52, 52))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelJogoLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(PosicaoInferiorEsquerda, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(178, 178, 178)))
                                                .addComponent(PosicaoInferiorDireita, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(196, Short.MAX_VALUE))
        );
        PainelJogoLayout.setVerticalGroup(
                PainelJogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PainelJogoLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(PosicaoSuperiorCentral, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(142, 142, 142)
                                .addComponent(PosicaoCentral, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(PainelJogoLayout.createSequentialGroup()
                                .addGap(161, 161, 161)
                                .addGroup(PainelJogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(PosicaoSuperiorDireita, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                                        .addComponent(PosicaoSuperiorEsquerda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                                .addGroup(PainelJogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(PosicaoInferiorEsquerda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(PosicaoInferiorDireita, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(107, 107, 107))
        );

        this.add(PainelJogo);
        pack();
    }
// fim de código gerado por IA ()

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new TelaJogo().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PainelJogo;
    private com.mycompany.usandoguibuilder.PanelPaint PosicaoCentral;
    private com.mycompany.usandoguibuilder.PanelPaint PosicaoInferiorDireita;
    private com.mycompany.usandoguibuilder.PanelPaint PosicaoInferiorEsquerda;
    private com.mycompany.usandoguibuilder.PanelPaint PosicaoSuperiorCentral;
    private com.mycompany.usandoguibuilder.PanelPaint PosicaoSuperiorDireita;
    private com.mycompany.usandoguibuilder.PanelPaint PosicaoSuperiorEsquerda;
    // End of variables declaration//GEN-END:variables
}
