package clases;
import Main.Juego;



public class Cronometro implements Runnable {

    int seg, min, hora;
    public String u;
    private Thread h1;
    Juego juego;

   public Cronometro(Juego nueva) {
        h1 = null;
        u = "0:0.0";
        juego =nueva;
    }

    public void run() {
        Relojable();
    }

    public void Relojable() {
        seg = 0;
        min = 0;
        hora = 0;
        while (true) {
            try {
                for (int i = seg; i <= 100; i++) {
                    if (min <= 9) {
                        if (i <= 9) {
                            u = hora + ":" + min + "." + "" + i;
                        } else {
                            if (i == 99) {
                                u = hora + ":" + (min + 1) + "." + "0";
                            } else {
                                u = hora + ":" + min + "." + i;
                            }
                        }
                    } else {
                        if (i <= 9) {
                            u = hora + ":" + min + "." + "0" + i;
                        } else {
                            if (i == 99) {
                                u = hora + ":" + (min + 1) + ".0";
                            } else {
                                u = hora + ":" + min + "." + i;
                            }
                        }
                    }
                    if (i == 99) {
                        i = 0;
                        min = min + 1;
                    }
                    if (min == 60) {
                        min = 0;
                        hora = hora + 1;
                    }
                    Thread.sleep(10);
                    juego.tiempo=u;
                    juego.min=this.hora;
                    juego.seg=this.min;
                }
            } catch (Exception e) {
            }
        }
    }

    public void iniciar() {
        if (h1 == null) {
            h1 = new Thread(this);
            h1.start();
        }
    }

    public void parar() {
        if (h1 != null) {
            h1.stop();
            h1 = null;
        }
    }
}
