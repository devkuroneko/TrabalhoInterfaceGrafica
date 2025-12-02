package com.mycompany.usandoguibuilder;

import java.awt.Dimension;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Cabrito extends JPanel {

    public Cabrito() {
        this.setPreferredSize(new Dimension(40, 40));
        this.setOpaque(false);

        // Nome do arquivo exato (Maiúscula importa!)
        String nomeArquivo = "bode.png";

        // --- ESTRATÉGIA FORÇA BRUTA (Tenta vários caminhos) ---
        String[] caminhosPossiveis = {
                "src/main/resources/Img/" + nomeArquivo,                        // Caminho Padrão Maven
                "TrabalhoInterfaceGrafica/src/main/resources/Img/" + nomeArquivo, // Se a raiz for a pasta POO2
                "resources/Img/" + nomeArquivo,                                   // Caminho direto
                "src/Img/" + nomeArquivo                                          // Caminho antigo
        };

        ImageIcon icone = null;
        String caminhoEncontrado = "";

        // 1. Tenta achar o arquivo no disco
        for (String caminho : caminhosPossiveis) {
            File arquivo = new File(caminho);
            if (arquivo.exists()) {
                icone = new ImageIcon(caminho);
                caminhoEncontrado = arquivo.getAbsolutePath();
                break; // Achou! Para de procurar.
            }
        }

        // 2. Se não achou no disco, tenta a última chance via Resource (Classpath)
        if (icone == null) {
            java.net.URL url = getClass().getResource("/Img/" + nomeArquivo);
            if (url != null) {
                icone = new ImageIcon(url);
                caminhoEncontrado = "Via Resource Interno";
            }
        }

        // --- RESULTADO ---
        if (icone != null) {
            // Sucesso
            this.add(new JLabel(icone));
            System.out.println("[SUCESSO] Bode carregado de: " + caminhoEncontrado);
        } else {
            // Fracasso Total - Mostra onde ele procurou
            JLabel erro = new JLabel("BODE?");
            erro.setForeground(java.awt.Color.RED);
            this.add(erro);

            System.err.println("--- ERRO FATAL: IMAGEM NÃO ENCONTRADA ---");
            System.err.println("Diretório de trabalho atual: " + System.getProperty("user.dir"));
            System.err.println("Tentei procurar nestes locais e falhei:");
            for (String c : caminhosPossiveis) {
                File f = new File(c);
                System.err.println(" - " + f.getAbsolutePath() + " (Existe? " + f.exists() + ")");
            }
        }
    }
}