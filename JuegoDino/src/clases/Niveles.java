/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import static Interface.Dibujable.path;
import Main.Juego;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author USUARIO
 */
public class Niveles {

    BufferedImage fondoNivel, piso, piso1, vueloAux, vuelo, vuelo1;
    BufferedImage nivel1, nivel2, nivel3, nivel4, nivel5;
    int posX, posXpiso = 0, posXpiso1 = 0, posY, alto, ancho, bann = 0, vueloX = 700;
    Dinosaurio dinosaurio;
    Obstaculo obstaculo, obstaculo2;
    public boolean actPiso = false, restar = false;
    Juego juego;
    Thread movObstaculo,moverPiso,efecVolar;

    public Niveles(Juego nuevoJ) {
        juego = nuevoJ;
        try {
            nivel1 = ImageIO.read(new File(path + "estrella.png"));
            nivel2 = ImageIO.read(new File(path + "estrella.png"));
            nivel3 = ImageIO.read(new File(path + "estrella.png"));
            nivel4 = ImageIO.read(new File(path + "estrella.png"));
            nivel5 = ImageIO.read(new File(path + "estrella.png"));
            piso = ImageIO.read(new File(path + "pisoE.png"));
            vuelo = ImageIO.read(new File(path + "vuelo1SinFondo.png"));
            vuelo1 = ImageIO.read(new File(path + "vuelo2SinFondo.png"));
        } catch (Exception ex) {
            System.out.println("Error: Constructor Niveles " + ex);
        }
        dinosaurio = new Dinosaurio();
        obstaculo = new Obstaculo(4);
        obstaculo2 = new Obstaculo(1);
        vueloAux = vuelo;
        
        
        
        posX = 0;
        posY = 0;
        
        alto = 20;
        ancho = 20;
    }

    public void crearNivel(Graphics2D buffer, int nivel) {

        switch (nivel) {
            case 1:
                buffer.drawImage(nivel1.getScaledInstance(ancho, alto, nivel1.SCALE_SMOOTH), 10, 5, null);
                break;
            case 2:
                buffer.drawImage(nivel2.getScaledInstance(ancho, alto, nivel2.SCALE_SMOOTH), 31, 5, null);
                break;
            case 3 :
                buffer.drawImage(nivel3.getScaledInstance(ancho, alto, nivel3.SCALE_SMOOTH), 51, 5, null);
                break;
            case 4:
                buffer.drawImage(nivel4.getScaledInstance(ancho, alto, nivel4.SCALE_SMOOTH), 71, 5, null);
                break;
            case 5:
                buffer.drawImage(nivel5.getScaledInstance(ancho, alto, nivel5.SCALE_SMOOTH), 91, 5, null);
                break;
            default:
                System.out.println("Error: cambio de nivel");
        }
//
//        
        
        buffer.drawImage(piso.getScaledInstance(2000, 15, piso.SCALE_SMOOTH), posXpiso, 195, null);
       
        
        
        dinosaurio.dibujar(buffer);
        buffer.drawImage(vueloAux.getScaledInstance(20, 20, vueloAux.SCALE_SMOOTH), vueloX, 40, null);
        obstaculo.dibujarObstaculos(buffer);

    }

    
    
    public void pisoMov() {
        dinosaurio.efectoCaminar();
        obstaculo.efectoVol();
        moverObstaculo();
        efectoVolar();
        
         moverPiso= new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    while (true) {
                        posXpiso = posXpiso - 10;
                        Thread.sleep(80);
                        if (posXpiso < -1295) {
                            posXpiso = 0;
                        }
                    }

                } catch (Exception ex) {
                    System.out.println("Error: Mover Piso " + ex);
                }
            }
        });
        moverPiso.start();
    }
    

    public void conexionDino() {
        dinosaurio.saltar();

    }

    public void moverObstaculo() {
        movObstaculo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        obstaculo.posX -= 1;
                        obstaculo.obst.x -= 1;

                        if (obstaculo.posX < (-40) || obstaculo2.posX2 < (-80)) {
                            // cuando se cre otro obstaculo
                            if (obstaculo.posX < (-40) ) {
                                
                            int x = (int) (Math.random() * 4) + 1;
                            obstaculo = new Obstaculo(x);
                            }else{
                                if (obstaculo2.posX2 < (-80)) {
                                    obstaculo2.posX2=1100;
                                }
                            
                            }
                            
                            
                        }

                        if (obstaculo.obst.intersects(dinosaurio.dectObstaculo) ) {

                            System.out.println("Pierde Vida");
                            juego.vida -= 1;

//                            vidas();
                            //Suspender hillos, restar contador vidas, 
                            if (obstaculo.obst.intersects(dinosaurio.dectObstaculo)) {
                                
                            int x = (int) (Math.random() * 4) + 1;
                            obstaculo = new Obstaculo(x);
                            }else{
                            
                            }
                        } else {
                            if (obstaculo.obst.x == 2 ) {
                                System.out.println(" suma puntos");
                                juego.puntos += 2;

                            }
                        }
//                        

                        Thread.sleep(10);

                    }

                } catch (Exception ex) {
                    System.out.println("Error: Mover Piso " + ex);
                }
            }
        });
        movObstaculo.start();
    }

    public void efectoVolar() {
        efecVolar= new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (bann == 1) {
                            vueloAux = vuelo;
                            bann = 0;
                        }
                        Thread.sleep(100);
                        vueloX -= 10;
                        if (vueloX < (-100)) {
                            vueloX = 700;
                        }
                        if (bann == 0) {
                            vueloAux = vuelo1;
                            bann = 1;
                        }
                        Thread.sleep(100);

                    }

                } catch (Exception ex) {
                    System.out.println("Error Caminar Dinosaurio" + ex);
                }
            }
        });
       efecVolar.start();

    }
}
