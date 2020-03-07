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
public class Obstaculo implements Dibujable {

    int posX, posY, w, h, bann = 0, ban,posX2,posY2;
    BufferedImage fotoAux, foto1, foto2,fotoAux2;
    Rectangle2D.Double obst;

    public Obstaculo(int x) {
        try {
                   fotoAux2=ImageIO.read(new File(path + "cactus1Sin.png"));
//            x = 1;
            switch (x) {
                case 1://cactus 1
                    fotoAux = ImageIO.read(new File(path + "cactus1Sin.png"));
                    break;
                case 2://cactus 2
                    fotoAux = ImageIO.read(new File(path + "cactus2Sin.png"));

                    break;
                case 3://cactus 3

                    fotoAux = ImageIO.read(new File(path + "cactus3Sin.png"));
                    break;
                case 4://cactus 4

                    foto1 = ImageIO.read(new File(path + "vuelo1SinFondo.png"));
                    foto2 = ImageIO.read(new File(path + "vuelo2SinFondo.png"));
                    fotoAux = foto1;
                    ban = 1;
                    efectoVol();
                    break;
                default:
                    throw new AssertionError();
            };
            if (ban == 1) {
                posY = 170;
                posY2 = 110;
            } else {
                posY = 180;
                posY2=120;

            }
            w = 30;
            h = 25;
            
            posX = 600;
            
            obst = new Rectangle2D.Double(posX, posY, w, h);
            

        } catch (Exception ex) {
            System.out.println("Error: Constructor Obstaculos " + ex);
        }

    }

    public void dibujarObstaculos(Graphics2D buffer) {
        
        buffer.drawImage(fotoAux.getScaledInstance(w, h, 200), posX, posY, null);
//        
        Color x = buffer.getColor();
        buffer.setColor(Color.red);
        buffer.draw(obst);
        buffer.setColor(x);
    }
 
   

    public void efectoVol() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (bann == 1) {
                            fotoAux = foto1;
                            bann = 0;
                        }
                        Thread.sleep(200);
                        if (bann == 0) {
                            fotoAux = foto2;
                            bann = 1;
                        }
                        Thread.sleep(200);

                    }

                } catch (Exception ex) {
                    System.out.println("Error Caminar Dinosaurio" + ex);
                }
            }
        }).start();
    }
}
