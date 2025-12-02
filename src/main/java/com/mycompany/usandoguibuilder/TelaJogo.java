package com.mycompany.usandoguibuilder;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class TelaJogo extends javax.swing.JFrame {

    // Instância da lógica (O Cérebro do jogo)
    private RegrasJogo regras;

    // Construtor
    public TelaJogo() {
        initComponents(); // Inicia o desenho visual

        // Configurações da Janela
        this.setTitle("Carcará vs Cabrito");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        configurarMenu();

        // Inicia o Jogo
        regras = new RegrasJogo();

        // Desenha o estado inicial
        atualizarTela();

        this.setVisible(true);
    }

    private void configurarMenu() {
        JMenuBar barraMenu = new JMenuBar();

        // Menu Jogo
        JMenu menuJogo = new JMenu("Jogo");
        JMenuItem itemReiniciar = new JMenuItem("Reiniciar");
        JMenuItem itemSair = new JMenuItem("Sair");

        itemReiniciar.addActionListener(e -> {
            regras.reiniciarJogo();
            atualizarTela();
        });
        itemSair.addActionListener(e -> System.exit(0));

        menuJogo.add(itemReiniciar);
        menuJogo.add(itemSair);

        // Menu Autoria
        JMenu menuAutoria = new JMenu("Autoria");
        JMenuItem itemNomes = new JMenuItem("Ver Nomes");
        itemNomes.addActionListener(e -> JOptionPane.showMessageDialog(this, "Autor: Matheus Cabral"));
        menuAutoria.add(itemNomes);

        barraMenu.add(menuJogo);
        barraMenu.add(menuAutoria);
        this.setJMenuBar(barraMenu);
    }
    // -----------------------------------

    // Lógica do jogo
    private void realizarJogada(RegrasJogo.Posicao posicaoClicada) {
        try {
            regras.processarJogada(posicaoClicada);
            atualizarTela();
            if (regras.isJogoFinalizado()) {
                JOptionPane.showMessageDialog(this, regras.getMensagemVitoria());
            }
        } catch (MovimentoInvalidoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void atualizarTela() {
        // Limpa painéis
        limparPainel(PosicaoSuperiorEsquerda);
        limparPainel(PosicaoSuperiorDireita);
        limparPainel(PosicaoSuperiorCentral);
        limparPainel(PosicaoCentral);
        limparPainel(PosicaoInferiorEsquerda);
        limparPainel(PosicaoInferiorDireita);

        // Adiciona peças
        adicionarPeca(regras.getPosicaoCabrito(), new Cabrito());
        adicionarPeca(regras.getPosicaoCarcara(), new Carcara());

        // Atualiza título
        String turno = regras.isTurnoDoCabrito() ? "Vez do CABRITO" : "Vez do CARCARÁ";
        this.setTitle("Carcará vs Cabrito - " + turno + " (Jogadas: " + regras.getContadorJogadas() + ")");

        PainelJogo.revalidate();
        PainelJogo.repaint();
    }

    private void adicionarPeca(RegrasJogo.Posicao pos, JPanel peca) {
        JPanel painel = getPainel(pos);
        if (painel != null) {
            painel.add(peca);
        }
    }

    private void limparPainel(JPanel p) {
        if (p != null) {
            p.removeAll();
            p.revalidate();
            p.repaint();
        }
    }

    private JPanel getPainel(RegrasJogo.Posicao pos) {
        switch (pos) {
            case SUPERIOR_ESQUERDA: return PosicaoSuperiorEsquerda;
            case SUPERIOR_DIREITA: return PosicaoSuperiorDireita;
            case SUPERIOR_CENTRAL: return PosicaoSuperiorCentral;
            case CENTRAL: return PosicaoCentral;
            case INFERIOR_ESQUERDA: return PosicaoInferiorEsquerda;
            case INFERIOR_DIREITA: return PosicaoInferiorDireita;
            default: return null;
        }
    }

    // --- EVENTOS DE CLIQUE ---
    private void PosicaoCentralMouseClicked(java.awt.event.MouseEvent evt) {
        realizarJogada(RegrasJogo.Posicao.CENTRAL);
    }
    private void PosicaoInferiorDireitaMouseClicked(java.awt.event.MouseEvent evt) {
        realizarJogada(RegrasJogo.Posicao.INFERIOR_DIREITA);
    }
    private void PosicaoSuperiorCentralMouseClicked(java.awt.event.MouseEvent evt) {
        realizarJogada(RegrasJogo.Posicao.SUPERIOR_CENTRAL);
    }
    private void PosicaoInferiorEsquerdaMouseClicked(java.awt.event.MouseEvent evt) {
        realizarJogada(RegrasJogo.Posicao.INFERIOR_ESQUERDA);
    }
    private void PosicaoSuperiorDireitaMouseClicked(java.awt.event.MouseEvent evt) {
        realizarJogada(RegrasJogo.Posicao.SUPERIOR_DIREITA);
    }
    private void PosicaoSuperiorEsquerdaMouseClicked(java.awt.event.MouseEvent evt) {
        realizarJogada(RegrasJogo.Posicao.SUPERIOR_ESQUERDA);
    }
    private void PosicaoSuperiorEsquerdaAncestorAdded(javax.swing.event.AncestorEvent evt) {}

    // --- CÓDIGO GERADO AUTOMATICAMENTE (Não Mexer) ---
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

        // Configuração dos eventos
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

        // Layout
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

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new TelaJogo().setVisible(true));
    }

    private javax.swing.JPanel PainelJogo;
    private com.mycompany.usandoguibuilder.PanelPaint PosicaoCentral;
    private com.mycompany.usandoguibuilder.PanelPaint PosicaoInferiorDireita;
    private com.mycompany.usandoguibuilder.PanelPaint PosicaoInferiorEsquerda;
    private com.mycompany.usandoguibuilder.PanelPaint PosicaoSuperiorCentral;
    private com.mycompany.usandoguibuilder.PanelPaint PosicaoSuperiorDireita;
    private com.mycompany.usandoguibuilder.PanelPaint PosicaoSuperiorEsquerda;
}