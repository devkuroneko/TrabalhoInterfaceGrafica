/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.usandoguibuilder;


import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.io.File;



/**
 *
 * @author Matheus Cabral
 * @author Marcus Vinicius da Luz Araújo
 *  *
 */
public class Cabrito extends JPanel {

    public Cabrito() {
        this.setPreferredSize(new Dimension(40, 40));
        this.setOpaque(false);

       String nomeImagem = "bode.png";
       String [] possiveisCaminhos = {
               "src/main/resources/Img/" + nomeImagem,
               "src/main/java/com/mycompany/usandoguibuilder/" + nomeImagem,
               "src/Img/" + nomeImagem,
               "img/" + nomeImagem,
               "TrabalhoInterfaceGrafica-master/src/main/resources/Img/" + nomeImagem
       };

       ImageIcon icone = null;
       String caminhoFinal = "";

       //
    String diretorioDoProjeto = System.getProperty("user.dir");
    System.out.println ("Imagem");
    System.out.println ("jAVA ESTÁ RODANDO EM = " + diretorioDoProjeto);

    for (String caminhoRelativo : possiveisCaminhos){
        File arquivo = new File (diretorioDoProjeto, caminhoRelativo);

        if (!arquivo.exists()){
            arquivo = new File (caminhoRelativo);
        }
        if (arquivo.exists()){
            System.out.println ("Imagem encontrada em:" + arquivo.getAbsolutePath());
            icone = new ImageIcon (arquivo.getAbsolutePath());
            caminhoFinal = arquivo.getAbsolutePath();
            break;
        } else {
            System.out.println("Falha, não está em:" + arquivo.getAbsolutePath());
        }
    }
    //Começo gerado por IA (CORREÇÃO0
        if (icone != null) {
            this.add(new JLabel(icone));
        } else {
            // Se falhar tudo, desenha texto vermelho
            JLabel erro = new JLabel("BODE?");
            erro.setForeground(java.awt.Color.RED);
            this.add(erro);
            System.err.println("--- ERRO CRÍTICO: NENHUMA IMAGEM ENCONTRADA ---");
        }
    }
}
//FIM GERADO POR IA







