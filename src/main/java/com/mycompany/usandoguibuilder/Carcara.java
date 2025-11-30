/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.usandoguibuilder;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Matheus Cabral
 */
public class Carcara extends JPanel {
    
    
    public Carcara(){
        ImageIcon iconCarcara = new ImageIcon("/home/gatopreto/NetBeansProjects/UsandoGuiBuilder/src/main/java/com/mycompany/usandoguibuilder/Img/eagle.png");
        JLabel carcara = new JLabel(iconCarcara);
        
        
        
        this.setOpaque(false);
        this.setBackground(new Color(100,100,100));
        
        this.add(carcara);
    }
    
}
