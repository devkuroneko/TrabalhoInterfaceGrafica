/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.usandoguibuilder;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author gatopreto
 */

public class PanelPaint extends JPanel{
    
    
    
    //cor inicial do circulo
    private Color corCirculo = Color.BLACK;
    
    //setter para modificar a cor 
    public void setCorCirculo(Color novaCor){
        this.corCirculo =  novaCor;
    }
    
    
    //método para saber se clickou no circulo
    public boolean clicouNoCirculo(int eixoX, int eixoY) {

        // valor do circulo
        double centroX = 37.5;
        double centroY = 37.5;
        double raio = 37.5;

        // formula da distância entre dois pontos (Teorema de Pitágoras)
        double distanciaEixoX = eixoX - centroX;
        double distanciaEixoY = eixoY - centroY;

        // Se a distância do clique até o centro for menor que o raio,
        // então o click é válido
        return (distanciaEixoX * distanciaEixoX + distanciaEixoY * distanciaEixoY) <= (raio * raio);
}

    //verifica se a região está vazia 
    //sem cabrito ou carcará
    public void posicaoVazia(){
        if (this == null){
            //faz nada
        }
    }

   
    
    
    @Override
    protected void paintComponent(Graphics g) {
        // 1. Chama o super para garantir que o painel limpe o fundo (evita artefatos visuais)
        super.paintComponent(g);

        // 2. Converte para Graphics2D para ter melhores controles (como antialiasing)
        Graphics2D circulo = (Graphics2D) g;

        // 3. Ativa o Antialiasing para o círculo não ficar serrilhado
        circulo.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        
        // 4. Define a cor e desenha o círculo
        circulo.setColor(corCirculo);
        
        // drawOval(x, y, largura, altura)
        // Para um círculo perfeito, largura e altura devem ser iguais
        circulo.fillOval(0, 0,75,75);
    }
    
     
}
