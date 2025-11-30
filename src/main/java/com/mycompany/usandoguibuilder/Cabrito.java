/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.usandoguibuilder;

import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Matheus Cabral
 */
public class Cabrito extends JPanel{
 
    
    public Cabrito(){
        //funciona somente com o PATH completo desde a raiz até a imagem
        //verificar pq não funciona com getClass e getResource
        ImageIcon iconeCabrito = new ImageIcon("/home/gatopreto/NetBeansProjects/UsandoGuiBuilder/src/main/java/com/mycompany/usandoguibuilder/Img/bode.png");
        JLabel Cabrito = new JLabel(iconeCabrito);
        this.setPreferredSize(new Dimension(40,40));
        
        
        this.setOpaque(false);
        this.setBackground(new Color(100,100,100));
        
        this.add(Cabrito);
    }    
   
    //WIP
    //valor painel anterior
    LinkedList<JPanel> linkedMovimentosCabrito = new LinkedList();
    public void listaMovimentosCabrito(JPanel painelAtualCabrito){    
        linkedMovimentosCabrito.add(painelAtualCabrito);
    }
    
 
    //posições válidas no jogo
    //ex.:
    //nome dos paineis
    //TODO: implementar a posição ATUAL VÁLIDA de acordo com o painel
    //talvez ENUM seja interessante de usar por ser algo fixo...pensamento
    //public void posicoesVálidas(JPanel posicao){
    //    
    //}
    
}
