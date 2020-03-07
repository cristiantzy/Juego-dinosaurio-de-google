/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author USUARIO
 */
public class Sonido implements Runnable {
    Clip sound;
    Thread hiloSound;

    public Sonido() {
        
    }
    

    public void iniciarSonido(String nombreSonido){
    try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
            sound = AudioSystem.getClip();
            sound.open(audioInputStream);
        } catch (Exception ex) {
        }
    }
    
    public void iniciarPrew(){
    iniciarSonido("C:\\Users\\USUARIO\\Documents\\INgenieria 2017-2\\DINOSAURIO\\JuegoDino\\src\\resources\\Fade.wav");
    sound.start();
   
    }
    
    public void detener(){
    sound.stop();
    
    }
    
    
    public void play(){
            new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        iniciarPrew();
                        Thread.sleep(22000);

                    }

                } catch (Exception ex) {
                    System.out.println("Error Caminar Dinosaurio" + ex);
                }
            }
        }).start();

    }

    @Override
    public void run() {
    }
    }
    
//    @Override
//    public void run() {
////        try{
////        
////        
////        }catch(){
////        
////        }
//    

