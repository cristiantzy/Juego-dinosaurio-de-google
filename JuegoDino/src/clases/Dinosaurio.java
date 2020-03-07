/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import Interface.Dibujable;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author USUARIO
 */
public class Dinosaurio implements Dibujable {

    BufferedImage auxFotoDino, fotoDino, fotoDino1;
    int posX, posY, time, ancho, alto, bann = 0;
    Rectangle2D.Double dectObstaculo;
    boolean salto;

    public Dinosaurio() {
        try {
            fotoDino = ImageIO.read(new File(path + "dinoSinFondo.png"));
            fotoDino1 = ImageIO.read(new File(path + "dinoSinFondo1.png"));
            auxFotoDino = fotoDino;
            posX = 50;
            posY = 160;

            ancho = 45;
            alto = 50;
            dectObstaculo = new Rectangle2D.Double(posX, posY, ancho, alto);

        } catch (Exception ex) {
            System.out.println("Error: Constructor Dinosaurio " + ex);

        }
    }

    public void dibujar(Graphics2D buffer) {
        buffer.drawImage(auxFotoDino.getScaledInstance(ancho, alto, fotoDino.SCALE_SMOOTH), posX, posY, null);
        Color x = buffer.getColor();
        buffer.setColor(Color.red);
        buffer.draw(this.dectObstaculo);
        buffer.setColor(x);
    }

    public void efectoCaminar() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (bann == 1) {
                            auxFotoDino = fotoDino;
                            bann = 0;
                        }
                        Thread.sleep(110);
                        if (bann == 0) {
                            auxFotoDino = fotoDino1;
                            bann = 1;
                        }
                        Thread.sleep(110);

                    }

                } catch (Exception ex) {
                    System.out.println("Error Caminar Dinosaurio" + ex);
                }
            }
        }).start();

    }

    public void saltar() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (!salto) {
                        salto = true;
                    for (int i = 0; i < 40; i++) {
                        posY -= 1;
                        Dinosaurio.this.dectObstaculo.y -= 1;
                        Thread.sleep(2);
                    }
                        for (int i = 40; i < 90; i++) {
                        posY -= 1;
                        Dinosaurio.this.dectObstaculo.y -= 1;
                        Thread.sleep(15);
                            
                        }
                    for (int i = 0; i <= 90; i++) {
                        posY += 1;
                        Dinosaurio.this.dectObstaculo.y += 1;
                        Thread.sleep(4);

                    }
                    
                    
                    salto=false;
                    posY=160;
                        Dinosaurio.this.dectObstaculo.y =160;
                   
                    }
                } catch (Exception ex) {
                    System.out.println("Error Dino Salto " + ex);
                }
            }
        }).start();
    }

}
