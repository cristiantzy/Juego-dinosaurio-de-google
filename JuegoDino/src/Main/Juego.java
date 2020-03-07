/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import clases.Niveles;
import clases.Sonido;
import clases.Cronometro;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 *
 * @author USUARIO
 */
public class Juego extends Canvas implements Runnable, KeyListener {

    BufferedImage imagen, piso, piso1;
    Graphics2D buffer;
    
    Thread hiloPrincipal = new Thread(this);
    Niveles nivel;
    public int posX, posX1, time = 100,vida=3;
    public String tiempo;
    public int min,seg,minC,segC;
    public int puntos = 0;
    public int niv=1;
    boolean actPiso = false,actTime=false;
    Sonido sonido;
    Cronometro cronometro;
    
    public Juego() {
        sonido = new Sonido();
            imagen = new BufferedImage(715, 215, BufferedImage.TYPE_INT_ARGB);
            buffer = (Graphics2D) imagen.getGraphics();
            setBackground(Color.WHITE);
            nivel = new Niveles(this);
            cronometro = new Cronometro(this);
            posX = 0;
            posX1 = 0;
            tiempo=cronometro.u;
            min=0;
            seg=0;
            minC=0;
            segC=0;
            
            this.addKeyListener(this);
    }

    @Override
    public void paint(Graphics g) {
        buffer.setColor(Color.BLACK);
        
        if (vida==0) {
            System.out.println("Perdiooooooooooooooo");
            hiloPrincipal.stop();
            sonido.detener();
        }
        if (getPuntos()==6) {
            niv++;
            setPuntos(0);
        }
        
        
        nivel.crearNivel(buffer,niv);
        
        buffer.drawString("Vidas: "+this.vida+"    Puntaje: "+this.getPuntos(), 500, 40);
        if (actTime) {
            if (min > minC &&  seg> segC) {
                minC=min;
                segC=seg;
            }
        buffer.drawString("Tiempo Max.  "+minC+" "+segC+"   Tiempo: "+tiempo, 400, 20);
        
        }else{
        buffer.drawString("Tiempo: "+tiempo, 500, 20);
        
        
        }
        
//       
        g.drawImage(imagen, 0, 0, this);
    }


    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(time);
                repaint();
            }

        } catch (Exception ex) {
            System.out.println("Error: Run " + ex);
        }

    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (KeyEvent.VK_SPACE == e.getKeyCode()) {
            System.out.println("PLAY");
            
            
            if (hiloPrincipal.getState() == Thread.State.NEW) {
                hiloPrincipal.start();
                nivel.pisoMov();
                cronometro.iniciar();
//                sonido.play();
            }else{
//                hiloPrincipal.resume();
                hiloPrincipal.stop();
                
            }
                

        }
        
        if (KeyEvent.VK_UP == e.getKeyCode()) {
//            System.out.println("arriba");
            nivel.conexionDino();

        }
//        if (KeyEvent.VK_DOWN == e.getKeyCode()) {
//            System.out.println("Abajo");
//            hiloPrincipal.suspend();
////            posDinoY+=20;
////            personaje.lienzo.repaint();
//
//        }
//        if (KeyEvent.VK_LEFT == e.getKeyCode()) {
//            System.out.println("izquierda");
//
//        }
//        if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
//            System.out.println("derecha");
//
//        }

    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    @Override
    public void update(Graphics g) {
        
        imagen = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        buffer = (Graphics2D) imagen.getGraphics();
        buffer.setColor(this.getBackground());
        buffer.fillRect(0, 0, this.getWidth(), this.getHeight());
        paint(g);
    }


}
