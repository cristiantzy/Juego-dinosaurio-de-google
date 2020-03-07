package Main;


import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author USUARIO
 */
public class VentanaD extends JFrame {

    JPanel centro;
    Juego juego;

    public VentanaD() {
        super("Dinosaurio Google");
        setSize(730, 250);
        setResizable(false);
        centro = new JPanel();
        juego = new Juego();
        juego.setBounds(0, 0, 715, 215);
        setLocation(400, 80);
        
//        setUndecorated(true); //-> le quita el borde al JFrame
        
        
        add(centro, BorderLayout.CENTER);
        centro.add(juego);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
