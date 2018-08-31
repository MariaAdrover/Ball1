/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ball1;

/**
 *
 * @author miaad
 */
public class Pelota implements Runnable {

    private Tablero tablero;
    private volatile int x;
    private volatile int y;
    private int ancho;
    private int alto;
    private volatile double v;
    private volatile double a;
    private volatile boolean runningAnimation;
    private volatile boolean paused;

    public Pelota(Tablero t, int x, int y, int ancho, int alto, double v, double a) {
        this.tablero = t;
        this.setX(x);
        this.setY(y);
        this.setAncho(ancho);
        this.setAlto(alto);
        this.setV(v);
        this.setV(a);
        this.runningAnimation = false;
        this.paused = false;
    }

    public void inicializarVariablesAnimacion1() {
        System.out.println("inicio variables1");
        this.v = 2;
        this.a = 0;
        this.restablecerPosicion();
    }

    public void inicializarVariablesAnimacion2() {
        System.out.println("inicio variables2");
        this.v = 1;
        this.a = 0.1;
        this.restablecerPosicion();
    }

    public void mover() {
        this.v = this.v + this.a;
        System.out.println("velocidad -- > " + this.v);

        if (this.v >= 0) {
            if ((this.x + this.v) < this.tablero.getAncho() - 100) {
                this.x += this.v;
            } else {
                this.v = -(this.v);
                this.a = -(this.a);
                this.x += this.v;
            }
        } else {
            if ((this.x + this.v) > 0) {
                this.x += this.v;
            } else {
                this.v = -(this.v);
                this.a = -(this.a);
                this.x += this.v;
            }
            /*this.reiniciarVariablesACero();
            this.stopAnimation();*/
        }
    }

    public void stopAnimation() {
        this.runningAnimation = false;
        System.out.println("FIN animacion");
    }

    public void reiniciarVariablesACero() {
        this.v = 0;
        this.a = 0;
    }

    public void restablecerPosicion() {
        this.x = 0;
        this.y = this.tablero.getAlto() / 2 - 50;
    }

    @Override
    public void run() {

        while (this.runningAnimation) {
            if (!this.paused) {
                this.mover();
            }

            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
            }
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getAncho() {
        return this.ancho;
    }

    public int getAlto() {
        return this.alto;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    public boolean isRunningAnimation() {
        return runningAnimation;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setRunningAnimation(boolean runningAnimation) {
        this.runningAnimation = runningAnimation;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
