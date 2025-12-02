package com.mycompany.usandoguibuilder;

import java.awt.Dimension;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Carcara extends JPanel {

    public Carcara() {
        this.setPreferredSize(new Dimension(40, 40));
        this.setOpaque(false);

        String nomeArquivo = "eagle.png";

        // Possíveis locais onde a imagem pode estar
        String[] caminhosPossiveis = {
                "src/main/resources/Img/" + nomeArquivo,
                "TrabalhoInterfaceGrafica/src/main/resources/Img/" + nomeArquivo,
                "resources/Img/" + nomeArquivo
        };

        ImageIcon icone = null;

        for (String caminho : caminhosPossiveis) {
            File arquivo = new File(caminho);
            if (arquivo.exists()) {
                icone = new ImageIcon(caminho);
                break;
            }
        }

        if (icone == null) {
            java.net.URL url = getClass().getResource("/Img/" + nomeArquivo);
            if (url != null) icone = new ImageIcon(url);
        }

        if (icone != null) {
            this.add(new JLabel(icone));
        } else {
            JLabel erro = new JLabel("CARCARÁ?");
            erro.setForeground(java.awt.Color.RED);
            this.add(erro);
        }
    }
}